package gui;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import java.awt.Panel;
import javax.swing.JTextField;

public class Main {
	private static final JLabel lblLocation = new JLabel("Location");
	private static final JLabel lblFolders = new JLabel("Folders");
	private static final JLabel lblImagePreview = new JLabel("Image Preview");
	private static final JPanel panel_2 = new JPanel();
	private static final JPanel panelChooser = new JPanel();
	private static final JMenuBar menuBar = new JMenuBar();
	private static final JMenu mnFile = new JMenu("File");
	private static final JMenuItem mntmOpen = new JMenuItem("Open");
	private static final JMenuItem mntmClear = new JMenuItem("Clear");
	private static final JLabel imagePrew = new JLabel("");
	private static final JPanel panel_1 = new JPanel();
	private static final JTextField locationField = new JTextField();

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
		frame.getContentPane().add(lblFolders, "cell 0 2");
		frame.getContentPane().add(panelChooser, "flowx,cell 0 3,alignx left,growy");
		frame.getContentPane().add(panel_2, "cell 1 3 1 3,grow");
		frame.getContentPane().add(lblImagePreview, "cell 0 4,alignx left");
		
		frame.getContentPane().add(imagePrew, "cell 0 5,growy");
		
		frame.getContentPane().add(panel_1, "cell 0 3");
		
		frame.getContentPane().add(locationField, "cell 0 1,growx");
		frame.setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		mnFile.add(mntmOpen);
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser jfc = new JFileChooser();
	
				if(jfc.showOpenDialog(menuBar) == JFileChooser.APPROVE_OPTION){
					
		                    File file = jfc.getSelectedFile();
		        			String filename = file.getAbsolutePath();
		                    try {
		                   
		                        imagePrew.setIcon(new ImageIcon(ImageIO.read(file)));
		                        locationField.setText(filename);
		                     
		                    } catch (IOException e) {
		                        e.printStackTrace();
		                    }
		                }
		        
				
			}
		});
		
		mnFile.add(mntmClear);
		JPanel panel = new JPanel();

		// frame.pack();
		// frame.setLocation(300, 300);
		frame.setVisible(true);
	}

}
