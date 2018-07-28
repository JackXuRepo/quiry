package cscc01.summer2018.team11.lucene;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import cscc01.summer2018.team11.file.FileInfo;
import cscc01.summer2018.team11.user.User;
import cscc01.summer2018.team11.user.UserService;


public class Index {

    private static IndexWriter writer;
    private static Directory index;

    public static void initialize() {
        try {
            // this directory will contain the indexes
            index = FSDirectory.open(Paths.get("lucene"));
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }

        // create the index writer
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        try {
            writer = new IndexWriter(index, config);
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void close() throws IOException {
        writer.close();
    }

    public static Directory getIndex() {
        return index;
    }

    public static void indexFile(FileInfo file) throws IOException {
        Document document = new Document();

        Field idField = new StringField("id", file.getFileId(), Field.Store.YES);
        Field titleField = new StringField("title", file.getTitle(), Field.Store.NO);
        Field descriptionField = new TextField("description", file.getDescription(), Field.Store.NO);
        Field contentField = new TextField("content", file.getContent(), Field.Store.NO);
        Field courseField = new StringField("course", file.getCourse(), Field.Store.NO);

        Field fileTField = new IntPoint("fileType", file.getFileType());
        Field contentTField = new IntPoint("contentType", file.getContentType());
        Field accessField = new IntPoint("accessLevel", file.getAccessLevel());

        Field dateField = new LongPoint("date", file.getUploadMs());

        String author = file.getAuthor();
        User user = UserService.getUser(author);
        Field authorField = new StringField("author", author, Field.Store.NO);
        Field firstNField = new StringField("firstName", user.getFirstName(), Field.Store.NO);
        Field lastNField = new StringField("lastName", user.getLastName(), Field.Store.NO);

        document.add(idField);
        document.add(titleField);
        document.add(courseField);
        document.add(descriptionField);
        document.add(contentField);

        document.add(fileTField);
        document.add(contentTField);
        document.add(accessField);
        document.add(dateField);

        document.add(authorField);
        document.add(firstNField);
        document.add(lastNField);

        writer.addDocument(document);
        writer.commit();

        System.out.println("indexed " + file.getId());
    }

    public static void removeFile(int fileId) throws IOException {
        removeFile(Integer.toString(fileId));
    }

    public static void removeFile(String fileId) throws IOException {
        writer.deleteDocuments(new Term("id", fileId));
        writer.commit();
    }

    public static int createIndex(Collection<FileInfo> collection) throws IOException {
        // index every file given list
        for (FileInfo file : collection) {
            indexFile(file);
            System.out.println("indexed: " + file.getId() + " " + file.getTitle());
        }
        return writer.numDocs();
    }

}
