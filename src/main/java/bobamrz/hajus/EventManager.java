package bobamrz.hajus;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import bobamrz.hajus.domain.Event;
import bobamrz.hajus.domain.Person;

public class EventManager {
	
    public long createAndStoreEvent(String title, Date theDate) {
    	Long eventId;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        eventId = (Long) session.save(theEvent);

        session.getTransaction().commit();
        return eventId;
    }
    
    public long createAndStorePerson(String name, int age) {
    	Long personId;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person person = new Person();
        person.setAge(age);
        person.setFirstname(name);
        person.setLastname("abc");
        personId = (Long) session.save(person);

        session.getTransaction().commit();
        return personId;
    }
    
    public List<Event> listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Event> result = session.createQuery("from Event").list();
        session.getTransaction().commit();
        return result;
    }
    
    public List<Person> listPersons() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Person> result = session.createQuery("from Person").list();
        session.getTransaction().commit();
        return result;
    }
    
    public List<Person> listPersonsWithEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
		List<Person> result = session.createQuery("from Person as p inner join fetch p.events").list();
        session.getTransaction().commit();
        return result;
    }
    
    public void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);
        aPerson.getEvents().add(anEvent);

        session.getTransaction().commit();
    }
    
    public void addEmailToPerson(Long personId, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        // adding to the emailAddress collection might trigger a lazy load of the collection
        aPerson.getEmailAddresses().add(emailAddress);

        session.getTransaction().commit();
    }
    
}
