package cscc01.summer2018.team11.crawler;


import java.io.InvalidObjectException;

import edu.uci.ics.crawler4j.crawler.CrawlController.WebCrawlerFactory;

import cscc01.summer2018.team11.user.User;
import cscc01.summer2018.team11.user.UserService;


public class CrawlFactory implements WebCrawlerFactory<Crawler> {

	private int accessLevel;
	private int contentType;
	private String course;
	private String userId;
	private String domain;
	private boolean domainRestricted;

	public CrawlFactory(String url, boolean domainRestricted, String userId,
			String course, int contentType) throws InvalidObjectException
	{
		this.domain = Crawler.removeProtocol(url);
		this.userId = userId;
		this.course = course;
		this.contentType = contentType;
		this.domainRestricted = domainRestricted;

		User user = UserService.getUser(userId);
		if (user == null) {
			throw new InvalidObjectException(userId);
		}
		this.accessLevel = user.getAccessLv();
	}

	@Override
	public Crawler newInstance() throws Exception {
		return new Crawler(domain, domainRestricted, userId, accessLevel,
				course, contentType);
	}

}
