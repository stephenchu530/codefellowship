package cool.is.chu.stephen.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class CodefellowshipController {

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/")
    public String getCodefellowship(Principal p, Model m) {
        if (p != null) {
            AppUser user = appUserRepository.findByUsername(p.getName());
            m.addAttribute("user", user);
        }
        m.addAttribute("p", p);
        return "codefellowship";
    }
}