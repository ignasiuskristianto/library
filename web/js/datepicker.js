// Copyright (c) 1999 Microsoft Corporation
var stCalFontSize;

var aMonthLengths = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
var aMonthNames = new Array('January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December');
var aMonthShortNames = new Array('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec');
var aWeekDays = new Array('Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat');
var cellSelected, stDate, ctrlDest, aNewDate = new Array();

function initCalendar(initialDate, dateDest, stFontSize)
{
	
	var workingYear, oDate, curYear, i, inYear, inMonth, inDay, oInDate, posX, posY;	
	var objLeft, objTop, objParent;
	ctrlDest = dateDest;
	oDate = new Date();
	curYear = oDate.getFullYear();
	stCalFontSize = stFontSize;
	if(typeof(calPlatform)=='undefined')
		document.body.insertAdjacentHTML('BeforeEnd', '<div id=calPlatform style="position:absolute; visibility:hidden; z-index:-99">'
			+ '\n<table border=0 cellspacing=2 cellpadding=0 bgcolor=silver style="border-width:1; border-style:outset">'
			+ '\n<tr>'
			+ '\n<td align=left>'
			+ '\n	<select id=selMonth onchange="buildCal()">'
			+ '\n	</select>'
			+ '\n	<select id=selYear onchange="checkLeapYear(); buildCal()">'
			+ '\n	</select>'
			+ '\n</td>'
			+ '\n<td valign=top align=right>'
			+ '\n	<table cellspacing=0 cellpadding=0 border=1 style="border-style:none">'
			+ '\n	<tr>'
			+ '\n		<td valign=middle align=center style="cursor:default" language=javascript onmousedown="this.style.borderStyle=\'inset\'" onmouseup="this.style.borderStyle=\'outset\'" onmouseout="this.style.borderStyle=\'outset\'" onclick="hideCal();calPlatform.style.zIndex=-99" style="width:1; height:1; border-style=outset; border-width:1"><font size=-2 face="Verdana"><b>&nbsp;X&nbsp;</b></font></td>'
			+ '\n	</tr>'
			+ '\n	</table>'
			+ '\n</td>'
			+ '\n</tr>'
			+ '\n<tr>'
			+ '\n	<td id=calSpot colspan=2>'
			+ '\n	</td>'
			+ '\n</tr>'
			+ '\n</table>'
			+ '\n</div>');

	if(selYear.options.length==0)
	{
		for(workingYear = curYear+10; workingYear >= curYear - 100; workingYear--)
		//for(workingYear = curYear - 60; workingYear <= curYear; workingYear++)
			addOption(selYear, workingYear, workingYear);

		for(i=0; i < 12; i++)
			addOption(selMonth, i, aMonthNames[i]);
		cellSelected = null;
	}
	
	if(initialDate!=null)
	{
		oInDate = new Date(initialDate);
	
		inYear = oInDate.getFullYear();
		inMonth = oInDate.getMonth();
		inDay = oInDate.getDate();
	}
	
	if(initialDate!=null && (inYear+10 < curYear-100 && inYear < curYear+100) && (inMonth >= 0 && inMonth < 12) && (inDay >= 1 && inDay <= aMonthLengths[inMonth]))
	{
		selYear.selectedIndex = inYear-curYear + 100;
		selMonth.selectedIndex = inMonth;
		aNewDate[0] = inDay;
		aNewDate[1] = selMonth.value;
		aNewDate[2] = selYear.value;
		checkLeapYear();
		buildCal();
		selDate(inDay);
	}
	else
	{
		selYear.selectedIndex = 10;
		selMonth.selectedIndex = oDate.getMonth();
		aNewDate[0] = oDate.getDate();
		aNewDate[1] = selMonth.value;
		aNewDate[2] = selYear.value;
		checkLeapYear();
		buildCal();
		selDate(oDate.getDate());
	}
	
	
	// Find the element's offsetTop and offsetLeft relative to the BODY tag.
	objLeft   = dateDest.offsetLeft;
	objTop    = dateDest.offsetTop;
	objParent = dateDest.offsetParent;
	while (objParent.tagName.toUpperCase() != "BODY")
	{
		objLeft  += objParent.offsetLeft;
		objTop   += objParent.offsetTop;
		objParent = objParent.offsetParent;
	}	

	posY = objTop + dateDest.offsetHeight;
			
	if (objLeft + calPlatform.offsetWidth > screen.availWidth)
		posX = objLeft - (objLeft + calPlatform.offsetWidth - screen.availWidth) - 50;
	else
		posX = objLeft;

	calPlatform.style.visibility = 'visible';
	calPlatform.style.zIndex = 99;
	calPlatform.style.posLeft = posX;
	calPlatform.style.posTop = posY;

	hideElement(objLeft, objTop, calPlatform, "SELECT");
	hideElement(objLeft, objTop, calPlatform, "OBJECT");
	hideElement(objLeft, objTop, calPlatform, "IFRAME");
	selMonth.style.visibility = '';
	selYear.style.visibility = '';
}

function hideCal()
{
	
	showElement("SELECT");	
	showElement("OBJECT");	
	showElement("IFRAME");	
	calPlatform.style.visibility='hidden';

}

function hideElement(x, y, ToolbarMenu, elmID)
{
	for (i = 0; i < document.all.tags(elmID).length; i++)
	{
		obj = document.all.tags(elmID)[i];
		if (! obj || ! obj.offsetParent)
			continue;

		// Find the element's offsetTop and offsetLeft relative to the BODY tag.
		objLeft   = obj.offsetLeft;
		objTop    = obj.offsetTop;
		objParent = obj.offsetParent;
		while (objParent.tagName.toUpperCase() != "BODY")
		{
			objLeft  += objParent.offsetLeft;
			objTop   += objParent.offsetTop;
			objParent = objParent.offsetParent;
		}

		if (x > (objLeft + obj.offsetWidth) || objLeft > (x + ToolbarMenu.offsetWidth))
			;
		else if (y > (objTop + obj.offsetHeight) || objTop - 15 > (y + ToolbarMenu.offsetHeight+20))
			;
		else
			obj.style.visibility = "hidden";
	}
}

function showElement(elmID)
{
	for (i = 0; i < document.all.tags(elmID).length; i++)
	{
		obj = document.all.tags(elmID)[i];
		if (! obj || ! obj.offsetParent)
			continue;
		obj.style.visibility = "";
	}
}

function buildCal()
{
	var oMonthInfo, calCode, curDate, curDay, fillerDate, prevMonthLen;
	var sunColor, comColor, curColor;
	oMonthInfo = new Date();
	oMonthInfo.setFullYear(selYear.value);
	oMonthInfo.setDate(1);
	oMonthInfo.setMonth(selMonth.value);
	
	firstWeekDay = oMonthInfo.getDay();
		
	if(selMonth.value > 0)
		prevMonthLen = aMonthLengths[selMonth.value-1];
	else
		prevMonthLen = aMonthLengths[0];
	
	comColor = 'black';
	sunColor = 'red';	
	
	calCode = '<table id=calTable border=0 cellspacing=0 cellpadding=3 style="font-size:' + stCalFontSize + '; cursor:default"><tr>';
	curDate = 1;
	curDay = 0;
	
	for(curDay = 0; curDay < 7; curDay++)
	{
		if (curDay == 0)
			curColor = sunColor;
		else
			curColor = comColor;
		calCode += '<td align=center style="width:25; color=' + curColor + '; font-weight:bold">' + aWeekDays[curDay] + '</td>';
	}
	curDay = 0;
	calCode += '</tr>';
	
	for(fillerDate = (prevMonthLen-firstWeekDay+1); fillerDate <= prevMonthLen; fillerDate++, curDay++)
	{
		if(curDay==0)
			calCode += '<tr>';
		calCode += '<td language=javascript onclick="prevMonth(' + fillerDate + ')" style="color=gray; border-width:1; border-style:outset">' + fillerDate + '</td>';
		if(curDay==7)
			calCode += '</tr>';
	}

	while(curDate <= aMonthLengths[selMonth.value])
	{
		for(; curDay < 7 && curDate <= aMonthLengths[selMonth.value]; curDate++, curDay++)
			calCode += '<td language=javascript onclick="hiliteDate(this)" ondblclick="commitDate()" style="color=black; border-width:1; border-style:outset">' + curDate + '</td>';
		if(curDay==7)
		{
			calCode += '</tr>';
			curDay = 0;
		}
	}
	for(fillerDate = 1; curDay != 0 && curDay < 7; fillerDate++, curDay++)
		calCode += '<td language=javascript onclick="nextMonth(' + fillerDate + ')" style="color=gray; border-width:1; border-style:outset">' + fillerDate + '</td>';
	calCode += '</tr></table>';
	calSpot.innerHTML = calCode;

	if(aNewDate[1]==selMonth.selectedIndex)
		selDate(aNewDate[0]);
}

function checkLeapYear()
{
	if((1996 - selYear.value)%4==0)
		aMonthLengths[1]=29;
	else
		aMonthLengths[1]=28;
}

function nextMonth(iDate)
{
	if(selMonth.selectedIndex < 11)
		selMonth.selectedIndex++;
	else
	{
		selMonth.selectedIndex = 0;
		selYear.selectedIndex--;
	}
	buildCal(); 
	selDate(iDate)
}

function prevMonth(iDate)
{
	if(selMonth.selectedIndex > 0)
		selMonth.selectedIndex--;
	else
	{
		selMonth.selectedIndex = 11;
		selYear.selectedIndex++;
	}
	buildCal(); 
	selDate(iDate)
}

function hiliteDate(cellChoice)
{
	if(cellSelected != null)
	{
		cellSelected.style.borderStyle = 'outset';
		cellSelected.style.backgroundColor = 'silver';
		cellSelected.style.color = 'black';
	}
	
	cellSelected = cellChoice;

	cellSelected.style.borderStyle = 'inset';
	cellSelected.style.backgroundColor = 'gray';
	cellSelected.style.color = 'white';
	
	aNewDate[0] = cellSelected.innerText;
	aNewDate[1] = selMonth.value;
	aNewDate[2] = selYear.value;
}

function selDate(iDate)
{
	var i, j;

	for(j=0; j < calTable.rows.length; j++)
		for(i=0; i < calTable.rows(j).cells.length; i++)
			if(calTable.rows(j).cells(i).style.color=='black' && calTable.rows(j).cells(i).innerText==iDate)
				hiliteDate(calTable.rows(j).cells(i));
}

function addOption(selectObject, optionValue, optionText)
{
	var tempOption;
	tempOption = document.createElement("OPTION");
	tempOption.value = optionValue;
	tempOption.text = optionText;
	selectObject.add(tempOption);
}

function commitDate()
{
	//ctrlDest.value = aNewDate[2] + '-' + (parseInt(aNewDate[1])+1).toString() + '-' + aNewDate[0];
	ctrlDest.value = aNewDate[0] + ' ' + aMonthShortNames[parseInt(aNewDate[1])] + ' ' + aNewDate[2];

	//document.selection.clear();
	calPlatform.style.visibility = 'hidden';
	
	showElement("SELECT");	
	showElement("OBJECT");	
	showElement("IFRAME");	
	
	calPlatform.style.zIndex = -99;
}
