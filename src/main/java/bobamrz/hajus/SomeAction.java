package bobamrz.hajus;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bobamrz.hajus.domain.Event;
import bobamrz.hajus.domain.Person;
import bobamrz.hajus.service.SomeService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Hello world!
 *
 */
public class SomeAction extends ActionSupport
{
	
	private static final Logger LOG = LoggerFactory.getLogger(SomeAction.class);
	private String message;
	private Integer num;
	private List<Event> events;
	private List<Person> persons;
	
	private SomeService someService;
	
    
	@Override
	public String execute() throws Exception {
		//try {
			EventManager manager = new EventManager();
			long eventId = someService.createAndStoreEvent("My event", new Date());
			long personId = someService.createAndStorePerson("James", 50);
			someService.addPersonToEvent(personId, eventId);
			events = someService.listEvents();
			//persons = manager.listPersons();
			persons = someService.listPersonsWithEvents();
			
		/*} catch (Exception e) {
			LOG.error("bad", e);
		}*/
		
		
		
		if (message == null) {
			message = "";
		}
		
		message = message + someService.getString();
		
		if (num != null) {
			for (int i = 0; i < num; i++) {
				message += someService.getString();
			}
		}
		
		LOG.debug("message: {}", message);
		return Action.SUCCESS;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public SomeService getSomeService() {
		return someService;
	}


	public void setSomeService(SomeService someService) {
		this.someService = someService;
	}


	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}


	public List<Event> getEvents() {
		return events;
	}


	public void setEvents(List<Event> events) {
		this.events = events;
	}


	public List<Person> getPersons() {
		return persons;
	}


	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
}
