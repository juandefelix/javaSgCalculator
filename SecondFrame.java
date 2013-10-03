package com.juanortiz;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

class SecondFrame {
	ThirdFrame t;
	Recipe r;
	JPanel mainPanel;
	ArrayList <JTextField> lbList;
	ArrayList <JTextField> ouncesList;
	
	JFrame theFrame;

	/********************************
	*           CONSTRUCTOR         *
	*********************************/
	
	public SecondFrame(Recipe x){
		this.r = x;
	}


// Basic layout of the GUI interface in buildGUI class
	
	
	/*******************************************************
	*           theFrame
	*               |
	*        backgroundPanel___________________               
	*         /                               |
	*     ButtonBox                         mainPanel	*        |                                | 
	*    Next Button           getListOfMalts() + checkBoxList[]
	*                                         
	*********************************************************/

	public void buildGUI(){
		
		/*****************
		 * FOR TEST ONLY *
		 *****************
		r = new Recipe();
		r.myMalts = new ArrayList<Malt>(); System.out.println("new Recipe r");
		r.volume = 5;
		Malt m = new Malt("Pale Malt (2 Row) UK"); r.myMalts.add(m); // m.lb = 10; m.oz = 0;
		Malt n = new Malt("Vienna Malt"); n.lb = 2.5f; r.myMalts.add(n); // n.oz = 0;
		Malt o = new Malt("Caramel/Crystal Malt – 60L"); r.myMalts.add(o); // o.lb = 1; o.oz = 0; r.myMalts.add(o);
		for (Malt x : r.myMalts) {
			System.out.println(x.potentialPoints());
		}
		System.out.printf("Specific Gravity: %.3f \n" ,r.specificGravity());
		
		--------------------------------------------------------------------------
		*/
		
		theFrame= new JFrame ("Specific Gravity Calculator");                              //creating 'theFrame'(JFrame)
		theFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		BorderLayout layout= new BorderLayout();
		JPanel background= new JPanel (layout);                                        //creating 'background' (JPanel)
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
		
		JPanel explanationPanel = new JPanel();
		JLabel explanation = new JLabel("Please, enter the weight of the ingredients:");
		explanationPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,10));
		explanationPanel.add(explanation);
		background.add(BorderLayout.NORTH, explanationPanel);
		
		Box buttonBox= new Box (BoxLayout.Y_AXIS);                                      //creating buttonBox (BoxObject)
		JButton next = new JButton ("Next");                                            //creating "Next" button 
		next.addActionListener(new MyNextListener());									//addig listener to the button
		buttonBox.add(next);                                                            //Adding buttons to buttonbox
		
		lbList = new ArrayList <JTextField>();                                 // initializing the arraylists
		ouncesList = new ArrayList <JTextField>();
		
		background.add(BorderLayout.EAST, buttonBox);                         //adding 'buttonBox' to 'background'JPannel 
		theFrame.getContentPane().add(background);                         //adding 'background' to 'theFrame' 
		
		GridLayout grid = new GridLayout (0, 5);                         //setting up 'grid' gridLayout
		grid.setVgap(1);
		grid.setHgap(20);
		mainPanel = new JPanel(grid);                         //new JPannel'mainPanel' with a grid layout 
		background.add(BorderLayout.CENTER, mainPanel);       //adding 'mainPanel' to 'background';
		for (Malt x: r.myMalts) {                           //adding Label JCheck to 'background'
			JLabel maltLabel = new JLabel(x.name); mainPanel.add(maltLabel);
			JLabel wLbLabel = new JLabel("Weight in pounds"); mainPanel.add(new JLabel("Weight in pounds")); 
			JTextField wLb = new JTextField("0"); mainPanel.add(wLb);lbList.add(wLb); 
			JLabel wOzLabel = new JLabel("Weight in ounces"); mainPanel.add(wOzLabel);
			JTextField wOz = new JTextField("0"); mainPanel.add(wOz); ouncesList.add(wOz); 
		}// end for
		
		theFrame.pack();		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = theFrame.getSize().width;
		int h = theFrame.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		theFrame.setLocation(x, y);
		theFrame.setVisible(true);
		
	}// end buildGUI method
		
	
	/*********************
	 *     LISTENERS     *
	 *********************/
	
	/*********************************************************************************************************
	 *     NextListener created a new Recipe and adds to the array myMalts all the malts selected in the GUI *
	 *********************************************************************************************************/
	
	public class MyNextListener implements ActionListener{
		public void actionPerformed(ActionEvent a){
			for(Malt m : r.myMalts){
				//System.out.println (m.name + " " + m.lb + " " + m.oz);    // for test purposes only
				int index = r.myMalts.indexOf(m);
				m.lb = Float.parseFloat(lbList.get(index).getText()); 
				m.oz = Integer.parseInt(ouncesList.get(index).getText());
			}
			theFrame.dispose();
			t = new ThirdFrame(r);
			t.buildGUI();
		} //close method
	} // end listener
}