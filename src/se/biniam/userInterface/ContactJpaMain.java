package se.biniam.userInterface;

import se.biniam.dao.ContactDAO;
import se.biniam.dao.ContactDAOImpl;
import se.biniam.domain.Contact;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class ContactJpaMain {
   static ContactDAO contactDAO=new  ContactDAOImpl();
   static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        boolean loop=true;
        while(loop){
        System.out.println("press 1 :- for creating contact ");
        System.out.println("press 2 :- for getting  all contacts ");
        System.out.println("press 3 :- for getting  contact by first name ");
        System.out.println("press 4 :- for getting contact by id");
        System.out.println("press 5 :- for updating contacts phone number ");
        System.out.println("press 6 :- for removing  contact by id");
        System.out.println("press 7 :to exit");
        int choice=scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                creatingContacts();
                break;
            case 2:
                getAllContacts();
                break;
            case 3:
                getByFirstName();
                break;
            case 4:
                getById();
                break;
            case 5:
                updatePhone();
                break;
            case 6:
                deleteById();
                break;
            case 7:
                loop=false;
                break;
        }
        }

    }
    public static void creatingContacts(){
        String id,username,firstName,lastName,phone;
        System.out.print("enter id :");
        id=scanner.nextLine();
        System.out.print("enter username :");
        username=scanner.nextLine();
        System.out.print("enter firstname :");
        firstName=scanner.nextLine();
        System.out.print("enter lastName :");
        lastName=scanner.nextLine();
        System.out.print("enter phone : ");
        phone=scanner.nextLine();
        Contact contact=new Contact(id,username,firstName,lastName,phone);
        contactDAO.create(contact);
    }
    //this method is going to list all contacts and also will
    // tell us if the person is pension or not
    public static void getAllContacts() {
        List<Contact> contacts = contactDAO.getAll();
       for (Contact c : contacts)
        System.out.println(c);

        for (Contact contact :contacts){
            int year = Calendar.getInstance().get(Calendar.YEAR);
            Integer id=Integer.parseInt(contact.getId().substring(0,4));
            if(year-id >=67)
                System.out.println(contact.getFirstName() +" is pension");
            else
                System.out.println(contact.getFirstName()+" is not pension");
        }
    }
    public static void getByFirstName(){
        System.out.print("enter the name you want to serach : ");
        String firstName=scanner.nextLine();
        System.out.println(contactDAO.getByFirstName(firstName));

    }
    public static void getById(){
        System.out.print("enter ID you want to serach : ");
        String id=scanner.nextLine();
        System.out.println(contactDAO.getById(id));

    }
    public static void updatePhone(){
        System.out.print("enter id you want to update : ");
        String id=scanner.nextLine();
        System.out.println();
        System.out.print("enter new phone number : ");
        String phone=scanner.nextLine();
        System.out.println("the contact update processes is :" +contactDAO.updatePhone(id,phone));
    }

    public static void deleteById(){
        System.out.print("enter id you want to update : ");
        String id=scanner.nextLine();

        System.out.println("deleting contact is: "+ contactDAO.deleteById(id));
    }
}
