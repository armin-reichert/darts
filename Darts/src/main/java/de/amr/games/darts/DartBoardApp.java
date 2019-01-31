package de.amr.games.darts;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import de.amr.games.darts.game.ui.DartBoardComponent;

public class DartBoardApp {

	public DartBoardApp(int boardSize) {
		JFrame frame = new JFrame("Dart Board");
		DartBoardComponent boardUI = new DartBoardComponent(boardSize);
		boardUI.addPropertyChangeListener(DartBoardComponent.PROPERTY_POINTS, evt -> {
			Integer points = (Integer) evt.getNewValue();
			JOptionPane.showMessageDialog(frame, points + " points");
		});
		frame.getContentPane().add(boardUI);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String... args) {
		EventQueue.invokeLater(() -> new DartBoardApp(900));
	}
}
