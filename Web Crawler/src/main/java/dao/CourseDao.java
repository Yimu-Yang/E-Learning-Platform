package dao;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.sql.*;

import java.io.File;

import model.Course;

public class CourseDao {

    private Connection conn;
    private String tableName;

    public CourseDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // register mysql driver
            File inputFile = new File("./config.xml");
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputFile);
            Element root = document.getRootElement();
            conn = DriverManager.getConnection(root.elementTextTrim("databaseUrl"), root.elementTextTrim("userName"), root.elementTextTrim("password"));
            tableName = root.elementTextTrim("tableName");
        } catch (SQLException | ClassNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    public int insertCourse(Course course) throws SQLException {
        Statement sta = conn.createStatement();
        if (!tableExist()) {
            String createTableSql = "CREATE TABLE " + this.tableName + "(" +
                    "    courseName varchar(255) NOT NULL PRIMARY KEY," +
                    "    provider varchar(255)," +
                    "    price varchar(255)," +
                    "    rating varchar(255)," +
                    "    courseDescription VARCHAR(10000)," +
                    "    imageURL varchar(255)," +
                    "    videoURL varchar(255)," +
                    "    courseTalkURL varchar(255)," +
                    "    courseRedirectURL varchar(255)," +
                    "    courseActualURL varchar(255)" +
                    ")";
            sta.executeUpdate(createTableSql);
        }

        String entryExistSql = "SELECT * FROM " + this.tableName + " WHERE courseName=\"" + modifyQuotation(course.getName()) + "\"";
        ResultSet rs = sta.executeQuery(entryExistSql);
        if (rs.next()) {
            return 1;
        }

        String sql = "INSERT INTO " + this.tableName + " (courseName, provider, price, rating, courseDescription, imageURL, "
                + "videoURL, courseTalkURL, courseRedirectURL, courseActualURL) VALUES (\"" + modifyQuotation(course.getName()) + "\", "
                + "\"" + modifyQuotation(course.getProvider()) + "\", " + "\"" + modifyQuotation(course.getPrice()) + "\", " + "\""
                + modifyQuotation(course.getRating()) + "\", " + "\"" + modifyQuotation(course.getDescription())
                + "\", " + "\"" + course.getImageURL() + "\", " + "\"" + course.getVideoURL() + "\", " + "\""
                + course.getCourseTalkURL() + "\", " + "\"" + course.getCourseRedirectURL() + "\", " + "\""
                + course.getCourseActualURL() + "\")";
        return sta.executeUpdate(sql);
//        try {
//            return sta.executeUpdate(sql);
//        } catch (SQLException e) {
//            System.out.println("error sql: " + sql);
//            e.printStackTrace();
//        }
//        return -1;
    }

    private boolean tableExist() throws SQLException {
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet tables = dbm.getTables(null, null, this.tableName, null);
        if (tables.next()) {
            return true;
        }
        return false;
    }

    public ResultSet runSql(String sql) throws SQLException {
        Statement sta = conn.createStatement();
        return sta.executeQuery(sql);
    }

    public boolean runSql2(String sql) throws SQLException {
        Statement sta = conn.createStatement();
        return sta.execute(sql);
    }

    @Override
    protected void finalize() throws Throwable {
        if (conn != null || !conn.isClosed()) {
            conn.close();
        }
    }

    private String modifyQuotation(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '"') {
                sb.append("'");
            } else {
                sb.append(input.charAt(i));
            }
        }
        return sb.toString();
    }

}
