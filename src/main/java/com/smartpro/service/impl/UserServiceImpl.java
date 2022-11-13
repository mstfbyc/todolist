package com.smartpro.service.impl;

import com.smartpro.dto.UserDto;
import com.smartpro.entity.User;
import com.smartpro.repository.UserRepository;
import com.smartpro.service.UserService;
import com.smartpro.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user = userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElse(new User());
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user == null){
            throw new IllegalArgumentException("Username not exist");
        }
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public TPage<UserDto> getAllPageable(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        TPage<UserDto> response = new TPage<>();
        response.setStat(data, Arrays.asList(modelMapper.map(data,UserDto[].class)));
        return response;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        return Arrays.asList(modelMapper.map(userList,UserDto[].class));
    }
}
