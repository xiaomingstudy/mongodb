<%--
  Created by IntelliJ IDEA.
  User: 27259
  Date: 2017/2/22
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>首页</title>
    <style>
        a:link{
            text-decoration: none;
            color: white;

        }
        a:hover{
            text-decoration: none;
            color: white;

        }

        a:visited{
            text-decoration: none;
            color: white;
        }
        .button{
           margin-right: 20px;
        }
    </style>
</head>
<body>

<div class="index_button">
    <input name="power" type="hidden" id="power" value="${power}">
    <button style="width:150px;height:150px" class="button button-3d button-action button-circle button-jumbo"><a href="${ctx}/case/caseList">案例管理</a></button>
    <button style="width:150px;height:150px" class="button button-3d button-action button-circle button-jumbo"><a href="${ctx}/deviceOne/deviceList">设备管理</a></button>
    <button style="width:150px;height:150px" class="button button-3d button-action button-circle button-jumbo"><a href="${ctx}/admin/user">用户管理</a></button>
    <button style="width:150px;height:150px" class="button button-3d button-action button-circle button-jumbo"><a href="${ctx}/roleA/roleAllList">角色管理</a></button>
    <c:if test="${power==1}"><button style="width:150px;height:150px" class="button button-3d button-action button-circle button-jumbo"><a href="${ctx}/roleButton/roleButtonAllList">权限管理</a></button></c:if>
    <c:if test="${power!=1}"><button style="width:150px;height:150px" disabled="disabled" class="button button-3d button-action button-circle button-jumbo"><a href="${ctx}/roleButton/roleButtonAllList">权限管理</a></button></c:if>
</div>

</body>
</html>
