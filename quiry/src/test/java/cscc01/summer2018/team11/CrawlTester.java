package cscc01.summer2018.team11;

import static org.junit.Assert.*;

import org.junit.Test;

import cscc01.summer2018.team11.crawler.CrawlControl;

public class CrawlTester {

	@Test
	public void testMain() throws Exception {
		CrawlControl.main(new String[] {"https://www.researchgate.net/publication/44982277_System_development_life_cycle"});
		
		
	}

}
