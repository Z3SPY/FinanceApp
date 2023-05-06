package Events;

import Events.node;
import Pages.pageFive.peopleObject;;


public class DoublyLinkedList {
        public node head = null;
        public node tail = null;
        public node nodeCur;

        public void addNodeFront(peopleObject val) {
            node newNode = new node(val);  


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
            node temp = nodeCur;
            temp = temp.prev;

            nodeCur = temp;
            
        }

        public void iterateBackward() {
            node temp = nodeCur;
            temp = temp.next;

            nodeCur = temp;
        }

        public void printAll() {
            //Node current will point to head  
            node current = head;  
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
