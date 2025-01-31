package Model;
import ControllerTools.*;

/**
 * An enumeration of the name of the paint tool. 
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public enum ToolType {
	/** The name of a Line tool. */
	LINE("Line"),
	
	/** The name of a Pencil tool. */
	PENCIL("Pencil"),
	
	/** The name of a Rectangle tool. */
	RECTANGLE("Rectangle"),
	
	/** The name of a Ellipse tool. */
	ELLIPSE("Ellipse"),
	
	/** The name of an Eraser tool. */
	ERASER("Eraser");

	/** The name of the paint tool. */
	private String myName;
	
	/**
	 * Initializes the tool type.
	 * 
	 * @param theName the name of this tool type
	 */
	ToolType(final String theName) {
		myName = theName;
	}
	
	/**
	 * Return the paint tool based on its name.
	 * 
	 * @return the paint tool.
	 */
	public PaintTool getTool() {
		PaintTool result = null;
		
		for (final ToolType inTool : ToolType.values()) {
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
	
	/**
	 * Return the string representation for the tool.
	 * 
	 * @return the string representation for the tool.
	 */
	@Override
	public String toString() {
		return myName;
	}

	
	
}
