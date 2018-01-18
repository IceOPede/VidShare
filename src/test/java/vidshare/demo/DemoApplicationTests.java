package vidshare.demo;

import Beans.Video;
import DB.VideoDAO;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoApplicationTests {

	@Test
	public void contextLoads() {

		ApplicationContext context =
				new ClassPathXmlApplicationContext("Spring-Module.xml");

		VideoDAO videoDAO = (VideoDAO) context.getBean("customerDAO");

		Video video = videoDAO.getVideoById(1);

		System.out.println(video.getName());
	}



}
