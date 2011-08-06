package components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import comandos.ComandoStorage;

public class GCode
implements ActionListener {
	private EditorPanel view;
	private String filename = null;
	
	public GCode() {
		this.view = new EditorPanel();
		createAndShowGUI();
		ComandoStorage.leerComandos();
	}
	
    
    

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == this.view.openButton) {
            int returnVal = this.view.fc.showOpenDialog(this.view);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = this.view.fc.getSelectedFile();
                //This is where a real application would open the file.
                this.openfile(file);
            } else {
                this.view.editorArea.setText("Open command cancelled by user.");
            }
            this.view.editorArea.setCaretPosition(this.view.editorArea.getDocument().getLength());

        //Handle save button action.
        } else if (e.getSource() == this.view.saveButton) {
            int returnVal = this.view.fc.showSaveDialog(this.view);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = this.view.fc.getSelectedFile();
                //This is where a real application would save the file.
                this.view.editorArea.setText("Saving: " + file.getName() + ".\n");
            } else {
            	this.view.editorArea.setText("Save command cancelled by user.\n");
            }
            this.view.editorArea.setCaretPosition(this.view.editorArea.getDocument().getLength());
        }
    }

    
    void openfile(File file) {
		// TODO Auto-generated method stub

		try {
StringBuffer texto = new StringBuffer();
String strLine = "";
        filename = file.getAbsoluteFile().toString();
        System.out.println("Opening: " + this.filename + ".\n");
        FileInputStream in;
			in = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        while ((strLine = br.readLine()) != null) {
        	texto.append(strLine+"\n");
        }

		this.view.editorArea.setText(texto.toString());
        

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 public boolean canSave() {   
    String content = this.view.editorArea.getText();
	File aFile = new File(this.filename);
if (aFile == null) {
//throw new IllegalArgumentException("File should not be null.");
return false;
}
if (!aFile.exists()) {
//throw new FileNotFoundException ("File does not exist: " + aFile);
	return false;
}
if (!aFile.isFile()) {
//throw new IllegalArgumentException("Should not be a directory: " + aFile);
return false;
}
if (!aFile.canWrite()) {
//throw new IllegalArgumentException("File cannot be written: " + aFile);
return false;
}
return true;
}

    public boolean setContents() {

String content = this.view.editorArea.getText();
    	File aFile = new File(this.filename);
if (!this.canSave()) {
	return false;
}

//use buffering
Writer output;
try {
	output = new BufferedWriter(new FileWriter(aFile));
	//FileWriter always assumes default encoding is OK!
	output.write( content );
	output.close();
} catch (IOException e) {
	return false;
}
return true;
}
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GCode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//frame.setLayout(new BorderLayout());
        //Add  JPanel content to the window.
        frame.add(this.view, BorderLayout.NORTH);


        //lineas
  //      JTextPane textPane = new JTextPane();
   //     JScrollPane scrollPane = new JScrollPane(textPane);
   //     JPanel lineas = new TextLineNumber(textPane);
   //     scrollPane.setRowHeaderView(lineas);
    //    frame.add(scrollPane, BorderLayout.SOUTH);

        //Display the window.

        this.view.openButton.addActionListener(this);
        this.view.saveButton.addActionListener(this);
        LineListener lineListener = new LineListener(this.view.editorArea, this.view.comandosArea);
        this.view.editorArea.addCaretListener(lineListener);
        this.view.editorArea.addCaretListener(new CurrentLineHighlighter());
        frame.pack();
        frame.setVisible(true);
    }
	public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
		
        try {
			UIManager.setLookAndFeel(
			    UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                new GCode();
            }
        });
    }
}
