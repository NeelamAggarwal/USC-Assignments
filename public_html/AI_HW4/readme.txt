Name-Neelam Aggarwal
USC ID- 6667974120
email- neelamag@usc.edu

In my homework there are two files:
1) student1.pl - It is Part1 of the HW having 18 clues.
2) student2.pl - It is Part2 of the HW having 19 clue with one clue replaced.

Queries:
1) aludra.usc.edu(1): gprolog

2) | ?- consult(student1).

3) | ?- myprogram([A,B,C,D,E,F]).
output:
A = [noah,swimming,australia,pineapple,jones]
B = [olivia,marathon,belguim,mango,lisa]
C = [jacob,volleyball,chile,blackberry,moore]
D = [chloe,shooting,morocco,grapes,harris]
E = [emma,badminton,cuba,coconut,maria]
F = [ethan,diving,jamaica,cherry,sharon] ? ;

A = [ethan,diving,australia,cherry,jones]
B = [olivia,marathon,belguim,mango,lisa]
C = [jacob,volleyball,chile,blackberry,moore]
D = [chloe,shooting,morocco,grapes,harris]
E = [emma,badminton,cuba,coconut,maria]
F = [noah,swimming,jamaica,pineapple,sharon] ? ;

4) | ?- myprogram([_,[X|_]|_]).
output:
X = olivia ?

5) | ?- consult(student2).

6) | ?- myprogram([[A|_],[B|_],[C|_],[D|_],[E|_],[F|_]]).
output:
A = ethan
B = olivia
C = emma
D = jacob
E = chloe
F = noah ?
 
7) | ?- myprogram(X),member([noah,_,_,_,Seller],X).
output:
Seller = sharon
X = [[ethan,diving,australia,cherry,jones],[olivia,marathon,belguim,mango,lisa],[emma,badminton,cuba,coconut,maria],[jacob,volleyball,chile,blackberry,moore],[chloe,shooting,morocco,grapes,harris],[noah,swimming,jamaica,pineapple,sharon]] ?


