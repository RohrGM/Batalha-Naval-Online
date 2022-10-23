package panel;

import javax.swing.JPanel;

import component.Player;
import util.SizePattern;

import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class Lobby extends JPanel {

	private static final long serialVersionUID = -5467402180679755332L;
	private JLabel lblPlayers = new JLabel("");
	private PanelController panelController;
	private JButton btnRoom = new JButton("RoomTest");

	public Lobby(PanelController panelController) {
		this.setPreferredSize(new Dimension(SizePattern.screenWidth, SizePattern.screenHeight));
		this.setFocusable(true);
		this.panelController = panelController;
		try {
			System.out.println();
			List<Player> players =  this.panelController.getServer().subscribe(this.panelController.getPlayer());
			Player player = Player.getPlayer(players, this.panelController.getPlayer().getId());
			this.panelController.setPlayer(player);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		initComponents();
	}

	private ActionListener roomTestBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(panelController.getCommunication().hello("aaaaaaaaaaaaaa"));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
	}

	private void initComponents() {
		setLayout(null);

		lblPlayers.setBounds(110, 138, 46, 14);
		lblPlayers.setText("");
		add(lblPlayers);
		btnRoom.addActionListener(roomTestBtnAction());

		btnRoom.setBounds(227, 246, 89, 23);
		add(btnRoom);
	}
}
