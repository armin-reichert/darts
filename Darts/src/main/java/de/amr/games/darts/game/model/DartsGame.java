package de.amr.games.darts.game.model;

public class DartsGame {

	private final int startPoints;
	private final Player[] players;
	private int turn;
	private int dartsThrownInTake;

	public DartsGame(int numPlayers, int startPoints) {
		this.startPoints = startPoints;
		players = new Player[numPlayers];
		for (int i = 0; i < numPlayers; i += 1) {
			players[i] = new Player(this);
			players[i].setName("Spieler " + (i + 1));
			players[i].setPointsScored(0);
			players[i].setPointsInTake(0);
			players[i].setPointsAverage(0);
			players[i].setLegsCompleted(0);
		}
		turn = 0;
		dartsThrownInTake = 0;
	}

	public int getNumPlayers() {
		return players.length;
	}

	public int getStartPoints() {
		return startPoints;
	}

	public void nextPlayer() {
		turn = (turn + 1) % players.length;
	}

	public Player getCurrentPlayer() {
		return players[turn];
	}

	public Player getPlayer(int i) {
		return i < players.length ? players[i] : null;
	}

	public int getDartsThrownInTake() {
		return dartsThrownInTake;
	}

	public void setDartsThrownInTake(int dartsThrownInTake) {
		this.dartsThrownInTake = dartsThrownInTake;
	}
}