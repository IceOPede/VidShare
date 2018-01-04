package vidshare.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import test.VideoCounter;

import java.util.List;

@RestController
public class App {

    public App() {
    }

    @RequestMapping("/getVideos")
    @ResponseBody
    public List<String> videoService(){
        VideoCounter videoCounter = new VideoCounter();
        return videoCounter.getVideoPathList();
    }
}
