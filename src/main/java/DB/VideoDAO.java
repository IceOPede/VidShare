package DB;

import Beans.Video;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");


    public void addVideo(Video p) {
        String sql = "INSERT INTO VIDEO (NAME, LIKES, TYPE) VALUES (?, ?, ?)";

        Connection conn = null;

        try {

            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getLikes());
            ps.setString(3, p.getType().name());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void updateVideo(Video p) {

        String sql = "UPDATE VIDEO SET LIKES = ? WHERE VIDEOID =?";

        Connection conn = null;

        try {

            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getLikes());
            ps.setString(2, p.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public List<Video> listVideo() {
        List<Video> videoNameList = new ArrayList<>();

        String sql = "SELECT * FROM VIDEO";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                videoNameList.add(new Video(
                        rs.getString("VIDEOID"),
                        rs.getString("NAME"),
                        rs.getString("TYPE"),
                        rs.getInt("LIKES")));

            }
            rs.close();
            ps.close();
            return videoNameList;
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
                        rs.getString("TYPE"),
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

    public void removeVideo(int id) {

    }

    public Video getVideobyName(String name){

        String sql = "SELECT * FROM VIDEO WHERE NAME = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            Video video = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                video = new Video(
                        rs.getString("VIDEOID"),
                        rs.getString("NAME"),
                        rs.getString("TYPE"),
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

    public List<String> getVideoNameList(){

        List<String> videoNameList = new ArrayList<>();

        String sql = "SELECT * FROM VIDEO";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                videoNameList.add(rs.getString("NAME"));
            }
            rs.close();
            ps.close();
            return videoNameList;
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

    public List<Video> getTopVideos(){

        List<Video> videoList = new ArrayList<>();

        String sql = "SELECT * FROM VIDEO ORDER BY likes DESC LIMIT 10";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                videoList.add(new Video(
                        rs.getString("VIDEOID"),
                        rs.getString("NAME"),
                        rs.getString("TYPE"),
                        rs.getInt("LIKES")));

            }
            rs.close();
            ps.close();



            return videoList;
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

}
