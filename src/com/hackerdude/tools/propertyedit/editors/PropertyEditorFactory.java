package com.hackerdude.tools.propertyedit.editors;

import javax.swing.JPanel;

/**
 * Title:        Property Editor SwingUI
 * Description:  A Swing UI to edit properties files in a tree-like format.
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author David Martinez
 * @version 1.0
 */

/**
 * This Factory can create a new property Editor panel depending on
 * the data type class.
 * <P>The factory is automatically called by the tree when there is a need
 * to get a different property editor because the selection has changed. It is
 * also useful by itself if you don't want the tree but want to use the
 * editor panels.
 */
public class PropertyEditorFactory extends JPanel {

	public PropertyEditorFactory() {
	}

	/**
	 * Call this method with a fully qualified classname and the factory will
	 * create the appropriate editor for the object.
	 */
	public static PropValueEditor createPropertyEditor(String dataTypeClass) {
	/** @todo Implement factory data in a properties file.  */
		if ( dataTypeClass == null ) return null;

		if ( dataTypeClass.equals("java.awt.Color") || dataTypeClass.equals("javax.swing.plaf.ColorUIResource") ) {
			return new ColorEditorPanel();
		}
		else if ( dataTypeClass.equals("java.awt.Font") || dataTypeClass.equals("javax.swing.plaf.FontUIResource") ) {
			return new FontEditor();
		} else if ( dataTypeClass.equals("java.lang.String") ) {
			return new StringEditor();
		} else if ( dataTypeClass.equals("java.lang.Boolean") ) {
			return new BooleanValueEditor();
		}
		else {
			System.out.println("I don't know how to edit "+dataTypeClass);
			return null; //new PropValueEditor();
		}
	}

}