package cscc01.summer2018.team11;


import cscc01.summer2018.team11.crawler.CrawlControl;
import cscc01.summer2018.team11.file.ContentType;
import cscc01.summer2018.team11.lucene.Index;

import edu.uci.ics.crawler4j.crawler.CrawlController;


public class CrawlTester {

	public static void main(String[] args) throws Exception {
		Index.initialize();
		CrawlController c = CrawlControl.crawl("https://www.utsc.utoronto.ca/~nick/cscC63/185/",
				true, "ferrisjo", "CSCC01", ContentType.EXAM);
		c.waitUntilFinish();
		Index.close();
	}

}
