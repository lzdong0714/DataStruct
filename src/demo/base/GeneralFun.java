package demo.base;

import demo.base.inter.Base;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年05月19日 13:59:00
 */
public class GeneralFun {

    public List<? extends Base>[] queryPrescriptEntityList(Integer clinicId, String outpreCode) {
        List<? extends Base> ret[] = new List[4];
        return ret;
    }



    private <T extends Base> List<T>[] divideEntityCollect(Collection<T> collection){
        LinkedList<T> ret[]= new LinkedList[countEnum.values().length];
        return ret;
    }
    enum countEnum{
        ONE, TWO
    }
}


