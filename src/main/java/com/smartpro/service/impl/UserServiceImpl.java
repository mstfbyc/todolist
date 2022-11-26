package com.smartpro.service.impl;

import com.smartpro.dto.UserDto;
import com.smartpro.entity.Users;
import com.smartpro.repository.UserRepository;
import com.smartpro.request.RegistirationRequest;
import com.smartpro.service.UserService;
import com.smartpro.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    @Override
    public UserDto save(UserDto userDto) {
        Users user = modelMapper.map(userDto, Users.class);
        user = userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public UserDto getById(Long id) {
        Users user = userRepository.findById(id).orElse(new Users());
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getByUsername(String username) {
        Users user = userRepository.findByUsername(username).orElse(null);
        if(user == null){
            throw new IllegalArgumentException("Username not exist");
        }
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public TPage<UserDto> getAllPageable(Pageable pageable) {
        Page<Users> data = userRepository.findAll(pageable);
        TPage<UserDto> response = new TPage<>();
        response.setStat(data, Arrays.asList(modelMapper.map(data,UserDto[].class)));
        return response;
    }

    @Override
    public List<UserDto> getAll() {
        List<Users> userList = userRepository.findAll();
        return Arrays.asList(modelMapper.map(userList,UserDto[].class));
    }


    public Boolean register(RegistirationRequest request){
        try{
            Users user = new Users();
            user.setEmail(request.getEmail());
            user.setNameSurname(request.getNameSurname());
            user.setPassword(encoder.encode(request.getPassword()));
            user.setUsername(request.getUsername());
            userRepository.save(user);
            return Boolean.TRUE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }
}
