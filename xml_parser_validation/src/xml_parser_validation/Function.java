package xml_parser_validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.lang.StringBuilder;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class Function {

	public void validateXml(DocumentBuilderFactory factory, DocumentBuilder builder, File workfile)
			throws SAXException, IOException {

		Document Doc = builder.parse(workfile);
		NodeList nodeList = Doc.getElementsByTagName("*");

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node p = nodeList.item(i);

			if (p.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) p;

				String id = element.getAttribute("*");

				NodeList nameList = element.getChildNodes();

				for (int j = 0; j < nameList.getLength(); j++) {
					Node n = nameList.item(j);
					if (n.getNodeType() == Node.ELEMENT_NODE) {

						Element name = (Element) n;
						if (name.getTextContent() != null && !name.getTextContent().isEmpty()) {
							System.out.println(workfile.getName() + id + ":" + name.getTagName() + "=     "
									+ name.getTextContent());
						}
					}
				}
			}
		}
	}

	public void validateJson(DocumentBuilderFactory factory, DocumentBuilder builder, File workfile)
			throws JSONException, FileNotFoundException, IOException, ParseException {

		// JSONObject obj_jsonobject = new JSONObject(workfile);
		// System.out.println(obj_jsonobject);
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(workfile.toString()));
		// System.out.println(obj);

		JsonFactory factory_json = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory_json);
		JsonNode rootNode = mapper.readTree(workfile);

		Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
		while (fieldsIterator.hasNext()) {

			Map.Entry<String, JsonNode> field = fieldsIterator.next();
			System.out.println(workfile.getName()+":" +" Key: " + field.getKey() + "\t Value:" + field.getValue());
		}

	}
}
