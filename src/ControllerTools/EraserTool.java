package ControllerTools;

import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import Model.ToolType;

/**
 * A tool for Eraser.
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class EraserTool extends AbstractPaintTool {
	
	/**
	 * Constructs an eraser tool.
	 */
    public EraserTool() {
		super(ToolType.ERASER, KeyEvent.VK_A);
		
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
