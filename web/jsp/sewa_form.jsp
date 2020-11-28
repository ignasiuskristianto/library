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
   <h1><bean:message key="page.formSewa.title"/></h1>
   </center>
  <tr valign="top"><td>&nbsp;</td></tr>
      <tr valign="top"><td></td></tr>
<body>
	<html:form action = "/sewa/save">
	<html:hidden property="id"/>
	<html:hidden property="idBukuList"/>
	<html:hidden property="subaction"/>
 	<br/>
        <center>           
        		<html:errors/>        
        		<br/>
        		<br/> 
                <table width="50%" border="3" cellspacing="0" cellpadding="0">
					<tr>
						<td align="left"><bean:message key="page.anggota.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left">
							<html:hidden property="idAnggota"/>
							<html:text name="SewaForm" property="namaAnggota" size="32" styleClass="textbox" readonly="true"/>
							<a href="#" onclick="window.open('<html:rewrite page="/anggota/popup.do"/>', 'gg', 'titlebar=no, toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbar=no, resizable=no, width=1000, height=600')"><html:img page="/images/popup.gif" border="0" title="Pilih Anggota"/></a>
						</td>
								
					</tr>
					<tr>
						<td align="left"><bean:message key="page.tglSewa.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left"><html:text property="tglSewa" size="12" readonly="true" styleClass="selectBox"/>&nbsp;
						<html:img page="/images/cal.gif" border="0" title="Calendar" onclick="displayCalendar(document.forms[0].tglSewa,'dd/mm/yyyy',this)"/>&nbsp;(dd/MM/yyyy)
						</td>
					</tr>
					<tr>
						<td align="left"><bean:message key="page.tglKembali.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left"><html:text property="tglKembali" size="12" readonly="true" styleClass="selectBox"/>&nbsp;
						<html:img page="/images/cal.gif" border="0" title="Calendar" onclick="displayCalendar(document.forms[0].tglKembali,'dd/mm/yyyy',this)"/>&nbsp;(dd/MM/yyyy)
						</td>
					</tr>
								
				</table>
				<br/>
				<br/>
				<br/>
				<table width="50%" border="3" cellspacing="0" cellpadding="0">
                            
					<tr>
						<td align="left"><bean:message key="page.buku.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left">
							<html:hidden property="idBuku"/>
							<html:text name="SewaForm" property="judulBuku" size="32" styleClass="textbox" readonly="true"/>
							<a href="#" onclick="window.open('<html:rewrite page="/buku/popup.do"/>', 'gg', 'titlebar=no, toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbar=no, resizable=no, width=1000, height=600')"><html:img page="/images/popup.gif" border="0" title="Pilih Buku"/></a>
						</td>
								
					</tr>
					<tr>
						<td align="left"><bean:message key="page.jumlahSewa.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left"><html:text property="jumlahSewa" size="12" styleClass="selectBox"/>&nbsp;
						</td>
					</tr>
								
				</table>
				<br/>
				<html:submit styleClass="button" onclick="return validDetail();" value="Tambah"/>
				<input class = "button" type="button" onclick="setDetail()" value="Bersihkan" />
				<br/>
				<br/>
				<br/>
				<table width="100%" border="1" cellspacing="0" cellpadding="0">
	                               
	    			<tr>			
						<td  bgcolor="#FFB90F"  align="center"><b>No</b></td>
	    				<td  bgcolor="#FFB90F"  align="center"><b><bean:message key="page.judulBuku.title"/></b></td>
	    				<td  bgcolor="#FFB90F"  align="center"><b><bean:message key="page.jumlahSewa.title"/></b></td>
	    				<td  bgcolor="#FFB90F" class="borderLeft text05 padding" align="center"><b>Edit</b></td>
    					<td  bgcolor="#FFB90F" class="borderLeft text05 padding" align="center"><b>Delete</b></td>
					</tr>	
					
					<!-- start isi table --------------------------------------------------------------------------- -->
					<% int i = 1; %>
					<logic:present name="detailSewaList" scope="request">
					
					            <% 
									try {
										i = Integer.parseInt(request.getParameter("start"))+1;
									}catch(Exception ex) {
										i = 1;
									}
								%>
								
						<logic:iterate id="detailSewa" name="detailSewaList" type="com.ams.model.DetailSewa" scope="request">	
							<tr>
							  	<td align="center"><%=i%></td>
								<td align="center"><bean:write name="detailSewa" property="buku.judul" scope="page"/></td>
								<td align="center"><bean:write name="detailSewa" property="jumlah" scope="page"/></td>
								<td><a href="#" onclick="javascript:setDetail('<bean:write name="detailSewa" property="buku.id" scope="page"/>','<bean:write name="detailSewa" property="buku.judul" scope="page"/>','<bean:write name="detailSewa" property="jumlah" scope="page"/>');">edit</a></td>
								<td><a href="#" onclick="javascript:hapusDetail('<bean:write name="detailSewa" property="buku.id" scope="page"/>');">delete</a></td>
							</tr>
						<% i++; %>
						</logic:iterate>
				    </logic:present> 
					<!-- end isi table --------------------------------------------------------------------------- -->
					
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
	if(document.forms[0].idAnggota.value==""){
		alert('Anggota harus dipilih!!!');
		window.open('<html:rewrite page="/buku/popup.do"/>', 'gg', 'titlebar=no, toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbar=no, resizable=no, width=1000, height=600');
		return false;
	}
	if(document.forms[0].tglSewa.value=="") {
		alert('Tanggal Sewa harus diisi!!!');
		return false;
	}
	if(document.forms[0].tglKembali.value=="") {
		alert('Tanggal Kembali harus diisi!!!');
		return false;
	}
	if(new Date(document.forms[0].tglSewa.value)>=new Date(document.forms[0].tglKembali.value)) {
		alert("Tanggal Kembali harus lebih dari Tanggal Sewa");
		return false;
	}
	document.forms[0].subaction.value="addSewa";
}
function validDetail(){
	if(document.forms[0].idBuku.value==""){
		alert('Buku harus dipilih!!!');
		window.open('<html:rewrite page="/buku/popup.do"/>', 'gg', 'titlebar=no, toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbar=no, resizable=no, width=1000, height=600');
		return false;
	}
	if(document.forms[0].jumlahSewa.value==""){
		alert('Jumlah sewa harus diisi!!!');
		document.forms[0].jumlahSewa.focus();
		return false;
	}
	if(document.forms[0].jumlahSewa.value<1){
		alert('Jumlah sewa harus harus angka positif!!!');
		document.forms[0].jumlahSewa.focus();
		return false;
	}
	document.forms[0].subaction.value=document.forms[0].idBukuList.value==""?"addDetail":"updDetail";
}
function hapusDetail(x){
	document.forms[0].idBuku.value=x;
	document.forms[0].subaction.value="delDetail";
	document.forms[0].submit();
}
function setDetail(x="",y="",z=""){
	document.forms[0].idBuku.value=x;
	document.forms[0].idBukuList.value=x;
	document.forms[0].judulBuku.value=y;
	document.forms[0].jumlahSewa.value=z;
}
function doBack()
{			
	window.location.href = ('<html:rewrite page="/sewa/list.do" />')
}
</script>
