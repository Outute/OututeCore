<!--start index-->
<%@ include file="/common/taglibs.jsp"%>

<head>
	<title><fmt:message key="index.title"/></title>
	<meta name="heading" content="<fmt:message key='index.heading'/>"/>
	<meta name="menu" content="Home"/>
</head>

<div class="login_bg floatr">
<div class="middle_right floatr">
	<div class="">
	<ul>
		<li>
			<fmt:message key="mainMenu.welcome"/>
		</li>
		<li>
			<fmt:message key="mainMenu.tutorialList">
				<fmt:param><c:url value="/listTutorial"/></fmt:param>
			</fmt:message>
		</li>
		<li>
			<fmt:message key="mainMenu.newTutorial">
				<fmt:param><c:url value="/createTutorial"/></fmt:param>
			</fmt:message>
		</li>
		<li>
		<fmt:message key="mainMenu.editProfile">
			<fmt:param><c:url value="/editProfile"/></fmt:param>
		</fmt:message>
	</li>
	</ul>
	</div>
</div>
</div>

<div class="main-content floatl">
  <h1>Welcome to OuTute...</h1>
  <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin rutrum nisi ut ligula laoreet pellentesque. Integer lacinia nunc id est ornare eu pellentesque sem consecteturonec ac urna auctor lectus fringilla hendrerit at eget duiivamus sit amet sem tortor.</p>
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
<!-- end -->
