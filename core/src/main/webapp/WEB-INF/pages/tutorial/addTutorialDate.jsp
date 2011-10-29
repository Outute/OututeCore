<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<fmt:setLocale value="en_US"/>
<!--start add tutorialForm-->
<div script="Util.calender('mycalendar','tutorialDate','<fmt:formatDate value="${tutorialSchedule.startDate}" pattern="yyyyMMdd"/>');" style="display: none;"></div>
<form id="addTutorialSchedule" action="addTimeShedule" method="post" onsubmit="saveTutorialSchedule('addTimeShedule','addTutorialSchedule','tutorialDetailFragment','rightTutorialPanel');return false;">
<input type="hidden" name="tutorial.id" value="${tutorial.id}"/>
<input type="hidden" name="tutorialSchedule.id" value="${tutorialSchedule.id}"/>
<input type="hidden" name="tutorialSchedule.startDate" id="tutorialDate" value=""/>
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
							<input type="text" class="input" id="form" name="fromTime" size="10" value="<fmt:formatDate value="${tutorialSchedule.fromTime}" pattern="hh:mmaaa"/>"/>
							<div id="fromam" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">am</div></div>
							<div id="frompm" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">pm</div></div>
						</td>
						<td><label for="to">To:</label></td>
						<td>
							<input type="text" class="input" id="tc" name="toTime" size="10" value="<fmt:formatDate value="${tutorialSchedule.toTime}" pattern="hh:mmaaa"/>"/>
							<div id="toam" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">am</div></div>
							<div id="topm" style="position: relative;float:right;display: none;"><div style="position: absolute;left: -30px; margin: 5px;">pm</div></div>
						</td>
					</tr>
					<tr>
						<td><label for="repeat">Repeats:</label></td>
						<td colspan="3"><select id="repeat" name="tutorialSchedule.durationType" class="select w200px" onchange="this.value=='0'?Util.hideId(['endstr','costtr','partr']):Util.showId(['endstr','costtr','partr']);">
								<option value="0" <c:if test="${tutorialSchedule.durationType==0}">selected="selected"</c:if>>No Repeat</option>
								<option value="1" <c:if test="${tutorialSchedule.durationType==1}">selected="selected"</c:if>>Weekly</option>
							</select>
						</td>
					</tr>
					<tr id="endstr" style="<c:if test="${tutorialSchedule==null||tutorialSchedule.durationType==0}">display:none;</c:if>">
						<td valign="top"><label>Ends:</label></td>
						<td colspan="3">
							<input type="radio" class="input" id="ends1" name="ends" value="0" <c:if test="${tutorialSchedule.endsOccurrence>0}">checked="checked"</c:if> onclick="if(this.checked){Util.id('occurrences').value='';}"/><label for="ends1">Never</label><br/>
							<input type="radio" class="input" id="ends2" name="ends" value="1" <c:if test="${tutorialSchedule.endsOccurrence>0}">checked="checked"</c:if>/><label for="ends2">After</label>
							<input type="text" class="input" id="occurrences" name="tutorialSchedule.endsOccurrence" size=10 value="${tutorialSchedule.endsOccurrence}" onclick="Util.id('ends2').click();"/><label for="occurrences">occurrences</label>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<hr style="color: #CCC; margin: 0px 0px 20px 0px;"/>
						</td>
					</tr>
					<tr id="costtr" style="<c:if test="${tutorialSchedule==null||tutorialSchedule.durationType==0}">display:none;</c:if>">
						<td colspan="2" align="right"><label for="cost">Cost:</label></td>
						<td colspan="2"><input type="text" class="input" id="cost" name="tutorialSchedule.cost" size="10" value="${tutorialSchedule.cost}"/></td>
					</tr>
					<tr id="partr" style="<c:if test="${tutorialSchedule==null||tutorialSchedule.durationType==0}">display:none;</c:if>">
						<td colspan="2" align="right"><label for="participate">Max participate:</label></td>
						<td colspan="2"><input type="text" class="input" id="participate" name="tutorialSchedule.maxParticipate" size="10" value="${tutorialSchedule.maxParticipate}"/></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="right">
				<br/>
				<input type="submit" value="Save" class="button" onclick="saveTutorialSchedule('addTimeShedule','addTutorialSchedule','tutorialDetailFragment','rightTutorialPanel');return false;"/>
				<input type="button" value="Cacel" class="button"/>
			</td>
		</tr>
	</table>
</div>
</form>

<!--start add tutorialForm-->