package Beans;

public class Video {

    private String id;
    private String name;
    private int likes;
    private Type type;

    public enum Type {
        LINK,
        VIDEO
    }

    private String path;

    public Video(String id, String path, String name) {
        this.id = id;
        this.path = path;
        this.name = name;
    }

    public Video(String id, String name, String type, int likes) {
        this.id = id;
        this.name = name;
        if (type.equals(Type.VIDEO.name())){
            this.type = Type.VIDEO;
        } else if (type.equals(Type.LINK.name())){
            this.type = Type.LINK;
        }

        this.likes = likes;
    }

    public Video(String name, Type type) {
        this.name = name;
        this.type = type;
        this.likes = 0;
    }


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

    public void liked(){
        this.likes = likes +1;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
