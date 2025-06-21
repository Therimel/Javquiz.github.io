/**
 *  Thomas  Wong
 *	ID: 219824259
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;


public class Quiz implements ActionListener{
	
		
	
		//Set up Arrays to Hold Questions, Answers, and Options
		String[] questions = 	{
									"Which company created Java?",
									"Which year was Java created?",
									"What was Java originally called?",
									"Who is credited with creating Java?"
								};
		String[][] options = 	{
									{"Sun Microsystems","IBM","Oracle","Alphabet"},
									{"1989","1996","1972","2002"},
									{"Pear","Latte","Oak","Koffing"},
									{"Steve Jobs","Elon Musk","James Gosling","Goro Majima"}
								};
		char[] answers = 		{
									'A',
									'B',
									'C',
									'C'
								};
		//Variable used later in code
		char guess;
		char answer;
		int index =0;
		int correct_guesses =0;
		int total_questions = questions.length;
		int result;
		int lives=3;
		
		//Set up the elements for the Gui
		JFrame frame = new JFrame();
		JTextField textfield = new JTextField();
		JTextArea textarea = new JTextArea();
		JButton buttonA = new JButton();
		JButton buttonB = new JButton();
		JButton buttonC = new JButton();
		JButton buttonD = new JButton();
		JButton restart = new JButton();
		JLabel answer_labelA = new JLabel();
		JLabel answer_labelB = new JLabel();
		JLabel answer_labelC = new JLabel();
		JLabel answer_labelD = new JLabel();
		JLabel lives_label = new JLabel();
		JLabel lives_left = new JLabel();
		JTextField number_right = new JTextField();
		JTextField percentage = new JTextField();
		
		
		//Display amount of live user has
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lives_left.setText(String.valueOf(lives));
				//Restart program if lives reach 0
				if(lives<=0) {
					index=0;
					lives=3;
					nextQuestion();
				} 
				}
			});
		
		public Quiz()  {
			//set default close operation
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//set the size of the window
			frame.setSize(650,650);
			//changes background colour
			frame.getContentPane().setBackground(new Color(50,50,50));
			frame.setLayout(null);
			
			//Set the text field color, size, font, border, and center text
			textfield.setBounds(0,0,650,50);
			textfield.setBackground(new Color(25,25,25));
			textfield.setForeground(new Color(0,25,205));
			textfield.setFont(new Font("Ink Free",Font.BOLD,30));
			textfield.setBorder(BorderFactory.createBevelBorder(1));
			textfield.setHorizontalAlignment(JTextField.CENTER);
			textfield.setEditable(false);
			
			//Set the text area color, size, font, border, and center text
			textarea.setBounds(0,50,650,50);
			textarea.setLineWrap(true);
			textarea.setWrapStyleWord(true);
			textarea.setBackground(new Color(25,25,25));
			textarea.setForeground(new Color(0,25,205));
			textarea.setFont(new Font("MV Boli",Font.BOLD,25));
			textarea.setBorder(BorderFactory.createBevelBorder(1));
			textarea.setEditable(false);
			
			//Set button A,B,C,D Font, ,size, Text, and add Action listener
			buttonA.setBounds(0,100,100,100);
			buttonA.setFont(new Font("MV Boli",Font.BOLD,35));
			buttonA.addActionListener(this);
			buttonA.setText("A");
			
			buttonB.setBounds(0,200,100,100);
			buttonB.setFont(new Font("MV Boli",Font.BOLD,35));
			buttonB.addActionListener(this);
			buttonB.setText("B");
			
			buttonC.setBounds(0,300,100,100);
			buttonC.setFont(new Font("MV Boli",Font.BOLD,35));
			buttonC.addActionListener(this);
			buttonC.setText("C");
			
			buttonD.setBounds(0,400,100,100);
			buttonD.setFont(new Font("MV Boli",Font.BOLD,35));
			buttonD.addActionListener(this);
			buttonD.setText("D");
			//Set size, font, and action listener of restart button
			restart.setBounds(225,425,200,50);
			restart.setFont(new Font("MV Boli",Font.BOLD,35));
			restart.addActionListener(this);
			restart.setText("Restart?");
			
			//Set the size, colour, and font of Labels ABCD
			answer_labelA.setBounds(125,100,500,100);
			answer_labelA.setBackground(new Color(50,50,50));
			answer_labelA.setForeground(new Color(0,25,205));
			answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,35));
			
			answer_labelB.setBounds(125,200,500,100);
			answer_labelB.setBackground(new Color(50,50,50));
			answer_labelB.setForeground(new Color(0,25,205));
			answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,35));
			
			answer_labelC.setBounds(125,300,500,100);
			answer_labelC.setBackground(new Color(50,50,50));
			answer_labelC.setForeground(new Color(0,25,205));
			answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,35));
			
			answer_labelD.setBounds(125,400,500,100);
			answer_labelD.setBackground(new Color(50,50,50));
			answer_labelD.setForeground(new Color(0,25,205));
			answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,35));
			
			//Set the Lives label and counter size, colour,text, and position
			lives_left.setBounds(535,510,100,100);
			lives_left.setBackground(new Color(25,25,25));
			lives_left.setForeground(new Color(255,0,0));
			lives_left.setFont(new Font("Ink Free",Font.BOLD,60));
			lives_left.setBorder(BorderFactory.createBevelBorder(1));
			lives_left.setOpaque(true);
			lives_left.setHorizontalAlignment(JTextField.CENTER);
			lives_left.setText(String.valueOf(lives));
			
			lives_label.setBounds(535,475,100,25);
			lives_label.setBackground(new Color(50,50,50));
			lives_label.setForeground(new Color(255,0,0));
			lives_label.setFont(new Font("MV Boli",Font.PLAIN,16));
			lives_label.setHorizontalAlignment(JTextField.CENTER);
			lives_label.setText("Lives");
			
			//Set the final score and percentage colour, font, and position
			number_right.setBounds(225,225,200,100);
			number_right.setBackground(new Color(25,25,25));
			number_right.setForeground(new Color(25,255,0));
			number_right.setFont(new Font("Ink Free",Font.BOLD,50));
			number_right.setBorder(BorderFactory.createBevelBorder(1));
			number_right.setHorizontalAlignment(JTextField.CENTER);
			number_right.setEditable(false);
			
			percentage.setBounds(225,325,200,100);
			percentage.setBackground(new Color(25,25,25));
			percentage.setForeground(new Color(25,255,0));
			percentage.setFont(new Font("Ink Free",Font.BOLD,50));
			percentage.setBorder(BorderFactory.createBevelBorder(1));
			percentage.setHorizontalAlignment(JTextField.CENTER);
			percentage.setEditable(false);
			
			// Add all of the elements to the window
			frame.add(lives_label);
			frame.add(lives_left);
			frame.add(answer_labelA);
			frame.add(answer_labelB);
			frame.add(answer_labelC);
			frame.add(answer_labelD);
			frame.add(buttonA);
			frame.add(buttonB);
			frame.add(buttonC);
			frame.add(buttonD);
			frame.add(textarea);
			frame.add(textfield);
			frame.setVisible(true);
			//got to nextQuestion method
			nextQuestion();
		}
		
		 
		public void nextQuestion(){
			//go to result when all questions are answered 
			if(index>=total_questions) {
				results();
			}
			//display question
			else {
				textfield.setText("Question "+(index+1));
				textarea.setText(questions[index]);
				answer_labelA.setText(options[index][0]);
				answer_labelB.setText(options[index][1]);
				answer_labelC.setText(options[index][2]);
				answer_labelD.setText(options[index][3]);
				timer.start();
			}
		}
	
		
		public void actionPerformed(ActionEvent e) {
				//temporarily disable buttons
				buttonA.setEnabled(false);
				buttonB.setEnabled(false);
				buttonC.setEnabled(false);
				buttonD.setEnabled(false);
				
				//Perform actions if buttons are presses
				if(e.getSource()==buttonA) {
					answer= 'A';
					if(answer == answers[index]) {
						correct_guesses++;
					}
					else {
						lives--;
						index--;
					}
				}
				if(e.getSource()==buttonB) {
					answer= 'B';
					if(answer == answers[index]) {
						correct_guesses++;
					}
					else {
						lives--;
						index--;
					}
				}
				if(e.getSource()==buttonC) {
					answer= 'C';
					if(answer == answers[index]) {
						correct_guesses++;
					}
					else {
						lives--;
						index--;
					}
				}
				//restart the program
				if(e.getSource()==restart) {
					lives=3;
					correct_guesses=0;
					index=-1;
					//remove elements from window
					frame.remove(percentage);
					frame.remove(restart);
					frame.remove(number_right);
				}
				if(e.getSource()==buttonD) {
					answer= 'D';
					if(answer == answers[index]) {
						correct_guesses++;
					}
					else {
						lives--;
						index--;
					}
				}
				//go to transition method
				transition();
		}
		
		 
		public void transition() {
			
			timer.stop();
			
			buttonA.setEnabled(false);
			buttonB.setEnabled(false);
			buttonC.setEnabled(false);
			buttonD.setEnabled(false);
			
			Timer pause = new Timer(1500, new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					//remove answer value and re enable buttons
					answer = ' ';
					lives_left.setText(String.valueOf(lives));
					buttonA.setEnabled(true);
					buttonB.setEnabled(true);
					buttonC.setEnabled(true);
					buttonD.setEnabled(true);
					//increase index value by 1
					index++;
					nextQuestion();
				}
			});
			pause.setRepeats(false);
			pause.start();
		}
		//display results of quiz
		public void results(){
			//disable function of buttons ABCD
			buttonA.setEnabled(false);
			buttonB.setEnabled(false);
			buttonC.setEnabled(false);
			buttonD.setEnabled(false);
			//calculate result
			result = (int)((correct_guesses/(double)total_questions)*100);
			//Change textfield Text and remove textarea and answer labels
			textfield.setText("RESULTS!");
			textarea.setText("");
			answer_labelA.setText("");
			answer_labelB.setText("");
			answer_labelC.setText("");
			answer_labelD.setText("");
			//display result in percent and fraction
			number_right.setText("("+correct_guesses+"/"+total_questions+")");
			percentage.setText(result+"%");
			//add elements to the window
			frame.add(number_right);
			frame.add(percentage);
			frame.add(restart);
					
				
		}
	}

