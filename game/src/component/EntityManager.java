package component;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import Interface.ICommunication;
import Interface.IEntity;

public class EntityManager implements Serializable {

	private static final long serialVersionUID = 7467458878249247743L;
	private List<IEntity> entities = new ArrayList<>();
	private ICommunication communication;
	private String id;

	public EntityManager(ICommunication communication, String id) {
		this.communication = communication;
		this.id = id;
	}

	public void removeEntity(IEntity entity) {
		this.entities.remove(entity);
	}

	public void addEntity(IEntity entity) {
		try {
			IEntity newEntity = entity.clone();
			newEntity.setManager(null);
			List<IEntity> aux = this.communication.updateEntities(newEntity, this.id);
			this.entities.add(entity);
			this.addEntities(aux);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void updateEntities() {
		try {
			List<IEntity> aux = this.communication.updateEntities(null, this.id);
			this.addEntities(aux);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void addEntities(List<IEntity> entities) {
		for (IEntity entity : entities) {
			entity.setManager(this);
			this.entities.add(entity);
		}
	}

	public List<IEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<IEntity> entities) {
		this.entities = entities;
	}
}
