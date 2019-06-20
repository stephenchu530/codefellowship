package cool.is.chu.stephen.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;

@Controller
public class AppUserController {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/createNewUser")
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

    @GetMapping("/users")
    public String showAllUsers(Principal p, Model m) {
        Iterable<AppUser> users = appUserRepository.findAll();
        m.addAttribute("users", users);
        return "allusers";
    }

    @GetMapping("/users/{id}")
    public String getUser(Principal p, Model m, @PathVariable long id) {
        boolean canfollow = true;
        AppUser owner = appUserRepository.findByUsername(p.getName());
        AppUser userToFollow = appUserRepository.findById(id).get();
        if (owner.getFollow().contains(userToFollow)) {
            canfollow = false;
        }

        if (owner.equals(userToFollow)) {
            canfollow = false;
        }

        m.addAttribute("p", p);
        m.addAttribute("user", userToFollow);
        m.addAttribute("canfollow", canfollow);
        return "user";
    }

    @GetMapping("/myprofile")
    public String getProfile(Principal p, Model m) {
        AppUser user = appUserRepository.findByUsername(p.getName());
        m.addAttribute("p", p);
        m.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/followuser/{id}")
    public RedirectView followUser(Principal p, @PathVariable long id) {
        if (!appUserRepository.findById(id).get().getUsername().equals(p.getName())) {
            AppUser owner = appUserRepository.findByUsername(p.getName());
            AppUser userToFollow = appUserRepository.findById(id).get();
            owner.setFollow(userToFollow);
            appUserRepository.save(owner);
        } else {
            throw new AppUserException("Cannot follow this user!");
        }
        return new RedirectView("/users/" + id);
    }

    // came from https://stackoverflow.com/questions/2066946/trigger-404-in-spring-mvc-controller
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    class AppUserException extends RuntimeException {
        public AppUserException(String s) {
            super(s);
        }
    }
}

