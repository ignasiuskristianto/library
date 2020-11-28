<!--
// please keep this text when you copy the source code
// made by Ragecreations version 0.2

//Get Date
var months=new Array(13);
months[1]="January";
months[2]="February";
months[3]="March";
months[4]="April";
months[5]="May";
months[6]="June";
months[7]="July";
months[8]="August";
months[9]="September";
months[10]="October";
months[11]="November";
months[12]="December";
var time=new Date();
var lmonth=months[time.getMonth() + 1];
var date=time.getDate();
var year=time.getYear();
if (year < 2000) 
year = year + 1900; 

//Get Time
var now = new Date();
var hours = now.getHours();
var minutes = now.getMinutes()
var timeValue = "" + ((hours >12) ? hours -12 :hours)
if (timeValue == "0") timeValue = 12;
timeValue += ((minutes < 10) ? ":0" : ":") + minutes
timeValue += (hours >= 12) ? " pm" : " am"

//Get Day
var days=new Array(7);
days[0]="Sunday";
days[1]="Monday";
days[2]="Tuesday";
days[3]="Wednesday";
days[4]="Thursday";
days[5]="Friday";
days[6]="Saturday";
var lday=days[time.getDay()];

//-->

