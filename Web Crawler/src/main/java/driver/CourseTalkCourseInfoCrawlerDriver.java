package driver;

import dao.CourseDao;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import crawler.CourseTalkCourseInfoCrawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CourseTalkCourseInfoCrawlerDriver {

    public static void main(String[] args) throws Exception {
        // 0 means only crawls on the seed url
        final int MAX_CRAWL_DEPTH = 0;
        final int NUMBER_OF_CRAWELRS = 4;
        final String CRAWL_STORAGE = "./crawlerData/crawl/root";

        /*
         * Instantiate crawl config
         */
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(CRAWL_STORAGE);
        config.setMaxDepthOfCrawling(MAX_CRAWL_DEPTH);

        /*
         * Instantiate controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * Add seed URLs
         */

        // collect course information from collected course pages
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./src/main/resources/crawlerData_courseTalkIndividualCoursePageUrl.txt"));
            String line = reader.readLine();
            while (line != null) {
                controller.addSeed(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        checkTableExist();

        /*
         * Start the crawl.
         */
        controller.start(CourseTalkCourseInfoCrawler.class, NUMBER_OF_CRAWELRS);
    }

    private static void checkTableExist() throws SQLException {
        CourseDao courseDao = new CourseDao();
        Statement sta = courseDao.conn.createStatement();
        if (!courseDao.tableExist()) {
            String createTableSql = "CREATE TABLE " + courseDao.tableName + "(" +
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
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Table already exist. Drop table and create a new one instead?(Yes/No)");
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("no") || input.toLowerCase().equals("n")) {
                System.exit(0);
            } else {
                String dropTableSql = "Drop Table " + courseDao.tableName;
                sta.executeUpdate(dropTableSql);
                String createTableSql = "CREATE TABLE " + courseDao.tableName + "(" +
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
        }
    }

}
