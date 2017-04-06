package com.demo.entity;

/**
 * Created by 27259 on 2017/3/24.
 */
public class DeviceEvery {
    // 设备状态(1：可用、2：使用中、3:未连接、4：维护中)
    public static final Integer DEVICE_STATUS_VALID = 1;

    public static final Integer DEVICE_STATUS_USING = 2;

    public static final Integer DEVICE_STATUS_UN_CONNECT = 3;

    public static final Integer DEVICE_STATUS_MAINTAIN = 4;

    // 设备删除状态(0未被删除,1已删除)
    public static final Integer DEVICE_IS_DELETE_FLAG = 1;

    public static final Integer DEVICE_IS_NOT_DELETE = 0;
    // 设备传输模式（图片）
    public static final Integer DEVICE_TRANSPORTMODE_IMAGE = 1;
    // 设备传输模式（视频）
    public static final Integer DEVICE_TRANSPORTMODE_H264 = 2;

    // 查询自动化设备类型，1安卓实体机，2安卓模拟器,3、IOS实体机,4、IOS模拟器
    public static final Integer AUTO_DEVICE_TYPE_ANDROID_REAL = 1;

    public static final String AUTO_DEVICE_TYPE_ANDROID_REAL_TEXT = "android_devices";


    // 查询应用类型，1：native,2:hybrid,3:webapp,4:wechat
    public static final Integer AUTO_DEVICE_APP_TYPE_NATIVE = 1;

    public static final Integer AUTO_DEVICE_APP_TYPE_HYBRID = 2;

    public static final Integer AUTO_DEVICE_APP_TYPE_WEB = 3;

    public static final Integer AUTO_DEVICE_APP_TYPE_WECHAT = 4;

    public static final String AUTO_DEVICE_APP_TYPE_NATIVE_TEXT = "native";

    public static final String AUTO_DEVICE_APP_TYPE_HYBRID_TEXT = "hybrid";

    public static final String AUTO_DEVICE_APP_TYPE_WEB_TEXT = "web";

    public static final String AUTO_DEVICE_APP_TYPE_WECHAT_TEXT = "wechat";

    public static final String AUTO_DEVICE_APP_TYPE = "app_type";

    public static final String AUTO_DEVICE_ANDOID_VERSION = "apk_min_sdk_version";



    public static final String AUTO_DEVICE_TYPE_ANDROID_REAL_TEXT_JOB = "android_device_jobs";
    public static final String AUTO_DEVICE_TYPE_ANDROID_EMULATOR_TEXT_JOB = "android_emulator_jobs";
    public static final String AUTO_DEVICE_TYPE_IOS_REAL_TEXT_JOB = "ios_device_jobs";

    // 数据来源（0:CSAE表、1：CSAERESULT表）
    public static final Integer TEST_RESULT_FLAG_FROM_CSAE = 0;
    public static final Integer TEST_RESULT_FLAG_FROM_CSAERESULT = 1;

    public static final String JOB_PARAMETER_SCRIPT_URL = "script_url";

    public static final String JOB_PARAMETER_APP_URL = "app_url";

    public static final String JOB_PARAMETER_RESULT_URL = "result_url";

    public static final String JOB_PARAMETER_DEVICE_ID = "device_id";

    public static final String JOB_PARAMETER_SETTING = "setting";

    public static final String JOB_STATUS_URL = "job_status_url";

    // 测试结果（1：待执行、2：执行中、3：成功、4：失败、5：挂起）
    public static final Integer TEST_RESULT_WAITING = 1;
    public static final Integer TEST_RESULT_RUNNING = 2;
    public static final Integer TEST_RESULT_SUCCESS = 3;
    public static final Integer TEST_RESULT_FAIL = 4;
    public static final Integer TEST_RESULT_SUSPENDING = 5;

    public static final String AUTO_RESULT_DEVICE_LOG = "device.log";
    public static final String AUTO_RESULT_JOB_LOG = "job.log";
    public static final String AUTO_RESULT_JSON = "result.json";

    public static final String AUTO_TEST_RESULT_STATUS_SUCCESS = "passed";
    public static final String AUTO_TEST_RESULT_STATUS_FAILED = "failed";
    public static final String AUTO_TEST_RESULT_STATUS_RUNNING = "running";

    public static final String FILE_TYPE_PNG = "png";
    public static final String FILE_TYPE_ZIP = "zip";

    public static final String RESULT_FILE_NAME = "result_v0.1.zip";

    public static final String MD5_CHECK_SUM = ".md5sum";
    // 自动化测试标识
    public static final Integer AUTO_TEST_FLAG = 1;


    // 应用平台（1：android、2：ios）
    public static final Integer AUTO_APP_PLATFORM_ANDROID = 1;


    // 自动化测试设备类型标识，1实体机，2模拟器
    public static final Integer AUTO_DEVICE_TYPE_FOR_TEST_REAL = 1;


}
