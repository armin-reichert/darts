package de.amr.games.darts.game.model;

public class Player {

	private final DartsGame game;

	private String name;
	private int pointsScored;
	private int pointsInTake;
	private int legsCompleted;
	private float pointsAverage;
	private int dartsThrown;

	public Player(DartsGame game) {
		this.game = game;
	}

	public DartsGame getGame() {
		return game;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPointsScored() {
		return pointsScored;
	}

	public void setPointsScored(int points) {
		this.pointsScored = points;
	}

	public void addToScore(int points) {
		pointsScored += points;
	}

	public int getPointsInTake() {
		return pointsInTake;
	}

	public void setPointsInTake(int pointsInTake) {
		this.pointsInTake = pointsInTake;
	}

	public void addToScoreInTake(int points) {
		this.pointsInTake += points;
	}

	public int getPointsRemaining() {
		return game.getStartPoints() - pointsScored;
	}

	public int getLegsCompleted() {
		return legsCompleted;
	}

	public void setLegsCompleted(int legsCompleted) {
		this.legsCompleted = legsCompleted;
	}

	public boolean isTurn() {
		return this == game.getCurrentPlayer();
	}

	public float getPointsAverage() {
		return pointsAverage;
	}

	public void setPointsAverage(float pointsAverage) {
		this.pointsAverage = pointsAverage;
	}

	public int getDartsThrown() {
		return dartsThrown;
	}

	public void setDartsThrown(int dartsThrown) {
		this.dartsThrown = dartsThrown;
	}
}