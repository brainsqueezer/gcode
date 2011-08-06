package comandos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ComandoStorage {
	public static ComandoStorage storage = new ComandoStorage();
	HashMap<String, Comando> map = new HashMap<String, Comando>();
	
	public ComandoStorage() {
		
	}
	public void anadir(Comando comando) {
		this.map.put(comando.id, comando);
	}
	public Comando buscar(String str) {
		return this.map.get(str);
	}


	public static void leerComandos() {
		 String filename = "/home/rap/workspace/gcode/config3.xml";
		 CommandParser dp = new CommandParser();
		 try {
			 ArrayList<Comando>  comandos = dp.print(filename);
			 for (Comando comando:comandos) {
				 ComandoStorage.storage.anadir(comando);
			 }
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
