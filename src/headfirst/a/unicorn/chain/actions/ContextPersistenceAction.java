package headfirst.a.unicorn.chain.actions;

import headfirst.a.unicorn.chain.Proxy.ActionChain;
import headfirst.a.unicorn.chain.Proxy.GenericAbstractAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: Liu Zhendong
 * @Description 建立一个执行上下文
 * @createTime 2020年01月09日 13:54:00
 */
public class ContextPersistenceAction extends GenericAbstractAction {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void doAction(Object input, Object output, ActionChain actionChain) throws IOException {
            // TODO 建立一个上下文实体
            logger.info("建立一个上下文实体");

            actionChain.doAction(input, output);

            logger.info("After : 建立一个上下文实体");

    }
}
