package Elements;

import java.awt.Color;
import java.util.ArrayList;

import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import java.awt.event.*;

import Events.navBarEventCallBack;
import Events.navBarEventMenu;
import Events.navBarEventSelected;

import java.util.List;


public class choiceList<E extends Object> extends JList<E> {
        
    public void addEventMenu(navBarEventSelected event) {
        events.add(event);
    }

    private final DefaultListModel model;
    private final List<navBarEventSelected> events;
    private int selectedIndex =  -1;
    
    public choiceList() {
        setOpaque(false); //Transparancy
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.WHITE);
        setFixedCellHeight(50);


        model = new DefaultListModel();
        events = new ArrayList<navBarEventSelected>();
        
        
        

        super.setModel(model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    int index = locationToIndex(me.getPoint());
                    Object obj = model.getElementAt(index);
                    if (obj instanceof navItem) {
                        navItem data = (navItem) obj;
                        if (data.getType() == navItem.MenuType.MENU) {
                            if (index != selectedIndex) {
                                selectedIndex = -1;
                                runEvent(index);
                            }
                        }
                    } else {
                        if (index != selectedIndex) {
                            selectedIndex = -1;
                            runEvent(index);

                        }
                    }
                    
                }
            }
        });



    }

    public void populate(ArrayList<E> items) {
        if (items.get(0) instanceof Elements.navItem) {
            
            ArrayList<navItem> choiceList = (ArrayList<navItem>) items;

            for (navItem n : choiceList) {
                model.addElement(((Elements.navItem) n).getNavText());
            }
        }

    }

    
    private void runEvent(int indexChange) {
        for (navBarEventSelected event : events) {
            event.menuSelected(indexChange, new navBarEventCallBack() {
                
                @Override
                public void call(int index) {
                    // This call back even run when animation is 
                    selectedIndex = index;
                    repaint();
                }

                
            });
        }
        
    }
}
