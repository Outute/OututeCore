<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" permissions="rolesAdapter">
	<ul id="primary-nav" class="main-links">
		<c:if test="${empty pageContext.request.remoteUser}"><li><a href="<c:url value="/login"/>" class="active"><span><fmt:message key="index.title"/></span></a></li></c:if>
		<menu:displayMenu name="MainMenu"/>
		<menu:displayMenu name="TutorialMenu"/>
		<menu:displayMenu name="UserMenu"/>
		<menu:displayMenu name="AdminMenu"/>
		<menu:displayMenu name="Logout"/>
	</ul>
</menu:useMenuDisplayer>