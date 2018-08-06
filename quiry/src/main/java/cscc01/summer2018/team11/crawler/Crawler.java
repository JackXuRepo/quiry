package cscc01.summer2018.team11.crawler;


import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import cscc01.summer2018.team11.file.FileGetter;
import cscc01.summer2018.team11.file.FileInfo;
import cscc01.summer2018.team11.file.FileService;
import cscc01.summer2018.team11.file.FileType;
import cscc01.summer2018.team11.file.Parser;


public class Crawler extends WebCrawler {

    private boolean domainRestricted;
    private String domain;
    private String userId;
    private String course;
    private int accessLevel;
    private int contentType;

    // private final static Pattern FILTERS = Pattern.compile(".*(\\.(txt|pdf|html|htm))$");

    public Crawler(String domain, boolean domainRestricted, String userId,
            int accessLevel, String course, int contentType)
    {
        this.domain = domain;
        this.domainRestricted = domainRestricted;
        this.userId = userId;
        this.accessLevel = accessLevel;
        this.contentType = contentType;
        this.course = course;
    }

    /**
     * Removes the protocol portion of an url so the domain can be compared.
     */
    public static String removeProtocol(String url) {
        int index = url.indexOf("://");
        if (index != -1) {
            url = url.substring(index + 3);
        }
        return url;
    }

    /**
     * Adds the protocol portion of an url so the domain can be compared.
     */
    public static String addProtocol(String url) {
        if (!url.contains("://")) {
            url = "http://" + url;
        }
        return url;
    }

    /**
     * Removes the charset content and returns the contentType strings.
     */
    public static String removeCharSet(String contentType) {
        int index = contentType.indexOf(";");
        if (index > 0) {
            contentType = contentType.substring(0, index);
        }
        return contentType;
    }

    /**
     * Returns the file name from the input url.
     */
    public static String urlToName(String url, int type) {
        // special case '/' at the end
        if (url.charAt(url.length() - 1) == '/') {
            url = url.substring(0, url.length() - 1);
        }

        // remove everything before '/'
        int index = url.lastIndexOf('/');
        if (index > 0) {
            url = url.substring(index);
        }

        // add file extention
        if (Parser.getFileType(url) != type) {
            url = url + "." + FileType.toString(type);
        }
        return url;
    }

    /**
     * Determines if URL should be visited by crawler.
     * Filtered by domain and duplicates.
     */
    @Override
    public boolean shouldVisit(Page refPage, WebURL url) {
        if (domainRestricted) {
            String href = url.getURL().toLowerCase();
            return removeProtocol(href).startsWith(domain);
        }
        return super.shouldVisit(refPage, url);
    }

    /**
     * Process a page after it is fetched.
     */
    @Override
    public void visit(Page page) {
        String pageType = page.getContentType();
        int fileType = FileType.NONE;

        if (pageType.contains("text/html")) {
            fileType = FileType.HTML;
        } else if (pageType.contains("text/plain")) {
            fileType = FileType.TEXT;
        } else if (pageType.contains("application/pdf")) {
            fileType = FileType.PDF;
        } else {
            return;
        }

        WebURL weburl = page.getWebURL();
        String title = weburl.getAnchor();
        String desc = weburl.getURL();

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            title = htmlParseData.getTitle();

            StringBuilder sb = new StringBuilder(desc);
            for (Map.Entry<String, String> entry : htmlParseData.getMetaTags().entrySet()) {
                sb.append("\n").append(entry.getKey()).append(" : ").append(entry.getValue());
            }
            desc = sb.toString();
        }

        String fileName = urlToName(weburl.getPath(), fileType);
        if (title.isEmpty()) {
            title = fileName;
        }

        FileInfo fileInfo = new FileInfo.Builder()
                .userId(userId)
                .accessLevel(accessLevel)
                .description(desc)
                .title(title)
                .contentType(contentType)
                .fileType(fileType)
                .course(course)
                .build();
        File localFile = FileGetter.createFile(fileInfo.getFileId(), fileName);

        try (FileOutputStream fos = new FileOutputStream(localFile)) {
            fos.write(page.getContentData());

        } catch (Exception ex) {
            FileGetter.deleteFile(fileInfo.getFileId());
            // respond error message
            ex.printStackTrace();
        }

        fileInfo.setPath(localFile.getAbsolutePath());
        FileService.addFile(fileInfo);
    }

}
