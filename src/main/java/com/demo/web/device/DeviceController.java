package com.demo.web.device;

import com.demo.entity.*;
import com.demo.service.account.AccountService;
import com.demo.service.account.ShiroDbRealm;
import com.demo.service.device.DeviceService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.FastHashMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 27259 on 2017/3/7.
 */
@Controller
@RequestMapping(value="/deviceOne")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private AccountService accountService;

    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("title", "标题");
    }


    private static final String PAGE_SIZE="5";

    @RequestMapping(value="deviceList")
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "caseids",required = false)String caseids,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
                       ServletRequest request){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        WebSClient ws = new WebSClient("http://192.168.235.132" + ":" + "3333");
        List<MacacaDeviceInfo> macacaList = null;
        Long userId = getCurrentUserId();
        User currentUser =accountService.getUser(userId);
        try {
            macacaList = ws.getDeviceInfos();
            if(macacaList!=null){
                for (MacacaDeviceInfo macacaDeviceInfo : macacaList) {
                    DeviceInfo deviceInfo = deviceService.getDeviceInfoBySerial_number(macacaDeviceInfo.getSerialNumber());
                    if(deviceInfo==null){
                        deviceInfo = new DeviceInfo();
                        deviceInfo.setExDeviceId(macacaDeviceInfo.getId());
                        deviceInfo.setSerialNumber(macacaDeviceInfo.getSerialNumber());
                        deviceInfo.setDevicePlatform(macacaDeviceInfo.getPlantform());
                        deviceInfo.setVersion(macacaDeviceInfo.getReleaseVersion());
                        deviceInfo.setFacturer(macacaDeviceInfo.getBrand());
                        deviceInfo.setSlaveIp(macacaDeviceInfo.getSlaveIP());
                        Integer status = macacaDeviceInfo.getStatus();
                        //刷新设备时，将设备状态保存为对应的状态
                        //reliable状态 （:1、可用，2、维护中，3、使用中，4、不可用）
                        //本地状态：（1：可用、2：使用中、3:未连接、4：维护中）
//                        if(deviceInfo.getDeviceStatus()!=3){
                            deviceInfo.setDeviceStatus(1);
//                        }

                        String str = macacaDeviceInfo.getCreatedAt();
                        deviceInfo.setInsertTime(new Date());
                        deviceInfo.setScreenWidth(Integer.valueOf(macacaDeviceInfo.getScreenWidth()));
                        deviceInfo.setScreenHeight(Integer.valueOf(macacaDeviceInfo.getScreenHeight()));
                        deviceInfo.setDeviceType("android");
                        deviceInfo.setUser(currentUser);
                        deviceInfo.setUserName(currentUser.getName());
                    }else{
                        deviceInfo.setExDeviceId(macacaDeviceInfo.getId());
                        deviceInfo.setSerialNumber(macacaDeviceInfo.getSerialNumber());
                        deviceInfo.setDevicePlatform(macacaDeviceInfo.getPlantform());
                        deviceInfo.setVersion(macacaDeviceInfo.getReleaseVersion());
                        deviceInfo.setFacturer(macacaDeviceInfo.getBrand());
                        deviceInfo.setSlaveIp(macacaDeviceInfo.getSlaveIP());
                        Integer status = macacaDeviceInfo.getStatus();
                        //刷新设备时，将设备状态保存为对应的状态
                        //reliable状态 （:1、可用，2、维护中，3、使用中，4、不可用）
                        //本地状态：（1：可用、2：使用中、3:未连接、4：维护中）
                        if(deviceInfo.getDeviceStatus()!=3){
                            deviceInfo.setDeviceStatus(1);
                        }

                        String str = macacaDeviceInfo.getCreatedAt();
                        deviceInfo.setInsertTime(new Date());
                        deviceInfo.setScreenWidth(Integer.valueOf(macacaDeviceInfo.getScreenWidth()));
                        deviceInfo.setScreenHeight(Integer.valueOf(macacaDeviceInfo.getScreenHeight()));
                        deviceInfo.setDeviceType("android");
                        deviceInfo.setUser(currentUser);
                        deviceInfo.setUserName(currentUser.getName());
                    }
                    deviceService.saveDevice(deviceInfo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Session session = SecurityUtils.getSubject().getSession();
        List<RoleButtonMsg> roleButtonMsgs = (List<RoleButtonMsg>)session.getAttribute("roleButtonMsg");
        Integer addDevicesButton = 0;
        Integer refreshDeviceButton = 0;
        for(RoleButtonMsg roleButtonMsg:roleButtonMsgs){
            if(roleButtonMsg.getButtonName()=="新增设备"){
                addDevicesButton=1;
            }else if(roleButtonMsg.getButtonId()==16){
                refreshDeviceButton=1;
            }
        }
        model.addAttribute("addDevicesButton",addDevicesButton);
        model.addAttribute("refreshDeviceButton",refreshDeviceButton);
        if(caseids==null){
            model.addAttribute("submitDeviceButton",1);
        }else{
            model.addAttribute("submitDeviceButton",0);
        }

        Page<DeviceInfo> devices=deviceService.getUserDeviceInfo(userId,searchParams,pageNumber, pageSize, sortType);
        model.addAttribute("caseIds",caseids);
        model.addAttribute("devices", devices);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);
        // 将搜索条件编码成字符串，用于排序，分页的URL
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));


        return "device/deviceList";
    }

    /**
     * 取出Shiro中的当前用户Id.
     */
    private Long getCurrentUserId() {
        ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.id;
    }

    //新增设备
    @RequestMapping(value="create")
    public String createDevice(Model model){
        DeviceInfo deviceA=new DeviceInfo();
        model.addAttribute(deviceA);
        model.addAttribute("action","newDevice");
        return "device/addDevice";
    }
    //提交设备表单
    @RequestMapping(value = "newDevice")
    public String addDevice(@Valid DeviceInfo deviceI, RedirectAttributes redirectAttributes){
        User user=new User(getCurrentUserId());
        deviceI.setUser(user);
        deviceService.saveDevice(deviceI);
        return "redirect:/deviceOne/deviceList";
    }

    //删除设备
    @RequestMapping(value="delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        DeviceInfo di=deviceService.getDevice(id);
        deviceService.deleteDevice(id);
        redirectAttributes.addFlashAttribute("message", "删除设备" +di.getSerialNumber()+":"+ di.getDeviceType() + "成功");
        return "redirect:/deviceOne/deviceList";
    }

    //编辑设备
    @RequestMapping(value = "editDevice/{id}")
    public String updateDeviceForm(@PathVariable("id") Long id,Model model){
        model.addAttribute("deviceA",deviceService.getDevice(id));
        model.addAttribute("action","update");
        return "device/editDevice";
    }
    @RequestMapping(value = "update")
    public String updateDeviceList(@Valid @ModelAttribute("deviceA") DeviceInfo deviceInfo, RedirectAttributes redirectAttributes){
        User user=new User(getCurrentUserId());
        deviceInfo.setUser(user);
        deviceService.saveDevice(deviceInfo);
        redirectAttributes.addFlashAttribute("message", "编辑此设备成功");
        return "redirect:/deviceOne/deviceList";
    }

    @RequestMapping(value = "seeDevice/{id}")
    public String seeDeviceForm(@PathVariable("id") Long id,Model model){
        model.addAttribute("deviceA",deviceService.getDevice(id));
        return "device/devicePage";
    }

}
