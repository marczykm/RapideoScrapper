package pl.marczyk.pages;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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
        DomElement loginElement = page.getElementByName("login");
        loginElement.setNodeValue(username);
        DomElement passwordElement = page.getElementByName("password");
        passwordElement.setNodeValue(password);
        page.getElementsByTagName("button");
        DomElement loginButton = getLoginButton(page);
        page = loginButton.click();
        if(isLoggedIn(page))
            return Status.SUCCESS;
        return Status.FAILED;
    }

    public boolean isLoggedIn(HtmlPage page) {
        DomElement userBox = page.getElementById("user-box");
        return userBox != null;
    }

    private DomElement getLoginButton(HtmlPage page){
        DomNodeList<DomElement> buttons = page.getElementsByTagName("button");
        Collection<DomElement> filter = Collections2.filter(buttons, new Predicate() {
            public boolean apply(Object input) {
                return "Zaloguj".equals(((DomElement) input).getTextContent());
            }
        });
        List<DomElement> list = new ArrayList<DomElement>(filter);
        return list.get(0);
    }
}
