package us.oscarsoto;

import java.util.List;

/**
 * @author oscarsoto on 1/5/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
public interface Posts {
    List<Post> retrieveAll();
    void save(Post post);

}
