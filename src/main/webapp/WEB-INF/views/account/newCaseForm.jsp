<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>新增案例</title>
</head>
<body>
<form id="inputForm" action="${ctx}/case/addCase" method="post" enctype="multipart/form-data" class="form-horizontal">
    <input type="hidden" name="id" value="${caseOne.id}"/>
    <input type="hidden" name="steplistjson" value="" id="steplistjson"/>
    <fieldset>
        <legend><small>新增案例</small></legend>
        <div class="control-group">
            <label for="case_num" class="control-label">案例编号:</label>
            <div class="controls">
                <input type="text" id="case_num" name="caseNumber"  value="${caseOne.caseNumber}" class="input-large required" minlength="3"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">案例名称:</label>
            <div class="controls">
                <input type="text" id="caseName" name="caseName" value="${caseOne.caseName}" class="input-large required"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">创建者:</label>
            <div class="controls">
                <input type="text" id="personName" name="personName" value="${caseOne.personName}" class="input-large required"/>
            </div>
        </div>
        <table id="contentTable2" class="table table-striped table-bordered table-condensed">
            <tr>
                <td>脚本</td>
                <td>
                    <input type="file" name="scriptPath" id="scriptPath"/>
                    <%--<iframe id="scriptId" src="<%=path%>/fileUpload/fileUploadPage.do?fileCount=1"></iframe>--%>
                </td>
            </tr>
        </table>
        <table id="contentTable" class="table table-striped table-bordered table-condensed">
            <thead>
            <th>步骤</th><th>描述</th><th>预期结果</th><th><input type="button" onclick="addStep(this)" value="操作步骤" /></th></thead>
            <tbody>
            </tbody>
        </table>



        <div class="form-actions">
            <input id="submit_btn" class="btn btn-primary" type="button" value="提交" onclick="mySubmit()"/>&nbsp;
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

    function addStep(obj){
            var insertObj=$("<tr>"+
                "<td style='display: none'><input type='hidden' name='stepId'></td>"+
                "<td><input type='text' name='stepNum'/></td>"+
                "<td><textarea name='stepDesc' rows='3' col='20'></textarea></td>"+
                "<td><textarea name='expectedResult' rows='3' col='20'></textarea></td>"+
                "<td colspan='2'>" +
                "<input type='button' value='添加' onclick='addStep(this)' />"+
                "<input type='button' value='删除' onclick='deleteStep(this)'/> </td></tr>"
            );
            insertObj.insertAfter($(obj).closest("tr"));
            $("input[name='stepNum']").each(function(index){
                $(this).val(index+1);
            });
        }

        function deleteStep(obj){
            $(obj).closest("tr").remove();
            $("input[name='stepNum']").each(function (index) {
                $(this).val(index+1);
            });
    }

    function mySubmit() {
        var steplistjson = getStepList();
        $("#steplistjson").val(steplistjson);
        $("#inputForm").submit();
    }
    function getStepList(){
        var stepArr = "";
        $("#contentTable tr:gt(0)").each(function(i){
            var stepId = $(this).find("input[name='stepId']").val();
            if(stepId==null||stepId=="undefined"||stepId==''){
                stepId = ""
            }
            var stepNum  = $(this).find("input[name='stepNum']").val();
            var stepDesc = $(this).find("textarea[name='stepDesc']").val();
            if(stepDesc == ""){
                alert("第"+(i+1)+"行步骤描述不能为空");
                stepArr = "";
                return false;
            }
            var expectedResult = $(this).find("textarea[name = 'expectedResult']").val();
            if(expectedResult == ""){
                alert("第"+(i+1)+"行步骤预期结果不能为空");
                stepArr = "";
                return false;
            }
            stepArr+="stepNum:"+stepNum+",stepDesc:"+stepDesc+",expectedResult:"+expectedResult+"&";
        });
        return stepArr;
    }

</script>
</body>
</html>
