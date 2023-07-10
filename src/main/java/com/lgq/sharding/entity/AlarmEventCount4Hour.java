package com.lgq.sharding.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 每小时风险各类统计
 * @ClassName: AlarmEventCount4Hour
 * @author: luogongquan
 * @since: 2023/5/26 15:56
 */
@NoArgsConstructor
@Data
public class AlarmEventCount4Hour{
    /**
     * 总数
     */
    private int total;
    /**
     * 超速总数
     */
    private int speedTotal;
    /**
     * 激烈驾驶总数
     */
    private int fierceTotal;
    /**
     * 分心驾驶总数
     */
    private int distractTotal;
    /**
     * 疲劳驾驶总数
     */
    private int tiredTotal;

    /**
     * 时间段
     */
    private String hourTime;

    /**
     * 超速驾驶处理数量相对于总数的占比
     */
    private String speedRate;
    /**
     * 分心驾驶处理数量相对于总数的占比
     */
    private String distractRate;
    /**
     * 激烈驾驶处理数量相对于总数的占比
     */
    private String fierceRate;
    /**
     * 疲劳驾驶处理数量相对于总数的占比
     */
    private String tiredRate;

    public int getTotal(){
        return this.speedTotal+this.distractTotal+this.fierceTotal+this.tiredTotal;
    }

    public String getSpeedRate(){
        if(this.total==0){
            return "0%";
        }
        return String.format("%.2f", (float) this.speedTotal / (float) this.total * 100) + "%";
    }
    public String getDistractRate(){
        if(this.total==0){
            return "0%";
        }
        return String.format("%.2f", (float) this.distractTotal / (float) this.total * 100) + "%";
    }
    public String getFierceRate(){
        if(this.total==0){
            return "0%";
        }
        return String.format("%.2f", (float) this.fierceTotal / (float) this.total * 100) + "%";
    }

    public String getTiredRate(){
        if(this.total==0){
            return "0%";
        }
        return String.format("%.2f", (float) this.tiredTotal / (float) this.total * 100) + "%";
    }

    public void setTotal(){
        this.total=this.speedTotal+this.distractTotal+this.fierceTotal+this.tiredTotal;
    }

    public void add(AlarmEventCount4Hour add){
        speedTotal+=add.getSpeedTotal();
        fierceTotal+=add.getFierceTotal();
        distractTotal+=add.getDistractTotal();
        tiredTotal+=add.getTiredTotal();
    }

    public AlarmEventCount4Hour(String hourTime){
        this.hourTime=hourTime;
        this.speedTotal=0;
        this.fierceTotal=0;
        this.distractTotal=0;
        this.tiredTotal=0;
    }
}
