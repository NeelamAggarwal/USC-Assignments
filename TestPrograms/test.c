
#include <string.h>

struct node
{
    int data;
    struct node* next;
} node;
    
struct node *call(struct node* curr, struct node* prev)
{
    struct node* next;
    if(curr == NULL)
        return prev;
    next = curr->next; curr->next = prev;
    call(next, curr);
}
int main()
{
    struct node* root;
    struct node* root1;
    struct node* root2;

   // struct node* root3;
    root =  malloc(sizeof(*root));
    root1 = malloc(sizeof(*root1));
    root2 = malloc(sizeof(*root2));
//    root3 = malloc(sizeof(*root3));
    root->data = 2;
    root->next = root1;
    root1->data = 3;
    root1->next = root2;
    root2->data = 4;

root2->next = NULL;
  //  root3->data = 5;
    struct node* returned;



returned = malloc(sizeof(*returned));   
 struct node* prev =NULL;
returned = call(root, prev);

  while(returned!=NULL) 
	{


		 printf("%d",returned->data);
returned = returned->next;	
}   return 0;
}



