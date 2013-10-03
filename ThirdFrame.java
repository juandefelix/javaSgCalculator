package com.juanortiz;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

class ThirdFrame {
	Recipe r;
	JPanel mainPanel;
	JFrame theFrameThree;
		

	/****************
	 * Construnctor *
	 ****************/
	
	public ThirdFrame(Recipe x){
		this.r = x;
	}
	
	
	// Basic layout of the GUI interface in buildGUI class
	/***********************************
	*           theFrame               *
	*               |                  *
	*        backgroundPanel           *
	*               |                  *
	*    JLabel +  JTextField + Button *
	************************************/
	
	public void buildGUI(){
		
		theFrameThree= new JFrame ("Specific Gravity Calculator");                         // creating 'theFrame'(JFrame)
		theFrameThree.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout= new BorderLayout();
		JPanel background= new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));                                          // creating 'background' (JPanel)    
		JPanel mainPanel = new JPanel();  
		
		theFrameThree.getContentPane().add(background);                                 // adding 'background' to 'theFrame'
		
		Box buttonBox= new Box (BoxLayout.Y_AXIS);                                      // creating buttonBox (BoxObject)
		JButton finish = new JButton ("Finish");                                        // creating "Finish" button 
		finish.addActionListener(new MyFinishListener());
		buttonBox.add(finish);                                                          // adding button to buttonbox
		background.add(BorderLayout.EAST, buttonBox);                                   // adding 'buttonBox' to 'background'JPannel 
		
		JPanel explanationPanel = new JPanel();
		JLabel explanation = new JLabel("We're almost done!");
		explanationPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,10));
		explanationPanel.add(explanation);
		background.add(BorderLayout.NORTH, explanationPanel);

		JLabel sgLabel = new JLabel("Estimated Specific Gravity of your recipe:"); 
		mainPanel.add(sgLabel);
		String s = String.format("%.3f", r.specificGravity());
		JTextField sgField = new JTextField(s); background.add(sgField);
		sgField.setEditable(false);
		mainPanel.add(sgField);
		background.add(BorderLayout.CENTER, mainPanel);                                   // adding 'mainPanel' to 'background'JPannel
		theFrameThree.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = theFrameThree.getSize().width;
		int h = theFrameThree.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		theFrameThree.setLocation(x, y);
		theFrameThree.setVisible(true);
		
	}// end buildGUI method
		
	
	/*********************
	 *     LISTENERS     *
	 *********************/
	
	public class MyFinishListener implements ActionListener{
		public void actionPerformed(ActionEvent a){
			theFrameThree.dispose();
		} //close method
	} // end listener
	
}