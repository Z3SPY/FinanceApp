package Elements;

import java.awt.Color;
import java.util.ArrayList;

import javax.management.modelmbean.ModelMBean;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import java.awt.event.*;
import java.awt.*;


import Events.navBarEventCallBack;
import Events.navBarEventMenu;
import Events.navBarEventSelected;
import Swing.Dashboard;

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
        setSelectionBackground(new Color(0, 0, 0, 0));

        this.setCellRenderer((ListCellRenderer<? super E>) getRenderer());
        
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

                    //Switch Pages
                    switch (index) {
                        case 0:
                            Dashboard.mainPanel.setSelectedIndex(0);
                        break;
                        case 1:
                            Dashboard.mainPanel.setSelectedIndex(1);
                        break;
                        case 2:
                            Dashboard.mainPanel.setSelectedIndex(2);
                        break;
                        default:
                        break;
                    }
                        
                }

                
            });
        }
        
    }

    private ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0,Color.BLACK));
                return listCellRendererComponent;
            }
        };
    }
}
