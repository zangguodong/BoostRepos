package Kit;

import Kit.Inner.TestBase;
import WTF.*;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * Created by zangguodong on 2017/6/26.
 */
public class ClientTest extends TestBase{
    @Inject
    private MyInter myInter;
    @Inject private Run run;
    @Inject private Runner runner;
    public void test(){
        myInter.say();
        run.run();
        runner.run();
    }
}
