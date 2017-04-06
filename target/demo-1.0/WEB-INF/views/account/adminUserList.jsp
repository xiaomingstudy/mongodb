<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
	<title>用户管理</title>

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

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>

	<div class="mainbody">
		<form method="post" name="formsearch" action="${ctx}/admin/user">
			<div class="classHead">
				<table class="searchtable">
					<div class="searchCase2">
						<tr>

							<td><span class="word">登录名:&nbsp;</span></td>
							<td>&nbsp;<input type="text" name="search_LIKE_loginName" value=""/></td>
							<td>用户名:&nbsp;</td>
							<td>&nbsp;<input type="text" name="search_LIKE_name" value=""/></td>
							<td>角色:&nbsp;</td>
							<td>&nbsp;<input type="text" name="search_LIKE_roleName" value=""/></td>
							<!--<td>&nbsp;<input type="hidden" name="search_EQ_id" value=""/></td>-->
						</tr>
					</div>
				</table>
				<div class="searchCase1">
					<input type="submit" style="background-color:#7aba7b" class="button button-action button-pill" value="查询"/>
					<c:if test="${rolePower==1}">

					<a href="${ctx}/register" style="background-color:#7aba7b" class="button button-action button-pill">添加用户</a>
					</c:if>
					<a href="${ctx}/admin/user" style="background-color:#7aba7b" class="button button-action button-pill">清空</a>
				</div>
			</div>
		</form>
	</div>


	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>登录名</th><th>用户名</th><th>角色</th><th>注册时间<th>管理</th></tr></thead>
		<tbody>
		<c:forEach items="${allUser.content}" var="user">
			<tr>
				<td><a href="${ctx}/admin/user/update/${user.id}">${user.loginName}</a></td>
				<td>${user.name}</td>
				<td>${user.roleName}</td>
				<td>
					<fmt:formatDate value="${user.registerDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
				</td>
				<td>
					<a href="${ctx}/admin/user/delete/${user.id}" style="margin-left: 30px">删除</a>
					<a href="${ctx}/admin/user/update/${user.id}" style="margin-left: 30px">编辑</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pageNumber">
		<tags:pagination page="${allUser}" paginationSize="5"/>
	</div>

</body>
</html>
