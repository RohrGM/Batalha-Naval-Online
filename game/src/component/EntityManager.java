package component;

import java.util.ArrayList;
import java.util.List;

import Interface.IEntity;

public class EntityManager {

	private List<IEntity> entities = new ArrayList<>();
	
	public void removeEntity(IEntity entity) {
		this.entities.remove(entity);
	}
	
	public void addEntity(IEntity entity) {
		this.entities.add(entity);
	}

	public List<IEntity> getEntities() {
		return entities;
	}

	public void setEntities(List<IEntity> entities) {
		this.entities = entities;
	}
	
	
}
