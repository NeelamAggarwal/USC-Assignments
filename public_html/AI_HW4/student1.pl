%[Name, Hobby, Nationality, Fruit, Seller]

next_to(X, Y, List) :- iright(X, Y, List).
next_to(X, Y, List) :- iright(Y, X, List).
iright(L, R, [L | [R | _]]).
iright(L, R, [_ | Rest]) :- iright(L, R, Rest).
%last([_,_,_,_,_,P],List).
%front([Q,_,_,_,_,_],List).
front(Q,[Q|_]).

myprogram(List) :-
=(List,[[_,_,_,_,_],[_,_,_,_,_],[_,_,_,_,_],[_,_,_,_,_],[_,_,_,_,_],[_,_,jamaica,_,_]]),

%clue1
member([emma,_,cuba,_,maria],List),
%clue2
member([_,badminton,_,coconut,_],List),
%clue8
member([noah,swimming,_,_,_],List),
%clue10
member([ethan,diving,_,cherry,_],List),
%clue3
iright([jacob,_,_,blackberry,_],[_,_,_,_,harris],List),
%clue4
%I have inbuilt in the list itself because jamaica is in the last and it is constant.
%clue5
member([olivia,marathon,belguim,_,_],List),
%clue6
member([_,_,australia,_,jones],List),
%clue7
member([_,shooting,_,_,harris],List),
%clue9
\+(member([_,volleyball,_,mango,lisa],List)),
\+(member([_,badminton,_,mango,lisa],List)),
member([_,_,_,mango,lisa],List),
%clue11
member([_,_,morocco,grapes,_],List),
%clue12
next_to([chloe,_,_,_,_],[jacob,_,_,_,_],List),
%clue13
next_to([chloe,_,_,_,_],[_,_,_,coconut,_],List),
%clue14
member([jacob,_,_,_,moore],List),
%clue15
iright([_,_,chile,_,_],[_,shooting,_,_,_],List),
%clue16
member([noah,_,_,pineapple,_],List),
%extra
member([_,_,_,_,sharon],List),
member([_,volleyball,_,_,_],List),
%clue17
iright([_,_,australia,_,_],[_,_,_,mango,_],List),
%clue18
\+front([emma,_,_,_,_],List),
\+front([jacob,_,_,_,_],List).