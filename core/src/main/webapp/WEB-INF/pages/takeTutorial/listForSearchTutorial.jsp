<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!--list searched tutorials-->
<c:forEach items="${tutorials}" var="t">
	<tr>
		<td colspan="2">
			<table class=innertable>
				<tr>
					<td class="iconTd"></td>
			  		<td>
			  			<div class="title">
			  				<div class="floatl tutorialName">${t.name}</div><div class="floatl tutor overhide">Instructor:<c:forEach items="${t.tutors}" var="tu" varStatus="stat">
			  						<c:if test="${stat.index>0}">,</c:if>&nbsp;${tu.firstName}
			  					</c:forEach><c:if test="${t.cost>0}">&nbsp;&nbsp;Max: ${t.cost} Cost:${t.cost}</c:if>
			  				</div>
			  			</div>
			  			<div class="content overhide">
			  				Description:${t.description}
			  			</div>
			  		</td>
			  		<td class="iconTextTd" nowrap="nowrap"><input type="button" value="View" onclick="viewTutorial('bookTutorialPage','${t.id}','rightTakeTutorialPanel','search_start','search_end');"/></td>
				</tr>
			</table>
		</td>
	</tr>
</c:forEach>
<!--end list searched tutorials-->