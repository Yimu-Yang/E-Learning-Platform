package driver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;

import dao.CourseDao;

public class DriverTest {

    @Test
    public void testPrint() {
        String output = "test: mvn test";
        assertEquals("test: mvn test", output, "Wrong Result");
    }

//    @Test
//    public void testDriver() throws SQLException {
//        CourseDao courseDao = new CourseDao();
//        ResultSet set = courseDao.runSql("select courseName from course");
//        while (set.next()) {
//            System.out.println(set.getString("courseName"));
//        }
//    }

}
