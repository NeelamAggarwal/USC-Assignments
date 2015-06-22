<?php 
			header('Content-Type: text/xml;charset=utf-8');
			
			$inputvalue = $_GET["CompanyName"];
			
			{
				$request1 = 'http://query.yahooapis.com/v1/public/yql?q=Select%20Name%2C%20Symbol%2C%20LastTradePriceOnly%2C%20Change%2C%20ChangeinPercent%2C%20PreviousClose%2C%20DaysLow%2C%20DaysHigh%2C%20Open%2C%20YearLow%2C%20YearHigh%2C%20Bid%2C%20Ask%2C%20AverageDailyVolume%2C%20OneyrTargetPrice%2C%20MarketCapitalization%2C%20Volume%2C%20Open%2C%20YearLow%20from%20yahoo.finance.quotes%20where%20symbol%3D%22' .$inputvalue. '%22&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys';
				$note= simplexml_load_file($request1);
				
				{
					$newxml = new SimpleXMLElement("<result></result>");
					//echo '<br/>';
					$Name = $newxml->addChild('Name',(string)$note->results->quote->Name);
					$Name = str_replace('&', ' and ' , $Name);
					$Symbol = $newxml->addChild('Symbol',$note->results->quote->Symbol );
					// $newxml->Name=$element->Name;
					// $newxml->Symbol=$element->Symbol;
					$Quote = $newxml->addChild('Quote');
					
					$sign = number_format((double)$note->results->quote->Change,2);
					if($sign >= 0)
						$ChangeType = $Quote->addChild('ChangeType','+');
					else
						$ChangeType = $Quote->addChild('ChangeType','-');
					
					$Change = $Quote->addChild('Change', substr($note->results->quote->Change,1));
					$ChangeinPercent = $Quote->addChild('ChangeinPercent', substr($note->results->quote->ChangeinPercent,1) );
					$LastTradePriceOnly = $Quote->addChild('LastTradePriceOnly', number_format((double)$note->results->quote->LastTradePriceOnly,2,'.',','));
					$Open = $Quote->addChild('Open',  number_format((double)$note->results->quote->Open,2,'.',','));
					$YearLow = $Quote->addChild('YearLow', number_format((double)$note->results->quote->YearLow,2,'.',',') );
					$YearHigh = $Quote->addChild('YearHigh', number_format((double)$note->results->quote->YearHigh,2,'.',','));
					$Volume = $Quote->addChild('Volume', number_format((double)$note->results->quote->Volume));
					$OneYearTargetPrice = $Quote->addChild('OneYearTargetPrice', number_format((double)$note->results->quote->OneyrTargetPrice,2,'.',','));
					$Bid = $Quote->addChild('Bid',   number_format((double)$note->results->quote->Bid,2,'.',','));
					$DaysLow = $Quote->addChild('DaysLow', number_format((double)$note->results->quote->DaysLow,2,'.',',') );
					$DaysHigh = $Quote->addChild('DaysHigh', number_format((double)$note->results->quote->DaysHigh,2,'.',',') );
					$Ask = $Quote->addChild('Ask', number_format((double)$note->results->quote->Ask,2,'.',','));
					$AverageDailyVolume = $Quote->addChild('AverageDailyVolume', number_format((double)$note->results->quote->AverageDailyVolume));
					$PreviousClose = $Quote->addChild('PreviousClose', number_format((double)$note->results->quote->PreviousClose,2,'.',','));
					$cap = $note->results->quote->MarketCapitalization;
					$caplast = substr($cap,-1);
					$rest = substr($cap, 0, -1);
					$MarketCapitalization = $Quote->addChild('MarketCapitalization', number_format((double)$rest,2). $caplast);
					$News = $newxml->addChild('News');
					
					
					
					$request2 ='http://feeds.finance.yahoo.com/rss/2.0/headline?s=' . $inputvalue. '&region=US&lang=en-US';
					$note2= simplexml_load_file($request2);
					
					{
					
						foreach($note2->channel->children() as $firstchild)
						{
							if($firstchild->getName()=='item')
							{
								//$news= "";
								$item = $News->addChild('item');
								
								foreach( $firstchild->children() as $secondchild )
								{
									if($secondchild->getName()=='link')
									{
										$linki =  $secondchild ;
										$link = $item->addChild('link', htmlspecialchars($linki));
									}
									if($secondchild->getName()=='title')
									{
										$titlei =$secondchild ;
										
										$title = $item->addChild('title', htmlspecialchars( $titlei));
									}						
								}
							}
						}
					}
					
					$request3 = 'http://chart.finance.yahoo.com/t?s='. $inputvalue .'&amp;lang=en-US&amp;amp;width=300&amp;height=180';
					// $note3= simplexml_load_file($request3);
					$newxml->StockChartImageURL=$request3;
					//echo "</td></tr></table>";
					//echo $newxml->asXML();
					Header('Content-type: text/xml');
					 $result = $newxml->asXML();
					print_r($result);
					return $result;
				}
			}
		?>
