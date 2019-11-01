package com.invillia.projetospring.controller;

import com.invillia.projetospring.exception.ActionNotPermitedException;
import com.invillia.projetospring.exception.UserNotFoundException;
import com.invillia.projetospring.model.Team;
import com.invillia.projetospring.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/team")
public class UserControllerTeam {

//    Front > Controller > Service > Repository


    private TeamService teamService;

    @Autowired
    public UserControllerTeam(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public String index(Model model) {
        System.out.println("entrou");
        model.addAttribute("users", teamService.print());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Team team) {
        return "registration-team";
    }

    @PostMapping("/addteam")
    public String addUser(@Valid Team team, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration-team";
        }
        teamService.save(team);
        return "arquivo-mensagem-sucesso";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Team team = teamService.findById(id).orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));
        teamService.delete(id);
        model.addAttribute("users", teamService.print());
        return "redirect:/team";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Team team = teamService.findById(id).orElseThrow(() -> new ActionNotPermitedException(String.valueOf(id)));
        model.addAttribute("user", team);
        return "updateTeam";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Team team, BindingResult result, Model model) {
        if (result.hasErrors()) {
            team.setId(id);
            return "updateteam";
        }
        teamService.update(team);
        model.addAttribute("users", teamService.print());
        return "index";
    }



}
