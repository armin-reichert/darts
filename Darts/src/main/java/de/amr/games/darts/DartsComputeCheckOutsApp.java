package de.amr.games.darts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.amr.games.darts.checkout.CheckOut;
import de.amr.games.darts.checkout.CheckOutTable;

public class DartsComputeCheckOutsApp {

	private static final int[][] INTERVALS = {
		/*@formatter:off*/
		{ 170, 150 }, 
		{ 149, 129 }, 
		{ 128, 108 }, 
		{ 107, 87 },
		{ 86, 66 }, 
		{ 65, 45 }, 
		{ 44, 24 }, 
		{ 23, 2 }
		/*@formatter:on*/
	};

	public static void main(String[] args) {
		DartsComputeCheckOutsApp app = new DartsComputeCheckOutsApp();
		for (int[] interval : INTERVALS) {
			int upper = interval[0], lower = interval[1];
			app.writeFile("html/" + getFilename(upper, lower), app.createHTML(upper, lower));
		}
		app.writeIndex("html/checkouts-index.htm");
	}

	private static int[] findInterval(int score) {
		for (int[] interval : INTERVALS) {
			int upper = interval[0], lower = interval[1];
			if (upper >= score && score >= lower) {
				return interval;
			}
		}
		return null;
	}

	private static String getFilename(int upper, int lower) {
		return "checkouts-" + upper + "-" + lower + ".htm";
	}

	public DartsComputeCheckOutsApp() {
	}

	private void writeIndex(String path) {
		StringBuilder sb = new StringBuilder();

		sb.append("<!doctype html>\n");
		sb.append("<html>\n");
		sb.append("<head>\n");
		sb.append("<style>\n");
		sb.append(" .scorelink { text-align:right; font-family: Arial; font-size: 20pt; color: lightgray; });"
				+ " .scorelink a { text-decoration: none; }" + " .scorelink a:visited { color: blue; }" + "\n");
		sb.append("</style>\n");
		sb.append("</head>\n");
		sb.append("<body>\n");
		sb.append("<h1>Darts Checkouts</h1>\n");
		sb.append("<table border=0 cellspacing=10>\n");

		// 2-dimensional table with scores
		int score = 170;
		int column = 1;
		sb.append("<tr>");
		while (score >= 2) {
			String url = "";
			int[] interval = findInterval(score);
			if (interval == null) {
				throw new IllegalStateException();
			}
			int upper = interval[0], lower = interval[1];
			boolean hasFinish = !CheckOutTable.getCheckOuts(score).isEmpty();
			url = getFilename(upper, lower) + "#" + +score;
			sb.append("<td class='scorelink'>");
			if (hasFinish) {
				sb.append("<a href='").append(url).append("'>");
			}
			sb.append(score);
			if (hasFinish) {
				sb.append("</a>");
			}
			sb.append("</td>\n");
			column += 1;
			if (column == 14) {
				sb.append("</tr>");
				sb.append("<tr>");
				column = 1;
			}
			score -= 1;
		}

		sb.append("</table>\n");
		sb.append("</body>\n");
		sb.append("</html>\n");

		writeFile(path, sb.toString());
	}

	private String createHTML(int upper, int lower) {
		StringBuilder sb = new StringBuilder();

		sb.append("<!doctype html>\n");
		sb.append("<html>\n");
		sb.append("<head>\n");
		sb.append("</head>\n");
		sb.append("<body>\n");
		sb.append("<table border=1 cellpadding=3>\n");

		for (int score = upper; score >= lower; score -= 1) {
			sb.append("<tr>\n");
			sb.append("<td valign='top'>").append("<a id='" + score + "'>\n").append(score).append("</a></td>\n");
			sb.append("<td>\n");
			for (CheckOut finish : CheckOutTable.getCheckOuts(score)) {
				sb.append(finish).append("&nbsp;&nbsp;\n");
			}
			sb.append("</td>\n");
			sb.append("</tr>\n");
		}

		sb.append("</table>\n");
		sb.append("</body>\n");
		sb.append("</html>\n");

		return sb.toString();
	}

	private void writeFile(String pathString, String text) {
		try {
			Path path = Files.write(Paths.get(pathString), text.getBytes());
			System.out.println("Created file: " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
