<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!--start tutorialForm-->
<%@ include file="/common/taglibs.jsp"%><head>
<title><fmt:message key="tutorial.profile.title" /></title>
<meta name="heading"
	content="<fmt:message key='tutorial.profile.heading'/>" />
<meta name="menu" content="TutorialMenu" />
</head>
<!-- Offer Tutorials -->
<table class="takeTutorialTable" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tbody>
		<tr valign="top">
			<td width="30%">
			<!-- Tutorials list -->
				<div class="takeTutorial" style="text-align: right;background-color: #DDD; height: 30px; line-height: 30px; padding-right: 10px;">
					<a href="javascript:;" id="takeMoreTutorial_0" onclick="clickTakeTutorial('rightTakeTutorialPanel','searchTutorialPage');return false;"><span class="addIcon"></span><fmt:message key="page.takeTutorial.TakeMoreTutorial"/></a>
				</div>
				<div id="takeTutorialList" class="tutorialList"  style="overflow-y: scroll; height: 415px; background-color: white;">
					<jsp:include page="/WEB-INF/pages/takeTutorial/listTutorial.jsp"></jsp:include>
				</div>
			<!-- end Tutorials list -->
			</td>
			<td style="background-color: #FFF;">
				<div id="rightTakeTutorialPanel" style="padding: 0; background-color: #FFF; height: 415px;">
					<jsp:include page="/WEB-INF/pages/takeTutorial/searchTutorial.jsp"></jsp:include>
				</div>
			</td>
		</tr>
	</tbody>
</table>
<!-- end Offer Tutorials -->