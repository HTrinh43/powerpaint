package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

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
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Model.ToolType;
import Model.UWColors;
import ControllerTools.EllipseTool;
import ControllerTools.EraserTool;
import ControllerTools.LineTool;
import ControllerTools.PaintTool;
import ControllerTools.PencilTool;
import ControllerTools.RectangleTool;


public class MenuBar implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2445544222417987308L;

	//Create a background dialog 
    private static final Dimension BUTTON_SIZE = new Dimension(26, 26);
	
    private JSlider mySlider;
    /** The panel that visually displays ONLY the BLUE value for the color. */
    private final JPanel myPrimaryColorDisplayPanel; 
    
    private final JPanel mySecondaryColorDisplayPanel;    
    
    private Color myPrimaryColor;   
    
    private Color mySecondaryColor;
    
    private PaintTool myCurrentTool;
    
    private DrawingArea myPane;
    
    
    
	public MenuBar(DrawingArea thePane) {
		myPane = thePane;
		myPrimaryColorDisplayPanel = new JPanel();
		mySecondaryColorDisplayPanel = new JPanel();
		myPrimaryColor = UWColors.PURPLE.getColor();
		mySecondaryColor = UWColors.GOLD.getColor();
		setUp();
	}
	
	public JMenuBar createMenuBar() {
		final JMenuBar menuBar = new JMenuBar();
		
		//setup Options menu
		final JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		
		//setup thickness slider
        final JMenu inThickness = new JMenu("Thickness");
        inThickness.add(mySlider);
        inThickness.setMnemonic(KeyEvent.VK_T);
        
        final JMenuItem primaryColor = new JMenuItem("Primary Color...");
        primaryColor.setMnemonic(KeyEvent.VK_P);
        //Primary listener
        primaryColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new background dialog to choose a color
				Color color = JColorChooser.showDialog
						(null,"Choose a Color", UWColors.PURPLE.getColor());
						myPrimaryColor = color;
						myPane.setPrimaryColor(myPrimaryColor);
			}});      
        
        final JMenuItem secondaryColor = new JMenuItem("Secondary Color...");
        secondaryColor.setMnemonic(KeyEvent.VK_S);
        //Primary listener
        secondaryColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new background dialog to choose a color
				Color color = JColorChooser.showDialog
						(null,"Choose a Color", UWColors.PURPLE.getColor());
						mySecondaryColor = color;
						myPane.setSecondaryColor(mySecondaryColor);
			}});      
        	
        final JMenuItem clear = new JMenuItem("Clear");
        clear.setMnemonic(KeyEvent.VK_C);
        clear.setEnabled(false);
        optionsMenu.add(inThickness);
        optionsMenu.addSeparator();
        optionsMenu.add(primaryColor);
        optionsMenu.add(secondaryColor);
        optionsMenu.addSeparator();
        optionsMenu.add(clear);
        
//        optionsMenu.add(primaryColorPanel);

        
        
		//Tools Menu
		final JMenu toolsMenu = this.createToolMenu();
		toolsMenu.setMnemonic(KeyEvent.VK_T);
        
        //Help Menu
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
               
        menuBar.add(optionsMenu);
		menuBar.add(toolsMenu);
		menuBar.add(helpMenu);
		return menuBar;
	}

    public void setCurrentTool(final PaintTool theTool) {
        myCurrentTool = theTool;
    }
    
	public Color getPrimaryColor() {
		return this.myPrimaryColor;
	}
	
	public Color getSecondaryColor() {
		return this.mySecondaryColor;
	}
	
	public void stateChanged(ChangeEvent e) {
		myPrimaryColorDisplayPanel.setBackground(myPrimaryColor);
	}
	
	private void setUp() {
		//setup Slider
        mySlider = new JSlider(0, 20, 1);
        mySlider.setPaintTicks(true);
        mySlider.setPaintLabels(true);
        mySlider.setMinorTickSpacing(1);
        mySlider.setMajorTickSpacing(5);
        ChangeListener cl = e -> {
            JSlider x = (JSlider) e.getSource();
            int thickness =  x.getValue();       
            myPane.setThickness(thickness);
            myPane.setCursorThickness();
            
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
	
	private JMenu createToolMenu() {
		JMenu menu = new JMenu("Tools");
		ArrayList<JRadioButton> buttonList = new ArrayList<>();
		buttonList.add(createRadioButton("Pencil", true, new MenuBarAction(ToolType.PENCIL), KeyEvent.VK_P));
		buttonList.add(createRadioButton("Line", false, new MenuBarAction(ToolType.LINE), KeyEvent.VK_L));
		buttonList.add(createRadioButton("Rectangle", false, new MenuBarAction(ToolType.RECTANGLE), KeyEvent.VK_R));
		buttonList.add(createRadioButton("Ellipse", false, new MenuBarAction(ToolType.ELLIPSE), KeyEvent.VK_E));
		buttonList.add(createRadioButton("Eraser", false, new MenuBarAction(ToolType.ERASER), KeyEvent.VK_A));
		
		final ButtonGroup btngrp = new ButtonGroup();
		for (JRadioButton button : buttonList) {
			btngrp.add(button);
			menu.add(button);
		}
		return menu;
	}
	
	private JRadioButton createRadioButton(String theName, boolean theBoolean, ActionListener theListener, int theMnemonic) {
		JRadioButton button = new JRadioButton(theName, theBoolean);
		button.addActionListener(theListener);
		button.setMnemonic(theMnemonic);
		return button;
	}
	class MenuBarAction implements ActionListener {

		private final ToolType myTool;
		MenuBarAction (final ToolType theTool){
			this.myTool = theTool;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(this.myTool == ToolType.LINE) {
        		myPane.setCurrentTool(new LineTool(myTool, KeyEvent.VK_L));
        	}
        	else if(this.myTool == ToolType.ELLIPSE) {
        		myPane.setCurrentTool(new EllipseTool(myTool, KeyEvent.VK_E));
        	}
        	else if(this.myTool == ToolType.ERASER) {
        		myPane.setCurrentTool(new EraserTool(myTool, KeyEvent.VK_S));
        	}
        	else if(this.myTool == ToolType.PENCIL) {
        		myPane.setCurrentTool(new PencilTool(myTool, KeyEvent.VK_P));
        	}
        	else if(this.myTool == ToolType.RECTANGLE) {
        		myPane.setCurrentTool(new RectangleTool(myTool, KeyEvent.VK_R));
        	}	
		}
		
	}
}
