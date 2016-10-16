package pl.marczyk;

import com.gargoylesoftware.htmlunit.WebClient;
import pl.marczyk.core.File;
import pl.marczyk.pages.LoginPage;
import pl.marczyk.pages.UserFilesPage;
import pl.marczyk.status.Status;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mm on 16.10.2016.
 */
public class App {
    final static WebClient client = new WebClient();
    public static void main(String[] args) throws Exception {
        Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);

        LoginPage loginPage = new LoginPage(client);
        Status status = loginPage.login(args[0], args[1]);
        if ( !Status.SUCCESS.equals(status)){
            throw new Exception("Not logged in");
        }

        UserFilesPage userFilesPage = new UserFilesPage(client);
        List<File> standardFiles = userFilesPage.getStandardFiles();
        System.out.println(standardFiles);
    }
}
