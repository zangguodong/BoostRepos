package Kit.Inner;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import Test.MyInter;
import Test.Run;
import javax.inject.Inject;

/**
 * Created by zangguodong on 2017/6/26.
 */
@Test
@Guice(moduleFactory = IModuleFactory.class)
public class TestBase {
    @Inject private MyInter myInter;
    @Inject private Run run;
    public void test(){
        myInter.say();
        run.run();
    }
}
