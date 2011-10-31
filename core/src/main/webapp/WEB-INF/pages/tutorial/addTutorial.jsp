<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start add tutorialForm-->
<form id="addTutorial" action="addTutorial" method="post" class="formContent" onsubmit="saveTutorial('addTutorial','listTutorialFragment','addTutorial','tutorialListTable');return false;">
<input type="hidden" name="tutorial.id" value="${tutorial.id}"/>
<div class="padding10">
	<div class="addTutorialTitle"><fmt:message key="page.offerTutorial.addTutorialTitle"/></div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
			  <td><label for="tutorial_name"><fmt:message key="page.offerTutorial.workshopName"/><span class="form-required">*</span></label></td>
			  <td>
			  <input type="text" id="tutorial_name" name="tutorial.name" maxlength="32" size="30" class="input w300px" value="${tutorial.name}"></td>
			</tr>
			<tr>
			  <td><label for="tutorial_description"><fmt:message key="page.offerTutorial.description"/></label></td>
			  <td><textarea id="tutorial_description" name="tutorial.description" rows="4" cols="30" class="textarea w300px">${tutorial.description}</textarea></td>
			</tr>
			<tr>
			  <td><label for="tutorial_category"><fmt:message key="page.offerTutorial.category"/></label></td>
			  <td>
			  	<select id="tutorial_category" name="tutorial.category" class="select w200px">
			  		<option value="1" <c:if test="${tutorial.category==1}"> elected="selected"</c:if>><fmt:message key="page.offerTutorial.Languages"/></option>
					<option value="2" <c:if test="${tutorial.category==2}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.StudyOversea"/></option>
					<option value="3" <c:if test="${tutorial.category==3}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.AcademicStudies"/></option>
					<option value="4" <c:if test="${tutorial.category==4}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.GamesSportsAndLeisure"/></option>
					<option value="5" <c:if test="${tutorial.category==5}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.Business"/></option>
					<option value="6" <c:if test="${tutorial.category==6}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.CareerConsultationAndDevelopment"/></option>
					<option value="7" <c:if test="${tutorial.category==7}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.ArtsAndLiterature"/></option>
					<option value="8" <c:if test="${tutorial.category==8}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.HowToForDummies"/></option>
					<option value="9" <c:if test="${tutorial.category==9}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.ComputerAndInternet"/></option>
					<option value="10" <c:if test="${tutorial.category==10}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.LegalIssues"/></option>
					<option value="11" <c:if test="${tutorial.category==11}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.FinanceInsuranceAndRealEstate"/></option>
					<option value="12" <c:if test="${tutorial.category==12}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.HealthAndLivingStyle"/></option>
					<option value="13" <c:if test="${tutorial.category==13}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.Travel"/></option>
					<option value="14" <c:if test="${tutorial.category==14}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.BeautyAndFashion"/></option>
					<option value="0" <c:if test="${tutorial.category==0}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.Others"/></option>
			  	</select>
			  </td>
			</tr>
			<tr>
			  <td><label><fmt:message key="page.offerTutorial.TeachingType"/></label></td>
			  <td>
			  	<input type="radio" class="input" id="tutorial_type_0" name="tutorial.type" value="0" onclick="this.checked && Util.hideId(['workshopPC','partitr','costtr']).showId('tutorialPC');" <c:if test="${tutorial==null||tutorial.type==0}">checked="checked"</c:if>>
			  	<label for="tutorial_type_0"><fmt:message key="page.offerTutorial.Tutorial"/></label>
			  	<input type="radio" class="input" id="tutorial_type_1" name="tutorial.type" value="1" onclick="this.checked && Util.hideId('tutorialPC').showId(['workshopPC','partitr','costtr']);" <c:if test="${tutorial.type==1}">checked="checked"</c:if>>
			  	<label for="tutorial_type_1"><fmt:message key="page.offerTutorial.Workshop"/></label>
			  </td>
			</tr>
			<tr>
			  <td colspan="2">
			  	<span><fmt:message key="page.offerTutorial.MaxParticipatesAndCost"/></span>
			  	<span id="tutorialPC" style="<c:if test="${tutorial.type==1}">display:none;</c:if>"><fmt:message key="page.offerTutorial.definedIndividual"/></span>
			  	<span id="workshopPC" style="<c:if test="${tutorial==null||tutorial.type==0}">display:none;</c:if>"><fmt:message key="page.offerTutorial.definedHere"/></span>
			  </td>
			</tr>
			<tr id="partitr" style="<c:if test="${tutorial==null||tutorial.type==0}">display:none;</c:if>">
			  <td><label for="participate"><fmt:message key="page.offerTutorial.MaxParticipates"/></label></td>
			  <td><input type="text" class="input" id="tutorial_participate" name="tutorial.maxParticipate" size="10" value="${tutorial.maxParticipate}"/></td>
			</tr>
			<tr id="costtr" style="<c:if test="${tutorial==null||tutorial.type==0}">display:none;</c:if>">
			  <td><label for="tutorial_cost"><fmt:message key="page.offerTutorial.Cost"/></label></td>
			  <td><input type="text" class="input" id="tutorial_cost" name="tutorial.cost" size="10" value="${tutorial.cost}"/></td>
			</tr>
			<tr align="right">
			  <td colspan="2">
			  	<input type="button" name="save" value="<fmt:message key="page.button.save"/>" class="button" onclick="saveTutorial('addTutorial','listTutorialFragment','addTutorial','tutorialListTable');return false;"/>
			  	<input type="button" name="cancel" value="<fmt:message key="page.button.cancel"/>" class="button" onclick="try{Util.id('firstTutorial').click();}catch(err){}"/>
			  </td>
			</tr>
		</tbody>
	</table>
</div>
</form>

<!--start add tutorialForm-->