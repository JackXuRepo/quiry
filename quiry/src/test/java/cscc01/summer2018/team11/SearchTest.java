package cscc01.summer2018.team11;


import java.io.IOException;

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
//                    .accessLevel(true, true)
//                    .author("David Ferris")
//                    .contentType(false, false, true)
//                    .fileType(false, false, true)
//                    .courses( new String[] {"cscc01", "cscc02"} )
//                    .daysPassed(364)
                    .build();

            // max number of search results to return
            System.out.println("found: " + search.search(100));

            for (String id : search.getResults()) {
                User user = UserService.getUser(FileService.getFileInfo(id).getAuthor());
                System.out.println(id +  " : " + user.getFirstName() + " " + user.getLastName());
            }
        } catch (ParseException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
