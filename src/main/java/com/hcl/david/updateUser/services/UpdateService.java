package com.hcl.david.updateUser.services;

import com.hcl.david.updateUser.models.User;
import com.hcl.david.updateUser.models.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateService {

    Logger logger = LoggerFactory.getLogger(UpdateService.class);

    @Autowired
    UserRepository userRepository;

    public Optional<User> searchUser(Long user_id) {

        logger.info("userid: " + user_id);

        return userRepository.findById(user_id);
    }

    public void updateUser(User user) {
        User former = userRepository.findById(user.getUser_id()).get();
        former.setUsername(user.getUsername());
        former.setPassword(user.getPassword());
        userRepository.save(former);
    }
}
