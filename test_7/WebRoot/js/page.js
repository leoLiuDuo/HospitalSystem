
function div_page(currentPage,totalPage,pagename){ 

//显示分页栏部分
    var tempStr;//底部的页码
    var tempStrSimple;//顶部页码
    
		if(currentPage==1){
    		tempStr="<li><a>&laquo;</a></li>";
    		tempStrSimple="<li><a>&laquo;</a></li>";
   		}
   		else{
    		tempStr = "<li><a href=\"\\test_7\\Noticeservlet.do?currentPage="+(currentPage-1)+"&page="+pagename+"\">&laquo;</a></li>";
    		tempStrSimple = "<li><a href=\"\\test_7\\Noticeservlet.do?currentPage="+(currentPage-1)+"&page="+pagename+"\" >&laquo;</a></li>";
    		
   		}
		if(currentPage==totalPage-1&&(currentPage-3)>0){
			
    		tempStr+="<li><a href=\"\\test_7\\Noticeservlet.do?currentPage="+(currentPage-3)+"&page="+pagename+"\">"+(currentPage-3)+"</a></li>";
    		
    	}else if(currentPage==totalPage&&(currentPage-3)>0){
    		tempStr+="<li><a href=\"\\test_7\\Noticeservlet.do?currentPage="+(currentPage-4)+"&page="+pagename+"\">"+(currentPage-4)+"</a></li>";
    		tempStr+="<li><a href=\"\\test_7\\Noticeservlet.do?currentPage="+(currentPage-3)+"&page="+pagename+"\">"+(currentPage-3)+"</a></li>";
    	}
    	for(var i=currentPage-2;i<=currentPage+2&&i<=totalPage;i++){
    		if(currentPage==i){
    			tempStr+="<li><a>"+i+"</a></li>";
    		}
    		else if(i>=1){
    			tempStr+="<li><a href=\"\\test_7\\Noticeservlet.do?currentPage="+i+"&page="+pagename+"\">"+i+"</a></li>";
    		}
   		}
		if(currentPage==1&&totalPage>=5){
    		tempStr+="<li><a href=\"\\test_7\\Noticeservlet.do?currentPage="+4+"&page="+pagename+"\">"+4+"</a></li>";
    		tempStr+="<li><a href=\"\\test_7\\Noticeservlet.do?currentPage="+5+"&page="+pagename+"\">"+5+"</a></li>";
    	}else if(currentPage==2&&totalPage>=5){
    		tempStr+="<li><a href=\"\\test_7\\Noticeservlet.do?currentPage="+5+"&page="+pagename+"\">"+5+"</a></li>";
    	}
		
    	if(currentPage==totalPage||totalPage==0){
    		tempStr+="<li><a>&raquo;</a></li>";
    		tempStrSimple+="<li><a>&raquo;</a></li>";
    	}
    	else{
    		tempStr+= "<li><a href=\"\\test_7\\Noticeservlet.do?currentPage="+(currentPage+1)+"&page="+pagename+"\" >&raquo;</a></li>";
    		tempStrSimple+="<li><a href=\"\\test_7\\Noticeservlet.do?currentPage="+(currentPage+1)+"&page="+pagename+"\" >&raquo;</a></li>";
    	}
	
	tempStr+="<li><a>当前页第"+currentPage+"页 共"+totalPage+"页</a></li>";
    document.getElementById("barcon").innerHTML = tempStr;
    document.getElementById("barconSimple").innerHTML = tempStrSimple;
} 
