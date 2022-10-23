package panel;

import java.awt.Dimension;
import java.rmi.RemoteException;

import javax.swing.JPanel;

import Interface.ICommunication;
import component.Player;
import service.Server;
import util.SizePattern;

public class PanelController extends JPanel {

	private ICommunication communication;
	private Server server;
	private Player player = new Player();

	public PanelController() {
		try {
			this.server = new Server();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(SizePattern.screenWidth, SizePattern.screenHeight));
		this.setFocusable(true);
		this.changePanel("Menu");
	}

	public void changePanel(String panelName) {
		this.removeAll();

		switch (panelName) {
		case "Menu":
			this.add(new Menu(this));
			break;
		case "Match":
			this.add(new Match(this));
			break;
		case "Lobby":
			this.add(new Lobby(this));
			break;
		case "GameDefend":
			this.add(new GameDefend(this));
			break;
		}

		this.revalidate();
		this.repaint();
	}

	public ICommunication getCommunication() {
		return communication;
	}

	public void setCommunication(ICommunication communication) {
		this.communication = communication;
	}

	public Server getServer() {
		return server;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}