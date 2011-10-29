<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start add tutorialForm-->
<form id="addTutorial" action="addTutorial" method="post" class="formContent" onsubmit="saveTutorial('addTutorial','addTutorial','listTutorialFregmaent','tutorialListTable');return false;">
<input type="hidden" name="tutorial.id" value="${tutorial.id}"/>
<div class="padding10">
	<div class="addTutorialTitle">Add a Tutorial or Workshop</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
			  <td><label for="tutorial_name">Workshop Name:<span class="form-required">*</span></label></td>
			  <td>
			  <input type="text" id="tutorial_name" name="tutorial.name" maxlength="32" size="30" class="input w300px" value="${tutorial.name}"></td>
			</tr>
			<tr>
			  <td><label for="tutorial_description">Description:</label></td>
			  <td><textarea id="tutorial_description" name="tutorial.description" rows="4" cols="30" class="textarea w300px">${tutorial.description}</textarea></td>
			</tr>
			<tr>
			  <td><label for="tutorial_category">Category:</label></td>
			  <td>
			  	<select id="tutorial_category" name="tutorial.category" class="select w200px">
			  		<option value="1" <c:if test="${tutorial.category==1}"> elected="selected"</c:if>>Languages</option>
					<option value="2" <c:if test="${tutorial.category==2}">selected="selected"</c:if>>Study Oversea</option>
					<option value="3" <c:if test="${tutorial.category==3}">selected="selected"</c:if>>Academic Studies</option>
					<option value="4" <c:if test="${tutorial.category==4}">selected="selected"</c:if>>Games Sports and Leisure</option>
					<option value="5" <c:if test="${tutorial.category==5}">selected="selected"</c:if>>Business</option>
					<option value="6" <c:if test="${tutorial.category==6}">selected="selected"</c:if>>Career Consultation and Development</option>
					<option value="7" <c:if test="${tutorial.category==7}">selected="selected"</c:if>>Arts and Literature</option>
					<option value="8" <c:if test="${tutorial.category==8}">selected="selected"</c:if>>How to for Dummies</option>
					<option value="9" <c:if test="${tutorial.category==9}">selected="selected"</c:if>>Computer and Internet</option>
					<option value="10" <c:if test="${tutorial.category==10}">selected="selected"</c:if>>Legal Issues</option>
					<option value="11" <c:if test="${tutorial.category==11}">selected="selected"</c:if>>Finance Insurance and Real Estate</option>
					<option value="12" <c:if test="${tutorial.category==12}">selected="selected"</c:if>>Health and Living Style</option>
					<option value="13" <c:if test="${tutorial.category==13}">selected="selected"</c:if>>Travel</option>
					<option value="14" <c:if test="${tutorial.category==14}">selected="selected"</c:if>>Beauty and Fashion</option>
					<option value="0" <c:if test="${tutorial.category==0}">selected="selected"</c:if>>Others</option>
			  	</select>
			  </td>
			</tr>
			<tr>
			  <td><label>Teaching Type:</label></td>
			  <td>
			  	<input type="radio" class="input" id="tutorial_type_0" name="tutorial.type" value="0" onclick="this.checked && Util.hideId(['workshopPC','partitr','costtr']).showId('tutorialPC');" <c:if test="${tutorial==null||tutorial.type==0}">checked="checked"</c:if>>
			  	<label for="tutorial_type_0">Tutorial</label>
			  	<input type="radio" class="input" id="tutorial_type_1" name="tutorial.type" value="1" onclick="this.checked && Util.hideId('tutorialPC').showId(['workshopPC','partitr','costtr']);" <c:if test="${tutorial.type==1}">checked="checked"</c:if>>
			  	<label for="tutorial_type_1">Workshop(Client must enroll in all sessions)</label>
			  </td>
			</tr>
			<tr>
			  <td colspan="2">
			  	<span>Max Participates & Cost:</span>
			  	<span id="tutorialPC" style="<c:if test="${tutorial.type==1}">display:none;</c:if>">will be defined at individual sessions</span>
			  	<span id="workshopPC" style="<c:if test="${tutorial==null||tutorial.type==0}">display:none;</c:if>">will be defined here</span>
			  </td>
			</tr>
			<tr id="partitr" style="<c:if test="${tutorial==null||tutorial.type==0}">display:none;</c:if>">
			  <td><label for="participate">Max Participates:</label></td>
			  <td><input type="text" class="input" id="participate" name="participate" size="10" value=""/></td>
			</tr>
			<tr id="costtr" style="<c:if test="${tutorial==null||tutorial.type==0}">display:none;</c:if>">
			  <td><label for="tutorial_cost">Cost:</label></td>
			  <td><input type="text" class="input" id="tutorial_cost" name="tutorial.cost" size="10" value="${tutorial.cost}"/></td>
			</tr>
			<tr align="right">
			  <td colspan="2">
			  	<input type="button" name="save" value="save" class="button" onclick="saveTutorial('addTutorial','addTutorial','listTutorialFregmaent','tutorialListTable');return false;"/>
			  	<input type="button" name="cancel" value="cancel" class="button"/>
			  </td>
			</tr>
		</tbody>
	</table>
</div>
</form>

<!--start add tutorialForm-->