package us.oscarsoto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import us.oscarsoto.models.DaoFactory;
import us.oscarsoto.models.Post;

import java.util.List;

/**
 * @author oscarsoto on 1/5/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */

@Controller
@RequestMapping("/posts")
public class PostsController {

    @GetMapping
    public String index(Model m){
        List<Post> posts = DaoFactory.getPostsDao().retrieveAll();
        m.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/create")
    public  String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/create")
    public String createNewPost(@ModelAttribute Post post){
        DaoFactory.getPostsDao().insert(post);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String findPostById(@PathVariable int id, Model model){
        Post post = DaoFactory.getPostsDao().findPostById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/{id}/edit")
    public String showEditPostById(@PathVariable int id, Model model){
        Post post = DaoFactory.getPostsDao().findPostById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPostById(@PathVariable int id, @ModelAttribute Post editedPost){
        Post existedPost = DaoFactory.getPostsDao().findPostById(id);
        existedPost.setTitle(editedPost.getTitle());
        existedPost.setBody(editedPost.getBody());
        DaoFactory.getPostsDao().updatePost(existedPost);
        return "redirect:/posts/"+id;
    }

}
