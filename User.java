import java.util.*;

public class User {
	
	//fields
	private String email;
	private String password;
	private List <Game> gamesOwned;
	private int nGamesOwned;
	private List <Game> gamesFinished;
	private int nGamesFinished;
	private List <Game> backLogGames;
	private int nBackLogs;
	
	//constructor
	public User(String email, String password) {
		
		this.email = email;
		this.password = password;
		gamesOwned = new ArrayList<>();
		nGamesOwned = gamesOwned.size();
		gamesFinished = new ArrayList<>();
		nGamesFinished = gamesFinished.size();
		backLogGames = new ArrayList<>();
		nBackLogs = backLogGames.size();
	}
	
	
	//methods
	
	
	//EMAIL
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//PASSWORD
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	//GAMES OWNED
	public List<Game> getGamesOwned() {
		return gamesOwned;
	}
	public int getnGamesOwned() {
		return nGamesOwned;
	}
	public void addToOwned(Game game) {
		gamesOwned.add(game);
	}
	public void removeFromOwned(Game game) {
		gamesOwned.remove(game);
	}
	
	
	//GAMES FINISHED
	public List<Game> getGamesFinished() {
		return gamesFinished;
	}
	public int getnGamesFinished() {
		return nGamesFinished;
	}
	public void addToFinished(Game game) {
		gamesFinished.add(game);
	}
	public void removeFromFinished(Game game) {
		gamesFinished.remove(game);
	}
	
	//BACKLOG GAMES
	public List<Game> getBackLogGames() {
		return backLogGames;
	}
	public int getnBackLogs() {
		return nBackLogs;
	}
	public void addToBackLogs(Game game) {
		backLogGames.add(game);
	}
	public void removeFromBackLogs(Game game) {
		backLogGames.remove(game);
	}
}
