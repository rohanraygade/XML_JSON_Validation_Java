package xml_parser_validation;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.Iterator;

import org.json.*;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

public class MyDomParser {

	public static void main(String args[]) throws SAXException, IOException, ParserConfigurationException, JSONException, ParseException
	{
			Function validator = new Function();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			File folder = new File("orders/");
			for (File workfile : folder.listFiles()) {
				if (workfile.isFile() && workfile.toString().endsWith(".xml"))
				{
						validator.validateXml(factory, builder, workfile);	
				}
				
				else if (workfile.isFile() && workfile.toString().endsWith(".json")) {
					
					validator.validateJson(factory, builder, workfile);
					
				}
				
				else {
					
					System.out.println(workfile.getName() + " File Type not parsed");
				}
				
				
			}	
	}}