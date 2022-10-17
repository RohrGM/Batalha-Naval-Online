package service;

import java.io.Serializable;

import component.Room;
import scene.SPrincipal;

public class ListenerService implements Serializable{	

	private static final long serialVersionUID = -8608123939016466199L;
	private SPrincipal main;
	
	public ListenerService(SPrincipal main) {
		this.main = main;
	}
	
	public void updateRoom(Room room) {
		main.setRoom(room);
	}
}
