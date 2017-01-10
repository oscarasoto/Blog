package us.oscarsoto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import us.oscarsoto.posts.Post;
import us.oscarsoto.posts.Posts;

import javax.validation.Valid;

/**
 * @author oscarsoto on 1/10/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    Users usersDao;

    @GetMapping("/create")
    public  String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping("/create")
    public String createNewPost(@Valid User user, Errors validation, Model model){

        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "users/create";
        }

        usersDao.save(user);
        return "redirect:/posts";
    }
}