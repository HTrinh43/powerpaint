package Actions;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import ControllerTools.PaintTool;
import Model.ToolType;
import View.DrawingArea;


public class ToolActions extends AbstractAction{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7033899212114438077L;

    private static final String ERROR_MESSAGE = "Resource not found: "; 
    
    private final PaintTool myTool;
    
    private final DrawingArea myDrawingArea;
    
    public ToolActions(final DrawingArea theDrawingArea, final PaintTool theTool) {
    	myDrawingArea = theDrawingArea;
    	myTool = theTool;
        putValue(NAME, myTool.getName());
        putValue(SHORT_DESCRIPTION, myTool.getName());
        putValue(SELECTED_KEY, true);
    }
    
//    private void setUpImage() {
//        final String imgLocation = "images/"
//                        + Character.toLowerCase(myDir.letter())
//                        + ".gif";
//
//        //When using ToolBarExample.class.getResource, runtime 
//        //is expecting the images to be in the same location as the 
//        //.class file for THIS class. In an eclipse project, that is
//        //bin/*package folder*
//        final URL imageURL = class.getResource(imgLocation);
//        if (Objects.nonNull(imageURL)) {                      
//            //image found
//            putValue(SMALL_ICON, new ImageIcon(imageURL, myDir.toString()));
//        } else {                                     
//            //no image found
//            System.err.println(ERROR_MESSAGE + imageURL);
//        }  
//    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		myDrawingArea.setCurrentTool(myTool);
	}

}
