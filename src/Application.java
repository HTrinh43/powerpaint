import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import View.ShapeGUI;


/**
 * The driver class for this demonstration code. 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class Application {

	/**
	 * Create a JFrame to demonstrate this panel.
	 * 
	 * @param args theArgs Command line arguments.
	 */
	public static void main(final String[] args) {        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
      //creating and showing this application's GUI.
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShapeGUI().start();
            }
        });
	}

}
