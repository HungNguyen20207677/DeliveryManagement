package com.sapo.edu.backend.service.implement;

import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.model.enumclasses.Roles;
import com.sapo.edu.backend.repository.UsersRepository;
import com.sapo.edu.backend.service.ShippersService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippersServiceImpl implements ShippersService {

    private final UsersRepository usersRepository;

    public ShippersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public ResponseEntity<List<Users>> getShippersListPagination(int currentPage, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("fullName").ascending());
            List<Users> usersList = usersRepository.findAllByRole(Roles.SHIPPER, pageable);
            if (usersList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(usersList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
