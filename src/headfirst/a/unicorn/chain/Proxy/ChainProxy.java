package headfirst.a.unicorn.chain.Proxy;

import headfirst.a.unicorn.chain.actions.ContextPersistenceAction;
import headfirst.a.unicorn.chain.actions.ExceptionAction;
import headfirst.a.unicorn.chain.actions.VirtualHandleAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年01月09日 11:58:00
 */
public class ChainProxy extends GenericAbstractAction {
    private static Logger logger = LoggerFactory.getLogger(ChainProxy.class);
    private List<ActionChain> actionChains;

    public ChainProxy(ActionChain action){this(Arrays.asList(action));}
    public ChainProxy(List<ActionChain> actionChains){
        this.actionChains = actionChains;
    }

    // 获取属性，但是设定为不可变对象
    public List<ActionChain> getActionChains(){return Collections.unmodifiableList(actionChains); }


    @Override
    public void doAction(Object input, Object output, ActionChain actionChain) throws IOException {
            List<Action> actions = getActions();
            if(actions == null || actions.size() == 0){
                // 这里没有actions 调用chain的filter
                actionChain.doAction(input, output);
                return;
            }else {
                // 调用这里要使用的actioin
                VirtualActionChain virtualActionChain = new VirtualActionChain(actions, actionChain);
                virtualActionChain.doAction(input, output);

                return;
            }
    }

    private void addAction(Action addAction, Class<Action> beforeAction){
        if(actions.isEmpty()){
            actions.add(addAction);
            return;
        }

        for(int index=0; index<actions.size(); index++){
            Action action = actions.get(index);
//             0TODO
//    //  写一个配配置文件，可以有序的添加filter
//            boolean is =
//            if()
        }



    }

    LinkedList<Action> actions = new LinkedList<>();
    private List<Action> getActions() {
        actions.add(new ContextPersistenceAction());
        actions.add(new ExceptionAction());
        actions.add(new VirtualHandleAction());
        return actions;
    }

    private void doInnerAction(Object in, Object out, ActionChain actionChain){

    }


    private static class VirtualActionChain implements ActionChain{
        private final ActionChain originalChain;
        private final List<Action> additionActions;
        private final int size ;
        private int currentPosition = 0;

        private VirtualActionChain(List<Action> actionList, ActionChain actionChain){
            this.additionActions = actionList;
            this.size = actionList.size();
            this.originalChain = actionChain;
        }

        //
        @Override
        public void doAction(Object inputObj, Object outputObj) throws IOException {
            if(currentPosition == size){
                originalChain.doAction(inputObj, outputObj);
            }else {
                currentPosition ++;
                Action nextAction = additionActions.get(currentPosition - 1);

                nextAction.doAction(inputObj, outputObj, this);
            }
        }
    }

}
