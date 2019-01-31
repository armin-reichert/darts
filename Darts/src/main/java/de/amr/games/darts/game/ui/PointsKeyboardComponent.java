package de.amr.games.darts.game.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import net.miginfocom.swing.MigLayout;

public class PointsKeyboardComponent extends JComponent {

	public static final String PROPERTY_POINTS = "points";

	private final ButtonGroup rbgFactor = new ButtonGroup();
	private JRadioButton rbSingle;
	private JRadioButton rbDouble;
	private JRadioButton rbTriple;
	private JButton button_25;

	public PointsKeyboardComponent() {
		setOpaque(false);
		setPreferredSize(new Dimension(612, 126));
		setLayout(new MigLayout("", "[grow]", "[][grow]"));

		JPanel panelFactor = new JPanel();
		panelFactor.setOpaque(false);
		add(panelFactor, "cell 0 0,alignx center");
		panelFactor.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));

		rbSingle = new JRadioButton("1x");
		rbSingle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				button_25.setEnabled(true);
			}
		});
		rbSingle.setFont(new Font("Tahoma", Font.BOLD, 18));
		rbSingle.setSelected(true);
		rbgFactor.add(rbSingle);
		panelFactor.add(rbSingle);

		rbDouble = new JRadioButton("2x");
		rbDouble.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				button_25.setEnabled(true);
			}
		});
		rbDouble.setFont(new Font("Tahoma", Font.BOLD, 18));
		rbgFactor.add(rbDouble);
		panelFactor.add(rbDouble);

		rbTriple = new JRadioButton("3x");
		rbTriple.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				button_25.setEnabled(false);
			}
		});
		rbTriple.setFont(new Font("Tahoma", Font.BOLD, 18));
		rbgFactor.add(rbTriple);
		panelFactor.add(rbTriple);

		JPanel panelButtons = new JPanel();
		panelButtons.setOpaque(false);
		add(panelButtons, "cell 0 1");
		panelButtons.setLayout(new MigLayout("", "[][][][][][][][][][][]", "[][]"));

		JButton button_1 = new JButton("1");
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(1);
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_1, "cell 0 0,growx");

		JButton button_2 = new JButton("2");
		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(2);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_2, "cell 1 0,growx");

		JButton button_3 = new JButton("3");
		button_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(3);
			}
		});
		button_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_3, "cell 2 0,growx");

		JButton button_4 = new JButton("4");
		button_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(4);
			}
		});
		button_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_4, "cell 3 0,growx");

		JButton button_5 = new JButton("5");
		button_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(5);
			}
		});
		button_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_5, "cell 4 0,growx");

		JButton button_6 = new JButton("6");
		button_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(6);
			}
		});
		button_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_6, "cell 5 0,growx");

		JButton button_7 = new JButton("7");
		button_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(7);
			}
		});
		button_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_7, "cell 6 0,growx");

		JButton button_8 = new JButton("8");
		button_8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(8);
			}
		});
		button_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_8, "cell 7 0,growx");

		JButton button_9 = new JButton("9");
		button_9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(9);
			}
		});
		button_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_9, "cell 8 0,growx");

		JButton button_10 = new JButton("10");
		button_10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(10);
			}
		});
		button_10.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_10, "flowx,cell 9 0,growx");

		JButton button_11 = new JButton("11");
		button_11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(11);
			}
		});

		JButton button_Out = new JButton("Out");
		button_Out.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(0);
			}
		});
		button_Out.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_Out, "cell 10 0,growx");
		button_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_11, "cell 0 1,growx");

		JButton button_12 = new JButton("12");
		button_12.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(12);
			}
		});
		button_12.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_12, "cell 1 1,growx");

		JButton button_13 = new JButton("13");
		button_13.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(13);
			}
		});
		button_13.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_13, "cell 2 1,growx");

		JButton button_14 = new JButton("14");
		button_14.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(14);
			}
		});
		button_14.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_14, "cell 3 1,growx");

		JButton button_15 = new JButton("15");
		button_15.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(15);
			}
		});
		button_15.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_15, "cell 4 1,growx");

		JButton button_16 = new JButton("16");
		button_16.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(16);
			}
		});
		button_16.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_16, "cell 5 1,growx");

		JButton button_17 = new JButton("17");
		button_17.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(17);
			}
		});
		button_17.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_17, "cell 6 1,growx");

		JButton button_18 = new JButton("18");
		button_18.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(18);
			}
		});
		button_18.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_18, "cell 7 1,growx");

		JButton button_19 = new JButton("19");
		button_19.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(19);
			}
		});
		button_19.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_19, "cell 8 1,growx");

		JButton button_20 = new JButton("20");
		button_20.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(20);
			}
		});
		button_20.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_20, "flowx,cell 9 1,growx");

		button_25 = new JButton("25");
		button_25.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				numberButtonPressed(25);
			}
		});
		button_25.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelButtons.add(button_25, "cell 10 1,growx");
	}

	private void numberButtonPressed(int number) {
		int factor = rbTriple.isSelected() ? 3 : rbDouble.isSelected() ? 2 : 1;
		int value = factor * number;
		rbSingle.setSelected(true);
		firePropertyChange(PROPERTY_POINTS, null, value);
	}

	public void reset() {
		rbSingle.setSelected(true);
		button_25.setEnabled(true);
	}

}
