package com.invillia.projetospring.controller;

import com.invillia.projetospring.exception.ActionNotPermitedException;
import com.invillia.projetospring.exception.UserNotFoundException;
import com.invillia.projetospring.model.People;
import com.invillia.projetospring.service.PeopleService;
import com.invillia.projetospring.service.TeamService;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
@RequestMapping(value = "/people")
public class UserControllerPeople {

    private PeopleService peopleService;
    private TeamService teamService;

    public UserControllerPeople(PeopleService peopleService, TeamService teamService) {
        this.peopleService = peopleService;
        this.teamService = teamService;
    }

//    @Autowired
//    public UserControllerPeople(TeamService teamService){
//        this.teamService = teamService;
//    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", peopleService.print());
        return "indexPeople";
    }

    @GetMapping("/signup")
    public String showSignUpForm(People people, Model model) {
        model.addAttribute("teams", teamService.print());
        return "registration-people";
    }

//    @GetMapping("/search-player")
//    public String searchPlayerForm(Model model){
//        model.addAttribute("people", new People());
//        return "search-people";
//    }

//    @GetMapping("/search-player/{name}")
//    public String getPlayerByName(@PathVariable String name, Model model){
//        People people = peopleService.findByName(name);
//        model.addAttribute("people", people);
//        return "search-people-result";
//    }

    @PostMapping("/addpeople")
    public String addUser(@Valid People people, BindingResult result) {
        if (result.hasErrors()) {
            return "registration-people";
        }
        peopleService.save(people);
        return "arquivo-mensagem-sucesso";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        People people = peopleService.findById(id).orElseThrow(() -> new ActionNotPermitedException(String.valueOf(id)));

        model.addAttribute("user", people);
        return "updatePeople";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid People people, BindingResult result, Model model) {
        if (result.hasErrors()) {
            people.setId(id);
            return "updatePeople";
        }
       peopleService.update(people);
        model.addAttribute("users", peopleService.print());
        return "indexPeople";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        People people = peopleService.findById(id).orElseThrow(() -> new UserNotFoundException(String.valueOf(id)));
        peopleService.delete(id);
        model.addAttribute("users", peopleService.print());
        return "redirect:/people";
    }

}
