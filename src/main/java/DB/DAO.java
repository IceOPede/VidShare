package DB;

import Beans.Video;

import java.util.List;

public interface DAO {

    public void addVideo(Video p);
    public void updateVideo(Video p);
    public List<Video> listVideo();
    public Video getVideoById(int id);
    public void removeVideo(int id);

}
