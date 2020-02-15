package crawler;

import dao.CourseDao;
import model.Course;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Selector;

public class CourseTalkCourseInfoCrawler extends WebCrawler {

    Set<String> visitedUrls = new HashSet<>();
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * Specify whether the given url should be crawled or not based on
     * the crawling logic. Here URLs with extensions css, js etc will not be visited
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        boolean notVisited = visitedUrls.add(href);
        return notVisited && href.contains("https://www.coursetalk.com/providers") && href.contains("/courses/");
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

            // insert the course's data into database
            try {
                this.insertCourseIntoDatabase(doc, url);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // write the current web page's URL to text file
//            try {
//                this.writeVisitedUrlToFile(url);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            // write the course's data to text file
//            try {
//                this.writeCourseDataToFile(doc, url);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    private void writeVisitedUrlToFile(String url) throws IOException {
        File file = new File("./src/main/resources/shouldVisited_CourseTalkCourseIndividualPage.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write(url + "\n");
        fr.close();
    }

    private void writeCourseDataToFile(Document doc, String url) throws IOException {
        File file = new File("./src/main/resources/crawlerData_courseTalkCourseInfo.txt");
        FileWriter fr = new FileWriter(file, true);

        Element title_provider = doc.select("a").select(".course-header-ng__course-actions__link").select(".js-review-this-course-btn").first();
        if (title_provider != null) {
            fr.write(title_provider.attr("data-analytics-course") + ", ");
            fr.write(title_provider.attr("data-analytics-provider") + ", ");
        } else {
            fr.write("N/A, ");
            fr.write("N/A, ");
        }

        Element priceP = doc.select("p").select(".course-details-panel__course-cost").first();
        if (priceP != null) {
            Element price = Selector.select("b", priceP).first();
            if (price != null) {
                fr.write(price.text() + ", ");
            } else {
                fr.write("N/A, ");
            }
        } else {
            fr.write("N/A, ");
        }

        Element ratingDiv = doc.select("div").select(".course-header-ng__rating__stars").first();
        if (ratingDiv != null) {
            Element rating = Selector.select("span.sr-only", ratingDiv).first();
            if (rating != null) {
                fr.write(rating.text() + ", ");
            } else {
                fr.write("N/A, ");
            }
        } else {
            fr.write("N/A, ");
        }

        Element courseInfo = doc.select("div").select(".course-info__description").first();
        if (courseInfo != null) {
            Element descriptionSpan = Selector.select("span.more-less-trigger__text--full", courseInfo).first();
            if (descriptionSpan != null) {
                fr.write(descriptionSpan.text() + " ");
            } else {
                fr.write("N/A, ");
            }
        } else {
            fr.write("N/A, ");
        }

        Element image = doc.select("img").select(".img-responsive").first();
        if (image != null) {
            fr.write(image.attr("src") + ", ");
        } else {
            fr.write("N/A, ");
        }

        Element videoDiv = doc.select("div").select(".video-wrapper").first();
        if (videoDiv != null) {
            Element video = Selector.select("iframe", videoDiv).first();
            Element video2 = Selector.select("source", videoDiv).first();
            if (video != null) {
                fr.write("https:" + video.attr("src") + ", ");
            } else if (video2 != null) {
                if (video2.attr("src").contains("https:")) {
                    fr.write(video2.attr("src") + ", ");
                } else {
                    fr.write("https:" + video2.attr("src") + ", ");
                }
            } else {
                fr.write("N/A, ");
            }
        } else {
            fr.write("N/A, ");
        }

        if (url != null) {
            fr.write(url + ", ");
        } else {
            fr.write("N/A, ");
        }

        Element courseRedirectUrl = doc.select("a").select(".btn").select(".btn-success").select(".btn-size-medium").select(".btn-block").select(".btn-course-page").select(".js-outbound").select(".js-auth-popup_open").last();
        if (courseRedirectUrl != null) {
            fr.write("https://www.coursetalk.com" + courseRedirectUrl.attr("href") + ", ");
        } else {
            fr.write("N/A");
        }

        String courseActualUrl = this.sendGet("https://www.coursetalk.com" + courseRedirectUrl.attr("href"));
        fr.write(courseActualUrl);

        fr.write("\n");
        fr.close();
    }

    private void insertCourseIntoDatabase(Document doc, String url) throws SQLException {
        Course course = new Course();

        Element title_provider = doc.select("a").select(".course-header-ng__course-actions__link").select(".js-review-this-course-btn").first();
        if (title_provider != null) {
            course.setName(title_provider.attr("data-analytics-course"));
            course.setProvider(title_provider.attr("data-analytics-provider"));
        } else {
            course.setName("N/A");
            course.setProvider("N/A");
        }

        Element priceP = doc.select("p").select(".course-details-panel__course-cost").first();
        if (priceP != null) {
            Element price = Selector.select("b", priceP).first();
            if (price != null) {
                course.setPrice(price.text());
            } else {
                course.setPrice("N/A");
            }
        } else {
            course.setPrice("N/A");
        }

        Element ratingDiv = doc.select("div").select(".course-header-ng__rating__stars").first();
        if (ratingDiv != null) {
            Element rating = Selector.select("span.sr-only", ratingDiv).first();
            if (rating != null) {
                course.setRating(rating.text());
            } else {
                course.setRating("N/A");
            }
        } else {
            course.setRating("N/A");
        }

        Element courseInfo = doc.select("div").select(".course-info__description").first();
        if (courseInfo != null) {
            Element descriptionSpan = Selector.select("span.more-less-trigger__text--full", courseInfo).first();
            if (descriptionSpan != null) {
                course.setDescription(descriptionSpan.text());
            } else {
                course.setDescription("N/A");
            }
        } else {
            course.setDescription("N/A");
        }

        Element image = doc.select("img").select(".img-responsive").first();
        if (image != null) {
            course.setImageURL(image.attr("src"));
        } else {
            course.setImageURL("N/A");
        }

        Element videoDiv = doc.select("div").select(".video-wrapper").first();
        if (videoDiv != null) {
            Element video = Selector.select("iframe", videoDiv).first();
            Element video2 = Selector.select("source", videoDiv).first();
            if (video != null) {
                course.setVideoURL("https:" + video.attr("src"));
            } else if (video2 != null) {
                if (video2.attr("src").contains("https:")) {
                    course.setVideoURL(video2.attr("src"));
                } else {
                    course.setVideoURL("https:" + video2.attr("src"));
                }
            } else {
                course.setVideoURL("N/A");
            }
        }

        else {
            course.setVideoURL("N/A");
        }

        if (url != null) {
            course.setCourseTalkURL(url);
        } else {
            course.setCourseTalkURL("N/A");
        }

        Element courseRedirectUrl = doc.select("a").select(".btn").select(".btn-success").select(".btn-size-medium").select(".btn-block").select(".btn-course-page").select(".js-outbound").select(".js-auth-popup_open").last();
        if (courseRedirectUrl != null) {
            course.setCourseRedirectURL("https://www.coursetalk.com" + courseRedirectUrl.attr("href"));
        } else {
            course.setCourseRedirectURL("N/A");
        }

        String courseActualUrl = this.sendGet(course);
        course.setCourseActualURL(courseActualUrl);

        CourseDao repo = new CourseDao();
        repo.insertCourse(course);
    }

    private String sendGet(Course course) {
        HttpGet request = new HttpGet(course.getCourseRedirectURL());
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                Document doc = Jsoup.parseBodyFragment(result);
                Element courseActualUrl = doc.select("a").first();
                return courseActualUrl.attr("href");
            } else {
                return "N/A";
            }
        } catch (Exception e) {
            return "N/A";
        }
    }

    private String sendGet(String url) {
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                Document doc = Jsoup.parseBodyFragment(result);
                Element courseActualUrl = doc.select("a").first();
                return courseActualUrl.attr("href");
            } else {
                return "N/A";
            }
        } catch (Exception e) {
            return "N/A";
        }
    }

}
