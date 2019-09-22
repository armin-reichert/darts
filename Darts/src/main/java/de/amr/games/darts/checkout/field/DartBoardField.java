package de.amr.games.darts.checkout.field;

/**
 * Dart board field.
 */
public class DartBoardField {

	public static DartBoardField Single(int number) {
		return new DartBoardField(number, DartBoardFieldType.SINGLE);
	}

	public static DartBoardField Double(int number) {
		return new DartBoardField(number, DartBoardFieldType.DOUBLE);
	}

	public static DartBoardField Triple(int number) {
		return new DartBoardField(number, DartBoardFieldType.TRIPLE);
	}

	private final int number;
	private final DartBoardFieldType type;

	private DartBoardField(int number, DartBoardFieldType type) {
		if (number >= 1 && number <= 20 || number == 25 && type != DartBoardFieldType.TRIPLE) {
			this.number = number;
			this.type = type;
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	public int getNumber() {
		return number;
	}

	public DartBoardFieldType getType() {
		return type;
	}

	public int getValue() {
		switch (type) {
		case SINGLE:
			return number;
		case DOUBLE:
			return 2 * number;
		case TRIPLE:
			return 3 * number;
		default:
			throw new IllegalStateException();
		}
	}

	@Override
	public String toString() {
		switch (type) {
		case SINGLE:
			return number == 25 ? "SBull" : "" + number;
		case DOUBLE:
			return number == 25 ? "Bull" : "D" + number;
		case TRIPLE:
			return "T" + number;
		default:
			return super.toString();
		}
	}

}