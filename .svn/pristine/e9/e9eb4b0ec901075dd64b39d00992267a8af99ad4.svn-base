package Actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import View.DrawingArea;

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
    
    private final String SECONDARY_COLOR = "Secondary Color";
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
        
        putValue(MNEMONIC_KEY, KeyEvent.VK_C);
        putValue(ACCELERATOR_KEY, 
                 KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));

        //When using a String with the file location, runtime 
        //is expecting the images to be in the outer eclipse project
        //folder. If you want to package your application into an
        //executable jar file and run it that way, this methods will
        //NOT work. 
    }

    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        //https://docs.oracle.com/javase/tutorial/uiswing/components/colorchooser.html
        final Color result = JColorChooser.showDialog(null, "A Color Chooser", null);
        if (result != null) {
        	if (myName.equals(PRIMARY_COLOR)) 
        		myPanel.setPrimaryColor(result);
        	else
        		myPanel.setSecondaryColor(result);
        }
    }

}
