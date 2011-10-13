<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="index.title"/></title>
	<meta name="heading" content="<fmt:message key='index.heading'/>"/>
	<meta name="menu" content="Home"/>
</head>

<div class="login_bg floatr">
<div class="middle_right floatr">
	<div class="login-section" id="login">
	<form method="post" id="loginForm" action="<c:url value='/j_security_check'/>" onsubmit="saveUsername(this);return validateForm(this)">
	<h2><fmt:message key='label.login'/></h2>
	<ul>
	<c:if test="${param.error != null}">
		<li class="error">
			<img src="${ctx}/images/iconWarning.gif" alt="<fmt:message key='icon.warning'/>" class=""/>
			<fmt:message key="errors.password.mismatch"/>
			<%--${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}--%>
		</li>
	</c:if>
		<li>

			<fmt:message key="label.username"/>

			<input type="text" class="" name="j_username" id="j_username" tabindex="1"/>
		</li>
		<li>

			<fmt:message key="label.password"/>

			<input type="password" class="" name="j_password" id="j_password" tabindex="2"/>
		</li>
	<c:if test="${appConfig['rememberMeEnabled']}">
		<div>
			<input type="checkbox" class="" name="_spring_security_remember_me" id="rememberMe" tabindex="3"/>
			&nbsp;&nbsp;&nbsp;
			<label for="rememberMe" class=""><fmt:message key="login.rememberMe"/></labelan>
		</div>
	</c:if>
		<li>
			<s:submit key="button.login" cssClass="button" tabindex="4" />
		</li>
		<div>
				<fmt:message key="login.signup">
					<fmt:param><c:url value="/signup"/></fmt:param>
				</fmt:message>
		</div>

	</ul>
	</form>
	</div>
</div>
</div>

<div class="main-content floatl">
  <h1>Welcome to OuTute...</h1>
  <p>To be added....</p>
  <a href="#"><img src="images/read-more-button.gif" alt="" /></a>
</div>
<div class="left-col floatl">
  <div class="search-area floatl">
      <ul>
      <li><img src="images/search-icon.gif" alt="" /></li>
        <li style="line-height:22px;">Search</li>
        <li><input type="text" class="search-input" /></li>
        <li><input type="image" src="images/go-button.gif" /></li>
      </ul>
      <div class="clear"></div>
  </div>
  <div class="category-section">
    <h1>Category</h1>
    <ul>
      <li><a href="#">Languages</a></li>
      <li> <a href="#">Education & Study Overseas</a></li>
      <li> <a href="#">Games, Sports & Hobbies</a></li>
      <li><a href="#">Business & Career Consulting</a></li>
      <li><a href="#">Arts,Literature, Movies & Music</a></li>
      <li><a href="#">How-tos</a></li>
      <li><a href="#">Computer & Internet</a></li>
      <li><a href="#">Spirituality</a></li>
      <li><a href="#">Legal Issues</a></li>
      <li><a href="#">Finance, Insurance & Real Estate</a></li>
      <li><a href="#">Health & Living Style</a></li>
      <li><a href="#">Travel</a></li>
      <li><a href="#">Beauty & Fashion</a></li>
    </ul>
    <div class="clear"></div>
  </div>
  <div class="tutor-section">
    <h1>Tutor of the month...</h1>
    <img src="images/artist-img.gif"  alt="" class="floatl"/>
    <p>Sed ut perspiciatis unde omnislo iste natus errorvoluptatemsedlo accusantium doloremqueametk laudantiumtotam remamliopak aperiameaqueipsa</p>
    <div class="clear"></div>
  </div>
</div>
<div class="right-col floatr">


<div class="side"> 
  <div class="box"> 
    <div class="box_tt"> 
      <ul class="tabbox tabbox2 tabbox3 tabboxh2"> 
        <li id="Tab_shangsheng_0" onmouseover="switchTab('shangsheng',0,3,'on','');"><h2>111</h2></li> 
        <li id="Tab_shangsheng_1" onmouseover="switchTab('shangsheng',1,3,'on','');"><h2>222</h2></li> 
        <li id="Tab_shangsheng_2" onmouseover="switchTab('shangsheng',2,3,'on','');"><h2>333</h2></li> 
      </ul> 
    </div> 
    <div class="box_con" id="List_shangsheng_0"> 
      <div class="hotplate hotplate_112x96"> 
        <dl> 
          <dt ></dt> 
          <dt style="float:right;"></dt> 
          <dd></dd> 
          <dd></dd> 
          <dd></dd> 
        </dl> 
      </div> 
    </div> 
    <div class="box_con" id="List_shangsheng_1" style="display:none"> 
      <div class="hotplate hotplate_112x96"> 
        <dl>
        <dt ></dt> 
        <dt style="float:right;"></dt> 
        <dd></dd> 
        <dd></dd> 
        <dd></dd> 
        </dl> 
      </div> 
    </div> 
    <div class="box_con" id="List_shangsheng_2" style="display:none"> 
      <div class="hotplate hotplate_112x96"> 
        <dl> 
        <dt ></dt> 
        <dt style="float:right;"></dt> 
        <dd></dd> 
        <dd></dd> 
        <dd></dd> 
        </dl> 
      </div> 
    </div> 
  </div>
</div>
</div>