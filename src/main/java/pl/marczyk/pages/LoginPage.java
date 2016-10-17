package pl.marczyk.pages;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import pl.marczyk.core.Credentials;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mm on 16.10.2016.
 */
public class LoginPage {
    private final Logger LOGGER = Logger.getLogger(LoginPage.class.getName());

    private WebClient client;
    private HtmlPage page;

    public LoginPage(WebClient client) {
        this.client = client;
    }

    public HtmlPage login(Credentials credentials) throws IOException {
        LOGGER.info("Logging in");
        page = client.getPage("https://rapideo.pl");
        List<HtmlForm> forms = page.getForms();
        HtmlForm htmlForm = forms.get(1);
        HtmlElement submit = htmlForm.getElementsByAttribute("button", "type", "submit").get(0);
        HtmlInput loginElement = htmlForm.getInputByName("login");
        loginElement.setValueAttribute(credentials.getUsername());
        HtmlInput passwordElement = htmlForm.getInputByName("password");
        passwordElement.setValueAttribute(credentials.getPassword());
        page.getElementsByTagName("button");
        page = submit.click();
        return page;
    }

    public boolean isLoggedIn(HtmlPage page) {
        DomElement loginBox = page.getElementById("loginBox");
        return loginBox == null;
    }
}
