package pl.marczyk.pages;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import pl.marczyk.status.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mm on 16.10.2016.
 */
public class LoginPage {
    private WebClient client;
    private HtmlPage page;

    public LoginPage(WebClient client) {
        this.client = client;
    }

    public Status login(String username, String password) throws IOException {
        page = client.getPage("http://rapideo.pl");
        List<HtmlForm> forms = page.getForms();
        HtmlForm htmlForm = forms.get(1);
        HtmlElement submit = htmlForm.getElementsByAttribute("button", "type", "submit").get(0);
        HtmlInput loginElement = htmlForm.getInputByName("login");
        loginElement.setValueAttribute(username);
        HtmlInput passwordElement = htmlForm.getInputByName("password");
        passwordElement.setValueAttribute(password);
        page.getElementsByTagName("button");
        page = submit.click();
        if(isLoggedIn(page))
            return Status.SUCCESS;
        return Status.FAILED;
    }

    public boolean isLoggedIn(HtmlPage page) {
        DomElement loginBox = page.getElementById("loginBox");
        return loginBox == null;
    }
}
