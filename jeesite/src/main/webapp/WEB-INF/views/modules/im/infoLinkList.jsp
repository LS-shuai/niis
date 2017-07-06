<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>情报来源链接管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/infolink/info">情报链接列表</a></li>
		<shiro:hasPermission name="InfoLink:add">
			<li><a href="${ctx}/infolink/info/form">添加链接</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="infoLink"
		action="${ctx}/infoLink/info/find" method="post"
		class="breadcrumb form-search">
		<div>
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" /> <label>链接编码：</label>
			<form:input path="infoLinkCode" htmlEscape="false"
				onkeyup="value=value.replace(/[^\w\/]/ig,'')" maxlength="50"
				class="input-medium" />
			&nbsp;&nbsp;<label>链接名称：</label>
			<form:input path="infoLinkName" htmlEscape="false"
				onkeyup="value=value.replace(/[^\w\u4E00-\u9FA5]/g, '')"
				maxlength="50" class="input-medium" />
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="查询" />
		</div>

	</form:form>

	<sys:message content="${message}" />
	<table id="contentTable" style="TABLE-LAYOUT: fixed"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<input id="id" name="id" type="hidden" value="${InfoLink.id}" />
				<th>链接编码</th>
				<th>链接名称</th>
				<th>链接类型</th>
				<th>链接值</th>
				<th>链接开关</th>
				<th>备注</th>
				<shiro:hasPermission name="InfoLink:add">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="InfoLink">
				<tr>
					<td style="WORD-WRAP: break-word" width="350px">${InfoLink.infoLinkCode}</td>
					<td style="WORD-WRAP: break-word" width="350px">${InfoLink.infoLinkName}</td>
 					<td><c:forEach items="${fns:getDictList('LINK_TYPE')}"
							var='dict'>
							<c:set value="${ fn:split(InfoLink.linkType, ',') }" var="names" />
							<c:forEach items="${ names }" var="name">
								<c:set var="idAsString">${dict.value}</c:set>
								<c:if test="${name eq idAsString}">
									<span>${dict}</span>
								</c:if>
							</c:forEach>
						</c:forEach></td>  
			 
						
					<td style="WORD-WRAP: break-word" width="500px">${InfoLink.linkValue}</td>
					<td><c:if test="${InfoLink.deleteStatus eq '1'}">
							<span>关闭</span>
						</c:if> <c:if test="${InfoLink.deleteStatus eq '0'}">
							<span>开启</span>
						</c:if></td>
					<td style="WORD-WRAP: break-word" width="350px">${InfoLink.infoLinkRemarks}</td>

					<shiro:hasPermission name="InfoLink:add">
						<td><a href="${ctx}/infolink/info/form?id=${InfoLink.id}">修改</a>&nbsp;
							<a href="${ctx}/infolink/info/delete?id=${InfoLink.id}&status=${InfoLink.deleteStatus}"><c:if test="${InfoLink.deleteStatus eq '0'}">
							<span>关闭</span>
						</c:if> <c:if test="${InfoLink.deleteStatus eq '1'}">
							<span>开启</span>
						</c:if></a>
					</shiro:hasPermission>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>