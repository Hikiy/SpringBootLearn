package com.hiki.springbootlearn.schedulerTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class SchedulerTask {
    //用于格式化时间
    private static final SimpleDateFormat dataFomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //5秒一次报时
    @Scheduled(fixedRate = 5000)
    //@Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
    //@Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
    //@Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按 fixedRate 的规则每5秒执行一
    private void time(){
        System.out.println("现在时间：" + dataFomat.format(new Date()));
    }
}
