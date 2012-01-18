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
	<script type="text/javascript">
	    try{
	        document.write('<input type="hidden" name="time_zone" value="'+((new Date()).getTimezoneOffset()/60*(-1))+'"/>');
	    }catch(err){}
    </script>
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
		<li>
			<s:submit key="button.login" cssClass="button" tabindex="4" style="height:23px;"/>
		</li>
		<li>
			<div><fmt:message key="login.signup"><fmt:param><c:url value="/signup" /></fmt:param></fmt:message></div>
		</li>
</ul>
	</form>
	</div>
</div>
</div>

<div class="main-content floatl">
  <h1>Our Tutorials Our World Talent Pool</h1>
  <p>Outute understands the power of the world talent, services the passionated knowledge seeker, and provides entrepreneurship spirirts to individual or group of talents.</p>
  <p>More Info here</p>
  <p>More Info here</p>
  <a href="#"><img src="images/read-more-button.gif" alt="" /></a>
</div>
<div class="left-col floatl">
  <div class="category-section">
    <h1>Category</h1>
    <ul>
      <li><img src="images/language_small.jpg" width="25" height="25" /><a href="#">Languages</a></li>
      <li><img src="images/academic_small.jpg" width="25" height="25" /><a href="#">Academic Studies</a></li>
      <li><img src="images/oversea_small.jpg" width="25" height="25" /><a href="#">Study Oversea</a></li>
      <li><img src="images/dummy_small.jpg" width="25" height="25" /><a href="#">How to for Dummies</a></li>
      <li><img src="images/computer_small.jpg" width="25" height="25" /><a href="#">Computer and Internet</a></li>
      <li><img src="images/otherTutotial_small.jpg" width="25" height="25" /><a href="#">Other Categories</a></li>
    </ul>
    <div class="clear"></div>
  </div>
  <div class="tutor-section">
    <h1>Tutor of the month...</h1>
    <img src="images/artist-img.gif"  alt="" class="floatl"/>
    <p>Here is tutor of the month profile information</p>
    <div class="clear"></div>
  </div>
</div>
<div class="right-col floatr">
	<div class="side"> 
		<div class="box"> 
			<div class="box_tt"> 
				<ul class="tabbox tabbox2 tabbox3 tabboxh2"> 
					<li id="Tab_shangsheng_0" onmouseover="switchTab('shangsheng',0,3,'on','');"><h2>Top Tutorial</h2></li> 
					<li id="Tab_shangsheng_1" onmouseover="switchTab('shangsheng',1,3,'on','');"><h2>New Tutorial</h2></li> 
					<li id="Tab_shangsheng_2" onmouseover="switchTab('shangsheng',2,3,'on','');"><h2>New Tutor</h2></li> 
				</ul> 
			</div> 
			<div class="box_con" id="List_shangsheng_0"> 
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/canadaTute.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutorial: </td>
										<td>Get to know Canada</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>Introduce you to Canada, I will include: life in Canada, work in Canada and share some stories in Canada.</td> 
									</tr>
									<tr align='top'>
										<td>Tutor: </td>
										<td>Andrew Martin</td> 
									</tr>
									<tr align='top'>
										<td>Category: </td>
										<td>Study Overseas</td>
									</tr>
									<tr align='top'>
										<td>Price: </td>
										<td>$15</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table>
				</div> 
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/canadaTute.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutorial: </td>
										<td>Get to know Canada</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>Introduce you to Canada, I will include: life in Canada, work in Canada and share some stories in Canada.</td> 
									</tr>
									<tr align='top'>
										<td>Tutor: </td>
										<td>Andrew Martin</td> 
									</tr>
									<tr align='top'>
										<td>Category: </td>
										<td>Study Overseas</td>
									</tr>
									<tr align='top'>
										<td>Price: </td>
										<td>$15</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table> 
				</div>
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/canadaTute.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutorial: </td>
										<td>Get to know Canada</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>Introduce you to Canada, I will include: life in Canada, work in Canada and share some stories in Canada.</td> 
									</tr>
									<tr align='top'>
										<td>Tutor: </td>
										<td>Andrew Martin</td> 
									</tr>
									<tr align='top'>
										<td>Category: </td>
										<td>Study Overseas</td>
									</tr>
									<tr align='top'>
										<td>Price: </td>
										<td>$15</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table>
				</div> 
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/canadaTute.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutorial: </td>
										<td>Get to know Canada</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>Introduce you to Canada, I will include: life in Canada, work in Canada and share some stories in Canada.</td> 
									</tr>
									<tr align='top'>
										<td>Tutor: </td>
										<td>Andrew Martin</td> 
									</tr>
									<tr align='top'>
										<td>Category: </td>
										<td>Study Overseas</td>
									</tr>
									<tr align='top'>
										<td>Price: </td>
										<td>$15</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table> 
				</div>
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/canadaTute.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutorial: </td>
										<td>Get to know Canada</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>Introduce you to Canada, I will include: life in Canada, work in Canada and share some stories in Canada.</td> 
									</tr>
									<tr align='top'>
										<td>Tutor: </td>
										<td>Andrew Martin</td> 
									</tr>
									<tr align='top'>
										<td>Category: </td>
										<td>Study Overseas</td>
									</tr>
									<tr align='top'>
										<td>Price: </td>
										<td>$15</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table>
				</div> 
			</div> 
			<div class="box_con" id="List_shangsheng_1" style="display:none"> 
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/canadaTute.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutorial: </td>
										<td>Get to know Canada</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>Introduce you to Canada, I will include: life in Canada, work in Canada and share some stories in Canada.</td> 
									</tr>
									<tr align='top'>
										<td>Tutor: </td>
										<td>Reid Chen</td> 
									</tr>
									<tr align='top'>
										<td>Category: </td>
										<td>Study Overseas</td>
									</tr>
									<tr align='top'>
										<td>Price: </td>
										<td>$30</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table> 
				</div>
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/canadaTute.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutorial: </td>
										<td>Get to know Canada</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>Introduce you to Canada, I will include: life in Canada, work in Canada and share some stories in Canada.</td> 
									</tr>
									<tr align='top'>
										<td>Tutor: </td>
										<td>Richard Peach</td> 
									</tr>
									<tr align='top'>
										<td>Category: </td>
										<td>Study Overseas</td>
									</tr>
									<tr align='top'>
										<td>Price: </td>
										<td>$18</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table> 
				</div>
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/canadaTute.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutorial: </td>
										<td>Get to know Canada</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>Introduce you to Canada, I will include: life in Canada, work in Canada and share some stories in Canada.</td> 
									</tr>
									<tr align='top'>
										<td>Tutor: </td>
										<td>Mark Martin</td> 
									</tr>
									<tr align='top'>
										<td>Category: </td>
										<td>Study Overseas</td>
									</tr>
									<tr align='top'>
										<td>Price: </td>
										<td>$19</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table> 
				</div>
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/canadaTute.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutorial: </td>
										<td>Get to know Canada</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>Introduce you to Canada, I will include: life in Canada, work in Canada and share some stories in Canada.</td> 
									</tr>
									<tr align='top'>
										<td>Tutor: </td>
										<td>Tim Wong</td> 
									</tr>
									<tr align='top'>
										<td>Category: </td>
										<td>Study Overseas</td>
									</tr>
									<tr align='top'>
										<td>Price: </td>
										<td>$22</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table> 
				</div>
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/canadaTute.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutorial: </td>
										<td>Get to know Canada</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>Introduce you to Canada, I will include: life in Canada, work in Canada and share some stories in Canada.</td> 
									</tr>
									<tr align='top'>
										<td>Tutor: </td>
										<td>Brenda Liu</td> 
									</tr>
									<tr align='top'>
										<td>Category: </td>
										<td>Study Overseas</td>
									</tr>
									<tr align='top'>
										<td>Price: </td>
										<td>$20</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table> 
				</div> 
			</div> 
			<div class="box_con" id="List_shangsheng_2" style="display:none"> 
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/User_M.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutor Name:</td>
										<td>Bean</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>I am a professor in University of Toronto, I would love to share some information with student overseas.</td> 
									</tr>
									<tr align='top'>
										<td>Specialty: </td>
										<td>Computer Software</td> 
									</tr>
									<tr align='top'>
										<td>Country: </td>
										<td>Canada</td>
									</tr>
									<tr align='top'>
										<td>Occupation: </td>
										<td>Professor</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table> 
				</div>
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/User_F.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutor Name:</td>
										<td>Bean</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>I am a professor in University of Toronto, I would love to share some information with student overseas.</td> 
									</tr>
									<tr align='top'>
										<td>Specialty: </td>
										<td>Computer Software</td> 
									</tr>
									<tr align='top'>
										<td>Country: </td>
										<td>Canada</td>
									</tr>
									<tr align='top'>
										<td>Occupation: </td>
										<td>Professor</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table> 
				</div>
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/User_M.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutor Name:</td>
										<td>Bean</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>I am a professor in University of Toronto, I would love to share some information with student overseas.</td> 
									</tr>
									<tr align='top'>
										<td>Specialty: </td>
										<td>Computer Software</td> 
									</tr>
									<tr align='top'>
										<td>Country: </td>
										<td>Canada</td>
									</tr>
									<tr align='top'>
										<td>Occupation: </td>
										<td>Professor</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table> 
				</div>
				<div class="hotplate hotplate_112x96"> 
					<table border='0'>
						<tr>
							<td>
								<img src='../../images/dummyData/User_F.jpg'>
							</td>
							<td>
								<table>
									<tr align='top'> 
										<td width='80'>Tutor Name:</td>
										<td>Bean</td>
									</tr>
									<tr valign='top'> 
										<td>Description: </td>
										<td>I am a professor in University of Toronto, I would love to share some information with student overseas.</td> 
									</tr>
									<tr align='top'>
										<td>Specialty: </td>
										<td>Computer Software</td> 
									</tr>
									<tr align='top'>
										<td>Country: </td>
										<td>Canada</td>
									</tr>
									<tr align='top'>
										<td>Occupation: </td>
										<td>Professor</td>
									</tr>
									<tr>
										<td></td>
										<td align='right'><a href="#"><img src="images/read-more-button.gif" alt="" /></a></td>
									</tr>
								</table> 
							</td>
						</tr>
					</table> 
				</div>
			</div> 
		</div> 
	</div>
</div>