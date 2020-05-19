package demo.base.inter;

import lombok.Data;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年05月18日 15:36:00
 */

@Data
public class BaseCheck extends Base implements Relate {

    private String nickName;
    private float money;


}
