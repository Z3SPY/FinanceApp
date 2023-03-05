package Elements;

import java.awt.Color;
import java.util.ArrayList;

import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import Events.navBarEventSelected;

import java.util.List;


public class choiceList<E extends Object> extends JList<E> {
        
    private final DefaultListModel model;
    private final List<navBarEventSelected> events;
    private int selectedIndex =  -1;
    
    public choiceList(ArrayList<E> items) {
        setOpaque(false); //Transparancy
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.WHITE);

        model = new DefaultListModel();
        events = new ArrayList<navBarEventSelected>();
        
        if (items.get(0) instanceof Elements.navItem) {
            
            ArrayList<navItem> choiceList = (ArrayList<navItem>) items;

            for (navItem n : choiceList) {
                model.addElement(((Elements.navItem) n).getNavText());
            }
        }
        

        super.setModel(model);
        this.setFixedCellHeight(50);
    }
}
