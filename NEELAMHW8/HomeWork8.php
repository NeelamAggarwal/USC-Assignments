<?php 
  if(isset($_GET["CompanyName"]))
     {
	  $text=str_replace(' ', '', $_GET["CompanyName"]);
	  if($text!="")
         { 
  $xml=simplexml_load_file("http://query.yahooapis.com/v1/public/yql?q=Select%20Name%2C%20Symbol%2C%20LastTradePriceOnly%2C%20Change%2C%20ChangeinPercent%2C%20PreviousClose%2C%20DaysLow%2C%20DaysHigh%2C%20Open%2C%20YearLow%2C%20YearHigh%2C%20Bid%2C%20Ask%2C%20AverageDailyVolume%2C%20OneyrTargetPrice%2C%20MarketCapitalization%2C%20Volume%2C%20Open%2C%20YearLow%20from%20yahoo.finance.quotes%20where%20symbol%3D%22".$text."%22&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
  
   $xml2=simplexml_load_file("http://feeds.finance.yahoo.com/rss/2.0/headline?s=".$text."&region=US&lang=en-US");
   $url = 'http://chart.finance.yahoo.com/t?s='.$text.'&amp;lang=en-US&amp;amp;width=300&amp;height=180';
   $elem2=$xml2->channel;
	  
	  $elem=$xml->results->quote;
	  $doc = new DOMDocument('1.0', 'utf-8');
	  
	 // $doc->preserveWhiteSpace = true;
	  $doc->formatOutput = true;
	  
	  $root = $doc->createElement('result');
	  $root = $doc->appendChild($root);
	  $title = $doc->createElement('Name');
      $title = $root->appendChild($title);
      $text = $doc->createTextNode($elem->Name);
      $text = $title->appendChild($text);
	  $title = $doc->createElement('Symbol');
      $title = $root->appendChild($title);
      $text = $doc->createTextNode($elem->Symbol);
      $text = $title->appendChild($text);
	  
	  $title = $doc->createElement('Quote');
	  $title = $root->appendChild($title);
	  
	  $change=$elem->Change;
	  $changenum=substr($change, 1);
	  $sym=substr($change, 0, 1);
	 
	  $innertitle = $doc->createElement('ChangeType');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode($sym);
      $text = $innertitle->appendChild($text);
  
      $innertitle = $doc->createElement('Change');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode($changenum);
      $text = $innertitle->appendChild($text);
	  
	  $cip=$elem->ChangeinPercent;
	  $cipnum=substr($cip, 1);
	  $cipsym=substr($cip, 0, 1);
	  
	  if($cipnum == "")
	  {
	   $cipnum = 0;
	  }
	  
	  
	  $innertitle = $doc->createElement('ChangeInPercent');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode($cipnum);
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('LastTradePriceOnly');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->LastTradePriceOnly), 2));
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('PreviousClose');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->PreviousClose),2));
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('DaysLow');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->DaysLow), 2));
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('DaysHigh');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->DaysHigh), 2));
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('Open');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->Open), 2));
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('YearLow');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->YearLow), 2));
      $text = $innertitle->appendChild($text);
      
	  $innertitle = $doc->createElement('YearHigh');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->YearHigh), 2));
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('Bid');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->Bid), 2));
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('Volume');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->Volume)));
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('Ask');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->Ask), 2));
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('AverageDailyVolume');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->AverageDailyVolume)));
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('OneYearTargetPrice');
	  $innertitle = $title->appendChild($innertitle);
	  $text = $doc->createTextNode(number_format(floatval($elem->OneyrTargetPrice), 2));
      $text = $innertitle->appendChild($text);
	  
	  $innertitle = $doc->createElement('MarketCapitalization');
	  $innertitle = $title->appendChild($innertitle);
	  if($elem->MarketCapitalization == "")
	  {
	  $text = $doc->createTextNode(" ");
      $text = $innertitle->appendChild($text);
	  }
	  else{
	  $text = $doc->createTextNode($elem->MarketCapitalization);
      $text = $innertitle->appendChild($text);
	  }
	  
	
	  
	  $title = $doc->createElement('News');
	  $title = $root->appendChild($title);
	  if($elem2->title=="Yahoo! Finance: RSS feed not found")
	  {
	   $innertitle = $doc->createElement('Item');
	   $innertitle = $title->appendChild($innertitle);
	  
	                            $iiiinnertitle = $doc->createElement('Title');
								$iiiinnertitle = $innertitle->appendChild($iiiinnertitle);
								$newtext = str_replace('"', '\"', $elem2->item->title);
	                            $text = $doc->createTextNode($newtext);
								
                                $text = $iiiinnertitle->appendChild($text);
								
								$iiiinnertitle = $doc->createElement('Link');
								$iiiinnertitle = $innertitle->appendChild($iiiinnertitle);
							    $value = "No Link";
	                            $text = $doc->createTextNode($value);
                                $text = $iiiinnertitle->appendChild($text);
								
	  }
	  else
	  {
	  foreach ($elem2->children() as $child)
       				{
         
		 				if($child->getName()=="item")
		 					{	
		  						$innertitle = $doc->createElement('Item');
	                            $innertitle = $title->appendChild($innertitle);
								
								$iiiinnertitle = $doc->createElement('Title');
								$iiiinnertitle = $innertitle->appendChild($iiiinnertitle);
								$newtext = str_replace('"', '\"', $child->title);
	                            $text = $doc->createTextNode($newtext);
								
                                $text = $iiiinnertitle->appendChild($text);
								
								$iiiinnertitle = $doc->createElement('Link');
								$iiiinnertitle = $innertitle->appendChild($iiiinnertitle);
							
	                            $text = $doc->createTextNode($child->link."");
                                $text = $iiiinnertitle->appendChild($text);
								
		                    }
                     }
					 }
	  $title = $doc->createElement('StockChartImageURL');
      $title = $root->appendChild($title);
	  
      $text = $doc->createTextNode($url);
      $text = $title->appendChild($text);				 
	  //header('Content-Type: text/xml;charset=utf-8');
	  echo $doc->saveXML();
	  }
}
	
?>
