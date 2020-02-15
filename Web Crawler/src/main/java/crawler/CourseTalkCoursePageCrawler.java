package crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

public class CourseTalkCoursePageCrawler extends WebCrawler {

    Set<String> visitedUrls = new HashSet<>();
    Set<String> storedCoursePage = new HashSet<>();

    /**
     * Specify whether the given url should be crawled or not based on
     * the crawling logic. Here URLs with extensions css, js etc will not be visited
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        boolean notVisited = visitedUrls.add(href);
        return notVisited && href.contains("https://www.coursetalk.com/search");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by the program.
     */
    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText(); //extract text from page
            String html = htmlParseData.getHtml(); //extract html from page
            Document doc = Jsoup.parseBodyFragment(html);
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            // write current web page's URL to text file
            try {
                this.writeVisitedUrlToFile(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // write course's links to text file
            try {
                this.writeCoursePageToFile(doc, url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeVisitedUrlToFile(String url) throws IOException {
        File file = new File("./src/main/resources/shouldVisited_CourseTalkCourseSearchPage.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write(url + "\n");
        fr.close();
    }

    private void writeCoursePageToFile(Document doc, String url) throws IOException {
        File file = new File("./src/main/resources/crawlerData_courseTalkIndividualCoursePageUrl.txt");
        FileWriter fr = new FileWriter(file, true);
        Elements courseLinks = doc.select("a").select(".link-unstyled").select(".js-course-search-result");
        for (Element courseLink : courseLinks) {
            String coursePageUrl = "https://www.coursetalk.com" + courseLink.attr("href");
            if (!storedCoursePage.contains(coursePageUrl)) {
                storedCoursePage.add(coursePageUrl);
                fr.write(coursePageUrl + "\n");
            }
        }
        fr.close();
    }

}
