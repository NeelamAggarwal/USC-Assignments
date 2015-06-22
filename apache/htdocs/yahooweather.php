<?php
header('Content-Type: text/xml;charset=utf-8');
       $FLocation = $_GET['location'];
       $FType = $_GET['type'];
       $FUnit = $_GET['tempunit'];
       
        if ($FUnit == "F" || $FUnit == "f") {
            $tempunit = 'f';
        } else if ($FUnit == "C" || $FUnit == "c"){
            $tempunit = 'c';
        }

        if ( $FType == "city")  {

            $string = str_replace(",",  " " , $_GET["location"] );
            $string = urlencode($string );
            $url = "places\$and(.q(%22".$string."%22),.type(7));start=0;count=5";
            $xml_url = GetXML($url);
			
			if($xml_url === false) {
			    //echo " Zero Results Found for Zipcode!!";
                $xml = new SimpleXMLElement('<weather/>');
                
                $xml->addChild('feed', "");         
                $xml->addChild('link', "");
                $xml->addChild('location', "");
                $xml->addChild('units', "");
                $xml->addChild('condition', "");
                $xml->addChild('img', "");
                $xml->addChild('forecast', "");
                $xml->forecast->addAttribute('day', "Empty");
                $xml->forecast->addAttribute('low', "Empty");
                $xml->forecast->addAttribute('high', "Empty");
                $xml->forecast->addAttribute('text', "Empty"); 
                                
                Header('Content-type: text/xml');
                $result = $xml->asXML();
                print_r($result);
                return $result;
			 }

            $newResultCount = 0 ;

            $doc = new DOMDocument(); 
            $str = $xml_url->asXML(); 
            $doc->loadXML($str); 
            $length = $doc->getElementsByTagName("woeid")->length; 


           if ( $length === 0) 
            {
            echo "Zero Results Found !! ";
            }  else {
            $counter = $length;
            for ( $i=0 ; $i < $length; $i++ ) {

                $woeid = (int)($xml_url->place[$i]->locality1->attributes()->woeid);
                if($woeid === 0)
                {
                    $woeid = (int)($xml_url->place[$i]->woeid);

                }

                $url = "http://weather.yahooapis.com/forecastrss?w=".$woeid."&u=".$tempunit;

               $sxml = @simplexml_load_file($url, null, LIBXML_NOCDATA);
               if ($sxml === false) {
                    libxml_clear_errors();
               }
                $title = $sxml->channel->title;
                $error = "Yahoo! Weather - Error";
             if (!strcmp ( $title , $error)){
             $counter = $counter - 1;
             } 

            }


            for ( $i=0 ; $i < $length; $i++ ) {

                $woeid = (int)($xml_url->place[$i]->locality1->attributes()->woeid);
                if($woeid === 0)
                {
                    $woeid = (int)($xml_url->place[$i]->woeid);

                }


                $newResultCount = GetWeather($woeid, $tempunit);
                print_r($newResultCount);
                return $newResultCount;

            }

            }

        } else  {
            $zipurl = "concordance/usps/".$_GET["location"];
            
            $xml = GetXML($zipurl);
            if ( $xml === false) {
                //echo " Zero Results Found for Zipcode!!";
                $xml = new SimpleXMLElement('<weather/>');
                
                $xml->addChild('feed', "");         
                $xml->addChild('link', "");
                $xml->addChild('location', "");
                $xml->addChild('units', "");
                $xml->addChild('condition', "");
                $xml->addChild('img', "");
                $xml->addChild('forecast', "");
                $xml->forecast->addAttribute('day', "Empty");
                $xml->forecast->addAttribute('low', "Empty");
                $xml->forecast->addAttribute('high', "Empty");
                $xml->forecast->addAttribute('text', "Empty"); 
                                
                Header('Content-type: text/xml');
                $result = $xml->asXML();
                print_r($result);
                return $result;
            }

            $woeid = (int)$xml->woeid;
            //print_r($woeid);
            if ( $woeid ==="")
            {
            echo "Zero Results Found!!";
            return;
            }  else {
           // echo "result(s) for Zipcode " ;
            //echo $_POST["Location"]  ;

            //echo "<tr><td class='cell'><b>Weather</b></td><td class='cell'><b>Temperature</b></td><td class='cell'><b>City</b></td><td class='cell'><b>Region</b></td><td class='cell'><b>Country</b></td><td class='cell'><b>Latitude</b></td><td class='cell'><b>Longitude</b></td><td class='cell'><b>Link to Details</b></td></tr>";

           $newResultCount = GetWeather($woeid, $tempunit);
           print_r($newResultCount);
           return $newResultCount;
        }
}


function GetXML($typeForUrl)  {
    $url = "http://where.yahooapis.com/v1/";
    $appid = "?appid=GxDHY9TV34FIdYW6HNBmJn3SjmIQiPTG36eeMTm8Z7BaSjE3kq7gGLHpPkNFG.ox1tSICmkl";
    $url = $url.$typeForUrl.$appid; 
    libxml_use_internal_errors();
    $sxml = @simplexml_load_file($url);
    

    libxml_clear_errors();    
   

    if($sxml === false){
      //  echo "Malformed Query. Please fix your query and try again.";
        libxml_clear_errors();
        return false;
    } 
      
    return $sxml;
}


function GetWeather($woeid, $tempUnit)  {
    $url = "http://weather.yahooapis.com/forecastrss?w=".$woeid."&u=".$tempUnit;
    //echo $url;

    libxml_use_internal_errors(true);
    $sxml = @simplexml_load_file($url, null, LIBXML_NOCDATA);
    
    if (($sxml === false) || ($sxml === NULL) ){
        //echo "FALSE";
        libxml_clear_errors();
        return false ;
    }

    $title = $sxml->channel->title;
    $error = "Yahoo! Weather - Error";
     if (!strcmp ( $title , $error)){
        return;
    } 


    $xml = GenerateRow($url, $sxml);
    //print_r($xml);

    return $xml;
}

function GenerateRow($url, $sxml){

    $yweather = 0 ;
    $namespaces = $sxml->getNameSpaces(true);
    if ( !isset($namespaces) || !isset($yweather) ){
    return ;
    }


    $yweather = $sxml->channel->item->children($namespaces['yweather']);
    $geo = $sxml->channel->item->children($namespaces['geo']);
    $location = $sxml->channel->children($namespaces['yweather'])->location;
    $unittype = $sxml->channel->children($namespaces['yweather'])->units;
    $forecast_day = $sxml->channel->item->children($namespaces['yweather'])->forecast->attributes()->day; 
    $forecast_low = $sxml->channel->item->children($namespaces['yweather'])->forecast->attributes()->low; 
    $forecast_high = $sxml->channel->item->children($namespaces['yweather'])->forecast->attributes()->high; 
    $forecast_text = $sxml->channel->item->children($namespaces['yweather'])->forecast->attributes()->text; 
     $linkin = (string)$sxml->channel->link ;


    $temperature = $yweather->condition->attributes()->text." ".$yweather->condition->attributes()->temp."&deg;".$sxml->channel->children($namespaces['yweather'])->units->attributes()->temperature;
    $city = $location->attributes()->city;
    $region = $location->attributes()->region;
    $country = $location->attributes()->country;
    $united =  $unittype->attributes()->temperature;
    $latitude = $geo->lat;
    $longitude = $geo->long;
    $link =  $sxml->channel->link;
    $textin = $yweather->condition->attributes()->text;
    $temp = $yweather->condition->attributes()->temp;
    for ( $i = 0; $i<=4 ;$i++) {
    $forecast_day1[$i] = (string)$sxml->channel->item->children($namespaces['yweather'])->forecast[$i]->attributes()->day; 
    $forecast_low1[$i] = (string)$sxml->channel->item->children($namespaces['yweather'])->forecast[$i]->attributes()->low; 
    $forecast_high1[$i] = (string)$sxml->channel->item->children($namespaces['yweather'])->forecast[$i]->attributes()->high; 
    $forecast_text1[$i] = (string)$sxml->channel->item->children($namespaces['yweather'])->forecast[$i]->attributes()->text; 

    }

    $image =  $sxml->channel->item->description;

    //preg_match('/<img[^>]+>/i', $image, $all_images);
    
    $imgpattern = '/src="(.*?)"/i';
   preg_match($imgpattern, $image, $matches);
   $all_images['src'] = $matches[1];
   $img=$all_images['src'];

   $link = "<a href=\"".$link."\" target=\"_blank\">Details</a>";
   $xml = new SimpleXMLElement('<weather/>');


    $xml->addChild('feed', "");
    $xml->feed->addAttribute('feed1', "$url");
    $xml->addChild('link', "");
    $xml->link->addAttribute('link1', "$linkin");
    $xml->addChild('location', "");
    $xml->location->addAttribute('city', "$city");
    $xml->location->addAttribute('region', "$region");
    $xml->location->addAttribute('country', "$country");
    $xml->addChild('units', "");
    $xml->units->addAttribute('units1', "$united");
    $xml->addChild('condition', "");
    $xml->condition->addAttribute('text', "$textin");
    $xml->condition->addAttribute('temp', "$temp");
    $xml->addChild('img', "");
    $xml->img->addAttribute('img1', "$img");
    $xml->addChild('forecast', "");
    $xml->forecast->addAttribute('day', "$forecast_day1[0]");
    $xml->forecast->addAttribute('low', "$forecast_low1[0]");
    $xml->forecast->addAttribute('high', "$forecast_high1[0]");
    $xml->forecast->addAttribute('text', "$forecast_text1[0]");
    $xml->addChild('forecast1', "");
    $xml->forecast1->addAttribute('day', "$forecast_day1[1]");
    $xml->forecast1->addAttribute('low', "$forecast_low1[1]");
    $xml->forecast1->addAttribute('high', "$forecast_high1[1]");
    $xml->forecast1->addAttribute('text', "$forecast_text1[1]");    
    $xml->addChild('forecast2', "");
    $xml->forecast2->addAttribute('day', "$forecast_day1[2]");
    $xml->forecast2->addAttribute('low', "$forecast_low1[2]");
    $xml->forecast2->addAttribute('high', "$forecast_high1[2]");
    $xml->forecast2->addAttribute('text', "$forecast_text1[2]");
    $xml->addChild('forecast3', "");
    $xml->forecast3->addAttribute('day', "$forecast_day1[3]");
    $xml->forecast3->addAttribute('low', "$forecast_low1[3]");
    $xml->forecast3->addAttribute('high', "$forecast_high1[3]");
    $xml->forecast3->addAttribute('text', "$forecast_text1[3]");
    $xml->addChild('forecast4', "");
    $xml->forecast4->addAttribute('day', "$forecast_day1[4]");
    $xml->forecast4->addAttribute('low', "$forecast_low1[4]");
    $xml->forecast4->addAttribute('high', "$forecast_high1[4]");
    $xml->forecast4->addAttribute('text', "$forecast_text1[4]");


Header('Content-type: text/xml');
$result = $xml->asXML();
//print_r($result);

return $result;


  /*testing null condition for every variable */

     if ( $all_images[0] == "") {
     $all_images = "N/A";
     } else {

    // echo $image;

    } 
    if ($temperature == "") {
    $temperature = "N/A" ;
    }

     if ($city == "") {
    $city = "N/A" ;
    }

    if ($region == "") {
    $region = "N/A" ;
    }


    if ($country == "") {
    $country = "N/A" ;
    }

    if ($latitude == "") {
    $latitude = "N/A" ;
    }

    if ($longitude == "") {
    $longitude = "N/A" ;
    }

    if ($link == "") {
    $link = "N/A" ;
    }

$imgurl="<a href=\"".$url."\" target=\"_blank\">";

$imgprefix = substr($all_images[0], 0, -2);
$imgfull = $imgprefix." title=\"".$temperature."\" alt=\"".$temperature."\">";

}

?>
