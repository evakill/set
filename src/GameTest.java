import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class GameTest {

	@Test
	public void isSetTestAllSame() {
		GameBoard gm = new GameBoard();
		Set<String> set = new TreeSet<String>();
		set.add("files/1pss.png");
		set.add("files/2pss.png");
		set.add("files/3pss.png");
		assertTrue(Card.isSet(set));
	}
	
	@Test
	public void isSetTestAllDifferent() {
		GameBoard gm = new GameBoard();
		Set<String> set = new TreeSet<String>();
		set.add("files/1psd.png");
		set.add("files/2rfs.png");
		set.add("files/3glo.png");
		assertTrue(Card.isSet(set));
	}
	
	@Test
	public void isSetTesSameAndDifferent() {
		GameBoard gm = new GameBoard();
		Set<String> set = new TreeSet<String>();
		set.add("files/1pss.png");
		set.add("files/2rfs.png");
		set.add("files/3gls.png");
		assertTrue(Card.isSet(set));
	}
	
	@Test
	public void isValidCardTestTrue() {
		Card c1 = new Card("files/1pss.png");
		Card c2 = new Card("files/2rfo.png");
		Card c3 = new Card("files/3gld.png");
		assertTrue(c1.isValidCard());
		assertTrue(c2.isValidCard());
		assertTrue(c3.isValidCard());
	}
	
	@Test
	public void isValidCardTestFalse() {
		Card c1 = new Card("files/1ps.png");
		Card c2 = new Card("files/prs1.png");
		Card c3 = new Card("");
		Card c4 = new Card("1pss.png");
		assertFalse(c1.isValidCard());
		assertFalse(c2.isValidCard());
		assertFalse(c3.isValidCard());
		assertFalse(c4.isValidCard());
	}

}
