package Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.JFileChooser;

import Model.ShapeModel;

public class SaveFileAction implements ActionListener{
	
	private final List<ShapeModel> myShapeList;
	
	public SaveFileAction(List<ShapeModel> theList) {
		myShapeList = theList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
        final String fileLocation = System.getProperty("user.dir") + "/save";
		
		
		JFileChooser chooser=new JFileChooser(new File(fileLocation));

		final int result = chooser.showSaveDialog(null);
		
		if (result == JFileChooser.CANCEL_OPTION)
			return;
		String path=chooser.getSelectedFile().getAbsolutePath();
		//add file extension
		if (!path.endsWith(".shp"))
			path += ".shp";
		
		try { 

            // Saving of object in a file 
            FileOutputStream file = new FileOutputStream 
                                           (path); 
            ObjectOutputStream out = new ObjectOutputStream 
                                           (file); 
  
            // Method for serialization of object 
            out.writeObject(myShapeList); 
  
            out.close(); 
            file.close(); 
        } 
  
        catch (IOException ex) { 
            System.out.println("IOException is caught"); 
        } 
	}

}
