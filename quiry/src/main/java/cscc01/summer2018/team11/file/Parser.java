package cscc01.summer2018.team11.file;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.jsoup.Jsoup;


public class Parser {

    public static int getFileType(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return FileType.toFileType(extension);
    }

    public static String getContent(String filePath) {
        String fileContent;
        File file = new File(filePath);

        switch (getFileType(file.getName())) {
        case FileType.HTML:
            fileContent = htmlContent(file);
            break;
        case FileType.PDF:
            fileContent = pdfContent(file);
            break;
        default:
            fileContent = fileContent(file);
        }

        return fileContent;
    }

    public static String fileContent(File file) {
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                fileContent.append(currentLine).append("\n");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileContent.toString();
    }

    public static String pdfContent(File file) {
        String parsedText = "";

        try (PDDocument doc = PDDocument.load(file)) {
            parsedText = new PDFTextStripper().getText(doc);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return parsedText;
    }

    public static String htmlContent(File file) {
        String fileContent = "";

        try {
            fileContent = Jsoup.parse(file, null).text();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileContent;
    }

}
