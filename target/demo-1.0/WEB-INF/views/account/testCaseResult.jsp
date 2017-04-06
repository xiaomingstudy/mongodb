<%--
  Created by IntelliJ IDEA.
  User: 27259
  Date: 2017/3/18
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试案例结果</title>
</head>
<body>
<div class="classBody">
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>案例编号</th>
            <th>案例名称</th>
            <th>品牌</th>
            <th>型号</th>
            <th>状态</th>
            <th>结束时间</th>
            <th>结果</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cases.content}"  var="ttt">
            <tr>
                <td><input name="chk" type="checkbox" value="" id="${ttt.id}" onclick=""/></td>
                <td><a href="${ctx}/case/seeCase/${ttt.id}">${ttt.caseNumber}</a> </td>
                <td>${ttt.caseName}</td>
                <td>${ttt.personName}</td>
                <td>
                    <fmt:formatDate value="${ttt.createDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
                </td>
                <td>
                    <a href="${ctx}/case/delete/${ttt.id}" style="margin-right: 30px">删除</a>
                    <a href="${ctx}/case/update/${ttt.id}">编辑</a>
                    <a href="${ctx}/case/update/${ttt.id}">执行结果</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pageNumber">
        <tags:pagination page="${cases}" paginationSize="5"/>
    </div>
</body>
</html>
