package us.oscarsoto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import us.oscarsoto.models.DaoFactory;
import us.oscarsoto.models.Post;

import javax.validation.Valid;
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
    public String createNewPost(@Valid Post post, Errors validation, Model model){

        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }

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
    public String editPostById(@PathVariable int id, @Valid Post editedPost, Errors validation, Model model){

        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("post", editedPost);
            return "posts/edit";
        }

        Post existingPost = DaoFactory.getPostsDao().findPostById(id);
        existingPost.setTitle(editedPost.getTitle());
        existingPost.setBody(editedPost.getBody());
        DaoFactory.getPostsDao().updatePost(existingPost);
        return "redirect:/posts/"+id;
    }

    @GetMapping("/{id}/delete")
    public String deletePostById(@PathVariable int id){
        Post existedPost = DaoFactory.getPostsDao().findPostById(id);
        DaoFactory.getPostsDao().deletePost(existedPost);
        return "redirect:/posts";
    }

}
