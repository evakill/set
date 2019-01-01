import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author evakill
 *
 */
@SuppressWarnings("serial")
public class Card extends JButton implements ActionListener, Comparable<Card>{
	private String name;
	private char num; 
	private char color;
	private char shade;
	private char shape;
	private boolean selected;
	private ImageIcon img;
	private static Set<String> selections = new TreeSet<String>();

	public Card(String filename) {
		img = new ImageIcon(filename);
		setIcon(img);
		setPreferredSize(new Dimension(45, 63));
		addActionListener(this);
		setMargin(new Insets(5, 5, 5, 5));
		setBorderPainted(selected);
		if(filename.length() == 14) {
			num = filename.charAt(6);
			color = filename.charAt(7);
			shade = filename.charAt(8);
			shape = filename.charAt(9);
			selected = false;
			name = filename;
		}
		if(!isValidCard()) {
			System.out.println("Invalid Card");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.selected) {
			selected = false;
			selections.remove(this.getName());
		} else if (selections.size() < 2) {
			selected = true;
			selections.add(this.getName());
		} else {
			selected = true;
			selections.add(this.getName());
			if(isSet(selections)) {
				Card[][] cards = GameBoard.getCards(); 
				for(int i = 0; i < 4; i++) {
					for(int j = 0; j < 3; j++) {
						if(selections.contains(cards[i][j].getName())) {
							cards[i][j] = null;
						} 
					}
				}
				Game.board.updateBoard();
				Game.status.incrSets();
				Game.status.updateStatus();
			}
			for(String cardName : selections) {
				Card card = GameBoard.getDeck().getCardMap().get(cardName);
				card.setSelected(false);
				card.setBorderPainted(false);
			}
			selections.clear();
		}
		setBorderPainted(selected);
	}

	public boolean isValidCard() {
		return ((num == '1' || num == '2' || num == '3') && 
				(color == 'g' || color == 'p' || color == 'r') && 
				(shade == 'f' || shade == 'l' || shade == 's') &&
				(shape == 'd' || shape == 'o' || shape == 's'));
	}

	public static boolean isSet(Set<String> selections) {
		Map<String, Card> cm = GameBoard.getDeck().getCardMap();
		Object[] arr = selections.toArray();
		Card c1 = cm.get(arr[0]);
		Card c2 = cm.get(arr[1]);
		Card c3 = cm.get(arr[2]);
		return( ((c1.num == c2.num && c2.num == c3.num) || 
				(c1.num != c2.num && c1.num != c3.num && c2.num != c3.num)) &&
				((c1.color == c2.color && c2.color == c3.color) ||
						(c1.color != c2.color && c1.color != c3.color && c2.color!= c3.color)) &&
				((c1.shade == c2.shade && c2.shade == c3.shade) ||
						(c1.shade != c2.shade && c1.shade != c3.shade && c2.shade != c3.shade)) &&
				((c1.shape == c2.shape && c2.shape == c3.shape) || 
						(c1.shape != c2.shape && c1.shape != c3.shape && c2.shape != c3.shape)) );
	}

	@Override
	public String getName() {
		return name;
	}

	public static Set<String> getSelections(){
		return selections;
	}

	@Override
	public void setSelected(boolean sel) {
		this.selected = sel;
	}

	@Override
	public boolean isSelected() {
		return this.selected;
	}

	@Override

	public int compareTo(Card o) {
		return 0;
	}
}
