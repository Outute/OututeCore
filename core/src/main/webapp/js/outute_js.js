// JavaScript Document
/* IE CollectGarbage */if (!-[1,]) {
	setInterval('CollectGarbage', 10000);
}
function clearErrorMessages(form) {
	try {
        var divs = form.getElementsByTagName("div");
        for(var i=0;i<divs.length;i++){
        	if(divs[i].className=='error'){
        		divs[i].style.display='none';
        	}
        }
    } catch (e) {
        alert(e);
    }
}

function addError(e, errorText) {
	try {
        var ctrlDiv = e.parentNode; // wwctrl_ div or span
        var divs = ctrlDiv.getElementsByTagName("div");
        for(var i=0;i<divs.length;i++){
        	if(divs[i].className=='error'){
        		if(divs[i].id==(e.id+'_'+errorText)){
        			divs[i].style.display='';
        		}else{
        			divs[i].style.display='none';
        		}
        	}
        }
    } catch (e) {
        alert(e);
    }
}
function switchTab(a,b,c,e,d){
	try{
		for(var i=0;i<c;i++){
			var f=document.getElementById("Tab_"+a+"_"+i),g=document.getElementById("List_"+a+"_"+i);
			if(i!=b){
				f.className=d;
				g.style.display="none";
			}
		}
		try{
			for(var ind=0;ind<CachePic[a][b].length;ind++){
				document.getElementById(a+"_pic_"+b+"_"+ind).src=CachePic[a][b][ind];
			}
		}catch(h){}
		document.getElementById("Tab_"+a+"_"+b).className=e;
		document.getElementById("List_"+a+"_"+b).style.display="";
	}catch(j){}
}

Util = typeof(Util)!='undefined' || {
	trimLeft : /^\s+/,
	trimRight : /\s+$/,
	rclass : /[\n\t\r]/g,
	rspaces: /\s+/,
	rselectTextarea : /^(?:select|textarea)/i,
	rinput : /^(?:color|date|datetime|email|hidden|month|number|password|range|search|tel|text|time|url|week)$/i,
	//rxhtmlTag : /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:]+)[^>]*)\/>/ig,
	r20 : /%20/g,
	rCRLF : /\r?\n/g,
	rbracket : /\[\]$/,
	__idSeed : 1,
	getIdSeed : function() {
		return 'tcid_'.concat(++Util.__idSeed);
	},
	getCtx: function(){
		if(window['app_ctx']){
			return window['app_ctx'];
		}
		try{
			var e = Util.id('app_ctx');
			window['app_ctx']=e?e.value:null;
		}catch(err){}
	},
	ajax : function(url, data, callbackArr, async, timeout){
		var xhr = Util.getXHR(), isAsync = async!==false, callbackArr = callbackArr || [], timeouter;
		var status, statusText, responseHeaders, responses, xml, content = data;
		url = url.indexOf('ajax=true')<0&&url.indexOf('?')<0?(url+'?ajax=true'):url.indexOf('ajax=true')?(url+'&ajax=true'):url;
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
		var reg = /<script(.|\s)*?\/script>/gi, el = Util.id(id), script, newdiv;
		if(!el){return this;}
		html=html?html.replace(reg, ''):'';
		try{
			el.innerHTML=html;
		}catch(err){
			while (el.firstChild) {
				el.removeChild(el.firstChild);
			}
			try{
			newdiv = document.createElement("div");
			newdiv.innerHTML=html;
			el.appendChild(newdiv);
			}catch(err1){alert(err1)}
		}
		var arr = Util.childFilter(el,function(elem,array){
			array[0]=Util.getAttr(elem,'script');
		});
		script = arr&&arr.length?arr[0]:null;
		if(script){
			setTimeout(function(){Util.eval(script);},100);
		}
		return this;
	},
	eval: function(script){
		try{return eval(('{'+script+'}'));}catch(err){}
		return undefined;
	},
	click: function(id){
		try{var el=Util.id(id);(el.onclick||el.click||function(){})();}catch(err){}
	},
	getAttr: function(id,name){
		var el = Util.id(id), attrNode = el?el.getAttributeNode(name):null;
		return el&&attrNode?attrNode.nodeValue:null;
	},
	setAttr: function(id,name,value){
		var el = Util.id(id);
		if(el){el.setAttribute(name, ""+value);}
		return this;
	},
	toggleClass: function(parentId,elId,className){
		var pel = Util.id(parentId), el = Util.id(elId);
		if(!(pel&&el)){return this;}
		if(parentId!=elId){
			var oldId = pel["selectedId_"+className];
			if(oldId){
				Util.removeClass(oldId,className);
			}
			pel["selectedId_"+className] = elId;
		}
		if(!Util.hasClass(el,className)){
			Util.addClass(el,className);
		}
		return this;
	},
	data: function(id,key,value){
		var el = Util.id(id);
		if(el){
			el['__data__']=el['__data__']||{};
			if(value!=undefined){
				el['__data__'][key]=value;
			}else{
				return el['__data__'][key];
			}
		}
	},
	showId : function(id){
		id = id?(id instanceof Array)?id:[id]:[];
		for(var i=0;i<id.length;i++){
			var el=Util.id(id[i]);
			if(el){
				el.style.display='';
			}
		}
		return this;
	},
	hideId : function(id){
		id = id?(id instanceof Array)?id:[id]:[];
		for(var i=0;i<id.length;i++){
			var el=Util.id(id[i]);
			if(el){
				el.style.display='none';
			}
		}
		return this;
	},
	validate: function(formId){
		var form=Util.id(formId), result=true;
		if(!form||!form.elements||!form.elements.length){return true;}
		for(var i=0, len=form.elements.length; i<len; i++){
			var a = form.elements[i];
			if(a.name && !a.disabled && (a.checked || Util.rselectTextarea.test(a.nodeName) || Util.rinput.test(a.type))){
				var script = Util.getAttr(a,'validate');
				if(script){
					result = result && (Util.eval(script)||false);
				}
			}
		}
		return result;
	},
	serialize: function(formId) {
		var s=[], form=Util.id(formId);
		if(!formId||!form){return null;}
		if(typeof(formId)=='object'&&form.nodeName.toLowerCase()!='form'){
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
	param: function(a) {
		var s = [],
			add = function( key, value ) {
				s[s.length] = encodeURIComponent( key ) + "=" + encodeURIComponent( value.replace( Util.rCRLF, "\r\n" ) );
			};
		if (a instanceof Array && a.length) {
			for(var i=0;i<a.length;i++){
				if(a[i].name && a[i].value){
					add(a[i].name, a[i].value);
				}
			}
		}
		return s.join( "&" ).replace( Util.r20, "+" );
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
		if(!el){return false;}
		className = " " + className + " ";
		if ((" "+el.className+" ").replace(Util.rclass," ").indexOf(className)>-1) {
			return true;
		}
		return false;
	},
	addClass: function(id, value){
		var el = Util.id(id), classNames = (value||"").split(Util.rspaces);
		if(!el){return this;}
		if(!value||Util.hasClass(el,value)){
			return this;
		}
		var className = el.className+" "+value;
		el.className = className.replace(Util.trimLeft,"").replace(Util.trimRight,"");
		return this;
	},
	removeClass: function(id, value){
		var el = Util.id(id);
		if(!el||!value||!Util.hasClass(el,value)){
			return this;
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
		return this;
	},
	childFilter: function(id, fun){
		var el = Util.id(id), arr=[];
		if(!el||!el.childNodes.length){return null;}
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
	dateToStr: function(date){
		var y = date.getFullYear()+"", m = (date.getMonth()+1)+"", d = date.getDate()+"";
		return y+""+(m.length<2?("0"+m):m)+""+(d.length<2?("0"+d):d);
	},
	strToDate: function(str){
		if(str&&str.length==8){
			var y = ~~str.substring(0,4), m = ~~str.substring(4,6)-1, d = ~~str.substring(6,8), date = new Date();
			date.setDate(d); date.setMonth(m); date.setFullYear(y);
			return date;
		}
		return null;
	},
	toDay: function(timezone){
		var date = new Date();
		return date;
	},
	monthStr: function(timeZone){
		return ['January','February','March','April','May','June','July','August','September','October','November','December'];
	},
	weekStr: function(timeZone){
		return ['Su','Mo','Tu','We','Th','Fr','Sa'];
	},
	bind: function(id,type,data,filterClass,func){
		var el=Util.id(id), filterClass=!filterClass?[]:(filterClass instanceof Array)?filterClass:[filterClass+""];
		if(!el){ return; }
		var f=function(event){
			var elem=event.srcElement||event.target, i=0, len=filterClass.length;
			for(i=0;i<len;i++){
				if(!Util.hasClass(elem,filterClass[i])){
					break;
				}
			}
			if(i==len){
				func(event,data,id);
			}
		};
		if (el.addEventListener) {
			el.addEventListener(type, f, false );
		} else if (el.attachEvent ) {
			el.attachEvent( 'on'+type, f);
		}
	},
	isSameDate: function(date1,date2){
		return Util.dateToStr(date1)==Util.dateToStr(date2);
	},
	isSameWeek: function(date1,date2){
		return date1.getDay()==date2.getDay()&&Math.abs((~~Util.dateToStr(date1))-(~~Util.dateToStr(date2)))<7;
	},
	isSameMonth: function(date1,date2){
		return Util.dateToStr(date1).indexOf(Util.dateToStr(date2).substring(0,6))==0;
	},
	getCalendarHeader: function(id){
		var el=Util.id(id);
		if(!el){return;}
		var h4s = el.getElementByName("H4");
		if(h4s&&h4s.length){
			return h4s[0].innerText;
		}
		return "";
	},
	getCalendarData: function(id){
		var el=Util.id(id), arr0=Util.childFilter(el,function(el1,arr1){
			if(Util.hasClass(el1,'calendar-panel')){
				arr1[0]={id:el1.id,selectedId:Util.getAttr(el1,'selectedId'),date:Util.getAttr(el1,'date')
						,today:Util.getAttr(el1,'today'),todayid:Util.getAttr(el1,'todayid')
						,idPrefix:Util.getAttr(el1,'idPrefix'),viewType:Util.getAttr(el1,'viewType')};
			}else{
				return true;
			}
		});
		return arr0&&arr0.length?arr0[0]:null;
	},
	setCalendarData: function(id,data){
		var el=Util.id(id), obj={selectedId:'',date:''},
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
	},
	calender: function(id, props, yyyyMMdd, diffMonth){
		return {
			init: function(id, props, yyyyMMdd){
				var el = Util.id(id);
				if(!el){return null;}
				props=typeof(props)=='string'?{input:props}:props;props['id']=id;
				var input = props.input||'', click=props.click, funcParams=props.funcParams, cellRender=props.cellRender;
				var dateHtml=[], c=0, x=0, tr=[], param=[], date = Util.toDay();
				var monthStr=Util.monthStr(), weekStr=Util.weekStr(), idPrefix=this.idPrefix=Util.getIdSeed()+'_';
				dateHtml[c++]='<div class="calendar-panel" id="{id}" selectedId="{selectedId}" date="{yyyyMMdd}" today="{today}" todayid="{todayid}" idPrefix="{idPrefix}" viewType="month">';
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
				//
				date = Util.strToDate(yyyyMMdd)||date;
				diffMonth = ~~diffMonth||0;
				if(diffMonth){
					date.setDate(1);
					date.setMonth(date.getMonth()+diffMonth);
				}
				var tdParams = [], selectedId='', todayId='', dateStr = Util.dateToStr(date), toDayStr = Util.dateToStr(Util.toDay());
				{
					c=0;
					var d = Util.toDay();
					{
						d.setTime(date.getTime());
						d.setDate(1);
						d.setDate(-1*d.getDay()+1);
					}
					for(var i=1;i<=42; i++){
						tr[c++]=(i+7>42?'<tr class="last">':'<tr>');
						for(;;i++){
							tr[c++]='<td id="{id}" class="calendar-day-cell {selected} {disabled} {last} {renderCss}">{'+i+'}</td>';
							{
								var dStr = Util.dateToStr(d), id_=idPrefix+dStr, isSelected = !selectedId && dStr==dateStr, isToDay=dStr==toDayStr;
								var s1 = ~~toDayStr>~~dStr, s2 = dateStr.substring(0,6)!=dStr.substring(0,6)&&~~dateStr!=~~dStr;
								selectedId = isSelected?id_:selectedId;
								todayId=isToDay?id_:todayId;
								tdParams[x++]=id_;
								tdParams[x++]=isSelected&&!s1&&!s2?'calendar-selected':'';
								tdParams[x++]=s1?'calendar-disabled':s2?'calendar-disabled-nextmonth':'calendar-selectabled';
								tdParams[x++]=i%7!=0?'':'last';
								if(typeof(cellRender)=='function'){
									var content=cellRender(d,i,id_,isToDay,props.data);
									if(typeof(content)=='string'){
										tdParams[x++]='';
										tdParams[x++]=content;
									}else{
										tdParams[x++]=content['css']||'';
										tdParams[x++]=content['html']||'';
									}
								}else{
									tdParams[x++]='';
									tdParams[x++]=d.getDate();
								}
								d.setDate(d.getDate()+1);
							}
							if(i%7==0){
								break;
							}
						}
						tr[c++]='</tr>';
					}
				}
				var calendarDayHtml = Util.replace(tr.join(''),tdParams,''), panelId=idPrefix+'panel'; 
				tdParams = undefined;
				{
					c=0;
					param[c++]=panelId;
					param[c++]=selectedId;
					param[c++]=dateStr+"";
					param[c++]=toDayStr;
					param[c++]=todayId;
					param[c++]=idPrefix;
					param[c++]=monthStr[date.getMonth()];
					param[c++]=date.getFullYear();
					for(var i=0;i<weekStr.length;i++){
						param[c++]=weekStr[i];
					}
					param[c++]=calendarDayHtml;
				}
				Util.html(id,Util.replace(dateHtml.join(''),param,''));
				{
					var dateInput = Util.id(input);
					if(dateInput){
						var dateStr = Util.dateToStr(date);
						dateInput.value=dateStr.substring(4,6)+"/"+dateStr.substring(6,8)+"/"+dateStr.substring(0,4);
					}
				}
				var panel=Util.id(panelId);
				if(!panel){ return null;}
				var eventHandle=function(event,data,id){
					var elem=event.srcElement||event.target;
					var props = data, input = props.input||'', click=props.click, funcParams=props.funcParams, id=props.id;
					var sd = Util.getCalendarData(id);
					if(!sd){return;}
					if(Util.hasClass(elem,'calendar-premonth')){
						var toDate = Util.dateToStr(Util.toDay());
						if(sd.date.substring(0,6)>toDate.substring(0,6)){
							Util.calender(id,props,sd.date,-1);
							if(typeof(click)=='function'){
								var date = Util.strToDate(sd.date);
								diffMonth = -1;
								date.setDate(1);
								date.setMonth(date.getMonth()+diffMonth);
								funcParams=funcParams||{};
								funcParams.date=Util.dateToStr(date);
								click(funcParams);
							}
						}
					}else if(Util.hasClass(elem,'calendar-nextmonth')){
						Util.calender(id,props,sd.date,1);
						if(typeof(click)=='function'){
							var date = Util.strToDate(sd.date);
							diffMonth = 1;
							date.setDate(1);
							date.setMonth(date.getMonth()+diffMonth);
							funcParams=funcParams||{};
							funcParams.date=Util.dateToStr(date);
							click(funcParams);
						}
					}else if(Util.hasClass(elem,'calendar-day-cell')&&Util.hasClass(elem,'calendar-selectabled')){
						var day = elem.textContent || elem.innerText, data = {date:sd.date.substring(0,6)+(day.length<2?'0':'')+day,selectedId:elem.id};
						Util.removeClass(sd.selectedId,'calendar-selected');
						Util.setCalendarData(id,data);
						Util.addClass(elem,'calendar-selected');
						var dateInput = Util.id(input), value=data.date.substring(4,6)+"/"+data.date.substring(6,8)+"/"+data.date.substring(0,4);
						if(dateInput){
							dateInput.value=value;
						}
						if(typeof(click)=='function'){
							funcParams=funcParams||{};
							funcParams.date=data.date;
							click(funcParams);
						}
					}
				};
				Util.bind(panelId,'click',props,[],eventHandle);
				return this;
			}
		}.init(id, props, yyyyMMdd);
	},
	calenderDayView: function(id, props, yyyyMMdd){
		return {
			init: function(id, props, yyyyMMdd){
				var el = Util.id(id);
				if(!el){return null;}
				props=typeof(props)=='string'?{input:props}:props;
				var input = props.input||'', click=props.click, funcParams=props.funcParams;
				var dateHtml=[], c=0, tr=[], param=[], date = new Date();
				var monthStr=Util.monthStr(), weekStr=Util.weekStr(), idPrefix=Util.getIdSeed()+'_';
				dateHtml[c++]='<div class="calendar-panel" id="{id}" date="{yyyyMMdd}" idPrefix="{idPrefix}" viewType="day">';
				dateHtml[c++]='<div class="calendar-content" style="display: block;">';
				dateHtml[c++]='<table class="calendar-day" style="width: 100%;" cellpadding="0" cellspacing="0">';
				dateHtml[c++]=/**/'<thead><tr>';
				dateHtml[c++]=/*   */'<th class="time-cell-header"><th colspan="7">{date_weekday}</th>';
				dateHtml[c++]=/**/'</tr></thead>';
				dateHtml[c++]=/**/'<tbody>{calendarDay}</tbody>';
				dateHtml[c++]='</table></div></div>';
				date = Util.strToDate(yyyyMMdd)||date, dateStr=Util.dateToStr(date);
				{
					c=0;
					for(var i=0; i<12; i++){
						tr[c++]=(i==11?'<tr class="last">':'<tr>');
						tr[c++]='<td id="{id}" class="calendar-day-cell time-cell">'+i*2+':00AM</td>';
						{
							param.push(idPrefix+(i*2));
						}
						if(i==0){
							tr[c++]='<td id="{id}" class="calendar-day-cell schedule-day-cell last" colspan="7" rowspan="12"><div style="position:relative;width:100%;height:456px;padding:0;margin:0;border:0;overflow:visible;display:block;">';
							{
								param.push(idPrefix+dateStr);
							}
							if(typeof(props.cellRender)=='function'){
								//tr[c++]='<div style="background-color:red; position: absolute; top:38px; width:100px; left:0px;">test</div></td>';
								tr[c++]=props.cellRender(dateStr,38,param,props.data);
							}
							tr[c++]='</div></td>';
						}
						tr[c++]='</tr>';
					}
				}
				var trHtml = Util.replace(tr.join(''),param,'');
				date = Util.strToDate(yyyyMMdd)||date;
				{
					c=0;
					param=[];
					param[c++]=idPrefix+'panel';
					param[c++]=Util.dateToStr(date);
					param[c++]=idPrefix;
					param[c++]=Util.dateToStr(date)+" Monday";
					param[c++]=trHtml;
				}
				Util.html(el,Util.replace(dateHtml.join(''),param,''));
				return this;
			}
		}.init(id, props, yyyyMMdd);
	},
	calenderWeekView: function(id, props, yyyyMMdd){
		return {
			init:function(id, props, yyyyMMdd){
				var el = Util.id(id);
				if(!el){return null;}
				props=typeof(props)=='string'?{input:props}:props;
				var monthStr=Util.monthStr(), weekStr=Util.weekStr();
				var input = props.input||'', click=props.click, funcParams=props.funcParams;
				var dateHtml=[], c=0, tr=[], param=[], date = Util.strToDate(yyyyMMdd)||Util.toDay();
				var selectedId='', todayId='', dateStr = Util.dateToStr(date), toDayStr = Util.dateToStr(Util.toDay());
				var monthStr=Util.monthStr(), weekStr=Util.weekStr(), idPrefix=Util.getIdSeed()+'_';
				dateHtml[c++]='<div class="calendar-panel" id="{id}" selectedId="{selectedId}" date="{yyyyMMdd}" today="{today}" todayid="{todayid}" idPrefix="{idPrefix}" viewType="week">';
				dateHtml[c++]='<div class="calendar-content" style="display: block;">';
				dateHtml[c++]='<table class="calendar-day" style="width: 100%;" cellpadding="0" cellspacing="0">';
				dateHtml[c++]=/**/'<thead><tr>';
				dateHtml[c++]=/*   */'<th class="time-cell-header"></th><th>{Su}</th><th>{Mo}</th><th>{Tu}</th><th>{We}</th><th>{Th}</th><th>{Fr}</th><th>{Sa}</th>';
				dateHtml[c++]=/**/'</tr></thead>';
				dateHtml[c++]=/**/'<tbody>{calendarDay}</tbody>';
				dateHtml[c++]='</table></div></div>';
				var d = Util.toDay(), weekArr=[];
				{
					d.setTime(date.getTime());
					d.setDate(-1*d.getDay()+d.getDate());
				}
				{
					c=0;
					for(var i=0; i<12; i++){
						tr[c++]=(i==11?'<tr class="last">':'<tr>');
						tr[c++]='<td id="{id}" class="calendar-day-cell time-cell">'+i*2+':00AM</td>';
						{
							param.push(idPrefix+(i*2));
						}
						if(i==0){
							for(var j=0;j<7;j++){
								tr[c++]='<td id="{id}" class="calendar-day-cell {selected} {disabled} schedule-week-cell'+(j==6?' last':'')+'" rowspan="12"><div style="position:relative;width:81px;height:456px;padding:0;margin:0;border:0;overflow:visible;display:block;">';
								{
									var dStr=weekArr[j]=Util.dateToStr(d), id_=idPrefix+dStr, isToDay=dStr==toDayStr;
									var s1 = ~~toDayStr>~~dStr, s2 = dateStr.substring(0,6)!=dStr.substring(0,6)&&~~toDayStr<~~dStr;
									todayId=isToDay?id_:todayId;
									param.push(id_);
									param.push(dStr==dateStr?'calendar-selected':'');
									param.push(s1?'calendar-disabled':s2?'calendar-disabled-nextmonth':'calendar-selectabled');
									d.setDate(d.getDate()+1);
								}
								if(typeof(props.cellRender)=='function'){
									//<div style="background-color:red; position: absolute; top:0px; left:0px; width:100px; float:left;">test</div>
									tr[c++]=props.cellRender(dStr,38,param,props.data);
								}
								tr[c++]='</div></td>';
							}
						}
						tr[c++]='</tr>';
					}
				}
				var trHtml = Util.replace(tr.join(''),param,'');
				{
					c=0;
					param=[];
					param[c++]=idPrefix+'pandel';
					param[c++]=selectedId;
					param[c++]=dateStr;
					param[c++]=toDayStr;
					param[c++]=todayId;
					param[c++]=idPrefix;
					for(var i=0;i<weekStr.length;i++){
						param[c++]=weekArr[i].substring(4,8)+" "+weekStr[i];
					}
					param[c++]=trHtml;
				}
				Util.html(el,Util.replace(dateHtml.join(''),param,''));
				return this;
			}
		}.init(id, props, yyyyMMdd);
	}
};
function ajaxError(status, statusText, responses){
	alert("Service is temporary unavailable:"+statusText);
}
function showMessages(html){
	setTimeout(function(){Util.html('messagesContent',html)},100);
}
function loadPage(url,data,parentId){
	Util.ajax(url,data,[function(status, statusText, responses){
		Util.html(parentId,responses.text);
	},ajaxError]);
}
function commitPage(commitUrl,commitData,reloadUrl,reloadData,parentId){
	Util.ajax(commitUrl,commitData,[function(status, statusText, responses){
		showMessages(responses.text);
		Util.ajax(reloadUrl,reloadData,[function(a,b,c){
			Util.html(parentId,c.text);
		},ajaxError]);
	},ajaxError]);
}
function getTutorialId(tutorialId){
	return {name:'tutorial.id',value:tutorialId};
}
function getTutoriaSchedulelId(tutorialScheduleId){
	return {name:'tutorialSchedule.id',value:tutorialScheduleId};
}
function getTime(time){
	var ampm=['am','pm'], time=(time||'').toLowerCase(), minute=0;
	for(var i=0;i<ampm.length;i++){
		var contains = time.indexOf(ampm[i])>-1 && (time=time.replace(ampm[i],''));
		if(contains || i+1==ampm.length){
			var arr = time.split(':');
			minute=(arr.length>0?(~~arr[0]*60):0)+(contains?i*12*60:0)+(arr.length>1?(~~arr[1]):0);
		}
	}
	return minute;
}
function validateFromTo(from,to,maxError,gtError){
	var fromTime = getTime(from.value), toTime = getTime(to.value);
	if(fromTime<0||fromTime>=24*60){
		addError(from,maxError);
		return false;
	}
	if(toTime<0||toTime>24*60){
		addError(to,maxError);
		return false;
	}
	if(fromTime>=toTime){
		addError(to,gtError);
		return false;
	}
	return true;
}
function clickAddTutorial(id,url){
	loadPage(url,null,id);
}
function clickTakeTutorial(id,url){
	return clickAddTutorial(id,url);
}
function clickTutorial(pId,tutorialId,url,reloadId){
	Util.toggleClass(pId,'tid_'+tutorialId,'tutorialSelected');
	Util.data(pId,'tutorialId',tutorialId);
	loadPage(url,Util.param([getTutorialId(tutorialId)]),reloadId);
}
function saveTutorial(commitUrl,reloadUrl,formId,id){
	commitPage(commitUrl,Util.serialize(formId),reloadUrl,null,id);
}
function editTutorial(url,tutorialId,id){
	loadPage(url,Util.param([getTutorialId(tutorialId)]),id);
}
function deleteTutorial(commitUrl,reloadUrl,tutorialId,id){
	if(!confirm('Are you sure to delete this tutorial?')){
		return;
	}
	commitPage(commitUrl,Util.param([getTutorialId(tutorialId)]),reloadUrl,null,id);
}
function clickAddDate(url,tutorialId,id){
	loadPage(url,Util.param([getTutorialId(tutorialId)]),id);
}
function saveTutorialSchedule(commitUrl,formId,reloadUrl,id){
	if(!Util.validate(formId)){
		return;
	}
	var param = Util.serialize(formId);
	commitPage(commitUrl,param,reloadUrl,param,id);
}
function editTutorialSchedule(url,tutorialScheduleId,tutorialId,id){
	loadPage(url,Util.param([getTutoriaSchedulelId(tutorialScheduleId),getTutorialId(tutorialId)]),id);
}
function deleteTutorialSchedule(commitUrl,tutorialScheduleId,tutorialId,reloadUrl,id){
	if(!confirm('Are you sure to delete this tutorial schedule?')){
		return;
	}
	commitPage(commitUrl,Util.param([getTutoriaSchedulelId(tutorialScheduleId),getTutorialId(tutorialId)]),reloadUrl,Util.param([getTutorialId(tutorialId)]),id);
}
function clickSearch(url,formId,id){
	loadPage(url,Util.serialize(formId),id);
}
function selectCalendar4Schedule(funcParams){
	var date=funcParams.date, url=funcParams.url, id=funcParams.id, tutorialId=funcParams.tutorialId, el=Util.id(id);
	date=date&&date.length==8?(date.substring(4,6)+"/"+date.substring(6,8)+"/"+date.substring(0,4)):Util.dateToStr(Util.toDay());
	Util.data(el,'search.start', date);
	viewTutorial(url,tutorialId,id);
}
function viewTutorial(url,tutorialId,id,start,end){
	var startDate='',endDate='',el=Util.id(id), arr=['search.start','search.end'];
	if(start==undefined){
		startDate=el?Util.data(el,arr[0]):'';
		endDate=el?Util.data(el,arr[1]):'';
	}else{
		var startEl = Util.id(start), endEl=Util.id(end);
		startDate=startEl?startEl.value:'', endDate=endEl?endEl.value:'';
		Util.data(el,arr[0], startDate);
		Util.data(el,arr[1], endDate);
		if(el){
			Util.data(el,'ids',{});
		}
	}
	loadPage(url,Util.param([getTutorialId(tutorialId),{name:'search.start',value:startDate},{name:'search.end',value:endDate}]),id);
}
function clickTutorialSchedule(id, tutorialScheduleId,isCheck,isWorkshop,cost,totalCostId, dateId){
	var el = Util.id(id), dateEl = Util.id(dateId);
	if(el&&dateEl&&dateEl.value){
		var ids = isWorkshop=='true'?{}:(Util.data(el,'ids')||{}), date=dateEl.value, key=tutorialScheduleId+"_"+date;
		if(isCheck){
			ids[key]=[tutorialScheduleId,date,~~cost||0];
		}else{
			ids[key]=null;
			try{delete ids[key];}catch(err){}
		}
		Util.data(el,'ids',ids); 
		calculateTotalCost(ids,totalCostId);
	}
}
function calculateTotalCost(ids,totalCostId){
	var totalCost=0, el=Util.id(totalCostId);
	if(el&&ids){
		for(var k in ids){
			if(ids[k]){
				totalCost+=ids[k][2]||0;
			}
		}
	}
	if(el){
		Util.html(el,totalCost+"");
	}
}
function clickBookNow(url,tutorialId,id){
	var el = Util.id(id), a=[],b=[], ids=el?Util.data(el,'ids'):null;
	if(ids){
		for(var k in ids){
			if(ids[k]){
				a.push(ids[k][0]);
				b.push(ids[k][1]);
			}
		}
		for(var k in ids){
			loadPage(url,Util.param([getTutorialId(tutorialId),{name:'book.ids',value:a.join(',')},{name:'book.dates',value:b.join(',')}]),id);
			break;
		}
	}
}
function unRegisterTutorial(commitUrl,reloadUrl,tutorialId,id){
	commitPage(commitUrl,Util.param([getTutorialId(tutorialId)]),reloadUrl,null,id);
}
function unRegisterTutorialSchedule(commitUrl,reloadUrl,tutorialScheduleId,date,tutorialId,id){
	commitPage(commitUrl,Util.param([getTutoriaSchedulelId(tutorialScheduleId),{name:'scheduleDate',value:date}]),reloadUrl,Util.param([{name:'id',value:tutorialId}]),id);
}
function cancelTutorialSchedule(tutorialScheduleId_date,id,trId,totalCostId){
	var el = Util.id(id), tr=Util.id(trId), trParent=tr?tr.parentNode:null, ids=el?Util.data(el,'ids'):null;
	if(el&&ids&&(tutorialScheduleId_date in ids)){
		ids[tutorialScheduleId_date]=null;
		try{delete ids[tutorialScheduleId_date];}catch(err){}
		Util.data(el,'ids',ids);
		calculateTotalCost(ids,totalCostId);
	}
	if(trParent){
		trParent.removeChild(tr);
	}
}
function clickRegister(commitUrl,reloadUrl,tutorialId,id,pId){
	var el = Util.id(id), a=[],b=[], ids=el?Util.data(el,'ids'):null;
	if(ids){
		for(var k in ids){
			if(ids[k]){
				a.push(ids[k][0]);
				b.push(ids[k][1]);
			}
		}
		commitPage(commitUrl,Util.param([getTutorialId(tutorialId),{name:'register.ids',value:a.join(',')},{name:'register.dates',value:b.join(',')}]),reloadUrl,Util.param([{name:'id',value:tutorialId}]),pId);
	}
}
function highLightCalendar(yyyyMMdd_array,calId){
	var arr=[], el = Util.id(calId);
	if(!yyyyMMdd_array||!calId){return;}
	if(typeof(yyyyMMdd_array)=='string'){
		arr = yyyyMMdd_array.split(',');
	}else if(yyyyMMdd_array instanceof Array){
		arr = yyyyMMdd_array;
	}else{
		return;
	}
	var data = Util.getCalendarData(el), idPrefix = data['idPrefix'];
	for(var i=0;i<arr.length;i++){
		var e = Util.id(idPrefix+arr[i]);
		if(Util.hasClass(e,'calendar-selectabled')){
			Util.addClass(idPrefix+arr[i],'highlight');
		}
	}
}
function loadDaySchedule(url,dateStr,calId){
	Util.ajax(url,Util.param([{name:'search.start',value:dateStr.substring(4,6)+"/"+dateStr.substring(6,8)+"/"+dateStr.substring(0,4)}]),[function(a,b,c){
		var el = Util.id(calId), sd=Util.getCalendarData(el), idPrefix=sd?sd.idPrefix:null;
		if(!el||!sd||!idPrefix){return;}
		var elem=Util.id(sd.idPrefix+dateStr), div=elem?elem.firstChild:null;
		if(div){
			Util.html(div,c.text);
		}
	},ajaxError]);
}
function loadWeekSchedule(url,dateStr,calId){
	Util.ajax(url,Util.param([{name:'search.start',value:dateStr.substring(4,6)+"/"+dateStr.substring(6,8)+"/"+dateStr.substring(0,4)}]),[function(a,b,c){
		var el = Util.id(calId), sd=Util.getCalendarData(el), idPrefix=sd?sd.idPrefix:null;
		if(!el||!sd||!idPrefix){return;}
		var newdiv = document.createElement("div");
		Util.html(newdiv,c.text);
		var arr = Util.childFilter(newdiv,function(el1,arr1){
			if(el1.className.indexOf('week_schedule_')>-1){
				arr1[arr1.length]=el1;
			}
			return true;
		});
		for(var i=0;i<arr.length;i++){
			arr[i].parentNode.removeChild[arr[i]];
		}
		newdiv=null;
		for(var i=0,len=arr.length;i<len;i++){
			var elem = arr[i], dateStr=elem.className.substring('week_schedule_'.length);
			if(!elem.childNodes.length){continue;}
			var contentDiv=Util.id(idPrefix+dateStr).firstChild;
			if(contentDiv){
				while(contentDiv.firstChild){
					contentDiv.removeChild(contentDiv.firstChild);
				}
				contentDiv.appendChild(elem);
			}
		}
	},ajaxError]);
}
function loadMonthSchedule(url,dateStr,calId){
	Util.ajax(url,Util.param([{name:'search.start',value:dateStr.substring(4,6)+"/"+dateStr.substring(6,8)+"/"+dateStr.substring(0,4)}]),[function(a,b,c){
		var el = Util.id(calId), sd=Util.getCalendarData(el), idPrefix=sd?sd.idPrefix:null;
		if(!el||!sd||!idPrefix){return;}
		var newdiv = document.createElement("div");
		Util.html(newdiv,c.text);
		var arr = Util.childFilter(newdiv,function(el1,arr1){
			if(el1.className.indexOf('month_schedule_')>-1){
				arr1[arr1.length]=el1;
			}
			return true;
		});
		for(var i=0;i<arr.length;i++){
			arr[i].parentNode.removeChild[arr[i]];
		}
		newdiv=null;
		for(var i=0,len=arr.length;i<len;i++){
			var elem = arr[i], dateStr=elem.className.substring('month_schedule_'.length);
			if(!elem.childNodes.length){continue;}
			var contentDiv=Util.id(idPrefix+dateStr).firstChild;
			if(contentDiv){
				while(contentDiv.firstChild && contentDiv.firstChild.className.indexOf('notremove')<0){
					contentDiv.removeChild(contentDiv.firstChild);
				}
				contentDiv.appendChild(elem);
			}
		}
	},ajaxError]);
}
function setCalendarHeader(headerId,title){
	try{
		Util.html(headerId,title);
	}catch(err){}
}
function clickDay(date){
	date = date||Util.getCalendarData('mycalendar').date||Util.dateToStr(Util.toDay());
	var calDay = Util.calenderDayView('mycalendar1',{},date);
	loadDaySchedule('dayTutorialSchedule',date,'mycalendar1');
}
function clickWeek(date){
	date = date||Util.getCalendarData('mycalendar').date||Util.dateToStr(Util.toDay());
	Util.calenderWeekView('mycalendar1',{},date);
	loadWeekSchedule('weekTutorialSchedule',date,'mycalendar1');
}
function clickMonth(date){
	date = date||Util.getCalendarData('mycalendar').date||Util.dateToStr(Util.toDay());
	Util.calender('mycalendar1',{cellRender:function(date,i,pid){
		var html='<div style="width:0px;height:0px;position:relative;display:block;overflow:visible;"><div style="position:absolute;left:0;top:0;" class="notremove">{date}</div></div>';
		return {css:'calendarMonthCellCss',html:Util.replace(html,[date.getDate()],'')};
	}},date);
	loadMonthSchedule('monthTutorialSchedule',date,'mycalendar1');
}
function clickRefresh(){
	var date = Util.getCalendarData('mycalendar').date||Util.dateToStr(Util.toDay());
	var el = Util.id('mycalendar1'), type=el&&el.childNodes.length?Util.getAttr(el.childNodes[0],'viewType'):'';
	var map={'month':clickMonth,'week':clickWeek,'day':clickDay};
	if(type in map){map[type](date);}
}
function clickCalendar(params){
	var date = (params||{}).date||Util.dateToStr(Util.toDay());
	var el = Util.id('mycalendar1'), type=el&&el.childNodes.length?Util.getAttr(el.childNodes[0],'viewType'):'';
	var map={'month':clickMonth,'week':clickWeek,'day':clickDay};
	if(type in map){map[type](date);}
}
function clickToDay(){
	var toDay = Util.dateToStr(Util.toDay());
	setTimeout(function(){Util.calender('mycalendar',{click:clickCalendar},toDay);},100);
	var el = Util.id('mycalendar1'), type=el&&el.childNodes.length?Util.getAttr(el.childNodes[0],'viewType'):'';
	var map={'month':clickMonth,'week':clickWeek,'day':clickDay};
	if(type in map){map[type](toDay);}
}
function clickTable(form){
	var parent = form&&form.parentNode&&Util.hasClass(form.parentNode,'display-table')?form.parentNode:null;
	if(parent){
		loadPage(form.action,Util.serialize(form),parent);
	}
	return false;
}
