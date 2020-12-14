package Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javax.swing.JFileChooser;

import Model.ShapeModel;
import View.DrawingArea;

public class LoadFileAction implements ActionListener{
	
	private final DrawingArea myDrawing;
	public LoadFileAction(final DrawingArea theArea) {
		myDrawing = theArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<ShapeModel> object = null;
        final String fileLocation = System.getProperty("user.dir") + "/save";
		
		JFileChooser chooser=new JFileChooser(new File(fileLocation));
		final int result = chooser.showSaveDialog(null);
		
		if (result == JFileChooser.CANCEL_OPTION)
			return;
		String path=chooser.getSelectedFile().getAbsolutePath();

		try { 
			  
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(path); 
            ObjectInputStream in = new ObjectInputStream 
                                         (file); 
  
            // Method for deserialization of object 
            object = (List<ShapeModel>)in.readObject(); 
            myDrawing.setList(object);
            in.close(); 
            file.close(); 
        } 
  
        catch (IOException ex) { 
            System.out.println("IOException is caught"); 
        } 
  
        catch (ClassNotFoundException ex) { 
            System.out.println("ClassNotFoundException" + 
                                " is caught"); 
        } 
    } 
		
	

}
