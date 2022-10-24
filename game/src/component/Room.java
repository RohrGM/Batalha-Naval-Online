package component;

import java.util.ArrayList;
import java.util.List;

import enums.Mode;

public class Room {
	private List<Player> players = new ArrayList<>();

	public List<Player> subscribe(Player player) {
		if (players.contains(player) == false) {
			players.add(player);
		}
		return players;
	}

	public List<Player> selectMode(Mode mode, long id) {
		if (isAvailableMode(mode)) {
			Player.getPlayer(players, id).setMode(mode);
		}
		return players;
	}

	public List<Player> getPlayers() {
		return players;
	}

	private boolean isAvailableMode(Mode mode) {
		for (Player player : this.players) {
			if (player.getMode() == mode) {
				return false;
			}
		}
		return true;
	}
}
