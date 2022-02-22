package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.database.Util;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

@Controller
@RequestMapping("/singlepage")
public class SinglePage {

    private final UserService userService;

    @Autowired
    public SinglePage(UserService userService, RoleService roleService, Util util) {
        this.userService = userService;
    }


    @GetMapping()
    public String getSinglePage(@AuthenticationPrincipal User authUser, Model model) {
        model.addAttribute("authUser", authUser);
        return "single-page";
    }
}
