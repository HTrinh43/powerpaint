package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
import ControllerTools.LineTool;
import ControllerTools.PaintTool;
import ControllerTools.PencilTool;
import ControllerTools.RectangleTool;


public class MenuBar extends JFrame implements PropertyChangeListener, ActionListener{
	
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
        
        final JMenuItem primaryColor = new JMenuItem("Primary Color");
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
        	
        final JMenuItem clear = new JMenuItem("Clear");
        clear.setMnemonic(KeyEvent.VK_C);
        optionsMenu.add(inThickness);
        optionsMenu.addSeparator();
        optionsMenu.add(primaryColor);
        optionsMenu.addSeparator();
        optionsMenu.add(clear);
        
//        optionsMenu.add(primaryColorPanel);

        
        
		//Tools Menu
		final JMenu toolsMenu = new JMenu("Tools");
		toolsMenu.setMnemonic(KeyEvent.VK_T);
		//create tool buttons
        final JRadioButton pencilB = new JRadioButton("Pencil", true);
        final JRadioButton lineB = new JRadioButton("Line", false);
        final JRadioButton rectangleB = new JRadioButton("Rectangle", false);
        final JRadioButton ellipseB = new JRadioButton("Ellipse", false);
        final JRadioButton erazerB = new JRadioButton("Erazer", false);
		
        pencilB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new background dialog to choose a color
				myCurrentTool = new PencilTool(ToolType.PENCIL, KeyEvent.VK_P);
				myPane.setCurrentTool(myCurrentTool);
			}});      
        
        lineB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new background dialog to choose a color
				myCurrentTool = new LineTool(ToolType.LINE, KeyEvent.VK_L);
				myPane.setCurrentTool(myCurrentTool);
			}}); 
        
        rectangleB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new background dialog to choose a color
				myCurrentTool = new RectangleTool(ToolType.RECTANGLE, KeyEvent.VK_R);
				myPane.setCurrentTool(myCurrentTool);
			}}); 
        
        ellipseB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//new background dialog to choose a color
				myCurrentTool = new EllipseTool(ToolType.ELLIPSE, KeyEvent.VK_E);
				myPane.setCurrentTool(myCurrentTool);
			}}); 
        
        pencilB.setMnemonic(KeyEvent.VK_P);
        lineB.setMnemonic(KeyEvent.VK_L);
        rectangleB.setMnemonic(KeyEvent.VK_R);
        ellipseB.setMnemonic(KeyEvent.VK_E);
        erazerB.setMnemonic(KeyEvent.VK_A);
        
        //add listener
        pencilB.addActionListener(this);
        lineB.addActionListener(this);
        rectangleB.addActionListener(this);
        ellipseB.addActionListener(this);
        erazerB.addActionListener(this);
        
        //groups tool buttons
        final ButtonGroup group = new ButtonGroup();
        group.add(pencilB);
        group.add(lineB);
        group.add(rectangleB);
        group.add(ellipseB);
        group.add(erazerB);
        
        toolsMenu.add(pencilB);
        toolsMenu.add(lineB);
        toolsMenu.add(rectangleB);
        toolsMenu.add(ellipseB);
        toolsMenu.add(erazerB);
        
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
	
	private JMenuItem createNewMenuItem(JMenu theMenu, String theText, int theKey) {
		JMenuItem menuItem = new JMenuItem(theText);
		menuItem.setMnemonic(theKey);
		theMenu.add(menuItem);
		return menuItem;
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
        mySlider = new JSlider(0, 20,10);
        mySlider.setPaintTicks(true);
        mySlider.setPaintLabels(true);
        mySlider.setMinorTickSpacing(1);
        mySlider.setMajorTickSpacing(5);
        ChangeListener cl = e -> {
            JSlider x = (JSlider) e.getSource();
            x.getValue();
        };
        mySlider.addChangeListener(cl);

        myPrimaryColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
        myPrimaryColorDisplayPanel.setBackground(myPrimaryColor);

        mySecondaryColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
        mySecondaryColorDisplayPanel.setBackground(mySecondaryColor);
        
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
//		if (e.getSource()== )
	}
}
