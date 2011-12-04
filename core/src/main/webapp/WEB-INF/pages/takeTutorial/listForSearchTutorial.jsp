<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--list searched tutorials-->
<table class="outertable">
<c:forEach items="${tutorials}" var="t">
	<tr>
		<td colspan="2">
			<table class=innertable>
				<tr>
					<td class="iconTd"></td>
			  		<td>
			  			<div class="title">
			  				<div class="floatl tutorialName">${t.name} <fmt:message key="page.takeTutorial.ID"/>${t.id}</div><div class="floatl tutor overhide"><fmt:message key="page.takeTutorial.Instructor"/><c:forEach items="${t.tutors}" var="tu" varStatus="stat">
			  						<c:if test="${stat.index>0}">,</c:if>&nbsp;${tu.firstName}
			  					</c:forEach><c:if test="${t.cost>0}">&nbsp;&nbsp;<fmt:message key="page.takeTutorial.Max"/> ${t.cost} <fmt:message key="page.takeTutorial.Cost"/>${t.cost}</c:if>
			  				</div>
			  			</div>
			  			<div class="content overhide">
			  				<fmt:message key="page.takeTutorial.Description"/>${t.description}
			  			</div>
			  		</td>
			  		<td class="iconTextTd" nowrap="nowrap"><input type="button" value="View" onclick="viewTutorial('bookTutorialPage','${t.id}','rightTakeTutorialPanel','search_start','search_end');"/></td>
				</tr>
			</table>
		</td>
	</tr>
</c:forEach>
<c:if test="${empty tutorials}">
	<tr>
		<td colspan="2">
			<table class=innertable>
				<tr>
					<td class="iconTd"></td>
			  		<td>
			  			<div class="title">
			  				<div class="floatl tutorialName"><fmt:message key="page.takeTutorial.NoRecordsFound"/></div><div class="floatl tutor overhide"></div>
			  			</div>
			  			<div class="content overhide">
			  			</div>
			  		</td>
			  		<td class="iconTextTd" nowrap="nowrap"></td>
				</tr>
			</table>
		</td>
	</tr>
</c:if>
</table>
<!--end list searched tutorials-->