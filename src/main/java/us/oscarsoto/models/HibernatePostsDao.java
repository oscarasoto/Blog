package us.oscarsoto.models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author oscarsoto on 1/6/17.
 *         There is no reasonable excuse for doing anything less than your best.
 *         - Martin, Robert C.
 */
public class HibernatePostsDao implements Posts {
    private Session session;

    public HibernatePostsDao (Session session){
        this.session = session;
    }

    @Override
    public List<Post> retrieveAll() {
        return session.createQuery("from Post").list();
    }

    @Override
    public void insert(Post post) {
        Transaction tx = session.beginTransaction();
        session.save(post);
        tx.commit();
    }

    @Override
    public Post findPostById(int id) {
        Query query = session.createQuery("FROM Post WHERE id = :id");
        query.setParameter("id", id);

        @SuppressWarnings("unchecked")
        Post post = (Post) query.getSingleResult();

        return post;
    }

    @Override
    public void updatePost(Post post) {
        Transaction tx = session.beginTransaction();
        session.update(post);
        tx.commit();
    }

    @Override
    public boolean deletePost(Post post) {
        return false;
    }
}
