package think.in.java.a.onjava8.my;

import headfirst.a.unicorn.chain.enumm.Index;

import java.util.List;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年06月23日 10:58:00
 */
public class InitService {

    public String initA(int index, List<String> list){
        if(list == null || list.isEmpty()) return "empty process A";
        if(list.size() < index) return String.format("%02d", index);
        return list.get(index);
    }


    public String initB(int index, List<String> list){
        if(list == null || list.isEmpty()) return "empty process B";
        if(list.size() < index) return String.format("%02d", index);
        return list.get(index);
    }


    public String initC(int index, List<String> list){
        if(list == null || list.isEmpty()) return "empty process C";
        if(list.size() < index) return String.format("%02d", index);
        return list.get(index);
    }

    /**
     * 同样的出参入参的函数声明，等价简化的策略模式，用枚举避免了工厂模式
     * @param index
     * @return
     */
    public InitFunction initService(int index){
        switch (Index.values()[index]){
            case SHOW:return this::initA;
            case CHECK:return this::initB;
            case HIDDEN:return this::initC;
            default:return this::initA;
        }
    };
}
