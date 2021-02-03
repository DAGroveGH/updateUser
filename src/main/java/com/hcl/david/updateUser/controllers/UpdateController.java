package com.hcl.david.updateUser.controllers;

import com.hcl.david.updateUser.models.User;
import com.hcl.david.updateUser.services.UpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UpdateController {

    Logger logger = LoggerFactory.getLogger(UpdateController.class);

    @Autowired
    UpdateService updateService;

    @GetMapping("/")
    public String searchUserPage(ModelMap modelMap) {
        logger.info("get");
        return "search";
    }

    @PostMapping(value = "/", params = {"user_id"})
    public String searchUserPost(Model model, @RequestParam(value = "user_id") String user_id) {
        logger.info("post");
        logger.info("id" + user_id);

        try {
            Long idParse = Long.parseLong(user_id);
            Optional<User> user = updateService.searchUser(idParse);
            if(user.isPresent()) {
                logger.info("conditional true" + user.get());
                model.addAttribute("user", user.get());
                return "details";
            } else {
                logger.info("conditional false");
                model.addAttribute("error", "Id does not exist");
            }
        } catch (Exception e) {
            logger.info("Caught exception "+  e.getMessage());
            model.addAttribute("error", "Invalid id");
        }
        return "search";
    }

    @PostMapping(value = "/update/{user_id}")
    public String updateUser(Model model, User user, @PathVariable("user_id") Long user_id) {
        logger.info("post");
        user.setUser_id(user_id);
        updateService.updateUser(user);
        model.addAttribute(user);
        return "success";
    }
}
