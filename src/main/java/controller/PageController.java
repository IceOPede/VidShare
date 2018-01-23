package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/index")
    public String getInternationalPageIndex() {
        return "index";
    }

    @GetMapping("/loginSite")
    public String getInternationalPageVideos() {
        return "login";
    }

    @GetMapping("/register")
    public String getInternationalPageRegister() {
        return "register";
    }
    @GetMapping("/topVideos")
    public String getInternationalPagetopTopVideos() {
        return "topVideos";
    }
    @GetMapping("/uploadSite")
    public String getInternationalPagetopUploadSite() {
        return "uploadSite";
    }

}
