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

