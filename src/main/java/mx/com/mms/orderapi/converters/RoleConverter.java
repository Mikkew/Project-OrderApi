package mx.com.mms.orderapi.converters;

import mx.com.mms.orderapi.dtos.RoleDTO;
import mx.com.mms.orderapi.entity.Role;

public class RoleConverter extends AbstractConverter<Role, RoleDTO>{

	@Override
	public RoleDTO fromEntity(Role entity) {
		return (entity != null) ? ( RoleDTO.builder()
			.id(entity.getRoleId())
			.name(entity.getName())
			.build()) : null;
	}

	@Override
	public Role fromDto(RoleDTO dto) {
		return (dto != null) ? ( Role.builder()
			.roleId(dto.getId())
			.name(dto.getName())
			.build()) : null;
	}

}
