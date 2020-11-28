<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="com.ams.model.dao.SewaDAO"%>


<html>
	<title><bean:message key="page.index.title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>

<body bgcolor="#D3D3D3">
<h1><bean:message key="page.detailSewa.title"/></h1>
<br/>
   
	
		<html:form action="sewa/detail">
		
		

			<center>
	
    		<table width="100%" border="1" cellspacing="0" cellpadding="0">
       
    			<tr>			
    				<td  bgcolor="#FFB90F"  align="center"><b><bean:message key="page.anggota.title"/></b></td>
    				<td  bgcolor="#FFB90F"  align="center"><b><bean:message key="page.tglSewa.title"/></b></td>
    				<td  bgcolor="#FFB90F"  align="center"><b><bean:message key="page.tglKembali.title"/></b></td>
    			
				</tr>
				
				<tr>
						<td align="center">
							<bean:write name="sewa" property="anggota.kode" scope="request"/> - 
							<bean:write name="sewa" property="anggota.nama" scope="request"/>
						</td>
						<td align="center"><bean:write name="sewa" property="formatedTglSewa" scope="request"/></td>
						<td align="center"><bean:write name="sewa" property="formatedTglKembali" scope="request"/></td>
					
				</tr>
			</table>
			<br/>
			<br/>
			<br/>
			<table width="100%" border="1" cellspacing="0" cellpadding="0">
                               
    			<tr>			
					<td  bgcolor="#FFB90F"  align="center"><b>No</b></td>
    				<td  bgcolor="#FFB90F"  align="center"><b><bean:message key="page.judulBuku.title"/></b></td>
    				<td  bgcolor="#FFB90F"  align="center"><b><bean:message key="page.jumlahSewa.title"/></b></td>
				</tr>	
				
				<!-- start isi table --------------------------------------------------------------------------- -->
				<% int i = 1; %>
				<logic:present name="sewa" property="detailSewaList" scope="request">
				
				            <% 
								try {
									i = Integer.parseInt(request.getParameter("start"))+1;
								}catch(Exception ex) {
									i = 1;
								}
							%>
							
					<logic:iterate id="detailSewa" name="sewa" property="detailSewaList"  type="com.ams.model.DetailSewa" scope="request">	
						<tr>
						  	<td align="center"><%=i%></td>
							<td align="center"><bean:write name="detailSewa" property="buku.judul" scope="page"/></td>
							<td align="center"><bean:write name="detailSewa" property="jumlah" scope="page"/></td>
						</tr>
					<% i++; %>
					</logic:iterate>
			    </logic:present> 
				<!-- end isi table --------------------------------------------------------------------------- -->
				
			</table>
			</center>		
		
			<br/>
			
			
			<center>
				<input type="button" onclick="doback()" value="Kembali" />
			</center>
			</html:form>
		</body>
		
<script type="text/javascript">
function doback(){
			window.location.href = ('<html:rewrite page="/sewa/list.do" />')
	}
</script>
</html>
