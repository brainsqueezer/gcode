package comandos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CommandParser {
 //private DocumentBuilder builder;

 public ArrayList<Comando> print(String fileName)
   throws SAXException, IOException, ParserConfigurationException {
	 
	 ArrayList<Comando> comandos = new ArrayList<Comando>();
	 DocumentBuilderFactory factory 
	  = DocumentBuilderFactory.newInstance();
	 DocumentBuilder builder
	  = factory.newDocumentBuilder();
  Document document = builder.parse(fileName);
  
  Element root = document.getDocumentElement();
  System.out.println("root: "+root.getTagName());
  
  NodeList nodes_i 
    = root.getChildNodes();
  for (int i = 0; i < nodes_i.getLength(); i++) {
   Node node_i = nodes_i.item(i);
   
   //comandos
   if (node_i.getNodeType() == Node.ELEMENT_NODE) {
	   
    Element chessboard = (Element) node_i;
    NodeList nodes_j = chessboard.getChildNodes();

	  String tip = ((Element) node_i).getAttribute("tip");
	  String id = ((Element) node_i).getAttribute("id");
	  String tipo = ((Element) node_i).getAttribute("tipo");
	  String modal = ((Element) node_i).getAttribute("modal");
	  Comando comando = new Comando(id, tip, tipo, modal);
	  comandos.add(comando);
	   System.out.println(((Element) node_i).getTagName()
			   + " size: "+ nodes_j.getLength() + "id:"+id+" tip:"+tip);
	  
   }
  }
  return comandos;
 }
 
 
 

 public void print_old(String fileName)
   throws SAXException, IOException, ParserConfigurationException {
	 DocumentBuilderFactory factory 
	  = DocumentBuilderFactory.newInstance();
	 DocumentBuilder builder
	  = factory.newDocumentBuilder();
  Document document = builder.parse(fileName);
  
  Element root = document.getDocumentElement();
  System.out.println("root: "+root.getTagName());
  
  NodeList nodes_i 
    = root.getChildNodes();
  for (int i = 0; i < nodes_i.getLength(); i++) {
   Node node_i = nodes_i.item(i);
   
   //chessboard
   if (node_i.getNodeType() == Node.ELEMENT_NODE) {
	   
    Element chessboard = (Element) node_i;
    NodeList nodes_j = chessboard.getChildNodes();

	   System.out.println(((Element) node_i).getTagName()
			   + " size: "+ nodes_j.getLength());
    for (int j = 0; j < nodes_j.getLength(); j++) {
     Node node_j = nodes_j.item(j);
     
     //bando
     if (node_j.getNodeType() == Node.ELEMENT_NODE) {
    	 
    	 System.out.println("bando");
    	 
      Element pieces = (Element) node_j;
      NodeList nodes_k = pieces.getChildNodes();

	   System.out.println(((Element) node_j).getTagName()
			   + " size: "+ nodes_k.getLength());
      
      for (int k = 0; k < nodes_k.getLength(); k++) {
       Node node_k = nodes_k.item(k);
       
       //pieza
       if (node_k.getNodeType() == Node.ELEMENT_NODE) {

      	 System.out.println("pieza");
        Element piece = (Element) node_k;
        Element position 
          = (Element) piece.getChildNodes().item(0);
        
        System.out.println((pieces.getTagName()
                       .equals("WHITEPIECES")
                     ? "White " : "Black ")
                    + piece.getTagName().toLowerCase()
                    + ": "
                    + position.getAttribute("COLUMN")
                    + position.getAttribute("ROW"));
       }
      }
     }
    }
   }
  }
  return;
 }
 
 
 
 
 
 
 public String  openfile(String filename) {
		// TODO Auto-generated method stub

		try {
			File file = new File(filename);
StringBuffer texto = new StringBuffer();
String strLine = "";
     System.out.println("Opening: " + file.getName() + ".\n");
     FileInputStream in;
			in = new FileInputStream(file);
     BufferedReader br = new BufferedReader(new InputStreamReader(in));
     while ((strLine = br.readLine()) != null) {
     	texto.append(strLine+"\n");
     }

		//System.out.println(texto.toString());
     return texto.toString();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

	}


 public static void main(String args[]) throws SAXException, IOException, ParserConfigurationException {
	 String filename = "/home/rap/workspace/gcode/config3.xml";
	 CommandParser dp = new CommandParser();
	// String data = dp.openfile(filename);
	 System.out.println("the begining");
	 dp.print(filename);
	 System.out.println("the end");
 }
 
 
 
}