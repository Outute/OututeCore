<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<c:if test="${category_type==0}">
<c:if test="${tutorial_category==1}"><fmt:message key="page.offerTutorial.Languages"/></c:if>
<c:if test="${tutorial_category==2}"><fmt:message key="page.offerTutorial.AcademicStudies"/></c:if>
<c:if test="${tutorial_category==3}"><fmt:message key="page.offerTutorial.StudyOversea"/></c:if>
<c:if test="${tutorial_category==4}"><fmt:message key="page.offerTutorial.HowToForDummies"/></c:if>
<c:if test="${tutorial_category==5}"><fmt:message key="page.offerTutorial.ComputerAndInternet"/></c:if>
<c:if test="${tutorial_category==0}"><fmt:message key="page.offerTutorial.Others"/></c:if>
</c:if>
<c:if test="${category_type==1}">
<option value="1" <c:if test="${tutorial_category==1}"> elected="selected"</c:if>><fmt:message key="page.offerTutorial.Languages"/></option>
<option value="2" <c:if test="${tutorial_category==3}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.AcademicStudies"/></option>
<option value="3" <c:if test="${tutorial_category==2}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.StudyOversea"/></option>
<option value="4" <c:if test="${tutorial_category==8}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.HowToForDummies"/></option>
<option value="5" <c:if test="${tutorial_category==9}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.ComputerAndInternet"/></option>
<option value="0" <c:if test="${tutorial_category==0}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.Others"/></option>
</c:if>