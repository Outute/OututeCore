<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--start add tutorialForm-->
<div>
	<form id="searchTutorial" action="listForSearchTutorial" method="post" class="searchContent" onsubmit="return false;">
		<table>
			<tr>
				<td><fmt:message key="page.takeTutorial.Category"/></td><td>
					<select name="search.category" class="select w200px font12px">
				  		<option value="" selected="selected"><fmt:message key="page.offerTutorial.ChooseACategory"/></option>
			  			<c:set var="category_type" scope="request" value="1"/>
			  			<jsp:include page="/WEB-INF/pages/fregment/tutorial_category.jsp"/>
				  	</select>
			  	</td>
                <td></td>
                <td><input type="submit" value="<fmt:message key="page.button.search"/>" class="button buttonMin" onclick="clickSearch('listForSearchTutorial','searchTutorial','listForSearchTutorial');return false;"/></td>
			</tr>
		</table>
	</form>
	<div class="listTutorial4Select" style="width: 100%; background-color: white; overflow-y: scroll; height: 200px;">
		<div id="listForSearchTutorial">
			<jsp:include page="/WEB-INF/pages/takeTutorial/listForSearchTutorial.jsp"/>
		</div>
	</div>
</div>

<!--start add tutorialForm-->