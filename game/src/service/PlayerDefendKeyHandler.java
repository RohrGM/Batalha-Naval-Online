package service;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import entity.PlayerDefend;

public class PlayerDefendKeyHandler implements KeyListener {
	
	private PlayerDefend player;

	public PlayerDefendKeyHandler(PlayerDefend player) {
		this.player = player;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.player.input(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
