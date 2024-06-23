package com.example.shopbook.serviecs;

import com.example.shopbook.dtos.UserDTO;
import com.example.shopbook.exceptions.DataNotFoundException;
import com.example.shopbook.models.User;



public interface IUserService {
    User createUser(UserDTO userDTO) throws DataNotFoundException;
    String login(String phoneNumber, String password);
}
