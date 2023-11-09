package com.lgq.strategy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 设备自检上报信息
 * @ClassName: DeviceReportInfo
 * @author: luogongquan
 * @since: 2023/8/1 10:19
 */
@Data
@Builder
@AllArgsConstructor
public class DeviceReportInfo {
    /**
     * 0位 ADAS摄像头连接状态：0未连接、1已连接
     */
    private Integer adasStatus;
    /**
     * DMS摄像头连接状态：0未连接、1已连接
     */
    private Integer dmsStatus;
    /**
     * GPS信息状态：0无、1有
     */
    private Integer gpsStatus;
    /**
     * 4G信号强度
     */
    private Integer fourthGeneration;
    /**
     * 设备温度
     */
    private Integer temperature;

    /**
     *版本信息
     */
    private String version;

    private List<SdInfo> sdInfoList;
    /**
     * 上报时间
     */
    private Long ts;

    /**
     * 车架号
     */
    private String carVin;

    @Data
    @Builder
    @AllArgsConstructor
    public static class SdInfo{
        /**
         *  0位 SD卡读写状态：0异常、1正常
         * 读写状态
         */
        private Integer readWriteStatus;

        /**
         * 1位 SD卡存储状态：0异常、1正常
         * 存储状态
         */
        private Integer storageStatus;
        /**
         * 总容量（m）
         */
        private Long totalCapacity;
        /**
         * 剩余容量（m）
         */
        private Long remainingCapacity;
    }

}
