package Model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;

public class ColorChooser extends JPanel
				implements ChangeListener{

    protected JColorChooser tcc;
    private Color myColor;
    
    public ColorChooser(Color theColor) {
    	
        super(new BorderLayout());

        //Set up color chooser for setting text color
        tcc = new JColorChooser(theColor);
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder(
                                             "Choose Color"));

        add(tcc, BorderLayout.PAGE_END);
    }
	@Override
	public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
		myColor = newColor;

	}
	
	public Color getColor() {
		return this.myColor;
	}
	
	public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Color Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ColorChooser(UWColors.GOLD.getColor());
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    

}
