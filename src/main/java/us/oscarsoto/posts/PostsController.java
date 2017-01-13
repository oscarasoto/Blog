package us.oscarsoto.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import us.oscarsoto.security.BaseController;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


/**
 * @author oscarsoto on 1/5/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */

@Controller
@RequestMapping("/posts")
public class PostsController extends BaseController {

    @Value("${file-upload-path}")
    private String uploadPath;

    @Autowired
    Posts postsDao;

    @GetMapping
    public String index(Model m){
        m.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/create")
    public  String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/create")
    public String createNewPost(@Valid Post post, Errors validation, @RequestParam(name = "file") MultipartFile uploadedFile, Model model){

        if(validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }
        post.setUser(loggedInUser());
        postsDao.save(post);
        String filename = "post"+post.getId()+uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);

        try {
            uploadedFile.transferTo(destinationFile);
            post.setFileName(filename);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }

        postsDao.save(post);
        System.out.println(post.getFileName());
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String showPostById(@PathVariable Long id, Model model){
        Post existingPost = postsDao.findOne(id);
        Long existingPostUserId = existingPost.getUser().getId();

        model.addAttribute("post",  existingPost);
        model.addAttribute("showEditControls", isLoggedIn() && existingPostUserId == loggedInUser().getId());
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

