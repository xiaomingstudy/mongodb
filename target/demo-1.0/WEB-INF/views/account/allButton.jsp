<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>权限管理</title>

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
    <form method="post" id="formsearch" name="formsearch" action="${ctx}/roleButton/roleButtonAllList">
        <div class="classHead">
            <table class="searchtable" >
                <div class="searchCase2">
                    <tr>
                        <td style="margin-left: 200px">权限配置:&nbsp;</td>
                        <td>
                            <input type="hidden" value="${roleId}">
                            <select id="roleId">
                                <option value="${thisRole.id}">${thisRole.roleName}</option>
                                <c:forEach items="${allRole}" var="roles">
                                    <option value="${roles.id}" <c:if test="${roleId}==${roles.id}"> selected="true"</c:if>>${roles.roleName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </div>
            </table>
            <div class="searchCase1">
                <input type="button" onclick="roleChoose()" style="background-color:#7aba7b" class="button button-action button-pill" value="查询"/>
                <a href="#" onclick="editRoleChoose()" style="background-color:#7aba7b" class="button button-action button-pill">提交</a>
            </div>
        </div>
    </form>
</div>


<table id="contentTable" border="0" class="table table-striped table-bordered table-condensed">
    <tbody>

    <tr>
        <c:forEach items="${deviceButton}" var="deviceButtons">
            <td>
                <c:if test="${deviceButtons.roleFlag==0}">
                    <input name="chk" type="checkbox" value="${deviceButtons.id}" id="${deviceButtons.id}"/>${deviceButtons.buttonName}
                </c:if>
                <c:if test="${deviceButtons.roleFlag==1}">
                    <input name="chk" checked="checked" type="checkbox" value="${deviceButtons.id}" id="${deviceButtons.id}"/>${deviceButtons.buttonName}
                </c:if>
            </td>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach items="${caseButton}" var="caseButtons">
            <td>
                <c:if test="${caseButtons.roleFlag==0}">
                    <input name="chk" type="checkbox" value="${caseButtons.id}" id="${caseButtons.id}"/>${caseButtons.buttonName}
                </c:if>
                <c:if test="${caseButtons.roleFlag==1}">
                    <input name="chk" checked="checked" type="checkbox" value="${caseButtons.id}" id="${caseButtons.id}"/>${caseButtons.buttonName}
                </c:if>
            </td>
        </c:forEach>
    </tr>

    <tr>
        <c:forEach items="${userButton}" var="userButtons">
            <td>
                <c:if test="${userButtons.roleFlag==0}">
                 <input name="chk" type="checkbox" value="${userButtons.id}" id="${userButtons.id}"/>${userButtons.buttonName}
                </c:if>
                <c:if test="${userButtons.roleFlag==1}">
                    <input name="chk" checked="checked" type="checkbox" value="${userButtons.id}" id="${userButtons.id}"/>${userButtons.buttonName}
                </c:if>
            </td>
        </c:forEach>
    </tr>

    <tr>
        <c:forEach items="${roleButton}" var="roleButtons">
            <td >
                <c:if test="${roleButtons.roleFlag==0}">
                    <input name="chk" type="checkbox" value="${roleButtons.id}" id="${roleButtons.id}"/>${roleButtons.buttonName}
                </c:if>
                <c:if test="${roleButtons.roleFlag==1}">
                    <input name="chk" checked="checked" type="checkbox" value="${roleButtons.id}" id="${roleButtons.id}"/>${roleButtons.buttonName}
                </c:if>
            </td>
        </c:forEach>
    </tr>


    <script>
        function editRoleChoose() {
            var buttonIds="";
                $("input:checkbox:checked").each(function () {

                    buttonIds += $(this).attr("id")+",";

                });
            var roleId = $("#roleId").val();
                $.ajax({
                    type : "post",
                    dataType : "json",
                    url : "${ctx}/roleButton/update.do",
                    data : {"buttonIds" : buttonIds,
                            "roleId" : roleId},
                    success : function(data) {
                        if (data.status == 1) {
                            alert("success");
                            $("#formsearch").attr("action", "${ctx}/roleButton/roleButtonAllList.do?roleId=" + roleId);
                            $("#formsearch").submit();
                        } else if (data.status == 0) {
                            alert(data.message);
                        }
                    }
                });



            <%--var roleId = $("#roleId").val();--%>
                <%--$("#formsearch").attr("action", "${ctx}/roleButton/update.do?buttonIds=" + buttonIds+"&roleId="+roleId);--%>
                <%--$("#formsearch").submit();--%>
        }
        function roleChoose() {
            var roleId = $("#roleId").val();
            $("#formsearch").attr("action", "${ctx}/roleButton/roleButtonAllList.do?roleId=" + roleId);
            $("#formsearch").submit();
        }
    </script>



    </tbody>
</table>
</body>
</html>
