package ControllerTools;

import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import Model.ToolType;

/**
 * A tool for Pencil.
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class PencilTool extends AbstractPaintTool {
	
	/**
	 * Constructs a Pencil tool.
	 */
    public PencilTool() {
		super(ToolType.PENCIL, KeyEvent.VK_P);
		
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
