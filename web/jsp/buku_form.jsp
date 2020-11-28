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
<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
<title><bean:message key="page.index.title"/></title>
   <!--Calendar-->
<script language="javascript" type="text/javascript" src="<html:rewrite page="/js/date.js"/>"></script><!--buat perhitungan tanggal-->
<script language="javascript" type="text/javascript" src="<html:rewrite page="/js/hitung.js"/>"></script><!--buat tampilan-->
<script language="javascript" type="text/javascript" src="<html:rewrite page="/js/dhtmlgoodies_calendar.js"/>"></script><!--buat calender-->
<link type="text/css" rel="stylesheet" href="<html:rewrite page="/js/dhtmlgoodies_calendar.css"/>" media="screen"></link>
<script language="javascript" type="text/javascript" src="<html:rewrite page="/js/Ajax.js"/>"></script><!--buat AJAX-->



</head>
<body bgcolor="#D3D3D3">

    
   <center> 
   <h1><bean:message key="page.formBuku.title"/></h1>
   </center>
  <tr valign="top"><td>&nbsp;</td></tr>
      <tr valign="top"><td></td></tr>
<body>
	<html:form action = "/buku/save">
	<html:hidden property="id"/>
 	<br/>
        <center>                    
                <table width="50%" border="3" cellspacing="0" cellpadding="0">
                            
					<tr>
						<td align="left"><bean:message key="page.kodeBuku.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left"><html:text name="BukuForm" property="kodeBuku" size="32" styleClass="textbox"/></td>
								
					</tr>
					<tr>
						<td align="left"><bean:message key="page.judulBuku.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left"><html:text name="BukuForm" property="judulBuku" size="32" styleClass="textbox"/>
						</td>
					</tr>
					<tr>
						<td align="left"><bean:message key="page.kategoriBuku.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left">
							<html:select property="idKategori">
								<logic:present name="kategoriBukuList" scope="request">
									<html:options collection="kategoriBukuList" property="id" labelProperty="nama" />
			                	</logic:present>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="left"><bean:message key="page.jumlahBuku.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left"><html:text name="BukuForm" property="jumlahBuku" size="32" styleClass="textbox"/>
						</td>
					</tr>			
				</table>
				<br>
					
					<input class = "button" type="button" onclick="doBack()" value="Kembali" />
					<html:submit styleClass="button" onclick="return valid();" value="Simpan"/>
				</center>
			<br>
			</html:form>
	</body>
</html>

<script language="javascript">
function valid(){
	if(document.forms[0].kodeBuku.value==""){
		alert('Kode buku harus diisi!!!');
		document.forms[0].kodeBuku.focus();
		return false;
	}
	if(document.forms[0].judulBuku.value==""){
		alert('Judul buku harus diisi!!!');
		document.forms[0].judulBuku.focus();
		return false;
	}
	if(document.forms[0].judulBuku.value.match(/[^a-zA-Z0-9 \.]/g)) {
		alert("Judul buku "+document.forms[0].judulBuku.value+" tidak diterima!!! Hanya boleh huruf, angka, spasi & titik");
		document.forms[0].judulBuku.focus();
		return false;
	}
	if((!Number.isInteger(parseInt(document.forms[0].jumlahBuku.value))) || (parseInt(document.forms[0].jumlahBuku.value)<1)) {
		alert("Jumlah buku "+document.forms[0].judulBuku.value+" minimal 1!!!");
		document.forms[0].jumlahBuku.focus();
		return false;
	}
}
function doBack()
{			
	window.location.href = ('<html:rewrite page="/buku/list.do" />')
}
</script>
