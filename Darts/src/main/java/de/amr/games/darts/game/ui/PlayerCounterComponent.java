package de.amr.games.darts.game.ui;

import static java.lang.String.format;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import de.amr.games.darts.checkout.CheckOutTable;
import de.amr.games.darts.game.model.Player;
import net.miginfocom.swing.MigLayout;

public class PlayerCounterComponent extends JComponent {

	private Player player;

	private CheckOutsTableModel tblModelCheckOuts;
	private JTable tblCheckOuts;
	private JLabel lblName;
	private JLabel lblPointsRemaining;
	private JLabel lblPointsInTake;
	private JLabel lblPointsAverage;
	private Component verticalStrut;
	private Font nameFont;

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Font getNameFont() {
		return nameFont;
	}

	public void setNameFont(Font nameFont) {
		this.nameFont = nameFont;
		lblName.setFont(nameFont);
	}

	public void updateView() {
		if (player == null)
			return;
		lblName.setText(player.getName());
		lblName.setFont(nameFont);
		lblPointsRemaining.setText(format("%d", player.getPointsRemaining()));
		lblPointsRemaining.setForeground(player.isTurn() ? Color.RED : Color.BLACK);
		lblPointsInTake.setText(format("%d", player.getPointsInTake()));
		lblPointsInTake.setForeground(player.isTurn() ? Color.RED : Color.GRAY);
		lblPointsAverage.setText(format("%.2f", player.getPointsAverage()));
		tblModelCheckOuts.setResults(CheckOutTable.getCheckOuts(player.getPointsRemaining()));
	}

	public PlayerCounterComponent() {
		setOpaque(false);
		tblModelCheckOuts = new CheckOutsTableModel();

		setLayout(new MigLayout("", "[grow]", "[][][][][][]"));

		lblName = new JLabel();
		lblName.setOpaque(true);
		lblName.setBackground(Color.WHITE);
		lblName.setForeground(new Color(0, 0, 255));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblName.setText("Player Name");
		add(lblName, "cell 0 0,growx");
		lblName.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					editName();
				}
			}
		});

		verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, "cell 0 1");

		lblPointsRemaining = new JLabel("501");
		lblPointsRemaining.setForeground(Color.RED);
		lblPointsRemaining.setToolTipText("Points remaining");
		lblPointsRemaining.setFont(new Font("Tahoma", Font.BOLD, 36));
		add(lblPointsRemaining, "cell 0 2,alignx center");

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3,grow");

		tblCheckOuts = new JTable();
		tblCheckOuts.setFont(new Font("Courier New", Font.PLAIN, 14));
		tblCheckOuts.setModel(tblModelCheckOuts);
		scrollPane.setViewportView(tblCheckOuts);
		tblCheckOuts.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

		lblPointsInTake = new JLabel("42");
		lblPointsInTake.setToolTipText("Points in current take");
		lblPointsInTake.setForeground(Color.RED);
		lblPointsInTake.setFont(new Font("Tahoma", Font.BOLD, 36));
		add(lblPointsInTake, "cell 0 4,alignx center");

		lblPointsAverage = new JLabel("59.25");
		lblPointsAverage.setToolTipText("Points Average");
		lblPointsAverage.setForeground(Color.GRAY);
		lblPointsAverage.setFont(new Font("Tahoma", Font.BOLD, 24));
		add(lblPointsAverage, "cell 0 5,alignx center");
	}

	protected void editName() {
		String newName = JOptionPane.showInputDialog(this, "Spieler benennen", player.getName());
		if (newName != null && newName.trim().length() > 0) {
			player.setName(newName);
			updateView();
		}
	}
}
