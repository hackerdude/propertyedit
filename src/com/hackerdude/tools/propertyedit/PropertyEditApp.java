package com.hackerdude.tools.propertyedit;

import javax.swing.UIManager;
import java.awt.*;
import com.hackerdude.tools.propertyedit.model.*;


/**
 * This sample application illustrates how to use the
 * Property Editor.
 */
public class PropertyEditApp {
	boolean packFrame = false;

	/**Construct the application*/
	public PropertyEditApp() {
		PropertyEditorFrame frame = new PropertyEditorFrame();
		//Validate frames that have preset sizes
		//Pack frames that have useful preferred size info, e.g. from their layout
		if (packFrame) {
			frame.pack();
		}
		else {
			frame.validate();
		}
		//Center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		frame.setVisible(true);
	}
	/**Main method*/
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		new PropertyEditApp();
	}
}