<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章详情</title>
	<meta name="decorator" content="default"/>	
		<style type="text/css">
	.input-medium {
    width: 206px;
}
	input, textarea, .uneditable-input {
 	width: 190px;
}
	</style>
	
</head>
<body>	
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			<th>文章编号</th> 
			<th>关键字</th> 
			<th>文章内容</th> 
			</tr>
		</thead>
		<tbody>
			<tr>
					<td>${InfoArticle.id}</td>
					<td>${InfoArticle.keywords}</td>					
					<td>${InfoArticle.content}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>