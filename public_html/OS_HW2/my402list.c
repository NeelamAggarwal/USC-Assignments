#include<stdio.h>
#include<stdlib.h>
#include"my402list.h"
 


int My402ListLength(My402List *MyList)
{
	return MyList->num_members;
}

int  My402ListEmpty(My402List *MyList)
{

	if(MyList->num_members==0)
		return 1;
	else
		return 0;
}
int  My402ListAppend(My402List *MyList, void *object)
{
	if(MyList!=NULL)
	{
		My402ListElem *addobj;
		My402ListElem *t; 
		addobj=(My402ListElem *)calloc(1,sizeof *addobj);
		if(addobj==NULL)
			return 0;
		addobj->obj=object;
		
		//t=(My402ListElem *)calloc(1,sizeof (My402ListElem));
		t=MyList->anchor.prev;
		t->next=addobj;
		addobj->prev=MyList->anchor.prev;
		addobj->next=&MyList->anchor;
		MyList->anchor.prev=addobj;

		MyList->num_members++;
		return 1;
	}
	else
		return 0;
}

int  My402ListPrepend(My402List *MyList, void *object)
{
	if(MyList!=NULL)
	{
		My402ListElem *addobj;
		//addobj=malloc(sizeof *addobj);
		addobj=calloc(1,sizeof *addobj);
		if(addobj==NULL)
			return 0;
		addobj->obj=object;
		
		addobj->next=MyList->anchor.next;
		addobj->prev=&MyList->anchor;
		MyList->anchor.next->prev=addobj;
		MyList->anchor.next=addobj;

		MyList->num_members++;
		return 1;
	}
	else
		return 0;
}

void My402ListUnlink(My402List *MyList, My402ListElem *elem)
{
	elem->prev->next=elem->next;
	elem->next->prev=elem->prev;
	MyList->num_members--;
}

void My402ListUnlinkAll(My402List *MyList)
{
	My402ListElem *temp;
	temp=malloc(sizeof *temp);
	
	temp=MyList->anchor.next;
	while(temp->next!=MyList->anchor.next)
	{
		temp->prev=NULL;
		temp=temp->next;
		temp->prev->next=NULL;
	}
	MyList->anchor.next=NULL;
	MyList->anchor.prev=NULL;
	MyList->num_members=0;
}

int  My402ListInsertAfter(My402List *MyList, void *object, My402ListElem *elem)
{
	if(elem==NULL)
		My402ListAppend(MyList,object);
	else
	{
		My402ListElem *insertobj;
		insertobj=calloc(1,sizeof *insertobj);
		if(insertobj==NULL)
			return 0;
		insertobj->obj= object;
		
		elem->next->prev=insertobj;
		insertobj->next=elem->next;
		insertobj->prev=elem;
		elem->next=insertobj;
	}
	MyList->num_members++;
	return 1;
}

int  My402ListInsertBefore(My402List *MyList, void *object, My402ListElem *elem)
{
	if(elem==NULL)
		My402ListPrepend(MyList,object);
	else
	{
		My402ListElem *insertobj;
		//insertobj=malloc(sizeof *insertobj);
		insertobj=calloc(1,sizeof *insertobj);
		if(insertobj==NULL)
			return 0;
		insertobj->obj= object;
		
		elem->prev->next=insertobj;
		insertobj->next=elem;
		insertobj->prev=elem->prev;
		elem->prev=insertobj;
	}
	MyList->num_members++;
	return 1;
}

My402ListElem *My402ListFirst(My402List *MyList)
{
	if(MyList->anchor.next==&MyList->anchor && MyList->anchor.prev==&MyList->anchor )
		return NULL;
	else
	    return MyList->anchor.next;
}

My402ListElem *My402ListLast(My402List *MyList)
{
	if(MyList->anchor.next==&MyList->anchor && MyList->anchor.prev==&MyList->anchor)
		return NULL;
	else
	    return MyList->anchor.prev;
}

My402ListElem *My402ListNext(My402List *MyList, My402ListElem* elem)
{
	if(MyList->anchor.prev==elem)
		return NULL;
	else
	    return elem->next;
}

My402ListElem *My402ListPrev(My402List *MyList, My402ListElem* elem)
{
	if(MyList->anchor.next==elem)
		return NULL;
	else
	    return elem->prev;
}

My402ListElem *My402ListFind(My402List *MyList, void *object)
{
	My402ListElem *temp;
	temp=malloc(sizeof *temp);
	if(temp==NULL)
			return NULL;
	temp= MyList->anchor.next;
	while(temp->next!= MyList->anchor.next)	
	{
		if(temp->obj==object)
			return temp;
		else
		temp=temp->next;
	}
	if(MyList->anchor.prev->obj==object)
		return MyList->anchor.prev;
	else
		return NULL;
}

int My402ListInit(My402List *MyList)
{
	if(MyList==NULL)
			return 0;
	MyList->anchor.prev = &MyList->anchor;
	MyList->anchor.next = &MyList->anchor;
	MyList->anchor.obj=NULL;
	MyList->num_members=0;
	return 1;
}
	


		//MyList->anchor.prev->next= addobj;
		//addobj->prev=MyList->anchor.prev;
		//addobj->next=&MyList->anchor;
		//MyList->anchor.prev=addobj;;

