package com.hackerdude.tools.propertyedit.model;

import java.util.*;
import java.io.*;

/**
 * A factory for property editor models. 
 * It can read them from regular property files, obtain their metadata and 
 * create the trees.
 *  
 * @author davidm <a href="mailto:davidm@yourdomain.com">davidm@yourdomain.com</a>
 */
public class PropertyEditorModelFactory {

	public static PropertyEditorModel createPropertyEditorModel(String propertyFileName) throws IOException {
		String modelName = new File(propertyFileName).getName();
		PropertyEditorModel model = new PropertyEditorModel(modelName);
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
		FileInputStream fileInputStream = new FileInputStream(propertyFileName);
		try {
			props.load(fileInputStream);
		} finally  { fileInputStream.close(); }
		return props;
	}

	private static HashMap getMetaData(String propertyFileName) throws IOException {
		Properties metaDataProps = new Properties();
		String metaFileName = propertyFileName+".metadata";
		FileInputStream metaFile = new FileInputStream(metaFileName);
		try {
			metaDataProps.load(metaFile);
		} finally {
			metaFile.close();
		}
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