package Actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.KeyStroke;

import View.DrawingArea;

/**
 * An Action for the ColorChoser Button. 
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class ColorChooserAction extends AbstractAction {

    /**  
     * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
     */
    private static final long serialVersionUID  = -2193928786463255262L;
    
    /**
     * The JPanel this Action sets the color on. 
     */
    private final DrawingArea myPanel;

    private final String myName;
    
    private final String PRIMARY_COLOR = "Primary Color";
    
    /**
     * Initializes the Actions Fields.
     * @param theText the Text for this Action. 
     * @param thePanel JPanel this Action sets the color on
     */
    public ColorChooserAction(final String theText,
                              final DrawingArea thePanel) {
        super(theText);
        myName = theText;
        myPanel = thePanel;
        if (myName.equals(PRIMARY_COLOR))
        	putValue(MNEMONIC_KEY, KeyEvent.VK_P);
        else
        	putValue(MNEMONIC_KEY, KeyEvent.VK_S);
//        putValue(ACCELERATOR_KEY, 
//                 KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
    }

    /**
     * Set the primary color and the second color for the panel. 
     * 
     * @param theEvent the action event.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {

        final Color result = JColorChooser.showDialog(null, "Color Chooser", null);
        if (result != null) {
        	if (myName.equals(PRIMARY_COLOR)) 
        		myPanel.setPrimaryColor(result);
        	else
        		myPanel.setSecondaryColor(result);
        }
    }

}
