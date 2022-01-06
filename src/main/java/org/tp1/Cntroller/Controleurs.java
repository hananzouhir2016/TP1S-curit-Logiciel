package org.tp1.Cntroller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controleurs {
	
	//@RequestMapping("/")
	
    @GetMapping("/Accueil")
    public String Accuiel(Map<String, Object> model) {
        return "Accueil.html";
    }

    @GetMapping("/Login")
    public String Login(Map<String, Object> model) {
        return "login.html";
    }

    @GetMapping("/Admin")
    public String Admin(Map<String, Object> model) {
        return "Admin.html";
    }

    @GetMapping("/User")
    public String User(Map<String, Object> model) {
        return "User.html";
    }
}
