<HTML>
	<HEAD>
		<title> HW6 PHP </title>
		
	</HEAD>
	<BODY>
		<H2 align="center"> Market Stock Search </H2>
			<FORM ID="form1" ACTION="HW6.php"  METHOD="POST">
				<table align ="center" STYLE="border-width:2px; border-style:solid;"> 
					<tr>
						<td>Company Symbol:</td>
						<td><input ID="input" name="input" size= "37"></td>
						<td><input TYPE="submit" ID="search" name="search" VALUE="Search"></td>
					</tr> 
					<tr>
						<td colspan="3">Example: GOOG, MSFT, YHOO, FB, AAPL,...etc </td>
					</tr>
				</table>
			</form>
			
		<?php 
			if (isset($_POST["input"]) && empty($_POST["input"]))
			{ 
		?>
				<script type="text/javascript"> 
				alert("Please enter a company symbol"); 
				//history.back();
				</script>
		<?php 
			} 
			else if(isset($_POST["input"]) && !empty($_POST["input"]))
			{
				$inputvalue = $_POST["input"];
				$inputvalue = preg_replace('/\s+/', '', $inputvalue);
				$request1 = 'http://query.yahooapis.com/v1/public/yql?q=Select%20Name%2C%20Symbol%2C%20LastTradePriceOnly%2C%20Change%2C%20ChangeinPercent%2C%20PreviousClose%2C%20DaysLow%2C%20DaysHigh%2C%20Open%2C%20YearLow%2C%20YearHigh%2C%20Bid%2C%20Ask%2C%20AverageDailyVolume%2C%20OneyrTargetPrice%2C%20MarketCapitalization%2C%20Volume%2C%20Open%2C%20YearLow%20from%20yahoo.finance.quotes%20where%20symbol%3D%22' .$inputvalue. '%22&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys';
				$note= simplexml_load_file($request1);
				if (empty($note->results->quote->Change))
				{
					echo "<H1 align='center'> Stock Information Not Available </H1>";
					
				}
				else
				{
					echo "<H2 align='center'> Search Results</H2>";
					//print_r ($note);
					echo '<table align="center" style="width:700px"> <tr><td>' ;
					echo '<H2 style="display:inline">' .$note->results->quote->Name . "</H2>";
					//$sym = '<H3 style="display:inline-block">' ."(" . $note->results->quote->Symbol . ") " . "</H3>"; 
					$sym = "(" . $note->results->quote->Symbol . ") " ;
					echo "     ";
					echo '<H2 style="display:inline">' . $sym . "</H2>";
					echo number_format((double)$note->results->quote->LastTradePriceOnly,2,'.',',') . "   "; echo "   ";
					//echo $note->results->quote->LastTradePriceOnly . " ";
					//echo number_format($note->results->quote->LastTradePriceOnly) . " ";
					$ch = $note->results->quote->Change;
					$chper =$note->results->quote->ChangeinPercent;
					//echo $note->results->quote->ChangeinPercent;
					//echo $ch = $note->results->quote->Change;
					$sign = substr($chper,0,1);
					$ch = substr($ch, 1);
					$chper = substr($chper, 1);
					//echo "1st" .$ch;
					$ch = number_format((double)$ch,2,'.',',');
					$chper = "(". $chper . ")";
					//echo "2nd" .$ch;
					$image = " ";
					if( $sign == '-')
					{
						$image = "down_r.gif";					
						$ch = '<div style="color:red; display:inline;">' . $ch . "</div>";
						$chper = '<div style="color:red; display:inline;">' . $chper . "</div>";
						echo '<img src="' . $image . '" style="display:inline"/>' ;
					}
					else if( $sign == '+')
					{
						$image = "up_g.gif";
						$ch = '<div style="color:green; display:inline;">' . $ch . "</div>";
						$chper = '<div style="color:green; display:inline;">' . $chper . "</div>";
						echo '<img src="' . $image . '" style="display:inline"/>' ;
					}
					else
					{
						$ch = '<div style="color:green; display:inline;">' . $ch . "</div>";
						$chper = '<div style="color:green; display:inline;">' . $chper . "</div>";
					}
					//echo "<img src=" . "down_r.gif" . "/>" ;
					
					echo $ch;
					echo $chper;
					//echo '<hr size="2" style="margin-bottom:0; margin-top:0">';
					echo '<div style="height: 0; border-top: 3px solid #000000; "></div>';
					//$fullstring = "";
					$fullstring= '<table style="width:700px" >';
					//$fullstring.= "<tr><td> <hr/></td></tr>";
					$fullstring.= '<tr><td style="border-top:soild">' . "Prev Close:" . "</td>";
					$fullstring.= "<td>". number_format((double)$note->results->quote->PreviousClose,2,'.',',') ."</td>";
					$fullstring.= "<td>" . "Day's Range:" . "</td>";
					$fullstring.= "<td align = 'right'>". number_format((double)$note->results->quote->DaysLow,2,'.',',') . " - " . number_format((double)$note->results->quote->DaysHigh,2,'.',',') ."</td></tr>" ;
					$fullstring.= "<tr><td>" . "Open:" . "</td>";
					$fullstring.= "<td>". number_format((double)$note->results->quote->Open,2,'.',',') . "</td>" ;
					$fullstring.= "<td>" . "52wk Range:" . "</td>";
					$fullstring.= "<td align = 'right'>". number_format((double)$note->results->quote->YearLow,2,'.',',') . " - " . number_format((double)$note->results->quote->YearHigh,2,'.',',') ."</td></tr>" ;
					$fullstring.= "<tr><td>" . "Bid:" . "</td>";
					$fullstring.= "<td>". number_format((double)$note->results->quote->Bid,2,'.',',') . "</td>" ;
					$fullstring.= "<td>" . "Volume:" . "</td>";
					$fullstring.= "<td align = 'right'>". number_format((double)$note->results->quote->Volume) . "</td></tr>" ;
					$fullstring.= "<tr><td>" . "Ask:" . "</td>";
					$fullstring.= "<td>". number_format((double)$note->results->quote->Ask,2,'.',',') . "</td>" ;
					$fullstring.= "<td>" . "Avg Vol(3m):" . "</td>";
					$fullstring.= "<td align = 'right'>". number_format((double)$note->results->quote->AverageDailyVolume) . "</td></tr>" ;
					$fullstring.= "<tr><td>" . "1y Target Est:" . "</td>";
					$fullstring.= "<td>". number_format((double)$note->results->quote->OneyrTargetPrice,2,'.',',') . "</td>" ;
					$fullstring.= "<td>" . "Market Cap:" . "</td>";
					$cap = $note->results->quote->MarketCapitalization;
					$caplast = substr($cap,-1);
					$rest = substr($cap, 0, -1);
					$fullstring.= "<td align='right'>". number_format((double)$rest,2). $caplast . "</td></tr>" ;
					//$fullstring.= "<td align = 'right'>". $note->results->quote->MarketCapitalization . "</td></tr>" ;
					//$fullstring.= "<td align = 'right'>". number_format((double)$note->results->quote->MarketCapitalization,2) . "</td></tr>" ;
					$fullstring.= "</table>";
					echo $fullstring;
					
					//var $xml = ($note);
					//htmlspecialchars($note, ENT_QUOTES);
					
					//
					
					$request2 ='http://feeds.finance.yahoo.com/rss/2.0/headline?s=' . $inputvalue. '&region=US&lang=en-US';
					$note2= simplexml_load_file($request2);
					if($note2->channel->item->title=="Yahoo! Finance: RSS feed not found")
					{	
						echo '<h2 align="center">Financial Company News is not available!</h2>';
					}
					else
					{
						echo '<div><H2 style="display:inline"> News Headlines </H2></div>';
						echo '<div style="height: 0; border-top: 3px solid #000000;"></div>';
						echo"<div><ul>";
						foreach($note2->channel->children() as $firstchild)
						{
							if($firstchild->getName()=='item')
							{
								//$news= "";
								foreach( $firstchild->children() as $secondchild )
								{
									if($secondchild->getName()=='link')
										{$linki = '<a target="_blank" href = "' . $secondchild .'">';}
									if($secondchild->getName()=='title')
										{$titlei = htmlentities($secondchild, ENT_QUOTES, 'UTF-8') ."</a><br/>";}						
								}
								echo "<li>".$linki. $titlei. "</li>";
							}
						}
						echo "</ul></div>";
					}
					echo "</td></tr></table>";
				}
			}
		?>
		<NOSCRIPT>
	</BODY>
</HTML>