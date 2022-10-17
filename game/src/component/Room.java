package component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable{

	private static final long serialVersionUID = 4326577134430499298L;
	private List<Player> players = new ArrayList<>();
	private Player playerTurn;

	public Room(Player player) {
		players.add(player);
		playerTurn = player;
	}
	
	public Room(){}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Player getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(Player playerTurn) {
		this.playerTurn = playerTurn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
