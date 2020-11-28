function resetPage() {
	document.forms[0].gotoPage.value = '';
	document.forms[0].gotoPage2.value = '';
	document.forms[0].start.value = '';
	document.forms[0].count.value = '';
	document.forms[0].orderBy.value = '';
	document.forms[0].ascDesc.value = '';
	document.forms[0].subaction.value = '';
};

function page(start,count) {
	document.forms[0].gotoPage.value = '';
	document.forms[0].start.value = start;
	document.forms[0].count.value = count;
	submitPageSort();
};

function gotoPager(x) {
	if (x >= 1) {
		if (document.forms[0].count.value == '' || document.forms[0].count.value == 0) document.forms[0].count.value = 50; 
		var a = (x - 1) * document.forms[0].count.value;
		document.forms[0].start.value = a;
		document.forms[0].gotoPage.value = x;
		document.forms[0].gotoPage2.value = x;
		submitPageSort();
	}
};

function sort(orderBy) {
	if ( document.forms[0].ascDesc.value=='' ) {
		document.forms[0].ascDesc.value='desc';

	} else if (document.forms[0].ascDesc.value=='desc') {
		document.forms[0].ascDesc.value='asc';
	} else {
		document.forms[0].ascDesc.value='desc';
	}
	if ( document.forms[0].orderBy.value != orderBy )
	{
		document.forms[0].orderBy.value = orderBy;
		document.forms[0].ascDesc.value='asc';
	}
	submitPageSort();
};


function submitForm() {
	resetPage();
	document.forms[0].subaction.value = 'search';
	document.forms[0].submit();
}

function submitPageSort(){
	document.forms[0].subaction.value = 'search';
	document.forms[0].submit();
}

function gowait(form) {
 	var t;
 	t = true;
 	//eval(t = validateRopForm(form));
 	if (t) {
 		document.getElementById("main").style.visibility="hidden";
   		document.getElementById("wait").style.visibility="visible";
   		window.setTimeout('showProgress()', 400);
 	}
 }
 
function showProgress(){ 
   var wg = document.getElementById("progressbar");
   wg.src=wg.src;
}

function arrow(url){

	if(document.forms[0].orderBy.value != '' && document.forms[0].ascDesc.value != ''){
		
		var oImg = document.createElement("img");
		
		if(document.forms[0].ascDesc.value=='desc') oImg.setAttribute('src', url+'sort_terang.gif');
		else oImg.setAttribute('src', url+'sort2_terang.gif');
		
		oImg.setAttribute('align', 'absmiddle');
		oImg.setAttribute('hspace', '5');
		
		document.getElementById(document.forms[0].orderBy.value).appendChild(oImg);
	}
	
}

function clearDate(x,y){
	x.value = "";
	y.value = "";
}

function disableSubmit(x) {
	document.getElementById("submitSave").disabled = "true";
	document.forms[0].subaction.value=x;	
	document.forms[0].submit();
}

function disableApproval(x) {
	document.getElementById("approvalSave").disabled = "true";
	document.getElementById("rejectSave").disabled = "true";
	document.forms[0].subaction.value=x;	
	document.forms[0].submit();
}