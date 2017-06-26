package Kit.Inner;

import Kit.Annotation.ImplBy;
import Test.MyInter;
import com.beust.jcommander.internal.Lists;
import com.google.inject.*;
import com.google.inject.Provider;
import org.testng.ITestContext;

import javax.inject.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by zangguodong on 2017/6/26.
 */
public class IModuleFactory implements org.testng.IModuleFactory {
    private TestModule module;
    private Class<?> clazz;
    @Override
    public Module createModule( ITestContext context, Class<?> testClass ) {
        List<Class<?>> services=getService(testClass);
        clazz=MyInter.class;
        module=new TestModule();
        for (Class<?> cla:services) {
            module.put(cla, provider(cla));
        }
        return module;
    }
    public List<Class<?>> getService(Class<?> clazz){
        Field[] fields=clazz.getDeclaredFields();
        List<Class<?>> list= Lists.newArrayList();
        for(Field field:fields){
            if(field.isAnnotationPresent(javax.inject.Inject.class)){
                System.out.println("that is "+field.getName());
                list.add(field.getType());
            }
        }return list;
    }
    private Provider provider(Class clazz){
        if(clazz.isAnnotationPresent(ImplBy.class)){
            Class clz=((ImplBy)clazz.getAnnotation(ImplBy.class)).clz();
            Module module=new Module() {
                @Override
                public void configure( Binder binder ) {
                    binder.bind(clazz).to(clz);
                }
            };
            Injector injector= Guice.createInjector(module);
            return new Provider() {
                @Override
                public Object get() {
                    return injector.getInstance(clz);
                }
            };
        }return null;
    }
}
