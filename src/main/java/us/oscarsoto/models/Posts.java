package us.oscarsoto.models;

import us.oscarsoto.models.Post;

import java.util.List;

/**
 * @author oscarsoto on 1/5/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
public interface Posts {
    List<Post> retrieveAll();
    void insert(Post post);
    Post findPostById(int id);
    void updatePost(Post post);
    boolean deletePost(Post post);
}
