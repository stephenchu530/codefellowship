package cool.is.chu.stephen.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Calendar;

@Controller
public class PostController {
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/createpost")
    public String createPost(Principal p, Model m) {
        AppUser user = appUserRepository.findByUsername(p.getName());
        m.addAttribute("p", p);
        m.addAttribute("user", user);
        return "createpost";
    }

    @PostMapping("/createnewpost")
    public RedirectView createNewPost(@RequestParam String body, Principal p) {
        AppUser user = appUserRepository.findByUsername(p.getName());
        Post post = new Post(body, new Timestamp(Calendar.getInstance().getTime().getTime()), user);
        postRepository.save(post);
        return new RedirectView("/myprofile");
    }

    @GetMapping("/feed")
    public String showFeed(Principal p, Model m) {
        AppUser user = appUserRepository.findByUsername(p.getName());
        m.addAttribute("p", p);
        m.addAttribute("follows", user.getFollow());
        return "followerfeed";
    }
}
