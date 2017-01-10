package us.oscarsoto.posts;

import org.springframework.data.repository.CrudRepository;

/**
 * @author oscarsoto on 1/5/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
public interface Posts extends CrudRepository<Post, Long>{

//    List<Post> retrieveAll();
//    void insert(Post post);
//    Post findPostById(Long id);
//    void updatePost(Post post);
//    void deletePost(Post post);
}
