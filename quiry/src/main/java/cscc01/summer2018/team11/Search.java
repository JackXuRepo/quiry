package cscc01.summer2018.team11;


import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;


public class Search {

    private IndexSearcher indexSearcher;

    public Search(Directory index) {
        try {
            IndexReader indexReader = DirectoryReader.open(index);
            indexSearcher = new IndexSearcher(indexReader);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public TopDocs search(String field, String searchQuery) throws ParseException, IOException {
        QueryParser queryParser = new QueryParser(field, new StandardAnalyzer());
        Query query = queryParser.parse(searchQuery);
        return indexSearcher.search(query, 10);
    }

    public Document getDocument(ScoreDoc scoreDoc) throws IOException {
        return indexSearcher.doc(scoreDoc.doc);
    }

}
