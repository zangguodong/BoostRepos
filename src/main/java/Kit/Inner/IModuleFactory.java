package Kit.Inner;

import Kit.Annotation.ImplBy;
import Kit.Constant.ClassType;
import WTF.MyInter;
import com.beust.jcommander.internal.Maps;
import com.google.inject.*;
import com.google.inject.Provider;
import org.testng.ITestContext;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by zangguodong on 2017/6/26.
 */
public class IModuleFactory implements org.testng.IModuleFactory {
    private TestModule module;
    private Class<?> clazz;
    @Override
    public Module createModule( ITestContext context, Class<?> testClass ) {
        Map<Class<?>,ClassType> map=getService(testClass);
        clazz=MyInter.class;
        module=new TestModule();
        map.forEach((e,v)->{
                module.put(e,mkProvider(e,v));

        });
        return module;
    }
    public Map<Class<?>,ClassType> getService( Class<?> clazz){
        Field[] fields=clazz.getDeclaredFields();
        Map<Class<?>,ClassType> map= Maps.newHashMap();
        for(Field field:fields){
            if(field.isAnnotationPresent(javax.inject.Inject.class)){
                if(field.getType().isInterface()){
                    map.put(field.getType(),ClassType.Tinterface);
                }else if(field.getType().isEnum()){
                    map.put(field.getType(),ClassType.Tenum);
                }else map.put(field.getType(),ClassType.Tclass);
            }
        }return map;
    }
    private Provider mkProvider(Class clazz,ClassType type){
        Class clz=clazz;
        if(type.equals(ClassType.Tinterface)&&clazz.isAnnotationPresent(ImplBy.class)){
            clz=((ImplBy)clazz.getAnnotation(ImplBy.class)).clz();
        }
        Class finalClz=clz;
        Module module=new Module() {
            @Override
            public void configure( Binder binder ) {
                if(finalClz.isInterface())
                    binder.bind(clazz).to(finalClz);
            }
        };
        Injector injector= Guice.createInjector(module);
        return new Provider() {
            @Override
            public Object get() {
                return injector.getInstance(finalClz);
            }
        };
    }
    private void initDefaultClassConfig(){

    }
}
