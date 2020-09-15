package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private UserMapper usersMapper;
    private HashService hashService;

    @Autowired
    public UserService(UserMapper usersMapper, HashService hashService) {
        this.usersMapper = usersMapper;
        this.hashService = hashService;
    }

    public int createUser(User user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setSalt(encodedSalt);
        newUser.setPassword(hashedPassword);

        return usersMapper.insertUser(newUser);
    }

    public User getUser(String userName) throws NullPointerException{
        if (isUsernameAvailable(userName)){
            throw new NullPointerException();
        }
        User user = usersMapper.getUser(userName);
        return user;
    }

    public void updateUser(User user) throws NullPointerException{
        if (isUsernameAvailable(user.getUsername())){
            throw new NullPointerException();
        }
        usersMapper.updateUser(user);
    }

    public void deleteUser(User user) throws NullPointerException{
        if (isUsernameAvailable(user.getUsername())){
            throw new NullPointerException();
        }
        usersMapper.deleteUser(user.getUsername());
    }

    public boolean isUsernameAvailable(String userName){
        return usersMapper.getUser(userName) == null;
    }

    public int getUserById(String username) {
        return usersMapper.getUserById(username);
    }
}
