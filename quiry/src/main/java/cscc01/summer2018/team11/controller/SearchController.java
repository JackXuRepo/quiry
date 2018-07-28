package cscc01.summer2018.team11.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cscc01.summer2018.team11.file.FileInfo;
import cscc01.summer2018.team11.file.FileService;
import cscc01.summer2018.team11.lucene.Search;


@RestController
@RequestMapping(value = "/search")
public class SearchController {

	@RequestMapping(value = "/advancedSearch", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<HashMap<String,String>>> doSearch(
			@RequestParam(value="searchText") String searchText,
			@RequestParam(value="author") String author,
			@RequestParam(value="dateUploaded") int dateUploaded,
			@RequestParam(value="instructorSearch") boolean instructorSearch,
			@RequestParam(value="studentSearch") boolean studentSearch,
			@RequestParam(value="pastExams") boolean pastExams,
			@RequestParam(value="notes") boolean notes,
			@RequestParam(value="journals") boolean journals,
			@RequestParam(value="pdfType") boolean pdfType,
			@RequestParam(value="txtType") boolean txtType,
			@RequestParam(value="htmlType") boolean htmlType,
			@RequestParam(value="courses", required = false) String[] courses)
	{
		ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
		List<String> resultIdList;

		System.out.println(searchText);
		System.out.println(author);
		System.out.println(dateUploaded);
		System.out.println(instructorSearch);
		System.out.println(studentSearch);
		System.out.println(pastExams);
		System.out.println(notes);
		System.out.println(journals);
		System.out.println(pdfType);
		System.out.println(txtType);
		System.out.println(htmlType);

		if (courses != null) {
			System.out.println("course : " + courses);
		}

		try {
			Search search = generateSearch(searchText, author, dateUploaded,
					instructorSearch, studentSearch, pastExams, notes, journals,
					pdfType, txtType, htmlType, courses);

			// max number of search results
			System.out.println(search.search(100));

			resultIdList = search.getResults();
		} catch (ParseException | IOException ex) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		System.out.println("File ID Results: "+ resultIdList);
		for(String fileId : resultIdList) {
			FileInfo tempFileInfo = FileService.getFileInfo(fileId);
			try {
				results.add(FileService.parseFileInfo(tempFileInfo));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}

		return ResponseEntity.status(HttpStatus.OK).body(results);
	}

	private static Search generateSearch(String searchText, String author,
			int dateUploaded, boolean instructorSearch, boolean studentSearch,
			boolean pastExams, boolean notes, boolean journals,
			boolean pdfType, boolean txtType, boolean htmlType,
			String[] courses) throws ParseException, IOException
	{
		Search.Builder b = new Search.Builder()
				.searchText(searchText)
				.accessLevel(studentSearch, instructorSearch)
				.contentType(pastExams, notes, journals)
				.fileType(pdfType, txtType, htmlType);

		if (author != null && !author.isEmpty()) {
			b.author(author);
		}
		if (dateUploaded > 0) {
			b.daysPassed(dateUploaded);
		}
		if (courses != null) {
			b.courses(courses);
		}

		return b.build();
	}

}
