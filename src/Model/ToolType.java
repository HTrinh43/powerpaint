package Model;

public enum ToolType {
	LINE("Line"),
	PENCIL("Pencil"),
	RECTANGLE("Rectangle"),
	ELLIPSE("Ellipse"),
	ERASER("Eraser");

	private String myName;
	ToolType(String toolName) {
		this.myName = toolName;
	}
	
	public String getTool() {
		return this.myName;
	}
}
