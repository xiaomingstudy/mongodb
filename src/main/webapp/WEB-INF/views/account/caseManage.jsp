<%--
  Created by IntelliJ IDEA.
  User: 27259
  Date: 2017/2/23
  Time: 9:59
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
    <title>案例管理</title>
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
           margin-left: 620px;
        }
    </style>
</head>
<body>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>


<div class="mainbody">
    <form method="post" name="formsearch" action="${ctx}/case/caseList">
        <div class="classHead">
            <table class="searchtable">
                <div class="searchCase2">
                    <tr>
                        <td><span class="word">案例名称:&nbsp;</span></td>
                        <td>&nbsp;<input type="text" name="search_EQ_caseName" value=""/></td>
                        <td>案例编号:&nbsp;</td>
                        <td>&nbsp;<input type="text" name="search_EQ_caseNumber" value=""/></td>
                        <td>创建人:&nbsp;</td>
                        <td>&nbsp;<input type="text" name="search_LIKE_personName" value=""/></td>
                    </tr>
                </div>
            </table>
            <div class="searchCase1">
                <input type="submit" style="background-color:#7aba7b" class="button button-action button-pill" value="查询"/>
                <c:if test="${canUsing==1}">
                <a href="${ctx}/case/create" style="background-color:#7aba7b" class="button button-action button-pill">新增案例</a>
                </c:if>
                <a href="${ctx}/case/caseList" style="background-color:#7aba7b" class="button button-action button-pill">清空</a>
            </div>
        </div>
    </form>
</div>

<div class="classBody">
    <form method="post" id="caseChecked">
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th class="conth" style="width: 50px">选择</th>
            <th>案例编号</th>
            <th>案例名称</th>
            <th>创建人</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>



        <c:forEach items="${cases.content}"  var="ttt">
            <tr>
                <td><input name="chk" type="checkbox" value="${ttt.id}" id="${ttt.id}" onclick=""/></td>
                <td><a href="${ctx}/case/seeCase/${ttt.id}">${ttt.caseNumber}</a> </td>
                <td>${ttt.caseName}</td>
                <td>${ttt.personName}</td>
                <td>
                    <fmt:formatDate value="${ttt.createDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
                </td>
                <td>
                    <a href="${ctx}/case/delete/${ttt.id}" style="margin-right: 30px">删除</a>
                    <a href="${ctx}/case/update/${ttt.id}">编辑</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pageNumber">
        <tags:pagination page="${cases}" paginationSize="5"/>
    </div>

    <div class="testCase">
       <input type="button" onclick="caseChoose()"  value="选择设备" class="button button-3d button-royal" style="margin-right: 80px"/>
    </div>
    </form>
</div>
<script>
    function caseChoose() {
        var length = $("input:checkbox:checked").size();
        var caseids="";
        if(length<1){
            alert("请选择案例");
            return;
        }else {
            $("input:checkbox:checked").each(function () {

                caseids += $(this).attr("id")+",";

            });
            $("#caseChecked").attr("action", "${ctx}/deviceOne/deviceList.do?caseids=" + caseids);
            $("#caseChecked").submit();
        }
    }
</script>
</body>

</html>
