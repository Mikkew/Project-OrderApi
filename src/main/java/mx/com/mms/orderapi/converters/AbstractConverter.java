package mx.com.mms.orderapi.converters;

import java.util.List;

public abstract class AbstractConverter<Entity, Dto> {
	public abstract Dto fromEntity(Entity entity);
	public abstract Entity fromDto(Dto dto);
	
	public List<Dto> fromEntity(List<Entity> entities) {
		return entities.stream().map(this::fromEntity).toList();
	}
	
	public List<Entity> fromDto(List<Dto> dtos) {
		return dtos.stream().map(this::fromDto).toList();
	}
	
}
