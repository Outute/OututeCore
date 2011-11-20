<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<c:if test="${category_type==0}">
<c:if test="${tutorial_category==1}"><fmt:message key="page.offerTutorial.Languages"/></c:if>
<c:if test="${tutorial_category==2}"><fmt:message key="page.offerTutorial.StudyOversea"/></c:if>
<c:if test="${tutorial_category==3}"><fmt:message key="page.offerTutorial.AcademicStudies"/></c:if>
<c:if test="${tutorial_category==4}"><fmt:message key="page.offerTutorial.GamesSportsAndLeisure"/></c:if>
<c:if test="${tutorial_category==5}"><fmt:message key="page.offerTutorial.Business"/></c:if>
<c:if test="${tutorial_category==6}"><fmt:message key="page.offerTutorial.CareerConsultationAndDevelopment"/></c:if>
<c:if test="${tutorial_category==7}"><fmt:message key="page.offerTutorial.ArtsAndLiterature"/></c:if>
<c:if test="${tutorial_category==8}"><fmt:message key="page.offerTutorial.HowToForDummies"/></c:if>
<c:if test="${tutorial_category==9}"><fmt:message key="page.offerTutorial.ComputerAndInternet"/></c:if>
<c:if test="${tutorial_category==10}"><fmt:message key="page.offerTutorial.LegalIssues"/></c:if>
<c:if test="${tutorial_category==11}"><fmt:message key="page.offerTutorial.FinanceInsuranceAndRealEstate"/></c:if>
<c:if test="${tutorial_category==12}"><fmt:message key="page.offerTutorial.HealthAndLivingStyle"/></c:if>
<c:if test="${tutorial_category==13}"><fmt:message key="page.offerTutorial.Travel"/></c:if>
<c:if test="${tutorial_category==14}"><fmt:message key="page.offerTutorial.BeautyAndFashion"/></c:if>
<c:if test="${tutorial_category==0}"><fmt:message key="page.offerTutorial.Others"/></c:if>
</c:if>
<c:if test="${category_type==1}">
<option value="1" <c:if test="${tutorial_category==1}"> elected="selected"</c:if>><fmt:message key="page.offerTutorial.Languages"/></option>
<option value="2" <c:if test="${tutorial_category==2}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.StudyOversea"/></option>
<option value="3" <c:if test="${tutorial_category==3}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.AcademicStudies"/></option>
<option value="4" <c:if test="${tutorial_category==4}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.GamesSportsAndLeisure"/></option>
<option value="5" <c:if test="${tutorial_category==5}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.Business"/></option>
<option value="6" <c:if test="${tutorial_category==6}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.CareerConsultationAndDevelopment"/></option>
<option value="7" <c:if test="${tutorial_category==7}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.ArtsAndLiterature"/></option>
<option value="8" <c:if test="${tutorial_category==8}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.HowToForDummies"/></option>
<option value="9" <c:if test="${tutorial_category==9}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.ComputerAndInternet"/></option>
<option value="10" <c:if test="${tutorial_category==10}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.LegalIssues"/></option>
<option value="11" <c:if test="${tutorial_category==11}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.FinanceInsuranceAndRealEstate"/></option>
<option value="12" <c:if test="${tutorial_category==12}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.HealthAndLivingStyle"/></option>
<option value="13" <c:if test="${tutorial_category==13}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.Travel"/></option>
<option value="14" <c:if test="${tutorial_category==14}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.BeautyAndFashion"/></option>
<option value="0" <c:if test="${tutorial_category==0}">selected="selected"</c:if>><fmt:message key="page.offerTutorial.Others"/></option>
</c:if>