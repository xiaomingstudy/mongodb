<%--
  Created by IntelliJ IDEA.
  User: 27259
  Date: 2017/3/13
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: 27259
  Date: 2017/3/13
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>编辑角色</title>
</head>
<body>
<form id="inputForm" action="${ctx}/roleA/addRoles" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${roleOne.id}"/>
    <fieldset>
        <legend><small>编辑角色</small></legend>
        <div class="control-group">
            <label for="role_name" class="control-label">角色名称:</label>
            <div class="controls">
                <input type="text" id="role_name" name="roleName"  value="${roleOne.roleName}" class="input-large required" minlength="3"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">角色编码:</label>
            <div class="controls">
                <input type="text" id="role_code" name="roleCode" value="${roleOne.roleCode}" class="input-large required"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">角色描述:</label>
            <div class="controls">
                <input type="text" id="role_comment" name="roleComment" value="${roleOne.roleComment}" class="input-large required"/>
            </div>
        </div>
        <div class="form-actions">
            <input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
            <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
        </div>
    </fieldset>
</form>
<script>
    $(document).ready(function() {
        //聚焦第一个输入框
        $("#case_num").focus();
        //为inputForm注册validate函数
        $("#inputForm").validate();
    });
</script>
</body>
</html>


