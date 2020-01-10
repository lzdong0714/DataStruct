package headfirst.a.unicorn.chain.actions;

import headfirst.a.unicorn.chain.Proxy.ActionChain;
import headfirst.a.unicorn.chain.Proxy.GenericAbstractAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @Author: Liu Zhendong
 * @Description  异常处理器
 * @createTime 2020年01月09日 14:12:00
 */
public class ExceptionAction extends GenericAbstractAction {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void doAction(Object input, Object output, ActionChain actionChain) throws IOException {
        try {
            logger.info("进入异常处理器");
            actionChain.doAction(input, output);
            logger.info("无异常");
        }catch (IOException ex){
            throw ex;
        }catch (Exception ex){
            logger.info("捕捉到了异常，继续处理");
        }
    }
}
