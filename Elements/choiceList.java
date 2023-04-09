package Elements;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.management.modelmbean.ModelMBean;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import java.awt.event.*;
import java.lang.annotation.Native;
import java.awt.*;


import Events.navBarEventCallBack;
import Events.navBarEventMenu;
import Events.navBarEventSelected;
import Swing.Dashboard;

import java.util.List;
import java.util.Map;


public class choiceList<E extends Object> extends JList<E> {
        
    public void addEventMenu(navBarEventSelected event) {
        events.add(event);
    }

    private final DefaultListModel<navItem> model;
    private final List<navBarEventSelected> events;
    private int selectedIndex =  -1;
    
    public choiceList() {
        setOpaque(false); //Transparancy
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.black);
        setFixedCellHeight(50);
        setSelectionBackground(new Color(0, 0, 0, 0)); //Makes Selected Background Opaque
        setSelectionForeground(Color.decode("#e4b348")); // Makes Selected Text Blue

        this.setCellRenderer((ListCellRenderer<? super E>) getRenderer());
        
        model = new DefaultListModel<navItem>();
        events = new ArrayList<navBarEventSelected>();
        
        
        

        super.setModel((ListModel<E>) model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    int index = locationToIndex(me.getPoint());
                    Object obj = model.getElementAt(index);
                    System.out.println(obj);
                    if (obj instanceof navItem) {
                        navItem data = (navItem) obj;
                        if (data.getType() == navItem.MenuType.MENU) {
                            if (index != selectedIndex) {
                                System.out.print("o");
                                runEvent(index);
                                switchPage(index);
                            }
                        }
                    } else {
                        if (index != selectedIndex) {
                            selectedIndex = -1;
                            //runEvent(index);
                            //switchPage(index);
                            

                        }
                    }
                    
                }
            }
        });



    }

    public void switchPage(int index){
        //Switch Pages
        repaint();
        switch (index) {
            case 0, 1, 2, 3, 4:
                Dashboard.mainPanel.setSelectedIndex(index);
                break;
            default:
                Dashboard.mainPanel.setSelectedIndex(4);
                break;
        }

    }

    public void populate(ArrayList<E> items) {
        if (items.get(0) instanceof Elements.navItem) {
            
            ArrayList<navItem> choiceList = (ArrayList<navItem>) items;

            for (navItem n : choiceList) {
                model.addElement(((Elements.navItem) n));
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



    private Map<String, ImageIcon> iconCache = new HashMap<>(); 
    private Map<String, ImageIcon> iconSlctCache = new HashMap<>();


    private ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {

                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0,Color.BLACK));  

                if (value instanceof navItem) {
                    navItem temp = (navItem) value;

                    listCellRendererComponent.setText(temp.getNavText());

                    if (!iconCache.containsKey(temp.getNavText())) {
                        iconCache.put(temp.getNavText(), temp.getNavIcon()); // Helps With Lag, Stores the Icon Image Inside of Hash Map To Prevent Reloading
                        iconSlctCache.put(temp.getNavText(), temp.getSelectedIcon());
                    }
                    

                    if (!isSelected)
                    listCellRendererComponent.setIcon(iconCache.get(temp.getNavText()));    
                    else 
                    listCellRendererComponent.setIcon(iconSlctCache.get(temp.getNavText()));    
                    

                }

                

                return listCellRendererComponent;
            }
        };
    }

 
    public void switchPageGlobal(int index) {
        if (index != selectedIndex) {
            System.out.print("o");
            runEvent(index);
            switchPage(index);
        }
    }
}
