package de.amr.games.darts;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import de.amr.games.darts.game.ui.DartsCounterComponent;

public class DartsCounterApp {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(DartsCounterApp::new);
	}

	public DartsCounterApp() {
		DartsCounterComponent window = new DartsCounterComponent();
		window.newGame(4, 501);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}