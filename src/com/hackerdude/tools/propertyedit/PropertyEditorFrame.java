package com.hackerdude.tools.propertyedit;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.hackerdude.tools.propertyedit.model.PropertyEditorModel;
import com.hackerdude.tools.propertyedit.model.PropertyEditorModelFactory;

/**
 * The Frame for the property editor application.
 */
public class PropertyEditorFrame extends JFrame {
	
private static final String WINDOW_TITLE = "Property Editor";
	JPanel contentPane;
	JMenuBar menuBar = new JMenuBar();
	JMenu mnuFile = new JMenu();
	JMenuItem mnuFileExit = new JMenuItem();
	JMenu mnuHelp = new JMenu();
	JMenuItem mnuHelpAbout = new JMenuItem();
	JToolBar toolBar = new JToolBar();
	JLabel statusBar = new JLabel();
	BorderLayout borderLayout1 = new BorderLayout();
	PropertyEditorPanel pnlPropertyEditor = new PropertyEditorPanel();
	JFileChooser fChooser = new JFileChooser(System.getProperty("user.dir"));
	
	private static final ImageIcon ICON_OPEN = new ImageIcon(PropertyEditorFrame.class.getResource("Open16.gif"));
	private static final ImageIcon ICON_SAVE = new ImageIcon(PropertyEditorFrame.class.getResource("Save16.gif"));
	private static final ImageIcon ICON_SAVE_AS = new ImageIcon(PropertyEditorFrame.class.getResource("SaveAs16.gif"));
	private static final ImageIcon ICON_HELP = new ImageIcon(PropertyEditorFrame.class.getResource("Help16.gif"));

	public final Action ACTION_FILEOPEN = new Action_FileOpen();
	public final Action ACTION_FILESAVE = new Action_FileSave();
	public final Action ACTION_FILESAVEAS = new Action_FileSaveAs();

	JButton openButton = new JButton(ACTION_FILEOPEN);
	JButton saveButton = new JButton(ACTION_FILESAVE);
	//JButton helpButton = new JButton();

	private String currentFileName = null;
	
	
	/**Construct the frame*/
	public PropertyEditorFrame() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		fChooser.setAcceptAllFileFilterUsed(true);
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**Component initialization*/
	private void jbInit() throws Exception  {
		//setIconImage(Toolkit.getDefaultToolkit().createImage(PropertyEditorFrame.class.getResource("[Your Icon]")));
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(400, 300));
		this.setTitle(WINDOW_TITLE);
		statusBar.setText(" ");
		mnuFile.setText("File");
		mnuFile.setMnemonic(KeyEvent.VK_F);
		mnuFileExit.setText("Exit");
		mnuFileExit.setMnemonic(KeyEvent.VK_X);

		mnuFileExit.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				jMenuFileExit_actionPerformed(e);
			}
		});
		mnuHelp.setText("Help");
		mnuHelp.setMnemonic(KeyEvent.VK_H);
		mnuHelpAbout.setText("About");
		mnuHelpAbout.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				jMenuHelpAbout_actionPerformed(e);
			}
		});
		openButton.setText(null);
		saveButton.setText(null);
		openButton.setToolTipText("Open File");
		saveButton.setToolTipText("Save File");
		//helpButton.setIcon(ICON_HELP);
		//helpButton.setToolTipText("Help");
		toolBar.add(openButton);
		toolBar.add(saveButton);
		//toolBar.add(helpButton);
		mnuFile.add(ACTION_FILEOPEN).setMnemonic(KeyEvent.VK_O);
		mnuFile.add(ACTION_FILESAVE).setMnemonic(KeyEvent.VK_S);
		mnuFile.add(ACTION_FILESAVEAS).setMnemonic(KeyEvent.VK_A);
		mnuFile.add(mnuFileExit);
		mnuHelp.add(mnuHelpAbout);
		menuBar.add(mnuFile);
		menuBar.add(mnuHelp);
		setJMenuBar(menuBar);
		contentPane.add(toolBar, BorderLayout.NORTH);
		contentPane.add(statusBar, BorderLayout.SOUTH);
		contentPane.add(pnlPropertyEditor, BorderLayout.CENTER);
	}
	/**File | Exit action performed*/
	public void jMenuFileExit_actionPerformed(ActionEvent e) {
		System.exit(0);
	}
	/**Help | About action performed*/
	public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
		PropertyEditorFrame_AboutBox dlg = new PropertyEditorFrame_AboutBox(this);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.show();
	}
	/**Overridden so we can exit when window is closed*/
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			jMenuFileExit_actionPerformed(null);
		}
	}


	/**
	 * 
	 */
	public void openFile(String fileName) {
		try {
			PropertyEditorModel model = PropertyEditorModelFactory.createPropertyEditorModel(fileName);
			pnlPropertyEditor.propertyTree.setModel(new PropertyTreeModel(model));
			pnlPropertyEditor.propertyTree.setSelectionPath(pnlPropertyEditor.propertyTree.getPathForRow(0));
			currentFileName = fChooser.getSelectedFile().getAbsolutePath();
			setTitle(WINDOW_TITLE+" - "+fChooser.getSelectedFile().getName());
		} catch ( java.io.IOException exc ) {
			exc.printStackTrace();
		}
	}

	/**
	 * Saves the document under the specified fileName.
	 * @param fileName
	 */
	protected void saveDocument(String fileName) {
		FileOutputStream fos = null;
		try {
			PropertyTreeModel treeModel = (PropertyTreeModel)pnlPropertyEditor.propertyTree.getModel();
			Properties properties = treeModel.getPropEditorModel().toProperties();
			fos = new FileOutputStream(fileName);
			properties.save(fos, "# Edited by PropertyEdit.");
		} catch ( java.io.IOException exc ) {
			exc.printStackTrace();
		} finally {
			if ( fos != null ) try { fos.close(); } catch (Exception exc) {}
		}
	}


	
	class Action_FileOpen extends AbstractAction {

		public Action_FileOpen() {
			super("Open",ICON_OPEN);
		}

		public void actionPerformed(ActionEvent ev) {
			if ( fChooser.showDialog(PropertyEditorFrame.this, "Open") == JFileChooser.APPROVE_OPTION ) {
				openFile(fChooser.getSelectedFile().getAbsolutePath());
			}

		}

	}



	class Action_FileSave extends AbstractAction {
		public Action_FileSave() {
			super("Save", ICON_SAVE);
		}

		public void actionPerformed(ActionEvent ev) {
			
			if ( currentFileName == null ) ACTION_FILESAVEAS.actionPerformed(ev);
			else saveDocument(currentFileName);

	}
}
	

	class Action_FileSaveAs extends AbstractAction {
		public Action_FileSaveAs() {
			super("Save As..", ICON_SAVE_AS);
		}

		public void actionPerformed(ActionEvent ev) {
			if ( fChooser.showDialog(PropertyEditorFrame.this, "Save") == JFileChooser.APPROVE_OPTION ) {
				String fileName = fChooser.getSelectedFile().getAbsolutePath();
				saveDocument(fileName);
				currentFileName = fChooser.getSelectedFile().getAbsolutePath();
				setTitle(WINDOW_TITLE+" - "+fChooser.getSelectedFile().getName());
			}

		}

	}
}