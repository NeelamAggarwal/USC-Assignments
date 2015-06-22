Author: Neelam Aggarwal

Description:
Design and build a robust text scraper that will connect to a page on www.walmart.com and return results about a given
keyword. There are two queries that will be performed:
Query 1: Total number of results
Given a keyword, such as "digital camera", return the total number of results found.
Query 2: Result Object
Given a keyword (e.g. "digital cameras") and page number (e.g. "1"), return the results in a result object and then
print results on screen. For each result, return the following information:
Title/Product Name (e.g. "Samsung TL100 Digital Camera")
Price of the product
For "digital cameras", there should be either 16 or 32 results that are returned for page 1. (You can work with either
number of results for the assignment)
How to run/execute the program:
Encapsulate your assignment inside an executable jar (e.g. java -jar Assignment.jar ...)
Handle the two queries above:
Query 1: (requires a single argument)
java -jar Assignment.jar <keyword> (e.g. java -jar Assignment.jar "digital camera")
Query 2: (requires two arguments)
java -jar Assignment.jar <keyword> <page number> (e.g. java -jar Assignment.jar "digital camera" 2


To run jar file:

1) java -jar Assignment.jar "digital camera" 1

2) java -jar Assignment.jar "digital camera" 
// When we'll give only one argument, second argument will be taken as 1 as default while computation.
3) java -jar Assignment.jar camera 2
//single word may be given without quotes but for more than one word quotes is mandatory.
4) java -jar Assignment.jar camera ;;
//if special character is given instead of integer, exception is handled, user defined message will be displayed.
5) java -jar Assignment.jar
// if no argument are given, user friendly msg is displayed & program exists.
5) java -jar Assignment.jar digital camera 3
// if more than two argument are given, user friendly msg is displayed & program exists.
6) 5) java -jar Assignment.jar camera 0
// if pageno zero is given, user friendly msg is displayed & program exists.


Future addition:
1) When total results is less than the (pageno*16), then display only first page. 
In this case, we have to again make a call to connect to first page. 
For example: Total result is 6 and user entered pageno 2, So, on pageno2 there will no products.
So, in this case page should be redirected to pageno1.
2) In some cases, there are two prices which are getting displayed as bigText1. bigText1. smallText1 smallText2
where bigText is dollar, smallText is cents.
It should be properly formatted as in bigText1.smalltext1  , bigText2.smallText2



To compile 
javac -cp "jsoup-1.7.3.jar" NewClass1.java

To run without using jar file
java -cp "jsoup-1.7.3.jar";. NewClass1 "camera" 1

