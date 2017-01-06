package us.oscarsoto.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oscarsoto on 1/5/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
public class ListPosts implements Posts {
    private List<Post> posts;

    public ListPosts(){
        posts = new ArrayList<>();
    }

    @Override
    public List<Post> retrieveAll() {
        return posts;
    }

    @Override
    public void insert(Post post) {
        post.setId(posts.size()+1);
        posts.add(post);
    }

    @Override
    public Post findPostById(int id) {
        return null;
    }

    @Override
    public void updatePost(Post post) {

    }

    @Override
    public boolean deletePost(Post post) {
        return false;
    }
}
