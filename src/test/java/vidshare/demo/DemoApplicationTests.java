package vidshare.demo;

import org.junit.Assert;
import org.junit.Test;
import test.VideoCounter;

public class DemoApplicationTests {

	@Test
	public void contextLoads() {

		VideoCounter videoCounter = new VideoCounter();
		Assert.assertEquals(2, videoCounter.getVideosCount());

	}

}
