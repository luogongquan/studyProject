package com.lgq.sharding.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Data
public class AlarmStatQuery  {

    private Integer pageSize;

    private Integer pageNum;
    /**
     * 统计开始时间不能为空 yyyy-MM-dd hh:mm:ss
     **/
//    @NotBlank(groups = Query.class,message = "统计开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    /**
     * 统计结束时间不能为空 yyyy-MM-dd hh:mm:ss
     **/
//    @NotBlank(groups = Query.class, message = "统计结束时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;


    /**
     * 显示天数
     **/
    private Integer days;

    /**
     * 运营城市列表
     */
    private List<String> serveCityCodes;


    /**
     * 客户类群编码
     */
    private String customerClass;



    /**
     * 集团code
     */
    private String subCustomer;

    /**
     * 客户ID
     */
    private String clienteleId;


    /**
     * 风险等级
     * 0：低风险
     * 1：中风险
     * 2：高风险
     **/
    private String riskLevel;

    /**
     * 处理状态
     * 0:  未处理
     * 1:  已人工处理
     * 2：无需处理
     * 3：超时处理
     **/
    private Integer handleStatus;

    private List<String> carVins;

    /**
     * 统计类别 car:车辆；custom：客户
     */
    private String countType;

    /**
     * 是否查询上一次的
     */
    private boolean lastFlag;

    /**
     * 是否只查一张表
     */
    private String alone;

    /**
     * 风险类型
     * 0: 超速驾驶
     * 1: 激烈驾驶
     * 2: 分心驾驶
     * 3: 疲劳驾驶
     **/
    private String riskType;

    /**
     * 处理方式 0: 不需干预  1: 语音下发 2：语音对讲 3：人工干预
     */
    private String handleType;
    /**
     * 处理结果
     * 0: 偏移/遮挡
     * 1: 图像异常
     * 2: 误报
     * 3: 无风险
     * 4: 语音下发
     * 5: 语音对讲
     * 6: 人工电话
     **/
    private String handleResults;

    public boolean isLastFlag(){
        return this.lastFlag;
    }
}