package Model;

import java.awt.Color;
import java.awt.Shape;
import java.io.Serializable;

/**
 * Constructs a shape model.
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class ShapeModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2167842251946136790L;
	private final Shape myShape;
	private final Color myColor;
	private final int myThickness;
	
	/**
	 * Initializes the shape mode object.
	 * 
	 * @param theShape the shape of this drawing object.
	 * @param theColor the color of this drawing object.
	 * @param theThickness the thickness of this drawing object.
	 */
	public ShapeModel(final Shape theShape, final Color theColor, final int theThickness) {
		this.myShape = theShape;
		this.myColor = theColor;
		this.myThickness = theThickness;
	}
	
	/**
	 * Returns the colors for this drawing objects.
	 * 
	 * @return the colors for this drawing objects.
	 */
	public Color getColor() {
		return this.myColor;
	}
	
	/**
	 * Returns the shape for this drawing objects.
	 * 
	 * @return the shape for this drawing objects.
	 */
	public Shape getShape() {
		return this.myShape;
	}
	
	/**
	 * Returns the thickness for this drawing objects.
	 * 
	 * @return the thickness for this drawing objects.
	 */
	public int getThickness() {
		return this.myThickness;
	}
}
