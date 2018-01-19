package controller;

import Beans.Person;
import Beans.Video;
import DB.PersonDAO;
import DB.VideoDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import videos.VideoCounter;

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
    public List<Video> videoService() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        VideoDAO videoDAO = (VideoDAO) context.getBean("videoDAO");

        List<Video> videoList = videoDAO.listVideo();

        return videoList;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView upload(@RequestParam("file") MultipartFile file) throws IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        VideoDAO videoDAO = (VideoDAO) context.getBean("videoDAO");

        List<String> videoList =  videoDAO.getVideoNameList();

        File cFile = new File(getClass().getResource("/static/Videos").getPath() + "/" + (videoList.size() + 1) + ".mp4");
        cFile.createNewFile();
        FileOutputStream out = new FileOutputStream(cFile);
        out.write(file.getBytes());

        Video video = new Video(""+(videoList.size() + 1) + ".mp4");

        videoDAO.addVideo(video);

        return new ModelAndView("redirect:Index.html");
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("pw") String pw) {

        Person person = new Person(null, name, email, pw);

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        PersonDAO personDAO = (PersonDAO) context.getBean("personDAO");

        personDAO.addPerson(person);

        return new ModelAndView("redirect:Index.html");
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("pw") String pw) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        PersonDAO personDAO = (PersonDAO) context.getBean("personDAO");

        boolean check = personDAO.checkPersonByEmail(email, pw);

        if (check) {
            System.out.println("success");
        } else {
            System.out.println("Error");
        }

        return new ModelAndView("redirect:Index.html");
    }

    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public int like(@RequestParam("name") String name) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        VideoDAO videoDAO = (VideoDAO) context.getBean("videoDAO");

        Video video = videoDAO.getVideobyName(name);
        video.liked();
        videoDAO.updateVideo(video);
        return video.getLikes();
    }

}
