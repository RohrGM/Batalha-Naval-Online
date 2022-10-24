package component;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import enums.Mode;

public class Player implements Serializable {

	private static final long serialVersionUID = 1613491619606389031L;
	private long id;
	private Mode mode = Mode.UNDEFINED;

	public Player() {
		this.id = ThreadLocalRandom.current().nextInt(0, 1000000 + 1);
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public long getId() {
		return id;
	}

	public static Player getPlayer(List<Player> players, long id) {
		for (Player player : players) {
			if (player.getId() == id) {
				return player;
			}
		}
		return null;
	}

	public static boolean isReady(List<Player> players) {
		for (Player player : players) {
			if (player.getMode() == Mode.UNDEFINED) {
				return false;
			}
		}
		return true;
		//return players.size() > 1 ? true : false;
	}
}
