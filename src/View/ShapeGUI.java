package View;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

import ControllerTools.LineTool;
import View.DrawingArea;
import Model.ToolType;

public class ShapeGUI implements PropertyChangeListener {
    /** The top level window for this application. */
    private final JFrame myFrame;
    
    /** A panel for drawing shapes. */
    private final DrawingArea myPanel;
    
    private final ToolBarFrame myToolBar;
    
    private final MenuBar myMenuBar;
    /** A tool for drawing lines. */
    private final LineTool myLineTool;
    
    public ShapeGUI() {
        myFrame = new JFrame();
        myPanel = new DrawingArea();
        myToolBar = new ToolBarFrame();
        myMenuBar = new MenuBar();
        myLineTool = new LineTool(ToolType.LINE, KeyEvent.VK_L);
        

    }
    
    public void start() {
        myFrame.add(myPanel, BorderLayout.CENTER);        
        myToolBar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myToolBar.setVisible(true);
        myPanel.setCurrentTool(myLineTool);
        final JToolBar toolBar = myToolBar.createToolBar();
        myFrame.add(toolBar, BorderLayout.SOUTH);
        
        JMenuBar myJmenubar = myMenuBar.createMenuBar();
        

        myFrame.setJMenuBar(myJmenubar);
        myFrame.pack();
    }
    
    private void addListener() {
    	
    }
    @Override
	public void propertyChange(PropertyChangeEvent evt) {
		myPanel.setPrimaryColor(myMenuBar.getPrimaryColor());
		System.out.println("get gere");
	}

}
