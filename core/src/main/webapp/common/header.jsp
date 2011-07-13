<!--start header-->
<%@ include file="/common/taglibs.jsp"%>

  <div class="content-box">
    <div class="top-box">
    <div class="top-left floatl">
      <a href="index.html" class="logo">OuTute</a>
   </div>
   <div class="top-right floatl">
      <c:if test="${pageContext.request.locale.language ne 'en'}">
        <div id="switchLocale"><a href="<c:url value='/?locale=en'/>"><fmt:message key="webapp.name"/>in English</a></div>
      </c:if>
    </div>
    <div class="top-right floatr">
      <!-- AddThis Button BEGIN -->
      <div class="addthis_toolbox addthis_default_style ">
        <a href="http://www.addthis.com/bookmark.php?v=250&amp;pubid=ra-4deee9d86173325e" class="addthis_button_compact STYLE1"> Share OuTute </a>
        <span class="addthis_separator">|</span>
        <a class="addthis_button_preferred_1"></a>
        <a class="addthis_button_preferred_2"></a>
        <a class="addthis_button_preferred_3"></a>
        <a class="addthis_button_preferred_4"></a>
      </div>
      <script type="text/javascript">var addthis_config = {"data_track_clickback":true};</script>
      <script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-4deee9d86173325e"></script>
      <!-- AddThis Button END -->
    </div>

    <div class="top_right_lower floatr">
      <div class="main-links">
        <ul>
        <li><a href="index.html" class="active"><span>Home</span></a></li>
        <li><a href="about-us.html"><span>About Us</span></a></li>
        <li><a href="#"><span>Gallery</span></a></li>
        <li><a href="#"><span>Blog</span></a></li>
        <li><a href="#"><span>Events</span></a></li>
        <li><a href="#"><span>Register</span></a></li>
        <li><a href="contact-us.html"><span>Contact Us</span></a></li>
        </ul>
        <div class="clear"></div>
      </div>
    </div>
  </div>
<%-- Put constants into request scope --%>
<appfuse:constants scope="request"/>
<!--end header-->