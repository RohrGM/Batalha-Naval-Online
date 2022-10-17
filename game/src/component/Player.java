package component;

import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = -7051252820163288760L;
	private String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
