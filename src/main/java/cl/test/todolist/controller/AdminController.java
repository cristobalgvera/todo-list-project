package cl.test.todolist.controller;

import cl.test.todolist.service.CredentialService;
import cl.test.todolist.service.UserService;
import cl.test.todolist.service.maintenance.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private CredentialService credentialService;

    @GetMapping(value = {"", "/"})
    public String welcome() {
        return "admin/welcome";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", adminUserService.usersCredentialsList());
        return "admin/rest/users";
    }
}
