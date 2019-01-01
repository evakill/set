import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.io.*;

/**
 * @author killenberge
 *
 */
public class Deck{
	private List<Card> deck;
	private Map<String, Card> cardMap = new TreeMap<String, Card>();

	public Deck(){
		try {
			Reader r = new FileReader("files/deck.txt");
			BufferedReader br = new BufferedReader(r);
			String line;
			try {
				line = br.readLine();
				deck = new LinkedList<Card>();
				while(line != null) {
					Card card = new Card("files/" + line);
					deck.add(card);
					cardMap.put(("files/" + line), card);
					line = br.readLine();
				}
				br.close();
			} catch (IOException e) {
				System.out.println("File empty");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public Card draw() {
		LinkedList<Card>linkedDeck = (LinkedList<Card>) deck;
		return linkedDeck.remove();
	}

	public void add(Card c) {
		deck.add(c);
		cardMap.put(c.getName(), c);
	}

	public int getCardsLeft() {
		return deck.size();
	}

	public Map<String, Card> getCardMap(){
		return cardMap;
	}
}