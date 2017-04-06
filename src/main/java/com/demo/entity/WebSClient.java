package com.demo.entity;


import com.demo.service.device.JobTaskService;
import org.apache.commons.httpclient.NameValuePair;
import javafx.print.PrinterJob;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.type.TypeReference;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by 27259 on 2017/3/25.
 */
public class WebSClient {


    public WebSClient() {
    }

    public WebSClient(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    private String endpointUrl = "192.168.235.132:3333";

    /**
     * 获取所有slave节点上的设备
     *
     * */

    public List<MacacaDeviceInfo> getDeviceInfos() throws Exception {

        String url = endpointUrl +"/api/matc/devices/devices";
        WebClient client = WebClient.create(url);
        Response res = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get();
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) res.getEntity());
        List<MacacaDeviceInfo> returnList =  parser.readValueAs(new TypeReference<List<MacacaDeviceInfo>>(){});
        return returnList;
    }

    /**
     * 刷新所有slave节点上的设备
     *
     * */

    public String deviceRefresh() throws Exception {
        String url = "http://" + endpointUrl + "/api/matc/devices/devices_refresh";
        WebClient client3 = WebClient.create(url);
//
        Response res = client3.accept(MediaType.TEXT_PLAIN).type(MediaType.TEXT_PLAIN).get();
        if (res.getStatus() == 200) {
            return IOUtils.toString((InputStream) res.getEntity());
        }
        return "";
    }



    /**
     * 根据设备唯一标识、控制类型，控制设备
     */
    public String controlDeviceById(String hpDeviceId, String control)
            throws Exception {
        String url = "http://" + endpointUrl + "/api/devices/" + hpDeviceId
                + "/" + control;

        WebClient client = WebClient.create(url);
        Response res = client.accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).post(null);
        if (res.getStatus() != 200) {
            return IOUtils.toString((InputStream) res.getEntity());
        }

        return "";
    }

    /**
     * 获取设备信息json,并将其转化为HpDeviceInfo对象的集合
     */
    public List<ConnectDeviceInfo> getDeviceInfo() throws Exception {

        String url = "http://" + endpointUrl + "/api/devices";
        WebClient client = WebClient.create(url);
        Response res = client.accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get();
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) res
                .getEntity());
        List<ConnectDeviceInfo> returnList = parser
                .readValueAs(new TypeReference<List<ConnectDeviceInfo>>() {
                });
        return returnList;
    }

    /**
     * 删除设备
     *
     * @return String
     * @throws Exception
     */
    public String deleteDeviceById(String id) throws Exception {
        String url = "http://" + endpointUrl + "/api/admin/devices/" + id
                + "/delete";
        WebClient client = WebClient.create(url);
        Response res = client.accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).post(null);
        if (res.getStatus() != 200) {
            return IOUtils.toString((InputStream) res.getEntity());
        }
        return "";
    }

    /**
     * 根据设备端口截图
     *
     * @return String 图片的字节码
     * @throws Exception
     */
    public String screenShotByPortId(Integer portId) throws Exception {
        String url = "http://" + endpointUrl + ":" + portId + "/screenshot";
        WebClient client = WebClient.create(url);
        Response res = client.accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN).get();
        return IOUtils.toString((InputStream) res.getEntity());
    }

    /**
     * 刷新设备
     *
     * @return String 刷新结果（200：成功，500：失败）
     * @throws Exception
     */
    public String devicesRefresh() throws Exception {
        String url = "http://" + endpointUrl + "/api/admin/devices_refresh";
        // WebClient client = WebClient.create(url);
        //
        // Response res = client.accept(MediaType.TEXT_PLAIN)
        // .type(MediaType.TEXT_PLAIN).post(null);
        // if (res.getStatus() == 200) {
        // return IOUtils.toString((InputStream) res.getEntity());
        // }
        // return "";

        HttpClient httpClient = new HttpClient();
        HttpMethod method = new PostMethod(url);

        httpClient.getHttpConnectionManager().getParams().setSoTimeout(7000);

        int executeMethod = httpClient.executeMethod(method);

        return String.valueOf(executeMethod);

    }

    /**
     * 查询Android设备，实体机或者模拟器均通过该接口
     *
     * @param deviceType
     *            1安卓实体机，2安卓模拟器
     * @param appType
     *            1native原生应用 2hybrid
     * @param version
     * @return
     * @throws Exception
     */
    public List getAutoDeviceListForAndroid(Integer deviceType,
                                            Integer appType, Integer version) throws Exception {

        StringBuilder url = new StringBuilder("http://" + endpointUrl);

        url.append("/api/");

        // 拼接url，1安卓实体机，2安卓模拟器
        if (DeviceEvery.AUTO_DEVICE_TYPE_ANDROID_REAL == deviceType) {
            url.append(DeviceEvery.AUTO_DEVICE_TYPE_ANDROID_REAL_TEXT);


        }

        // 拼接get方式参数
        url.append("?").append(DeviceEvery.AUTO_DEVICE_ANDOID_VERSION)
                .append("=").append(version);

        // 1native原生应用 2hybrid 该字段可以不传
        if (DeviceEvery.AUTO_DEVICE_APP_TYPE_NATIVE.equals(appType)) {
            url.append("&").append(DeviceEvery.AUTO_DEVICE_APP_TYPE)
                    .append("=")
                    .append(DeviceEvery.AUTO_DEVICE_APP_TYPE_NATIVE_TEXT);
        }
        if (DeviceEvery.AUTO_DEVICE_APP_TYPE_HYBRID.equals(appType)) {
            url.append("&").append(DeviceEvery.AUTO_DEVICE_APP_TYPE)
                    .append("=")
                    .append(DeviceEvery.AUTO_DEVICE_APP_TYPE_HYBRID_TEXT);
        }

        System.out.println(url.toString());
        WebClient client = WebClient.create(url.toString());
        Response res = client.accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get();
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) res
                .getEntity());
        List<MasterDeviceInfo> returnList = null;
        if (DeviceEvery.AUTO_DEVICE_TYPE_ANDROID_REAL == deviceType) {
            // 安卓实体机结果集
            returnList = parser
                    .readValueAs(new TypeReference<List<MasterDeviceInfo>>() {
                    });
        } else {
            // 安卓虚拟机结果集
            returnList = null;
        }
        return returnList;
    }

    /**
     * 查询ios设备，实体机通过该接口--或者模拟器目前自动化matc系统接口没有提供
     *
     * @param deviceType
     *            1ios实体机
     * @param appType
     *            1native原生应用 2hybrid
     * @param version
     * @return
     * @throws Exception
     */

    /**
     *
     * @param jobParameter
     *            封装参数
     * @return String 返回job任务id

     */
    public String submitJob(JobReal jobParameter) throws HttpException,
            IOException {
        Integer deviceType = jobParameter.getDeviceType();

        Integer appType = jobParameter.getAppType();


        StringBuilder url = new StringBuilder(endpointUrl);
        url.append("/");

        // 拼接url，1安卓实体机，2安卓模拟器, 3ios实体机
        if (DeviceEvery.AUTO_DEVICE_TYPE_ANDROID_REAL.equals(deviceType)) {
            url.append(DeviceEvery.AUTO_DEVICE_TYPE_ANDROID_REAL_TEXT_JOB);
        }

        // 组装script_url
        NameValuePair scriptUrl = new NameValuePair(
                DeviceEvery.JOB_PARAMETER_SCRIPT_URL,
                jobParameter.getScriptUrl());

        // 组装app_url
        NameValuePair appUrl = new NameValuePair(
                DeviceEvery.JOB_PARAMETER_APP_URL, jobParameter.getAppUrl());

        // 组装result_url
        NameValuePair resultUrl = new NameValuePair(
                DeviceEvery.JOB_PARAMETER_RESULT_URL,
                jobParameter.getResultUrl());

        // 组装app_type
        NameValuePair appTypeValuePair = new NameValuePair();
        appTypeValuePair.setName(DeviceEvery.AUTO_DEVICE_APP_TYPE);

        if (DeviceEvery.AUTO_DEVICE_APP_TYPE_NATIVE.equals(appType)) {
            appTypeValuePair
                    .setValue(DeviceEvery.AUTO_DEVICE_APP_TYPE_NATIVE_TEXT);
        } else if (DeviceEvery.AUTO_DEVICE_APP_TYPE_HYBRID.equals(appType)) {
            appTypeValuePair
                    .setValue(DeviceEvery.AUTO_DEVICE_APP_TYPE_HYBRID_TEXT);
        } else if (DeviceEvery.AUTO_DEVICE_APP_TYPE_WEB.equals(appType)) {
            appTypeValuePair
                    .setValue(DeviceEvery.AUTO_DEVICE_APP_TYPE_WEB_TEXT);
        } else if (DeviceEvery.AUTO_DEVICE_APP_TYPE_WECHAT.equals(appType)) {
            appTypeValuePair
                    .setValue(DeviceEvery.AUTO_DEVICE_APP_TYPE_WECHAT_TEXT);
        }
        NameValuePair setting = new NameValuePair();
        // 如果类型为模拟器，需要加入API和screen

        setting = new NameValuePair(DeviceEvery.JOB_PARAMETER_SETTING,
                "{\"NoReinstall\":true}");


        // 组装device_id
        NameValuePair deviceId = new NameValuePair();
        // 如果类型为模拟器，则不需要device_id

        deviceId = new NameValuePair(
                DeviceEvery.JOB_PARAMETER_DEVICE_ID,
                jobParameter.getDeviceId());
        // 组装job_status_url
        NameValuePair jobStatusUrl = new NameValuePair(
                DeviceEvery.JOB_STATUS_URL, jobParameter.getJobStatusUrl());

        HttpClient httpClient = new HttpClient();
        PostMethod method = new PostMethod("http://192.168.235.132:3333/api/matc/jobs/add");

        NameValuePair[] paramPairs = { scriptUrl, appUrl, resultUrl,
                appTypeValuePair, deviceId, setting, jobStatusUrl };

//		method.setRequestBody(paramPairs);
//		httpClient.executeMethod(method);

        method.setRequestBody(paramPairs);
        httpClient.executeMethod(method);
        String jobid = method.getResponseBodyAsString();
        jobid = jobid.substring(9,jobid.lastIndexOf("\"")).trim();
//c此处还有代码哦！
        return jobid;
    }

    /**
     *
     * 查询job状态
     *
     * @param jobId
     *            endpointUrl 格式为182.207.8.53:8080
     * @throws Exception
     */
    public PrinterJob.JobStatus queryJobStatus(String jobId) throws Exception {
        String url = "http://" + endpointUrl + "/api/jobs/" + jobId;
        WebClient client = WebClient.create(url);

        Response res = client.accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get();
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) res
                .getEntity());
        PrinterJob.JobStatus jobStatus = parser
                .readValueAs(new TypeReference<PrinterJob.JobStatus>() {
                });

        return jobStatus;
    }

    /**
     * 取消job
     *
     * @param jobId
     *            endpointUrl 格式为182.207.8.53:8080
     * @return 返回成功与否标志
     * @throws Exception
     */
    public int jobCancel(String jobId) throws Exception {
        String url = "http://" + endpointUrl + "/api/jobs/" + jobId + "/cancel";
        HttpClient httpClient = new HttpClient();
        HttpMethod method = new PostMethod(url);
        int executeMethod = httpClient.executeMethod(method);
        return executeMethod;
    }

    /**
     * 取消result
     * 该种情况只有在matc系统调用我们系统返回job的结果集合失败的情况下，我们主动去调用接口getResult拉去数据之后将数据删除掉
     *
     * @param jobId
     *            endpointUrl 格式为182.207.8.53:8080
     * @return 返回删除成功与否标志
     * @throws Exception
     */
    public int deleteResult(String jobId) throws Exception {
        String url = "http://" + endpointUrl + "/api/jobs/" + jobId;
        HttpClient httpClient = new HttpClient();
        HttpMethod method = new DeleteMethod(url);
        int executeMethod = httpClient.executeMethod(method);
        return executeMethod;
    }


    public static void main(String[] args) throws Exception {
        NameValuePair appNumber = new NameValuePair("app_number", "1");
        NameValuePair dataType = new NameValuePair("data_type", "1");
        NameValuePair jobStatusUrl = new NameValuePair("USER_NAME", "");
        NameValuePair jobNumberUrl = new NameValuePair("JOB_NUMBER", "");
        HttpClient httpClient = new HttpClient();

        NameValuePair[] paramPairs = { appNumber, dataType, jobStatusUrl,
                jobNumberUrl };

        PostMethod method = new PostMethod(
                "http://localhost:8080/demo/services/wsdevice/verificationCode");
        method.addRequestHeader("Content-Type", MediaType.TEXT_PLAIN);
        method.setRequestBody(paramPairs);

        httpClient.executeMethod(method);

        System.out.println(method.getResponseBodyAsString());
    }


}
