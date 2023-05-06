package Events;
import Pages.pageFive;
import Pages.pageFive.peopleObject;;


public class node {
    public node prev;
    public node next;
    public peopleObject val;

    public node(peopleObject data) {
        this.val = data;
    }
}
