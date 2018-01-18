package vidshare.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import test.VideoCounter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView upload(@RequestParam("file") MultipartFile file) throws IOException {

        VideoCounter videoCounter = new VideoCounter();
        File cFile = new File("C:\\_ws\\VidShare\\src\\main\\resources\\static\\Videos\\"+ (videoCounter.getVideosCount()+1)+".mp4");
        cFile.createNewFile();
        FileOutputStream out = new FileOutputStream(cFile);
        out.write(file.getBytes());
        return new ModelAndView("redirect:Index.html");
    }

}
