package vidshare.demo;

import Beans.Person;
import Beans.Video;
import DB.PersonDAO;
import DB.VideoDAO;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoApplicationTests {

	@Test
	@Ignore
	public void contextLoads() {

		ApplicationContext context =
				new ClassPathXmlApplicationContext("Spring-Module.xml");

		VideoDAO videoDAO = (VideoDAO) context.getBean("videoDAO");

		Video video = videoDAO.getVideoById(1);

		System.out.println(video.getName());
	}

	@Test
	@Ignore
	public void test(){

		ApplicationContext context =
				new ClassPathXmlApplicationContext("Spring-Module.xml");

		PersonDAO personDAO = (PersonDAO) context.getBean("personDAO");

		Person person = personDAO.getPersonById(1);

		System.out.println(person.getEmail());

	}



}
