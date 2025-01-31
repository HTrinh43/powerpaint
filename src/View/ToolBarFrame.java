package View;

import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;


import Actions.ToolActions;
import ControllerTools.PaintTool;
import Model.ToolType;
import View.ToolBarFrame;

/**
 * Creates the tool bar frame for the drawing panel.
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class ToolBarFrame extends JToolBar {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -4447570618384470786L;
 

    /**
     * Constructs a new ToolBarFrame with all its controls.
     */
    public ToolBarFrame(final Map<PaintTool, ToolActions> theMap) {
    	super();
        final ButtonGroup toolGroup = new ButtonGroup(); 
        
        final Map<String, Integer> inMnemonicKeys = new HashMap<String, Integer>();
        inMnemonicKeys.put(ToolType.LINE.toString(), KeyEvent.VK_L);
        inMnemonicKeys.put(ToolType.PENCIL.toString(), KeyEvent.VK_P);
        inMnemonicKeys.put(ToolType.ELLIPSE.toString(), KeyEvent.VK_E);
        inMnemonicKeys.put(ToolType.RECTANGLE.toString(), KeyEvent.VK_R);
        inMnemonicKeys.put(ToolType.ERASER.toString(), KeyEvent.VK_A);
        
        for (final PaintTool p : theMap.keySet()) {

            final JRadioButtonMenuItem item = new JRadioButtonMenuItem(theMap.get(p));
            item.setIcon(new ImageIcon(setUpImage(p), p.getName()));
            final int inMnemonic = inMnemonicKeys.get(p.getName());
            item.setMnemonic(inMnemonic);
            add(item);
            toolGroup.add(item);
        }
    }

    /**
     * Setup the image for the tool bar.
     * 
     * @return a fully-stocked tool bar.
     */
    private URL setUpImage(final PaintTool theTool) {
        final String inImgLocation = System.getProperty("user.dir") + "/images/"
                        + theTool.getName().toLowerCase(Locale.ENGLISH)
                        + ".gif";
        
        URL inImageURL = null;
		try {
			inImageURL = new File(inImgLocation).toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (Objects.nonNull(inImageURL)) {                      
            //image found
        	return inImageURL;
        } else {   
        	System.err.println("No Image Found");
            return inImageURL;
        }  
    }

}