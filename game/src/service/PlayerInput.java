package service;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import Interface.IPlayerInput;

public class PlayerInput implements KeyListener, Serializable{
	private static final long serialVersionUID = -4521220385712019778L;
	private IPlayerInput player;
	private boolean onePress = true;

	public PlayerInput(IPlayerInput player) {
		this.player = player;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(this.onePress) {
			this.player.input(e);
			this.onePress = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.onePress = true;
	}
}
