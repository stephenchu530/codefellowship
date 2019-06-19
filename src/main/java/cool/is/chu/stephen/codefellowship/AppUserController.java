package cool.is.chu.stephen.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import java.sql.Date;
import java.util.ArrayList;

@Controller
public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/users")
    public RedirectView createUser(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String fName,
                                   @RequestParam String lName,
                                   @RequestParam Date birthDate,
                                   @RequestParam String bio) {
        AppUser newUser = new AppUser(username, bCryptPasswordEncoder.encode(password), fName, lName, birthDate, bio);
        appUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

    @GetMapping("/signup")
    public String createNewUser() {
        return "signup";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/users/{userName}")
    public String getProfile(@PathVariable String userName, Model m) {
        AppUser user = appUserRepository.findByUsername(userName);
        m.addAttribute("user", user);
        return "profile";
    }
}

