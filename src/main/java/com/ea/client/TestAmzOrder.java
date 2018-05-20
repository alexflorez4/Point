package main.java.com.ea.client;

import main.java.com.ea.services.SpreadSheetOps;
import main.java.com.ea.services.SpreadSheetOpsProdImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

public class TestAmzOrder {

    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("/application.xml");
        try
        {
            SpreadSheetOps spreadSheetOps = container.getBean(SpreadSheetOpsProdImpl.class);

            File fileToParse = new File("C:\\Users\\alexf\\Desktop\\testingParseFile.html");

            spreadSheetOps.processHtmlAmzOrdersFile("shades", fileToParse);
        }
        finally
        {
            container.close();
        }
    }
}
