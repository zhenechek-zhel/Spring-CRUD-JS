package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.database.Util;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class RootController {

    private Util util;
    private UserService userService;

    @Autowired
    public RootController(Util util, UserService userService) {
        this.util = util;
        this.userService = userService;
    }

    @GetMapping("")
    public String rootBoot(){
        if (userService.getAllUsers().isEmpty()){
            util.addUsersToDB();
        }
        return "redirect:/login";
    }

}
