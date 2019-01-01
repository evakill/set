/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	public static ControlPanel control;
	public static StatusPanel status;
	public static GameBoard board;
	public static JFrame frame = new JFrame("SET");

	public void run() {
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setLocation(0,0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		startGame();
	}


	public static void startGame() {
		JFrame instructions = new JFrame("Intructions");
		instructions.setPreferredSize(new Dimension(500, 500));
		instructions.setLocation(300, 300);
		instructions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel text = new JLabel("<html>This program is an implementation of the card game Set.<br> "
				+ "The object of the game is to identify a 'set' of three cards from 12 cards laid out "
				+ "on the table. Each card has a variation of the following four features: <br>" + 
				"Color: Each card is red, green, or purple. <br>" +
				"Symbol: Each card contains ovals, squiggles, or diamonds.<br>" + 
				"Number: Each card has one, two, or three symbols. <br>" + 
				"Shading: Each card is solid, open, or striped. <br>" + 
				"A 'Set' consists of three cards in which each feature is EITHER the same on each card "
				+ "OR is different on each card. That is to say, any feature in the 'Set' of three cards "
				+ "is either common to all three cards or is different on each card. The objective is to "
				+ "find as many sets as possible as quickly as possible. Collect a set by clicking on three "
				+ "cards in a set. There is no penalty if the selected cards are not a set. If you are stuck, "
				+ "you many shufle the cards in the board using the shuffle button. The game ends when there"
				+ "are no cards left in the deck and no sets left on the board.</html>");
		instructions.add(text, BorderLayout.NORTH);
		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instructions.dispose();
				board = new GameBoard();
				status = new StatusPanel();
				control = new ControlPanel();

				frame.add(control, BorderLayout.NORTH);
				frame.add(board, BorderLayout.CENTER);
				frame.add(status, BorderLayout.SOUTH);

			}
		});
		instructions.add(start, BorderLayout.SOUTH);
		instructions.pack();
		instructions.setVisible(true);
	}
	
	public static void endGame() {
		frame.dispose();
		GameBoard.timer.stop();
		int score = Game.status.getSeconds();
		String message = "<html> Game Over. Your Score is " + score / 60 + ":" + score % 60 + ". <br> <br>";
		JFrame end = new JFrame("GAME OVER");
		end.setPreferredSize(new Dimension(500, 500));
		end.setLocation(300, 300);
		end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int[] highscores = new int[3];

		try {
			Reader r = new FileReader("files/highscores.txt");
			BufferedReader br = new BufferedReader(r);
			try {
				highscores[0] = Integer.parseInt(br.readLine().trim());
				highscores[1] = Integer.parseInt(br.readLine().trim());
				highscores[2] = Integer.parseInt(br.readLine().trim());
				br.close();
			} catch (IOException e) {
				System.out.println("highscores incorrect format");
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("File not found");
		}

		for(int i = 0; i < 3; i++) {
			if(highscores[i] > score) {
				message += "NEW HIGHSCORE! <br> <br>";
				int temp = highscores[i];
				highscores[i] = score;
				for(int j = i + 1; j < 3; j++) {
					if(highscores[j] > temp) {
						int temp2 = highscores[j];
						highscores[j] = temp;
						temp = temp2;
					}
				}
				break;
			}
		}


		message += "Highcores: <br> 1. " + highscores[0] + "<br> 2. " + highscores[1] + " <br> 3. " + highscores[2] + "</html>";
		System.out.println(message);
		JLabel write = new JLabel(message);
		end.add(write);
		
		PrintWriter writer;
		try {
			writer = new PrintWriter("files/highscores.txt");
			for(int i = 0; i < 3; i++) {
				writer.write("" + highscores[i] + '\n');
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		end.pack();
		end.setVisible(true);

	}

	/**
	 * Main method run to start and run the game. Initializes the GUI elements specified in Game and
	 * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}