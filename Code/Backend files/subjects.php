<?php
mysql_connect("MYSQL5002.Smarterasp.net","9ab3ca_pranu","pintu848");
mysql_select_db("db_9ab3ca_pranu");
 
$q=mysql_query("SELECT * FROM subjects WHERE username='".$_REQUEST['username']."'");
while($e=mysql_fetch_assoc($q))
        $output[]=$e;
 
print(json_encode($output));
 
mysql_close();
?>