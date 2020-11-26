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

import Model.UWColors;
import View.ToolBarFrame;


public class ToolBarFrame extends JFrame {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -4447570618384470786L;

    /** A constant for the string "Exit". */
    private static final String EXIT_STRING = "Exit";

    /** The panel used inside the frame. */
    private final JPanel myPanel;

    /** The exit action. */
    private Action myExitAction;

    /** A list of color actions. */
    private List<Action> myColorActions;

    // Constructor

    /**
     * Constructs a new ToolBarFrame with all its controls.
     */
    public ToolBarFrame() {
        super("Buttons with Actions");
        
        setupActions(); // initializes myActions

        final JToolBar toolBar = createToolBar();
        add(toolBar, BorderLayout.SOUTH);
        
//        setJMenuBar(createMenuBar());
        MenuBar menuBar = new MenuBar();
        setJMenuBar(menuBar.createMenuBar());

        myPanel = new DrawingArea();
        add(myPanel, BorderLayout.CENTER);

        pack();

    }

    /**
     * Sets up all the Actions.
     */
    private void setupActions() {
    	
    	//adding toolbar here
        myColorActions = new ArrayList<>();

        myColorActions.add(new ColorAction("Gold", new ImageIcon("./images/yellow-ball.gif"),
                                             UWColors.GOLD.getColor()));
        
        myColorActions.add(new ColorAction("Purple", new ImageIcon("./images/blue-ball.gif"), 
        		UWColors.PURPLE.getColor()));
        

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
    private JToolBar createToolBar() {
        final JToolBar bar = new JToolBar();
        bar.add(myExitAction);
        bar.addSeparator();
        
        
        final ButtonGroup btngrp = new ButtonGroup();
        for (final Action ca : myColorActions) {
            final JToggleButton tb = new JToggleButton(ca);
            btngrp.add(tb);
            bar.add(tb);
        }
        
        //uncomment if you don't want any of the buttons selected on start. 
        //btngrp.clearSelection();

        return bar;
    }

    /**
     * @return a fully-stocked menu bar.
     */
    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();

        // setup the file menu
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        fileMenu.add(myExitAction);
        menuBar.add(fileMenu);

        // setup the color menu
        final JMenu colorMenu = new JMenu("Color");
        colorMenu.setMnemonic(KeyEvent.VK_O);
        
        final ButtonGroup btngrp = new ButtonGroup();

        for (final Action ca : myColorActions) {
            final JRadioButtonMenuItem btn = new JRadioButtonMenuItem(ca);
            btngrp.add(btn);
            colorMenu.add(btn);
        }

        menuBar.add(colorMenu);

        return menuBar;
    }

    // main()


    /**
     * Sets the background of the panel to the specified color.
     */
    private class ColorAction extends AbstractAction {
        
        /** A generated serialization ID. */
        private static final long serialVersionUID = 5378597116905801274L;
        
        /** The color to use. */
        private final Color myColor;

        /**
         * Constructs an action with the specified name and icon to set the
         * panel to the specified color.
         * 
         * @param theName The name.
         * @param theIcon The icon.
         * @param theColor The color.
         */
        ColorAction(final String theName, final Icon theIcon, final Color theColor) {
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
            putValue(Action.MNEMONIC_KEY,
                     KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
            
            // tool tips
            putValue(Action.SHORT_DESCRIPTION, theName + " background");
            
            // coordinate button selection
//            putValue(Action.SELECTED_KEY, true);
            
            myColor = theColor;
        }

        /**
         * Sets the panel to the specified color.
         * 
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
//            myPanel.setBackground(myColor);
        }
    }

}
