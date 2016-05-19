/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.database;

import entities.Author;
import org.hibernate.SessionFactory;
import entities.Book;
import entities.Genre;
import entities.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import web.beans.Pager;

/**
 *
 * @author yehor
 */
public class DataHelper {
    
    private SessionFactory sessionFactory;
    private static DataHelper instance;
    private Pager currentPager;
    private DetachedCriteria currentCriteria;
    
    private DataHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    public static DataHelper getInstance() {
        if (instance == null) {
            instance = new DataHelper();
        }
        return instance;
    }
    
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    
    
    public void runCurrentCriteria()
    {
        Criteria criteria = currentCriteria.getExecutableCriteria(getSession());
        List<Book> list = criteria.setFirstResult(currentPager.getFrom()).setMaxResults(currentPager.getTo()).list();
        currentPager.setCurrentBookList(list);
    }
    
    public void getAllBooks(Pager pager) {
        currentPager = pager;
        Criteria criteria = getSession().createCriteria(Book.class);

        Integer totalBooks = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
        currentPager.setTotalBooksCount(totalBooks);
        currentCriteria = DetachedCriteria.forClass(Book.class);
        
        runCurrentCriteria();
    }
    
    public List<Author> getAllAuthors()
    {
        return getSession().createCriteria(Author.class).list();
    }
    
    public List<Genre> getAllGenres() {
        return getSession().createCriteria(Genre.class).list();
    }
    
    public void getBooksByGenre(long id, Pager pager) {
        currentPager = pager;
        Criterion criterion = Restrictions.eq("genre.id", id);
        
        Criteria criteria = getSession().createCriteria(Book.class).add(criterion);

        Integer totalBooks = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
        currentPager.setTotalBooksCount(totalBooks);
        
        currentCriteria = DetachedCriteria.forClass(Book.class);
        currentCriteria.add(criterion);
        
        runCurrentCriteria();
    }
    
    public void getBooksByAuthor(String author, Pager pager)
    {
        currentPager = pager;
        Criterion criterion = Restrictions.ilike("author.fio", author, MatchMode.ANYWHERE);

        Criteria criteria = getSession().createCriteria(Book.class, "book").createAlias("book.author", "author");

        Integer totalBooks = (Integer) criteria.add(criterion).setProjection(Projections.rowCount()).uniqueResult();
        currentPager.setTotalBooksCount(totalBooks);
        currentCriteria = DetachedCriteria.forClass(Book.class, "book").createAlias("book.author", "author");
        currentCriteria.add(criterion);

        runCurrentCriteria();
    }
    
    public void getBooksByName(String name, Pager pager)
    {
        currentPager = pager;
        Criterion criterion = Restrictions.ilike("name", name, MatchMode.START);

        Criteria criteria = getSession().createCriteria(Book.class);

        Integer totalBooks = (Integer) criteria.add(criterion).setProjection(Projections.rowCount()).uniqueResult();
        currentPager.setTotalBooksCount(totalBooks);
        currentCriteria = DetachedCriteria.forClass(Book.class);
        currentCriteria.add(criterion);

        runCurrentCriteria();
    }
    
    public byte[] getBookImage(long id) {
        return (byte[]) getFieldValue("image", id);
    }
    
    public byte[] getBookContent(long id) {
        return (byte[]) getFieldValue("content", id);
    }
    
    private Object getFieldValue(String fieldName, long id) {
        return getSession().createCriteria(Book.class).setProjection(Projections.property(fieldName)).add(Restrictions.eq("id", id)).uniqueResult();
    }
}
