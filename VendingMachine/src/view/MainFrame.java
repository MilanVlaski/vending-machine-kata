package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	public MainFrame() {
		setSize(400, 500);
		setLocationRelativeTo(null);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static class Items extends JPanel {
		public Items() {

		}
	}

	public static class Display extends JPanel {
		public Display() {

		}
	}

	public static class CoinHole extends JPanel {
		public CoinHole() {

		}
	}


	public static class CoinReturn extends JPanel {
		public CoinReturn() {

		}
	}


	public static class Dispenser extends JPanel {
		public Dispenser() {

		}
	}
}
