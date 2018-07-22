package cscc01.summer2018.team11.lucene;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import cscc01.summer2018.team11.file.ContentType;
import cscc01.summer2018.team11.file.FileType;


public class Search {

    private IndexSearcher indexSearcher;
    private TopDocs hits;

    public Search(Directory index) {
        try {
            IndexReader indexReader = DirectoryReader.open(index);
            indexSearcher = new IndexSearcher(indexReader);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int search(String searchText, int contentType, int fileType,
            int daysPassed, String authorName, String[] courseArr)
                    throws ParseException, IOException
    {
        BooleanQuery.Builder b = new BooleanQuery.Builder();
        StandardAnalyzer analyzer = new StandardAnalyzer();

        MultiFieldQueryParser searchTextParser = new MultiFieldQueryParser(
                new String[] {"title", "description", "content"}, analyzer);
        b.add(searchTextParser.parse(searchText), BooleanClause.Occur.SHOULD);

        MultiFieldQueryParser authorParser = new MultiFieldQueryParser(
                new String[] {"author", "firstName", "lastName"}, analyzer);
        b.add(authorParser.parse(authorName), BooleanClause.Occur.SHOULD);

        // TODO: use QueryParser if courseArr is String
        if (courseArr != null) {
            for (String course : courseArr) {
                b.add(new TermQuery(new Term("course", course.toLowerCase())), BooleanClause.Occur.SHOULD);
            }
        }

        if (fileType != FileType.NONE) {
            b.add(IntPoint.newExactQuery("fileType", fileType), BooleanClause.Occur.MUST);
        }

        if (contentType != ContentType.NONE) {
            b.add(IntPoint.newExactQuery("contentType", contentType), BooleanClause.Occur.MUST);
        }

        if (daysPassed > 0) {
            long currentMs = System.currentTimeMillis();
            long pastMs = currentMs - TimeUnit.DAYS.toMillis(daysPassed);
            b.add(LongPoint.newRangeQuery("date", pastMs , currentMs), BooleanClause.Occur.MUST);
        }

        hits = indexSearcher.search(b.build(), 100);
        return hits.scoreDocs.length;
    }

    public int search(String field, String searchQuery) throws ParseException, IOException {
        QueryParser queryParser = new QueryParser(field, new StandardAnalyzer());
        Query query = queryParser.parse(searchQuery);
        hits = indexSearcher.search(query, 10);
        return hits.scoreDocs.length;
    }

    public List<String> getResults() throws IOException {
        List<String> results = new ArrayList<>();
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            String fileId = indexSearcher.doc(scoreDoc.doc).get("id");
            results.add(fileId);
        }
        return results;
    }

    public TopDocs getRawResults() {
        return hits;
    }

}
