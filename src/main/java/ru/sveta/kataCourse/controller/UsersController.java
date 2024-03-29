package ru.sveta.kataCourse.controller;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sveta.kataCourse.model.User;
import ru.sveta.kataCourse.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/all")
    public String getAll(Model model){
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "users/userlist";
    }
    @GetMapping("/{id}")
    public String get(@PathVariable("id") long id, Model model){
        User user = userService.getById(id);
        System.out.println(user.getName());
        model.addAttribute("user",user);
        return "users/show";
    }

    @GetMapping("/new")
    public String createForm(@ModelAttribute("user") User user){
        return "users/new";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "users/new";
        userService.createUser(user);
        return "redirect:/user/all";
    }

    @PostMapping("/{id}/del")
    public String delete(@PathVariable("id") long id){
        userService.deleteById(id);
        return "redirect:/user/all";
    }

    @GetMapping("/{id}/edit")
    public String editForm(Model model,@PathVariable("id")long id){
        model.addAttribute("user",userService.getById(id));
        return "users/edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@ModelAttribute("user")@Valid User user,
                       BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "users/edit";
        userService.updateUser(user.getId(), user);
        return "redirect:/user/all";
    }



}

