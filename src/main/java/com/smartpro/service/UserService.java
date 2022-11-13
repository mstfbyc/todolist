package com.smartpro.service;

import com.smartpro.dto.UserDto;
import com.smartpro.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);
    UserDto getById(Long id);
    UserDto getByUsername(String username);
    TPage<UserDto> getAllPageable(Pageable pageable);
    List<UserDto> getAll();
}
