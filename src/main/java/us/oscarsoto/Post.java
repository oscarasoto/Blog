package us.oscarsoto;

/**
 * @author oscarsoto on 1/5/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
public class Post {
    private long id;
    private String title;
    private String body;

    public Post(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}

