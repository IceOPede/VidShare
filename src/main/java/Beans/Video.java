package Beans;

import javax.persistence.*;

@Entity
@Table(name = "video")
public class Video {

    private String id;
    private String name;
    private int likes;

    private String path;

    public Video(String id, String path, String name) {
        this.id = id;
        this.path = path;
        this.name = name;
    }

    @Id
    @Column(name="videoId")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
