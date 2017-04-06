package com.demo.web.account;

import com.demo.entity.RoleMaint;
import com.demo.entity.User;
import com.demo.service.account.RoleService;
import com.demo.service.account.ShiroDbRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by 27259 on 2017/3/13.
 */
@Controller
@RequestMapping(value = "/roleA")
public class RoleController {
        @Autowired
        private RoleService roleService;
        private static final String PAGE_SIZE = "5";
        private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
        static {
            sortTypes.put("auto", "自动");
            sortTypes.put("title", "标题");
        }

        @RequestMapping(value = "roleAllList")
        public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                           @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                           @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
                           ServletRequest request) {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            Long userId = getCurrentUserId();

            Page<RoleMaint> roless = roleService.getRole(searchParams, pageNumber, pageSize, sortType);

            model.addAttribute("roleAll", roless);
            model.addAttribute("sortType", sortType);
            model.addAttribute("sortTypes", sortTypes);
            // 将搜索条件编码成字符串，用于排序，分页的URL
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

            return "account/roleList";
        }

        /**
         * 取出Shiro中的当前用户Id.
         */
        private Long getCurrentUserId() {
            ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
            return user.id;
        }

        //删除角色
        @RequestMapping(value = "delete/{id}")
        public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
            RoleMaint roleM= roleService.getRoleOne(id);
            roleService.deleteRoleM(id);
            redirectAttributes.addFlashAttribute("message", "删除角色" + roleM.getRoleName() + "成功");
            return "redirect:/roleA/roleAllList";
        }

        @RequestMapping(value = "create", method = RequestMethod.GET)
        public String createForm(Model model) {
            model.addAttribute("roleOne", new RoleMaint());
            model.addAttribute("action", "addRoles");
            return "account/addRoleForm";
        }

        @RequestMapping(value = "addRoles", method = RequestMethod.POST)
        public String addRoles(@Valid RoleMaint newRole, RedirectAttributes redirectAttributes
        ) {
            User user = new User(getCurrentUserId());
            newRole.setUser(user);
            roleService.saveRoleOne(newRole);
            redirectAttributes.addFlashAttribute("message", "新增角色成功");
            return "redirect:/roleA/roleAllList";
        }

        @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
        public String updateForm(@PathVariable("id") Long id, Model model) {
            model.addAttribute("roleOne", roleService.getRoleOne(id));
            model.addAttribute("action", "editRoleOne");
            return "account/editRoleForm";
        }

        @RequestMapping(value = "editRoleOne", method = RequestMethod.POST)
        public String update(@Valid @ModelAttribute("roleOne") RoleMaint editRoleA, RedirectAttributes redirectAttributes) {
            User user = new User(getCurrentUserId());
            editRoleA.setUser(user);
            roleService.saveRoleOne(editRoleA);
            redirectAttributes.addFlashAttribute("message", "编辑角色成功");
            return "redirect:/roleA/roleAllList";
        }



       @RequestMapping(value = "seeRolePage/{id}", method = RequestMethod.GET)
        public String seePageForm(@PathVariable("id")Long id,Model model) {
            RoleMaint rolem=roleService.getRoleOne(id);
            model.addAttribute("roleOne", rolem);
            return "account/rolePage";
        }



}
