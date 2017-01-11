package us.oscarsoto.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import us.oscarsoto.security.BaseController;


import javax.validation.Valid;


/**
 * @author oscarsoto on 1/5/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */

@Controller
@RequestMapping("/posts")
public class PostsController extends BaseController {

    @Autowired
    Posts postsDao;

    @GetMapping
    public String index(Model m){
//        List<Post> posts = DaoFactory.getPostsDao().retrieveAll();

        m.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/create")
    public  String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/create")
    public String createNewPost(@Valid Post post, Errors validation, Model model){

        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }

//        DaoFactory.getPostsDao().insert(post);
//        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(loggedInUser());
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String findPostById(@PathVariable Long id, Model model){
//        Post post = DaoFactory.getPostsDao().findPostById(id);
        model.addAttribute("post", postsDao.findOne(id));
        return "posts/show";
    }

    @GetMapping("/{id}/edit")
    public String showEditPostById(@PathVariable Long id, Model model){

        model.addAttribute("post", postsDao.findOne(id));
        return "posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPostById(@PathVariable Long id, @Valid Post editedPost, Errors validation, Model model){

        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("post", editedPost);
            return "posts/edit";
        }

        Post existingPost = postsDao.findOne(id);
        if (existingPost.getUser().getId() != loggedInUser().getId()) {
            return "redirect:/posts";
        }
        existingPost.setTitle(editedPost.getTitle());
        existingPost.setBody(editedPost.getBody());
        postsDao.save(existingPost);
        return "redirect:/posts/"+id;
    }

    @GetMapping("/{id}/delete")
    public String deletePostById(@PathVariable Long id){
        Post existedPost = postsDao.findOne(id);

        postsDao.delete(existedPost);
        return "redirect:/posts";
    }

}

//Nest case number 02841849
