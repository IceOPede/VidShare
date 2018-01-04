package vidshare.demo;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import test.VideoCounter;

@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {

		VideoCounter videoCounter = new VideoCounter();
		Assert.assertEquals(2, videoCounter.getVideosCount());

	}

}
