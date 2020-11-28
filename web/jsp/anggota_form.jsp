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
   <h1><bean:message key="page.formAnggota.title"/></h1>
   </center>
  <tr valign="top"><td>&nbsp;</td></tr>
      <tr valign="top"><td></td></tr>
<body>
	<html:form action = "/anggota/save">
	<html:hidden property="id"/>
 	<br/>
        <center>                    
                <table width="50%" border="3" cellspacing="0" cellpadding="0">
                            
					<tr>
						<td align="left"><bean:message key="page.kodeAnggota.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left"><html:text name="AnggotaForm" property="kode" size="32" styleClass="textbox"/></td>
								
					</tr>
					<tr>
						<td align="left"><bean:message key="page.namaAnggota.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left"><html:text name="AnggotaForm" property="nama" size="32" styleClass="textbox"/>
						</td>
					</tr>
					<tr>
						<td align="left"><bean:message key="page.tglLahir.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left"><html:text property="tglLahir" size="12" readonly="true" styleClass="selectBox"/>&nbsp;
						<html:img page="/images/cal.gif" border="0" title="Calendar" onclick="displayCalendar(document.forms[0].tglLahir,'dd/mm/yyyy',this)"/>&nbsp;(dd/MM/yyyy)
						</td>
					</tr>
					<tr>
						<td align="left"><bean:message key="page.tglGabung.title"/></td>
						<td align="left">: &nbsp;</td>
						<td align="left"><html:text property="tglGabung" size="12" readonly="true" styleClass="selectBox"/>&nbsp;
						<html:img page="/images/cal.gif" border="0" title="Calendar" onclick="displayCalendar(document.forms[0].tglGabung,'dd/mm/yyyy',this)"/>&nbsp;(dd/MM/yyyy)
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
function getAge(birthDate) {
  var now = new Date();
  function isLeap(year) {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
  }
  // days since the birthdate    
  var days = Math.floor((now.getTime() - birthDate.getTime())/1000/60/60/24)-1;
  var age = 0;
  // iterate the years
  for (var y = birthDate.getFullYear(); y <= now.getFullYear(); y++){
    var daysInYear = isLeap(y) ? 366 : 365;
    if (days >= daysInYear){
      days -= daysInYear;
      age++;
      // increment the age only if there are available enough days for the year.
    }
  }
  return age;
}
function valid(){
	if(document.forms[0].kode.value==""){
		alert('Kode harus diisi!!!');
		document.forms[0].kode.focus();
		return false;
	}
	if(document.forms[0].nama.value==""){
		alert('Nama harus diisi!!!');
		document.forms[0].nama.focus();
		return false;
	}
	if(document.forms[0].tglLahir.value=="") {
		alert('Tanggal Lahir harus diisi!!!');
		return false;
	}
	if(document.forms[0].tglGabung.value=="") {
		alert('Tanggal Gabung harus diisi!!!');
		return false;
	}
	if(document.forms[0].nama.value.match(/[^a-zA-Z ]/g)) {
		alert("Nama anggota "+document.forms[0].nama.value+" tidak diterima!!! Hanya boleh huruf & spasi");
		document.forms[0].nama.focus();
		return false;
	}
	var tglLahirStr = document.forms[0].tglLahir.value,
		parts = tglLahirStr.match(/[(\d{2})(\d{2})(\d{4})]*/g),
		dateObj = new Date(parts[4], parts[2]-1, parts[0]); // months 0-based!
	if(getAge(dateObj)<17) {
		alert(document.forms[0].nama.value+" belum cukup umur!!");
		document.forms[0].tglLahir.focus();
		return false;
	}
}
function doBack()
{			
	window.location.href = ('<html:rewrite page="/anggota/list.do" />')
}
</script>
