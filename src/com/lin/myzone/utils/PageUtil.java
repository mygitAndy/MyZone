package com.lin.myzone.utils;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

import com.lin.myzone.beans.PageInfo;



public class PageUtil {
	
	public static void pageInfoPreSet(PageInfo pageInfo,HttpServletRequest request){
		 if (request.getParameter("pageIndex")!=null && !request.getParameter("pageIndex").equals("")) {
			 int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			 pageInfo.setPageIndex(pageIndex);
		 }
	}
	
	public static void pageInfoSet(PageInfo pageInfo,HttpServletRequest request) {
		 Integer pageIndex = pageInfo.getPageIndex();
		 Integer pageMax = pageInfo.getPageMax();
		 Integer pageTotal = pageInfo.getPageTotal();	
		 Integer firstpage = 1;
		 Integer prepage = pageIndex - 1;
		 Integer nextPage = pageIndex +1;
		 Integer lastpage = pageInfo.getPageNum();  
		 lastpage = (lastpage == 0)?1:lastpage;
		 
		 if (pageTotal <= pageMax) {
				pageIndex = 1;
		 }
		 
		 StringBuffer sb = new StringBuffer();
		 sb.append("<table width='100%' border='0' cellpadding='0' cellspacing='0'>");
		 sb.append("<tr>");
		 sb.append("<td>").append("&nbsp;&nbsp;").append("</td>");
		 sb.append("<td style='float: right;'>");
		
		 
		 if (pageIndex == 1) {
			
			 sb.append("<input type='button' value='上一页'  class='buttonDisableFirst'/>");
		 }else{
			 sb.append("<input type='button' value='上一页'   class='buttonFirst' onclick=\"mySubmit('").append(prepage).append("')\">"); 
		 }
		
		 
		 if(lastpage<=5){
			 for(int i=1;i<lastpage;i++){
				 if(pageIndex==i){
					 sb.append("<input class='buttonSelect  buttonClick'  type='button' value='"+i+"'  onclick=\"mySubmit('").append(i).append("')\">");
				 }else{
					 sb.append("<input type='button' value='"+i+"' class='buttonClick'  onclick=\"mySubmit('").append(i).append("')\">");
				 }
			 }
			 if(pageIndex==lastpage){
				 sb.append("<input id='lastpage' class='buttonSelect buttonClick' type='button' value='"+lastpage+"'  onclick=\"mySubmit('").append(lastpage).append("')\">");
			 }else{
				 sb.append("<input id='lastpage' type='button' value='"+lastpage+"' class='buttonClick'  onclick=\"mySubmit('").append(lastpage).append("')\">");
			 }
		 }else{
			 if(pageIndex<=3){
				 for(int i=1;i<=4;i++){
					 if(pageIndex==i){
						 sb.append("<input class='buttonSelect buttonClick' type='button' value='"+i+"'  onclick=\"mySubmit('").append(i).append("')\">");
					 }else{
						 sb.append("<input type='button' value='"+i+"' class='buttonClick'  onclick=\"mySubmit('").append(i).append("')\">");
					 }
				 }
				 sb.append("<span style='margin-left:3px;'>...</span>");
				 sb.append("<input id='lastpage' type='button' value='"+lastpage+"' class='buttonClick'  onclick=\"mySubmit('").append(lastpage).append("')\">");
			 }else if(pageIndex>3&&(lastpage-pageIndex)>=3){
					 sb.append("<input type='button' value='1' class='buttonClick'  onclick=\"mySubmit('").append(1).append("')\">");
					 sb.append("<span style='margin-left:3px;'>...</span>");
					 sb.append("<input type='button' value='"+prepage+"' class='buttonClick' onclick=\"mySubmit('").append(prepage).append("')\">");
					 sb.append("<input class='buttonSelect buttonClick'  type='button' value='"+pageIndex+"'   onclick=\"mySubmit('").append(pageIndex).append("')\">");
					 sb.append("<input type='button' value='"+nextPage+"' class='buttonClick' onclick=\"mySubmit('").append(nextPage).append("')\">");
					 sb.append("<span style='margin-left:3px;'>...</span>");
					 sb.append("<input type='button'  id='lastpage'　 value='"+lastpage+"' class='buttonClick'  onclick=\"mySubmit('").append(lastpage).append("')\">");
			 }else{
					 sb.append("<input type='button' value='1' class='buttonClick'  onclick=\"mySubmit('").append(1).append("')\">");
					 sb.append("<span style='margin-left:3px;'>...</span>");
					 for(int i=(lastpage-3);i<lastpage;i++){
						 if(pageIndex==i){
							 sb.append("<input class='buttonSelect' type='button' value='"+i+"' class='buttonClick'  onclick=\"mySubmit('").append(i).append("')\">");
						 }else{
							 sb.append("<input  type='button' value='"+i+"' class='buttonClick'  onclick=\"mySubmit('").append(i).append("')\">");
						 } 
					 }  
					 if(pageIndex==lastpage){
						 sb.append("<input id='lastpage' class='buttonSelect buttonClick' type='button' value='"+lastpage+"'   onclick=\"mySubmit('").append(lastpage).append("')\">");
					 }else{
						 sb.append("<input id='lastpage' type='button' value='"+lastpage+"' class='buttonClick'  onclick=\"mySubmit('").append(lastpage).append("')\">");
					 }
				 }	 
			 
		 } 
		 if (pageIndex >= lastpage) {
			 sb.append("<input type='button' value='下一页' class='buttonDisableLast'/>");
		 }else{
			 sb.append("<input type='button' value='下一页' class='buttonLast' onclick=\"mySubmit('").append(nextPage).append("')\">");
		 }
		 sb.append("共"+pageIndex+"/"+lastpage+"页 &nbsp;&nbsp;共"+pageTotal+"条数据 &nbsp;&nbsp;到第<input type='text' onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" onafterpaste=\"this.value=this.value.replace(/\\D/g,'')\"" +
		 		" id='pageIndexValue'  style='width:30px;'> 页<input id='goPageIndexValue' type='button' value='Go' class='buttonClick'  onclick=\"myGoSubmit()\">");
		 sb.append("</td>");
		 sb.append("<td>").append("&nbsp;&nbsp;").append("</td>");
		 sb.append("</tr>");
		 sb.append("</table>");
		
		 sb.append("<script type=\"text/javascript\">");
		 //sb.append("debugger;");
		 sb.append("function mySubmit(page){");
		 sb.append("document.getElementById('pageIndex').value=page;");
		 sb.append("document.cFM.submit();");
		 sb.append("}");
		 sb.append("function myGoSubmit(){");
		 sb.append("var pageIndex=document.getElementById('pageIndexValue').value.trim();var lastIndex=document.getElementById('lastpage').value;");
		 sb.append("if(parseInt(pageIndex)<1){");
		 sb.append("document.getElementById('pageIndex').value=1;");
		 sb.append("}else if(parseInt(pageIndex)>parseInt(lastIndex)){");
		 sb.append("document.getElementById('pageIndex').value=lastIndex;");
		 sb.append("}else{");
		 sb.append("document.getElementById('pageIndex').value=pageIndex;");
		 sb.append("}");
		 sb.append("document.cFM.submit();");
		 sb.append("}");
		 sb.append("</script>");
		 request.getSession().setAttribute("pageIndex", pageIndex);
		 request.setAttribute("page", sb.toString());
		 
		 
	}
	
	
	
	public static void pageInfoSet1(PageInfo pageInfo,HttpServletRequest request) {
		 Integer pageIndex = pageInfo.getPageIndex();
		 Integer pageMax = pageInfo.getPageMax();
		 Integer pageTotal = pageInfo.getPageTotal();	
		 Integer firstpage = 1;
		 Integer prepage = pageIndex - 1;
		 Integer nextPage = pageIndex +1;
		 Integer lastpage = pageInfo.getPageNum();  
		 lastpage = (lastpage == 0)?1:lastpage;
		 
		 if (pageTotal <= pageMax) {
				pageIndex = 1;
		 }
		 
		 String prevState = "";   //上一页的状态,如果为disable，表示不可以点击
		 String nextState = "";   //下一页的状态 ,如果为disable，表示不可以点击
		 String curContent = pageIndex +"/"+lastpage;   //当前页内容，  1/4  表示  共4页，当前在第一页
		 
		 
		 if (pageIndex <= 1) {
			 prevState = "disable";
		 }
		 if (pageIndex >= lastpage) {
			nextState = "disable";
		 }
		 request.setAttribute("pageIndex", pageIndex);
		 request.setAttribute("prevState", prevState);
		 request.setAttribute("nextState", nextState);
		 request.setAttribute("curContent", curContent);
		 request.setAttribute("lastPage", lastpage);
	}
	
	
	
}
