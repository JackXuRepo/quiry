package cscc01.summer2018.team11;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cscc01.summer2018.team11.file.ContentType;
import cscc01.summer2018.team11.file.FileInfo2;
import cscc01.summer2018.team11.file.FileType;
import cscc01.summer2018.team11.lucene.Index;
import cscc01.summer2018.team11.user.User;


/**
 * Mock File Objects.
 * http://www.randompassages.com/
 */
public class MockFiles {

    public static Map<String, FileInfo2> fileStorage = new HashMap<>();
    // public static Map<String, User>  userStorage = new HashMap<>();

    public static FileInfo2 doc1() {
        String userId = "hallsara";
        String firstName = "Sarah";
        String lastName = "Hall";

        int fileId = 1001;
        int uploadDate = 1;
        int fileType = FileType.PDF;
        int contentType = ContentType.JOURNAL;

        String course = "cscc01";
        String title = "Wolf Border";
        String description = "Were you in love with her?";

        String content = "The question is in a way meaningless, she knows, but one must ask. " +
                "Love in such situations is rarely real. Sex is the engine, exalting " +
                "and ruining people, sex and frustration. Love is what people believe is " +
                "worth the path of devastation.";

        User usr = mock(User.class);
        when(usr.getFirstName()).thenReturn(firstName);
        when(usr.getLastName()).thenReturn(lastName);

        FileInfo2 doc = mock(FileInfo2.class);
        when(doc.getId()).thenReturn(fileId);
        when(doc.getFileId()).thenReturn(Integer.toString(fileId));
        when(doc.getAuthor()).thenReturn(userId);
        when(doc.getCourse()).thenReturn(course);
        when(doc.getTitle()).thenReturn(title);
        when(doc.getDescription()).thenReturn(description);
        when(doc.getContent()).thenReturn(content);

        when(doc.getFileType()).thenReturn(fileType);
        when(doc.getContentType()).thenReturn(contentType);
        when(doc.getUploadMs()).thenReturn(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate));

        Index.userStorage.put(userId, usr);
        fileStorage.put(doc.getFileId(), doc);
        return doc;
    }

    public static FileInfo2 doc2() {
        String userId = "houelleb";
        String firstName = "Michel";
        String lastName = "Houellebecq";

        int fileId = 1002;
        int uploadDate = 2;
        int fileType = FileType.TEXT;
        int contentType = ContentType.JOURNAL;

        String course = "cscc02";
        String title = "The Possibility of an Island";
        String description = "During the first part of your life, you only become aware of happiness once you have lost it.";

        String content = "Then an age comes, a second one, in which you already know, at the " +
                "moment when you begin to experience true happiness, that you are, at the end of " +
                "the day, going to lose it. When I met Belle, I understood that I had just " +
                "entered this second age. I also understood that I hadn’t reached the third age, " +
                "in which anticipation of the loss of happiness prevents you from living.";

        User usr = mock(User.class);
        when(usr.getFirstName()).thenReturn(firstName);
        when(usr.getLastName()).thenReturn(lastName);

        FileInfo2 doc = mock(FileInfo2.class);
        when(doc.getId()).thenReturn(fileId);
        when(doc.getFileId()).thenReturn(Integer.toString(fileId));
        when(doc.getAuthor()).thenReturn(userId);
        when(doc.getCourse()).thenReturn(course);
        when(doc.getTitle()).thenReturn(title);
        when(doc.getDescription()).thenReturn(description);
        when(doc.getContent()).thenReturn(content);

        when(doc.getFileType()).thenReturn(fileType);
        when(doc.getContentType()).thenReturn(contentType);
        when(doc.getUploadMs()).thenReturn(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate));

        Index.userStorage.put(userId, usr);
        fileStorage.put(doc.getFileId(), doc);
        return doc;
    }

    public static FileInfo2 doc3() {
        String userId = "houelleb";
        String firstName = "Michel";
        String lastName = "Houellebecq";

        int fileId = 1003;
        int uploadDate = 3;
        int fileType = FileType.HTML;
        int contentType = ContentType.JOURNAL;

        String course = "cscc03";
        String title = "The Possibility of an Island";
        String description = "Your only chance of survival";

        String content = "If you are sincerely smitten, lies in hiding this fact from the woman " +
                "you love, of feigning a casual detachment under all circumstances. What sadness " +
                "there is in this simple observation! What an accusation against man! However, it " +
                "had never occurred to me to contest this law, nor to imagine disobeying it: love " +
                "makes you weak, and the weaker of the two is oppressed, tortured and finally " +
                "killed by the other, who in his or her turn oppresses, tortures and kills without " +
                "having evil intentions, without even getting pleasure from it, with complete " +
                "indifference; that’s what men, normally, call love.";

        User usr = mock(User.class);
        when(usr.getFirstName()).thenReturn(firstName);
        when(usr.getLastName()).thenReturn(lastName);

        FileInfo2 doc = mock(FileInfo2.class);
        when(doc.getId()).thenReturn(fileId);
        when(doc.getFileId()).thenReturn(Integer.toString(fileId));
        when(doc.getAuthor()).thenReturn(userId);
        when(doc.getCourse()).thenReturn(course);
        when(doc.getTitle()).thenReturn(title);
        when(doc.getDescription()).thenReturn(description);
        when(doc.getContent()).thenReturn(content);

        when(doc.getFileType()).thenReturn(fileType);
        when(doc.getContentType()).thenReturn(contentType);
        when(doc.getUploadMs()).thenReturn(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate));

        Index.userStorage.put(userId, usr);
        fileStorage.put(doc.getFileId(), doc);
        return doc;
    }

    public static FileInfo2 doc4() {
        String userId = "mitchell";
        String firstName = "David";
        String lastName = "Mitchell";

        int fileId = 1004;
        int uploadDate = 29;
        int fileType = FileType.PDF;
        int contentType = ContentType.NOTES;

        String course = "cscc04";
        String title = "Ghostwritten";
        String description = "They look so fine, and young, and wrapped up in each other.";

        String content = "Love is so fresh and clean at that age. Don’t you think? Margareta! " +
                "I’m surprised at you! We both know there’s no such thing as love! What do you " +
                "call it? Tatyana snuffed our her cigarette. That sly smile. Mutations of " +
                "wanting. You’re not serious. I am quite serious. Look at those kids. The boys " +
                "want to get the girls into bed so they can have their corks popped off their " +
                "bottles and forth. When a man blows his nose you don’t call it love. Why get " +
                "all misty-eyed when a man blows another part of his anatomy? As for the girls, " +
                "they’re either going along for the ride because they can get the things they " +
                "want from the boys, or else maybe they enjoy being in bed too. Thought I doubt " +
                "it. I never knew an eighteen-year-old boy who didn’t drop the egg off his " +
                "spoon at the first fence.";

        User usr = mock(User.class);
        when(usr.getFirstName()).thenReturn(firstName);
        when(usr.getLastName()).thenReturn(lastName);

        FileInfo2 doc = mock(FileInfo2.class);
        when(doc.getId()).thenReturn(fileId);
        when(doc.getFileId()).thenReturn(Integer.toString(fileId));
        when(doc.getAuthor()).thenReturn(userId);
        when(doc.getCourse()).thenReturn(course);
        when(doc.getTitle()).thenReturn(title);
        when(doc.getDescription()).thenReturn(description);
        when(doc.getContent()).thenReturn(content);

        when(doc.getFileType()).thenReturn(fileType);
        when(doc.getContentType()).thenReturn(contentType);
        when(doc.getUploadMs()).thenReturn(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate));

        Index.userStorage.put(userId, usr);
        fileStorage.put(doc.getFileId(), doc);
        return doc;
    }

    public static FileInfo2 doc5() {
        String userId = "mantelhi";
        String firstName = "Hilary";
        String lastName = "Mantel";

        int fileId = 1005;
        int uploadDate = 30;
        int fileType = FileType.TEXT;
        int contentType = ContentType.NOTES;

        String course = "mgta01";
        String title = "Wolf Hall";
        String description = "Christ, he thinks, by my age I ought to know.";

        String content = "You don’t get on by being original. You don’t get on by being bright. " +
                "You don’t get on by being strong. You get on by being a subtle crook; somehow " +
                "he thinks that’s what Norris is, and he feels an irrational dislike taking " +
                "root, and he tries to dismiss it, because he prefers his dislikes rational, " +
                "but after all, these circumstances are extreme, the cardinal in the mud, the " +
                "humiliating tussle to get him back in the saddle, the talking, talking on the " +
                "barge, and worse, the talking, talking on his knees, as if Wolsey’s " +
                "unravelling, in a great unweaving of scarlet thread that might lead you back " +
                "into a scarlet labyrinth, with a dying monster at its heart.";

        User usr = mock(User.class);
        when(usr.getFirstName()).thenReturn(firstName);
        when(usr.getLastName()).thenReturn(lastName);

        FileInfo2 doc = mock(FileInfo2.class);
        when(doc.getId()).thenReturn(fileId);
        when(doc.getFileId()).thenReturn(Integer.toString(fileId));
        when(doc.getAuthor()).thenReturn(userId);
        when(doc.getCourse()).thenReturn(course);
        when(doc.getTitle()).thenReturn(title);
        when(doc.getDescription()).thenReturn(description);
        when(doc.getContent()).thenReturn(content);

        when(doc.getFileType()).thenReturn(fileType);
        when(doc.getContentType()).thenReturn(contentType);
        when(doc.getUploadMs()).thenReturn(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate));

        Index.userStorage.put(userId, usr);
        fileStorage.put(doc.getFileId(), doc);
        return doc;
    }

}
