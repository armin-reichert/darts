package de.amr.games.darts.game.ui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import net.miginfocom.swing.MigLayout;

public class NewGameDialog extends JDialog {

	private final ButtonGroup bg_numPlayers = new ButtonGroup();
	private final ButtonGroup bg_startPoints = new ButtonGroup();
	private JRadioButton rb_2_players;
	private JRadioButton rb_3_players;
	private JRadioButton rb_4_players;
	private JRadioButton rb_101;
	private JRadioButton rb_301;
	private JRadioButton rb_501;

	public Runnable onOK;

	public int getNumPlayers() {
		return rb_2_players.isSelected() ? 2 : rb_3_players.isSelected() ? 3 : 4;
	}

	public int getStartPoints() {
		return rb_101.isSelected() ? 101 : rb_301.isSelected() ? 301 : 501;
	}

	public NewGameDialog(JFrame owner) {
		super(owner);
		setTitle("New Game");
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][]"));

		JLabel lblAnzahlSpieler = new JLabel("Anzahl Spieler");
		getContentPane().add(lblAnzahlSpieler, "cell 0 0");

		JPanel panelNumPlayers = new JPanel();
		getContentPane().add(panelNumPlayers, "cell 1 0,aligny center");

		rb_2_players = new JRadioButton("2");
		bg_numPlayers.add(rb_2_players);
		panelNumPlayers.add(rb_2_players);

		rb_3_players = new JRadioButton("3");
		bg_numPlayers.add(rb_3_players);
		panelNumPlayers.add(rb_3_players);

		rb_4_players = new JRadioButton("4");
		bg_numPlayers.add(rb_4_players);
		panelNumPlayers.add(rb_4_players);

		JLabel lblStartpunkte = new JLabel("Startpunkte");
		getContentPane().add(lblStartpunkte, "cell 0 1,aligny center");

		JPanel panelStartPoints = new JPanel();
		getContentPane().add(panelStartPoints, "cell 1 1,aligny center");

		rb_101 = new JRadioButton("101");
		bg_startPoints.add(rb_101);
		panelStartPoints.add(rb_101);

		rb_301 = new JRadioButton("301");
		bg_startPoints.add(rb_301);
		panelStartPoints.add(rb_301);

		rb_501 = new JRadioButton("501");
		bg_startPoints.add(rb_501);
		panelStartPoints.add(rb_501);

		JSeparator separator = new JSeparator();
		getContentPane().add(separator, "cell 0 2 2 1");

		JPanel panelActions = new JPanel();
		getContentPane().add(panelActions, "cell 0 3 2 1,grow");

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(evt -> {
			if (onOK != null) {
				onOK.run();
			}
			setVisible(false);
		});
		panelActions.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(evt -> setVisible(false));
		panelActions.add(btnCancel);

		// init
		rb_4_players.setSelected(true);
		rb_501.setSelected(true);
	}

}
