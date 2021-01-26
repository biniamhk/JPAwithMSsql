package se.biniam.dao;

import se.biniam.domain.Contact;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {

  static   EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("JPAlAB");
    @Override
    public void create(Contact contact) {
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(contact);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Contact> getAll() {

        List<Contact> contacts;
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        contacts=entityManager.createQuery("From Contact ",Contact.class).getResultList();
        entityManager.getTransaction().commit();

        return contacts;
    }

    @Override
    public List<Contact> getByFirstName(String firstName) {
        List<Contact> contacts;
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
       contacts= entityManager.createQuery("From Contact c where c.firstName= :firstName",Contact.class)
                                 .setParameter("firstName",firstName).getResultList();
        return contacts;
    }

    @Override
    public List<Contact> getById(String id) {
        List<Contact> contacts;
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        contacts= entityManager.createQuery("From Contact c where c.id= :id",Contact.class)
                .setParameter("id",id).getResultList();
        return contacts;

    }

    @Override
    public boolean updatePhone(String id,String newPhone) {
        boolean updated=false;
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Contact contact=entityManager.find(Contact.class,id);
        if(contact!=null) {
            contact.setPhone(newPhone);
            updated = true;
            entityManager.getTransaction().commit();
        }
        return updated;


    }

    @Override
    public boolean deleteById(String id) {
        boolean delete=false;
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Contact contact=entityManager.find(Contact.class,id);
        if(contact!=null) {
            entityManager.remove(contact);
            delete = true;
            entityManager.getTransaction().commit();
        }
        return delete;
    }

}
