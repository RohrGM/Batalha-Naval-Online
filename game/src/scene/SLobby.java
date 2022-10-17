package scene;

import javax.swing.JPanel;

import component.Room;
import util.Service;
import util.SizePattern;

import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SLobby extends JPanel {

	private static final long serialVersionUID = -5467402180679755332L;
	private JLabel lblPlayers = new JLabel("");
	JButton btnRoom = new JButton("RoomTest");

	private SPrincipal main;
	
	public SLobby(SPrincipal main) {
		this.main = main;		
		try {
			main.getService().joinRoom(main.getPlayer(), main.getListener());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		initComponents();
	}
	
	private ActionListener roomTestBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(main.getRoom().getPlayers().size());
			}
		};
	}
	
	private void initComponents() {
		int wPanel = SizePattern.getWidht();
		int hPanel = SizePattern.getHeight();

		setBounds(SizePattern.getxOffSet(), SizePattern.getyOffSet(), wPanel, hPanel);
		setLayout(null);		
		
		lblPlayers.setBounds(110, 138, 46, 14);
		lblPlayers.setText("");
		add(lblPlayers);
		btnRoom.addActionListener(roomTestBtnAction());
		
		
		btnRoom.setBounds(227, 246, 89, 23);
		add(btnRoom);
	}
}
