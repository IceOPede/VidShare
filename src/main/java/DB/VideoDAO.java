package DB;

import Beans.Video;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VideoDAO implements DAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
    public Video getVideoById(int id) {

        String sql = "SELECT * FROM VIDEO WHERE VIDEOID = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            Video video = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                video = new Video(
                        rs.getString("VIDEOID"),
                        rs.getString("NAME"),
                        rs.getInt("LIKES")
                );
            }
            rs.close();
            ps.close();
            return video;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    @Override
    public void removeVideo(int id) {

    }
}
