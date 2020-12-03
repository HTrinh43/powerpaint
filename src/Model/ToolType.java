package Model;
import ControllerTools.*;

public enum ToolType {
	LINE("Line"),
	PENCIL("Pencil"),
	RECTANGLE("Rectangle"),
	ELLIPSE("Ellipse"),
	ERASER("Eraser");


	private String myName;
	ToolType(final String theName) {
		myName = theName;
//		currentTool = setValue();
	}
	
	public PaintTool getTool() {
		PaintTool result = null;
		
		for (final ToolType t : ToolType.values()) {
			if (myName.equals(ToolType.LINE.toString())) {
				result = new LineTool();
			}
			else if (myName.equals("Pencil")) {
				result = new PencilTool();
			}
			else if (myName.equals("Rectangle")) {
				result = new RectangleTool();
			}
			else if (myName.equals("Ellipse")) {
				result = new EllipseTool();
			}
			else if (myName.equals("Eraser")) {
				result = new EraserTool();
			}
		}	
		return result;
	}
	
	
	@Override
	public String toString() {
		return myName;
	}

	
	
}
