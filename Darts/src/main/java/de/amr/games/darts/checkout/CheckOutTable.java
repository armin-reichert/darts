package de.amr.games.darts.checkout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.amr.games.darts.checkout.field.DartBoardField;

public class CheckOutTable {

	private final static CheckOutTable INSTANCE = new CheckOutTable();

	private final Map<Integer, List<CheckOut>> checkOutsByScore = new HashMap<>();

	private void add(CheckOut co) {
		int score = co.getScore();
		if (!checkOutsByScore.containsKey(score)) {
			checkOutsByScore.put(score, new ArrayList<>());
		}
		checkOutsByScore.get(score).add(co);
	}

	private CheckOutTable() {

		final int[] fields = new int[20];
		final int[] fields25 = new int[21];
		for (int i = 1; i <= 20; i += 1) {
			fields[i - 1] = fields25[i - 1] = i;
		}
		fields25[20] = 25;

		// Double-Out
		for (int i : fields25) {
			add(new CheckOut(DartBoardField.Double(i)));
		}

		// Single-Double
		for (int i : fields25) {
			for (int j : fields25) {
				add(new CheckOut(DartBoardField.Single(i), DartBoardField.Double(j)));
			}
		}

		// Double-Double
		for (int i : fields25) {
			for (int j : fields25) {
				add(new CheckOut(DartBoardField.Double(i), DartBoardField.Double(j)));
			}
		}

		// Triple-Double
		for (int i : fields) {
			for (int j : fields25) {
				add(new CheckOut(DartBoardField.Triple(i), DartBoardField.Double(j)));
			}
		}

		// Single-Single-Double
		for (int i : fields25) {
			for (int j : fields25) {
				for (int k : fields25) {
					add(new CheckOut(DartBoardField.Single(i), DartBoardField.Single(j), DartBoardField.Double(k)));
				}
			}
		}

		// Single-Double-Double
		for (int i : fields25) {
			for (int j : fields25) {
				for (int k : fields25) {
					add(new CheckOut(DartBoardField.Single(i), DartBoardField.Double(j), DartBoardField.Double(k)));
				}
			}
		}

		// Single-Triple-Double
		for (int i : fields25) {
			for (int j : fields) {
				for (int k : fields25) {
					add(new CheckOut(DartBoardField.Single(i), DartBoardField.Triple(j), DartBoardField.Double(k)));
				}
			}
		}

		// Double-Single-Double
		for (int i : fields25) {
			for (int j : fields25) {
				for (int k : fields25) {
					add(new CheckOut(DartBoardField.Double(i), DartBoardField.Single(j), DartBoardField.Double(k)));
				}
			}
		}

		// Double-Double-Double
		for (int i : fields25) {
			for (int j : fields25) {
				for (int k : fields25) {
					CheckOut finish = new CheckOut(DartBoardField.Double(i), DartBoardField.Double(j), DartBoardField.Double(k));
					add(finish);
				}
			}
		}

		// Double-Triple-Double
		for (int i : fields25) {
			for (int j : fields) {
				for (int k : fields25) {
					add(new CheckOut(DartBoardField.Double(i), DartBoardField.Triple(j), DartBoardField.Double(k)));
				}
			}
		}

		// Triple-Single-Double
		for (int i : fields) {
			for (int j : fields25) {
				for (int k : fields25) {
					add(new CheckOut(DartBoardField.Triple(i), DartBoardField.Single(j), DartBoardField.Double(k)));
				}
			}
		}

		// Triple-Double-Double
		for (int i : fields) {
			for (int j : fields25) {
				for (int k : fields25) {
					add(new CheckOut(DartBoardField.Triple(i), DartBoardField.Double(j), DartBoardField.Double(k)));
				}
			}
		}

		// Triple-Triple-Double
		for (int i : fields) {
			for (int j : fields) {
				for (int k : fields25) {
					add(new CheckOut(DartBoardField.Triple(i), DartBoardField.Triple(j), DartBoardField.Double(k)));
				}
			}
		}
	}

	public static List<CheckOut> getCheckOuts(int score) {
		List<CheckOut> checkOuts = INSTANCE.checkOutsByScore.get(score);
		return checkOuts == null ? Collections.emptyList() : Collections.unmodifiableList(checkOuts);
	}
}
