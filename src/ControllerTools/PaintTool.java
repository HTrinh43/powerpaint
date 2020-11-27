package ControllerTools;

import java.awt.Point;
import java.awt.Shape;

public interface PaintTool {
    
	/**
     * Returns the name of this tool.
     * 
     * @return the name of this tool.
     */
	String getName();
	
	/**
     * Returns the Mnemonic for this tools.
     * 
     * @return the Mnemonic for this tools.
     */
	int getMnemonic();
	
	/**
     * Returns the Shape that this tools draws.
     * 
     * @return the Shape to draw
     */
    Shape getShape();

    /**
     * Sets the initial point for the Shape drawn by this tool.
     * 
     * @param thePoint the start point to set
     */
    void setStartPoint(Point thePoint);
    
    /**
     * Returns the initial point for the current shape drawn by this tool.
     * 
     * @return
     */
    Point getStartPoint();
    
    /**
     * Sets the next point for the Shape drawn by this tool.
     * 
     * @param thePoint the next point to set
     */
    void setEndPoint(Point thePoint);
    Point getEndPoint();
    /**
     * Resets the tool using default values.
     */
    void reset();
}
