package com.demo.web.device;

import com.demo.entity.*;
import com.demo.service.account.AccountService;
import com.demo.service.account.CaseService;
import com.demo.service.account.CaseStepResultService;
import com.demo.service.account.ShiroDbRealm;
import com.demo.service.device.DeviceService;
import com.demo.service.device.JobTaskService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 27259 on 2017/3/22.
 */
@Controller
@RequestMapping(value = "jobTask")
public class TaskJobController {

    @Autowired
    JobTaskService jobTaskService;
    @Autowired
    private CaseStepResultService caseStepResultService;
    @Autowired
    private CaseService caseService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DeviceService deviceService;

    private String testServerIp = "localhost";

    private Integer testPort=8080;

    private  String masterIP = "192.168.235.132";
    private  Integer masterPort = 3333;

    @RequestMapping(value = "/job")
    public  @ResponseBody
    ModelMap jobList(@RequestParam(value = "caseId", required = false) String caseIds,
                     @RequestParam(value = "deviceId", required = false) String deviceIds

    )
    {
        ModelMap model = new ModelMap();
        try{
            if (StringUtils.isNotBlank(deviceIds) && StringUtils.isNotBlank(caseIds)){
                JobTask jobTask = new JobTask();
                caseIds = caseIds.substring(0,caseIds.lastIndexOf(","));
                deviceIds = deviceIds.substring(0,deviceIds.lastIndexOf(","));

                String[] caseArray = caseIds.split(",");
                String[] deviceArray = null;
                String deviceApi = null;
                //String[] temp = deviceIds.split("&");
                //deviceApi = temp[0];
                deviceArray = deviceIds.split(",");
                Long userId = getCurrentUserId();
                User thisUser = accountService.getUser(userId);
                jobTask.setUser(thisUser);
                Date date = new Date();
                jobTask.setInsertTime(date);
                for(int i=0;i<caseArray.length;i++){
                    Integer caseId = Integer.valueOf(caseArray[i]);
                    Case requestCase = caseService.getCase(Long.valueOf(caseId));
                    System.out.println("111111111111111111111");
                    for (int j=0;j<deviceArray.length;j++){
                        try{
                            Integer   deviceId = Integer.valueOf(deviceArray[j]);

                            DeviceInfo  deviceInfo = deviceService.getDevice(Long.valueOf(deviceId));

                            jobTask.setDeviceId(Long.valueOf(deviceId));

                            //案例及案例步骤结果表

                            String caseResultId=caseStepResultService.caseResultForAuto(caseId, deviceId,userId);
                            System.out.println(caseResultId);
                            jobTask.setId(Long.valueOf(caseResultId));
                            try{
                                Integer appId = requestCase.getAppId();
                                if(appId==null){
                                    model.addAttribute("status",0);
                                    model.addAttribute("message","没有应用");
                                    return model;
                                }

                                JobReal jobParameter = new JobReal();

                                jobParameter.setAppUrl(DeviceU.getDemoServerAppUrl(testServerIp, testPort,
                                        Long.valueOf(requestCase.getAppId())));

                                jobParameter.setResultUrl(DeviceU.getDemoServerResultUrl(testServerIp, testPort,
                                        Long.valueOf(caseId), Long.valueOf(caseResultId)));
                                jobParameter.setDeviceType(1);
                                jobParameter.setDeviceId(deviceInfo.getExDeviceId());

                                jobParameter.setScriptUrl(DeviceU.getDemoServerScriptUrl(testServerIp,
                                        testPort,Long.valueOf(requestCase.getScriptId())));


                                jobParameter
                                        .setJobStatusUrl(DeviceU.getDemoJobStatusUrl(testServerIp, testPort));

                                WebSClient wsClient = new WebSClient(
                                        DeviceU.getMatcServer(masterIP, masterPort));
                                //job创建完成后由master返回的projectID
                                String jobId = wsClient.submitJob(jobParameter);
                                //save master projectId for jobTask
                                jobTask.setJobId(jobId);
                                jobTaskService.saveTaskJob(jobTask);
                                   /* Case caseA=null;
                                    String appPath= caseA.getAppPath();*/

                            }catch (Exception e){
                                System.out.println(e);
                            }


                        }catch (Exception e){

                        }

                    }

                }

                model.addAttribute("status",1);
                model.addAttribute("message","提交任务成功");
            }

            return model;
        }catch (Exception e){

            System.out.println(e);
            model.addAttribute("status",0);
            model.addAttribute("message","提交任务失败");
            return model;
        }

    }

    /**
     * 取出Shiro中的当前用户Id.
     */
    private Long getCurrentUserId() {
        ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.id;
    }
}
