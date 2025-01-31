package Model;
import java.awt.Color;

/**
 * An enumeration of the official University of Washington colors. 
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public enum UWColors {
	/** UW Purple: #4B2E83. */
    PURPLE("#4B2E83"),
    
    /** UW Gold: #B7A57A. */
    GOLD("#b7a57a");
    
    /** Stores the enumeration's exact Color based on RGB values. */ 
    private Color myColor;
    
    /**
     * Creates the UWColor object. 
     * 
     * @param theHexString the hexadecimal representation of the UW Color's RGB values. 
     */
    UWColors(final String theHexString) {
        myColor = Color.decode(theHexString);
    }
    
    /**
     * Returns the associated Color object for each UW Color based on the published
     * RGB values. 
     * @return the associated Color
     */
    public Color getColor() {
        return myColor;
    }
}
