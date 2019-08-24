package com.thinkgem.jeesite.common.xstream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4jDemo {
	
	public static void main(String[] args) throws DocumentException {
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root id=\"test\"><children>123</children></root>";
		Document document=DocumentHelper.parseText(xml);
		Element rootElement = document.getRootElement();
		System.out.println("根节点  "+rootElement.getName());
		System.out.println("根节点  "+rootElement.attributeCount());
		System.out.println("根节点  "+rootElement.attributeValue("id"));
		System.out.println("根节点  "+rootElement.getText());
		
		Document document2 = DocumentHelper.createDocument();
		Element addElement = document2.addElement("test");
		Element addElement2 = addElement.addElement("test2");
		addElement2.setText("name");
		addElement2.addAttribute("id","id1");
		System.out.println(document2.asXML());
	}

}
