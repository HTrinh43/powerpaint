package ControllerTools;

import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import ControllerTools.AbstractPaintTool;
import Model.ToolType;

/**
 * A tool for drawing Lines.
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class LineTool extends AbstractPaintTool {
	
	/**
	 * Constructs a line tool.
	 */
    public LineTool() {
		super(ToolType.LINE, KeyEvent.VK_L);
		
	}

    /**
     * Returns the shape drawn by this tool.
     * 
     * @return the shape drawn by this tool.
     */
	@Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }

}

