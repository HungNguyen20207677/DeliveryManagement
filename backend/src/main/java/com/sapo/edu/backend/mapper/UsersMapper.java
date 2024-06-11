package com.sapo.edu.backend.mapper;

import com.sapo.edu.backend.dto.UsersDTO;
import com.sapo.edu.backend.model.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    Users toEntity(UsersDTO usersDTO);
    UsersDTO toDTO(Users users);
    List<UsersDTO> toDTOList(List<Users> users);
    List<Users> toEntityList(List<UsersDTO> usersDTOS);
}
