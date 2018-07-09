package cscc01.summer2018.team11;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import cscc01.summer2018.team11.file.FileInfo;
import cscc01.summer2018.team11.lucene.Index;
import cscc01.summer2018.team11.lucene.Search;


/**
 * Test Index and Search.
 */
public class Lucene {

    private static Index index = new Index();
    private static List<FileInfo> fileStorage = new ArrayList<>();

    private static FileInfo doc1() {
        String email = "doc1@example.com";
        String title = "doc1";
        String description = "test doc1";
        String course = "cscc01";
        int access = 0;
        String type = "txt";

        String content = "Ever man are put down his very. And marry may table him avoid. Hard " +
                "sell it were into it upon. He forbade affixed parties of assured to me " +
                "windows. Happiness him nor she disposing provision. Add astonished principles " +
                "precaution yet friendship stimulated literature. State thing might stand one " +
                "his plate. Offending or extremity therefore so difficult he on provision. " +
                "Tended depart turned not are. ";

        return new FileInfo(email, title, description, course, access, type, content);
    }

    private static FileInfo doc2() {
        String email = "doc2@abc.com";
        String title = "doc2";
        String description = "test doc2";
        String course = "cscc01";
        int access = 1;
        String type = "html";

        String content = "Led ask possible mistress relation elegance eat likewise debating. By " +
                "message or am nothing amongst chiefly address. The its enable direct men depend " +
                "highly. Ham windows sixteen who inquiry fortune demands. Is be upon sang fond " +
                "must shew. Really boy law county she unable her sister. Feet you off me like " +
                "like six. Among sex are leave law built now. In built table in an rapid blush. " +
                "Merits behind on afraid or warmly. ";

        return new FileInfo(email, title, description, course, access, type, content);
    }

    private static FileInfo doc3() {
        String email = "doc3@xyz.com";
        String title = "doc3";
        String description = "test doc3";
        String course = "cscc01";
        int access = 2;
        String type = "pdf";

        String content = "Article evident arrived express highest men did boy. Mistress sensible " +
                "entirely am so. Quick can manor smart money hopes worth too. Comfort produce " +
                "husband boy her had hearing. Law others theirs passed but wishes. You day real " +
                "less till dear read. Considered use me dispatched melancholy sympathize discretion " +
                "led. Oh feel if up to till like. ";

        return new FileInfo(email, title, description, course, access, type, content);
    }

    public static void main(String[] args) {
        // test with 3 documents
        fileStorage.add( doc1() );
        fileStorage.add( doc2() );
        fileStorage.add( doc3() );

        index();
        search();
    }

    public static void index() {
        try {
            index.createIndex(fileStorage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void search() {
        Search search = new Search(index.getIndex());

        // content: me, she, like
        try {
            TopDocs hits = search.search("content", "like");
            System.out.println("found: " + hits.scoreDocs.length);

            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                Document doc = search.getDocument(scoreDoc);

                // lookup id in FileStorage to locate actual file
                FileInfo file = getFile(doc.get("id"));
                System.out.println(file.getTitle());
            }
        } catch (ParseException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static FileInfo getFile(String id) {
        // use hash map or database lookup
        for (FileInfo file : fileStorage) {
            if (file.getId().equals(id)) {
                return file;
            }
        }
        return null;
    }

}
