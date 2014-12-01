
import java.awt.Choice;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

import net.miginfocom.swing.MigLayout;

import java.awt.Panel;

import javax.swing.JTextField;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;


public class Main {
	private static final JLabel lblLocation = new JLabel("Location");
	//private static final JLabel lblFolders = new JLabel("Folders");
	private static final JLabel lblImagePreview = new JLabel("Image Preview");
	private static final JPanel panel_2 = new JPanel();
	private static final JMenuBar menuBar = new JMenuBar();
	private static final JMenu mnFile = new JMenu("File");
	private static final JMenuItem mntmOpen = new JMenuItem("Open");
	private static final JMenuItem mntmClear = new JMenuItem("Clear");
	private static final JLabel imagePrew = new JLabel("");
	private static final JTextField locationField = new JTextField();
	
	private static FileSystemModel fileSystemModel;
	private static JTextArea fileDetailsTextArea = new JTextArea();
	private static JTree tree = new JTree();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		locationField.setColumns(10);
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("GUI");
		frame.setSize(983, 558);
		frame.getContentPane().setLayout(
				new MigLayout("debug", "[grow][grow]", "[][][][grow][][grow]"));

		frame.getContentPane().add(lblLocation, "flowx,cell 0 1,alignx left");
		
		frame.getContentPane().add(tree, "cell 0 3,grow");
		frame.getContentPane().add(panel_2, "cell 1 3 1 3,grow");
		frame.getContentPane().add(lblImagePreview, "cell 0 4,alignx left");
		
		frame.getContentPane().add(imagePrew, "cell 0 5,growy");
		
		frame.getContentPane().add(locationField, "cell 0 1,growx");
		frame.setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		mnFile.add(mntmOpen);
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				JFileChooser jfc = new JFileChooser();
//	
//				if(jfc.showOpenDialog(menuBar) == JFileChooser.APPROVE_OPTION){
//					
//		                    File file = jfc.getSelectedFile();
//		        			String filename = file.getAbsolutePath();
//		                    try {
//		                   
//		                        imagePrew.setIcon(new ImageIcon(ImageIO.read(file)));
//		                        locationField.setText(filename);
//		                     
//		                    } catch (IOException e) {
//		                        e.printStackTrace();
//		                    }
//		                }
//		        
			    int result;
			    String choosertitle = null;
			    
			   JFileChooser chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle(choosertitle);
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    //
			    // disable the "All files" option.
			    //
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(menuBar) == JFileChooser.APPROVE_OPTION) { 	
					  //System.out.println("getCurrentDirectory(): " +  chooser.getCurrentDirectory());
					 // System.out.println("getSelectedFile() : "  +  chooser.getSelectedFile()); 
			    	locationField.setText(chooser.getSelectedFile().toString());
			    	
			    	
			    	fileDetailsTextArea.setEditable(false);
				    fileSystemModel = new FileSystemModel(new File(chooser.getSelectedFile().toString()));
				    tree = new JTree(fileSystemModel);
				    tree.setEditable(true);
				    tree.addTreeSelectionListener(new TreeSelectionListener() {
					      public void valueChanged(TreeSelectionEvent event) {
					        File file = (File) tree.getLastSelectedPathComponent();
					        fileDetailsTextArea.setText(getFileDetails(file));
					      }
					    });
				    
				    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane(tree), new JScrollPane(fileDetailsTextArea));
				    
				    frame.getContentPane().add(splitPane);
				   
			    	
			    	
			    	
			    	
			    	
			    	//TreeDisplay(chooser.getSelectedFile().toString());
			    }
			    else{
			    	System.out.println("No Selection");
			      }
			    }  
		});
		
		mnFile.add(mntmClear);
		JPanel panel = new JPanel();

		// frame.pack();
		// frame.setLocation(300, 300);
		frame.setVisible(true);
	}
	
	
	
	
	
	  public static void TreeDisplay(String directory) {
		  
		    fileDetailsTextArea.setEditable(false);
		    fileSystemModel = new FileSystemModel(new File(directory));
		    tree = new JTree(fileSystemModel);
		    tree.setEditable(true);
		    tree.addTreeSelectionListener(new TreeSelectionListener() {
		      public void valueChanged(TreeSelectionEvent event) {
		        File file = (File) tree.getLastSelectedPathComponent();
		        fileDetailsTextArea.setText(getFileDetails(file));
		      }
		    });
		    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane(
		            tree), new JScrollPane(fileDetailsTextArea));
		   
		      }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  private static String getFileDetails(File file) {
		    if (file == null)
		      return "";
		    StringBuffer buffer = new StringBuffer();
		    buffer.append("Name: " + file.getName() + "\n");
		    buffer.append("Path: " + file.getPath() + "\n");
		    buffer.append("Size: " + file.length() + "\n");
		    return buffer.toString();
		  }

}

class FileSystemModel implements TreeModel {
	  private File root;
	 
	  private Vector listeners = new Vector();
	 
	  public FileSystemModel(File rootDirectory) {
	    root = rootDirectory;
	  }
	 
	  public Object getRoot() {
	    return root;
	  }
	 
	  public Object getChild(Object parent, int index) {
	    File directory = (File) parent;
	    String[] children = directory.list();
	    return new TreeFile(directory, children[index]);
	  }
	 
	  public int getChildCount(Object parent) {
	    File file = (File) parent;
	    if (file.isDirectory()) {
	      String[] fileList = file.list();
	      if (fileList != null)
	        return file.list().length;
	    }
	    return 0;
	  }
	 
	  public boolean isLeaf(Object node) {
	    File file = (File) node;
	    return file.isFile();
	  }
	 
	  public int getIndexOfChild(Object parent, Object child) {
	    File directory = (File) parent;
	    File file = (File) child;
	    String[] children = directory.list();
	    for (int i = 0; i < children.length; i++) {
	      if (file.getName().equals(children[i])) {
	        return i;
	      }
	    }
	    return -1;
	 
	  }
	 
	  public void valueForPathChanged(TreePath path, Object value) {
	    File oldFile = (File) path.getLastPathComponent();
	    String fileParentPath = oldFile.getParent();
	    String newFileName = (String) value;
	    File targetFile = new File(fileParentPath, newFileName);
	    oldFile.renameTo(targetFile);
	    File parent = new File(fileParentPath);
	    int[] changedChildrenIndices = { getIndexOfChild(parent, targetFile) };
	    Object[] changedChildren = { targetFile };
	    fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);
	 
	  }
	 
	  private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
	    TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
	    Iterator iterator = listeners.iterator();
	    TreeModelListener listener = null;
	    while (iterator.hasNext()) {
	      listener = (TreeModelListener) iterator.next();
	      listener.treeNodesChanged(event);
	    }
	  }
	 
	  public void addTreeModelListener(TreeModelListener listener) {
	    listeners.add(listener);
	  }
	 
	  public void removeTreeModelListener(TreeModelListener listener) {
	    listeners.remove(listener);
	  }
	 
	  private class TreeFile extends File {
	    public TreeFile(File parent, String child) {
	      super(parent, child);
	    }
	 
	    public String toString() {
	      return getName();
	    }
	  }
	}
