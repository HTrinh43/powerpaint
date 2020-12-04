package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

import Actions.ColorChooserAction;
import Actions.ToolActions;
import ControllerTools.EllipseTool;
import ControllerTools.LineTool;
import ControllerTools.PaintTool;
import ControllerTools.PencilTool;
import ControllerTools.RectangleTool;
import View.DrawingArea;

import Model.MutableDrawingObject;
import Model.ToolType;

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
    private LineTool myLineTool;

    private ColorChooserAction[] myColorActions;

    private final Map<PaintTool, ToolActions> myToolActions;

    
    public ShapeGUI() {
        super("Power Paint");
        myPanel = new DrawingArea();

        myToolActions = new HashMap();
        myColorActions = new ColorChooserAction[2];
        
        myColorActions[0] = new ColorChooserAction("Primary Color", myPanel);
        myColorActions[1] = new ColorChooserAction("Secondary Color", myPanel);
 
        for (final ToolType d : ToolType.values()) {
        	myToolActions.put(d.getTool(), new ToolActions(myPanel, d.getTool()));
        }
        myToolBar = new ToolBarFrame(myToolActions);
        
        myMenuBar = new MenuBar(myToolActions, myColorActions, myPanel);
        myLineTool = new LineTool();
        add(myPanel, BorderLayout.CENTER);
        add(myToolBar, BorderLayout.SOUTH);   
        this.setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void start() {
       
//        myToolBar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set tool
        myPanel.setCurrentTool(myLineTool);
//        final JToolBar toolBar = myToolBar.createToolBar();
     
//        JMenuBar myJmenubar = myMenuBar.createMenuBar();
        this.setJMenuBar(myMenuBar);
        this.addPropertyChangeListener(this);
        this.myPanel.addPropertyChangeListener(myMenuBar);
        this.pack();

    }
    
    private void addListener() {
    	
    }
    


	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		System.out.println("something changed");
	}
}
