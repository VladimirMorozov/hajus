package bobamrz.hajus.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import bobamrz.hajus.HibernateUtil;
import bobamrz.hajus.domain.Event;
import bobamrz.hajus.domain.Person;

public class SomeService {

	private SessionFactory sessionFactory;

	public String getString() {
		return "this is string";
	}
	
    public long createAndStoreEvent(String title, Date theDate) {
    	Session session = sessionFactory.getCurrentSession();
    	Long eventId;
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        eventId = (Long) session.save(theEvent);
        return eventId;
    }
    
    public long createAndStorePerson(String name, int age) {
    	Session session = sessionFactory.getCurrentSession();
    	Long personId;
    	
        Person person = new Person();
        person.setAge(age);
        person.setFirstname(name);
        person.setLastname("abc");
        personId = (Long) session.save(person);

        return personId;
    }
    
    public List<Event> listEvents() {
    	Session session = sessionFactory.getCurrentSession();
        List<Event> result = session.createQuery("from Event").list();
        return result;
    }
    
    public List<Person> listPersons() {
    	Session session = sessionFactory.getCurrentSession();
        List<Person> result = session.createQuery("from Person").list();
        return result;
    }
    
    public List<Person> listPersonsWithEvents() {
    	Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
		List<Person> result = session.createQuery("from Person as p inner join fetch p.events").list();
        return result;
    }
    
    public void addPersonToEvent(Long personId, Long eventId) {
    	Session session = sessionFactory.getCurrentSession();
        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);
        aPerson.getEvents().add(anEvent);
    }
    
    public void addEmailToPerson(Long personId, String emailAddress) {
    	Session session = sessionFactory.getCurrentSession();
        Person aPerson = (Person) session.load(Person.class, personId);
        // adding to the emailAddress collection might trigger a lazy load of the collection
        aPerson.getEmailAddresses().add(emailAddress);
    }


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
