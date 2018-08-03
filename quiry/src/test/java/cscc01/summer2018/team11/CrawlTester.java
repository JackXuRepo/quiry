package cscc01.summer2018.team11;

import org.junit.Test;

import cscc01.summer2018.team11.crawler.CrawlControl;

public class CrawlTester {

	@Test
	public void testMain() throws Exception {
		CrawlControl.Crawl("https://www.eduers.com/mcat/practicetests/", "2");
		
		
	}

}
