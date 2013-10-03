package com.juanortiz;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;


public class SpecificGravityCalculator {
	Recipe r;
	SecondFrame s;
	JPanel mainPanel;
	ArrayList <JCheckBox> checkboxList;
	JFrame theFrame;
	JLabel vol;
	JTextField volField;
	
/*****************************************************************************************************
 * this METHOD returns an arrayList<String> containing the malt names in "Beer_ingredients_test.txt"
 *****************************************************************************************************/

	public ArrayList<String> getListOfMalts(){
				ArrayList<String> malts = new ArrayList<String>();
				try {
					FileReader fileReader = new FileReader (new File("Beer_ingrediens_test.txt"));
					BufferedReader reader = new BufferedReader (fileReader);
					String line= null;
					reader.readLine();
					while ((line = reader.readLine() ) !=null) {
						String [] maltDescriptionArray = line.split("\t");
						malts.add(maltDescriptionArray[0]);
					}
					
					reader.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				} //end catch
				return malts;
		} // end method
		
/*--------------------------------------MAIN METHOD--------------------------------------*/
	
	public static void main(String[] args) {
		SpecificGravityCalculator c = new SpecificGravityCalculator();
		c.buildGUI();	
	}
	

	// Basic layout of the GUI interface in buildGUI class
			/*******************************************************
			*           theFrame
			*               |
			*        backgroundPanel___________________               
			*         /                               |
			* buttonBox                           mainPanel			*     |                                   | 
			* 5 x JButton              getListOfMalts() + checkBoxList[]
			*                                         |
			*                              JCheckBoxes +  Labels
			*********************************************************/
	
	public void buildGUI(){
		
		
		// System.out.println("new BeatBox"); // only for test
		theFrame = new JFrame ("Specific Gravity Calculator");                            // creating 'theFrame'(JFrame)
		theFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		BorderLayout layout= new BorderLayout();
		JPanel background= new JPanel (layout);                                        // creating 'background' (JPanel)
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,10));
		
		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox= new Box (BoxLayout.Y_AXIS);                                     // creating buttonBox (BoxObject)
		

		
		/*******************************
		 * Adding buttons to buttonbox *
		 *******************************/
		
		JButton next = new JButton ("Next");
		next.addActionListener(new MyNextListener());
		buttonBox.add(next);
		
		//JButton previous = new JButton ("Previous");
		//previous.addActionListener(new MyPreviousListener());
		//buttonBox.add(previous);
				
		//JButton saveButton = new JButton ("Save Recipe");
		//saveButton.addActionListener(new MySaveListener());
		//buttonBox.add(saveButton);
		
		//JButton loadButton = new JButton ("Load Recipe");
		//loadButton.addActionListener(new MyLoadInListener());
		//buttonBox.add(loadButton);
		
		//JButton cancel = new JButton ("Cancel");
		//cancel.addActionListener(new MyCancelListener());
		//buttonBox.add(cancel);

		//**************************************************************
	

		background.add(BorderLayout.EAST, buttonBox);                         // adding 'buttonBox' to 'background'JPannel 
		theFrame.getContentPane().add(background);                            // adding 'background' to 'theFrame' 
		
		JPanel explanationPanel = new JPanel();
		JLabel explanation = new JLabel(" Please, select the malts and adjunts you are using in your recipe");
		explanationPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10,10));
		explanationPanel.add(explanation);
		background.add(BorderLayout.NORTH, explanationPanel);
		
		GridLayout grid = new GridLayout (0, 4);                              // setting up 'grid' gridLayout
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel = new JPanel(grid);                                         // new JPannel'mainPanel' with a grid layout 
		background.add(BorderLayout.CENTER, mainPanel);                       // adding 'mainPanel' to 'background'
		
		vol = new JLabel("Total Volume (Gallons)");                                     // setting up 'vol' and 'volField'
		mainPanel.add(vol);
		volField = new  JTextField("0");
		mainPanel.add(volField);		
		
		for (String s: getListOfMalts()) {                                    //adding Label JCheck to 'background'
			mainPanel.add(new Label(s));
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}
		
		theFrame.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = theFrame.getSize().width;
		int h = theFrame.getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		theFrame.setLocation(x, y);
	//theFrame.setLocationRelativeTo(null);		//theFrame.setBounds(50, 50, 300, 300);      //final setup to theFrame
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
			float f = Float.parseFloat(volField.getText());
			r = new Recipe(f);
			r.myMalts = new ArrayList<Malt>();
			for (JCheckBox j: checkboxList){
				if (j.isSelected()) {
					int index = checkboxList.indexOf(j);
					String nameMalt = getListOfMalts().get(index);
					r.myMalts.add(new Malt(nameMalt));
					}
			}
			theFrame.dispose();
			s = new SecondFrame(r);
			s.buildGUI();
		}
	} //close inner class	
} // end class 