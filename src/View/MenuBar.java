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
import Model.UWColors;
import ControllerTools.PaintTool;


public class MenuBar extends JPanel implements PropertyChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2445544222417987308L;


	public MenuBar() {

		myPrimaryColorDisplayPanel = new JPanel();
		mySecondaryColorDisplayPanel = new JPanel();
		myPrimaryColor = UWColors.PURPLE.getColor();
		myPrimaryColor = UWColors.GOLD.getColor();
		myBackgroundDialog = null;
		setUp();
	}
	//Create a background dialog 
	private JDialog myBackgroundDialog;
	
    private static final Dimension BUTTON_SIZE = new Dimension(26, 26);
	
    private JSlider mySlider;
    /** The panel that visually displays ONLY the BLUE value for the color. */
    private final JPanel myPrimaryColorDisplayPanel;
    
    private final JPanel mySecondaryColorDisplayPanel;
    
    private Color myPrimaryColor;
    
    private Color mySecondaryColor;
    
    private PaintTool myCurrentTool;
    
	
	public JMenuBar createMenuBar() {
		final JMenuBar menuBar = new JMenuBar();
		
		//setup Options menu
		final JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		
		//setup thickness slider
        final JMenu inThickness = new JMenu("Thickness");
        inThickness.add(mySlider);
        inThickness.setMnemonic(KeyEvent.VK_T);
        

//        JPanel primaryColorPanel = new JPanel();
//        primaryColorPanel.add(myPrimaryColorDisplayPanel);
//        primaryColorPanel.add(new JLabel("Primary Color"));
        //setup Color Choose for Primary Color
        final JMenuItem primaryColor = new JMenuItem("Primary Color");
        primaryColor.setMnemonic(KeyEvent.VK_P);
        //Primary listener
        primaryColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JColorChooser choosenColor = new JColorChooser();
				//new background dialog to choose a color
				myBackgroundDialog = JColorChooser.createDialog
						(primaryColor,
								"Choose a Color",
								false,
								choosenColor,
								new ActionListener()
								{public void actionPerformed(ActionEvent evvv){
									myPrimaryColorDisplayPanel.setBackground(choosenColor.getColor());}},
								null);	
				myBackgroundDialog.setVisible(true);
			}});      
        
        final JMenuItem clear = new JMenuItem("Clear");
        clear.setMnemonic(KeyEvent.VK_C);
        optionsMenu.add(inThickness);
        optionsMenu.addSeparator();
        optionsMenu.add(primaryColor);
        optionsMenu.add(mySecondaryColorDisplayPanel);
        optionsMenu.addSeparator();
        optionsMenu.add(clear);
//        optionsMenu.add(primaryColorPanel);

        
        
		//Tools Menu
		final JMenu toolsMenu = new JMenu("Tools");
		toolsMenu.setMnemonic(KeyEvent.VK_T);
		
        final JRadioButton pencilB = new JRadioButton("Pencil", true);
        final JRadioButton lineB = new JRadioButton("Line", false);
        final JRadioButton rectangleB = new JRadioButton("Rectangle", false);
        final JRadioButton ellipseB = new JRadioButton("Ellipse", false);
        final JRadioButton erazerB = new JRadioButton("Erazer", false);
		
        pencilB.setMnemonic(KeyEvent.VK_P);
        lineB.setMnemonic(KeyEvent.VK_L);
        rectangleB.setMnemonic(KeyEvent.VK_R);
        ellipseB.setMnemonic(KeyEvent.VK_E);
        erazerB.setMnemonic(KeyEvent.VK_A);
        
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
	
	private void addListener() {
		
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
}
