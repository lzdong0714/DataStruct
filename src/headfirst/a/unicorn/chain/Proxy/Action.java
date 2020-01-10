package headfirst.a.unicorn.chain.Proxy;

import java.io.IOException;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年01月09日 12:04:00
 */
public interface Action {


    public void doAction(Object input, Object output, ActionChain actionChain)throws IOException;

}
