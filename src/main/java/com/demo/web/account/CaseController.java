package com.demo.web.account;

import com.demo.entity.*;
import com.demo.service.account.CaseScriptService;
import com.demo.service.account.CaseService;
import com.demo.service.account.CaseStepService;
import com.demo.service.account.ShiroDbRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletRequest;

/**
 * Created by 27259 on 2017/2/27.
 */
@Controller
@RequestMapping(value = "/case")
public class CaseController {

    @Autowired
    private CaseService caseService;
    @Autowired
    private CaseStepService caseStepService;
    @Autowired
    private CaseScriptService caseScriptService;


    private static final String PAGE_SIZE = "7";




    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("title", "标题");
    }

    @RequestMapping(value = "caseList")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
                       ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Long userId = getCurrentUserId();

        Page<Case> cases = caseService.getUserCase(userId, searchParams, pageNumber, pageSize, sortType);
        Session session = SecurityUtils.getSubject().getSession();
        List<RoleButtonMsg> roleButtonMsgs = (List<RoleButtonMsg>)session.getAttribute("roleButtonMsg");
        Integer canUsing = 0;
        for(RoleButtonMsg roleButtonMsg:roleButtonMsgs){
            if(roleButtonMsg.getButtonId()==3){
                canUsing=1;
            }
        }
        model.addAttribute("cases", cases);
        model.addAttribute("canUsing", canUsing);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

        return "account/caseManage";
    }

    /**
     * 取出Shiro中的当前用户Id.
     */
    private Long getCurrentUserId() {
        ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.id;
    }

    //删除案例
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Case casez= caseService.getCase(id);
        caseService.deleteCase(id);
        redirectAttributes.addFlashAttribute("message", "删除案例" + casez.getCaseName() + "成功");
        return "redirect:/case/caseList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("caseOne", new Case());
        model.addAttribute("action", "addCase");
        return "account/newCaseForm";
    }

    @RequestMapping(value = "addCase", method = RequestMethod.POST)
    public String addCase(@RequestParam(required = false) Long id,
                          @RequestParam(required = false) String caseNumber,
                          @RequestParam(required = false) String caseName,
                          @RequestParam(required = false) String personName,
                          @RequestParam(required = false) MultipartFile scriptPath,
                          @RequestParam(required = false) String steplistjson,
                          RedirectAttributes redirectAttributes,
                          HttpServletRequest request) throws IOException, ParseException {
        User user = new User(getCurrentUserId());
        Case newCase = new Case();
        newCase.setCaseNumber(Integer.parseInt(caseNumber));
        newCase.setCaseName(caseName);
        newCase.setPersonName(personName);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        Date today = sdf.parse(dateNowStr);
        newCase.setCreateDate(today);
        newCase.setUser(user);
        newCase.setAppId(1);
        if(scriptPath!=null){
            if(scriptPath.getSize()>0){
                String scriptId = fileAddedPage(scriptPath);
                newCase.setScriptId(Integer.parseInt(scriptId));
            }
        }
        caseService.saveCase(newCase);
        if(steplistjson!=null){
            String[] arrays  =  steplistjson.split("&");
            for(int i=0;i<arrays.length;i++){
                String[] step = arrays[i].split(",");
                CaseStep caseStep = new CaseStep();
                caseStep.setStepNum(Integer.parseInt(step[0].split(":")[1]));
                caseStep.setStepDesc(step[1].split(":")[1]);
                caseStep.setExpectedResult(step[2].split(":")[1]);
                caseStep.setCaseId(newCase.getId());
                caseStepService.saveCaseStep(caseStep);
            }

        }
        redirectAttributes.addFlashAttribute("message", "新增案例成功");
        return "redirect:/case/caseList";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Case thisCase = caseService.getCase(id);
        model.addAttribute("caseOne",thisCase);
        Integer scriptId = thisCase.getScriptId();
        if(scriptId!=null){
            CaseScript caseScript = caseScriptService.findById(Long.valueOf(scriptId));
            if(caseScript!=null){
                if(caseScript.getScriptPath()!=null||caseScript.getScriptPath()!=""){
                    model.addAttribute("caseScriptName",caseScript.getScriptPath());
                }else{
                    model.addAttribute("caseScriptName","暂未上传脚本");
                }
            }else{
                model.addAttribute("caseScriptName","暂未上传脚本");
            }
        }else{
            model.addAttribute("caseScriptName","暂未上传脚本");
        }
        model.addAttribute("caseStepOne", caseStepService.getCaseStep(id));
        model.addAttribute("action", "editCase");
        return "account/editCaseForm";
    }

    @RequestMapping(value = "editCase", method = RequestMethod.POST)
    public String update(@RequestParam(required = false) Long id,
                         @RequestParam(required = false) String caseNumber,
                         @RequestParam(required = false) String caseName,
                         @RequestParam(required = false) String personName,
                         @RequestParam(required = false) MultipartFile scriptPath,
                         @RequestParam(required = false) String steplistjson,
                         RedirectAttributes redirectAttributes,
                         HttpServletRequest request) throws IOException {
        User user = new User(getCurrentUserId());
        Case editCaseOne = caseService.getCase(id);
        editCaseOne.setUser(user);
        if(scriptPath!=null){
            if(scriptPath.getSize()>0){
                String scriptId = fileAddedPage(scriptPath);
                editCaseOne.setScriptId(Integer.parseInt(scriptId));
            }
        }
        caseService.saveCase(editCaseOne);

       if(steplistjson!=null||steplistjson==""){
           List<CaseStep> list = caseStepService.getCaseStep(id);
           for(CaseStep caseStep :list){
               caseStepService.deleteCaseStep(caseStep);
           }
           String[] arrays  =  steplistjson.split("&");
           for(int i=0;i<arrays.length;i++){
               String[] step = arrays[i].split(",");
               CaseStep caseStep = new CaseStep();
               caseStep.setStepNum(Integer.parseInt(step[0].split(":")[1]));
               caseStep.setStepDesc(step[1].split(":")[1]);
               caseStep.setExpectedResult(step[2].split(":")[1]);
               caseStep.setCaseId(id);
               caseStepService.saveCaseStep(caseStep);
               }

           }
        redirectAttributes.addFlashAttribute("message", "编辑案例成功");
        return "redirect:/case/caseList";
    }



    @RequestMapping(value = "seeCase/{id}", method = RequestMethod.GET)
    public String seePageForm(@PathVariable("id")Long id,Model model) {
        Case caseB=caseService.getCase(id);
        Integer scriptId = caseB.getScriptId();
        if(scriptId!=null){
            CaseScript caseScript = caseScriptService.findById(Long.valueOf(scriptId));
            if(caseScript!=null){
                if(caseScript.getScriptPath()!=null||caseScript.getScriptPath()!=""){
                    model.addAttribute("caseScriptName",caseScript.getScriptPath());
                }else{
                    model.addAttribute("caseScriptName","暂未上传脚本");
                }
            }else{
                model.addAttribute("caseScriptName","暂未上传脚本");
            }
        }else{
            model.addAttribute("caseScriptName","暂未上传脚本");
        }
        List<CaseStep> caseStep = caseStepService.getCaseStep(id);

        model.addAttribute("caseOne", caseB);
        model.addAttribute("caseStepOne", caseStep);
        return "account/casePage";
    }

    /**文件新增，通过页面
     * @param multipartFile
     * @return
     */
    public String fileAddedPage(MultipartFile multipartFile) {
        String fileUploadPath = "F:\\attachment";
        String pathExt = "script\\";
        Long scriptId;
        long size = multipartFile.getSize();
        CaseScript bean=new CaseScript();
        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd_HH_mm_ss_SSS");// 新文件名称格式
        String oldFileName = multipartFile.getOriginalFilename();// 原文件名称
        String fileType = multipartFile.getContentType();
        int end = oldFileName.lastIndexOf(".");
        fileType = oldFileName.substring(end+1).trim();// 文件类型
        // if(fileType.equalsIgnoreCase("exe"))
        // new Exception();
        String newFileName = oldFileName;// 新文件名称
        System.out.println("原文件名称：" + oldFileName + "************;新文件名称："+ newFileName + ";**********文件类型："+ fileType);
        // 文件上传，添加记录
        String filePath=fileUploadPath+pathExt+newFileName;
        bean.setScriptPath(filePath);
        try {
            File fileDir = new File(fileUploadPath+pathExt);
            if(!fileDir.exists())
                fileDir.mkdirs();
            File file = new File(filePath);
            multipartFile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            scriptId = caseScriptService.saveCaseScript(bean);
            return  scriptId.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
