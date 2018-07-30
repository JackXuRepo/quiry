package cscc01.summer2018.team11.crawler;

import java.util.Set;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class Crawler extends WebCrawler{
	
	private String domain;


	public void init(int id, CrawlController crawlController) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		super.init(id, crawlController);
	}

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(txt|pdf|html))$");
	
	
	/***
	 * Setting the url configs
	 */
	@Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return FILTERS.matcher(href).matches() && 
        		href.startsWith(this.domain);
	}
	
	/**
	 * processing a webpage
	 */
	@Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("URL: " + url);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            //String text = htmlParseData.getText();
            //String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            for (WebURL link : links){
            	String s = link.toString();
            	if (s.substring(s.length()-3).equals("pdf") || s.substring(s.length()-3).equals("txt")){
            		System.out.println(link);
            	}
            }
        }
   }

    @SuppressWarnings("deprecation")
	@Override
    public void onStart() {
        domain = (String) myController.getCustomData();
    }
	
	
	
}
