package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.MutableDrawingObject;
import Model.ToolType;
import Model.UWColors;
import Actions.ColorChooserAction;
import Actions.ToolActions;
import ControllerTools.EllipseTool;
import ControllerTools.EraserTool;
import ControllerTools.LineTool;
import ControllerTools.PaintTool;
import ControllerTools.PencilTool;
import ControllerTools.RectangleTool;


public class MenuBar  extends JMenuBar implements ActionListener, PropertyChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2445544222417987308L;
	
	private final int DEFAULT_THICKNESS = 10;

	private final int MAXIMUM_SLIDER_VALUE = 20;
	
	private final int MINIMUM_SLIDER_VALUE = 0;
	//Create a background dialog 
    private static final Dimension BUTTON_SIZE = new Dimension(26, 26);
	
    private JSlider mySlider;
    /** The panel that visually displays ONLY the BLUE value for the color. */
    private final JPanel myPrimaryColorDisplayPanel; 
    
    private final JPanel mySecondaryColorDisplayPanel;    
    
    private Color myPrimaryColor;   
    
    private Color mySecondaryColor;
    
    private int myThickness;
    
    private Map<PaintTool, ToolActions> myMap;
    
//    private ShapeGUI myPane;
    
	public MenuBar(final Map<PaintTool, ToolActions> theMap, final ColorChooserAction[] theColor) {
		myPrimaryColor = UWColors.PURPLE.getColor();
		mySecondaryColor = UWColors.GOLD.getColor();
		myThickness = DEFAULT_THICKNESS;
		myPrimaryColorDisplayPanel = new JPanel();
		mySecondaryColorDisplayPanel = new JPanel();
		setUp();
		this.add(createMenuBar(theMap, theColor));
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

    

	private void setUp() {
		//setup Slider
        mySlider = new JSlider(MINIMUM_SLIDER_VALUE, MAXIMUM_SLIDER_VALUE, myThickness);
        mySlider.setPaintTicks(true);
        mySlider.setPaintLabels(true);
        mySlider.setMinorTickSpacing(1);
        mySlider.setMajorTickSpacing(5);
        ChangeListener cl = e -> {
            JSlider x = (JSlider) e.getSource();
            int thickness =  x.getValue();       
//            myDrawingObject.setThickness(thickness);
        };
        mySlider.addChangeListener(cl);

        myPrimaryColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
        myPrimaryColorDisplayPanel.setBackground(myPrimaryColor);

        mySecondaryColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
        mySecondaryColorDisplayPanel.setBackground(mySecondaryColor);
        
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
//	private JMenu createToolMenu() {
//		JMenu menu = new JMenu("Tools");
//		ArrayList<JRadioButton> buttonList = new ArrayList<>();
//		buttonList.add(createRadioButton("Pencil", true, new MenuBarAction(ToolType.PENCIL), KeyEvent.VK_P));
//		buttonList.add(createRadioButton("Line", false, new MenuBarAction(ToolType.LINE), KeyEvent.VK_L));
//		buttonList.add(createRadioButton("Rectangle", false, new MenuBarAction(ToolType.RECTANGLE), KeyEvent.VK_R));
//		buttonList.add(createRadioButton("Ellipse", false, new MenuBarAction(ToolType.ELLIPSE), KeyEvent.VK_E));
//		buttonList.add(createRadioButton("Eraser", false, new MenuBarAction(ToolType.ERASER), KeyEvent.VK_A));
//		
//		final ButtonGroup btngrp = new ButtonGroup();
//		for (JRadioButton button : buttonList) {
//			btngrp.add(button);
//			menu.add(button);
//		}
//		myButtonGroup = btngrp;
//		return menu;
//	}
	
	private JMenu createOptionMenu(final ColorChooserAction[] theColor) {
		final JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		//setup thickness slider
        final JMenu inThickness = new JMenu("Thickness");
        inThickness.add(mySlider);
        inThickness.setMnemonic(KeyEvent.VK_T);
        
        final JMenuItem primaryColor = buildColorMenu(theColor[0]);
        final JMenuItem secondaryColor = buildColorMenu(theColor[1]);
        final JMenuItem clear = new JMenuItem("Clear");
        
        clear.setMnemonic(KeyEvent.VK_C);
        clear.setEnabled(false);
        optionsMenu.add(inThickness);
        optionsMenu.addSeparator();
        optionsMenu.add(primaryColor);
        optionsMenu.add(secondaryColor);
        optionsMenu.addSeparator();
        optionsMenu.add(clear);
        
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
	
//	private JRadioButton createRadioButton(String theName, boolean theBoolean, ActionListener theListener, int theMnemonic) {
//		JRadioButton button = new JRadioButton(theName, theBoolean);
//		button.addActionListener(theListener);
//		button.setMnemonic(theMnemonic);
//		
//		return button;
//	}
	
	public Color getPrimaryColor() {
		return this.myPrimaryColor;
	}
	
	public Color getSecondaryColor() {
		return this.mySecondaryColor;
	}
	
	public void stateChanged(ChangeEvent e) {
		myPrimaryColorDisplayPanel.setBackground(myPrimaryColor);
	}
	
//	class MenuBarAction implements ActionListener {
//
//		private final ToolType myTool;
//		MenuBarAction (final ToolType theTool){
//			this.myTool = theTool;
//		}
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			if(this.myTool == ToolType.LINE) {
//				myDrawingObject.setCurrentTool(new LineTool());
//        	}
//        	else if(this.myTool == ToolType.ELLIPSE) {
//        		myDrawingObject.setCurrentTool(new EllipseTool());
//        	}
//        	else if(this.myTool == ToolType.ERASER) {
//        		myDrawingObject.setCurrentTool(new EraserTool());
//        	}
//        	else if(this.myTool == ToolType.PENCIL) {
//        		myDrawingObject.setCurrentTool(new PencilTool());
//        	}
//        	else if(this.myTool == ToolType.RECTANGLE) {
//        		myDrawingObject.setCurrentTool(new RectangleTool());
//        	}	
//		}
//		
//	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
