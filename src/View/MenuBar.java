package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import Model.ToolType;
import Actions.ColorChooserAction;
import Actions.ToolActions;

import ControllerTools.PaintTool;



public class MenuBar  extends JMenuBar implements ActionListener, PropertyChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2445544222417987308L;
	
	private final int DEFAULT_THICKNESS = 10;

	private final int MAXIMUM_SLIDER_VALUE = 20;
	
	private final int MINIMUM_SLIDER_VALUE = 0;
	
	private JSlider mySlider;
	
	private JMenuItem myClearItem;
	
	private final DrawingArea myDrawingArea;
   
	public MenuBar(final Map<PaintTool, ToolActions> theMap, final ColorChooserAction[] theColor, final DrawingArea theDrawingArea) {
		myDrawingArea = theDrawingArea;
		createSlider(myDrawingArea);
		JMenuBar inMenuBar = createMenuBar(theMap, theColor);
		add(inMenuBar);
	}
	
	public JMenuBar createMenuBar(final Map<PaintTool, ToolActions> theMap, final ColorChooserAction[] theColor) {
		final JMenuBar menuBar = new JMenuBar();
		
        final JMenu optionsMenu = createOptionMenu(theColor);
		//Tools Menu
		final JMenu toolsMenu = createToolMenu(theMap);
        //Help Menu
		final JMenu helpMenu = createHelpMenu();
                
        menuBar.add(optionsMenu);
		menuBar.add(toolsMenu);
		menuBar.add(helpMenu);
		return menuBar;
	}

	private void createSlider(final DrawingArea theDrawingArea) {
		//setup Slider
        mySlider = new JSlider(MINIMUM_SLIDER_VALUE, MAXIMUM_SLIDER_VALUE, DEFAULT_THICKNESS);
        mySlider.setPaintTicks(true);
        mySlider.setPaintLabels(true);
        mySlider.setMinorTickSpacing(1);
        mySlider.setMajorTickSpacing(5);
        ChangeListener cl = e -> {
            JSlider x = (JSlider) e.getSource();
            int thickness =  x.getValue();       
            theDrawingArea.setThickness(thickness);
        };
        mySlider.addChangeListener(cl);
	}
	

		
	private JMenu createOptionMenu(final ColorChooserAction[] theColor) {
		final JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		//setup thickness slider
        final JMenu inThickness = new JMenu("Thickness");
        inThickness.add(mySlider);
        inThickness.setMnemonic(KeyEvent.VK_T);
        
        final JMenuItem primaryColor = buildColorMenu(theColor[0]);
        final JMenuItem secondaryColor = buildColorMenu(theColor[1]);
        myClearItem = new JMenuItem("Clear");
        
        myClearItem.setMnemonic(KeyEvent.VK_C);
//        myClearItem.setEnabled(false);
        myClearItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	myDrawingArea.clear();
            }
        });
        
        optionsMenu.add(inThickness);
        optionsMenu.addSeparator();
        optionsMenu.add(primaryColor);
        optionsMenu.add(secondaryColor);
        optionsMenu.addSeparator();
        optionsMenu.add(myClearItem);
        
		return optionsMenu;
	}
	
    private JMenuItem buildColorMenu(final ColorChooserAction theColorAction) {
        final JMenuItem chooseColorItem = new JMenuItem(theColorAction);
        return chooseColorItem;
    } 
	
	private JMenu createToolMenu(final Map<PaintTool, ToolActions> theMap ) {
		final JMenu menu = new JMenu("Tools");
        menu.setMnemonic('T');
        final ButtonGroup toolGroup = new ButtonGroup();
        for (final PaintTool p : theMap.keySet()) {
            final JRadioButtonMenuItem item = new JRadioButtonMenuItem(theMap.get(p));
            menu.add(item);
            toolGroup.add(item);
        }
        return menu;
	}
	
	private JMenu createHelpMenu() {
		final JMenu helpMenu = new JMenu("Help");
        final JMenuItem about = new JMenuItem("About");
        
        helpMenu.setMnemonic(KeyEvent.VK_H);
        about.setMnemonic(KeyEvent.VK_A);
        //https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#create
        helpMenu.add(about);
        about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(about,
					    "Alex Trinh \n" 
						+ "Autumn 2020 \n" 
					    + "TCSS 305 Assignment 4",
					    "About",
					    JOptionPane.PLAIN_MESSAGE);
			}	
        });
		return helpMenu;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if ("CLEAR".equals(evt.getPropertyName())) {
            myClearItem.setEnabled((boolean) evt.getNewValue());
        }
	}
}
