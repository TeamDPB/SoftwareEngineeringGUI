package gui;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import net.miginfocom.swing.MigLayout;

import java.awt.Panel;

import javax.swing.JTextField;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.beans.PropertyChangeListener;

public class MainT extends JPanel implements PropertyChangeListener {
	public MainT() {
	}
	private static final JLabel lblLocation = new JLabel("Location");
	private static final JMenuBar menuBar = new JMenuBar();
	private static final JMenu mnFile = new JMenu("File");
	private static final JMenuItem mntmOpen = new JMenuItem("Open");
	private static final JMenuItem mntmClear = new JMenuItem("Clear");
	public static final JLabel imagePrew = new JLabel("");
	private static final JTextField locationField = new JTextField();

	private static FileSystemModel fileSystemModel;
	private static final JScrollPane scrollPanenew = new JScrollPane();
	private static  Image img;
	private File f;
	private JLabel impr;
	private static JTree tree;

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		locationField.setColumns(10);
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("GUI");
		frame.setSize(983, 558);
		frame.getContentPane().setLayout(
				new MigLayout("debug", "[grow][grow]",
						"[][][grow][grow][][grow]"));

		frame.getContentPane().add(lblLocation, "flowx,cell 0 1,alignx left");

		frame.getContentPane().add(scrollPanenew, "cell 0 2 1 3,grow");
		imagePrew.setBackground(Color.PINK);

		frame.getContentPane().add(imagePrew,
				"cell 0 5,alignx center,aligny center");

		frame.getContentPane().add(locationField, "cell 0 1,growx");
		frame.setJMenuBar(menuBar);
		

		menuBar.add(mnFile);
		mnFile.add(mntmOpen);

		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {

					@Override
					protected Boolean doInBackground() throws Exception {
						// TODO Auto-generated method stub
						int result;
						String choosertitle = null;

						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new java.io.File("."));
						chooser.setDialogTitle(choosertitle);
						chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						chooser.setAcceptAllFileFilterUsed(false);
						//
						if (chooser.showOpenDialog(menuBar) == JFileChooser.APPROVE_OPTION) {

							locationField.setText(chooser.getSelectedFile()
									.toString());

							fileSystemModel = new FileSystemModel();
							final JTree tree = new JTree(fileSystemModel);
						
							scrollPanenew.setViewportView(tree);

							// frame.getContentPane().add(scrollPanenew,
							// BorderLayout.CENTER);
							tree.addTreeSelectionListener(new TreeSelectionListener() {
								public void valueChanged(TreeSelectionEvent e) {
									

									Object object = e.getPath()
											.getLastPathComponent();
									if (object instanceof File) {
										File file = (File) object;
									
										
										if (file.toString().toLowerCase().endsWith(".jpg") ) {
											
												BufferedImage image = null;
										        try
										        {
										          image = ImageIO.read(file);
										          DisplayImage di = new DisplayImage(file);
										          
										        }
										        catch (Exception e1)
										        {
										          e1.printStackTrace();
										          System.exit(1);
										        }
										        ImageIcon imageIcon = new ImageIcon(image);
										        Image images = imageIcon.getImage(); // transform it 
										        Image newimg = images.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
										        imageIcon = new ImageIcon(newimg);  // transform it back
										       // Dimension size = new Dimension(500,500);
										        imagePrew.setIcon(imageIcon);
										   	 tree.addMouseListener(new MouseAdapter() {
											      public void mouseClicked(MouseEvent e) {
											    	  if(e.getClickCount() >= 2){
											    		
											    		    
											    	  }
											      }
											    });   
										       
										}
										  }	
									
									
									
										}

									
															
							});
							

						} else {
							System.out.println("No Selection");
						}
						return null;
					}

				};
				worker.execute();

			}
		});
		
		

		mnFile.add(mntmClear);
		// JPanel panel = new JPanel();

		// frame.pack();
		// frame.setLocation(300, 300);
		frame.setVisible(true);
	}
	 static void doMouseClicked(MouseEvent me) {
		    TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
		    if (tp != null)
		      locationField.setText(tp.toString());
		    else
		      locationField.setText("");
		  }
	 public void paintComponent(Graphics g) {
		  // fill the background
		  if (img != null) {
		   // draw the image
		   g.drawImage(img,0,0,300,300,null);
		  
		  }
		 }
	
	/*
	 * 
	 * public static void TreeDisplay(String directory) {
	 * 
	 * display.setEditable(false); fileSystemModel = new FileSystemModel(new
	 * File(directory)); tree = new JTree(fileSystemModel);
	 * tree.setEditable(true); tree.addTreeSelectionListener(new
	 * TreeSelectionListener() { public void valueChanged(TreeSelectionEvent
	 * event) { File file = (File) tree.getLastSelectedPathComponent();
	 * display.setText(getFileDetails(file)); } }); JSplitPane splitPane = new
	 * JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane( tree), new
	 * JScrollPane(display));
	 * 
	 * }
	 */

	private static String getFileDetails(File file) {
		if (file == null)
			return "";
		StringBuffer buffer = new StringBuffer();
		buffer.append("Name: " + file.getName() + "\n");
		buffer.append("Path: " + file.getPath() + "\n");
		buffer.append("Size: " + file.length() + "\n");
		return buffer.toString();
	}

	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

}

class FileSystemModel implements TreeModel {
	private String root; // The root identifier

	private Vector listeners; // Declare the listeners vector

	public FileSystemModel() {

		root = System.getProperty("user.home");
		File tempFile = new File(root);
		root = tempFile.getParent();

		listeners = new Vector();
	}

	public Object getRoot() {
		return (new File(root));
	}

	public Object getChild(Object parent, int index) {
		File directory = (File) parent;
		String[] directoryMembers = directory.list();
		return (new File(directory, directoryMembers[index]));
	}

	public int getChildCount(Object parent) {
		File fileSystemMember = (File) parent;
		if (fileSystemMember.isDirectory()) {
			String[] directoryMembers = fileSystemMember.list();
			return directoryMembers.length;
		}

		else {

			return 0;
		}
	}

	public int getIndexOfChild(Object parent, Object child) {
		File directory = (File) parent;
		File directoryMember = (File) child;
		String[] directoryMemberNames = directory.list();
		int result = -1;

		for (int i = 0; i < directoryMemberNames.length; ++i) {
			if (directoryMember.getName().equals(directoryMemberNames[i])) {
				result = i;
				break;
			}
		}

		return result;
	}

	public boolean isLeaf(Object node) {
		return ((File) node).isFile();
	}

	public void addTreeModelListener(TreeModelListener l) {
		if (l != null && !listeners.contains(l)) {
			listeners.addElement(l);
		}
	}

	public void removeTreeModelListener(TreeModelListener l) {
		if (l != null) {
			listeners.removeElement(l);
		}
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
		// Does Nothing!
	}

	public void fireTreeNodesInserted(TreeModelEvent e) {
		Enumeration listenerCount = listeners.elements();
		while (listenerCount.hasMoreElements()) {
			TreeModelListener listener = (TreeModelListener) listenerCount
					.nextElement();
			listener.treeNodesInserted(e);
		}
	}

	public void fireTreeNodesRemoved(TreeModelEvent e) {
		Enumeration listenerCount = listeners.elements();
		while (listenerCount.hasMoreElements()) {
			TreeModelListener listener = (TreeModelListener) listenerCount
					.nextElement();
			listener.treeNodesRemoved(e);
		}

	}

	public void fireTreeNodesChanged(TreeModelEvent e) {
		Enumeration listenerCount = listeners.elements();
		while (listenerCount.hasMoreElements()) {
			TreeModelListener listener = (TreeModelListener) listenerCount
					.nextElement();
			listener.treeNodesChanged(e);
		}

	}

	public void fireTreeStructureChanged(TreeModelEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Enumeration listenerCount = listeners.elements();
				while (listenerCount.hasMoreElements()) {
					TreeModelListener listener = (TreeModelListener) listenerCount
							.nextElement();
					listener.treeStructureChanged(null);
				}

			}

		});
		
	}
	
}
class DisplayImage {
	 
	 private Image img;
	  
   public DisplayImage(File f) throws IOException
   {
       BufferedImage img=ImageIO.read(f);
       ImageIcon icon=new ImageIcon(img);
       Image images = icon.getImage(); // transform it 
       Image newimg = images.getScaledInstance(400, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
       icon = new ImageIcon(newimg);  // transform it back
       JFrame frame=new JFrame();
       
       frame.setLayout(new FlowLayout());
       frame.setSize(400,400);
       JLabel lbl=new JLabel();
       
       
       
     
       lbl.setIcon(icon);
       frame.add(lbl);
       
       frame.setVisible(true);
     
   }
   public void paintComponent(Graphics g) {
   	  // fill the background
   	  if (img != null) {
   	   // draw the image
   	   g.drawImage(img,0,0,300,300,null);
   	  
   	  }
   	 }
}
  