package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Actions.ToolActions;
import ControllerTools.EllipseTool;
import ControllerTools.EraserTool;
import ControllerTools.LineTool;
import ControllerTools.PaintTool;
import ControllerTools.PencilTool;
import ControllerTools.RectangleTool;
import Model.MutableDrawingObject;
import Model.ToolType;
import Model.UWColors;
import View.ToolBarFrame;


public class ToolBarFrame extends JToolBar {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -4447570618384470786L;
 
    // Constructor

    /**
     * Constructs a new ToolBarFrame with all its controls.
     */
    public ToolBarFrame(final Map<PaintTool, ToolActions> theMap) {
//        setupActions(theMap); // initializes myActions
    	super();
        final ButtonGroup toolGroup = new ButtonGroup(); 
        
        for (final PaintTool p : theMap.keySet()) {

            final JRadioButtonMenuItem item = new JRadioButtonMenuItem(theMap.get(p));
            System.out.println(setUpImage(p));
            item.setIcon(new ImageIcon(setUpImage(p), p.getName()));
            add(item);
            toolGroup.add(item);
        }
    }

    /**
     * @return a fully-stocked tool bar.
     */
    private URL setUpImage(final PaintTool theTool) {
        final String imgLocation = System.getProperty("user.dir") + "/images/"
                        + theTool.getName().toLowerCase()
                        + ".gif";
        
        URL imageURL = null;
		try {
			imageURL = new File(imgLocation).toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (Objects.nonNull(imageURL)) {                      
            //image found
        	return imageURL;
        } else {   
        	System.err.println("No Image Found");
            return imageURL;
        }  
    }

}