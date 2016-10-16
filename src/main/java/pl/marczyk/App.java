package pl.marczyk;

import com.gargoylesoftware.htmlunit.WebClient;
import pl.marczyk.pages.LoginPage;
import pl.marczyk.status.Status;

import java.io.IOException;

/**
 * Created by mm on 16.10.2016.
 */
public class App {
    final static WebClient client = new WebClient();
    public static void main(String[] args) throws IOException {
        LoginPage loginPage = new LoginPage(client);
        Status status = loginPage.login("kenji89", "");
        System.out.println(status);
    }
}
