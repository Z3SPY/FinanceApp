package Events;

import Pages.pageFive.peopleObject;

;


public class cDoublyLinkedList {
        public cnode head = null;
        public cnode tail = null;
        public cnode nodeCur;

        public void addNodeFront(article val) {
            cnode newNode = new cnode(val);


                //if list is empty, head and tail points to newNode  
            if(head == null) {  
                head = tail = newNode;  
                //head's previous will be null  
                head.prev = null;  
                //tail's next will be null  
                tail.next = null;  
                
                //Sets Up Current Node
                nodeCur = head;
            }  
            else {  
                //add newNode to the end of list. tail->next set to newNode  
                tail.next = newNode;  
                //newNode->previous set to tail  
                newNode.prev = tail;  
                //newNode becomes new tail  
                tail = newNode;  
                
                //Loops
                tail.next = head;
                head.prev = tail;  
            } 
        }

        public void iterateForward() {
            cnode temp = nodeCur;
            temp = temp.prev;

            nodeCur = temp;
            
        }

        public void iterateBackward() {
            cnode temp = nodeCur;
            temp = temp.next;

            nodeCur = temp;
        }

        public void printAll() {
            //Node current will point to head  
            cnode current = head;
            if(head == null) {  
                System.out.println("List is empty");  
            }  
            else {  
                System.out.println("Nodes of the circular linked list: ");  
                do{  
                    //Prints each node by incrementing pointer.  
                    System.out.print(" "+ current.val);  
                    current = current.next;  
                }while(current != head);  
                System.out.println();  
            }  
        }
    
}
