package com.example.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class Index {

    private IndexWriter writer;
    
    public Index() throws IOException {
        // this directory will contain the indexes
        Directory indexDirectory = new RAMDirectory();
        
        // create the indexer
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        writer = new IndexWriter(indexDirectory, config);
    }

    public void close() throws IOException {
        writer.close();
    }
    
    private Document getDocument(File file) throws IOException {
        Document document = new Document();
        
        // index file contents
        Field contentField = new TextField("contents",
                  IOUtils.toString(new FileReader(file)), Field.Store.YES);
        // index file name
        Field fileNameField = new StringField("filename",
                  file.getName(),Field.Store.YES);
        // index file path
        Field filePathField = new StoredField("filepath", file.getCanonicalPath());
        
        document.add(contentField);
        document.add(fileNameField);
        document.add(filePathField);
    
        return document;
    }   

    private void indexFile(File file) throws IOException {
        System.out.println("Indexing " + file.getCanonicalPath());
        Document document = getDocument(file);
        writer.addDocument(document);
   }

   public int createIndex(String dataDirPath) throws IOException {
       // get all files in the data directory
       File[] files = new File(dataDirPath).listFiles();
       
       for (File file : files) {
           if ( !file.isDirectory()
                && !file.isHidden()
                && file.exists()
                && file.canRead() ) {
               indexFile(file);
           }
       }
       
       return writer.numDocs();
   }
}
