<%--
  Created by IntelliJ IDEA.
  User: 27259
  Date: 2017/3/13
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>角色管理</title>

    <style>
        .word{
            margin-bottom: 20px;
        }
        .searchCase1,.searchCase2{
            text-align: center;
            margin-top:15px;
            margin-bottom: 10px;
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
        .searchtable{
            text-align: center;
            margin: 0 auto;
        }
    </style>

</head>

<body>
<c:if test="${not empty message}">
    <div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
</c:if>

<div class="mainbody">
    <form method="post" name="formsearch" action="${ctx}/roleA/roleAllList">
        <div class="classHead">
            <table class="searchtable" >
                <div class="searchCase2">
                    <tr>
                        <td style="margin-left: 200px">角色名:&nbsp;</td>
                        <td>&nbsp;<input type="text" name="search_LIKE_roleName" value=""/></td>
                    </tr>
                </div>
            </table>
            <div class="searchCase1">
                <input type="submit" style="background-color:#7aba7b" class="button button-action button-pill" value="查询"/>
                <a href="${ctx}/roleA/create" style="background-color:#7aba7b" class="button button-action button-pill">添加</a>
            </div>
        </div>
    </form>
</div>


<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead><tr><th>角色名</th><th>角色描述</th><th>创建日期</th><th>操作</tr></thead>
    <tbody>
    <c:forEach items="${roleAll.content}" var="rolerA">
        <tr>
            <td><a href="${ctx}/roleA/seeRolePage/${rolerA.id}">${rolerA.roleName}</a></td>
            <td>${rolerA.roleComment}</td>
            <td>
                <fmt:formatDate value="${rolerA.roleDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
            </td>
            <td>
                <a href="${ctx}/roleA/delete/${rolerA.id}" style="margin-left: 30px">删除</a>
                <a href="${ctx}/roleA/update/${rolerA.id}" style="margin-left: 30px">编辑</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pageNumber">
    <tags:pagination page="${roleAll}" paginationSize="5"/>
</div>
</body>
</html>
