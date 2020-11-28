<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<html>
<head>
	<title><bean:message key="page.index.title"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#D3D3D3">
	<center>
		<table width="50%" border="0" cellspacing="0" cellpadding="0" align ="center">
			<tr align="center">
			<td height="100" valign="top" style="font-size:50px;"><bean:message key="page.login.title"/></td>
			</tr>
			<html:form action="/login" focus="username">
				<tr valign="top"><td><html:errors/></td></tr>
				<tr align = "center">
					<td><bean:message key="page.username.title" /></td>
					<td>:</td>
					<td><html:text name="LoginForm" property="username" size="25"></html:text></td>
				</tr>
				<tr align = "center">
					<td><bean:message key="page.password.title" /></td>
					<td>:</td>
					<td><html:password name ="LoginForm" property="password" size="25"></html:password></td>
				</tr>	
				<tr>
					<td align = "center">
					<html:submit styleClass="button" onclick="return submitForm();" value= "login"><bean:message key="page.login.title"/></html:submit></td>
				</tr>
			</html:form>
		</table>
	</center>

</body>


<script language="javascript">
function submitForm(){
	if(document.forms[0].username.value == "") {
		alert("Username User Harus diisi!!");
		document.forms[0].username.focus();
		return false;
	}
	if(document.forms[0].password.value == "") {
		alert("Password Harus diisi!!");
		document.forms[0].password.focus();
		return false;
	}
}
</script>
</html>