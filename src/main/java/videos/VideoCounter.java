package videos;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VideoCounter {

    public int getVideosCount(){
        int count = new File(getClass().getResource("/static/Videos").getPath()).list().length;
        return count;
    }

    public List<String> getVideoPathList(){
        String[] fileList =  new File(getClass().getResource("/static/Videos").getPath()).list();
        return new ArrayList<>(Arrays.asList(fileList));
    }
}
