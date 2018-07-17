package cscc01.summer2018.team11.file;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class Parser {

    public static String getExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }

    public static int getFileType(String fileName) {
        /* TODO: method stub */
        return FileType.PDF;
    }

    public static String getContent(String fileName) {
        String fileContent;
        String extension = getExtension(fileName);
        if (extension.equals("pdf")) {
            fileContent = pdfFileContent(fileName);
        } else {
            fileContent = fileContent(fileName);
        }
        return fileContent;
    }

    public static String fileContent(String fileName) {
        BufferedReader br = null;
        FileReader fr = null;
        String fileContents = "";

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                fileContents = fileContents + currentLine + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return fileContents;
    }

    public static String pdfFileContent(String fileName) {
        PDDocument pdDoc = null;
        File file = new File(fileName);
        String parsedText = "";
        try {
            pdDoc = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();

            parsedText = pdfStripper.getText(pdDoc);
            pdDoc.close();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e1) {
                e.printStackTrace();
            }

        }
        return parsedText;
    }

}
