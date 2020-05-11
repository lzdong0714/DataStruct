package demo.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Liu Zhendong
 * @Description  集合泛型问题
 * @createTime 2020年05月11日 11:42:00
 */
public class CollectAboutTemplate {

    enum BUTTON{RED, YELLOW, BLUE, BLACK}

    @Data
    @Accessors(chain = true)
    class Base{
        int age;
        String info;
    }
    @Data
    @Accessors(chain = true)
    class ContextBase extends Base{
        int sex;
        String edu;
    }
    @Data
    @Accessors(chain = true)
    class HolderBase extends Base{
        int beautiy;
    }
    @Data
    @Accessors(chain = true)
    class ReverseBase extends Base{
        int vote;
    }

    @Data
    @Accessors(chain = true)
    class Node<E extends Base>{

        private E contextBase;
        private String baseAppend_1;
        private float baseAppend_2;
    }


    private Map<BUTTON, List<Base>> map;


    public void setMap(Map<BUTTON, List<Base>> map) {
        this.map = map;
    }

    public Map getMap(){
        return map;
    }

    public static void main(String[] args) {

        CollectAboutTemplate collectAboutTemplate = new CollectAboutTemplate();

        HolderBase holderBase = collectAboutTemplate.new HolderBase();
        holderBase.setBeautiy(1)
        .setAge(100);
        LinkedList<HolderBase> holderBases = new LinkedList<>();
        holderBases.add(holderBase);

        HashMap<BUTTON, List<? extends Base>> outMap = new HashMap<>();
        outMap.put(BUTTON.BLACK, holderBases);

    }
}
