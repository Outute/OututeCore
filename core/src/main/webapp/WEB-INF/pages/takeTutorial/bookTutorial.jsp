<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<fmt:setLocale value="en_US"/>
<!--start add tutorialForm-->
<div script="Util.calender('mycalendar','tutorialDate','<fmt:formatDate value="${timeSchedule.startDate}" pattern="yyyyMMdd"/>');" style="display: none;"></div>
<form id="addTimeSchedule" action="addTimeShedule" method="post" onsubmit="saveTimeSchedule('addTimeShedule','addTimeSchedule','tutorialDetailFragment','rightTutorialPanel');return false;">
<input type="hidden" name="tutorial.id" value="${tutorial.id}"/>
<input type="hidden" name="timeSchedule.id" value="${timeSchedule.id}"/>
<input type="hidden" name="timeSchedule.startDate" id="tutorialDate" value=""/>
<div class="padding10">
	<div class="addTutorialTitle"><span>Add Date for This Tutorial</span></div>
	<table width="100%" height="300px" border="0" cellpadding="0" cellspacing="0" style="background-color: #EEE">
		<tr valign="top">
			<td valign="middle"><div id="mycalendar" class="w200px padding10"></div></td>
			<td>
				<table class="formContent">
					<tr valign="top">
						<td><label for="from">From:</label></td>
						<td>
							<input type="text" class="input" id="form" name="fromTime" size="10" value="<fmt:formatDate value="${timeSchedule.fromTime}" pattern="hh:mmaaa"/>"/>
							<div id="fromam" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">am</div></div>
							<div id="frompm" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">pm</div></div>
						</td>
						<td><label for="to">To:</label></td>
						<td>
							<input type="text" class="input" id="tc" name="toTime" size="10" value="<fmt:formatDate value="${timeSchedule.toTime}" pattern="hh:mmaaa"/>"/>
							<div id="toam" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">am</div></div>
							<div id="topm" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">pm</div></div>
						</td>
					</tr>
					<tr>
						<td><label for="repeat">Repeats:</label></td>
						<td colspan="3"><select id="repeat" name="timeSchedule.repeat" class="select w200px" onchange="this.value=='0'?Util.hideId(['endstr','costtr','partr']):Util.showId(['endstr','costtr','partr']);">
								<option value="0" <c:if test="${timeSchedule.repeat==0}">selected="selected"</c:if>>No Repeat</option>
								<option value="1" <c:if test="${timeSchedule.repeat==1}">selected="selected"</c:if>>Weekly</option>
							</select>
						</td>
					</tr>
					<tr id="endstr" style="<c:if test="${timeSchedule.repeat==0}">display:none;</c:if>">
						<td valign="top"><label>Ends:</label></td>
						<td colspan="3">
							<input type="radio" class="input" id="ends1" name="ends" value="0" <c:if test="${timeSchedule.endsOccurrence>0}">checked="checked"</c:if>/><label for="ends1">Never</label><br/>
							<input type="radio" class="input" id="ends2" name="ends" value="1" <c:if test="${timeSchedule.endsOccurrence>0}">checked="checked"</c:if>/><label for="ends2">After</label>
							<input type="text" class="input" id="occurrences" name="timeSchedule.endsOccurrence" size=10 value="${timeSchedule.endsOccurrence}"/><label for="occurrences">occurrences</label>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<hr style="color: #CCC; margin: 0px 0px 20px 0px;"/>
						</td>
					</tr>
					<tr id="costtr" style="<c:if test="${timeSchedule.repeat==0}">display:none;</c:if>">
						<td colspan="2" align="right"><label for="cost">Cost:</label></td>
						<td colspan="2"><input type="text" class="input" id="cost" name="timeSchedule.cost" size="10" value="${timeSchedule.cost}"/></td>
					</tr>
					<tr id="partr" style="<c:if test="${timeSchedule.repeat==0}">display:none;</c:if>">
						<td colspan="2" align="right"><label for="participate">Max participate:</label></td>
						<td colspan="2"><input type="text" class="input" id="participate" name="timeSchedule.maxParticipate" size="10" value="${timeSchedule.maxParticipate}"/></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">
				<br/>
				<input type="submit" value="Save" class="button" onclick="saveTimeSchedule('addTimeShedule','addTimeSchedule','tutorialDetailFragment','rightTutorialPanel');return false;"/>
				<input type="button" value="Cacel" class="button"/>
			</td>
		</tr>
	</table>
</div>
</form>

<!--start add tutorialForm-->