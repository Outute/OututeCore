<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" permissions="rolesAdapter">
	<div class="main-links">
	<ul>
		<c:if test="${empty pageContext.request.remoteUser}"><li><a href="<c:url value="/login"/>" class="active"><span><fmt:message key="index.title"/></span></a></li></c:if>
		<menu:displayMenu name="MainMenu"/>
		<menu:displayMenu name="AboutUs"/>
        <menu:displayMenu name="Categories"/>
        <menu:displayMenu name="ContactUs"/>
		<menu:displayMenu name="MyOutute"/>
		<menu:displayMenu name="Logout"/>
	</ul>
	</div>
	<div class="main2-links">
    <ul>
        <menu:displayMenu name="ManageTutorial"/>
        <menu:displayMenu name="CreateTutorial"/>
        <menu:displayMenu name="OfferTutorial"/>
        <menu:displayMenu name="UserMenu"/>
    </ul>
    </div>
</menu:useMenuDisplayer>