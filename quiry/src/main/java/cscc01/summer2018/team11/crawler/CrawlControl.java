package cscc01.summer2018.team11.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class CrawlControl {
	@SuppressWarnings("deprecation")
	public static void Crawl(String URL, String userID) throws Exception {
        String crawlStorageFolder = "C:data";
        int numberOfCrawlers = 20;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(1);
        config.setPolitenessDelay(1000);
        config.setIncludeBinaryContentInCrawling(true);
        config.setMaxPagesToFetch(1000);
        

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        String[] toCrawler = new String[2];
        toCrawler[0] = URL;
        toCrawler[1] = userID;
        controller.setCustomData(toCrawler);
        controller.addSeed(URL);
        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(Crawler.class, numberOfCrawlers);
    }
}
