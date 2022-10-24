package panel;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import component.Player;
import enums.Mode;
import util.SizePattern;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Lobby extends JPanel implements Runnable {

	private static final long serialVersionUID = -5467402180679755332L;
	private final int FPS = 2;
	private Thread lobbyThread;
	private JLabel lblAction = new JLabel("Escolha sua posição");
	private JButton btnAttackMode = new JButton("Atacar");
	private JButton btnDefendMode = new JButton("Defender");
	private PanelController panelController;

	public Lobby(PanelController panelController) {
		this.setPreferredSize(new Dimension(SizePattern.screenWidth, SizePattern.screenHeight));
		this.setFocusable(true);
		this.panelController = panelController;
		try {
			List<Player> players = this.panelController.getCommunication().subscribe(this.panelController.getPlayer());
			Player player = Player.getPlayer(players, this.panelController.getPlayer().getId());
			this.panelController.setPlayer(player);
			this.updateButtonStatus(players);

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		initComponents();
		startLobbyThread();
	}
	
	private void startLobbyThread() {
		this.lobbyThread = new Thread(this);
		this.lobbyThread.start();
	}
	
	private void updateContext() {
		try {
			List<Player> players = this.panelController.getCommunication().getPlayers();
			Player player = Player.getPlayer(players, this.panelController.getPlayer().getId());
			this.panelController.setPlayer(player);
			this.updateButtonStatus(players);
			
			if (Player.isReady(players)) {
				this.lobbyThread = null;
				
				this.panelController.changePanel(this.panelController.getPlayer().getMode() == Mode.DEFEND? "GameDefend": "GameAttack");
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {
		setLayout(null);

		int wLblAction = 300;
		int hLblAction = 40;
		lblAction.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAction.setLocation(SizePattern.getXCenterPosition(SizePattern.screenWidth, wLblAction), 125);
		lblAction.setSize(new Dimension(wLblAction, hLblAction));
		lblAction.setHorizontalAlignment(SwingConstants.CENTER);
		lblAction.setVerticalAlignment(SwingConstants.CENTER);
		add(lblAction);

		int wAttackBtn = 300;
		int hAttackBtn = 40;
		btnAttackMode.addActionListener(attackModeBtnAction());
		btnAttackMode.setLocation(SizePattern.getXCenterPosition(SizePattern.screenWidth - (wAttackBtn), wAttackBtn),
				200);
		btnAttackMode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAttackMode.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAttackMode.setSize(new Dimension(wAttackBtn, hAttackBtn));
		add(btnAttackMode);

		int wDefendBtn = 300;
		int hDefendBtn = 40;
		btnDefendMode.addActionListener(defendModeBtnAction());
		btnDefendMode.setLocation(SizePattern.getXCenterPosition(SizePattern.screenWidth + (wDefendBtn), wDefendBtn),
				200);
		btnAttackMode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDefendMode.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDefendMode.setSize(new Dimension(wDefendBtn, hDefendBtn));

		add(btnDefendMode);
	}

	private void updateButtonStatus(List<Player> players) {
		for (Player player : players) {
			if (player.getId() != this.panelController.getPlayer().getId()) {
				switch (player.getMode()) {
				case ATTACK:
					this.btnAttackMode.setEnabled(false);
					this.btnDefendMode.setEnabled(true);
					break;
				case DEFEND:
					this.btnDefendMode.setEnabled(false);
					this.btnAttackMode.setEnabled(true);										
					break;
				default:
					this.btnAttackMode.setEnabled(true);
					this.btnDefendMode.setEnabled(true);
					break;
				}
			}else {
				switch (player.getMode()) {
				case ATTACK:
					this.btnAttackMode.setBackground(Color.GREEN);
					this.btnDefendMode.setBackground(getBackground());
					break;
				case DEFEND:
					this.btnAttackMode.setBackground(getBackground());
					this.btnDefendMode.setBackground(Color.GREEN);										
					break;
				default:
					this.btnAttackMode.setBackground(getBackground());
					this.btnDefendMode.setBackground(getBackground());
					break;
				}
			}
		}
	}

	private ActionListener attackModeBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Player> players = panelController.getCommunication().selectMode(
							panelController.getPlayer().getMode() != Mode.ATTACK ? Mode.ATTACK : Mode.UNDEFINED,
							panelController.getPlayer().getId());
					Player player = Player.getPlayer(players, panelController.getPlayer().getId());
					panelController.setPlayer(player);
					updateButtonStatus(players);

				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		};
	}

	private ActionListener defendModeBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Player> players = panelController.getCommunication().selectMode(
							panelController.getPlayer().getMode() != Mode.DEFEND? Mode.DEFEND : Mode.UNDEFINED,
							panelController.getPlayer().getId());
					Player player = Player.getPlayer(players, panelController.getPlayer().getId());
					panelController.setPlayer(player);
					updateButtonStatus(players);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		};
	}

	@Override
	public void run() {
		double interval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(this.lobbyThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / interval;
			lastTime = currentTime;
			
			if(delta >= 1) {
				this.updateContext();
				delta = 0;
			}
		}		
	}
}
