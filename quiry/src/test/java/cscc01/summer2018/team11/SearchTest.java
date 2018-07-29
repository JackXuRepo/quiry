package cscc01.summer2018.team11;


import java.io.IOException;
import java.util.Map;

import org.apache.lucene.queryparser.classic.ParseException;

import cscc01.summer2018.team11.file.FileService;
import cscc01.summer2018.team11.lucene.Index;
import cscc01.summer2018.team11.lucene.Search;
import cscc01.summer2018.team11.user.User;
import cscc01.summer2018.team11.user.UserService;


/**
 * Test Index and Search.
 */
public class SearchTest {

    public static void main(String[] args) {
        Index.initialize();

        try {
            Search search = new Search.Builder()
                    .searchText("you")
                    .accessLevel(true, true)
                    .contentType(true, true, true)
                    .fileType(true, true, true)
//                    .author("David Ferris")
//                    .courses( new String[] {"cscc01", "cscc02"} )
//                    .daysPassed(364)
                    .build();

            // max number of search results to return
            System.out.println("found: " + search.search(100));

            for (Map.Entry<String, String> entry : search.getResults().entrySet()) {
                String id = entry.getKey();
                User user = UserService.getUser(FileService.getFileInfo(id).getAuthor());
                System.out.println(id +  " : " + user.getFirstName() + " " + user.getLastName() + "\t" + entry.getValue());
            }

        } catch (ParseException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
