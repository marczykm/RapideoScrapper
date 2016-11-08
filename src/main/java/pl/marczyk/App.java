package pl.marczyk;

import com.gargoylesoftware.htmlunit.WebClient;
import pl.marczyk.core.Credentials;
import pl.marczyk.model.File;
import pl.marczyk.pages.UserFilesPage;

import java.util.List;

/**
 * Author: marcin on 08.11.16.
 */
public class App {
    public static void main(String[] args) throws Exception {
        UserFilesPage userFilesPage = new UserFilesPage(new WebClient());
        List<File> standardFiles = userFilesPage.getStandardFiles(new Credentials(args[0], args[1]));
        System.out.println(standardFiles);
    }
}
