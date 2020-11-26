package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import Model.ColorChooser;
import Model.UWColors;


public class MenuBar {
	
	public MenuBar() {

		myPrimaryColorDisplayPanel = new JPanel();
		mySecondaryColorDisplayPanel = new JPanel();
		myPrimaryColor = UWColors.PURPLE.getColor();
		myPrimaryColor = UWColors.GOLD.getColor();
		myBackgroundDialog = null;
		setUp();
	}
	
	private JDialog myBackgroundDialog;
	
    private static final Dimension BUTTON_SIZE = new Dimension(26, 26);
	
    private JSlider mySlider;
    /** The panel that visually displays ONLY the BLUE value for the color. */
    private final JPanel myPrimaryColorDisplayPanel;
    
    private final JPanel mySecondaryColorDisplayPanel;
    
    private Color myPrimaryColor;
    
    private Color mySecondaryColor;
	
	public JMenuBar createMenuBar() {
		final JMenuBar menuBar = new JMenuBar();
		
		//setup Options menu
		final JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		
		menuBar.add(optionsMenu);
		//setup thickness slider
        final JMenu inThickness = new JMenu("Thickness");
        optionsMenu.add(inThickness);
        inThickness.add(mySlider);
        
        optionsMenu.addSeparator();

        final JMenuItem primaryColor = new JMenuItem("Primary Color");
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
			}
        	
        });
//        primaryColor.add(myPrimaryColorDisplayPanel);
//        primaryColor.revalidate();  // to invoke the layout manager
//        primaryColor.repaint();
        optionsMenu.add(primaryColor);
        optionsMenu.add(myPrimaryColorDisplayPanel);
        optionsMenu.add(mySecondaryColorDisplayPanel);
        
//        this.myPrimaryColor = myPrimaryColor1.getColor();
//        this.mySecondaryColor = mySecondaryColor2.getColor();
        
        menuBar.add(optionsMenu);
		return menuBar;
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
        };
        mySlider.addChangeListener(cl);

        myPrimaryColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
        myPrimaryColorDisplayPanel.setBackground(myPrimaryColor);
        
        mySecondaryColorDisplayPanel.setPreferredSize(BUTTON_SIZE);
        mySecondaryColorDisplayPanel.setBackground(mySecondaryColor);
        
	}
}
