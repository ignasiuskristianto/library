/*
	validasi input hanya angka [0-9]
	tidak mengandung spesial karakter ,./?<>
*/
function isNumber(input) {
	var numericExpression = /^[0-9]+$/;
	
	if(input.match(numericExpression)){
		return true;
	} 
	return false;
}

/*
	validasi alphabet 
*/

function isAlphabet(input){
	var alphaExp = /^[a-zA-Z ]+$/;
	
	if(input.match(alphaExp)){
		return true;
	}
	
	return false;
}

/*
	validasi alphanumeric 
*/

function isAlphanumeric(input){
	var alphaExp = /^[0-9a-zA-Z]+$/;
	
	if(input.match(alphaExp)){
		return true;
	}
	
	return false;
}

/*
	validasi email 
*/
function emailValidator(input){
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	
	if(input.match(emailExp)){
		return true;
	}
	
	return false;
}

/*
	validasi NA 
*/
function cekNA(field){
	var hasilTrim = trim(field.value);
	var cek = hasilTrim.toLowerCase();
	
	if(cek=="na" || cek=="n.a" || cek=="n/a"){
		return false;
	}
	
	return true;
}

function cekAddressNA(field){
	var cek1 = 0;
	for(i = 0; i < field.length; i++){
		
		var cek = trim(new String(field[i])).toLowerCase();
		if(cek=="na" || cek=="n.a" || cek=="n/a" || cek==""){
			cek1 = 1;
		}
		
	}
	
	if(cek1 > 0){
		return false;
	}
}

/*
	validasi DOB 
*/
function cekDOB(field){
	var DOB = new Date(field.value);
	
	if(DOB.getFullYear() < 1900){
		return false;
	}
}

/*
	validasi NPWP
*/
function isValidNPWP(field){

	if (field.value.length > 0) {
		if(!isNumber(field.value) || eval(field.value.length) > 15 || eval(field.value.length) < 14){
			alert(field.title+' must contain only numbers [0-9] and has 14/15 digits of number');
			return false;
		}
	}
	return true;
}

/*
	validasi nama
	tidak mengandung title atau singkatan
*/
function isValidName(field){
	var myInput = "A;B;C;D;E;F;G;H;J;K;L;M;N;O;P;Q;R;S;T;U;V;W;X;Y;Z;Dr;Tn;" +
					"Ny;NA;MBA;Dra;Drs;SE;ST;JD;BP;BPK;Mr;Mrs;NN;ALM;HJ;SH;MM;Ir;Drg;SIP;BS;MA";
	var myInputResult = myInput.split(";");
	var myFullName = field.value;
	var myFullNameResult = myFullName.split(" ");
	
	for(i = 0; i < myFullNameResult.length; i++){
		for(j = 0; j < myInputResult.length; j++){
			if(myFullNameResult[i].toLowerCase()==myInputResult[j].toLowerCase()) {
				return false;
			}
		}
	}
	
	return true;
}

/*
	validasi required	 
*/


function validateForm(form, subaction){
	
	var errorMsg = "";
	var eLen = form.elements.length;
	
	for (var i=0; i<eLen; i++) {
		
		/*
			validasi full name 
		*/
		if(form.elements[i].name == "fullName" && form.elements[i].value.length == 0){ 
			 errorMsg += form.elements[i].title+" is required.\n";
		}
		
		if(form.elements[i].name == "fullName" && form.elements[i].value.length > 0 && !isAlphabet(form.elements[i].value)){ 
			 errorMsg += form.elements[i].title+" can only contain letter.\n";
		}
		
		if(form.elements[i].name == "fullName" && form.elements[i].value.length > 0 && isAlphabet(form.elements[i].value) && !isValidName(form.elements[i])){ 
			 errorMsg += form.elements[i].title+" must not contain title or abbreviation.\n";
		}
		
		/*
			validasi place of birth 
		*/
		if(form.elements[i].name == "placeOfBirth" && form.elements[i].value.length == 0){ 
			 errorMsg += form.elements[i].title+" is required.\n";
		}
		
		/*
			validasi date of birth 
		*/
		if(form.elements[i].name == "dateOfBirth" && form.elements[i].value.length == 0){ 
			 errorMsg += form.elements[i].title+" is required.\n";
		}
		
		/*
			validasi mother maiden name 
		*/
		if(form.elements[i].name == "motherMaidenName" && form.elements[i].value.length == 0){ 
			 errorMsg += form.elements[i].title+" is required.\n";
		}
		
		if(form.elements[i].name == "motherMaidenName" && form.elements[i].value.length > 0 && !isAlphabet(form.elements[i].value)){ 
			 errorMsg += form.elements[i].title+" can only contain letter.\n";
		}
		
		if(form.elements[i].name == "motherMaidenName" && form.elements[i].value.length > 0 && isAlphabet(form.elements[i].value) && !isValidName(form.elements[i])){ 
			 errorMsg += form.elements[i].title+" must not contain title or abbreviation.\n";
		}
		
		/*
			validasi id number 
		*/
		if(form.elements[i].name == "idNumber" && form.elements[i].value.length == 0){ 
			 errorMsg += form.elements[i].title+" is required.\n";
		}
		
		/*
			validasi  id exp date
		*/
		if(form.elements[i].name == "idExpiredDate" && form.elements[i].value.length == 0){ 
			 errorMsg += form.elements[i].title+" is required.\n";
		}
		
		/*
			validasi number of dependent 
		*/
		if(form.elements[i].name == "numberOfDependent" && form.elements[i].value.length == 0){ 
			 errorMsg += form.elements[i].title+" is required.\n";
		}
		
		if(form.elements[i].name == "numberOfDependent" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
			 errorMsg += form.elements[i].title+" must be an integer.\n";
		}
		
		if(subaction=="ADDRESS"){
		
			/*
				validasi address1 
			*/
			if(form.elements[i].name == "address1" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			/*
				validasi rt 
			*/
			if(form.elements[i].name == "rt" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			if(form.elements[i].name == "rt" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
				errorMsg += form.elements[i].title+" must be an integer.\n";
			}
			
			/*
				validasi rw 
			*/
			if(form.elements[i].name == "rw" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			if(form.elements[i].name == "rw" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
				errorMsg += form.elements[i].title+" must be an integer.\n";
			}
			
			/*
				validasi kelurahan 
			*/
			if(form.elements[i].name == "kelurahan" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			/*
				validasi kecamatan 
			*/
			if(form.elements[i].name == "kecamatan" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			/*
				validasi city 
			*/
			if(form.elements[i].name == "city" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			/*
				validasi postal code			 
			*/
			if(form.elements[i].name == "postalCode" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			if(form.elements[i].name == "postalCode" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
				errorMsg += form.elements[i].title+" must be an integer.\n";
			}
			
			/*
				validasi phone number1 
			*/
			if(form.elements[i].name == "phoneNumber1" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			/*
				 validasi email
			*/
			if(form.elements[i].name == "email" && form.elements[i].value.length > 0 && !emailValidator(form.elements[i].value)){ 
				errorMsg += form.elements[i].title+" is not correct.\n";
			}
			
			/*
				validasi letter address1 
			*/
			if(form.elements[i].name == "letterAddress1" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			/*
				validasi letter rt 
			*/
			if(form.elements[i].name == "letterRt" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			if(form.elements[i].name == "letterRt" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
				errorMsg += form.elements[i].title+" must be an integer.\n";
			}
			
			/*
				validasi letter rw 
			*/
			if(form.elements[i].name == "letterRw" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			if(form.elements[i].name == "letterRw" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
				errorMsg += form.elements[i].title+" must be an integer.\n";
			}
			
			/*
				validasi letter kelurahan 
			*/
			if(form.elements[i].name == "letterKelurahan" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			/*
				validasi letter kecamatan 
			*/
			if(form.elements[i].name == "letterKecamatan" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			/*
				validasi letter city 
			*/
			if(form.elements[i].name == "letterCity" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			/*
			 	validasi letter postal code
			*/
			if(form.elements[i].name == "letterPostalCode" && form.elements[i].value.length == 0){ 
				errorMsg += form.elements[i].title+" is required.\n";
			}
			
			if(form.elements[i].name == "letterPostalCode" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
				errorMsg += form.elements[i].title+" must be an integer.\n";
			}
			
			/*
			if (subaction2=="SAEDIT"){
			
				if (form.elements[i].name == "address1" && !cekNA(form.elements[i])){
					errorMsg += form.elements[i].title+" must not like NA, N.A, N/A.\n";
				}
			
				if (form.elements[i].name == "city" && !cekNA(form.elements[i])){
					errorMsg += form.elements[i].title+" must not like NA, N.A, N/A.\n";
				}
				
				if (form.elements[i].name == "phoneNumber1" && !cekNA(form.elements[i])){
					errorMsg += form.elements[i].title+" must not like NA, N.A, N/A.\n";
				}
				
				if (form.elements[i].name == "kelurahan" && !cekNA(form.elements[i])){
					errorMsg += form.elements[i].title+" must not like NA, N.A, N/A.\n";
				}
				
				if (form.elements[i].name == "kecamatan" && !cekNA(form.elements[i])){
					errorMsg += form.elements[i].title+" must not like NA, N.A, N/A.\n";
				}
				
				if((form.elements[i].name == "postalCode" && form.elements[i].value.length > 5) || (form.elements[i].name == "postalCode" && form.elements[i].value.length < 5)){
					errorMsg += form.elements[i].title+" must be 5 digits of number.\n";
				}
				
			}
			*/
			
		}
		
		/*
			validasi saedit
		*/
		/*
		if(subaction2=="SAEDIT"){
			
			if (form.elements[i].name == "motherMaidenName" && !cekNA(form.elements[i])){
				errorMsg += form.elements[i].title+" must not like NA, N.A, N/A.\n";
			}
			
			if (form.elements[i].name == "idNumber" && !cekNA(form.elements[i])){
				errorMsg += form.elements[i].title+" must not like NA, N.A, N/A.\n";
			}
			
			if (form.elements[i].name == "placeOfBirth" && !cekNA(form.elements[i])){
				errorMsg += form.elements[i].title+" must not like NA, N.A, N/A.\n";
			}
			
			if(form.elements[i-2].name == "spouseFullName" && form.elements[i-2].value.length > 0 && !cekNA(form.elements[i])){ 
				errorMsg += form.elements[i].title+" must not like NA, N.A, N/A.\n";
			}
			
			if (form.elements[i].name == "dateOfBirth" && !cekDOB(form.elements[i])){
				errorMsg += form.elements[i].title+" year must more than 1900.\n";
			}
			
			if (form.elements[i].name == "spouseDateOfBirth" && !cekDOB(form.elements[i])){
				errorMsg += form.elements[i].title+" year must more than 1900.\n";
			}
			
			if (!cekAddressNA(address)){
				errorMsg += "Address in Personal Address must not like NA, N.A, N/A or NULL.\n";
			}
			
			if (!cekAddressNA(kelurahan)){
				errorMsg += "Kelurahan in Personal Address must not like NA, N.A, N/A or NULL.\n";
			}
			
			if (!cekAddressNA(kecamatan)){
				errorMsg += "Kecamatan in Personal Address must not like NA, N.A, N/A or NULL.\n";
			}
			
			if (!cekAddressNA(city)){
				errorMsg += "City in Personal Address must not like NA, N.A, N/A or NULL.\n";
			}
			
			if (!cekAddressNA(phoneNumber)){
				errorMsg += "Phone Number in Personal Address must not like NA, N.A, N/A or NULL.\n";
			}
			
			for(j = 0; j < postalCode.length; j++){
				if(postalCode[j].length > 5 || postalCode[j].length < 5){
					errorMsg += "Postal Code in Personal Address must be 5 digits of number.\n";
				}
			}
			
		}
		*/
		/*
			validasi spouse full name 
		*/
		if(form.elements[i].name == "spouseFullName" && form.elements[i].value.length > 0 && !isAlphabet(form.elements[i].value)){ 
			 errorMsg += form.elements[i].title+" can only contain letter.\n";
		}
		
		if(form.elements[i].name == "spouseFullName" && form.elements[i].value.length > 0 && isAlphabet(form.elements[i].value) && !isValidName(form.elements[i])){ 
			 errorMsg += form.elements[i].title+" must not contain title or abbreviation.\n";
		}
		
		/*
			spouse company rt 
		*/
		if(form.elements[i].name == "spouseCompanyRt" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
			errorMsg += form.elements[i].title+" must be an integer.\n";
		}
		
		/*
			spouse company rw 
		*/
		if(form.elements[i].name == "spouseCompanyRw" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
			errorMsg += form.elements[i].title+" must be an integer.\n";
		}
		
		/*
			spouse company postal code 
		*/
		if(form.elements[i].name == "spouseCompanyPostalCode" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
			errorMsg += form.elements[i].title+" must be an integer.\n";
		}
		
		/*
			spouse job lenght 
		*/
		if(form.elements[i].name == "spouseJobLength" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
			errorMsg += form.elements[i].title+" must be an integer.\n";
		}
		
		/*
			validasi company name 
		*/
		if(form.elements[i].name == "companyName" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		
		/*
			validasi company address1
		*/
		if(form.elements[i].name == "companyAddress1" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		
		/*
			validasi company rt  
		*/
		if(form.elements[i].name == "companyRt" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		
		if(form.elements[i].name == "companyRt" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
			errorMsg += form.elements[i].title+" must be an integer.\n";
		}
		
		/*
			validasi company rw 
		*/
		if(form.elements[i].name == "companyRw" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		
		if(form.elements[i].name == "companyRw" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
			errorMsg += form.elements[i].title+" must be an integer.\n";
		}
		
		/*
			validasi company kelurahan 
		*/
		if(form.elements[i].name == "companyKelurahan" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		
		/*
			validasi company kecamatan 
		*/
		if(form.elements[i].name == "companyKecamatan" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		
		/*
			validasi company city 
		*/
		if(form.elements[i].name == "companyCity" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		
		/*
			validasi company postal code 
		*/
		if(form.elements[i].name == "companyPostalCode" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		
		if(form.elements[i].name == "companyPostalCode" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
			errorMsg += form.elements[i].title+" must be an integer.\n";
		}
		
		/*
			validasi company phone number1 
		*/
		if(form.elements[i].name == "companyPhoneNumber1" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		
		/*
			validasi company ext 1 
		*/
		if(form.elements[i].name == "companyPhoneExt1" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		 
		if(form.elements[i].name == "companyPhoneExt1" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
			errorMsg += form.elements[i].title+" must be an integer.\n";
		}
		
		/*
			validasi company ext 2 
		*/
		if(form.elements[i].name == "companyPhoneExt2" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
			errorMsg += form.elements[i].title+" must be an integer.\n";
		}
		
		/*
			validasi job rank 
		*/
		if(form.elements[i].name == "jobRank" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		
		/*
			validasi job length 
		*/
		if(form.elements[i].name == "jobLength" && form.elements[i].value.length == 0){ 
			errorMsg += form.elements[i].title+" is required.\n";
		}
		
		if(form.elements[i].name == "jobLength" && form.elements[i].value.length > 0 && !isNumber(form.elements[i].value)){ 
			errorMsg += form.elements[i].title+" must be an integer.\n";
		}
		
	}
	
	if (errorMsg!=""){
		alert(errorMsg);
		return false;
	}
	
	
	return true;
}
