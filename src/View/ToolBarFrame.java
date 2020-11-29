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

import ControllerTools.EllipseTool;
import ControllerTools.EraserTool;
import ControllerTools.LineTool;
import ControllerTools.PencilTool;
import ControllerTools.RectangleTool;
import Model.ToolType;
import Model.UWColors;
import View.ToolBarFrame;


public class ToolBarFrame extends JFrame {
    
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
    
    private DrawingArea myPane;

    // Constructor

    /**
     * Constructs a new ToolBarFrame with all its controls.
     */
    public ToolBarFrame(DrawingArea thePane) {
        myPane = thePane;
        setupActions(); // initializes myActions
    }

    /**
     * Sets up all the Actions.
     */
    private void setupActions() {
    	
    	//adding toolbar here
        myToolsAction = new ArrayList<>();

        myToolsAction.add(new ToolAction("Pencil", new ImageIcon("./images/pencil.gif"), 
        		ToolType.PENCIL, KeyEvent.VK_P));   
        myToolsAction.add(new ToolAction("Line", new ImageIcon("./images/line.gif"), 
        		ToolType.LINE, KeyEvent.VK_L));
        myToolsAction.add(new ToolAction("Retangle", new ImageIcon("./images/rectangle.gif"), 
        		ToolType.RECTANGLE, KeyEvent.VK_R));
        myToolsAction.add(new ToolAction("Ellipse", new ImageIcon("./images/ellipse.gif"), 
        		ToolType.ELLIPSE, KeyEvent.VK_E));
        myToolsAction.add(new ToolAction("Eraser", new ImageIcon("./images/eraser.gif"), 
        		ToolType.ERASER, KeyEvent.VK_A));
        // anonymous inner class in use here
        myExitAction = new AbstractAction(EXIT_STRING, new ImageIcon("./images/exit.gif")) {

            /** A generated serialization ID. */
            private static final long serialVersionUID = -3641127125217134863L;

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                // close this Window
                dispatchEvent(new WindowEvent(ToolBarFrame.this, WindowEvent.WINDOW_CLOSING)); 
                // do NOT call System.exit()
            }
        };

        myExitAction.putValue(Action.SHORT_DESCRIPTION, EXIT_STRING);
        myExitAction.putValue(Action.ACCELERATOR_KEY,
                                KeyStroke.getKeyStroke('Q',
                                                       java.awt.event.InputEvent.CTRL_MASK));
    }

    /**
     * @return a fully-stocked tool bar.
     */
    public JToolBar createToolBar() {
        final JToolBar bar = new JToolBar();
        bar.add(myExitAction);
        bar.addSeparator();
        
        
        final ButtonGroup btngrp = new ButtonGroup();
        for (final Action ca : myToolsAction) {
            final JToggleButton tb = new JToggleButton(ca);
            btngrp.add(tb);
            bar.add(tb);
        }
        
        //uncomment if you don't want any of the buttons selected on start. 
        //btngrp.clearSelection();

        return bar;
    }
    // main()


    /**
     * Sets the background of the panel to the specified color.
     */
    private class ToolAction extends AbstractAction {
        
        /** A generated serialization ID. */
        private static final long serialVersionUID = 5378597116905801274L;
        

        /**
         * Constructs an action with the specified name and icon to set the
         * panel to the specified color.
         * 
         * @param theName The name.
         * @param theIcon The icon.
         */
        private final ToolType myTool;
        ToolAction(final String theName, final Icon theIcon, ToolType theTool, int theMnemonic) {
            super(theName);
            
            // small icons are usually assigned to the menu
            putValue(Action.SMALL_ICON, theIcon);
            
            // Here is how to assign a larger icon to the tool bar.
            final ImageIcon icon = (ImageIcon) theIcon;
            final Image largeImage =
                icon.getImage().getScaledInstance(15, -1, java.awt.Image.SCALE_SMOOTH);
            final ImageIcon largeIcon = new ImageIcon(largeImage);
            putValue(Action.LARGE_ICON_KEY, largeIcon);
            
            // set a mnemonic on the first character of the name
            putValue(Action.MNEMONIC_KEY, theMnemonic);
            
            // tool tips
            putValue(Action.SHORT_DESCRIPTION, theName + " background");
            
            // coordinate button selection
//            putValue(Action.SELECTED_KEY, true);
            myTool = theTool;
        }

        /**
         * Sets the panel to the specified color.
         * 
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
        	if(this.myTool == ToolType.LINE) {
        		myPane.setCurrentTool(new LineTool(myTool, KeyEvent.VK_L));
        	}
        	else if(this.myTool == ToolType.ELLIPSE) {
        		myPane.setCurrentTool(new EllipseTool(myTool, KeyEvent.VK_E));
        	}
        	else if(this.myTool == ToolType.ERASER) {
        		myPane.setCurrentTool(new EraserTool(myTool, KeyEvent.VK_S));
        	}
        	else if(this.myTool == ToolType.PENCIL) {
        		myPane.setCurrentTool(new PencilTool(myTool, KeyEvent.VK_P));
        	}
        	else if(this.myTool == ToolType.RECTANGLE) {
        		myPane.setCurrentTool(new RectangleTool(myTool, KeyEvent.VK_R));
        	}
        }
    }

}