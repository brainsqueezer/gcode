package components;

import java.util.StringTokenizer;

import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import comandos.Comando;
import comandos.ComandoStorage;

public class LineListener implements CaretListener {
	JSyntaxArea textArea ;
	JTextArea comandoArea;
	public LineListener(JSyntaxArea textArea, JTextArea comandoArea) {
		this.textArea = textArea;
		this.comandoArea = comandoArea;
	}

	@Override
	public void caretUpdate(CaretEvent arg0) {
		// default all
		//DefaultCaret.
		Comando comando = null;
		int line = this.textArea.getCurrentLine();
		try {
			String text = this.textArea.getText();
			String[] lines = text.split("\n");
			String currentline = lines[line-1];
			//System.out.println("line: "+line+"content: "+curentline);
			String[] words = currentline.split(" ");
			String currentword = words[0];
			comando = ComandoStorage.storage.buscar(currentword);
		} catch(Exception e) {
			System.out.println(e+" line:"+line);
			
		}
		if (comando != null) {
			this.comandoArea.setText(comando.id+": "+ comando.tipo + ": "+ comando.tip);
		} else {
			this.comandoArea.setText("");
			
		}
	}
	
	public void lala (String str) {
		int count = 0;
		StringTokenizer token = new StringTokenizer(str);
		while (token.hasMoreTokens()) {
			count++;
			str = token.nextToken();
			
		}
	}
	
	public static void main(String[] args) {
		String line = "Hola que tal estas";
	String[] words = line.split(" ");
	System.out.println(words.length + " words");
	}

}
