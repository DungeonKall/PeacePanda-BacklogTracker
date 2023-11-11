import java.util.*;


public class Game {
	
	//fields
	private String gameName;
	private int year;
	private boolean gameFinished; 
	private List <Game> gameCatalog;
	private int nGames;
	
	//constructor
	public Game(String gameName, int year) {
		
		this.gameName = gameName;
		this.setYear(year);
		gameFinished = false;
		//add game to gameCatalog as soon as new game is created
	}
	
	
	
	//NAME
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	
	//YEAR
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	//GAME STATUS
	public boolean isGameFinished() {
		return gameFinished;
	}
	public void setGameFinished(boolean gameFinished) {
		this.gameFinished = gameFinished;
	}

	
	//GAME CATALOG
	public List<Game> getGamesOwned() {
		return gameCatalog;
	}
	public int getnGames() {
		return gameCatalog.size();
	}
	public void addCatalog(Game game) {
		gameCatalog.add(game);
	}
	public void removeFromFinished(Game game) {
		gameCatalog.remove(game);
	}
	//populate game catalog
}
