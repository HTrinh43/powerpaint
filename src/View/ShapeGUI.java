package View;

import java.awt.BorderLayout;
import java.awt.Image;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


import Actions.ColorChooserAction;
import Actions.ToolActions;

import ControllerTools.LineTool;
import ControllerTools.PaintTool;
import View.DrawingArea;

import Model.ToolType;

/**
 * A JFrame for drawing panel.
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class ShapeGUI extends JFrame implements PropertyChangeListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4266589551161261270L;

	/** The top level window for this application. */

    
    /** A panel for drawing shapes. */
    private final DrawingArea myPanel;
    
    private final ToolBarFrame myToolBar;
    
    private final MenuBar myMenuBar;
    /** A tool for drawing lines. */
    private final LineTool myLineTool;

    private final ColorChooserAction[] myColorActions;

    private final Map<PaintTool, ToolActions> myToolActions;

    /**
     * Initializes the shape GUI.
     */
    public ShapeGUI() {
        super("Power Paint");
        myPanel = new DrawingArea();

        myToolActions = new HashMap<PaintTool, ToolActions>();
        myColorActions = new ColorChooserAction[2];
        
        myColorActions[0] = new ColorChooserAction("Primary Color", myPanel);
        myColorActions[1] = new ColorChooserAction("Secondary Color", myPanel);
 
        for (final ToolType d : ToolType.values()) {
        	myToolActions.put(d.getTool(), new ToolActions(myPanel, d.getTool()));
        }
        myToolBar = new ToolBarFrame(myToolActions);
        myMenuBar = new MenuBar(myToolActions, myColorActions, myPanel);
        myLineTool = new LineTool();
        
        final List<Image> icons = createStatusBarIcon();
		setIconImages(icons);
        add(myPanel, BorderLayout.CENTER);
        add(myToolBar, BorderLayout.SOUTH);   
        this.setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    /**
     * 
     * @return a list of icon for status bar
     */
    private List<Image> createStatusBarIcon() {
        final ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/images/w.gif");
		//resize the icon and adding icon to
		final Image image = icon.getImage(); // transform it 
		final Image newImg16 = image.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);   
		final Image newImg32 = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH); 		
		final List<Image> icons = new ArrayList<Image>();
		icons.add(newImg16);
		icons.add(newImg32);
		return icons;
    }
    
    /**
     * Sets up the JFrame. 
     */
    public void start() {
       
        //set a line tool as the current tool 
        myPanel.setCurrentTool(myLineTool);
        
        this.setJMenuBar(myMenuBar);
        this.addPropertyChangeListener(this);
        this.myPanel.addPropertyChangeListener(myMenuBar);
        this.pack();

    }
    
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
}
