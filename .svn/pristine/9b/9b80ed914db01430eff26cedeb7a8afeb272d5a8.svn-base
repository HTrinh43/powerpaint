package Model;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import ControllerTools.PaintTool;
import ControllerTools.PencilTool;

public class MutableDrawingObject implements PropertyChangeListener{
    private PaintTool myCurrentTool;
    
    private final PropertyChangeSupport myPcs;
    
    private Color myPrimaryColor;
    
    private Color mySecondaryColor;
    
    private int myThickness;
    
    public MutableDrawingObject (){
    	myCurrentTool = new PencilTool();
    	myPrimaryColor = UWColors.PURPLE.getColor();
    	mySecondaryColor = UWColors.GOLD.getColor();
    	myThickness = 10;
        myPcs = new PropertyChangeSupport(this);
    }
    
    public void setCurrentTool(final PaintTool theTool) {
    	myCurrentTool = theTool;
    }
    
    public void setPrimaryColor(final Color theColor) {
    	myPrimaryColor = theColor;
    }
    
    public void setSecondaryColor(final Color theColor) {
    	mySecondaryColor = theColor;
    }
    
    public void setThickness(final int theThickness) {
    	myThickness = theThickness;
    }
    
    public Color getPrimaryColor() {
    	return myPrimaryColor;
    }
    
    public Color getSecondaryColor() {
    	return mySecondaryColor;
    }
    
    public int getThickness() {
    	return myThickness;
    }
    
    public PaintTool getCurrentTool() {
    	return myCurrentTool;
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
    	myPcs.addPropertyChangeListener(theListener);
    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
