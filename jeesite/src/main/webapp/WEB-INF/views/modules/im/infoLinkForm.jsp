<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>情报来源链接管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						jQuery.validator.addMethod("alnum", function(value,
								element) {
							return this.optional(element)
									|| /^[a-zA-Z0-9]+$/.test(value);
						}, "只能包括英文字母和数字");
						$("#name").focus();
						$("#inputForm")
								.validate(
										{
											rules : {
												"infoLinkCode" : {
													"required" : true,
													"maxlength" : 50,

													remote : "${ctx}/infolink/info/checkInfoLinkCode?oldInfoLinkCode="
															+ encodeURIComponent('${InfoLink.infoLinkCode}')
												},
												"infoLinkName" : {
													remote : "${ctx}/infolink/info/checkInfoLinkName?oldinfoLinkName="
															+ encodeURIComponent('${InfoLink.infoLinkName}')
												},

												"linkValue" : {
													"required" : true,
													url : true,
												},
												"linkType" : {
													"required" : true,
												}

											},
											messages : {
												infoLinkCode : {
													"required" : "链接编码不能为空",
													"maxlength" : "链接编码最长50个字符",

													remote : "链接编码已存在",
												},
												"infoLinkName" : {
													"required" : "链接名称不能为空",
													"maxlength" : "链接名称最长100个字符",
													remote : "链接名称已存在",
												},
												"linkValue" : {
													"required" : "链接不能为空",
													url : "请填写正确的链接",
												},
												"linkType" : {
													"required" : "请选择链接类型",
												},
											},

											submitHandler : function(form) {
												loading('正在提交，请稍等...');
												form.submit();
											},
											errorContainer : "#messageBox",
											errorPlacement : function(error,
													element) {
												$("#messageBox").text(
														"输入有误，请先更正。");
												if (element.is(":checkbox")
														|| element.is(":radio")
														|| element
																.parent()
																.is(
																		".input-append")) {
													error.appendTo(element
															.parent().parent());
												} else {
													error.insertAfter(element);
												}
											}
										});

						//回显链接类型 （checkbox选中）
						var org = '${InfoLink.linkType}';
						var orgArr = [];
						if (org != "") {
							orgArr = org.split(",");
						}
						$('.org').each(function(i) {
							for (var j = 0; j < orgArr.length; j++) {
								if (i == orgArr[j] && orgArr.length != 0) {
									$(this).attr('checked', 'checked');
								}
							}
						});
					});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/infolink/info/">链接信息列表</a></li>

		<li class="active"><a
			href="${ctx}/infolink/info/form?id=${InfoLink.id}"> ${not empty InfoLink.id ? '链接修改':'链接添加'}
		</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="InfoLink"
		action="${ctx}/infolink/info/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />

		<div class="control-group">
			<label class="control-label">链接编码: </label>
			<div class="controls">
				<input id="oldinfoLinkCode" name="oldinfoLinkCode" type="hidden"
					value="${InfoLink.infoLinkCode}">
				<form:input path="infoLinkCode" htmlEscape="false" maxlength="50"
					class="required input-xlarge alnum" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>

		</div>
		<div class="control-group">
			<label class="control-label">链接名称: </label>
			<div class="controls">
				<input id="oldinfoLinkName" name="oldinfoLinkName" type="hidden"
					value="${InfoLink.infoLinkName}">

				<form:input path="infoLinkName" htmlEscape="false"
					onkeyup="value=value.replace(/[^\w\u4E00-\u9FA5]/g, '')"
					maxlength="100" class="required input-xlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>

		</div>

		<div class="control-group">
			<label class="control-label">链接类型: </label>
			<div class="controls">
				<form:select path="linkType" class="input-medium">
					<option value="">请选择</option>
					<form:options items="${fns:getDictList('LINK_TYPE')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline"><font color="#FF0000">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">链接值: </label>
			<div class="controls">
				<form:textarea path="linkValue" htmlEscape="false" rows="3"
					maxlength="200" class="input-xxlarge" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>

		</div>
		<div class="control-group">
			<label class="control-label"> 链接开关:</label>
			<div class="controls">
				<form:select path="deleteStatus">
					<form:option value="1" label="关闭" />
					<form:option value="0" label="开启" />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">信息备注:</label>
			<div class="controls">
				<form:textarea path="infoLinkRemarks" htmlEscape="false"
					onkeyup="value=value.replace(/[^\w\u4E00-\u9FA5]/g, '')" rows="4"
					maxlength="200" class="input-xxlarge" />
			</div>
		</div>

		<div class="form-actions">

			<shiro:hasPermission name="infoLink:add">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />
			</shiro:hasPermission>
			&nbsp; <input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>