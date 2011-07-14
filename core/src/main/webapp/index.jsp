<!--start index-->
<%@ include file="/common/taglibs.jsp"%>

<div class="login_bg floatr">
  <div class="login-section" id="login">
  <form method="post" id="loginForm" action="<c:url value='/j_security_check'/>"
      onsubmit="saveUsername(this);return validateForm(this)">
  <h2><fmt:message key='label.login'/></h2>
  <ul>
  <c:if test="${param.error != null}">
      <li class="error">
          <img src="${ctx}/images/iconWarning.gif" alt="<fmt:message key='icon.warning'/>" class="icon"/>
          <fmt:message key="errors.password.mismatch"/>
          <%--${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}--%>
      </li>
  </c:if>
      <li>
         <label for="j_username" class="required desc">
              <fmt:message key="label.username"/> <span class="req">*</span>
          </label>
          <input type="text" class="text medium" name="j_username" id="j_username" tabindex="1" />
      </li>
      <li>
          <label for="j_password" class="required desc">
              <fmt:message key="label.password"/> <span class="req">*</span>
          </label>
          <input type="password" class="text medium" name="j_password" id="j_password" tabindex="2" />
      </li>
  <c:if test="${appConfig['rememberMeEnabled']}">
      <li>
          <input type="checkbox" name="_spring_security_remember_me" id="rememberMe" tabindex="3"/>
          <fmt:message key="login.rememberMe"/>
      </li>
  </c:if>
      <li>
          <input type="submit" class="button" name="login" value="<fmt:message key='button.login'/>" tabindex="4" />
            <p>
                <fmt:message key="login.signup">
                    <fmt:param><c:url value="/signup"/></fmt:param>
                </fmt:message>
            </p>
    </li>
  </ul>
  </form>
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


<!-- modified from xunlei --> 
<div class="side"> 
  <div class="box"> 
    <div class="box_tt"> 
      <ul class="tabbox tabbox2 tabbox3 tabboxh2"> 
        <li id="Tab_shangsheng_0" onmouseover="switchTab('shangsheng',0,3,'on','');"><h2>楂樻竻褰遍櫌</h2></li> 
        <li id="Tab_shangsheng_1" onmouseover="switchTab('shangsheng',1,3,'on','');"><h2>鐑挱澶х墖</h2></li> 
        <li id="Tab_shangsheng_2" onmouseover="switchTab('shangsheng',2,3,'on','');"><h2>缁忓吀宸ㄧ尞</h2></li> 
      </ul> 
    </div> 
    <script>CachePic["shangsheng"]=[]</script> 
    <div class="box_con" id="List_shangsheng_0"> 
      <div class="hotplate hotplate_112x96"> 
        <dl> 
          <dt ><a  href="http://kankan.xunlei.com/vod/mp4/58/58798.shtml" title="鑸掓穱鎯呰糠閮瘜鍩? target="_blank" blockid="3409"><img id="shangsheng_pic_0_0" src="http://images.movie.xunlei.com/gallery/643/e95a7221fb6bd3b1f86f1043fc766671.jpg" alt="鑸掓穱鎯呰糠閮瘜鍩? title="鑸掓穱鎯呰糠閮瘜鍩? /></a><a  href="http://kankan.xunlei.com/vod/mp4/58/58798.shtml" title="鑸掓穱鎯呰糠閮瘜鍩? class="hotplate_link" target="_blank" blockid="3409">鑸掓穱鎯呰糠閮瘜鍩?/a></dt> 
          <dt style="float:right;"><a  href="http://act.shop.xunlei.com/guanyunchang/index.html?fref=act_017_512 " title="瀛欎开寮曡鐢勫瓙涓? target="_blank" blockid="3409"><img id="shangsheng_pic_0_1" src="http://images.movie.xunlei.com/gallery/643/de77a9f56038d00c5afa572145efd39a.jpg" alt="瀛欎开寮曡鐢勫瓙涓? title="瀛欎开寮曡鐢勫瓙涓? /></a><a  href="http://act.shop.xunlei.com/guanyunchang/index.html?fref=act_017_512 " title="瀛欎开寮曡鐢勫瓙涓? class="hotplate_link" target="_blank" blockid="3409">瀛欎开寮曡鐢勫瓙涓?/a></dt> 
          <dd><a  href="http://kankan.xunlei.com/vod/mp4/34/34425.shtml" title="銆婅冻鐞冨挨鐗┿€? blockid="3409">銆婅冻鐞冨挨鐗┿€?/a>闈掓槬缇庡コ瓒崇悆鍠滃墽</dd> 
          <dd><a  href="http://kankan.xunlei.com/vod/mp4/34/34221.shtml" title="銆婂垁閿嬩笅鐨勭埍銆? blockid="3409">銆婂垁閿嬩笅鐨勭埍銆?/a>灏忕敯鍒囪涓绘紨鏃ユ湰蹇嶆湳澶х墖</dd> 
          <dd><a  href="http://kankan.xunlei.com/vod/mp4/34/34982.shtml" title="銆婇緳铏庨棬銆? blockid="3409">銆婇緳铏庨棬銆?/a>鐢勫瓙涓?浣欐枃涔?璋㈤渾閿?鐐叿鍔ㄤ綔</dd> 
        </dl> 
      </div> 
    </div> 
    <script> 
    CachePic["shangsheng"][0]=[
    "http://images.movie.xunlei.com/gallery/643/e95a7221fb6bd3b1f86f1043fc766671.jpg"
    ,"http://images.movie.xunlei.com/gallery/643/de77a9f56038d00c5afa572145efd39a.jpg"
    ,"http://images.movie.xunlei.com/gallery/643/7088d741b6c58eb6a825ad7c6b6c99b8.jpg"
    ,"http://images.movie.xunlei.com/gallery/643/31a88e2926f50444df9bc6245ceac74b.jpg"
    ,"http://images.movie.xunlei.com/gallery/643/e9dbebc3ae3f1a19a9a1e27c0c58af9e.jpg"
    ]
    </script> 
    <div class="box_con" id="List_shangsheng_1" style="display:none"> 
      <div class="hotplate hotplate_112x96"> 
        <dl> 
          <dt ><a  href="http://kankan.xunlei.com/vod/mp4/60/60794.shtml" title="鎯呮浼︾悊瓒呭嚭鎯宠薄" target="_blank" blockid="3410"><img id="shangsheng_pic_1_0" src="http://images.movie.xunlei.com/gallery/643/afbfdaf55c679489c53371786b69ba18.jpg" alt="鎯呮浼︾悊瓒呭嚭鎯宠薄" title="鎯呮浼︾悊瓒呭嚭鎯宠薄" /></a><a  href="http://kankan.xunlei.com/vod/mp4/60/60794.shtml" title="鎯呮浼︾悊瓒呭嚭鎯宠薄" class="hotplate_link" target="_blank" blockid="3410">鎯呮浼︾悊瓒呭嚭鎯宠薄</a></dt> 
          <dt style="float:right;"><a  href="http://kankan.xunlei.com/vod/mp4/40/40112.shtml" title="澶濠氬墠澶у昂搴﹀簥鎴? target="_blank" blockid="3410"><img id="shangsheng_pic_1_1" src="http://images.movie.xunlei.com/gallery/643/4aceb59d05235b412e080886f23dfcee.jpg" alt="澶濠氬墠澶у昂搴﹀簥鎴? title="澶濠氬墠澶у昂搴﹀簥鎴? /></a><a  href="http://kankan.xunlei.com/vod/mp4/40/40112.shtml" title="澶濠氬墠澶у昂搴﹀簥鎴? class="hotplate_link" target="_blank" blockid="3410">澶濠氬墠澶у昂搴﹀簥鎴?/a></dt> 
          <dd><a  href="http://kankan.xunlei.com/vod/mp4/24/24505.shtml" title="銆婄壒鍔¤糠鍩庛€? blockid="3410">銆婄壒鍔¤糠鍩庛€?/a>鎴愰緳 寰愯嫢鐟?鏇惧織浼?鐗瑰伐鍔ㄤ綔鐗?/dd> 
          <dd><a  href="http://kankan.xunlei.com/vod/mp4/43/43845.shtml" title="銆婃媶寮归儴闃熴€? blockid="3410">銆婃媶寮归儴闃熴€?/a>濂ユ柉鍗℃渶浣崇數褰?鎴樹簤澶х墖</dd> 
          <dd><a  href="http://kankan.xunlei.com/vod/mp4/29/29970.shtml" title="銆婃潃鎵嬭幈鏄傘€? blockid="3410">銆婃潃鎵嬭幈鏄傘€?/a>鍚曞厠路璐濇澗鎵у缁忓吀鏋垬鐗?/dd> 
        </dl> 
      </div> 
    </div> 
    <script> 
    CachePic["shangsheng"][1]=[
    "http://images.movie.xunlei.com/gallery/643/afbfdaf55c679489c53371786b69ba18.jpg"
    ,"http://images.movie.xunlei.com/gallery/643/4aceb59d05235b412e080886f23dfcee.jpg"
    ,"http://images.movie.xunlei.com/gallery/643/6b669adc95f0fb60d0582309fca8aec6.jpg"
    ,"http://images.movie.xunlei.com/gallery/643/046684ce28332018ed9a29a18edb9737.jpg"
    ,"http://images.movie.xunlei.com/gallery/643/006b0de9f2c804f3256f157d256fde82.jpg"
    ]
    </script> 
    <div class="box_con" id="List_shangsheng_2" style="display:none"> 
      <div class="hotplate hotplate_112x96"> 
        <dl> 
          <dt ><a  href="http://kankan.xunlei.com/vod/mp4/0/797.shtml" title="闄堝杩呰嫤杩介儜甯屾€? target="_blank" blockid="3411"><img id="shangsheng_pic_2_0" src="http://images.movie.xunlei.com/gallery/643/d8794c61c87c09b358d0cfc4754de7f6.jpg" alt="闄堝杩呰嫤杩介儜甯屾€? title="闄堝杩呰嫤杩介儜甯屾€? /></a><a  href="http://kankan.xunlei.com/vod/mp4/0/797.shtml" title="闄堝杩呰嫤杩介儜甯屾€? class="hotplate_link" target="_blank" blockid="3411">闄堝杩呰嫤杩介儜甯屾€?/a></dt> 
          <dt style="float:right;"><a  href="http://kankan.xunlei.com/vod/mp4/2/2503.shtml" title="鏉庡槈娆ｇ粷鑹茶鎯? target="_blank" blockid="3411"><img id="shangsheng_pic_2_1" src="http://images.movie.xunlei.com/gallery/643/4c095785d789df5ef3fe8bf0d545e63d.jpg" alt="鏉庡槈娆ｇ粷鑹茶鎯? title="鏉庡槈娆ｇ粷鑹茶鎯? /></a><a  href="http://kankan.xunlei.com/vod/mp4/2/2503.shtml" title="鏉庡槈娆ｇ粷鑹茶鎯? class="hotplate_link" target="_blank" blockid="3411">鏉庡槈娆ｇ粷鑹茶鎯?/a></dt> 
          <dd><a  href="http://kankan.xunlei.com/vod/mp4/16/16807.shtml" title="銆婂崄闈㈠煁浼忋€? blockid="3411">銆婂崄闈㈠煁浼忋€?/a>绔犲瓙鎬?閲戝煄姝?鍒樺痉鍗?/dd> 
          <dd><a  href="http://kankan.xunlei.com/vod/mp4/19/19769.shtml" title="銆婂攼浼檸鐐圭棣欍€? blockid="3411">銆婂攼浼檸鐐圭棣欍€?/a>鍛ㄦ槦椹?宸╀繍 鎼炵瑧鐖辨儏</dd> 
          <dd><a  href="http://kankan.xunlei.com/vod/mp4/22/22768.shtml" title="銆婁腑鍗庤嫳闆勩€? blockid="3411">銆婁腑鍗庤嫳闆勩€?/a>閮戜紛鍋?璋㈤渾閿?鏉ㄦ伃濡?濂囧够鐗?/dd> 
        </dl> 
      </div> 
    </div> 
    <script> 
    CachePic["shangsheng"][2]=[
    "http://images.movie.xunlei.com/gallery/643/d8794c61c87c09b358d0cfc4754de7f6.jpg"
    ,"http://images.movie.xunlei.com/gallery/643/4c095785d789df5ef3fe8bf0d545e63d.jpg"
    ,"http://images.movie.xunlei.com/gallery/643/cf407b68a70f897d630f15ebcaa8e6f4.jpg"
    ,"http://images.movie.xunlei.com/gallery/643/ada809170a13b8e6f578bd2d80ab2712.jpg"
    ,"http://images.movie.xunlei.com/gallery/642/94678f4ea7e1979a47eb192c9226dfea.jpg"
    ]
    </script>
  </div>
</div>
<!-- end -->
