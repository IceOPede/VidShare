package DB;

import Beans.Video;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class VideoDAO implements DAO {

    @Override
    public void addVideo(Video p) {

    }

    @Override
    public void updateVideo(Video p) {

    }

    @Override
    public List<Video> listVideo() {
        return null;
    }

    @Override
    public Video getPersonById(int id) {
        return null;
    }

    @Override
    public void removeVideo(int id) {

    }
}
