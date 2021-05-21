package com.example.phisicscatalog.parserXml;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class QuestionsXMLParser {

    private static final String DATANODE_TAG_NAME = "datanode";
    private static final String QUESTION_TAG_NAME = "question";
    private static final String ANSWER1_TAG_NAME = "answer1";
    private static final String ANSWER2_TAG_NAME = "answer2";
    private static final String ANSWER3_TAG_NAME = "answer3";
    private static final String ANSWER4_TAG_NAME = "answer4";
    private static final String CORRECT_TAG_NAME = "correct";

    private static final String ATTRIBUTE_NAME = "name";

    private String nameOfXMLFile;


    private List<QuestionsInfoModel> listOfThemeElements = new LinkedList<>();

    public QuestionsXMLParser(String nameOfXMLFile) {
        this.nameOfXMLFile = nameOfXMLFile;
        //this.pathToXLMFile = "android.resource://main/res/xml/data_mehanica.xml" ;

        Log.d("list", "Объект создан ");
        Log.d("list", "путь до документа " + nameOfXMLFile);
    }

    public List<QuestionsInfoModel> parse(Context context) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputStream inputStream = context.getAssets().open(nameOfXMLFile);
        Document document = builder.parse(inputStream);
        Element element = document.getDocumentElement();
        NodeList nodeListOnlyDataNodeElement = element.getElementsByTagName(DATANODE_TAG_NAME);

        return findIntoInXMLFile(nodeListOnlyDataNodeElement);
    }

    private List<QuestionsInfoModel> findIntoInXMLFile(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            String question = "null";
            String answer1 = "null";
            String answer2 = "null";
            String answer3 = "null";
            String answer4 = "null";
            String correctAnswer = "null";
            NodeList childNodeList = nodeList.item(i).getChildNodes();
            for (int j = 0; j < childNodeList.getLength(); j++) {
                switch (childNodeList.item(j).getNodeName()) {
                    case QUESTION_TAG_NAME:
                        question = childNodeList.item(j).getTextContent();
                        break;
                    case ANSWER1_TAG_NAME:
                        answer1 = childNodeList.item(j).getTextContent();
                        break;
                    case ANSWER2_TAG_NAME:
                        answer2 = childNodeList.item(j).getTextContent();
                        break;
                    case ANSWER3_TAG_NAME:
                        answer3 = childNodeList.item(j).getTextContent();
                        break;
                    case ANSWER4_TAG_NAME:
                        answer4 = childNodeList.item(j).getTextContent();
                        break;
                    case CORRECT_TAG_NAME:
                        correctAnswer = childNodeList.item(j).getTextContent();
                        break;
                }
            }
            List<String> answerList = new ArrayList<>();
            answerList.add(answer1);
            answerList.add(answer2);
            answerList.add(answer3);
            answerList.add(answer4);
            listOfThemeElements.add(new QuestionsInfoModel(question, answerList, Integer.parseInt(correctAnswer)));
        }
        return listOfThemeElements;

    }
}
