function nulis(a){
	var tulis = "";
	var koma = a.indexOf(".");
	if(koma == -1){
		var string1 = a;
		var string2 = "";
	}
	else{
		var string1 = a.substring(0, koma);
		var string2 = a.substring(koma, a.length);
	}
	for(i=0;i<string1.length;i++){
		if((string1.length-i)%3==0 && i!=0)
		{
			tulis+=",";
		}
		tulis+=string1.charAt(i);
	}
	tulis = tulis + string2;
	return tulis;
}

function angka(a){
	var tulis="";
	for(i=0;i<a.length;i++){
		if(a.charAt(i)==",")
		{
			continue;
		}
		tulis+=a.charAt(i);
	}
	return eval(tulis);
}

function cek(a){
	var tulis="";
	for(i=0;i<a.length;i++){
		if(a.charAt(i)==",")
		{
			continue;
		}
		tulis+=a.charAt(i);
	}
	return tulis;
}

function IsNumeric(strString)
//  check for valid numeric strings	
{
	var strValidChars = "0123456789,";
	var strChar;
	var blnResult = true;

	if (strString.length == 0) return false;

	//test strString consists of valid characters listed above
	for (i = 0; i < strString.length && blnResult == true; i++)
	{
		strChar = strString.charAt(i);
		if (strValidChars.indexOf(strChar) == -1)
		{
			blnResult = false;
		}
	}
	return blnResult;
}

function HideContent(d) {

	document.getElementById(d).style.display = "none";
	
}

function ShowContent(d) {
	
	document.getElementById(d).style.display = "block";
	
}

function ReverseDisplay(d) {

	if(document.getElementById(d).style.display == "none") { 
		document.getElementById(d).style.display = "block"; 
	}
	else { 
		document.getElementById(d).style.display = "none"; 
	}
	
}

function colaps(name, showMode){
	alert('a: '+showMode);
	cells = document.getElementsByName(name);

	//mode = btn.checked ? showMode : 'none';

	for(j = 0; j < cells.length; j++) cells[j].style.display = showMode;
	alert('l');
}

function bukaTutup(bShow, sec, nCol)
{
  var r;
  sec = document.getElementById(sec);
  if (sec && nCol < sec.rows[0].cells.length) {
    for (r = 0; r < sec.rows.length; ++r) {
      sec.rows[r].cells[nCol].style.display = bShow ? '' : 'none';
    }
  }
}


function cekDigit(nilai,digit){
	hitung = 0, flag = 0;
	for(i=0; i<nilai.length; i++){
		if(nilai.charAt(i)==".") flag = 1;
		if(flag==1)hitung++; 				
	}
	return hitung-1;
}

function splitName(name,input,msg){

	var formname = document.forms[0].elements[name];
	var fullName = trim(formname.value);
	
	var fullNameResult = fullName.split(" ");
	for(i = 0; i < fullNameResult.length; i++){
		for(j = 0; j < input.length; j++){
			if(ValidateName(fullNameResult[i])){
				alert( 'Name only containing letter' );
				return true;
			}
			if (fullNameResult[i].toLowerCase() == 'i' && i > 0) {
				alert( msg+' must not contain title or abbreviation. Like I ');
				return true;
			}
			if(fullNameResult[i].toLowerCase()==input[j].toLowerCase()) {
				alert( msg+' must not contain title or abbreviation. Like '+fullNameResult[i] );
				return true;
			}
		}
	}
	
}

function ValidateEmail(object_value)
{		
	email_pattern=new RegExp("^[a-zA-Z0-9]+(([_]|\\.|-)?[a-zA-Z0-9])*@([a-zA-Z0-9]+([_]|-?[a-zA-Z0-9])*(\\.))+[a-zA-Z]{2,4}$");
	return object_value.search(email_pattern);
}

function ValidateName(object_value)
{		
	name_pattern=new RegExp("^[a-zA-Z\\s]+(\\s)*$");
	return object_value.search(name_pattern);
}

function NameValidate(element){

    var nameString = element.value;
    var cleanName = '';
    
    for(var i = 0; i < nameString.length; i++)
    {       
        if(nameString.charAt(0) != ' '){
	        if(((nameString.charAt(i) != ' ' || ((nameString.charAt(i-1) != ' ' && nameString.charAt(i-2) != ' ') && nameString.charAt(1) != ' ')) || nameString.charAt(0).toLowerCase() == 'i') && !ValidateName(nameString.charAt(i))) {
	        	cleanName += nameString.charAt(i);
	        }
        }	
    }
    
    if (cleanName.length != 0){
    	element.value = cleanName;
    }else{
    	element.value = '';
    }

}

function isNum(e){	//angka only
	var key = window.event ? e.keyCode : e.which;
	 
	if (
		(key > 47 && key < 58) || key == 8 || key == 0
		) return true; 
		return false;
}

function trim(sString){
	while (sString.substring(0,1) == ' ')
	{
		sString = sString.substring(1, sString.length);
	}
	while (sString.substring(sString.length-1, sString.length) == ' ')
	{
		sString = sString.substring(0,sString.length-1);
	}
return sString;
}