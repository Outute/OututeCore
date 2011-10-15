// JavaScript Document
function switchTab(a,b,c,e,d)
{
	try{for(i=0;i<c;i++)
	{
		var f=document.getElementById("Tab_"+a+"_"+i),g=document.getElementById("List_"+a+"_"+i);
		if(i!=b){f.className=d;g.style.display="none"}
	}
	try{
		for(ind=0;ind<CachePic[a][b].length;ind++)
			document.getElementById(a+"_pic_"+b+"_"+ind).src=CachePic[a][b][ind]}catch(h){}document.getElementById("Tab_"+a+"_"+b).className=e;
			document.getElementById("List_"+a+"_"+b).style.display=""
	}
	catch(j){}
}

Util = typeof(Util)!='undefined' || {
	trimLeft : /^\s+/,
	trimRight : /\s+$/,
	rclass : /[\n\t\r]/g,
	rspaces: /\s+/,
	rselectTextarea : /^(?:select|textarea)/i,
	rinput : /^(?:color|date|datetime|email|hidden|month|number|password|range|search|tel|text|time|url|week)$/i,
	r20 : /%20/g,
	rCRLF : /\r?\n/g,
	rbracket : /\[\]$/,
	__idSeed : 1,
	getIdSeed : function() {
		return 'tcid_'.concat(++Util.__idSeed);
	},
	ajax : function(url, data, callbackArr, async, timeout){
		var xhr = Util.getXHR(), isAsync = async!==false, callbackArr = callbackArr || [], timeouter;
		var status, statusText, responseHeaders, responses, xml, content = data;
		xhr.open(content?'POST':'GET',url,isAsync);
		if(content){
			xhr.setRequestHeader("Content-Length", content.length);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		}
		xhr.onreadystatechange = function(){
			if (xhr.readyState == 4){
				xhr.onreadystatechange = function(){};
				timeouter && clearTimeout(timeouter);
				status = xhr.status;
				responseHeaders = xhr.getAllResponseHeaders();
				responses = {};
				responses.text = xhr.responseText;
				statusText = xhr.statusText;
				if (xhr.status == 200){
					callbackArr.length>0 && callbackArr[0](status, statusText, responses);
				} else {//request.status == 404 and other
					callbackArr.length>1 && callbackArr[1](status, statusText, responses);
				}
			}
		};
		xhr.send(content?content:null);
		if(isAsync && timeout){
			timeouter = setTimeout(function(){xhr.abort();},timeout);
		}
	},
	getXHR : function(){
		return (function(){try{return new window.XMLHttpRequest();}catch(e){}})()
				||(function(){try{return new window.ActiveXObject( "Microsoft.XMLHTTP" );}catch(e){}})();
	},
	id : function(id){
		return typeof(id)=='string'?document.getElementById(id):id;
	},
	html : function(id, html){
		var reg = /<script(.|\s)*?\/script>/gi, el = Util.id(id), script;
		el.innerHTML=html.replace(reg, '');
		var arr = Util.childFilter(el,function(elem,array){
			array[0]=Util.getAttr(elem,'script');
		});
		script = arr&&arr.length?arr[0]:null;
		if(script){
			setTimeout(function(){eval(('{'+script+'}'))},100);
		}
	},
	getAttr: function(id,name){
		var el = typeof(id)=='string'?Util.id(id):id;
		var attrNode = el.getAttributeNode(name);
		return attrNode?attrNode.nodeValue:null;
	},
	showId : function(id){
		id = id?(id instanceof Array)?id:[id]:[];
		for(var i=0;i<id.length;i++){
			Util.id(id[i]).style.display='';
		}
		return this;
	},
	hideId : function(id){
		id = id?(id instanceof Array)?id:[id]:[];
		for(var i=0;i<id.length;i++){
			Util.id(id[i]).style.display='none';
		}
		return this;
	},
	serialize: function(formId) {
		if(formId==null){
			return null;
		}
		var s=[], form=Util.id(formId);
		if(typeof(formId)=='object'){
			for(var k in formId){
				s.push({name:k,value:formId[k]});
			}
		}else if(form && form.elements.length){
			for(var i=0; i<form.elements.length; i++){
				var a = form.elements[i];
				if(a.name && !a.disabled && (a.checked || Util.rselectTextarea.test(a.nodeName) || Util.rinput.test(a.type))){
					s.push(a);
				}
			}
		}
		return Util.param(s);
	},
	param: function( a ) {
		var s = [],
			add = function( key, value ) {
				s[ s.length ] = encodeURIComponent( key ) + "=" + encodeURIComponent( value.replace( Util.rCRLF, "\r\n" ) );
			};
		if (a instanceof Array && a.length) {
			for(var i=0;i<a.length;i++){
				add( a[i].name, a[i].value );
			}
		}/* else {
			for ( var prefix in a ) {
				Util.buildParams( prefix, a[ prefix ], traditional, add );
			}
		}*/
		alert("param:"+s.length);
		return s.join( "&" ).replace( Util.r20, "+" );
	},
	buildParams: function( prefix, obj, traditional, add ) {
		if ((obj instanceof Array) && obj.length ) {
			for(var i=0;i<obj.length;i++){
				var v = obj[i];
				if (traditional || rbracket.test(prefix)) {
					add( prefix, v );
				} else {
					Util.buildParams( prefix + "[" + ( typeof v === "object" || (v instanceof Array) ? i : "" ) + "]", v, traditional, add );
				}
			}
		} else if ( !traditional && obj != null && typeof obj === "object" ) {
			if((obj instanceof Array) || (function(){var k;for(var k in obj);return k==undefined;})()){
				add( prefix, "" );
			} else {
				for ( var name in obj ) {
					Util.buildParams( prefix + "[" + name + "]", obj[ name ], traditional, add );
				}
			}
		} else {
			add( prefix, obj );
		}
	},
	replace : function(str,arr,blank){
		var i=0, len = arr.length;
		str = str.replace(/\{\w*\}/g,function(block){
			return i<len?arr[i++]:blank==undefined?block:blank;
		});
		return str;
	},
	hasClass: function(id, className) {
		var el = Util.id(id);
		className = " " + className + " ";
		if ( (" " + el.className + " ").replace(Util.rclass, " ").indexOf( className ) > -1 ) {
			return true;
		}
		return false;
	},
	addClass: function(id, value){
		var el = Util.id(id), classNames = (value||"").split(Util.rspaces);
		if(!value||Util.hasClass(el,value)){
			return;
		}
		var className = el.className+" "+value;
		el.className = className.replace(Util.trimLeft,"").replace(Util.trimRight,"");
	},
	removeClass: function(id, value){
		var el = Util.id(id);
		if(!value||!Util.hasClass(el,value)){
			return;
		}
		var className = el.className, indexOf=-1;
		while((indexOf=className.indexOf(value,indexOf))>-1){
			if(indexOf+value.length==className.length){
				className=className.substring(0,indexOf);
				break;
			}
			if(className.charAt(indexOf+value.length)==' '){
				className = className.substring(0,indexOf)+className.substring(indexOf+value.length+1,className.length);
				break;
			}
		}
		el.className = className.replace(Util.trimLeft,"").replace(Util.trimRight,"");
	},
	childFilter: function(id, fun){
		var el = typeof(id)=='string'?Util.id(id):id, arr=[];
		if(!el.childNodes.length){return null;}
		for(var i=0;i<el.childNodes.length;i++){
			var elem = el.childNodes[i];
			if ( !elem || elem.nodeType === 3 || elem.nodeType === 8 || elem.nodeType === 2 ) {
				continue;
			}
			if(typeof(fun)=='function'&&!fun(elem,arr)){
				break;
			}
		}
		return arr;
	},
	calender: function(id, input, day, month, year, diffMonth){
		var el = Util.id(id);
		if(!el){return;}
		var dateHtml=[], c=0, tr=[], param=[], date = new Date();
		var monthStr=['January','February','March','April','May','June','July','August','September','October','November','December'];
		var weekStr=['Su','Mo','Tu','We','Th','Fr','Sa'];
		dateHtml[c++]='<div class="calendar-panel" id="{id}" selectedId="{selectedId}" day="{day}" month="{month}" year="{year}">';
		dateHtml[c++]='<div class="calendar-header"><table style="width: 100%;">';
		dateHtml[c++]=/*   */'<tr><td class="calendar-premonth" style="width: 15px; cursor: pointer;">◄</td>';
		dateHtml[c++]=/*   */'<td style="text-align: center;"><h4>{August}&nbsp;{2011}</h4></td>';
		dateHtml[c++]=/*   */'<td  class="calendar-nextmonth" style="width: 15px; cursor: pointer;">►</td></tr>';
		dateHtml[c++]='</table></div>';
		dateHtml[c++]='<div class="calendar-content" style="display: block;">';
		dateHtml[c++]='<table class="calendar-day" style="width: 100%;" cellpadding="0" cellspacing="0">';
		dateHtml[c++]=/**/'<thead><tr>';
		dateHtml[c++]=/*   */'<th>{Su}</th><th>{Mo}</th><th>{Tu}</th><th>{We}</th><th>{Th}</th><th>{Fr}</th><th>{Sa}</th>';
		dateHtml[c++]=/**/'</tr></thead>';
		dateHtml[c++]=/**/'<tbody>{calendarDay}</tbody>';
		dateHtml[c++]='</table></div></div>';
		c=0;
		for(var i=1;i<=42; i++){
			tr[c++]=(i+7>42?'<tr class="last">':'<tr>');
			while(i%7!=0){
				tr[c++]='<td id="{id}" class="calendar-day-cell {selected} {disabled}">{'+i+++'}</td>';
			}
			tr[c++]='<td id="{id}" class="calendar-day-cell {selected} {disabled} last">{'+i+'}</td>';
			tr[c++]='</tr>';
		}
		if(day&&month&&year){
			diffMonth=diffMonth||0;year = ~~year; month = ~~month+~~diffMonth; day = ~~day;
			date.setFullYear(year+~~(month/12));
			date.setMonth(month%12);
			date.setDate(1);
		}
		c=0;
		var tdParams = [], d = new Date(), dd = date.getDate(), mm=date.getMonth(), yy=date.getFullYear(), selectedId='';
		d.setTime(date.getTime());
		d.setDate(-1*d.getDay()+1);
		for(var i=0;i<42;i++){
			var dd_=d.getDate(), dm=d.getMonth(), dy=d.getFullYear(), isSelected = !selectedId && (dd==dd_&&mm==dm), id_=Util.getIdSeed();
			if(isSelected){selectedId=id_;}
			tdParams[c++]=id_;
			tdParams[c++]=(isSelected?'calendar-selected':'');
			tdParams[c++]=(mm!=dm||yy>dy||mm>d.getMonth()||dd>dd_?'calendar-disabled':'calendar-selectabled');
			tdParams[c++]=d.getDate();
			d.setDate(d.getDate()+1);
		}
		var calendarDayHtml = Util.replace(tr.join(''),tdParams,''); tdParams = undefined;
		{
			c=0;
			param[c++]=Util.getIdSeed();
			param[c++]=selectedId;
			param[c++]=date.getDate();
			param[c++]=date.getMonth();
			param[c++]=date.getFullYear();
			param[c++]=monthStr[date.getMonth()];
			param[c++]=date.getFullYear();
			for(var i=0;i<weekStr.length;i++){
				param[c++]=weekStr[i];
			}
			param[c++]=calendarDayHtml;
		}
		Util.html(id,Util.replace(dateHtml.join(''),param,''));
		{
			var y_=''+yy, m_=''+mm, d_=''+dd;
			Util.id(input).value=y_+"-"+(m_.length<2?("0"+m_):m_)+"-"+(d_.length<2?("0"+d_):d_);
		}
		var arrTmp = Util.childFilter(el,function(elem,arrs){
			if (!Util.hasClass(elem,'calendar-panel')) {
				return true;
			}else{
				arrs[0]=elem;
			}
		});
		var panel=arrTmp&&arrTmp.length?arrTmp[0]:null;
		if(!panel){
			return;
		}
		var getCalendarData=function(){
			var arr0=Util.childFilter(el,function(el1,arr1){
				if(Util.hasClass(el1,'calendar-panel')){
					arr1[0]={id:el1.id,selectedId:Util.getAttr(el1,'selectedId'),day:Util.getAttr(el1,'day'),month:Util.getAttr(el1,'month'),year:Util.getAttr(el1,'year')};
				}else{
					return true;
				}
			});
			return arr0&&arr0.length?arr0[0]:null;
		}
		var setCalendarData=function(data){
			var obj={selectedId:'',day:'',month:'',year:''},
				arr0=Util.childFilter(el,function(el1,arr1){
				if(Util.hasClass(el1,'calendar-panel')){
					for(var k in data){
						if(k in obj){
							el1.getAttributeNode(k).nodeValue=data[k];
						}
					}
				}else{
					return true;
				}
			});
			return arr0&&arr0.length?arr0[0]:null;
		}
		var eventHandle=function(event){
			var elem=event.srcElement||event.target;
			if(Util.hasClass(elem,'calendar-premonth')){
				var sd = getSelectedDate();
				if(sd){
					Util.calender(id,input,sd.day,sd.month,sd.year,-1);
				}
			}
			if(Util.hasClass(elem,'calendar-nextmonth')){
				var sd = getCalendarData();
				if(sd){
					Util.calender(id,input,sd.day,sd.month,sd.year,1);
				}
			}
			if(Util.hasClass(elem,'calendar-day-cell')&&Util.hasClass(elem,'calendar-selectabled')){
				var sd = getCalendarData();
				if(sd){
					var day = elem.textContent || elem.innerText;
					Util.removeClass(sd.selectedId,'calendar-selected');
					setCalendarData({day:day,selectedId:elem.id});
					Util.addClass(elem,'calendar-selected');
					Util.id(input).value=sd.year+"-"+(sd.month.length<2?("0"+sd.month):sd.month)+"-"+(day.length<2?("0"+day):day);
				}
			}
		};
		if (panel.addEventListener) {
			panel.addEventListener('click', eventHandle, false );
		} else if (panel.attachEvent ) {
			panel.attachEvent( "onclick", eventHandle );
		}
	}
};

function clickAddTutorial(id,url){
	Util.ajax(url,null,[function(status, statusText, responses){
		Util.html(id,responses.text);
	},function(status, statusText, responses){alert("ERROR :"+status);}]);
}
