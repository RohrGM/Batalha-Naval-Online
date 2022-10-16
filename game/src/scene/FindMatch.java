package scene;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import server.Server;
import util.SizePattern;

public class FindMatch extends JPanel {
	
	private static final long serialVersionUID = -1199195760028985345L;
	private JTextField txtIp = new JTextField();
	private JTextField txtPort = new JTextField();
	private JButton btnFindMatch = new JButton("Buscar");
	private JButton btnBack = new JButton("Voltar");
	private Principal main;
	private Server server;

	public FindMatch(Server server, Principal main) {	
		this.server = server;
		this.main = main;
		
		initComponents();
	}
	
	private ActionListener findMatchBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("aqui 2");
			}
		};
	}
	
	private ActionListener backToMenuBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.changeScene("Menu");
			}
		};
	}
	
	private void initComponents() {	
		
		int wPanel = SizePattern.getWidht();
		int hPanel = SizePattern.getHeight();
		
		setBounds(-8, -32, wPanel, hPanel);
		setLayout(null);
		
		int wIpTxt = 300;
		int hIpTxt = 40;
		txtIp.setLocation(SizePattern.getXCenterPosition(wPanel, wIpTxt), 125);
		txtIp.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtIp.setSize(new Dimension(wIpTxt, hIpTxt));
		txtIp.setColumns(10);
		add(txtIp);
		
		
		int wPortTxt = 200;
		int hPortTxt = 40;
		txtPort.setLocation(SizePattern.getXCenterPosition(wPanel, wPortTxt), 170);
		txtPort.setAlignmentX(Component.CENTER_ALIGNMENT);
		txtPort.setSize(new Dimension(wPortTxt, hPortTxt));
		txtPort.setColumns(10);
		add(txtPort);
		
		int wFindBtn = 100;
		int hFindBtn = 40;
		btnFindMatch.addActionListener(findMatchBtnAction());
		btnFindMatch.setLocation(SizePattern.getXCenterPosition(wPanel, wFindBtn), 220);
		btnFindMatch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFindMatch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFindMatch.setSize(new Dimension(wFindBtn, hFindBtn));		
		add(btnFindMatch);
		
		int wBackBtn = 100;
		int hBackBtn = 40;
		btnBack.addActionListener(backToMenuBtnAction());
		btnBack.setLocation(SizePattern.getXCenterPosition(wPanel, wBackBtn), 265);
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBack.setSize(new Dimension(wBackBtn, hBackBtn));		
		add(btnBack);
		
	}

}
