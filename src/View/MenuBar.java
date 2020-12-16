package View;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeListener;

import Actions.*;

import ControllerTools.PaintTool;
import Model.ToolType;

/**
 * A menubar that has three menus: Options, Tools, Help
 * 
 * @author Alex Trinh, Natalie Nguyen Hong
 * @version Fall 2020
 */
public class MenuBar  extends JMenuBar implements ActionListener, PropertyChangeListener{
	
	/**
	 * A generated serial version UID for object Serialization. 
	 */
	private static final long serialVersionUID = 2445544222417987308L;
	
	/** The default thickness. */
	private final int DEFAULT_THICKNESS = 10;

	/** The maximum value of thickness slider. */
	private final int MAXIMUM_SLIDER_VALUE = 20;
	
	/** The minimum value of thickness slider. */
	private final int MINIMUM_SLIDER_VALUE = 0;
	
	private JSlider mySlider;
	
	private JMenuItem myClearItem;
	
	private JMenuItem myUndoItem;
	
	private JMenuItem myRedoItem;
	
	private final DrawingArea myDrawingArea;
   
	/**
	 * Build the menu bar for this GUI.
	 * 
	 * @param theMap the map of ToolAction
	 * @param theColor the color action
	 * @param theDrawingArea the drawing panel
	 */
	public MenuBar(final Map<PaintTool, ToolActions> theMap, final ColorChooserAction[] theColor, final DrawingArea theDrawingArea) {
		myDrawingArea = theDrawingArea;
		createSlider(myDrawingArea);
		JMenuBar inMenuBar = createMenuBar(theMap, theColor);
		add(inMenuBar);
	}
	
	/**
	 * Create the menu bar that has three menus.
	 * 
	 * @param theMap theMap the map of ToolAction
	 * @param theColor the color action
	 * @return the menu bar 
	 */
	public JMenuBar createMenuBar(final Map<PaintTool, ToolActions> theMap, final ColorChooserAction[] theColor) {
		final JMenuBar inMenuBar = new JMenuBar();
		
		final JMenu inFileMenu = createFileMenu();
		
		final JMenu inEditMenu = createEditMenu();
		
        final JMenu inOptionsMenu = createOptionMenu(theColor);
		//Tools Menu
		final JMenu inToolsMenu = createToolMenu(theMap);
        //Help Menu
		final JMenu inHelpMenu = createHelpMenu();
		
		inMenuBar.add(inFileMenu);
		inMenuBar.add(inEditMenu);
		inMenuBar.add(inOptionsMenu);
		inMenuBar.add(inToolsMenu);
		inMenuBar.add(inHelpMenu);
		return inMenuBar;
	}

	/**
	 * Create the slider to adjust the thickness. 
	 * 
	 * @param theDrawingArea the drawing panel
	 */
	private void createSlider(final DrawingArea theDrawingArea) {
		//setup Slider
        mySlider = new JSlider(MINIMUM_SLIDER_VALUE, MAXIMUM_SLIDER_VALUE, DEFAULT_THICKNESS);
        mySlider.setPaintTicks(true);
        mySlider.setPaintLabels(true);
        mySlider.setMinorTickSpacing(1);
        mySlider.setMajorTickSpacing(5);
        ChangeListener cl = e -> {
            JSlider x = (JSlider) e.getSource();
            int thickness =  x.getValue();       
            theDrawingArea.setThickness(thickness);
        };
        mySlider.addChangeListener(cl);
	}
	
	private JMenu createFileMenu() {
		final JMenu fileMenu = new JMenu("File");
        //save drawing panel
        final JMenuItem saveItem = new JMenuItem("Save");
        
        final ActionListener saveFileActionListener = new SaveFileAction(myDrawingArea.getList());
        saveItem.addActionListener(saveFileActionListener);
        
        //load drawing panel
        final JMenuItem loadItem = new JMenuItem("Load");
        
        final ActionListener loadFileActionListener = new LoadFileAction(myDrawingArea);
        loadItem.addActionListener(loadFileActionListener);
      
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
		return fileMenu;
	}
	
	private JMenu createEditMenu() {
		final JMenu editMenu = new JMenu("Edit");
		myUndoItem = new JMenuItem("Undo");
		myUndoItem.setEnabled(false);
        myUndoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myDrawingArea.undo();
			}
        	
        });
        
        myRedoItem = new JMenuItem("Redo");
        myRedoItem.setEnabled(false);
        myRedoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				myDrawingArea.redo();
			}
        });
        
        editMenu.add(myUndoItem);
        editMenu.add(myRedoItem);
        
        String os = System.getProperty("os.name");
        if (os.equals("Mac OS X")) {
        	KeyStroke undoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
            myUndoItem.setAccelerator(undoKeyStroke);
            KeyStroke redoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask());
            myRedoItem.setAccelerator(redoKeyStroke);
        }
        else {
        	KeyStroke undoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
            myUndoItem.setAccelerator(undoKeyStroke);
            KeyStroke redoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK);
            myRedoItem.setAccelerator(redoKeyStroke);
        }
        
		return editMenu;
	}
	
	
	/**
	 * Create the option menu.
	 * 
	 * @param theColor the color action
	 * @return the option menu.
	 */
	private JMenu createOptionMenu(final ColorChooserAction[] theColor) {
		final JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		//setup thickness slider
        final JMenu inThickness = new JMenu("Thickness");
        inThickness.add(mySlider);
        inThickness.setMnemonic(KeyEvent.VK_T);
        
        final JMenuItem primaryColor = buildColorMenu(theColor[0]);
        final JMenuItem secondaryColor = buildColorMenu(theColor[1]);
        
        myClearItem = new JMenuItem("Clear");     
        myClearItem.setMnemonic(KeyEvent.VK_C);
        myClearItem.setEnabled(false);
        myClearItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
            	myDrawingArea.clear();
                myClearItem.setEnabled(false);
            }
        });
        
        
        
        // Add thickness, color and clear menu
        optionsMenu.add(inThickness);
        optionsMenu.addSeparator();
        optionsMenu.add(primaryColor);
        optionsMenu.add(secondaryColor);
        optionsMenu.addSeparator();
        optionsMenu.add(myClearItem);
        
		return optionsMenu;
	}
	
	/**
	 * Creates the color menu.
	 * 
	 * @param theColorAction the color action
	 * @return the color menu
	 */
    private JMenuItem buildColorMenu(final ColorChooserAction theColorAction) {
        final JMenuItem chooseColorItem = new JMenuItem(theColorAction);
        return chooseColorItem;
    } 
	
    /**
     * Creates the tool menu.
     * 
     * @param theMap the map of tool action
     * @return the tool menu
     */
	private JMenu createToolMenu(final Map<PaintTool, ToolActions> theMap ) {
		final JMenu menu = new JMenu("Tools");
        menu.setMnemonic('T');
        //Map contains Tool and Mnemonic for each Tool
        final Map<String, Integer> inMnemonicKeys = new HashMap<String, Integer>();
        inMnemonicKeys.put(ToolType.LINE.toString(), KeyEvent.VK_L);
        inMnemonicKeys.put(ToolType.PENCIL.toString(), KeyEvent.VK_P);
        inMnemonicKeys.put(ToolType.ELLIPSE.toString(), KeyEvent.VK_E);
        inMnemonicKeys.put(ToolType.RECTANGLE.toString(), KeyEvent.VK_R);
        inMnemonicKeys.put(ToolType.ERASER.toString(), KeyEvent.VK_A);
        final ButtonGroup toolGroup = new ButtonGroup();
        for (final PaintTool p : theMap.keySet()) {
            final JRadioButtonMenuItem item = new JRadioButtonMenuItem(theMap.get(p));
            final int inMnemonic = inMnemonicKeys.get(p.getName());
            item.setMnemonic(inMnemonic);
            menu.add(item);
            toolGroup.add(item);
        }
        return menu;
	}
	
	/**
	 * Creates the help menu.
	 * 
	 * @return the help menu.
	 */
	private JMenu createHelpMenu() {
		final JMenu helpMenu = new JMenu("Help");
        final JMenuItem about = new JMenuItem("About");
        

        
        helpMenu.setMnemonic(KeyEvent.VK_H);
        about.setMnemonic(KeyEvent.VK_A);
        //https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#create
        helpMenu.add(about);
        about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "/images/w.gif");
				//resize the icon
				Image image = icon.getImage(); // transform it 
				Image newImg = image.getScaledInstance(40, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				icon = new ImageIcon(newImg);
				JOptionPane.showMessageDialog(about,
					    "Alex Trinh \n" 
						+ "Autumn 2020 \n" 
					    + "TCSS 305 Assignment 4",
					    "About",
					    JOptionPane.PLAIN_MESSAGE, icon);
			}	
        });
		return helpMenu;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	/**
	 * Updates the clear button.
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent theEvent) {
//		System.out.println(theEvent.getPropertyName());
//		System.out.println((boolean) theEvent.getNewValue());
		if ("CLEAR".equals(theEvent.getPropertyName())) {
            myClearItem.setEnabled(((boolean) theEvent.getNewValue()));
        }
		else if("REDO_ENABLE".equals(theEvent.getPropertyName())){
			myRedoItem.setEnabled(((boolean) theEvent.getNewValue()));
		}
		else if("UNDO_IS_ENABLE".equals(theEvent.getPropertyName())) {
			myUndoItem.setEnabled(((boolean) theEvent.getNewValue()));
		}
		else if("UNDO_IS_DISABLE".equals(theEvent.getPropertyName())) {
			myUndoItem.setEnabled(((boolean) theEvent.getNewValue()));
		}
		
	}
}
