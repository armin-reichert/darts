package de.amr.games.darts.checkout;

import de.amr.games.darts.checkout.field.DartBoardField;

public class CheckOut {

	private final DartBoardField fields[];

	public CheckOut(DartBoardField... fields) {
		this.fields = fields;
	}

	public int getScore() {
		int sum = 0;
		for (DartBoardField field : fields) {
			sum += field.getValue();
		}
		return sum;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fields.length; ++i) {
			DartBoardField field = fields[i];
			sb.append(field);
			if (i < fields.length - 1) {
				sb.append("->");
			}
		}
		return sb.toString();
	}
}
