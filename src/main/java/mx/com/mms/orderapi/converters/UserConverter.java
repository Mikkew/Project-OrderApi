package mx.com.mms.orderapi.converters;

import mx.com.mms.orderapi.dtos.UserDTO;
import mx.com.mms.orderapi.entity.User;

public class UserConverter extends AbstractConverter<User, UserDTO>{

	@Override
	public UserDTO fromEntity(User entity) {
		return (entity != null) ? ( UserDTO.builder()
			.id(entity.getUserId())
			.firstName(entity.getFirstName())
			.lastName(entity.getLastName())
			.email(entity.getEmail())
			.username(entity.getUsername())
			.build()):null;
	}

	@Override
	public User fromDto(UserDTO dto) {
		return (dto != null) ? (User.builder()
			.userId(dto.getId())
			.firstName(dto.getFirstName())
			.lastName(dto.getLastName())
			.email(dto.getEmail())
			.username(dto.getUsername())
			.password(dto.getPassword())
			.build()) : null;
	}

}
