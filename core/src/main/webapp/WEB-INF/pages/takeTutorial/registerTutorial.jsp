<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- register tutorial -->
<div class="classTutorialDetail">
	<div class="classTutorialTitle"><span>${tutorial.name}</span></div>
	<div class="classDescription"><span>${tutorial.description}</span></div>
	<hr class="tutorialHr"/>
	<div style="float:left; position:relative; width: 100%;">
		<div class="tutorialDetail">
			<div><span class="bold"><fmt:message key="page.takeTutorial.ID"/></span><span>${tutorial.id}</span></div>
			<div><span class="bold"><fmt:message key="page.takeTutorial.Category"/></span>
				<span><c:set var="tutorial_category" scope="request" value="${tutorial.category}"/>
			  		<c:set var="category_type" scope="request" value="0"/>
			  		<jsp:include page="/WEB-INF/pages/fregment/tutorial_category.jsp"/></span></div>
			<div><span class="bold"><fmt:message key="page.takeTutorial.TutorialType"/></span>
				<span><c:set var="tutorial_type" scope="request" value="${tutorial.type}"/>
					<c:set var="tutorial_showtype" scope="request" value="0"/>
					<jsp:include page="/WEB-INF/pages/fregment/tutorial_type.jsp"/></span></div>
			<div><span class="bold"><fmt:message key="page.takeTutorial.TotalCost"/></span>
				<span id="takeTutorialTotalCost">${totalCost}</span></div>
		</div>
		<div style="float: right; width:25%; display:inline; text-align: right; position: absolute;bottom: 10px;">
			<span class="takeMoreTutorialIcon"></span>
			<a href="javascript:;" onclick="Util.click('takeMoreTutorial_0');return false;"><fmt:message key="page.takeTutorial.TakeMoreTutorial"/></a>
		</div>
	</div>
</div>
<div class="listTutorialSchedule" style="width: 100%; background-color: white; overflow-y: scroll; height: 290px;">
	<table class="outertable">
		<c:forEach items="${tutorialSchedules}" var="ts" varStatus="status">
		<tr id="register_ts_${status.index}">
			<td>
				<table class=innertable>
					<tr>
						<td class="iconTd"><span class="calendarIcon"></span></td>
				  		<td>
				  			<div class="tutorialScheduleTimeContent">
				  				<fmt:formatDate value="${ts.fromTime}" pattern="EEEEE MMMMM dd, yyyy - " timeZone="${timeZone}"/><fmt:formatDate value="${ts.fromTime}" pattern="hh:mmaaa" timeZone="${timeZone}"/>&nbsp;<fmt:message key="page.takeTutorial.to"/>&nbsp;<fmt:formatDate value="${ts.toTime}" pattern="hh:mmaaa" timeZone="${timeZone}"/>
				  			</div>
				  			<div class="tutorialScheduleCostContent">
				  				<fmt:message key="page.takeTutorial.Max1"/>&nbsp;<strong>${ts.maxParticipate}</strong>&nbsp;<fmt:message key="page.takeTutorial.participates"/>&nbsp;-&nbsp;$${ts.cost}&nbsp;<c:if test="${ts.durationType>0}"><fmt:message key="page.takeTutorial.each"/></c:if>
				  			</div>
				  		</td>
				  		<td class="iconTextTd" nowrap="nowrap">
							<a href="javascript:;" onclick="cancelTutorialSchedule('${ts.id}_<fmt:formatDate value="${ts.fromTime}" pattern="MM/dd/yyyy" timeZone="${timeZone}"/>','rightTakeTutorialPanel','register_ts_${status.index}','takeTutorialTotalCost');return false;"><span class="removeIcon"></span><fmt:message key="page.lable.cancel"/></a>
				  		</td>
					</tr>
				</table>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<div style="width: 100%; background-color: white; overflow: hidden; height: 40px;">
	<table class="w100pc">
		<tr>
			<td align="right">
				<input type="button" value="<fmt:message key="page.button.register"/>" class="button buttonMin" onclick="clickRegister(this,'registerTutorial','listTakedTutorial','${tutorial.id}','rightTakeTutorialPanel','takeTutorialList');"/>
				<input type="button" value="<fmt:message key="page.button.cancel"/>" class="button buttonMin" onclick="Util.click('takeMoreTutorial_0');"/>
			</td>
		</tr>
	</table>
</div>
<!-- end register tutorial -->