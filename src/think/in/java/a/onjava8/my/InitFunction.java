package think.in.java.a.onjava8.my;

import java.util.List;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年06月23日 17:32:00
 */
@FunctionalInterface
public interface InitFunction {
    String init(int index, List<String> list);
}
