package Kit.Inner;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * Created by zangguodong on 2017/6/26.
 */
@Test
@Guice(moduleFactory = IModuleFactory.class)
public abstract class TestBase {
}
