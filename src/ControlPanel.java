import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.*;

/**
 * 
 */

/**
 * @author killenberge
 *
 */
@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
	private static JButton shuffle;

	public ControlPanel() {
		setPreferredSize(new Dimension(1000, 50));
		shuffle = new JButton("Shuffle");
		
		
		shuffle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.board.shuffle();
				Game.board.updateBoard();
			}
		});
		
		add(shuffle);
	}

}
