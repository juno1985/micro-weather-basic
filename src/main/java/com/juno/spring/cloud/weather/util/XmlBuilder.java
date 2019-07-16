package com.juno.spring.cloud.weather.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlBuilder {
	/**
	 * 将xml数据转化为java对象
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	
	public static Object xmlStringToObject(Class<?> clazz, String xmlStr) throws JAXBException, IOException {
		Object xmlObject = null;
		Reader reader = null;
		JAXBContext context = JAXBContext.newInstance(clazz);
		Unmarshaller shaller = context.createUnmarshaller();
		reader = new StringReader(xmlStr);
		xmlObject = shaller.unmarshal(reader);
		if(null!=reader) {
			reader.close();
		}
		return xmlObject;
	}
		

}
