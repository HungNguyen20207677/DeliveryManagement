package com.sapo.edu.backend.service.implement;

import com.sapo.edu.backend.dto.UsersDTO;
import com.sapo.edu.backend.mapper.UsersMapper;
import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.repository.UsersRepository;
import com.sapo.edu.backend.service.UsersService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    private UsersMapper usersMapper;


    private final PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> users = new ArrayList<>(usersRepository.findAll());
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Users> getUserById(int id) {
        try {
            Optional<Users> userData = usersRepository.findById(id);

            if (userData.isPresent()) {
                return new ResponseEntity<>(userData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Users> updateUser(@Valid int id, @Valid UsersDTO usersDTO) {
        try {
            Optional<Users> userData = usersRepository.findById(id);

            // Map from DTO to Entity
            Users updatedUser = usersMapper.toEntity(usersDTO);

            if (userData.isPresent()) {
                Users existingUser = userData.get();

                // Updates
                existingUser.setUsername(updatedUser.getUsername());
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

//                existingUser.setPassword(updatedUser.getPassword());

                existingUser.setFullName(updatedUser.getFullName());
                existingUser.setRole(updatedUser.getRole());
                existingUser.setPhone(updatedUser.getPhone());
                existingUser.setStatus(updatedUser.getStatus());
                existingUser.setSoTienDuNo(updatedUser.getSoTienDuNo());


                return new ResponseEntity<>(usersRepository.save(existingUser), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteUser(@Valid int userId) {
        try {
            usersRepository.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Page<Users>> getUsersListPagination(int currentPage, int pageSize) {
        try {
//            Pageable firstPageWithTwoElements = (Pageable) PageRequest.of(currentPage, 10);
            Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("updatedAt").ascending());
            Page<Users> categoriesList = usersRepository.findAll(pageable);
            if (categoriesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(categoriesList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
