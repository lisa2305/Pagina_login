package it.marconi.pagina_login1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;
import it.marconi.pagina_login1.domains.Person;
import it.marconi.pagina_login1.services.UserService;

@Controller
@RequestMapping("/")
public class ControllerLogin {

    @Autowired
    UserService servizioUser;

     @GetMapping("/user")
    public ModelAndView choose(@RequestParam("type") String type) {

        if(type.equals("new"))
            return new ModelAndView("registration").addObject("userForm", new Person());
        else if(type.equals("login"))
            return new ModelAndView("login").addObject("userForm", new Person());
        else 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagina non trovata!");
    }

    @PostMapping("/user/login")
    public ModelAndView logginUser(@ModelAttribute Person userForm,RedirectAttributes attr) {
        String username = userForm.getNickName();     
        Optional<Person> user = servizioUser.getUserByUsername(username);

        if (user.isPresent()) {
            return new ModelAndView("redirect:/user/" + username);
        }
        else {
            return new ModelAndView("redirect:/user?type=login");
        }
    }

     @PostMapping("/user/new")
    public ModelAndView nuovoUtente(@ModelAttribute Person userForm) {
        servizioUser.addUser(userForm); 
        String username = userForm.getSurname();         
        return new ModelAndView("redirect:/user/" + username);
    }

    @GetMapping("/user/{nickname}")
    public  ModelAndView userDetail(@PathVariable("nickname") String nickname) {
        Optional<Person> user = servizioUser.getUserByUsername(nickname);

        if (user.isPresent())
            return new ModelAndView("user-detail").addObject("user", user.get());
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!");
    }

}   