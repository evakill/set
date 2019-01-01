import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {
	private static Card[][] cards;
	private static Deck deck;
	private static final int INTERVAL = 10;
	private static int counter;
	public static Timer timer;
	

	public GameBoard() {
		deck = new Deck();
		cards = new Card[4][3];
		setLayout(new GridLayout(3,4));
		deck.shuffle();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 3; j++) {
				cards[i][j] = deck.draw();
				add(cards[i][j]);
			}
		}

		timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();

		setVisible(true);
	}
	
	public void tick() {
		counter++;
		if(counter % 100 == 0) {
			Game.status.incrSecs();
			Game.status.updateStatus();
		}
	}

	public void updateBoard() {
		removeAll();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 3; j++) {
				if(cards[i][j] == null) {
					cards[i][j] = deck.draw();
				}
				add(cards[i][j]);
			}
		}
		Card.getSelections().clear();
	}

	public void shuffle() {
		this.removeAll();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 3; j++) {
				cards[i][j].setSelected(false);
				deck.add(cards[i][j]);
			}
		}
		deck.shuffle();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 3; j++) {
				cards[i][j] = deck.draw();
				add(cards[i][j]);
			}
		}
	}
	
	public boolean setsLeft() {
		ArrayList<String> cardNames = new ArrayList<String>();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 3; j++) {
				cardNames.add(cards[i][j].getName());
			}	
		}
		for(int i = 0; i < 12; i++) {
			Set<String> test = new TreeSet<String>();
			for(int j = i + 1; j < 12; j++) {
				for(int k = j + 1; k < 12; k++) {
					test.clear();
					test.add(cardNames.get(i));
					test.add(cardNames.get(j));
					test.add(cardNames.get(k));
					if(Card.isSet(test)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static Card[][] getCards() {
		return cards;
	}

	public static Deck getDeck() {
		return deck;
	}
}
