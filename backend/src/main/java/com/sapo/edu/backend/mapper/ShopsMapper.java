package com.sapo.edu.backend.mapper;

import com.sapo.edu.backend.dto.ShopsDTO;
import com.sapo.edu.backend.model.Shops;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShopsMapper {
    Shops toEntity(ShopsDTO shopsDTO);
    ShopsDTO toDTO(Shops shops);
    List<ShopsDTO> toDTOList(List<Shops> users);
    List<Shops> toEntityList(List<ShopsDTO> shopsDTOS);
}
