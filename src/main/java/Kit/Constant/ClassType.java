package Kit.Constant;

import lombok.AllArgsConstructor;

/**
 * Created by zangguodong on 2017/6/26.
 */
@AllArgsConstructor
public enum  ClassType {
    Tenum("enum"),
    Tclass("class"),
    Tinterface("interface"),
    TOthers("others");
    private String desc;
}
