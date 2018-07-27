package cscc01.summer2018.team11;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import cscc01.summer2018.team11.database.FileDAO;
import cscc01.summer2018.team11.file.ContentType;
import cscc01.summer2018.team11.file.FileInfo;
import cscc01.summer2018.team11.file.FileType;
import cscc01.summer2018.team11.user.AccessLevel;


/**
 * Mock File Objects. Run this to populate File database.
 */
public class MockFiles {

    public static FileInfo file1() {
        int fileId = 1001;
        int uploadDate = 0;
        int accessLevel = AccessLevel.INSTRUCTOR;
        int fileType = FileType.PDF;
        int contentType = ContentType.EXAM;

        String userId = "houelleb";
        String fileName = "a2.pdf";
        String course = "cscc01";
        String title = "Assignment 2";
        String description = "Read first C01Assignment2 handout\nThen read assignmentHanout.pdf";

        File file = new File("files/" + fileId + "/" + fileName);
        return new FileInfo.Builder().fileId(fileId).userId(userId).fileType(fileType)
                .contentType(contentType).accessLevel(accessLevel).course(course)
                .title(title).description(description).path(file.getAbsolutePath())
                .uploadMs(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate))
                .build();
    }

    public static FileInfo file2() {
        int fileId = 1002;
        int uploadDate = 1;
        int accessLevel = AccessLevel.INSTRUCTOR;
        int fileType = FileType.TEXT;
        int contentType = ContentType.JOURNAL;

        String userId = "mitchell";
        String fileName = "big.txt";
        String course = "cscc02";
        String title = "this file";
        String description = "This site contains technical papers, essays, reports, software, and other materials by Peter Norvig.";

        File file = new File("files/" + fileId + "/" + fileName);
        return new FileInfo.Builder().fileId(fileId).userId(userId).fileType(fileType)
                .contentType(contentType).accessLevel(accessLevel).course(course)
                .title(title).description(description).path(file.getAbsolutePath())
                .uploadMs(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate))
                .build();
    }

    public static FileInfo file3() {
        int fileId = 1003;
        int uploadDate = 2;
        int accessLevel = AccessLevel.STUDENT;
        int fileType = FileType.HTML;
        int contentType = ContentType.NOTES;

        String userId = "mantelhi";
        String fileName = "columbia.html";
        String course = "cscc01";
        String title = "Columbia University";
        String description = "I was laid off from Columbia U in 2011. My last day was June 30th, 2011, and then I spent the next 3 months cleaning up after myself:";

        File file = new File("files/" + fileId + "/" + fileName);
        return new FileInfo.Builder().fileId(fileId).userId(userId).fileType(fileType)
                .contentType(contentType).accessLevel(accessLevel).course(course)
                .title(title).description(description).path(file.getAbsolutePath())
                .uploadMs(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate))
                .build();
    }

    public static FileInfo file4() {
        int fileId = 1004;
        int uploadDate = 7;
        int accessLevel = AccessLevel.STUDENT;
        int fileType = FileType.HTML;
        int contentType = ContentType.NOTES;

        String userId = "ferrisjo";
        String fileName = "cscc63.html";
        String course = "mgta01";
        String title = "Boo Hoo Hoo!";
        String description = "Welcome to my machine-generated, generic home page. There is nothing here yet.";

        File file = new File("files/" + fileId + "/" + fileName);
        return new FileInfo.Builder().fileId(fileId).userId(userId).fileType(fileType)
                .contentType(contentType).accessLevel(accessLevel).course(course)
                .title(title).description(description).path(file.getAbsolutePath())
                .uploadMs(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate))
                .build();
    }

    public static FileInfo file5() {
        int fileId = 1005;
        int uploadDate = 8;
        int accessLevel = AccessLevel.INSTRUCTOR;
        int fileType = FileType.TEXT;
        int contentType = ContentType.JOURNAL;

        String userId = "houelleb";
        String fileName = "gpl3.txt";
        String course = "mgta02";
        String title = "A Quick Guide to GPLv3";
        String description = "The GPL in other formats: plain text, Texinfo, LaTeX, standalone HTML, ODF, Docbook v4 or v5, Markdown, and RTF.";

        File file = new File("files/" + fileId + "/" + fileName);
        return new FileInfo.Builder().fileId(fileId).userId(userId).fileType(fileType)
                .contentType(contentType).accessLevel(accessLevel).course(course)
                .title(title).description(description).path(file.getAbsolutePath())
                .uploadMs(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate))
                .build();
    }

    public static FileInfo file6() {
        int fileId = 1006;
        int uploadDate = 29;
        int accessLevel = AccessLevel.INSTRUCTOR;
        int fileType = FileType.PDF;
        int contentType = ContentType.NOTES;

        String userId = "mitchell";
        String fileName = "kiwi.pdf";
        String course = "mgta02";
        String title = "Krisanth Technologies";
        String description = "Over the past 11 years, we have successfully inspected, installed and commissioned aviation refueling equipment for numerous clients including airlines, oil companies, offshore rigs and military organizations.";

        File file = new File("files/" + fileId + "/" + fileName);
        return new FileInfo.Builder().fileId(fileId).userId(userId).fileType(fileType)
                .contentType(contentType).accessLevel(accessLevel).course(course)
                .title(title).description(description).path(file.getAbsolutePath())
                .uploadMs(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate))
                .build();
    }

    public static FileInfo file7() {
        int fileId = 1007;
        int uploadDate = 30;
        int accessLevel = AccessLevel.STUDENT;
        int fileType = FileType.TEXT;
        int contentType = ContentType.JOURNAL;

        String userId = "mantelhi";
        String fileName = "readme.txt";
        String course = "psya02";
        String title = "CSC01TeamProjectRepo11";
        String description = "Here's where you'll find this repository's source files. To give your users an idea of what they'll find here, add a description to your repository.";

        File file = new File("files/" + fileId + "/" + fileName);
        return new FileInfo.Builder().fileId(fileId).userId(userId).fileType(fileType)
                .contentType(contentType).accessLevel(accessLevel).course(course)
                .title(title).description(description).path(file.getAbsolutePath())
                .uploadMs(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate))
                .build();
    }

    public static FileInfo file8() {
        int fileId = 1008;
        int uploadDate = 365;
        int accessLevel = AccessLevel.STUDENT;
        int fileType = FileType.PDF;
        int contentType = ContentType.EXAM;

        String userId = "ferrisjo";
        String fileName = "sakila.pdf";
        String course = "psya03";
        String title = "Sakila Sample Database";
        String description = "This document describes the Sakila sample database—its history, installation, structure and usage.";

        File file = new File("files/" + fileId + "/" + fileName);
        return new FileInfo.Builder().fileId(fileId).userId(userId).fileType(fileType)
                .contentType(contentType).accessLevel(accessLevel).course(course)
                .title(title).description(description).path(file.getAbsolutePath())
                .uploadMs(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate))
                .build();
    }

    public static FileInfo file9() {
        int fileId = 1009;
        int uploadDate = 366;
        int accessLevel = AccessLevel.STUDENT;
        int fileType = FileType.HTML;
        int contentType = ContentType.EXAM;

        String userId = "ferrisjo";
        String fileName = "wiki.html";
        String course = "psya03";
        String title = "Wikipedia, the free encyclopedia";
        String description = "This HTML tutorial contains hundreds of HTML examples. With our online HTML editor, you can edit the HTML, and click on a button to view the result.";

        File file = new File("files/" + fileId + "/" + fileName);
        return new FileInfo.Builder().fileId(fileId).userId(userId).fileType(fileType)
                .contentType(contentType).accessLevel(accessLevel).course(course)
                .title(title).description(description).path(file.getAbsolutePath())
                .uploadMs(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(uploadDate))
                .build();
    }

    public static void main(String[] args) throws SQLException, IOException {
//        UserStorage.initialize();
//        FileStorage.initialize();
//
//        Index.initialize();
//        Index.indexFile( file1() );
//        Index.indexFile( file2() );
//        Index.indexFile( file3() );
//        Index.indexFile( file4() );
//        Index.indexFile( file5() );
//        Index.indexFile( file6() );
//        Index.indexFile( file7() );
//        Index.indexFile( file8() );
//        Index.indexFile( file9() );
//        Index.close();

        FileDAO fileDao = new FileDAO();
        fileDao.updateFile( file1() );
        fileDao.updateFile( file2() );
        fileDao.updateFile( file3() );
        fileDao.updateFile( file4() );
        fileDao.updateFile( file5() );
        fileDao.updateFile( file6() );
        fileDao.updateFile( file7() );
        fileDao.updateFile( file8() );
        fileDao.updateFile( file9() );

        for (int id : fileDao.getAllFileIds()) {
            System.out.println(id + " : " + fileDao.getFileByFileId(id));
        }
    }

}
