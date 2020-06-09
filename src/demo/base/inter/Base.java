package demo.base.inter;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年05月18日 15:35:00
 */
@Data
public class Base {

    private String  name;
    private int age;
    private LocalDateTime time;
}
