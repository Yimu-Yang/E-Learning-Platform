package driver;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import crawler.CourseTalkCourseInfoCrawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        /*
         * Start the crawl.
         */
        controller.start(CourseTalkCourseInfoCrawler.class, NUMBER_OF_CRAWELRS);
    }

}
