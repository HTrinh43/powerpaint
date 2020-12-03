package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Actions.ToolActions;
import ControllerTools.EllipseTool;
import ControllerTools.EraserTool;
import ControllerTools.LineTool;
import ControllerTools.PaintTool;
import ControllerTools.PencilTool;
import ControllerTools.RectangleTool;
import Model.MutableDrawingObject;
import Model.ToolType;
import Model.UWColors;
import View.ToolBarFrame;


public class ToolBarFrame extends JToolBar {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -4447570618384470786L;

    /** A constant for the string "Exit". */
    private static final String EXIT_STRING = "Exit";

    /** The panel used inside the frame. */
//    private final JPanel myPanel;

    /** The exit action. */
    private Action myExitAction;

    /** A list of color actions. */
    private List<Action> myToolsAction;
    
//    private ShapeGUI myPane;

    // Constructor

    /**
     * Constructs a new ToolBarFrame with all its controls.
     */
    public ToolBarFrame(final Map<PaintTool, ToolActions> theMap) {
//        setupActions(theMap); // initializes myActions
    	super();
        final ButtonGroup toolGroup = new ButtonGroup(); 
        
        for (final PaintTool p : theMap.keySet()) {

            final JRadioButtonMenuItem item = new JRadioButtonMenuItem(theMap.get(p));

            add(item);
            toolGroup.add(item);
        }
    }

    /**
     * Sets up all the Actions.
     */
//    private void setupActions(final Map<PaintTool, ToolActions> theMap) {
//    	
//    	//adding toolbar here
//
//        // anonymous inner class in use here
//        myExitAction = new AbstractAction(EXIT_STRING, new ImageIcon("./images/exit.gif")) {
//
//            /** A generated serialization ID. */
//            private static final long serialVersionUID = -3641127125217134863L;
//
//            @Override
//            public void actionPerformed(final ActionEvent theEvent) {
//                // close this Window
//                dispatchEvent(new WindowEvent(ToolBarFrame.this, WindowEvent.WINDOW_CLOSING)); 
//                // do NOT call System.exit()
//            }
//        };
//
//        myExitAction.putValue(Action.SHORT_DESCRIPTION, EXIT_STRING);
//        myExitAction.putValue(Action.ACCELERATOR_KEY,
//                                KeyStroke.getKeyStroke('Q',
//                                                       java.awt.event.InputEvent.CTRL_MASK));
//    }

    /**
     * @return a fully-stocked tool bar.
     */
	private JToggleButton createToolMenu(final PaintTool theTool, final Map<PaintTool, ToolActions> theMap ) {
		
        final JToggleButton button = new JToggleButton();

        //Add the action for this Direction. 
        	button.setAction(theMap.get(theTool));
            button.setText(theTool.getName());
        return button;
	}
    // main()


    /**
     * Sets the background of the panel to the specified color.
     */
 

}