package com.demo.web.utils;

/**
 * Created by 27259 on 2017/4/6.
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.ws.WebServiceContext;

import com.demo.entity.*;
import com.demo.service.account.*;
import com.demo.service.device.JobTaskService;
import com.demo.service.task.TblTestAttachmentService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.demo.service.task.TaskService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


@Path("/wsdevice")
public class DeviceEmulator {
    @Autowired
    private AppServer appService;

    @Autowired
    private TblTestAttachmentService tblTestAttachmentService;

    @Autowired
    private CaseStepResultService caseStepResultService;

    @Autowired
    private TaskService batchTaskService;

    @Autowired
    private CaseScriptService scriptInfoService;

    @Autowired
    private CaseResultService caseResultService;

    @Autowired
    private CaseService caseService;

    @Autowired
    private JobTaskService jobTaskService;

    @Resource
    private WebServiceContext webServiceContext;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public javax.ws.rs.core.Response aaa()
            throws NumberFormatException, Exception {
        System.out.println("终于进来了");
        return null;
    }



    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/app/{projectAppId}")
    public javax.ws.rs.core.Response app(
            @PathParam("projectAppId") String projectAppIdStr)
            throws NumberFormatException, Exception {
        if (StringUtils.isBlank(projectAppIdStr)) {
            return null;
        }
//**********************************
      /*  File file = new File("C:\\Users\\lixj.test\\Desktop\\android-app-bootstrap.apk");
        if (projectAppIdStr.endsWith(DeviceEvery.MD5_CHECK_SUM)) {
            String md5Hex = DigestUtils.md5Hex(new FileInputStream(
                    file));

            ResponseBuilder response = Response.ok().entity(md5Hex);
            response.header(
                    "Content-Disposition",
                    "attachment;filename='"
                            + file.getName()
                            + DeviceEvery.MD5_CHECK_SUM + "'");
            return response.build();
        } else {
            ResponseBuilder response = Response.ok(file);

            response.header(
                    "Content-Disposition",
                    "attachment;filename='"
                            + file.getName() + "'");
            return response.build();
        }*/

        //************************************************
        String projectAppId = null;
        if (projectAppIdStr.endsWith(DeviceEvery.MD5_CHECK_SUM)) {
            projectAppId = projectAppIdStr.replace(
                    DeviceEvery.MD5_CHECK_SUM, "");
        } else {
            projectAppId = projectAppIdStr;
        }

        AppInfo projectApp = appService.findOneById(Long.valueOf(projectAppId));

        if (projectApp != null) {

            if (projectApp.getAppPath() != null) {
                File file = new File(projectApp.getAppPath());

                if (projectAppIdStr.endsWith(DeviceEvery.MD5_CHECK_SUM)) {
                    String md5Hex = DigestUtils.md5Hex(new FileInputStream(
                            file));

                    ResponseBuilder response = Response.ok().entity(md5Hex);
                    response.header(
                            "Content-Disposition",
                            "attachment;filename='"
                                    + projectApp.getAppName()
                                    + DeviceEvery.MD5_CHECK_SUM + "'");
                    return response.build();
                } else {
                    ResponseBuilder response = Response.ok(file);

                    response.header(
                            "Content-Disposition",
                            "attachment;filename='"
                                    + projectApp.getAppName() + "'");
                    return response.build();
                }

            }
        }
        return null;
    }

    /**
     * 脚本参数化下获取脚本文件接口
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/scriptParam/{scriptId}")
    public javax.ws.rs.core.Response scriptParam(
            @PathParam("scriptId") String scriptIdStr) throws Exception {
        if (StringUtils.isBlank(scriptIdStr)) {
            return null;
        }

        try {
            String scriptId = null;
            if (scriptIdStr.endsWith(DeviceEvery.MD5_CHECK_SUM)) {
                scriptId = scriptIdStr.replace(DeviceEvery.MD5_CHECK_SUM,
                        "");
            } else {
                scriptId = scriptIdStr;
            }
            CaseScript caseScript = scriptInfoService.findById(Long.valueOf(scriptId));
            String thisFilePath = caseScript.getScriptPath();
            File parent = new File(thisFilePath);
            File[] files = parent.listFiles();
            if (files.length > 0) {
                File resultFile = files[0];
                if (scriptIdStr.endsWith(DeviceEvery.MD5_CHECK_SUM)) {
                    String md5Hex = DigestUtils
                            .md5Hex(new FileInputStream(resultFile));

                    ResponseBuilder response = Response.ok().entity(
                            md5Hex);
                    response.header(
                            "Content-Disposition",
                            "attachment;filename='"
                                    + resultFile.getName()
                                    + DeviceEvery.MD5_CHECK_SUM
                                    + "'");
                    return response.build();
                } else {

                    ResponseBuilder response = Response.ok(resultFile);

                    response.header(
                            "Content-Disposition",
                            "attachment;filename='"
                                    + resultFile.getName() + "'");
                    return response.build();
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
//        String scriptId = null;
//        if (scriptIdStr.endsWith(DeviceConstants.MD5_CHECK_SUM)) {
//            scriptId = scriptIdStr.replace(DeviceConstants.MD5_CHECK_SUM,
//                    "");
//        } else {
//            scriptId = scriptIdStr;
//        }
////		TblScriptInfo scriptInfo = scriptInfoService.getScriptById(Long
////				.valueOf(scriptId));
//        File file = new File("C:\\Users\\lixj.test\\Desktop\\macaca-mobile-sample.test.js");
//        if (scriptIdStr.endsWith(DeviceConstants.MD5_CHECK_SUM)) {
//            String md5Hex = DigestUtils.md5Hex(new FileInputStream(
//                    file));
//
//            ResponseBuilder response = Response.ok().entity(md5Hex);
//            response.header(
//                    "Content-Disposition",
//                    "attachment;filename='"
//                            + file.getName()
//                            + DeviceConstants.MD5_CHECK_SUM + "'");
//            return response.build();
//        } else {
//            ResponseBuilder response = Response.ok(file);
//
//            response.header(
//                    "Content-Disposition",
//                    "attachment;filename='"
//                            + file.getName() + "'");
//            return response.build();
//        }

    }

    //原获取脚本文件接口
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/script/{scriptId}")
    public javax.ws.rs.core.Response script(
            @PathParam("scriptId") String scriptIdStr) throws Exception {
        if (StringUtils.isBlank(scriptIdStr)) {
            return null;
        }

        try {

               /* String scriptId = null;
                if (scriptIdStr.endsWith(DeviceEvery.MD5_CHECK_SUM)) {
                    scriptId = scriptIdStr.replace(DeviceEvery.MD5_CHECK_SUM,
                            "");
                } else {
                    scriptId = scriptIdStr;
                }
//			TblScriptInfo scriptInfo = scriptInfoService.getScriptById(Long
//					.valueOf(scriptId));
                File file = new File("C:\\Users\\lixj.test\\Desktop\\macaca-mobile-sample.test.js");
                if (scriptIdStr.endsWith(DeviceEvery.MD5_CHECK_SUM)) {
                String md5Hex = DigestUtils.md5Hex(new FileInputStream(
                        file));

                ResponseBuilder response = Response.ok().entity(md5Hex);
                response.header(
                        "Content-Disposition",
                        "attachment;filename='"
                                + file.getName()
                                + DeviceEvery.MD5_CHECK_SUM + "'");
                return response.build();
            } else {
                ResponseBuilder response = Response.ok(file);

                response.header(
                        "Content-Disposition",
                        "attachment;filename='"
                                + file.getName() + "'");
                return response.build();
            }
*/
            String scriptId = "";
            if (scriptIdStr.endsWith(DeviceEvery.MD5_CHECK_SUM)) {
                scriptId = scriptIdStr.replace(DeviceEvery.MD5_CHECK_SUM,
                        "");
            } else {
                scriptId = scriptIdStr;
            }
            CaseScript scriptInfo = scriptInfoService.findById(Long
                    .valueOf(scriptId));
            String filePath = scriptInfo.getScriptPath();
            String fileName = filePath.substring(filePath.lastIndexOf("\\"));
            if (scriptInfo != null) {
                if (scriptIdStr.endsWith(DeviceEvery.MD5_CHECK_SUM)) {
                    String md5Hex = DigestUtils
                            .md5Hex(new FileInputStream(new File(scriptInfo.getScriptPath())));

                    ResponseBuilder response = Response.ok().entity(
                            md5Hex);
                    response.header(
                            "Content-Disposition",
                            "attachment;filename='"
                                    + fileName
                                    + DeviceEvery.MD5_CHECK_SUM
                                    + "'");
                    return response.build();
                } else {

                    ResponseBuilder response = Response.ok(new File(filePath));

                    response.header(
                            "Content-Disposition",
                            "attachment;filename='"
                                    + fileName + "'");
                    return response.build();
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * http://localhost:8080/demo/page/jsp/device/test.jsp页面进行测试
     * matc系统根据我们返回的错误码是否是200来决定是否上传结果成功
     *
     * @param body
     * @param caseId
     * @param caseResultId
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/result/{caseId}/{caseResultId}")
    public String result(MultipartBody body,
                         @PathParam("caseId") String caseId,
                         @PathParam("caseResultId") String caseResultId) throws Exception {

        List<Attachment> allAttachments = body.getAllAttachments();

        String message = "";

        Attachment attachment = null;
        int fileNumber = 0;
        for (Attachment temp : allAttachments) {
            fileNumber++;
            attachment = temp;

        }

        if (fileNumber == 0) {
            message = "結果附件不能为空";
        }

        if (fileNumber != 1) {
            message = "結果附件只能为一个";
        }

        if (StringUtils.isNotBlank(message)) {
            throw new RuntimeException(message);
        } else {
            message = this.saveAutoTestResult(attachment, caseId,
                    caseResultId);
        }

        if (StringUtils.isNotBlank(message)) {
            throw new RuntimeException(message);
        }

        return message;
    }

    /**
     * 提供给matc系统的用于job开始执行时候通知我们
     *
     * @param jobId
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/status/{projectId}")
    public String jobStatus(@PathParam("projectId") String jobId,
                            @FormParam("status") String jobStatus,
                            @FormParam("error_message") String errorMessage) throws Exception {

        return "";
    }

    /**
     * 保存matc系统返回给的自动化测试结果
     */
    public String saveAutoTestResult(Attachment attachment, String caseId,
                                     String caseResultId) throws Exception {

        String msg;

        DataHandler dataHandler = attachment.getDataHandler();
        InputStream inputStream = dataHandler.getInputStream();

        // 获取附件的存放的路径

        String url = "F:/attachment/"+caseId+"/"+caseResultId;

        String fileName = dataHandler.getName().substring(
                dataHandler.getName().lastIndexOf("/") + 1);
        msg = getAutoTestResult(inputStream, url, fileName, Integer
                .parseInt(caseId), Integer.parseInt(caseResultId));

        IOUtils.closeQuietly(inputStream);
        return msg;
    }

    public TblTestAttachment createNewAttachment(String fileName, String path,
                                                 String fileType) {
        TblTestAttachment tblTestAttachment = new TblTestAttachment();
        tblTestAttachment.setCreateTime(new Date());
        tblTestAttachment.setFileNameNew(fileName);
        tblTestAttachment.setFileNameOld(fileName);
        tblTestAttachment.setFilePath(path);
        tblTestAttachment.setFileType(fileType);
        tblTestAttachmentService.save(tblTestAttachment);
        return tblTestAttachment;
    }


    public String getAutoTestResult(InputStream inputStream, String url,
                                    String zipFileName, Integer caseId, Integer caseResultId)
            throws Exception {

        String path = url + zipFileName;
        // 将压缩包下载到caseresultid/压缩包.zip --自动化测试
        FileUtils.copyInputStreamToFile(inputStream, new File(path));

        // 数据库保存附件记录
        TblTestAttachment tblTestAttachment = this.createNewAttachment(
                zipFileName, path, DeviceEvery.FILE_TYPE_ZIP);
        Long id = tblTestAttachment.getId();

        CaseResult caseResult =caseResultService.findById(Long.valueOf(caseResultId));
        caseResult.setTestAttachmentId(id);

        caseResult.setEndTime(new Date());

        String msg;

        ZipFile file = new ZipFile(path);

        // 获取result.json文件，并解析
        ZipEntry jsonFile = file.getEntry(DeviceEvery.AUTO_RESULT_JSON);

        if (jsonFile != null) {

            // 解析json文件
            inputStream = file.getInputStream(jsonFile);
            MappingJsonFactory factory = new MappingJsonFactory();
            JsonParser parser = factory.createJsonParser(inputStream);
            JobResult jobResult = parser
                    .readValueAs(new TypeReference<JobResult>() {
                    });

            System.out.println(jobResult);
            // 获取到的json结果不为空，或者映射成功
            if (jobResult != null) {
                String caseResultStatus = jobResult.getStatus();
                // 将最后执行的result结果信息同步到case与step上
                Integer status = 0;
                if(DeviceEvery.AUTO_TEST_RESULT_STATUS_SUCCESS.equals(caseResultStatus)){
                    status=3;
                }else if(DeviceEvery.AUTO_TEST_RESULT_STATUS_FAILED.equals(caseResultStatus)){
                    status=4;
                }
                caseResult.setTestResult(status);
                caseResultService.saveCaseResult(caseResult);

                List<CaseStepResult> stepResultList = (List<CaseStepResult>) caseStepResultService.getListByCaseResultId(caseResultId);

                if (CollectionUtils.isEmpty(stepResultList)) {
                    msg = "根据caseResultId:" + caseResultId
                            + "没有查询到相关的自动化case_result_step_result结果记录";
                    file.close();
                    IOUtils.closeQuietly(inputStream);
                    return msg;
                }

                List<StepResult> steps = jobResult.getSteps();

                if (CollectionUtils.isEmpty(steps)) {
                    msg = "传递过来的step_result_list为空";
                    file.close();
                    IOUtils.closeQuietly(inputStream);
                    return "";
                }

                // 保存步骤结果列表
                for (int i = 0; i < steps.size(); i++) {
                    StepResult step = steps.get(i);
                    String pictureName = step.getScreenShot();
                    if(StringUtils.isNotEmpty(pictureName)&&!"null".equals(pictureName)){
                        pictureName = pictureName.substring(0,pictureName.lastIndexOf(",")).trim();
                        ZipEntry pictureNameFile = file.getEntry("sample/screenshot/"+pictureName);
                        inputStream = file.getInputStream(pictureNameFile);

                    }
                    // 步骤不返回状态，返回的最后一个step即为最有成功的step
                    // String status = step.getStatus();
                    String errorMessage = step.getErrorMessage();
                    if(StringUtils.isEmpty(errorMessage)){
                        errorMessage="";
                    }

//					ZipEntry pictureFile = file.getEntry(pictureName);
//					inputStream = file.getInputStream(pictureFile);
                    if (i < stepResultList.size()) {
                        CaseStepResult stepResult = stepResultList.get(i);

                        String stepPicturePath = url + stepResult.getId();
                        FileUtils.copyInputStreamToFile(inputStream, new File(
                                stepPicturePath + "/" + pictureName));

                        TblTestAttachment testCaseResultAttachment = this
                                .createNewAttachment(pictureName,
                                        stepPicturePath + "/" + pictureName,
                                        DeviceEvery.FILE_TYPE_PNG);

                        File pictureFilePath = new File(stepPicturePath+ "/" + pictureName);

                        BufferedImage image = ImageIO.read(pictureFilePath);

                        String str = testCaseResultAttachment.getId().toString();
                        stepResult.setTestAttachmentId(Integer.parseInt(str));
                        stepResult
                                .setTestResult(DeviceEvery.TEST_RESULT_SUCCESS);
                        stepResult.setErrorMessage(errorMessage);
                        caseStepResultService.saveCaseStepResult(stepResult);
                    }

                }
            }

        }
        file.close();
        IOUtils.closeQuietly(inputStream);
        return "";
    }

}
