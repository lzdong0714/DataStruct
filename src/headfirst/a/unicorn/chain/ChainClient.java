package headfirst.a.unicorn.chain;

import headfirst.a.unicorn.chain.Proxy.ActionChain;
import headfirst.a.unicorn.chain.Proxy.ChainProxy;

import java.io.IOException;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年01月09日 14:52:00
 */
public class ChainClient {



    public static void main(String[] args) throws IOException {
        ActionChain actionChain = new ActionChain() {
            @Override
            public void doAction(Object inputObj, Object outputObj) throws IOException {
                System.out.println(Thread.currentThread().getName() + "我啥也不想干");
            }
        };
        ChainProxy chainProxy = new ChainProxy(actionChain);
        chainProxy.doAction("input", "output", actionChain);


    }
}
