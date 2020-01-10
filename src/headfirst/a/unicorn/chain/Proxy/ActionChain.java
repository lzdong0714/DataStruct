package headfirst.a.unicorn.chain.Proxy;

import java.io.IOException;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年01月09日 12:00:00
 */
public interface ActionChain {

    public void doAction(Object inputObj, Object outputObj) throws IOException;
}
