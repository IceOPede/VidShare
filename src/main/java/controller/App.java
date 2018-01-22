package controller;

import Beans.Person;
import Beans.Video;
import DB.PersonDAO;
import DB.VideoDAO;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.List;

@RestController
public class App {

    private static final String ACCESS_TOKEN = "93paGsy2SdkAAAAAAAABQLklTHl9Ok_x0G7rFYH7thCYUqp0GX9ReYf6lEsZXdlv";

    public App() {
    }

    @RequestMapping("/getVideos")
    @ResponseBody
    public List<Video> videoService() throws DbxException {
        VideoDAO videoDAO = (VideoDAO) VideoDAO.context.getBean("videoDAO");

        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        List<Video> videoList = videoDAO.listVideo();

        for (Video video: videoList){
            if (video.getType().name().equals(Video.Type.VIDEO.name())){
                ListFolderResult result = client.files().listFolder("/static/Videos");
                for (Metadata metadata : result.getEntries()){
                    if (metadata.getName().equals(video.getName())){
                        video.setUrl(client.files().getTemporaryLink(metadata.getPathLower()).getLink());
                    }
                }
            }
        }

        return videoList;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam("file") MultipartFile file) throws IOException, DbxException {

        VideoDAO videoDAO = (VideoDAO) VideoDAO.context.getBean("videoDAO");

        List<String> videoList =  videoDAO.getVideoNameList();

//        File cFile = new File(getClass().getResource("/static/Videos").getPath() + "/" + (videoList.size() + 1) + ".mp4");
//        cFile.createNewFile();
//        FileOutputStream out = new FileOutputStream(cFile);
//        out.write(file.getBytes());

        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        InputStream in =  new BufferedInputStream(file.getInputStream());
        FileMetadata metadata = client.files().uploadBuilder("/static/Videos/"+(videoList.size() + 1) + ".mp4").uploadAndFinish(in);

        Video video = new Video(""+(videoList.size() + 1) + ".mp4", Video.Type.VIDEO);
        videoDAO.addVideo(video);

        return new ModelAndView("redirect:index.html");
    }

    @RequestMapping(value = "/uploadURL", method = RequestMethod.POST)
    public ModelAndView uploadURL(@RequestParam("URL") String url) throws IOException {

        VideoDAO videoDAO = (VideoDAO) VideoDAO.context.getBean("videoDAO");

        Video video = new Video("https://www.youtube.com/embed/"+url.split("\\W*((?i)watch\\?v=(?-i))\\W*")[1], Video.Type.LINK);

        videoDAO.addVideo(video);

        return new ModelAndView("redirect:index.html");
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("pw") String pw) {

        Person person = new Person(null, name, email, pw);

        PersonDAO personDAO = (PersonDAO) PersonDAO.context.getBean("personDAO");

        personDAO.addPerson(person);

        return new ModelAndView("redirect:index.html");
    }

//    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
//    public ModelAndView login(@RequestParam("email") String email, @RequestParam("pw") String pw) {
//
//        PersonDAO personDAO = (PersonDAO) PersonDAO.context.getBean("personDAO");
//
//        Person check = personDAO.checkPersonByEmail(email, pw);
//
//        if (check != null) {
//            System.out.println("Success");
//            return new ModelAndView("redirect:index.html");
//        } else {
//            System.out.println("Error");
//            return new ModelAndView("redirect:login.html");
//        }
//    }

    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public int like(@RequestParam("name") String name) {

        VideoDAO videoDAO = (VideoDAO) VideoDAO.context.getBean("videoDAO");

        Video video = videoDAO.getVideobyName(name);
        video.liked();
        videoDAO.updateVideo(video);

        return video.getLikes();
    }

    @RequestMapping(value = "/allTopVideos")
    public List<Video> topVideos() throws DbxException {

        VideoDAO videoDAO = (VideoDAO) VideoDAO.context.getBean("videoDAO");

        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        List<Video> videoList = videoDAO.getTopVideos();

        for (Video video: videoList){
            if (video.getType().name().equals(Video.Type.VIDEO.name())){
                ListFolderResult result = client.files().listFolder("/static/Videos");
                for (Metadata metadata : result.getEntries()){
                    if (metadata.getName().equals(video.getName())){
                        video.setUrl(client.files().getTemporaryLink(metadata.getPathLower()).getLink());
                    }
                }
            }
        }


        return videoList;
    }

}
