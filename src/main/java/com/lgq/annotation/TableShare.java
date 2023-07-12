package com.lgq.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface TableShare {
    String desc() default "分表切面";

    String logicName() default "alarm_event";

    String startTimeParam() default "startTime";

    String endTimeParam() default "endTime";

    String eqTime() default "alarmStartTime";
}
