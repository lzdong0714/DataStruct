package demo.base;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.IsoFields;
import java.util.stream.IntStream;

/**
 * @Author: Liu Zhendong
 * @Description 获取时间戳序号
 * @createTime 2020年05月08日 13:47:00
 */
public class TimePrint {

    public static void main(String[] args) {
        TimePrint timePrint = new TimePrint();
//        IntStream.range(0, 20)
//                .forEach(item ->{
//                    try {
//                        Thread.sleep(200);
//                        timePrint.millPrint();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                });
//
        timePrint.PrintTodayBegin();
    }

    private long timePrint(){
        long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println(second);
        return second;
    }

    private long millPrint(){
        long second = LocalDateTime.now()
                .toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(second);
        return second;
    }

    private void PrintTodayBegin(){
        LocalDateTime todayBegin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime todayEnd   = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);


        System.out.println("today's todayBegin " + todayBegin);
        System.out.println("today's todayEnd " + todayEnd);
    }
}
