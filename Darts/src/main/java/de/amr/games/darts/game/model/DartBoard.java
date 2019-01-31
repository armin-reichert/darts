package de.amr.games.darts.game.model;

import static de.amr.games.darts.game.model.DartBoard.Ring.BULLS_EYE;
import static de.amr.games.darts.game.model.DartBoard.Ring.DOUBLE;
import static de.amr.games.darts.game.model.DartBoard.Ring.OUT;
import static de.amr.games.darts.game.model.DartBoard.Ring.SIMPLE;
import static de.amr.games.darts.game.model.DartBoard.Ring.SINGLE_BULL;
import static de.amr.games.darts.game.model.DartBoard.Ring.TRIPLE;
import static java.lang.Math.atan2;
import static java.lang.Math.round;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;

import java.util.stream.Stream;

public class DartBoard {

	public static final float BOARD_REFERENCE_DIAMETER = 451f;

	/* List starts with segment "6" (0 degree position) in counter-clockwise direction. */
	private static int[] SEGMENTS = { 6, 13, 4, 18, 1, 20, 5, 12, 9, 14, 11, 8, 16, 7, 19, 3, 17, 2, 15, 10 };

	/**
	 * Ring dimensions are given for a board diameter of 900;
	 */
	public enum Ring {
		BULLS_EYE(0, 6.35f),
		SINGLE_BULL(6.35f, 15.9f),
		TRIPLE(97.4f, 107f),
		DOUBLE(160.4f, 170f),
		SIMPLE(0, 170f),
		OUT(170f, Integer.MAX_VALUE);

		public boolean contains(int radius, double scaling) {
			return (int) (scaling * inner) <= radius && radius <= (int) (scaling * outer);
		}

		private Ring(float inner, float outer) {
			this.inner = inner;
			this.outer = outer;
		}

		public final float inner;
		public final float outer;
	}

	public static int computeRadius(int x, int y) {
		return (int) sqrt(x * x + y * y);
	}

	public static int computeAngle(int x, int y) {
		int angle = (int) round(toDegrees(atan2(y, x)));
		return angle < 0 ? angle + 360 : angle;
	}

	public static int getSegment(int angle) {
		if (angle < 0 || angle > 360) {
			throw new IllegalArgumentException();
		}
		angle = (angle + 9) % 360;
		return SEGMENTS[angle / 18];
	}

	public static Ring getRing(int radius, double scaling) {
		return Stream.of(BULLS_EYE, SINGLE_BULL, TRIPLE, DOUBLE, SIMPLE).filter(ring -> ring.contains(radius, scaling))
				.findFirst().orElse(OUT);
	}

	public static int getPoints(Ring ring, int segment) {
		if (ring == null) {
			throw new IllegalArgumentException("Ring is NULL");
		}
		switch (ring) {
		case OUT:
			return 0;
		case SIMPLE:
			return segment;
		case DOUBLE:
			return 2 * segment;
		case TRIPLE:
			return 3 * segment;
		case SINGLE_BULL:
			return 25;
		case BULLS_EYE:
			return 50;
		default:
			throw new IllegalArgumentException("Illegal value for ring: " + ring);
		}
	}
}
