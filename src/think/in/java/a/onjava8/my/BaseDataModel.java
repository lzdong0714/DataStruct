package think.in.java.a.onjava8.my;

import lombok.Data;

import java.util.function.Function;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年10月20日 13:33:00
 */
@Data
public class BaseDataModel {

    private int x;

    private int y;

    private int add(int x, int y){return 0;}

    private Function<BaseDataModel,Integer> cal;


    private Integer result;
    public Integer getResult(){
        if(this.cal != null)return cal.apply(this);
        else return 0;
    }
}
