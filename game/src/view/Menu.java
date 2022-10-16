package view;
import java.awt.EventQueue;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.SizePattern;
import server.Server;
import service.Service;

import java.io.Serializable;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Menu extends JFrame implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane = new JPanel();
	private JPanel menuPanel = new JPanel();
	private JButton btnHostMatch = new JButton("Hospedar Partida");
	private JButton btnFindMatch = new JButton("Buscar Partida");
	
	private Server server = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}

	public Menu() {		
		initComponents();
		
	}
	
	private ActionListener hostMatchBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server = new Server();
					server.start();
				} catch (RemoteException el) {
					JOptionPane.showInternalConfirmDialog(rootPane, el.getMessage());
				}
			}
		};
	}
	
	private ActionListener findMatchBtnAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("aqui 2");
			}
		};
	}
	
	private void initComponents() {
		SizePattern sizePattern = new SizePattern();
		
		//################################## PAINEL PRINCIPAL ####################################
		
		int wPanel = sizePattern.getWidht();
		int hPanel = sizePattern.getHeight();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, wPanel, hPanel);
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		contentPane.setLayout(null);
		
		//######################################## MENU ##########################################
		
		menuPanel.setBounds(-8, -32, wPanel, hPanel);
		menuPanel.setLayout(null);
		contentPane.add(menuPanel);
		
		int wHostBtn = 300;
		int hHostBtn = 40;
		btnHostMatch.addActionListener(hostMatchBtnAction());
		btnHostMatch.setLocation(sizePattern.getXCenterPosition(wPanel, wHostBtn), 125);
		btnHostMatch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHostMatch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnHostMatch.setSize(new Dimension(wHostBtn, hHostBtn));
		menuPanel.add(btnHostMatch);
		
		int wFindBtn = 300;
		int hFindBtn = 40;
		btnFindMatch.addActionListener(findMatchBtnAction());
		btnFindMatch.setLocation(sizePattern.getXCenterPosition(wPanel, wHostBtn), 200);
		btnHostMatch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFindMatch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnFindMatch.setSize(new Dimension(wFindBtn, hFindBtn));		
		menuPanel.add(btnFindMatch);
	}
}
