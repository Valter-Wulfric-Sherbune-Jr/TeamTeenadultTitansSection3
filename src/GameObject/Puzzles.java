package GameObject;

import java.io.Serializable;

public class Puzzles implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7983065870395608398L;
	private String puzzleId;
	private String puzzleDesc;
	private String puzzleType;
	private String puzzleSolution;
	private String puzzleHint;
	private int puzzleDamage;

	//Create Default Object
	public Puzzles() {
		this.puzzleId = "P00";
		this.puzzleDesc = "Invalid Puzzle Description";
		this.puzzleSolution = "Invalid Puzzle";
		this.puzzleHint = "Invalid Puzzle Hint";
		this.puzzleDamage = 0;
	}

	//Create Object with set parameter
	public Puzzles(String puzzleId, String puzzleDesc, String puzzleType, String puzzleSolution, String puzzleHint,
			int puzzleDamage) {
		super();
		this.puzzleId = puzzleId;
		this.puzzleDesc = puzzleDesc;
		this.puzzleType = puzzleType;
		this.puzzleSolution = puzzleSolution;
		this.puzzleHint = puzzleHint;
		this.puzzleDamage = puzzleDamage;
	}

	
	//Get and set the puzzle id
	public String getPuzzleId() {
		return puzzleId;
	}
	public void setPuzzleId(String puzzleId) {
		this.puzzleId = puzzleId;
	}

	
	//Get and set the puzzle description
	public String getPuzzleDesc() {
		return puzzleDesc;
	}
	public void setPuzzleDesc(String puzzleDesc) {
		this.puzzleDesc = puzzleDesc;
	}

	
	//Get and set the puzzle type
	public String getPuzzleType() {
		return puzzleType;
	}
	public void setPuzzleType(String puzzleType) {
		this.puzzleType = puzzleType;
	}

	
	//Get and set puzzle solution
	public String getPuzzleSolution() {
		return puzzleSolution;
	}
	public void setPuzzleSolution(String puzzleSolution) {
		this.puzzleSolution = puzzleSolution;
	}

	
	//Get and set puzzle hint
	public String getPuzzleHint() {
		return puzzleHint;
	}
	public void setPuzzleHint(String puzzleHint) {
		this.puzzleHint = puzzleHint;
	}

	
	//Get and set puzzle damage
	public int getPuzzleDamage() {
		return puzzleDamage;
	}
	public void setPuzzleDamage(int puzzleDamage) {
		this.puzzleDamage = puzzleDamage;
	}

	

}
