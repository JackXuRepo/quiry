package cscc01.summer2018.team11;


import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

import cscc01.summer2018.team11.lucene.Index;
import cscc01.summer2018.team11.lucene.Search;


/**
 * Test Index and Search.
 */
public class Lucene {

    public static void main(String[] args) {
        Index.initialize();

        MockFiles.doc1();
        MockFiles.doc2();
        MockFiles.doc3();
        MockFiles.doc4();
        MockFiles.doc5();

        index();
        search();
    }

    public static void index() {
        try {
            Index.createIndex(MockFiles.fileStorage.values());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void search() {
        Search search = new Search(Index.getIndex());

        // content: me, she, like
        try {
            // search.search("content", "you");
            int n = search.search("you",  -1, -1, -1, "David", null);
            System.out.println("found: " + n);

            for (String id : search.getResults()) {
                System.out.println(id);
            }
        } catch (ParseException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
