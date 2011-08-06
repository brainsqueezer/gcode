package components;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextArea;
import javax.swing.text.Element;

public class JSyntaxArea extends JTextArea {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8877625454984828444L;
	/*
	StyledDocument document = null;
	MutableAttributeSet defaultSyntax = new SimpleAttributeSet();
	MutableAttributeSet highlightSyntax = new SimpleAttributeSet();
	*/
	public JSyntaxArea() {
		/*
		StyledEditorKit editorKit = new StyledEditorKit();
		//this.setEditorKit(editorKit);
		document = (StyledDocument)this.getDocument();
		
		//set colors for the different styles
		StyleConstants.setBackground(highlightSyntax, Color.lightGray);
		StyleConstants.setForeground(highlightSyntax, Color.RED);

		StyleConstants.setBackground(defaultSyntax, Color.WHITE);
		StyleConstants.setForeground(defaultSyntax, Color.BLACK);
		*/
		//JEditorPane kaka = new JEditorPane();
		
	}
	public JSyntaxArea(int i, int j) {
		super(i,j);

        Font font = new Font("DejaVu Sans Mono", Font.PLAIN, 12);
        this.setFont(font);
	//	this.setSize(i, j);
		/*
		StyledEditorKit editorKit = new StyledEditorKit();
		//this.setEditorKit(editorKit);
		document = (StyledDocument)this.getDocument();
		
		//set colors for the different styles
		StyleConstants.setBackground(highlightSyntax, Color.lightGray);
		StyleConstants.setForeground(highlightSyntax, Color.RED);

		StyleConstants.setBackground(defaultSyntax, Color.WHITE);
		StyleConstants.setForeground(defaultSyntax, Color.BLACK);
*/
	}
	
	
	public void paintComponent( Graphics g )
	{
	Graphics2D g2d = (Graphics2D) g;
	g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON );
	super.paintComponent( g );
	}
	
	
	
	/*
	private void setDefault() {
		int len = document.getLength();
		String str = null;
		document.setCharacterAttributes(0, len , defaultSyntax, false);
	}
	
	private void setHighlight(int offset, int len) {
		document.setCharacterAttributes(offset, len , highlightSyntax,false);
	}
	*/
	public int getCurrentLine() {
		int  caretPosition = this.getCaretPosition();
		Element root = this.getDocument().getDefaultRootElement();
		int current = root.getElementIndex(caretPosition) +1;
		return current;
	}

}
