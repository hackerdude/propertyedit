package com.hackerdude.tools.propertyedit.model;

import java.util.*;
import java.io.*;

public class PropertyEditorModelFactory {

	public static PropertyEditorModel createPropertyEditorModel(String propertyFileName) throws IOException {
		PropertyEditorModel model = new PropertyEditorModel(propertyFileName);
		Properties props = getProperties(propertyFileName);

		HashMap propsMetaData = new HashMap();
		try {
			propsMetaData = getMetaData(propertyFileName);
		}
		catch (Exception ex) {
			System.out.println("Couldn't read metadata... Will not show nifty editing help");
		}

		applyToModel(model, props, propsMetaData);
		return model;
	}

	public static PropertyEditorModel createPropertyEditorModel(String name, Map properties) {
		PropertyEditorModel model = new PropertyEditorModel(name);
		applyToModel(model, properties, new HashMap());
		return model;
	}


	private static void applyToModel(PropertyEditorModel model, Map props, HashMap metaData) {
		// First we need to sort all the values...
		TreeSet sortedSet = new TreeSet(props.keySet());
		Iterator it = sortedSet.iterator();
		while ( it.hasNext() ) {
			String key = (String)it.next();
			Object value = props.get(key);
//			System.out.println("Key: "+key);
			PropertyEditorNode newNode;
			if ( value != null ) newNode = model.getOrCreateNode(key, value);
			else newNode = model.getOrCreateNode(key, value);
			String description = (String)metaData.get(key);
			if ( description != null ) {
				newNode.setNodeDescription(description);
			}
			// TODO: Read the metadata and add it to the node.
		}
	}

	private static Properties getProperties(String propertyFileName) throws IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(propertyFileName));
		return props;
	}

	private static HashMap getMetaData(String propertyFileName) throws IOException {
		Properties metaDataProps = new Properties();
		String metaFileName = propertyFileName+".metadata";
		FileInputStream metaFile = new FileInputStream(metaFileName);
		metaDataProps.load(metaFile);
		return new HashMap(metaDataProps);
	}

	public static void main(String[] args ) {
		String fileName = "sample.properties";
		try {
			PropertyEditorModel model = PropertyEditorModelFactory.createPropertyEditorModel(fileName);
		} catch ( java.io.IOException exc ) {
			exc.printStackTrace();
		}

	}



}