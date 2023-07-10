package com.lgq.sharding.entity;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: AlarmEvent
 * @author: luogongquan
 * @since: 2023/7/10 10:30
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AlarmEvent{

    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     **/
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 创建人id
     **/
    private Long creatorId;

    /**
     * 创建人姓名
     **/
    private String creatorName;

    /**
     * 创建时间
     **/
    private Date createTime;

    /**
     * 删除标识（0：未删除，1：已删除）
     **/
    private Integer isDeleted;

    /**
     * 车架号
     **/
    private String vin;

    /**
     * 车牌号
     **/
    private String vno;

    /**
     * 风险等级
     * 0：低风险
     * 1：中风险
     * 2：高风险
     **/
    private Integer riskLevel;
    private String riskLevelStr;

    /**
     * 报警开始时间
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date alarmStartTime;

    /**
     * 报警结束时间
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date alarmEndTime;

    /**
     * 风险类型
     * 0: 超速驾驶
     * 1: 激烈驾驶
     * 2: 分心驾驶
     * 3: 疲劳驾驶
     **/
    private Integer riskType;
    private String riskTypeStr;

    /**
     * 处理状态
     * 0:  未处理
     * 1:  已人工处理
     * 2：无需处理
     * 3：超时处理
     **/
    private Integer handleStatus;
    private String handleStatusStr;

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
    private Integer handleResults;
    private String handleResultsStr;

    private String duration;

    /**
     * 设备品牌id
     **/
    private Integer deviceBrand;
    private String deviceBrandName;
    /** 设备型号id*/
    private Integer deviceType;
    private String deviceTypeName;
    /** 客户名称id*/
    private String customerCode;
    private String customerName;

    /**
     * 分表字段 传递列表报警开始时间“yyyy-MM-dd HH:mm:ss”
     **/
    @NotNull(groups = Query.class, message = "startTime不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    /**
     * 运营城市行政区划
     */
    private String serveCityCode;

    /**
     * 客户类群编码
     */
    private String customerClass;

    /**
     * 客户类群
     */
    private String customerClassName;


    /**
     * 集团code
     */
    private String subCustomer;

    /**
     * 集团
     */
    private String subCustomerName;


    /**
     * 车辆品牌名称
     **/
    private String carBrandName;

    /**
     * 运营城市
     **/
    private String cityName;

    /**
     * 设备号
     **/
    private String deviceNo;


    /**
     * 车辆型号名称
     **/
    private String carModelName;


    /**
     * 产生来源 1：聚合，2：护航
     */
    private Integer generateSource;
    private String generateSourceName;

    public interface Query {
    }


}