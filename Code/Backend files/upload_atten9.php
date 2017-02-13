
<?php

mysql_connect("MYSQL5002.Smarterasp.net","9ab3ca_pranu","********");
mysql_select_db("db_9ab3ca_pranu");
/*
mysql_connect("localhost","root","");
mysql_select_db("atten");
*/

$user=$_REQUEST['username'];
$subject=$_REQUEST['subject'];
$date1=$_REQUEST['date'];
$stu=$_REQUEST['stu'];

//Breaking date to day,month,year for feature use 
$date2=explode("-",$date1);
$day=$date2[0];
$month=$date2[1];
$year=$date2[2];

//echo $day."..".$month."..".$year;

//collect all student names 
$tot=mysql_query("SELECT name,phone FROM students where faculty='$user' and subject='$subject'");
/*if(!$tot)
{
echo "NOT TOT";
}
*/

while($row = mysql_fetch_array($tot))
  {
  //echo $row[0]."<br>";
  
  $a=mysql_num_rows($tot);
 // echo $a;
//inserting all students with present as default 
 $instot=mysql_query("insert into attendance values('$user','$subject','$row[0]','Present','$day','$month','$year','$row[1]')");
 
 
 }
 /*
 if(!$instot)
 {
 echo "not";
 die('NOT: ' . mysql_error());
 }
 else
 {
 echo "OK";
 }
 */
//print_r($tot);

// Breaking the students from received from STU variable eg: [Naresh..absent,Ravali..Absent] 
$stu1=explode(",",$stu);
$j=0;
//print_r($stu1);
$stu2=explode("[",$stu1[0]);
//print_r($stu2);
$stu3=explode("]",$stu1[sizeof($stu1)-1]);
//print_r($stu3);

for($i=0;$i<=sizeof($stu1)-2;$i++)
{
$id[$i]=$stu1[$i+1];
}

$id[sizeof($stu1)-2]=$stu2[1];
$id[sizeof($stu1)-1]=$stu3[0];

//print_r($id);
for($i=0;$i<sizeof($id);$i++)
{
$sp[$i]=explode("..",$id[$i]);
}
/*
for($i=0;$i<sizeof($sp);$i++)
{
for($j=0;$j<2;$j++)
{
echo $sp[$i][$j]."..";
}echo "\n";}
*/

//checking each stu names and status trailssssss
for($i=0;$i<sizeof($id);$i++)
{
$stu_name[$i]=$sp[$i][0];
$stu_status[$i]=$sp[$i][1];
}
//print_r($stu_name);
//print_r($stu_status);

// update the students with absent which will replace the previous present value
for($i=0;$i<sizeof($id);$i++)
{
//echo $sp[$i][0]."..".$sp[$i][1].",,";

//print_r($sp);

$jstupdate=mysql_query("UPDATE attendance SET stu='$stu_name[$i]',status='$stu_status[$i]' WHERE stu='$stu_name[$i]' and username='$user' and subject='$subject' and day='$day' and month='$month' and year='$year'");
/*
if(!$jstupdate)
{
echo "not jstupdate";
}
*/
//$q=mysql_query("insert into attendance values('$user','$subject','$date1','$stu_name[$i]','$stu_status[$i]')");
}
/*
//collecting all studnets and sending in json form to android

$q=mysql_query("SELECT * FROM students WHERE faculty='$user' and subject='$subject' ");
while($e=mysql_fetch_assoc($q))
        $output[]=$e;
 
print(json_encode($output));
*/
$ab=array();
$pcou=array();
$totcou=array();
$per=array();
$per2=array();
$output;

$tot1=mysql_query("SELECT * from attendance where username='$user' and subject='$subject' and day='$day' and month='$month' and year='$year' ");
//$row=mysql_fetch_array($tot1);
//print_r($row);
while($row1=mysql_fetch_array($tot1))
{
//echo $row1[2];
$ab[$row1[2]]=$row1[2];
}
//echo "<br>";
$ab1=array_values($ab);
//print_r($ab1);

for($i=0;$i<count($ab1);$i++)
{
$present=mysql_query("SELECT stu FROM attendance where username='$user' and subject='$subject' and day<='$day' and month<='$month' and year<='$year' and status='Present' and stu='$ab1[$i]' ");

$pcou[$ab1[$i]]=mysql_num_rows($present);

$totclasses=mysql_query("SELECT * FROM attendance where username='$user' and subject='$subject' and stu='$ab1[$i]' and day<='$day' and month<='$month' and year<='$year' ");

$totcou[$ab1[$i]]=mysql_num_rows($totclasses);
}
//print_r($pcou);
//echo "<br><br>";
//print_r($totcou);
//echo "<br><br><br>";

$pcou1=array_values($pcou);
$totcou1=array_values($totcou);
//print_r($pcou1);
//echo "<br><br>";
//print_r($totcou1);
//echo "<br><br>";
for($i=0;$i<count($pcou1);$i++)
{
for($j=0;$j<count($totcou1);$j++)
{
$per[$i]=round((($pcou1[$i]/$totcou1[$j])*100),2);
}
}
//print_r($per);
for($i=0;$i<count($ab1);$i++)
{
$fi=mysql_query("SELECT * FROM attendance where username='$user' and subject='$subject' and stu='$ab1[$i]' and day<='$day' and month<='$month' and year<='$year'");

$per1[$ab1[$i]]=$per[$i];
}

$abcd=mysql_query("SELECT * FROM attendance where username='$user' and subject='$subject' and day='$day' and month='$month' and year='$year'");
$r=mysql_query("SELECT * FROM percentage where username='$user' and subject='$subject' ");

if(mysql_num_rows($r)==0)
{
while($row=mysql_fetch_array($abcd))
{


mysql_query("INSERT INTO percentage (username,subject,day,month,year,student,phone) values('$user','$subject','$day','$month','$year','$row[2]','$row[7]')");
}
}

$per2=array_keys($per1);
for($k=0;$k<count($per);$k++)
{
mysql_query("UPDATE percentage SET percen='$per[$k]',day='$day',month='$month',year='$year' where student='$per2[$k]' and subject='$subject'");
}


$q=mysql_query("SELECT * FROM percentage WHERE username='$user' and subject='$subject' and day='$day' and month='$month' and year='$year'");
while($g=mysql_fetch_assoc($q))
{
        $output1[]=$g;
}
if(isset($output1))
{	
//print($output1);
print(json_encode($output1));
}
mysql_close();
?>
