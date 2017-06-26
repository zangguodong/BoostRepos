package ScreenShot.Network.MouseEventGroup;

import lombok.Getter;

/**
 * Created by zangguodong on 2017/6/15.
 */
@Getter
public class ClickLeft {
    private static ClickLeft clickLeft;
    private int x,y;
    private ClickLeft(int x,int y){
        this.x=x;
        this.y=y;
    }
    public ClickLeft getInstance(int x,int y){
        if (clickLeft!=null) return clickLeft;
        else return new ClickLeft(x,y);
    }
}
