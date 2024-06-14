package com.sapo.edu.backend.mapper;

import com.sapo.edu.backend.dto.ShopsDTO;
import com.sapo.edu.backend.model.Shops;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-15T02:08:08+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ShopsMapperImpl implements ShopsMapper {

    @Override
    public Shops toEntity(ShopsDTO shopsDTO) {
        if ( shopsDTO == null ) {
            return null;
        }

        Shops shops = new Shops();

        shops.setFullName( shopsDTO.getFullName() );
        shops.setPhone( shopsDTO.getPhone() );
        shops.setSoTienDuNo( shopsDTO.getSoTienDuNo() );

        return shops;
    }

    @Override
    public ShopsDTO toDTO(Shops shops) {
        if ( shops == null ) {
            return null;
        }

        ShopsDTO shopsDTO = new ShopsDTO();

        shopsDTO.setFullName( shops.getFullName() );
        shopsDTO.setPhone( shops.getPhone() );
        shopsDTO.setSoTienDuNo( shops.getSoTienDuNo() );

        return shopsDTO;
    }

    @Override
    public List<ShopsDTO> toDTOList(List<Shops> users) {
        if ( users == null ) {
            return null;
        }

        List<ShopsDTO> list = new ArrayList<ShopsDTO>( users.size() );
        for ( Shops shops : users ) {
            list.add( toDTO( shops ) );
        }

        return list;
    }

    @Override
    public List<Shops> toEntityList(List<ShopsDTO> shopsDTOS) {
        if ( shopsDTOS == null ) {
            return null;
        }

        List<Shops> list = new ArrayList<Shops>( shopsDTOS.size() );
        for ( ShopsDTO shopsDTO : shopsDTOS ) {
            list.add( toEntity( shopsDTO ) );
        }

        return list;
    }
}
