<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!--start tutorialForm-->
<%@ include file="/common/taglibs.jsp"%><head>
<title><fmt:message key="tutorial.profile.title" /></title>
<meta name="heading"
	content="<fmt:message key='tutorial.profile.heading'/>" />
<meta name="menu" content="TutorialMenu" />
</head>
<!-- Offer Tutorials -->
<table class="offerTutorialTable" width="100%" border="0" cellspacing="0" cellpadding="0">
	<tbody>
		<tr valign="top">
			<td width="30%">
			<!-- Tutorials list -->
				<div class="addTutorial" style="text-align: right;background-color: #DDD; height: 30px; line-height: 30px; padding-right: 10px; *width: 310px;">
					<a href="javascript:;" onclick="clickAddTutorial('rightTutorialPanel','addTutorialPage?ajax=true');return false;"><span class="addIcon"></span><fmt:message key="page.offerTutorial.AddTutorial"/></a>
				</div>
				<div id="tutorialListTable" class="tutorialList"  style="overflow-y: scroll; height: 435px; background-color: white; width: 300px;">
					<jsp:include page="/WEB-INF/pages/tutorial/listTutorialFragment.jsp"/>
				</div>
			<!-- end Tutorials list -->
			</td>
			<td style="background-color: #FFF;">
				<div id="rightTutorialPanel" style="padding: 0; background-color: #FFF; height: 420px;">
					<jsp:include page="/WEB-INF/pages/tutorial/tutorialDetailFragment.jsp"/>
				</div>
			</td>
		</tr>
	</tbody>
</table>
<!-- end Offer Tutorials -->
<script type="text/javascript">
Util.click(Util.id('tutorial_id_${id}')||Util.id('firstTutorial'));
</script>