/**
 
   This is a simple JFrame Tic-Tac-Toe board game
   generator. Must have 2 players.  Enjoy! 

   Authors: djs2 and iakuba2

*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class board extends JComponent {
	
	static int n = 3;
	int turnCounter = n*n;
	boolean xTurn = false;
	boolean oTurn = false;
	boolean gameWon = false;
	boolean catsGame = false;
	JFrame board = new JFrame("Custom Tic-Tac-Toe Board");
	JButton[][] sections = new JButton[n][n];
	JButton start = new JButton("Start game");
	JButton newGame = new JButton("New Game");
	JOptionPane turn;
	
	private board() {
		super();
		if (n%2 == 0) {turnCounter += 1;}
		board.setSize(500, 700);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setVisible(true);
		board.setResizable(false);
		
	}
	
	private void turnSetter(int turnCounter) {
		if (turnCounter % 2 == 0) {
			xTurn = true;
			oTurn = false;
		} 
		if (turnCounter % 2 == 1) {
			xTurn = false;
			oTurn = true;
		}
	}
	
	private void gameStart() {
		JPanel main = new JPanel(new BorderLayout());
		JPanel menu = new JPanel(new BorderLayout());
		JPanel gameboard = new JPanel(new GridLayout(n,n));
		
		board.add(main);
		
		main.setPreferredSize(new Dimension(500, 700));
		gameboard.setPreferredSize(new Dimension(500, 500));
		menu.setPreferredSize(new Dimension(500, 100));
		
		main.add(gameboard, BorderLayout.NORTH);
		main.add(menu, BorderLayout.SOUTH);
		
		menu.add(start, BorderLayout.EAST);
		menu.add(newGame, BorderLayout.WEST);
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				xTurn = true;
				System.out.println("");
				System.out.println("Game started");
			}
		});	
		//Setting clear button
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						sections[i][j].setText("");
						xTurn = false;
						oTurn = false;
						gameWon = false;
						turnCounter = n*n;
						if (n%2 == 0) {turnCounter += 1;}
					}
				}
				System.out.println("Game Cleared");
			}
		});	
		//Setting each button
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sections[i][j] = new JButton();
				sections[i][j].setText("");
				sections[i][j].setVisible(true);
				gameboard.add(sections[i][j]);
				sections[i][j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent ae) {
						Object source = ae.getSource();
						if (source instanceof JButton) {
							if (((JButton) source).getText().equals("") && xTurn) {
								((JButton) source).setText("X");
								turnSetter(turnCounter);
								turnCounter--;
								checkWinner();
							} 
						if (((JButton) source).getText().equals("") && oTurn) {
								((JButton) source).setText("O");
								turnSetter(turnCounter);
								turnCounter--;
								checkWinner();
							}
						}
					}
				});
			}
		}
	}
	
	private void checkWinner() {
		for (int i = 1, count = 1; i < n; i++) {
			if (sections[i][i].getText().equals(sections[i-1][i-1].getText()) && ! sections[i][i].getText().equals("")) {
				count++;
				if (count == n) {
					xTurn = false;
					oTurn = false;
					System.out.println(sections[i][i].getText() + " has won");
				}
			} else {count = 0;}
		}
		for (int i = 0; i < n; i++) {
			int count = 1;
			for (int j = 1; j < n; j++) {
				if (sections[i][j].getText().equals(sections[i][j-1].getText()) && ! sections[i][j].getText().equals("")) {
					count++;

					if (count == n) {
						xTurn = false;
						oTurn = false;
						System.out.println(sections[i][j].getText() + " has won");
					}
				} else {
					count = 0;
				}
			}
		}
		
		for (int j = 0; j < n; j++) {
			int count = 1;
			for (int i = 1; i < n; i++) {
				if (sections[i][j].getText().equals(sections[i-1][j].getText()) && ! sections[i][j].getText().equals("")) {
					count++;

					if (count == n) {
						xTurn = false;
						oTurn = false;
						System.out.println(sections[i][j].getText() + " has won");
					}
				} else {
					count = 0;
				}
			}
		}
	}
		
	public static void main(String[] args) {
		
		
		String prompt = ("Choose a size board by typing the number of rows (3, 4, 5, 6)");
		Scanner lineScanner = new Scanner(System.in);
		
		while (true) {
			int userInput = 0;
			System.out.println(prompt);
			String nextLine = lineScanner.nextLine();
            Scanner inputScanner = new Scanner(nextLine);
			
            if (!inputScanner.hasNextInt()) {
            	System.out.println("Please follow the direction as stated.");
            	continue;
			}
            userInput = inputScanner.nextInt();
            if (inputScanner.hasNext()) {
            	System.out.println("Please follow the direction as stated");
            	continue;
            }
            if (userInput > 6 || userInput < 3) {
            	System.out.println("Please follow the direction as stated");
            	continue;
            }
            inputScanner.close();
            n = userInput;
            board Game = new board();
    		Game.gameStart();
    		break;
		}
		lineScanner.close();
	}
	
}
