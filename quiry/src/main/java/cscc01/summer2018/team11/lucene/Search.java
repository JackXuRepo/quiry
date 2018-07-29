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
import cscc01.summer2018.team11.user.AccessLevel;


public class Search {

    private IndexSearcher indexSearcher;
    private Query query;
    private TopDocs hits;

    private Search(Query query) throws IOException {
        Directory index = Index.getIndex();
        IndexReader indexReader = DirectoryReader.open(index);
        indexSearcher = new IndexSearcher(indexReader);
        this.query = query;
    }

    public int search(int maxResults) throws IOException {
        hits = indexSearcher.search(query, maxResults);
        return hits.scoreDocs.length;
    }

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


    public static class Builder {

        BooleanQuery.Builder b = new BooleanQuery.Builder();
        StandardAnalyzer analyzer = new StandardAnalyzer();

        public Search build() throws IOException {
            return new Search(b.build());
        }

        public Builder searchText(String searchText) throws ParseException {
            MultiFieldQueryParser searchTextParser = new MultiFieldQueryParser(
                    new String[] {"title", "description", "content"}, analyzer);
            b.add(searchTextParser.parse(searchText), BooleanClause.Occur.MUST); // MUST
            return this;
        }

        public Builder author(String author) throws ParseException {
            // manually split the search term
            String[] words = author.split("[^a-zA-Z0-9']+");

            BooleanQuery.Builder authorQuery = new BooleanQuery.Builder();
            for (String str : words) {
//                    .add(new QueryParser("author", analyzer).parse(author), BooleanClause.Occur.SHOULD)
//                    .add(new QueryParser("firstName", analyzer).parse(author), BooleanClause.Occur.SHOULD)
//                    .add(new QueryParser("lastName", analyzer).parse(author), BooleanClause.Occur.SHOULD)
                authorQuery.add(new TermQuery(new Term("author", str)), BooleanClause.Occur.SHOULD)
                    .add(new TermQuery(new Term("firstName", str)), BooleanClause.Occur.SHOULD)
                    .add(new TermQuery(new Term("lastName", str)), BooleanClause.Occur.SHOULD);
            }
            b.add(authorQuery.build(), BooleanClause.Occur.MUST);
//            MultiFieldQueryParser authorParser = new MultiFieldQueryParser(
//                    new String[] {"author", "firstName", "lastName"}, analyzer);
//            b.add(authorParser.parse(author), BooleanClause.Occur.MUST);
            return this;
        }

        public Builder courses(String[] courseArr) {
            BooleanQuery.Builder courseQuery = new BooleanQuery.Builder();
            for (String course : courseArr) {
                courseQuery.add(new TermQuery(new Term("course", course.toLowerCase())), BooleanClause.Occur.SHOULD);
            }
            b.add(courseQuery.build(), BooleanClause.Occur.MUST);
            return this;
        }

        public Builder fileType(boolean pdf, boolean txt, boolean html) {
            List<Integer> values = new ArrayList<>(3);
            if (pdf) {
                values.add(FileType.PDF);
            }
            if (txt) {
                values.add(FileType.TEXT);
            }
            if (html) {
                values.add(FileType.HTML);
            }
            b.add(IntPoint.newSetQuery("fileType", values), BooleanClause.Occur.MUST);
            return this;
        }

        public Builder contentType(boolean exam, boolean notes, boolean journal) {
            List<Integer> values = new ArrayList<>(3);
            if (exam) {
                values.add(ContentType.EXAM);
            }
            if (notes) {
                values.add(ContentType.NOTES);
            }
            if (journal) {
                values.add(ContentType.JOURNAL);
            }
            b.add(IntPoint.newSetQuery("contentType", values), BooleanClause.Occur.MUST);
            return this;
        }

        public Builder daysPassed(int days) {
            long currentMs = System.currentTimeMillis();
            long pastMs = currentMs - TimeUnit.DAYS.toMillis(days);
            b.add(LongPoint.newRangeQuery("date", pastMs , currentMs), BooleanClause.Occur.MUST);
            return this;
        }

        public Builder accessLevel(boolean student, boolean instructor) {
            List<Integer> values = new ArrayList<>(3);
            if (student) {
                values.add(AccessLevel.STUDENT);
            }
            if (instructor) {
                values.add(AccessLevel.INSTRUCTOR);
            }
            b.add(IntPoint.newSetQuery("accessLevel", values), BooleanClause.Occur.MUST);
            return this;
        }

    }

}
