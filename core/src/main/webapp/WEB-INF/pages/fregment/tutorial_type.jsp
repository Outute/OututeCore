<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<c:if test="${tutorial_showtype==0}">
<c:if test="${tutorial_type==0}"><fmt:message key="page.offerTutorial.Class"/></c:if>
<c:if test="${tutorial_type==1}"><fmt:message key="page.offerTutorial.Workshop"/></c:if>
</c:if>