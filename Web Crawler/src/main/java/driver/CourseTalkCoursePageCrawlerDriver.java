package driver;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import crawler.CourseTalkCoursePageCrawler;

public class CourseTalkCoursePageCrawlerDriver {

    public static void main(String[] args) throws Exception {
        // 0 means only crawls on the seed url
        final int MAX_CRAWL_DEPTH = 100;
        final int NUMBER_OF_CRAWELRS = 4;
        final String CRAWL_STORAGE = "/Users/yimuyang/IdeaProjects/Web_Crawler/crawlerData/crawl/root";

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

        // collect actual course pages' URLs on CourseTalk
        controller.addSeed("https://www.coursetalk.com/search");
        int seedsNum = 100;
        for (int i = 1; i < seedsNum; i++) {
            String pageUrl = "https://www.coursetalk.com/search?filters=&page=" + seedsNum + "&sort=-ct_score";
            controller.addSeed(pageUrl);
        }

        /*
         * Start the crawl.
         */
        controller.start(CourseTalkCoursePageCrawler.class, NUMBER_OF_CRAWELRS);
    }

}
