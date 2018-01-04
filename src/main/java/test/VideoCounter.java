package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VideoCounter {

    public int getVideosCount(){
        int count = new File("D:\\_WS_IntelliJ\\VidShare\\src\\main\\resources\\static\\Videos").list().length;
        return count;
    }

    public List<String> getVideoPathList(){
        String[] fileList =  new File("D:\\_WS_IntelliJ\\VidShare\\src\\main\\resources\\static\\Videos").list();
        return new ArrayList<>(Arrays.asList(fileList));
    }
}
