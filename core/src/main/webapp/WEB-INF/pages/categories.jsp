<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- Categories -->
<head>
    <title><fmt:message key="tutorial.profile.title"/></title>
    <meta name="heading" content="<fmt:message key='tutorialList.heading'/>"/>
    <meta name="menu" content="AdminMenu"/>
</head>
<style>
span.pagelinks{
	text-align: right;
}
</style>

<div class="main-content">
<h2><strong>Outute Categories</strong></h2>
   <table width="100%" border="0" cellspacing="10" cellpadding="10">
     <tr>
       <td width="33%"><a href="#" onclick="updateCategory('language')"><img src="images/language.jpg" id="languageImg"/></a></td>
       <td width="33%"><a href="#" onclick="updateCategory('academic')"><img src="images/academic.jpg" id="academicImg"/></a></td>
       <td width="33%"><a href="#" onclick="updateCategory('oversea')"><img src="images/oversea.jpg" id="overseaImg"/></a></td>
     </tr>
     <tr>
       <td width="33%"><a href="#" onclick="updateCategory('dummy')"><img src="images/dummy.jpg" id="dummyImg"/></a></td>
       <td width="33%"><a href="#" onclick="updateCategory('computer')"><img src="images/computer.jpg" id="computerImg"/></a></td>
       <td width="33%"><a href="#" onclick="updateCategory('other')"><img src="images/otherTutotial.jpg" id="otherImg"/></a></td>
     </tr>
  	</table>
	<table width="100%" border="0" cellspacing="10" cellpadding="10">
		<tbody>
			<tr>
				<td>
					<div id="rightTakeTutorialPanel" style="padding: 0; background-color: #FFF; height: 415px;">
						<jsp:include page="/WEB-INF/pages/takeTutorial/searchTutorialForCategory.jsp"/>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	<script type="text/javascript">
		Util.click(Util.id('takedTutorial_${id}'));
	</script>
</div>