package xyz.sunnytoday.common.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xyz.sunnytoday.common.task.Task;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Xml Config 파일 (app-config.xml)을 읽는다.
 * 깃헙 리포지토리에 비공개로 앱키를 저장하고, 외부에서 수정 가능하게 활용하기 위해 사용
 */
public class XmlConfigParser {
    private final File file;
    private Map<String, Appkey> appKeys;
    private List<Task> tasks;

    public XmlConfigParser(String fileUrl) {
        this.file = new File(fileUrl);
        this.parse();
    }

    /**
     * 설정을 새로 읽어들인다.
     */
    public void parse() {
        this.appKeys = new HashMap<>();
        this.tasks = new ArrayList<>();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(this.file);
            document.getDocumentElement().normalize();

            NodeList appKeyList = document.getElementsByTagName("app-key");
            NodeList taskList = document.getElementsByTagName("task");


            // 앱키 정보 읽기
            for (int i = 0; i < appKeyList.getLength(); i++) {

                Element element = (Element) appKeyList.item(i);
                Appkey appkey = new Appkey(element.getElementsByTagName("name").item(0).getTextContent());
                NodeList childNodes = element.getElementsByTagName("key");

                for (int j = 0; j < childNodes.getLength(); j++) {
                    appkey.setKey(((Element) childNodes.item(j)).getAttribute("name")
                            , childNodes.item(j).getTextContent());
                }

                this.appKeys.put(appkey.getName(), appkey);
            }

            // 타이머 반복작업 정보 읽기
            for (int i = 0; i < taskList.getLength(); i++) {

                Element element = (Element) taskList.item(i);
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String classUrl = element.getElementsByTagName("class").item(0).getTextContent();
                int interval = Integer.parseInt(element.getElementsByTagName("interval").item(0).getTextContent());

                this.tasks.add(new Task(name, classUrl, interval));
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Appkey> getAppKeys() {
        return this.appKeys;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
