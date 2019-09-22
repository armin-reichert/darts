package de.amr.games.darts.game.ui;

import static de.amr.games.darts.game.model.DartBoard.Ring.BULLS_EYE;
import static de.amr.games.darts.game.model.DartBoard.Ring.DOUBLE;
import static de.amr.games.darts.game.model.DartBoard.Ring.SINGLE_BULL;
import static de.amr.games.darts.game.model.DartBoard.Ring.TRIPLE;
import static java.lang.Math.toRadians;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import de.amr.games.darts.game.model.DartBoard;

public class DartBoardComponent extends JComponent {

	public static final String PROPERTY_POINTS = "points";

	private static Image boardImage;
	private static Image dartImage;

	static {
		InputStream dartImageSource = DartBoardComponent.class.getResourceAsStream("/dart.png");
		if (dartImageSource == null) {
			throw new RuntimeException("Dart image not found");
		}
		try {
			dartImage = ImageIO.read(dartImageSource);
		} catch (IOException e) {
			throw new RuntimeException("Dart image could not be loaded");
		}
		InputStream boardImageSource = DartBoardComponent.class.getResourceAsStream("/dartboard.png");
		if (boardImageSource == null) {
			throw new RuntimeException("Board image not found");
		}
		try {
			boardImage = ImageIO.read(boardImageSource);
		} catch (IOException e) {
			throw new RuntimeException("Board image could not be loaded");
		}
	}

	private Point center;
	private double scaling;
	private int diameter;
	private Point currentTarget;
	private DartBoard.Ring currentRing;
	private int currentSegment;
	private Font textFont;
	private boolean drawSegments;

	public DartBoardComponent() {
		this(600);
	}

	public DartBoardComponent(int diameter) {
		setDiameter(diameter);
		registerEventHandlers();
	}

	private void registerEventHandlers() {
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				onMouseClicked(e.getX(), e.getY());
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseMoved(MouseEvent e) {
				onMouseMoved(e.getX(), e.getY());
			}
		});
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				setDiameter(Math.min(getWidth(), getHeight()));
			}
		});
		getInputMap().put(KeyStroke.getKeyStroke('s'), "showSegments");
		getActionMap().put("showSegments", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawSegments = !drawSegments;
				repaint();
			}
		});
	}

	private static String getValueAsText(DartBoard.Ring ring, int segment) {
		switch (ring) {
		case OUT:
			return "Out";
		case SIMPLE:
			return "" + segment;
		case DOUBLE:
			return "Double " + segment;
		case TRIPLE:
			return "Triple " + segment;
		case SINGLE_BULL:
			return "Single Bull";
		case BULLS_EYE:
			return "Bulls-Eye";
		default:
			return "";
		}
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
		scaling = (double) diameter / DartBoard.BOARD_REFERENCE_DIAMETER;
		center = new Point(diameter / 2 - 2, diameter / 2 + 4);
		textFont = new Font("Arial", Font.BOLD, diameter / 20);
		setPreferredSize(new Dimension(diameter, diameter));
		repaint();
	}

	public int getDiameter() {
		return diameter;
	}

	protected void onMouseMoved(int viewX, int viewY) {
		setCurrentTarget(viewX, viewY);
		repaint();
	}

	protected void onMouseClicked(int viewX, int viewY) {
		setCurrentTarget(viewX, viewY);
		repaint();
		int points = DartBoard.getPoints(currentRing, currentSegment);
		firePropertyChange(PROPERTY_POINTS, -1, points);
	}

	private void setCurrentTarget(int viewX, int viewY) {
		currentTarget = new Point(viewX, viewY);
		// Convert view to model coordinate
		int x = viewX - center.x;
		int y = center.y - viewY;
		// Compute polar coordinate
		int radius = DartBoard.computeRadius(x, y);
		int angle = DartBoard.computeAngle(x, y);
		// Compute segment and ring
		currentSegment = DartBoard.getSegment(angle);
		currentRing = DartBoard.getRing(radius, scaling);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		draw(g2);
		g2.dispose();
	}

	private void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(boardImage, 0, 0, diameter, diameter, null);
		if (drawSegments) {
			g.setColor(Color.YELLOW);
			drawRing(g, BULLS_EYE);
			drawRing(g, SINGLE_BULL);
			drawRing(g, TRIPLE);
			drawRing(g, DOUBLE);
			for (int angle = 9; angle < 360; angle += 18) {
				double rad = toRadians(angle);
				g.translate(center.x, center.y);
				g.rotate(rad);
				g.drawLine((int) (scaling * SINGLE_BULL.outer), 0, (int) (scaling * DOUBLE.outer), 0);
				g.rotate(-rad);
				g.translate(-center.x, -center.y);
			}
		}
		// draw dart at target position
		if (currentTarget != null && currentTarget.distance(center) <= diameter / 2) {
			g.setColor(currentRing == DartBoard.Ring.OUT ? Color.GRAY : Color.YELLOW);
			int targetWidth = diameter / 5;
			int targetHeight = diameter / 5;
			g.fillOval(currentTarget.x - 5, currentTarget.y - 5, 10, 10);
			g.drawImage(dartImage, currentTarget.x, currentTarget.y - targetHeight, targetWidth,
					targetHeight, null);
		}
		// draw current value as text
		if (currentRing != null) {
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.setColor(Color.GRAY);
			g.setFont(textFont);
			String text = getValueAsText(currentRing, currentSegment);
			int width = g.getFontMetrics().stringWidth(text);
			g.drawString(text, getWidth() - width - 10, getHeight() - g.getFontMetrics().getDescent());
		}
	}

	private void drawRing(Graphics2D g, DartBoard.Ring ring) {
		int radius = (int) (scaling * ring.inner);
		if (radius > 0) {
			g.drawOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
		}
		radius = (int) (scaling * ring.outer);
		if (radius <= getWidth()) {
			g.drawOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
		}
	}
}