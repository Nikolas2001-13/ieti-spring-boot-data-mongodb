package org.ada.school.service;

import org.ada.school.dto.UserDto;
import org.ada.school.model.User;
import org.ada.school.repository.UserDocument;
import org.ada.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceMongoDB
        implements UserService
{

    private final UserRepository userRepository;

    public UserServiceMongoDB(@Autowired UserRepository userRepository )
    {
        this.userRepository = userRepository;
    }

    @Override
    public User create( User user )
    {
        userRepository.save(new UserDocument(user.getId(), user.getName(), user.getEmail(), user.getLastName(), user.getCreatedAt()));
        return user;
    }

    @Override
    public User findById( String id )
    {
        UserDocument userDocument = userRepository.findById(id).orElse(null);
        User user = new User(userDocument.getId(), userDocument.getName(), userDocument.getEmail(), userDocument.getLastName(), userDocument.getCreatedAt());
        return user;
    }

    @Override
    public List<User> all()
    {
        List<UserDocument> listUserDocument = userRepository.findAll();
        List<User> userList = new ArrayList<>();
        for(UserDocument userDocument: listUserDocument){
            User user = new User(userDocument.getId(), userDocument.getName(), userDocument.getEmail(),userDocument.getLastName(), userDocument.getCreatedAt());
            userList.add(user);
        }
        return userList;
    }

    @Override
    public boolean deleteById( String id )
    {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        } return false;
    }

    @Override
    public User update(UserDto userDto, String id) {
        if (userRepository.existsById(id)){
            UserDocument userDocument = userRepository.findById(id).get();
            userDocument.update(userDto);
            userRepository.save(userDocument);
            return new User(userDocument.getId(),userDocument.getName(),userDocument.getEmail(),userDocument.getLastName(),userDocument.getCreatedAt());
        } return null;
    }

}
