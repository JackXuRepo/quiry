package cscc01.summer2018.team11.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Set;
import java.util.regex.Pattern;

import cscc01.summer2018.team11.file.FileGetter;
import cscc01.summer2018.team11.file.FileInfo;
import cscc01.summer2018.team11.file.FileService;
import cscc01.summer2018.team11.user.User;
import cscc01.summer2018.team11.user.UserService;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class Crawler extends WebCrawler{
	
	private String domain;
	private String userID;
	private User user;


	public void init(int id, CrawlController crawlController) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		super.init(id, crawlController);
	}

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(txt|pdf))$");
	
	
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
            Set<WebURL> urls = htmlParseData.getOutgoingUrls();
            for(WebURL link:urls){
            	String s = link.toString();
            	if (s.substring(s.length()-3).equals("pdf")){
            		try {
						URL myURL = new URL(s);
						String fileTitle = (myURL.getFile()).substring(((myURL.getFile()).lastIndexOf('/')) + 1);
						System.out.println(fileTitle);
						FileInfo fileInfo = new FileInfo.Builder()
                                				.userId(userID)
                                				.accessLevel(user.getAccessLv())
                                				.description("undefined")
                                				.title(fileTitle)
                                				.contentType(-1)
                                				.fileType(0)
                                				.course("undefined")
                                				.build();
						File localFile = FileGetter.createFile(fileInfo.getFileId(), fileTitle);
                        FileOutputStream fos = new FileOutputStream(localFile);
                        InputStream URLinput = myURL.openStream();
                        byte[] bytes = new byte[1024];
                        int byteLength;
                        while ((byteLength = URLinput.read(bytes)) != -1) {
                            fos.write(bytes, 0, byteLength);
                        }
                        fos.close();
                        URLinput.close();
                        // complete add file to database
                        fileInfo.setPath(localFile.getAbsolutePath());
                        System.out.println("able to get absolute path");
                        FileService.addFile(fileInfo);
                    } catch (Exception ex) {
                        //FileGetter.deleteFile(fileInfo.getFileId());
                        // respond error message
                        ex.printStackTrace();
                        //String response = ex.getMessage();
                    }

            	}
            	else if (s.substring(s.length()-3).equals("txt")){
            		try {
						URL myURL = new URL(s);
						String fileTitle = (myURL.getFile()).substring(((myURL.getFile()).lastIndexOf('/')) + 1);
						System.out.println(fileTitle);
						FileInfo fileInfo = new FileInfo.Builder()
                                				.userId(userID)
                                				.accessLevel(user.getAccessLv())
                                				.description("undefined")
                                				.title(fileTitle)
                                				.contentType(-1)
                                				.fileType(1)
                                				.course("undefined")
                                				.build();
						File localFile = FileGetter.createFile(fileInfo.getFileId(), fileTitle);
                        FileOutputStream fos = new FileOutputStream(localFile);
                        InputStream URLinput = myURL.openStream();
                        byte[] bytes = new byte[1024];
                        int byteLength;
                        while ((byteLength = URLinput.read(bytes)) != -1) {
                            fos.write(bytes, 0, byteLength);
                        }
                        fos.close();
                        URLinput.close();
                        // complete add file to database
                        fileInfo.setPath(localFile.getAbsolutePath());
                        System.out.println("able to get absolute path");
                        FileService.addFile(fileInfo);
                    } catch (Exception ex) {
                        //FileGetter.deleteFile(fileInfo.getFileId());
                        // respond error message
                        ex.printStackTrace();
                        //String response = ex.getMessage();
                    }

            	}
            }
        }
            	//String html = htmlParseData.getHtml()
    }

    @SuppressWarnings("deprecation")
	@Override
    public void onStart() {
        domain = ((String[])myController.getCustomData())[0];
        userID = ((String[])myController.getCustomData())[1];
        user = UserService.getUser(userID);
    }
	
	
}
