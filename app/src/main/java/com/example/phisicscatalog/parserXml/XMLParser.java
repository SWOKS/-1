package com.example.phisicscatalog.parserXml;

import android.content.Context;
import android.util.Log;

import com.example.phisicscatalog.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {

    private static final String DATANODE_TAG_NAME = "datanode";
    private static final String THEME_TAG_NAME = "theme";
    private static final String FORMULA_TAG_NAME = "formula";
    private static final String DESCRIPTION_TAG_NAME = "description";

    private static final String ATTRIBUTE_NAME = "name";

    private String nameOfXMLFile;


    private List<InfoModel> listOfThemeElements = new LinkedList<>();

    public XMLParser(String nameOfXMLFile) {
        this.nameOfXMLFile = nameOfXMLFile;
        //this.pathToXLMFile = "android.resource://main/res/xml/data_mehanica.xml" ;

        Log.d("list", "Объект создан ");
        Log.d("list", "путь до документа " + nameOfXMLFile);


    }

    public List<InfoModel> parse(Context context) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputStream inputStream = context.getAssets().open(nameOfXMLFile);
        Document document = builder.parse(inputStream);
        Element element = document.getDocumentElement();
        NodeList nodeListOnlyDataNodeElement = element.getElementsByTagName(DATANODE_TAG_NAME);

        return findIntoInXMLFile(nodeListOnlyDataNodeElement);
    }

    private List<InfoModel> findIntoInXMLFile(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            String theme = "null";
            String formula = "null";
            String description = "null";
            NodeList childNodeList = nodeList.item(i).getChildNodes();
            for (int j = 0; j < childNodeList.getLength(); j++) {
                switch (childNodeList.item(j).getNodeName()) {
                    case THEME_TAG_NAME:
                        theme = childNodeList.item(j).getTextContent();
                        break;
                    case FORMULA_TAG_NAME:
                        formula = childNodeList.item(j).getTextContent();
                        break;
                    case DESCRIPTION_TAG_NAME:
                        description = childNodeList.item(j).getTextContent();
                        break;
                }
            }
            listOfThemeElements.add(new InfoModel(theme, formula, description));
        }
        return listOfThemeElements;

    }
}
