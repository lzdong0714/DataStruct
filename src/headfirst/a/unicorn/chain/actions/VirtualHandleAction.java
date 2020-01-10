package headfirst.a.unicorn.chain.actions;

import headfirst.a.unicorn.chain.Proxy.ActionChain;
import headfirst.a.unicorn.chain.Proxy.GenericAbstractAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年01月09日 14:19:00
 */
public class VirtualHandleAction extends GenericAbstractAction {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void doAction(Object input, Object output, ActionChain actionChain) throws IOException {
        logger.info("实际处理类");
        actionChain.doAction(input,output);
        logger.info("其实啥也不干");

    }
}
