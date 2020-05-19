package demo.base.inter;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Liu Zhendong
 * @Description
 * @createTime 2020年05月18日 15:40:00
 */
@Data
public class MainShow {

    private BaseCheck data = new BaseCheck();


    public static void main(String[] args) {
        MainShow mainShow = new MainShow();
        BaseCheck data = mainShow.getData();
        data.setAge(1);
        data.setName("lll");
        data.setTime(LocalDateTime.now());


    }


}
