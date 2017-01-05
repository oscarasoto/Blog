package us.oscarsoto;

/**
 * @author oscarsoto on 1/5/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
public class DaoFactory {
    private static Posts postsDao;

    public static Posts getPostsDao(){
        if (postsDao == null) {
            postsDao = new ListPosts();
        }
        return postsDao;
    }
}