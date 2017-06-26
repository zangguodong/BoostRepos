package ScreenShot.Network;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by zangguodong on 2017/6/15.
 */
@AllArgsConstructor
public enum MouseEvent {
    clickRight("ClickRight"),
    clickLeft("ClickLeft"),
    drag("Drag"),
    clickLeftDown("ClickLeftDown"),
    clickLeftUp("ClickLeftUp"),
    clickRightDown("ClickRightDown"),
    clickRightUp("ClickRightUp");
    @Getter
    private String desc;
}
