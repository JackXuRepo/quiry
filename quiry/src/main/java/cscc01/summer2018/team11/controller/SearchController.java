package cscc01.summer2018.team11.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cscc01.summer2018.team11.lucene.Index;
import cscc01.summer2018.team11.lucene.Search;


@RestController
@RequestMapping(value = "/search")
public class SearchController {

	@RequestMapping(value = "/advancedSearch", method = RequestMethod.GET)
	public ResponseEntity<String> registerUser(
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
			@RequestParam(value="courses") String[] courses){
		
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
		System.out.println(courses);
		
		Search search = new Search(Index.getIndex());
		
//		try {
//			search.search(searchText, contentType, fileType, daysPassed, authorName, courseArr)
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return ResponseEntity.status(HttpStatus.OK).body("Search Concluded");

		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}



}
