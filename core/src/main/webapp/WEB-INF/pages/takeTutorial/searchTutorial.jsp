<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start add tutorialForm-->
<div>
	<form id="searchTutorial" action="listForSearchTutorial" method="post" class="searchContent" onsubmit="return false;">
		<table>
			<tr><th colspan="4"><fmt:message key="page.takeTutorial.SearchTutorial"/></th></tr>
			<tr><td><fmt:message key="page.takeTutorial.ID"/></td><td><input type="text" name="id" class="inputMin w150px"/></td></tr>
			<tr>
				<td colspan="5">
					<div style="clear: both; position: relative; display: inline;"><hr style="margin: 10px 0px; clear: both;"/><div style="position:absolute;float: right; left: 200px; top:0px; background-color: white; padding: 0px 10px; display: block;"><fmt:message key="page.takeTutorial.OR"/></div></div>
				</td>
			</tr>
			<tr>
				<td><fmt:message key="page.takeTutorial.Name"/></td><td><input type="text" name="search.name" class="inputMin w150px"/></td>
				<td><fmt:message key="page.takeTutorial.Category"/></td><td>
					<select name="search.category" class="select w200px font12px">
				  		<option value="" selected="selected"><fmt:message key="page.offerTutorial.ChooseACategory"/></option>
			  			<c:set var="category_type" scope="request" value="1"/>
			  			<jsp:include page="/WEB-INF/pages/fregment/tutorial_category.jsp"/>
				  	</select>
			  	</td>
			</tr>
			<tr>
				<td><fmt:message key="page.takeTutorial.Start"/></td><td><input type="text" id="search_start" name="search.start" class="inputMin w150px"/></td>
				<td><fmt:message key="page.takeTutorial.End"/></td><td><input type="text" id="search_end" name="search.end" class="inputMin w150px"/></td>
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
		<div id="listForSearchTutorial">
			<jsp:include page="/WEB-INF/pages/takeTutorial/listForSearchTutorial.jsp"/>
		</div>
	</div>
</div>

<!--start add tutorialForm-->