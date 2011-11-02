<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start add tutorialForm-->
<div>
	<form id="searchTutorial" action="listForSearchTutorial" method="post" class="searchContent" onsubmit="return false;">
		<table>
			<tr><th colspan="4"><fmt:message key="page.takeTutorial.SearchTutorial"/></th></tr>
			<tr><td><fmt:message key="page.takeTutorial.ID"/></td><td><input type="text" name="id" class="inputMin w100px;"/></td></tr>
			<tr>
				<td colspan="5">
					<div style="clear: both; position: relative; display: inline;"><hr style="margin: 10px 0px; clear: both;"/><div style="position:absolute;float: right; left: 200px; top:0px; background-color: white; padding: 0px 10px; display: block;"><fmt:message key="page.takeTutorial.OR"/></div></div>
				</td>
			</tr>
			<tr>
				<td><fmt:message key="page.takeTutorial.Name"/></td><td><input type="text" name="search.name" class="inputMin w100px"/></td>
				<td><fmt:message key="page.takeTutorial.Category"/></td><td>
					<select name="search.category" class="select w100px">
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
				<td><fmt:message key="page.takeTutorial.Start"/></td><td><input type="text" id="search_start" name="search.start" class="inputMin w100px"/></td>
				<td><fmt:message key="page.takeTutorial.End"/></td><td><input type="text" id="search_end" name="search.end" class="inputMin w100px"/></td>
			</tr>
			<tr>
				<td><fmt:message key="page.takeTutorial.TutorName"/></td><td><input type="text" name="search.tutorName" class="inputMin w200px"/></td>
				<td></td><td></td>
				<td><input type="submit" value="<fmt:message key="page.button.search"/>" class="button buttonMin" onclick="clickSearch('listForSearchTutorial','searchTutorial','listForSearchTutorial');return false;"/></td>
			</tr>
		</table>
	</form>
	<div class="listTutorial4Select" style="width: 100%; background-color: white; overflow-y: scroll; height: 280px;">
		<table class="outertable">
			<tr>
				<th><fmt:message key="page.takeTutorial.TutorialSearchResult"/></th>
				<td><select><option><fmt:message key="page.takeTutorial.SortBy"/></option></select></td>
			</tr>
		</table>
		<table class="outertable" id="listForSearchTutorial">
			<jsp:include page="/WEB-INF/pages/takeTutorial/listForSearchTutorial.jsp"></jsp:include>
		</table>
	</div>
</div>

<!--start add tutorialForm-->