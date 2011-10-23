<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start add tutorialForm-->
<form id="searchTutorial" action="listForSearchTutorial" method="post" class="searchContent" onsubmit="return false;">
<div>
	<table>
		<tr><th>Search Tutorial</th></tr>
		<tr><td>ID:</td><td><input type="text" name="id" class="inputMin w100px;"/></td></tr>
		<tr>
			<td colspan="5">
				<div style="position: relative; display: inline;"><hr style="margin: 10px 0px;"/><div style="position:absolute;float: right; left: 200px; background-color: white; padding: 0px 10px;">OR</div></div>
			</td>
		</tr>
		<tr><td>Name:</td><td><input type="text" name="name" class="inputMin w200px;"/></td></tr>
		<tr>
			<td>Start:</td><td><input type="text" name="start" class="inputMin w100px;"/></td>
			<td>End:</td><td><input type="text" name="end" class="inputMin w100px;"/></td>
		</tr>
		<tr>
			<td>Tutorial Name:</td><td><input type="text" name="name" class="inputMin w200px;"/></td>
			<td></td><td></td>
			<td><input type="submit" name="Search" value="Search" class="button buttonMin" onclick="return false;"/></td>
		</tr>
	</table>
	<div class="listTutorial" style="width: 100%; background-color: white; overflow-y: scroll; height: 280px;">
		<table class="outertable">
			<tr>
				<th>Tutorial Search Result:</th>
				<td><select><option>Sort by</option></select></td>
			</tr>
			<c:forEach items="${timeSchedules}" var="ts">
			<tr>
				<td colspan="2">
					<table class=innertable>
						<tr>
							<td class="iconTd"><span class="calendarIcon"></span></td>
					  		<td>
					  			<div class="timeScheduleTimeContent">
					  				<fmt:formatDate value="${ts.startDate}" pattern="EEEEE MMMMM dd, yyyy - "/><fmt:formatDate value="${ts.fromTime}" pattern="hh:mmaaa"/>&nbsp;to&nbsp;<fmt:formatDate value="${ts.toTime}" pattern="hh:mmaaa"/>
					  			</div>
					  			<div class="timeScheduleCostContent">
					  				Max&nbsp;<strong>${ts.maxParticipate}</strong>&nbsp;participates&nbsp;-&nbsp;$${ts.cost}&nbsp;<c:if test="${ts.repeat>0}">each</c:if>
					  			</div>
					  		</td>
					  		<td class="iconTextTd" nowrap="nowrap"><a href="javascript:;" onclick="editTimeSchedule('addTutorialDatePage','${ts.id}','${tutorial.id}','rightTutorialPanel');return false;"><span class="editTutorialIcon">+</span>Edit</a></td>
					  		<td class="iconTextTd" nowrap="nowrap"><a href="javascript:;" onclick="deleteTimeSchedule('removeTimeSchedule','${ts.id}','${tutorial.id}','tutorialDetailFragment','rightTutorialPanel');return false;"><span class="deleteTutorialIcon">-</span>Delete</a></td>
						</tr>
					</table>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>
</form>

<!--start add tutorialForm-->