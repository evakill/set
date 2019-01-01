import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author killenberge
 *
 */
@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	private static int cardsLeft;
	private static int setsFound;
	private static int secondsElapsed;
	private static JLabel sets;
	private static JLabel cards;
	private static JLabel secs;
	
	
	public StatusPanel() {
		cardsLeft = GameBoard.getDeck().getCardsLeft();
		setsFound = 0;
		secondsElapsed = 0;
		cards = new JLabel("Cards Left: " + cardsLeft);
		sets = new JLabel("Sets Found: " + setsFound);
		secs = new JLabel((secondsElapsed / 60) + ":" + (secondsElapsed % 60));
		add(cards);
		add(secs);
		add(sets);
		setVisible(true);
	}
	
	public void updateStatus() {
		cardsLeft = GameBoard.getDeck().getCardsLeft();
		if(cardsLeft == 0 && !Game.board.setsLeft()) {
			Game.endGame();
		}
		cards.setText("Cards Left: " + cardsLeft);
		sets.setText("Sets Found: " + setsFound);
		secs.setText((secondsElapsed / 60) + ":" + (secondsElapsed % 60));
	}
	
	public void incrSecs() {
		secondsElapsed++;
	}
	
	public void incrSets() {
		setsFound++;
	}
	
	public int getSeconds() {
		return secondsElapsed;
	}
	

}
