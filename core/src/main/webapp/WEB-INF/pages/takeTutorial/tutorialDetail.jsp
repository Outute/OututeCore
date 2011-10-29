<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!-- tutorial detail -->

<div class="classTutorialDetail">
	<div class="classTutorialTitle"><span>${tutorial.name}</span></div>
	<div class="classDescription"><span>${tutorial.description}</span></div>
	<hr class="tutorialHr"/>
	<div style="float:left; position:relative; width: 100%;">
		<div class="tutorialDetail">
			<div><span class="bold">ID:</span><span>${tutorial.id}</span></div>
			<div><span class="bold">Category:</span><span>${tutorial.category}</span></div>
			<div><span class="bold">Tutorial Type:</span><span>${tutorial.type}</span></div>
		</div>
		<div style="float: right; width:25%; display:inline; text-align: right; position: absolute;bottom: 10px;">
			<span class="takeMoreTutorialIcon"></span>
			<a href="javascript:;" onclick="clickAddDate('addTutorialDatePage','${tutorial.id}','rightTutorialPanel');return false;">Take More Tutorial</a>
		</div>
	</div>
</div>
	<div class="listTutorialSchedule" style="width: 100%; background-color: white; overflow-y: scroll; height: 330px;">
		<table class="outertable">
			<c:forEach items="${tutorialSchedules}" var="ts">
			<tr>
				<td>
					<table class=innertable>
						<tr>
							<td class="iconTd"><span class="calendarIcon"></span></td>
					  		<td>
					  			<div class="tutorialScheduleTimeContent">
					  				<fmt:formatDate value="${ts.startDate}" pattern="EEEEE MMMMM dd, yyyy - "/><fmt:formatDate value="${ts.fromTime}" pattern="hh:mmaaa"/>&nbsp;to&nbsp;<fmt:formatDate value="${ts.toTime}" pattern="hh:mmaaa"/>
					  			</div>
					  			<div class="tutorialScheduleCostContent">
					  				Max&nbsp;<strong>${ts.maxParticipate}</strong>&nbsp;participates&nbsp;-&nbsp;$${ts.cost}&nbsp;<c:if test="${ts.durationType>0}">each</c:if>
					  			</div>
					  		</td>
					  		<td class="iconTextTd" nowrap="nowrap">
								<a href="javascript:;"><span class="removeIcon"></span>Cancel</a>
					  		</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
<!-- end tutorial detail -->