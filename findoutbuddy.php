<?php

#
# Find out the buddy id
#

$API_URL_PREFIX = "https://apis.daum.net";
$MYPEOPLE_BOT_APIKEY = "please, add the api key of your bot";
$API_URL_POSTFIX = "&apikey=" .$MYPEOPLE_BOT_APIKEY;


if($_POST['action'] == "addBuddy" || $_POST['action'] == "sendFromMessage") {
	$buddyId = $_POST['buddyId'];	

	# Write buddy into a file
	$myFile = "buddy.txt";
	$fh = fopen($myFile, 'w') or die("can't open file");
	fwrite($fh, $buddyId);
	fclose($fh);
	
	$msg = "Your buddy is ".$buddyId;
	sendMessage($buddyId, $msg);
}



function sendMessage($buddyId, $msg)
{
        global $API_URL_PREFIX, $API_URL_POSTFIX, $MYPEOPLE_BOT_APIKEY, $fh;

        $url =  $API_URL_PREFIX."/mypeople/buddy/send.xml";
 
        $msg = urlencode(str_replace(array("\n",'\n'), "\r", $msg));
 
        $postData = array();
        $postData['buddyId'] = $buddyId;
 
        $postData['content'] = $msg;
        $postData['apikey'] = $MYPEOPLE_BOT_APIKEY;
        $postVars = http_build_query($postData);
 
 
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $postVars);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt ($ch, CURLOPT_SSL_VERIFYPEER, 0);
        $result = curl_exec($ch);
        curl_close($ch);
}


?>
