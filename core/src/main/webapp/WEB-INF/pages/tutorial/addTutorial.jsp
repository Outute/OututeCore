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
			  		<c:set var="tutorial_category" scope="request" value="${tutorial.category}"/>
			  		<c:set var="category_type" scope="request" value="1"/>
			  		<jsp:include page="/WEB-INF/pages/fregment/tutorial_category.jsp"/>
			  	</select>
			  </td>
			</tr>
			<tr>
			  <td><label><fmt:message key="page.offerTutorial.TeachingType"/></label></td>
			  <td>
			  	<input type="radio" class="input" id="tutorial_type_0" name="tutorial.type" value="0" onclick="this.checked && Util.hideId(['workshopPC','partitr','costtr']).showId('tutorialPC');" <c:if test="${tutorial==null||tutorial.type==0}">checked="checked"</c:if>>
			  	<label for="tutorial_type_0"><fmt:message key="page.offerTutorial.Class"/></label>
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