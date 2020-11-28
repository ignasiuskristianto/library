<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.ams.model.dao.UserDAO"%>

<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title><bean:message key="page.index.title"/></title>
   <!--Calendar-->
<script language="javascript" type="text/javascript" src="<html:rewrite page="/js/date.js"/>"></script><!--buat perhitungan tanggal-->
<script language="javascript" type="text/javascript" src="<html:rewrite page="/js/hitung.js"/>"></script><!--buat tampilan-->
<script language="javascript" type="text/javascript" src="<html:rewrite page="/js/dhtmlgoodies_calendar.js"/>"></script><!--buat calender-->
<link type="text/css" rel="stylesheet" href="<html:rewrite page="/js/dhtmlgoodies_calendar.css"/>" media="screen"></link>
<script language="javascript" type="text/javascript" src="<html:rewrite page="/js/Ajax.js"/>"></script><!--buat AJAX-->
</head>
 	<body>

    
    <H3 align="center"><bean:message key="page.userList.title"/></H3>
   
 	<tr valign="top"><td>&nbsp;</td></tr>
      <tr valign="top"><td></td></tr>
	<body bgcolor="#D3D3D3">
	
	<center>
    <h3><html:errors/></h3>
    </center>

		<html:form action = "/user/list">
			<html:hidden property="start"/>
			<html:hidden property="count"/>
			<html:hidden property="subaction"/>
	    <br>
		
        <center>                    
             <table width="50%" border="3" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left"><bean:message key="page.username.title"/></td>
					<td align="left">: &nbsp;</td>
					<td align="left"><html:text name="UserForm" property="username" size="32" styleClass="textbox"/>
					</td>
				</tr>
							
				</table>
				<br>
					<input type="button" onclick="doSearch()" value="Search" />
				</center>
			<br>
			
		<!-- start table ------------------------------------------------------------------------- -->	
		
		<!-- paging atas ------------------------------------------------------------------------- -->	
		
		<center>	
			<table width="80%" border="0" cellspacing="0" cellpadding="0">			
				<tr>
					<td align="left"><bean:write name="PAGER" scope="request" filter="false"/></td> 
					<td align="center">Goto page : <html:text property="gotoPage" size="4" styleClass="textbox" onchange="gotoPager();" />&nbsp;</td>
    				<td align="right"><bean:write name="PAGERITEM" scope="request" filter="false"/></td> 
				</tr>			
			</table>		
		</center>	
		
		
		<!-- paging atas end ---------------------------------------------------------------------- -->	
				
				<br/>
		<center>
			
          <table width="100%" border="1" cellspacing="0" cellpadding="0">
                               
    			<tr>			
					<td  bgcolor="#FFB90F"  align="center"><b>No</b></td>
    				<td  bgcolor="#FFB90F"  align="center"><b><bean:message key="page.username.title"/></b></td>
    				<td  bgcolor="#FFB90F" class="borderLeft text05 padding" align="center"><b>Detail</b></td>
    				<td  bgcolor="#FFB90F" class="borderLeft text05 padding" align="center"><b>Edit</b></td>
    				<td  bgcolor="#FFB90F" class="borderLeft text05 padding" align="center"><b>Delete</b></td>
				</tr>	
				
				<!-- start isi table --------------------------------------------------------------------------- -->
				<% int i = 1; %>
				<logic:present name="userList" scope="request">
				
				            <% 
								try {
									i = Integer.parseInt(request.getParameter("start"))+1;
								}catch(Exception ex) {
									i = 1;
								}
							%>
							
					<logic:iterate id="user" name="userList"  type="com.ams.model.User" scope="request">	
				  <tr>
				  	<td align="center"><%=i%></td>						
						<td align="center"><bean:write name="user" property="username" scope="page"/></td>
						
						<td align="center"><html:link action="/user/detail" paramId="id" paramName="user" paramProperty="id">detail</html:link></td>					
						<td align="center"><html:link action="/user/form" paramId="id" paramName="user" paramProperty="id">edit</html:link></td>
						<td align="center"><html:link action="/user/delete" paramId="id" paramName="user" paramProperty="id">delete</html:link></td>
					</tr>
					<% i++; %>
					</logic:iterate>
			    </logic:present> 
				<!-- end isi table --------------------------------------------------------------------------- -->
				
			</table>  
		</center>	
		
		<!-- paging bawah ------------------------------------------------------------------------- -->	
		
		
		<!-- paging bawah end --------------------------------------------------------------------------------------- -->	
		
		
		<br>
		<br>
		<center>
			<input class = "button" type="button" onclick="window.location.href=('<html:rewrite page="/index.jsp"/>');" value=<bean:message key ="page.menu.title" />">
			<input class = "button" type="button"  onclick="window.location.href=('<html:rewrite page="/user/form.do"/>');" value=<bean:message key ="page.tambahUser.title" />">
		</center>
		<tr>
			<td>
				<center>
					<input class = "button" type="button" onclick="window.location.href=('<html:rewrite page="/login.do"/>');" value=Logout />
				</center>
			</td> 
		</tr>
	    <!-- end table ------------------------------------------------------------------------- -->	
	    
</html:form>
</body>
<script type="text/javascript">
function gotoPager() {
		if (document.forms[0].gotoPage.value >= 1) {
			if (document.forms[0].count.value == '' || document.forms[0].count.value == 0) document.forms[0].count.value = 5; 
			var a = (document.forms[0].gotoPage.value - 1) * document.forms[0].count.value;
			document.forms[0].start.value = a;
			/* document.forms[0].gotoPage2.value = document.forms[0].gotoPage.value; */
			document.forms[0].submit();
		}
	};

	/* function gotoPager2() {
		if (document.forms[0].gotoPage2.value >= 1) {
			if (document.forms[0].count.value == '' || document.forms[0].count.value == 0) document.forms[0].count.value = 5; 
			var a = (document.forms[0].gotoPage2.value - 1) * document.forms[0].count.value;
			document.forms[0].start.value = a;
			document.forms[0].gotoPage.value = document.forms[0].gotoPage2.value;
			document.forms[0].submit();
		}
	}; */

	function page(start,count) {
    		document.forms[0].gotoPage.value = '';
    		document.forms[0].start.value = start;
    		document.forms[0].count.value = count;
    		document.forms[0].submit();
    };

    function doSearch(){
    	document.forms[0].subaction.value = 'search';	
			document.forms[0].submit();
	}
    
</script>
</html>