package com.sapo.edu.backend.service.implement;

import com.sapo.edu.backend.dto.ShopsDTO;
import com.sapo.edu.backend.mapper.ShopsMapper;
import com.sapo.edu.backend.mapper.UsersMapper;
import com.sapo.edu.backend.model.Shops;
import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.repository.ShopsRepository;
import com.sapo.edu.backend.repository.UsersRepository;
import com.sapo.edu.backend.service.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopsServiceImpl implements ShopsService {

    private final ShopsRepository shopsRepository;

    @Autowired
    private ShopsMapper shopsMapper;

    public ShopsServiceImpl(ShopsRepository shopsRepository) {
        this.shopsRepository = shopsRepository;
    }

    @Override
    public ResponseEntity<List<Shops>> getAllShops() {
        try {
            List<Shops> shops = new ArrayList<>(shopsRepository.findAll());
            return new ResponseEntity<>(shops, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Shops> getShopById(int id) {
        try {
            Optional<Shops> shopData = shopsRepository.findById(id);

            if (shopData.isPresent()) {
                return new ResponseEntity<>(shopData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Shops> createShop(ShopsDTO shopsDTO) {
        try {
            Shops _shop = shopsRepository.save(shopsMapper.toEntity(shopsDTO));
            return new ResponseEntity<>(_shop, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Shops> updateShop(int id, ShopsDTO shopsDTO) {
        try {
            Optional<Shops> shopData = shopsRepository.findById(id);

            if (shopData.isPresent()) {
                Shops _shop = shopData.get();

                Shops updatedShop = shopsMapper.toEntity(shopsDTO);

                _shop.setFullName(updatedShop.getFullName());
                _shop.setPhone(updatedShop.getPhone());
                _shop.setSoTienDuNo(updatedShop.getSoTienDuNo());

                return new ResponseEntity<>(shopsRepository.save(_shop), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteShop(int id) {
        try {
            shopsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Page<Shops>> getShopsListPagination(int currentPage, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("updatedAt").ascending());
            Page<Shops> shopsList = shopsRepository.findAll(pageable);
            if (shopsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(shopsList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
