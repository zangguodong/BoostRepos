package ScreenShot.Network.MouseEventGroup;

import lombok.Getter;

/**
 * Created by zangguodong on 2017/6/15.
 */
@Getter
public class ClickRight {
    private static ClickRight clickRight;
    private int x,y;
    private ClickRight(int x,int y){
        this.x=x;
        this.y=y;
    }
    public ClickRight getInstance(int x,int y){
        if (clickRight!=null) return clickRight;
        else return new ClickRight(x,y);
    }
}
