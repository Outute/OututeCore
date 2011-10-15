<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" permissions="rolesAdapter">
	<ul id="primary-nav">
		<c:if test="${empty pageContext.request.remoteUser}"><li><a href="<c:url value="/login"/>" class="active"><span><fmt:message key="index.title"/></span></a></li></c:if>
		<menu:displayMenu name="MainMenu"/>
		<menu:displayMenu name="TutorialMenu"/>
		<!--<menu:displayMenu name="UserMenu"/>
		<menu:displayMenu name="AdminMenu"/>
		--><menu:displayMenu name="MyOutute"/>
		<menu:displayMenu name="Logout"/>
		</ul>
		<div class="clear"></div>
		<div style="padding-top:6px; height:22px; font-size:11px" class="main-links">
			<ul>
				<li><a href="manageTutorials.html"><span>Outline Tutorials</span></a></li>
				<li><a class="active" href="createTutorial"><span>Offer Tutorials</span></a></li>
				<li><a href="registerTutorials.html"><span>Take Tutorials</span></a></li>
				<li><a href="#"><span>Edit Profile</span></a></li>
			</ul>
		</div>
		<div class="clear"></div>
</menu:useMenuDisplayer>