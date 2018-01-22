package DB;

import Beans.Person;
import Beans.Video;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private DataSource dataSource;

    public static ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addPerson(Person p) {

        String sql = "INSERT INTO PERSON (name, email, pw) VALUES (?, ?, ?)";

        Connection conn = null;

        try {

            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getPw());
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

    public void updatePerson(Person p) {

    }

    public List<Person> listPerson() {

        List<Person> personList = new ArrayList<>();

        String sql = "SELECT * FROM PERSON";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                personList.add(new Person(
                        rs.getString("PERSONID"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PW")));

            }
            rs.close();
            ps.close();
            return personList;
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

    public Person getPersonById(int id) {

        String sql = "SELECT * FROM PERSON WHERE PERSONID = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            Person person = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                person = new Person(
                        rs.getString("PERSONID"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PW")
                );
            }
            rs.close();
            ps.close();
            return person;
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

    public void removePerson(int id) {

    }

    public Person checkPersonByEmail(String email, String pw) {

        String sql = "SELECT * FROM PERSON WHERE email=? AND pw=?";

        Connection conn = null;

        try {

            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pw);
            ResultSet rs = ps.executeQuery();
            Person person = null;
            if (rs.next()) {
                person = new Person(rs.getString("PERSONID"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PW")
                );
            }
            return person;
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

    public Person checkPersonOnlyByEmail(String email) {

        String sql = "SELECT * FROM PERSON WHERE email=?";

        Connection conn = null;

        try {

            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            Person person = null;
            if (rs.next()) {
                person = new Person(rs.getString("PERSONID"),
                        rs.getString("NAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PW")
                );
            }
            return person;
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

}
