package com.ndungutse.springboot.myfirstwebapptodo.welcome;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class WelcomeController {
    // // @Autowired
    // private AuthenticationService authService;

    // // OR Constructor Auto Woring
    // public LoginController(AuthenticationService authService) {
    // this.authService = authService;
    // }

    // ****** Handling Parameterized request
    // @RequestMapping("/login")
    // // Model: passing data from controller to a JSP
    // public String goToLogginPage(@RequestParam String name, ModelMap model) {
    // model.put("name", name);
    // return "login";
    // }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    // Model: passing data from controller to a JSP
    public String gotToWelcomePage(ModelMap model) {
        System.out.println("LOGEDIN USER ************ " + getLoggedInUsername());
        model.put("username", getLoggedInUsername());
        return "welcome";
    }

    // @RequestMapping(value = "/login", method = RequestMethod.POST)
    // public String goToWelcome(@RequestParam String username, @RequestParam String
    // password, ModelMap model) {

    // // Valid User name is eric and password is test
    // if (authService.authenticate(username, password)) {
    // model.put("username", username);
    // return "welcome";
    // } else {
    // model.put("errorMessage", "Invalid Cridentials");
    // return "login";
    // }
    // }

    private String getLoggedInUsername() {
        Authentication authenitcation = SecurityContextHolder.getContext().getAuthentication();
        return authenitcation.getName();
    }

}
