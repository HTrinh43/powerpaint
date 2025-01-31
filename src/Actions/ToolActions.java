package Actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import ControllerTools.PaintTool;
import Model.ToolType;
import View.DrawingArea;

/**
 * An action for the Tool button.
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class ToolActions extends AbstractAction{
	
	/**
	 * A generated serial version UID for object Serialization. 
	 */
	private static final long serialVersionUID = 7033899212114438077L;
    
	/* The paint tool. */
    private final PaintTool myTool;
    
    /* The drawing area panel. */
    private final DrawingArea myDrawingArea;
    
    /*
     * Initializes the Actions fields.
     * 
     * @param theDrawingArea Jpanel this actions sets the tool on
     * @param theTool the tool for this action
     */
    public ToolActions(final DrawingArea theDrawingArea, final PaintTool theTool) {
    	myDrawingArea = theDrawingArea;
    	myTool = theTool;
        putValue(NAME, myTool.getName());
        putValue(SHORT_DESCRIPTION, myTool.getName());
        if (myTool.getName().equals(ToolType.LINE.toString())) {
            putValue(SELECTED_KEY, true);
        }
        else
        	putValue(SELECTED_KEY, false);

    }
    
    /**
     * Sets the current tool for the panel.
     * 
     * @param theEvent the action event
     */
	@Override
	public void actionPerformed(final ActionEvent theEvent) {
		myDrawingArea.setCurrentTool(myTool);
	}

}
