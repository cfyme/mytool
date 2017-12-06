package com.fshows.commons.util;

import com.alibaba.fastjson.JSONObject;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public final class XmlStream {
    private final static String ROOT_ELEMENT_XML = "Result";
    private final static String XML_VERSION = "1.0";
    private final static ConcurrentHashMap<Class<?>, JAXBContext> jaxbContexts = new ConcurrentHashMap<Class<?>, JAXBContext>();

    /**
     * Xml2Bean
     *
     * @param content xml内容
     * @param clazz   bean类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromXML(InputStream content, Class<T> clazz) {
        JAXBContext jaxbContext = getJaxbContext(clazz);
        try {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Source source = new StreamSource(content);
            XmlRootElement rootElement = clazz
                    .getAnnotation(XmlRootElement.class);
            if (rootElement == null
                    || rootElement.name().equals(
                    XmlRootElement.class.getMethod("name")
                            .getDefaultValue().toString())) {
                JAXBElement<T> jaxbElement = unmarshaller.unmarshal(source,
                        clazz);
                return jaxbElement.getValue();
            } else {
                return (T) unmarshaller.unmarshal(source);
            }
        } catch (JAXBException ex) {
            throw new RuntimeException("Could not unmarshaller class [" + clazz
                    + "]: " + ex.getMessage(), ex);
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException("Could not unmarshaller class [" + clazz
                    + "]: " + ex.getMessage(), ex);
        } finally {
            if (content != null) {
                try {
                    content.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
    }

    /**
     * Xml2Bean
     *
     * @param content xml内容
     * @param clazz   bean类型
     * @return
     */
    public static <T> T fromXML(String content, Class<T> clazz, Charset charset) {
        return fromXML(new ByteArrayInputStream(content.getBytes(charset)), clazz);
    }

    /**
     * map2xml
     *
     * @param map value无嵌套的map
     * @return xml内容
     */
    public static String map2xml(Map<String, String> map, Charset charset) {
        StringWriter sw = new StringWriter();
        try {
            XMLStreamWriter xw = XMLOutputFactory.newInstance()
                    .createXMLStreamWriter(sw);
            xw.writeStartDocument(charset.name(), XML_VERSION);
            xw.writeStartElement(ROOT_ELEMENT_XML);
            for (Entry<String, String> entry : map.entrySet()) {
                if (StringUtil.isBlank(entry.getValue())) {
                    continue;
                }
                xw.writeStartElement(entry.getKey());
                xw.writeCData(entry.getValue());
                xw.writeEndElement();
            }
            xw.writeEndDocument();
            xw.flush();
            xw.close();
        } catch (XMLStreamException e) {
            throw new IllegalArgumentException(e);
        } finally {
            try {
                sw.close();
            } catch (IOException e) {
                ;
            }
        }
        return sw.getBuffer().toString();
    }

    /**
     * map2xml
     *
     * @param json value无嵌套的json
     * @return xml内容
     */
    public static String map2xml(JSONObject json, Charset charset) {
        StringWriter sw = new StringWriter();
        try {
            XMLStreamWriter xw = XMLOutputFactory.newInstance()
                    .createXMLStreamWriter(sw);
            xw.writeStartDocument(charset.name(), XML_VERSION);
            xw.writeStartElement(ROOT_ELEMENT_XML);
            for (Entry<String, Object> entry : json.entrySet()) {
                if (StringUtil.isBlank(json.getString(entry.getKey()))) {
                    continue;
                }
                xw.writeStartElement(entry.getKey());
                xw.writeCData(json.getString(entry.getKey()));
                xw.writeEndElement();
            }
            xw.writeEndDocument();
            xw.flush();
            xw.close();
        } catch (XMLStreamException e) {
            throw new IllegalArgumentException(e);
        } finally {
            try {
                sw.close();
            } catch (IOException e) {
                ;
            }
        }
        return sw.getBuffer().toString();
    }

    /**
     * xml2map
     *
     * @param content 无嵌套节点的xml内容
     * @return map对象
     */
    public static Map<String, String> xml2map(String content) {
        Map<String, String> map = new HashMap<String, String>();
        StringReader sr = new StringReader(content);
        try {
            XMLStreamReader xr = XMLInputFactory.newInstance()
                    .createXMLStreamReader(sr);
            while (true) {
                int event = xr.next();
                if (event == XMLStreamConstants.END_DOCUMENT) {
                    xr.close();
                    break;
                } else if (event == XMLStreamConstants.START_ELEMENT) {
                    String name = xr.getLocalName();
                    while (true) {
                        event = xr.next();
                        if (event == XMLStreamConstants.START_ELEMENT) {
                            name = xr.getLocalName();
                        } else if (event == XMLStreamConstants.END_ELEMENT) {
                            break;
                        } else if (event == XMLStreamConstants.CHARACTERS) {
                            String value = xr.getText();
                            map.put(name, value);
                        }
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new IllegalArgumentException(e);
        } finally {
            sr.close();
        }
        return map;
    }

    /**
     * Bean2Xml
     *
     * @param object bean对象
     * @return xml内容
     */
    public static String toXML(Object object, Charset charset) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        toXML(object, os, charset);
        return StringUtil.newString(os.toByteArray(), charset);
    }

    /**
     * Bean2Xml
     *
     * @param t  bean对象
     * @param os 输出流
     */
    @SuppressWarnings("unchecked")
    public static <T> void toXML(T t, OutputStream os, Charset charset) {
        Class<T> clazz = (Class<T>) t.getClass();
        JAXBContext jaxbContext = getJaxbContext(clazz);
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING,
                    charset.name());
            XmlRootElement rootElement = clazz
                    .getAnnotation(XmlRootElement.class);
            if (rootElement == null
                    || rootElement.name().equals(
                    XmlRootElement.class.getMethod("name")
                            .getDefaultValue().toString())) {
                marshaller.marshal(new JAXBElement<T>(new QName(
                        ROOT_ELEMENT_XML), clazz, t), os);
            } else {
                marshaller.marshal(t, os);
            }
        } catch (JAXBException ex) {
            throw new RuntimeException("Could not marshal class [" + clazz
                    + "]: " + ex.getMessage(), ex);
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException("Could not marshaller class [" + clazz
                    + "]: " + ex.getMessage(), ex);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    ;
                }
            }
        }
    }

    private static JAXBContext getJaxbContext(Class<?> clazz) {
        JAXBContext jaxbContext = jaxbContexts.get(clazz);
        if (jaxbContext == null) {
            try {
                jaxbContext = JAXBContext.newInstance(clazz);
                jaxbContexts.putIfAbsent(clazz, jaxbContext);
            } catch (JAXBException ex) {
                throw new RuntimeException(
                        "Could not instantiate JAXBContext for class [" + clazz
                                + "]: " + ex.getMessage(), ex);
            }
        }
        return jaxbContext;
    }
}
