package Kit.Inner;

import com.beust.jcommander.internal.Maps;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;

import java.util.Map;

/**
 * Created by zangguodong on 2017/6/26.
 */
public class TestModule extends AbstractModule{
    private Map<Class<?>,Provider> map= Maps.newHashMap();
    public void put(Class<?> clz,Provider provider){map.put(clz,provider);}
    @Override
    protected void configure() {
        map.forEach((k,v)->{
            bind(k).toProvider(v);
        });
    }
}
