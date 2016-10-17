package pl.marczyk;

import com.gargoylesoftware.htmlunit.WebClient;
import pl.marczyk.core.Credentials;
import pl.marczyk.model.File;
import pl.marczyk.pages.LoginPage;
import pl.marczyk.pages.UserFilesPage;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mm on 16.10.2016.
 */
public class App {
    final static WebClient client = new WebClient();
    public static void main(String[] args) throws Exception {
        Logger.getLogger(UserFilesPage.class.getName()).setLevel(Level.ALL);
        Logger.getLogger(LoginPage.class.getName()).setLevel(Level.ALL);

        Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        Logger.getLogger("org.apache.http.client.protocol.ResponseProcessCookies").setLevel(Level.OFF);
        Credentials credentials = new Credentials(args[0], args[1]);

        UserFilesPage userFilesPage = new UserFilesPage(client);
        List<File> standardFiles = userFilesPage.getStandardFiles(credentials);
        System.out.println(standardFiles);
    }
}
