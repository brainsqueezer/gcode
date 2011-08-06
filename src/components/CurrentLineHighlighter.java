package components;

import java.awt.Color;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.*;


/*
* Usage: textComp.addCaretListener(new CurrentLineHighlighter());
*/
public class CurrentLineHighlighter implements CaretListener {
static final Color DEFAULT_COLOR = new Color(240, 240, 220);

private Highlighter.HighlightPainter painter;
private Object highlight;

public CurrentLineHighlighter() {
	this(null);
}

public CurrentLineHighlighter(Color highlightColor) {
	Color c = highlightColor != null ? highlightColor : DEFAULT_COLOR;
	painter = new DefaultHighlighter.DefaultHighlightPainter(c);
}

public void caretUpdate(CaretEvent evt) {
	JTextComponent comp = (JTextComponent)evt.getSource();
	if (comp != null && highlight != null) {
		comp.getHighlighter().removeHighlight(highlight);
		highlight = null;
	}
	
	int pos = comp.getCaretPosition();
	Element elem = Utilities.getParagraphElement(comp, pos);
	int start = elem.getStartOffset();
	//int end = comp.getWidth();
	int end = elem.getEndOffset();
	try {
		highlight = comp.getHighlighter().addHighlight(start, end, painter);
	} catch (BadLocationException ex) {
		ex.printStackTrace();
	}
}
}
