package cscc01.summer2018.team11;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFCreator {
	
		//Just a test pdf file creator, not part of project.
	

		public static void main(String args[]) throws IOException {	
			//addPage("/cmshome/make2/test/new.pdf");
			//writePdf("/cmshome/make2/test/new.pdf");
		}
		
		public static void createPdf() {
			PDDocument document = new PDDocument();
			try {
				document.save("/cmshome/make2/test/new.pdf");
				document.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static void addPage(String path) {
			File file = new File(path);
			try {
				PDDocument document = PDDocument.load(file);
				document.addPage(new PDPage());
				document.save(path);
				document.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static void writePdf(String path) {
			File file = new File(path);
			try {
				PDDocument document = PDDocument.load(file);
				PDPage page = document.getPage(0);
				PDPageContentStream contentStream = new PDPageContentStream(document, page);
				contentStream.beginText();
				contentStream.setFont(PDType1Font.HELVETICA, 12);
				contentStream.newLineAtOffset(25, 500);
				
				for (int i=0;i<10;i++) {
					String text = "Test.";
					contentStream.showText(text);
				}
				
				contentStream.endText();
				contentStream.close();
				document.save(path);
				document.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private static String String(int i) {
			// TODO Auto-generated method stub
			return null;
		}
}
