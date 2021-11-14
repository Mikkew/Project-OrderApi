package mx.com.mms.orderapi.converters;

import java.util.List;

public abstract class AbstractConverter<Entity, Dto> {
	public abstract Dto fromEntity(Entity entity);
	public abstract Entity fromDto(Dto dto);
	
	public abstract Dto toDto(Dto dto);
	public abstract Entity toEntity(Entity entity);
	
	public List<Dto> fromEntity(List<Entity> entities) {
		return entities.stream().map(this::fromEntity).toList();
	}
	
	public List<Entity> fromDto(List<Dto> dtos) {
		return dtos.stream().map(this::fromDto).toList();
	}
	
	public List<Entity> toEntity(List<Entity> entities) {
		return entities.stream().map(e -> toEntity(e)).toList();
	}
	
	public List<Dto> toDto(List<Dto> dtos) {
		return dtos.stream().map(e -> toDto(e)).toList();
	}
	
}
