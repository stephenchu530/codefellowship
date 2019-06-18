package cool.is.chu.stephen.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class CodefellowshipController {

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/")
    public String getCodefellowship(Principal p, Model m) {
        if (p != null)
            m.addAttribute("user", p.getName());
            m.addAttribute("present", p);
        return "codefellowship";
    }
}