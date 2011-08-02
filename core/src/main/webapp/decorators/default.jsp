<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<%@ include file="/common/meta.jsp" %>
		<title><decorator:title/> | <fmt:message key="webapp.name"/></title>
		<link rel="stylesheet" type="text/css" media="all" href="<c:url value='css/outute_style.css'/>" />
		<link rel="stylesheet" type="text/css" media="print" href="<c:url value='/styles/${appConfig["csstheme"]}/print.css'/>" />
		<script type="text/javascript" src="<c:url value='/js/outute_js.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/scripts/prototype.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/scripts/scriptaculous.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/scripts/global.js'/>"></script>
		<style type="text/css">
		<!--
		.STYLE1 {color: #006600}
		-->
		</style>
		<decorator:head/>
	</head>

	<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>
		<div id="container">
			<div class="content-box">
				<jsp:include page="/common/header.jsp"/>
				<%@ include file="/common/messages.jsp" %>
				<decorator:body/>
			</div>
			<div class="footer-links floatl">
				<jsp:include page="/common/footer.jsp"/>
			</div>
		</div>
	</body>
</html>
