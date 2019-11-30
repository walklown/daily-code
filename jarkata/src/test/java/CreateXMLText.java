
import lombok.Getter;
import lombok.Setter;
import org.dom4j.DocumentFactory;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * DOM、SAX、DOM4J、JDOM、StAX生成XML，返回各种方式生成XML的字符串形式
 * <p>
 * <cities><city><id>151</id><name>长沙市</name></city><city><id>152</id><name>永州市
 *
 * </name></city></cities>
 * </p>
 *
 * @author fzbtshy@163.com
 */
public class CreateXMLText {

    /**
     * DOM方式生成XML
     *
     * @param list
     * @return
     */
    public String domToXml(List<City> list) {
        String xmlStr = null;
        try {
            if (null != list && !list.isEmpty()) {

                // 实例化DOM生成工厂
                DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                // 由工厂实例化文档创建器
                DocumentBuilder db = dbf.newDocumentBuilder();

                // 由文档创建器实例化文档对象
                org.w3c.dom.Document doc = db.newDocument();
                // 文档对象创建一个根元素<cities>
                org.w3c.dom.Element cities = doc.createElement("cities");
                doc.appendChild(cities);

                for (City po : list) {

                    // 创建<cities>的子元素<city>
                    org.w3c.dom.Element cityElt = doc.createElement("city");
                    cities.appendChild(cityElt);

                    // 创建<city>的子元素<id>
                    org.w3c.dom.Element idElt = doc.createElement("id");
                    cityElt.appendChild(idElt);

                    // 创建子元素<id>的文本值
                    // 第一种方式
//                    Text idTxt = doc.createTextNode(String.valueOf(c.getId()));
                    // idElt.appendChild(idTxt);
                    // 第二种方式
                    idElt.setTextContent(String.valueOf(po.getId()));

                    // 创建<city>的子元素<name>
                    org.w3c.dom.Element nameElt = doc.createElement("name");
                    cityElt.appendChild(nameElt);

                    // 创建子元素<name>的文本值
                    Text nameTxt = doc.createTextNode(po.getName());
                    nameElt.appendChild(nameTxt);
//     nameElt.setTextContent(po.getName());

                }
                xmlStr = getDomXml(doc);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        System.out.println("DOM:" + xmlStr);
        return xmlStr;
    }

    /**
     * 将org.w3c.dom.Document内容转化成String
     *
     * @param doc
     * @return
     */
    private String getDomXml(Node doc) {
        String xmlStr = null;
        try {
// 以 Document Object Model（DOM）树的形式充当转换 Source 树的持有者
            DOMSource source = new DOMSource(doc);

            //用来生成XML文件
            // 要生成文件需构造PrintWriter的writer,
//DOM中这种方式name的值没有写进去,由于编码的问题
//   PrintWriter writerXml = new PrintWriter(new FileOutputStream("city-dom.xml"));
            //用OutputStreamWriter加了编码就OK了
            PrintWriter writerXml = new PrintWriter(new OutputStreamWriter(new FileOutputStream("city-jdom.xml"), "utf-8"));
            Result resultXml = new StreamResult(writerXml);
            // 实现此接口的对象包含构建转换结果树所需的信息
//   Result resultXml = new StreamResult(new FileOutputStream("city-dom.xml "));

            //用来得到XML字符串形式
            // 一个字符流，可以用其回收在字符串缓冲区中的输出来构造字符串。
            StringWriter writerStr = new StringWriter();
            Result resultStr = new StreamResult(writerStr);

            // 此抽象类的实例能够将源树转换为结果树。
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer();
            // 设置编码
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            // 是否缩进
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // 将 XML Source 转换为 Result
            transformer.transform(source, resultXml);
            transformer.transform(source, resultStr);

            //获取XML字符串
            xmlStr = writerStr.getBuffer().toString();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlStr;
    }

    /**
     * SAX方式生成XML
     *
     * @param list
     * @return
     */
    public String saxToXml(List<City> list) {
        String xmlStr = null;
        try {
            //用来生成XML文件
            // 要生成文件需构造PrintWriter的writer
//   PrintWriter writerXml = new PrintWriter("city-sax.xml");
//   Result resultXml = new StreamResult(writerXml);
            // 实现此接口的对象包含构建转换结果树所需的信息
//            Result resultXml = new StreamResult(new FileOutputStream("city-sax.xml"));

            //用来得到XML字符串形式
            // 一个字符流，可以用其回收在字符串缓冲区中的输出来构造字符串
            StringWriter writerStr = new StringWriter();
            // 构建转换结果树所需的信息。
            Result resultStr = new StreamResult(writerStr);

            // 创建SAX转换工厂
            SAXTransformerFactory sff = (SAXTransformerFactory)

                    SAXTransformerFactory
                            .newInstance();
            // 转换处理器，侦听 SAX ContentHandler
//解析事件，并将它们转换为结果树 Result
            TransformerHandler th = sff.newTransformerHandler();
            // 将源树转换为结果树
            Transformer transformer = th.getTransformer();
            // 设置字符编码
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            // 是否缩进
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            //设置与用于转换的此 TransformerHandler 关联的 Result
            //注：这两个th.setResult不能同时启用
//   th.setResult(resultXml);
            th.setResult(resultStr);

            th.startDocument();
            AttributesImpl attr = new AttributesImpl();
            th.startElement("", "", "cities", attr);
            if (null != list && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    th.startElement("", "", "city", attr);

                    th.startElement("", "", "id", attr);
                    String id = String.valueOf(list.get(i).getId());
                    th.characters(id.toCharArray(), 0, id.length());
                    th.endElement("", "", "id");

                    th.startElement("", "", "name", attr);
                    String name = String.valueOf(list.get(i).getName());
                    th.characters(name.toCharArray(), 0, name.length());
                    th.endElement("", "", "name");

                    th.endElement("", "", "city");
                }
            }

            th.endElement("", "", "cities");
            th.endDocument();
            xmlStr = writerStr.getBuffer().toString();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("SAX:" + xmlStr);
        return xmlStr;
    }

    /**
     * StAX生成XML，它是The Streaming API for XML简称
     * JDK1.6的新增
     *
     * @param list
     * @return
     */
    public String stAXToXml(List<City> list) {
        String xmlStr = null;
        try {
            if (null != list && !list.isEmpty()) {
                StringWriter writerStr = new StringWriter();

                PrintWriter writerXml = new PrintWriter(new OutputStreamWriter(new FileOutputStream("city-StAX.xml"), "utf-8"));

//定义用于获取 XMLEventWriter 和 XMLStreamWriter 的工厂抽象实现
                XMLOutputFactory xof = XMLOutputFactory.newInstance();
                //指定如何写XML，注：以下两行只能启用一行
    XMLStreamWriter xmlsw = xof.createXMLStreamWriter(writerStr);
//                XMLStreamWriter xmlsw = xof.createXMLStreamWriter(writerXml);

                //写入XML文档声明
                xmlsw.writeStartDocument("UTF-8", "1.0");
                xmlsw.writeStartElement("cities");
                // 写入注释到xml文档
                xmlsw.writeComment("省和城市信息");
                for (City po : list) {
                    xmlsw.writeStartElement("city");
                    //添加<id>节点
                    xmlsw.writeStartElement("id");
                    xmlsw.writeCharacters(String.valueOf(po.getId()));
                    // 结束<id>节点
                    xmlsw.writeEndElement();
                    //添加<name>节点
                    xmlsw.writeStartElement("name");
                    xmlsw.writeCharacters(po.getName());
                    // 结束<name>节点
                    xmlsw.writeEndElement();

                    xmlsw.writeEndElement();
                }
                // 结束<cities>节点
                xmlsw.writeEndElement();
                // 结束 XML 文档
                xmlsw.writeEndDocument();
                xmlsw.flush();
                xmlsw.close();

                xmlStr = writerStr.getBuffer().toString();
                writerStr.close();
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("StAX:" + xmlStr);
        return xmlStr;
    }

    /**
     * JDOM方式生成XML
     *
     * @param list
     * @return
     */
    public String jdomToXml(List<City> list) {
        String xmlStr = null;
        try {
            // 创建文档根节点<cities>
            Element citiesElt = new Element("cities");
            if (null != list && !list.isEmpty()) {
                for (City po : list) {
                    // 创建子元素节点<city>
                    Element cityElt = new Element("city");
                    // 创建子元素节点<id>
                    Element idElt = new Element("id");
                    // 向元素<city>中添加子元素<id>
                    cityElt.addContent(idElt);
                    // 添加id文本
                    idElt.addContent(String.valueOf(po.getId()));

                    // 创建元素节点<name>
                    Element nameElt = new Element("name");
                    // 向元素<city>中添加子元素<name>
                    cityElt.addContent(nameElt);
                    // 添加name文本
                    nameElt.addContent(po.getName());

                    // 在文档根节点添加子节点<city>
                    citiesElt.addContent(cityElt);
                }
            }

            Document doc = new Document(citiesElt);
            XMLOutputter out = new XMLOutputter();
            //获得XML字符串形式
            xmlStr = out.outputString(doc);

            //生成XML文件
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new

                    FileOutputStream("city-jdom.xml"), "utf-8"));

            out.output(doc, writer);
            writer.flush();
            writer.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JDOM:" + xmlStr);
        return xmlStr;
    }

    /**
     * DOM4J生成XML
     *
     * @param list
     * @return
     */
    public String dom4jToXml(List<City> list) {
        String strXml = null;
        try {
            if (null != list && !list.isEmpty()) {
                DocumentFactory df = DocumentFactory.getInstance();
                // org.dom4j.Document doc = DocumentHelper.createDocument();
                org.dom4j.Document doc = df.createDocument("UTF-8");
                // 创建根节点
                org.dom4j.Element citiesElt = doc.addElement("cities");

                for (City po : list) {
                    // 在节点<cities>下增加子节点<city>
                    org.dom4j.Element cityElt = citiesElt.addElement

                            ("city");

                    // 在节点<city>下增加子节点<id>
                    org.dom4j.Element idElt = cityElt.addElement

                            ("id");
                    idElt.addText(String.valueOf(po.getId()));

                    // 在节点<city>下增加子节点<name>
                    org.dom4j.Element nameElt = cityElt.addElement

                            ("name");
                    nameElt.addText(po.getName());
                }

                // 有样式(缩进)的写出
                OutputFormat opf = OutputFormat.createPrettyPrint();
                opf.setEncoding("UTF-8");
                opf.setTrimText(true);

                //生成XML文件
                XMLWriter xmlOut = new XMLWriter(new FileOutputStream

                        ("city-dom4j.xml"), opf);
                xmlOut.write(doc);
                xmlOut.flush();
                xmlOut.close();

                //获取XML字符串形式
                StringWriter writerStr = new StringWriter();
                XMLWriter xmlw = new XMLWriter(writerStr, opf);
                xmlw.write(doc);
                strXml = writerStr.getBuffer().toString();

                // 无样式的
                //   strXml = doc.asXML();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("DOM4J:" + strXml);
        return strXml;
    }

    public static void main(String[] args) throws Exception {
        List<City> list = City.getCityList();
        CreateXMLText xml = new CreateXMLText();
        xml.domToXml(list);
        xml.saxToXml(list);
        xml.stAXToXml(list);
        xml.dom4jToXml(list);
        xml.jdomToXml(list);
    }

}

@Getter
@Setter
class City {
    private String id;

    private String name;

    private static List<City> cityList = new LinkedList<City>() {{
        add(new City() {{
            setId("1");
            setName("杭州");
        }});
    }};

    public static List<City> getCityList() {
        return cityList;
    }
}