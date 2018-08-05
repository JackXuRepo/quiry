package cscc01.summer2018.team11.crawler;


import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


public class CrawlControl {

    public static CrawlController crawl(String url, boolean domainRestricted,
            String userId, String course, int contentType) throws Exception
    {
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder("crawl");
        config.setUserAgentString("Mozilla/5.0 (crawler4j)");
        config.setIncludeBinaryContentInCrawling(true);

        config.setPolitenessDelay(1000);
        config.setMaxDepthOfCrawling(5);
        config.setMaxPagesToFetch(100);

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
        url = Crawler.addProtocol(url);
        controller.addSeed(url);

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        CrawlFactory factory = new CrawlFactory(url, domainRestricted, userId, course, contentType);
        controller.startNonBlocking(factory, 1);

        return controller;
    }

}
