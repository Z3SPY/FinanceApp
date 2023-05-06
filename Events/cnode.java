package Events;

import Pages.pageFive.peopleObject;

;


public class cnode {
    public cnode prev;
    public cnode next;
    public article val;

    public cnode(article data) {
        this.val = data;
    }
}
