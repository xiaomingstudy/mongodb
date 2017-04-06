package com.demo.web.account;

import com.demo.entity.Button;
import com.demo.entity.RoleButtonMsg;
import com.demo.entity.RoleMaint;
import com.demo.service.account.ButtonServer;
import com.demo.service.account.RoleButtonMsgServer;
import com.demo.service.account.RoleService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 27259 on 2017/4/3.
 */
@Controller
@RequestMapping(value = "/roleButton")
public class RoleButtonController {
    @Autowired
    private RoleButtonMsgServer roleButtonMsgServer;
    @Autowired
    private RoleService roleServer;

    @Autowired
    private ButtonServer buttonServer;

    private static final String PAGE_SIZE = "5";
    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("title", "标题");
    }

    @RequestMapping(value = "roleButtonAllList")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType,
                       @RequestParam(value = "roleId",required = false) String roleId,
                       Model model,
                       ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        //String roleId = request.getParameter("roleId");
        Iterable<Button> allButton = buttonServer.getAllButton();
        if(roleId==null){
            roleId="1";
        }
        Iterable<RoleButtonMsg> thisRoleButtons = roleButtonMsgServer.getRoleButtonMsg(Long.valueOf(roleId));
        List<Button> caseButton =new ArrayList<Button>();
        List<Button> deviceButton =new ArrayList<Button>();
        List<Button> userButton =new ArrayList<Button>();
        List<Button> roleButton =new ArrayList<Button>();
        for(Button roleButtonMsg :allButton){
            Long buttonId = roleButtonMsg.getId();
            Integer thisFlag = 0;
            for(RoleButtonMsg buttonMsg:thisRoleButtons){
                if(Long.valueOf(buttonMsg.getButtonId())==buttonId){
                    thisFlag=1;
                }
            }
            if(buttonId<10){
                roleButtonMsg.setRoleFlag(thisFlag);
                caseButton.add(roleButtonMsg);
            }else if(buttonId>10&&buttonId<20){
                roleButtonMsg.setRoleFlag(thisFlag);
                deviceButton.add(roleButtonMsg);
            }else if(buttonId>20&&buttonId<30){
                roleButtonMsg.setRoleFlag(thisFlag);
                userButton.add(roleButtonMsg);
            }else{
                roleButtonMsg.setRoleFlag(thisFlag);
                roleButton.add(roleButtonMsg);
            }
        }
        Iterable<RoleMaint> allRole = roleServer.getAllRole();
        List<RoleMaint> allroles = new ArrayList<RoleMaint>();
        RoleMaint thisRole = new RoleMaint();
        for(RoleMaint roleMaint :allRole){
            if(roleMaint.getId()==Long.valueOf(roleId)){
                thisRole=roleMaint;
            }
            allroles.add(roleMaint);
        }
        model.addAttribute("thisRole", thisRole);
        model.addAttribute("allRole", allroles);
        model.addAttribute("caseButton", caseButton);
        model.addAttribute("deviceButton", deviceButton);
        model.addAttribute("userButton", userButton);
        model.addAttribute("roleButton", roleButton);
        model.addAttribute("roleId", Long.valueOf(roleId));
//        model.addAttribute("sortTypes", sortTypes);
//        // 将搜索条件编码成字符串，用于排序，分页的URL
//        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

        return "account/allButton";
    }


    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public String updateForm(@RequestParam(value = "buttonIds",required = false) String buttonIds,
                             @RequestParam(value = "roleId",required = false) String roleId,
                             RedirectAttributes redirectAttributes, Model model) {
        System.out.println(buttonIds);
        System.out.println(roleId);
        Iterable<RoleButtonMsg> thisRoleButtons = roleButtonMsgServer.getRoleButtonMsg(Long.valueOf(roleId));
        String[] buttonId = buttonIds.split(",");
        List<Integer> thisRoleButtonIds = new ArrayList<Integer>();

        for(RoleButtonMsg roleButtonMsg:thisRoleButtons){
            roleButtonMsg.setFlag(1);
            roleButtonMsgServer.saveroleButtonMsg(roleButtonMsg);
        }

        for(int i=0;i<buttonId.length;i++){
            RoleButtonMsg roleButtonMsg=roleButtonMsgServer.getRoleButtonMsgById(Long.valueOf(buttonId[i]),Long.valueOf(roleId));
            if(roleButtonMsg!=null){
                roleButtonMsg.setFlag(0);
                roleButtonMsgServer.saveroleButtonMsg(roleButtonMsg);
            }else{
                RoleButtonMsg addRoleButtonMsg = new RoleButtonMsg();
                addRoleButtonMsg.setFlag(0);
                addRoleButtonMsg.setButtonId(Integer.parseInt(buttonId[i]));
                Button button = buttonServer.getButtonById(Long.valueOf(buttonId[i]));
                addRoleButtonMsg.setButtonName(button.getButtonName());
                addRoleButtonMsg.setRoleId(Integer.parseInt(roleId));
                roleButtonMsgServer.saveroleButtonMsg(addRoleButtonMsg);
            }
        }

        redirectAttributes.addFlashAttribute("message", "编辑成功");
        model.addAttribute("status",1);
        return "";
    }

}
