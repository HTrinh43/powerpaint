package ControllerTools;

import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

import Model.ToolType;

public class PencilTool extends AbstractPaintTool {
	
    public PencilTool() {
		super(ToolType.PENCIL, KeyEvent.VK_P);
		
	}

	@Override
    public Shape getShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }

}
