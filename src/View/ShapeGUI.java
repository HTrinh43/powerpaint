package View;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

import ControllerTools.EllipseTool;
import ControllerTools.LineTool;
import ControllerTools.PencilTool;
import ControllerTools.RectangleTool;
import View.DrawingArea;
import Model.ToolType;

public class ShapeGUI extends JFrame {
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
    private PencilTool myPencilTool;

    
    public ShapeGUI() {
        super("Power Paint");
        myPanel = new DrawingArea();
        myToolBar = new ToolBarFrame(myPanel);
        myMenuBar = new MenuBar(myPanel);
        myPencilTool = new PencilTool(ToolType.PENCIL, KeyEvent.VK_P);
        add(myPanel, BorderLayout.CENTER);
        this.setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void start() {
       
        myToolBar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set tool
        myPanel.setCurrentTool(myPencilTool);
        final JToolBar toolBar = myToolBar.createToolBar();
        this.add(toolBar, BorderLayout.SOUTH);        
        JMenuBar myJmenubar = myMenuBar.createMenuBar();
        this.setJMenuBar(myJmenubar);
    }
}
