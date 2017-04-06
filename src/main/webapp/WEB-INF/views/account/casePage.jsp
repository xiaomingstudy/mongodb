<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>查看案例</title>
</head>

<body>
<form id="inputForm" action="${ctx}/case/${action}" method="post" class="form-horizontal" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${caseOne.id}" id="id"/>
    <input type="hidden" name="steplistjson" value="" id="steplistjson"/>
    <fieldset>
        <legend><small>编辑案例</small></legend>
        <div class="control-group">
            <label class="control-label">案例编号:</label>
            <div class="controls">
                ${caseOne.caseNumber}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">案例名称:</label>
            <div class="controls">
                ${caseOne.caseName}
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">创建者:</label>
            <div class="controls">
                ${caseOne.personName}
            </div>
        </div>

        <table id="contentTable2" class="table table-striped table-bordered table-condensed">
            <tr>
                <td>当前脚本</td>
                <td>
                    ${caseScriptName}
                </td>
            </tr>
        </table>
        <table id="contentTable" class="table table-striped table-bordered table-condensed">
            <thead>
            <th>步骤</th><th>描述</th><th>预期结果</th></thead>
            <tbody>
            <c:forEach items="${caseStepOne}"  var="cos">
                <tr>
                    <td>${cos.stepNum}</td>
                    <td>${cos.stepDesc}</td>
                    <td>${cos.expectedResult}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <div class="form-actions">
            <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
        </div>
    </fieldset>
</form>

</body>
</html>
