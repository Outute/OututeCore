<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start add tutorialForm-->
<div>
	<form id="searchTutorial" action="listForSearchTutorial" method="post" class="searchContent" onsubmit="return false;">
		<table>
			<tr><th colspan="4">Search Tutorial</th></tr>
			<tr><td>ID:</td><td><input type="text" name="id" class="inputMin w100px;"/></td></tr>
			<tr>
				<td colspan="5">
					<div style="clear: both; position: relative; display: inline;"><hr style="margin: 10px 0px; clear: both;"/><div style="position:absolute;float: right; left: 200px; top:0px; background-color: white; padding: 0px 10px; display: block;">OR</div></div>
				</td>
			</tr>
			<tr><td>Name:</td><td><input type="text" name="search.name" class="inputMin w200px"/></td></tr>
			<tr>
				<td>Start:</td><td><input type="text" id="search_start" name="search.start" class="inputMin w100px"/></td>
				<td>End:</td><td><input type="text" id="search_end" name="search.end" class="inputMin w100px"/></td>
			</tr>
			<tr>
				<td>Tutor Name:</td><td><input type="text" name="search.tutorName" class="inputMin w200px"/></td>
				<td></td><td></td>
				<td><input type="submit" value="Search" class="button buttonMin" onclick="clickSearch('listForSearchTutorial','searchTutorial','listForSearchTutorial');return false;"/></td>
			</tr>
		</table>
	</form>
	<div class="listTutorial4Select" style="width: 100%; background-color: white; overflow-y: scroll; height: 280px;">
		<table class="outertable">
			<tr>
				<th>Tutorial Search Result:</th>
				<td><select><option>Sort by</option></select></td>
			</tr>
		</table>
		<table class="outertable" id="listForSearchTutorial">
			<jsp:include page="/WEB-INF/pages/takeTutorial/listForSearchTutorial.jsp"></jsp:include>
		</table>
	</div>
</div>

<!--start add tutorialForm-->