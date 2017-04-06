<%--
  Created by IntelliJ IDEA.
  User: 27259
  Date: 2017/3/7
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>设备管理</title>
    <style>
        .word{
            margin-bottom: 20px;
        }
        .searchCase1,.searchCase2{
            text-align: center;
            margin-top:15px;
            margin-bottom: 10px;
        }
        .searchCase2{
            text-align: center;
            margin-left:30px;
            margin-top:20px;
        }
        .searchCase1 a{
            margin-left:30px;
        }
        .classHead{
            border: 1px solid rgba(191, 191, 191, 0.58);
            background-color: rgba(224, 224, 224, 0.13);
            border-radius: 8px;
        }
        .classBody{
            margin-top: 30px;
        }

        .classBody td{
            text-align: center;
            border: 1px solid rgba(185, 185, 185, 0.58);
        }
        .testCase{
            text-align: center;
        }
        .pageNumber{
            margin-top: 30px;
            margin-left: 650px;
        }
    </style>
</head>
<script>
   /* function  searchDevices() {
        $("#serachForm").submit();
    }
*/

</script>

<body>

<c:if test="${not empty message}">
    <div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>


<div class="mainbody">
    <form method="post" name="formsearch" action="${ctx}/deviceOne/deviceList">
        <div class="classHead">
            <table class="searchtable">
                <div class="searchCase2">
                    <tr>

                        <td>序列号:&nbsp;</td>
                        <td>&nbsp;<input type="text" id="serialNumber" name="search_EQ_serialNumber" value="${serialNumber}"/></td>
                        <td>使用人:&nbsp;</td>
                        <td>&nbsp;<input type="text" name="search_LIKE_userName" value="${userName}"/></td>
                        <td><span class="word">厂商:&nbsp;</span></td>
                        <td>&nbsp;<input type="text" name="search_EQ_facturer" value="${facturer}"/></td>
                    </tr>
                </div>
            </table>
            <div class="searchCase1">
                <input type="submit" style="background-color:#7aba7b" class="button button-action button-pill" value="查询"></a>
                <c:if test="${addDevicesButton==1}">
                <a href="${ctx}/deviceOne/create" style="background-color:#7aba7b" class="button button-action button-pill">新增设备</a>
                </c:if>
                <a href="${ctx}/deviceOne/deviceList" style="background-color:#7aba7b" class="button button-action button-pill">清空</a>
            </div>
        </div>
    </form>
</div>

<div class="classBody">
    <form method="post" id="devicePhoneChecked">
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th class="conth" style="width: 50px">选择</th>
            <th>序列号</th>
            <th>型号</th>
            <th>平台</th>
            <th>厂商</th>
            <th>状态</th>
            <th>使用人</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <input type="hidden" value="${caseIds}" name="caseIds" id="caseids">
        <c:forEach items="${devices.content}"  var="dev">
            <tr>
                <td><input name="chk" id="${dev.id}" type="checkbox" class="deviceChecked" value="${dev.id}"/></td>
                <td><a href="${ctx}/deviceOne/seeDevice/${dev.id}">${dev.serialNumber}</a> </td>
                <td>${dev.deviceType}</td>
                <td>${dev.devicePlatform}</td>
                <td>${dev.facturer}</td>
                <td>${dev.deviceStatus}</td>
                <td>${dev.userName}</td>
                <td>
                    <fmt:formatDate value="${dev.insertTime}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
                </td>
                <td>
                    <a href="${ctx}/deviceOne/delete/${dev.id}" style="margin-right: 30px">删除</a>
                    <a href="${ctx}/deviceOne/editDevice/${dev.id}">编辑</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pageNumber">
        <tags:pagination page="${devices}" paginationSize="5"/>
    </div>
    <div class="testCase">
        <c:if test="${refreshDeviceButton==1}">
        <span class="button-wrap">
            <a href="${ctx}/deviceOne/deviceList" class="button button-pill button-raised button-primary">刷新设备</a>
        </span>
        </c:if>
        <c:if test="${submitDeviceButton==0}">
        <span class="button-wrap" style="margin-left: 30px">
            <input class="button button-pill button-raised button-primary" onclick="chooseAutoTest()" value="自动化测试">
        </span>
        </c:if>
    </div>
    </form>
</div>

<script>
    function chooseAutoTest(){
        var caseId = $("#caseids").val();
        var legnth = $(".deviceChecked").size();
        var caseIdAll = caseId.split(",");
        var lengths = caseIdAll.length;
        var deviceId = "";
        if (legnth < 1) {
            alert("请选择Android手机设备");
            return;
        }else{
            $("input:checkbox:checked").each(function(){
                deviceId+=$(this).attr("id")+",";
            });
            var url="${ctx}/jobTask/job.do?caseId=" + caseId+"&deviceId="+deviceId;
            $.ajax({
                type : "post",
                dataType : "json",
                url : url,
                success : function(data) {
                    if (data.status == 1) {
                        alert(data.message);
                        $("#devicePhoneChecked").attr("action","${ctx}/case/caseList.do");
                        $("#devicePhoneChecked").submit();
                    } else if (data.status == 0) {
                        alert(data.message);
                    }
                }
            });



           <%--$("#devicePhoneChecked").attr("action", "${ctx}/jobTask/job.do?caseids=" + caseids+"&deviceId="+deviceId);--%>
            <%--$("#devicePhoneChecked").submit();--%>
        }
    }
</script>

</body>
</html>
