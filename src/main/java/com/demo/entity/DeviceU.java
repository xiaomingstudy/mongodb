package com.demo.entity;

import javax.persistence.Entity;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by 27259 on 2017/3/25.
 */
public class DeviceU  {
    public static String getDemoServerScriptUrl(String ip, Integer port,
                                                Long scriptId) {
        return "http://" + ip + ":" + port + "/demo/services/wsdevice/script/"
                + scriptId;
    }
    //参数化
    public static String getDemoServerScriptUrl(String ip, Integer port,
                                                String uuid) {
        return "http://" + ip + ":" + port + "/demo/services/wsdevice/scriptParam/"
                + uuid;
    }

    public static String getDemoServerAppUrl(String ip, Integer port, Long appId) {
        return "http://" + ip + ":" + port + "/demo/services/wsdevice/app/"
                + appId;
    }

    public static String getDemoServerResultUrl(String ip, Integer port,
                                                Long caseId, Long caseResultId) {
        return "http://" + ip + ":" + port + "/demo/services/wsdevice/result/"
                + caseId + "/" + caseResultId;
    }

    public static String getDemoJobStatusUrl(String ip, Integer port) {
        return "http://" + ip + ":" + port + "/demo/services/wsdevice/status/";
    }

    public static String getMatcServer(String ip, Integer port) {
        return "http://" + ip + ":" + port + "/api";
    }



    /**
     *
     * @param status
     * @return
     */
    public static Integer convertAutoTestResultToOwn(String status) {
        if (DeviceEvery.AUTO_TEST_RESULT_STATUS_SUCCESS.equals(status)) {
            return 3;
        }
        if (DeviceEvery.AUTO_TEST_RESULT_STATUS_FAILED.equals(status)) {
            return 4;
        }
        return 1;
    }
}
