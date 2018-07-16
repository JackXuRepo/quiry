package cscc01.summer2018.team11.lucene;


import java.io.IOException;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import cscc01.summer2018.team11.file.FileInfo2;


public class Index {

    private IndexWriter writer;
    private Directory index;

    public Index() {
        // this directory will contain the indexes
        index = new RAMDirectory();

        // create the index writer
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        try {
            writer = new IndexWriter(index, config);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        writer.close();
    }

    public Directory getIndex() {
        return index;
    }

    public void indexFile(FileInfo2 file) throws IOException {
        Document document = new Document();

        Field idField = new StringField("id", file.getFileId(), Field.Store.YES);
        Field typeField = new StringField("type", file.getFileType().toString(), Field.Store.NO);
        Field titleField = new StringField("title", file.getTitle(), Field.Store.NO);
        Field emailField = new StringField("email", file.getAuthor(), Field.Store.NO);
        Field courseField = new StringField("course", file.getCourse(), Field.Store.NO);

        Field descriptionField = new TextField("description", file.getDescription(), Field.Store.NO);
        Field contentField = new TextField("content", file.getContent(), Field.Store.NO);

        document.add(idField);
        document.add(typeField);
        document.add(titleField);
        document.add(emailField);
        document.add(courseField);
        document.add(descriptionField);
        document.add(contentField);

        writer.addDocument(document);
   }

   public int createIndex(List<FileInfo2> files) throws IOException {
       // index every file given list
       for (FileInfo2 file : files) {
           indexFile(file);
           System.out.println("indexed: " + file.getId() + " " + file.getTitle());
       }
       writer.commit();
       return writer.numDocs();
   }

}
