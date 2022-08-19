import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class FileProcessor implements Runnable
{
    File selectedFile;

    public FileProcessor(File selectedFile, SearchObject searchObject)
    {
        this.selectedFile = selectedFile;
        this.searchObject = searchObject;
    }

    SearchObject searchObject;

    @Override
    public void run()
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
            long threadId = Thread.currentThread().getId();
            System.out.println("Current file: " + selectedFile.getName() + " in thread " + threadId);
            Document doc = dBuilder.parse(selectedFile);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/dataHolder/item";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node nNode = nodeList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) nNode;
                    if (
                            searchObject.getAccounts().contains(getAccountFromNode(eElement))
                            && searchObject.getHCPREPB().contains(getHCPREPBFromNode(eElement))
                            && searchObject.getOrderGroups().contains(getOrderGroupFromNode(eElement))
                            && searchObject.getScenario().equals(getScenarioFromNode(eElement))
                            && searchObject.getYear().equals(getYearFromNode(eElement))
                            && searchObject.getCountry().equals(getCountryFromNode(eElement))
                            && searchObject.getCoorderGroups().contains(getCoorderGroupFromNode(eElement)))
                    {
                        System.out.println("Found node in file: " + selectedFile.getName() + "Country: " + getCountryFromNode(
                                eElement));
                    }
                }
            }
            //System.out.println("Current file: " + selectedFile.getName() + " done with processing!");

        }

        catch (ParserConfigurationException | IOException | XPathExpressionException | SAXException e)
    {
        e.printStackTrace();
    }
    }


    private static String getCountryFromNode(Element node)
    {
        return getElementByTag("_-bic_-zxfMpct", node);
    }

    private static String getScenarioFromNode(Element node)
    {
        return getElementByTag("_-bic_-zbpscen", node);
    }

    private static String getYearFromNode(Element node)
    {
        return getElementByTag("Fiscyear", node);
    }

    private static String getAccountFromNode(Element node)
    {
        return getElementByTag("_-bic_-zkpAccnt", node);
    }

    private static String getHCPREPBFromNode(Element node)
    {
        return getElementByTag("_-bic_-zxfSbbrn", node);
    }

    private static String getOrderGroupFromNode(Element node)
    {
        return getElementByTag("_-bic_-zkpIogrp", node);
    }

    private static String getCoorderGroupFromNode(Element node)
    {
        return getElementByTag("Coorder", node);
    }

    private static String getElementByTag(String tag, Element node)
    {
        return node.getElementsByTagName(tag).item(0).getTextContent();
    }
}
