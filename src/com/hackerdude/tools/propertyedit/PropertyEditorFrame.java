package com.hackerdude.tools.propertyedit;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

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
 * The Frame for the property editor sample application.
 */
public class PropertyEditorFrame extends JFrame {
	JPanel contentPane;
	JMenuBar jMenuBar1 = new JMenuBar();
	JMenu jMenuFile = new JMenu();
	JMenuItem jMenuFileExit = new JMenuItem();
	JMenu jMenuHelp = new JMenu();
	JMenuItem jMenuHelpAbout = new JMenuItem();
	JToolBar jToolBar = new JToolBar();
	JButton jButton1 = new JButton();
	JButton jButton2 = new JButton();
	JButton jButton3 = new JButton();
	ImageIcon image1;
	ImageIcon image2;
	ImageIcon image3;
	JLabel statusBar = new JLabel();
	BorderLayout borderLayout1 = new BorderLayout();
	PropertyEditorPanel pnlPropertyEditor = new PropertyEditorPanel();
	JFileChooser fChooser = new JFileChooser(System.getProperty("user.dir"));

	Action ACTION_FILEOPEN =new Action_FileOpen();

	/**Construct the frame*/
	public PropertyEditorFrame() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**Component initialization*/
	private void jbInit() throws Exception  {
		image1 = new ImageIcon(com.hackerdude.tools.propertyedit.PropertyEditorFrame.class.getResource("openFile.gif"));
		image2 = new ImageIcon(com.hackerdude.tools.propertyedit.PropertyEditorFrame.class.getResource("closeFile.gif"));
		image3 = new ImageIcon(com.hackerdude.tools.propertyedit.PropertyEditorFrame.class.getResource("help.gif"));
		//setIconImage(Toolkit.getDefaultToolkit().createImage(PropertyEditorFrame.class.getResource("[Your Icon]")));
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(400, 300));
		this.setTitle("Property Editor");
		statusBar.setText(" ");
		jMenuFile.setText("File");
		jMenuFileExit.setText("Exit");
		jMenuFileExit.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				jMenuFileExit_actionPerformed(e);
			}
		});
		jMenuHelp.setText("Help");
		jMenuHelpAbout.setText("About");
		jMenuHelpAbout.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				jMenuHelpAbout_actionPerformed(e);
			}
		});
		jButton1.setToolTipText("Open File");
		jButton1.setAction(ACTION_FILEOPEN);
		jButton2.setIcon(image2);
		jButton2.setToolTipText("Close File");
		jButton3.setIcon(image3);
		jButton3.setToolTipText("Help");
		jToolBar.add(jButton1);
		jToolBar.add(jButton2);
		jToolBar.add(jButton3);
		jMenuFile.add(jMenuFileExit);
		jMenuHelp.add(jMenuHelpAbout);
		jMenuBar1.add(jMenuFile);
		jMenuBar1.add(jMenuHelp);
		this.setJMenuBar(jMenuBar1);
		contentPane.add(jToolBar, BorderLayout.NORTH);
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


	class Action_FileOpen extends AbstractAction {
		public Action_FileOpen() {
			super("Open",image1);
		}

		public void actionPerformed(ActionEvent ev) {
			if ( fChooser.showDialog(null, "Open") == JFileChooser.APPROVE_OPTION ) {
				String fileName = fChooser.getSelectedFile().getAbsolutePath();

//				PropertyEditorModelFactory fac = new PropertyEditorModelFactory();
				try {
					PropertyEditorModel model = PropertyEditorModelFactory.createPropertyEditorModel(fileName);
					pnlPropertyEditor.jTree1.setModel(new PropertyTreeModel(model));
				} catch ( java.io.IOException exc ) {
					exc.printStackTrace();
				}
			}


		}

	}

}