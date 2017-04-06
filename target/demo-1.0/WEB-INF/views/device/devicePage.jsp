<%--
  Created by IntelliJ IDEA.
  User: 27259
  Date: 2017/3/12
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: 27259
  Date: 2017/3/9
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>新增设备</title>
</head>

<body>
<form id="inputForm" action="${ctx}/deviceOne/newDevice" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${deviceA.id}"/>
    <fieldset>
        <legend><small>设备查看</small></legend>
        <div class="control-group">
            <label for="serial_number" class="control-label">序列号:</label>
            <div class="controls">
                <input type="text" id="serial_number" name="serialNumber" disabled="disabled" value="${deviceA.serialNumber}" class="input-large required" minlength="3"/>
            </div>
        </div>
        <div class="control-group">
            <label for="device_type" class="control-label">型号:</label>
            <div class="controls">
                <input type="text" id="device_type" name="deviceType" disabled="disabled" value="${deviceA.deviceType}" class="input-large required"/>
            </div>
        </div>
        <div class="control-group">
            <label for="device_platform" class="control-label">平台:</label>
            <div class="controls">
                <input type="text" id="device_platform" name="devicePlatform" disabled="disabled" value="${deviceA.devicePlatform}" class="input-large required" />
            </div>
        </div>
        <div class="control-group">
            <label for="fac_turer" class="control-label">厂商:</label>
            <div class="controls">
                <input type="text" id="fac_turer" name="facturer" disabled="disabled" value="${deviceA.facturer}" class="input-large required"/>
            </div>
        </div>
        <div class="control-group">
            <label for="device_status" class="control-label">状态:</label>
            <div class="controls">
                <input type="text" id="device_status" name="deviceStatus" disabled="disabled" value="${deviceA.deviceStatus}" class="input-large required" />
            </div>
        </div>
        <div class="control-group">
            <label for="user_name" class="control-label">使用人:</label>
            <div class="controls">
                <input type="text" id="user_name" name="userName" disabled="disabled" value="${deviceA.userName}" class="input-large required"/>
            </div>
        </div>
    </fieldset>
</form>
</body>
</html>


