package se.biniam.dao;

import se.biniam.domain.Contact;

import java.util.List;

public interface ContactDAO {
     void create(Contact contact);
     List<Contact> getAll();
     List<Contact> getByFirstName(String firstName);
     List<Contact> getById(String id);
     boolean updatePhone(String id,String Phone);
     boolean deleteById(String id);



}
