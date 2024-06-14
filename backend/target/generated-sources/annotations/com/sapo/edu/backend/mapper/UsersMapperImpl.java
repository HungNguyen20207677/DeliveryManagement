package com.sapo.edu.backend.mapper;

import com.sapo.edu.backend.dto.UsersDTO;
import com.sapo.edu.backend.model.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-14T08:42:59+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UsersMapperImpl implements UsersMapper {

    @Override
    public Users toEntity(UsersDTO usersDTO) {
        if ( usersDTO == null ) {
            return null;
        }

        Users users = new Users();

        users.setUsername( usersDTO.getUsername() );
        users.setPassword( usersDTO.getPassword() );
        users.setFullName( usersDTO.getFullName() );
        users.setRole( usersDTO.getRole() );
        users.setPhone( usersDTO.getPhone() );
        users.setStatus( usersDTO.getStatus() );
        users.setSoTienDuNo( usersDTO.getSoTienDuNo() );

        return users;
    }

    @Override
    public UsersDTO toDTO(Users users) {
        if ( users == null ) {
            return null;
        }

        UsersDTO usersDTO = new UsersDTO();

        usersDTO.setUsername( users.getUsername() );
        usersDTO.setPassword( users.getPassword() );
        usersDTO.setFullName( users.getFullName() );
        usersDTO.setRole( users.getRole() );
        usersDTO.setPhone( users.getPhone() );
        usersDTO.setStatus( users.getStatus() );
        usersDTO.setSoTienDuNo( users.getSoTienDuNo() );

        return usersDTO;
    }

    @Override
    public List<UsersDTO> toDTOList(List<Users> users) {
        if ( users == null ) {
            return null;
        }

        List<UsersDTO> list = new ArrayList<UsersDTO>( users.size() );
        for ( Users users1 : users ) {
            list.add( toDTO( users1 ) );
        }

        return list;
    }

    @Override
    public List<Users> toEntityList(List<UsersDTO> usersDTOS) {
        if ( usersDTOS == null ) {
            return null;
        }

        List<Users> list = new ArrayList<Users>( usersDTOS.size() );
        for ( UsersDTO usersDTO : usersDTOS ) {
            list.add( toEntity( usersDTO ) );
        }

        return list;
    }
}
