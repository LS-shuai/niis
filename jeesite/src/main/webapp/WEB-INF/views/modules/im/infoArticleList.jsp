<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>情报信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
function page(n, s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}
	// --列头全选框被单击---
	function ChkAllClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		var tempState = cbAll.checked;
		for (i = 0; i < arrSon.length; i++) {
			if (arrSon[i].checked != tempState)
				arrSon[i].click();
		}
	}

	// --子项复选框被单击---
	function ChkSonClick(sonName, cbAllId) {
		var arrSon = document.getElementsByName(sonName);
		var cbAll = document.getElementById(cbAllId);
		for (var i = 0; i < arrSon.length; i++) {
			if (!arrSon[i].checked) {
				cbAll.checked = false;
				return;
			} else
				cbAll.checked = true;
		}
	}

	function getContent(id) { 
		top.$.jBox.open("iframe:${ctx}/infoarticle/info/getContent?id="
				+ id, "文章详情", 800, 500, {
			buttons : {
				"关闭" : true
			},
			loaded : function(h) {
				$(".jbox-content", top.document).css("overflow-y", "hidden");
			}
		});
	}


</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/infoarticle/info">情报信息列表</a></li>
	</ul>
 	<form:form id="searchForm" modelAttribute="infoArticle"
		action="${ctx}/infoarticle/find" method="post"
		class="breadcrumb form-search">
		<div>
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" /> <label>文章标题：</label>
			<form:input path="title" htmlEscape="false"
				onkeyup="value=value.replace(/[^\w\/]/ig,'')" maxlength="50"
				class="input-medium" />
			&nbsp;&nbsp; 
			
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="查询" />&nbsp;&nbsp;&nbsp;<!-- <input id="btnSubmit" class="btn btn-primary" type="submit"
				value="批量删除" /> -->
		</div>

	</form:form> 
	<sys:message content="${message}" />
	<table id="contentTable" style="TABLE-LAYOUT: fixed"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<input id="id" name="id" type="hidden" value="${InfoArticle.id}" />
				<th width="20px"><input id="chkAll" name="chkAll" type="checkbox" value="checkbox" onClick="ChkAllClick('data','chkAll') " /></th>
				<th width="200px">标题</th>
				<th width="200px">文章链接</th>
				<!-- 				<th>类型</th>
 -->
				<th width="300px">关键字</th>
				<th width="50px">内容</th>
				<th width="150px">创建时间</th>
				<shiro:hasPermission name="cms:article:edit">
					<th width="100px">操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="InfoArticle">
				<tr>
					<td><input type="checkbox" name="data"
						onClick="ChkSonClick('data','chkAll')" value="${InfoArticle.id}" /></td>
					<td>${InfoArticle.title}</td>
					<td>${InfoArticle.link}</td>
	
					<td >${InfoArticle.keywords}</td>
					<td><a href="javascript:return" onclick="getContent('${InfoArticle.id}')">详情</a></td> 
					<td >${InfoArticle.createon}</td>

					<shiro:hasPermission name="cms:article:edit">
						<td><a href="${ctx}/infoarticle/info/delete?id=${InfoArticle.id}">删除</a> &nbsp;&nbsp;
						<a href="${ctx}/infoarticle/info/push?id=${InfoArticle.id}">推送</a>&nbsp;
					</shiro:hasPermission>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>