<!--start menu-->
<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" permissions="rolesAdapter">
<div class="top_right_lower floatr">
	<div class="main-links">
		<ul>
		    <c:if test="${empty pageContext.request.remoteUser}"><li><a href="<c:url value="/login"/>" class="active"><fmt:message key="login.title"/></a></li></c:if>
		    <menu:displayMenu name="MainMenu"/>
		    <menu:displayMenu name="TutorialMenu"/>
		    <menu:displayMenu name="UserMenu"/>
		    <menu:displayMenu name="AdminMenu"/>
		    <menu:displayMenu name="Logout"/>
		    
						
						
		</ul>
		<div class="clear"></div>
	</div>
</div>
</menu:useMenuDisplayer>


<!--end menu-->