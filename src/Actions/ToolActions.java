package Actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import ControllerTools.PaintTool;
import Model.ToolType;
import View.DrawingArea;
import View.ShapeGUI;


public class ToolActions extends AbstractAction{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7033899212114438077L;
    
    private final PaintTool myTool;
    
    private final DrawingArea myDrawingArea;
    
    public ToolActions(final DrawingArea theDrawingArea, final PaintTool theTool) {
    	myDrawingArea = theDrawingArea;
    	myTool = theTool;
        putValue(NAME, myTool.getName());
        putValue(SHORT_DESCRIPTION, myTool.getName());
        if (myTool.getName() == ToolType.LINE.toString()) {
            putValue(SELECTED_KEY, true);
        }
        else
        	putValue(SELECTED_KEY, false);

    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		myDrawingArea.setCurrentTool(myTool);
	}

}
