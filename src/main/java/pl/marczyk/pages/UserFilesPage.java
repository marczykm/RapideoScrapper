package pl.marczyk.pages;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import pl.marczyk.core.Credentials;
import pl.marczyk.core.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by mm on 16.10.2016.
 */
public class UserFilesPage {

    private final Logger LOGGER = Logger.getLogger(UserFilesPage.class.getName());

    private WebClient client;
    private HtmlPage page;

    public UserFilesPage(WebClient client) {
        this.client = client;
    }

    public List<File> getStandardFiles(Credentials credentials) throws Exception {
        LoginPage loginPage = new LoginPage(client);
        page = client.getPage("https://rapideo.pl/twoje_pliki?new");
        if (!loginPage.isLoggedIn(page)){
            page = loginPage.login(credentials);
            if (!loginPage.isLoggedIn(page)){
                throw new Exception("Not logged in");
            }
        }

        DomElement standardFiles = page.getElementById("StandardFiles");
        while (standardFiles == null){
            LOGGER.warning("Couldn't retrieve files list! Trying again.");
            page = client.getPage("https://rapideo.pl/twoje_pliki?new");
            standardFiles = page.getElementById("StandardFiles");
        }
        DomNodeList<HtmlElement> tr = standardFiles.getElementsByTagName("tr");
        Collection<File> transform = Collections2.transform(tr, new Function<HtmlElement, File>() {
            public File apply(HtmlElement input) {

                // name and href
                DomNodeList<HtmlElement> label = input.getElementsByTagName("label");
                DomNodeList<HtmlElement> a = label.get(0).getElementsByTagName("a");
                String name = a.get(0).getTextContent();
                File file = new File(name);
                String href = a.get(0).getAttribute("href");
                file.setMainUrl(href);

                List<HtmlElement> fileInfo = input.getElementsByAttribute("div", "class", "fileInfo");
//                fileInfo.get(0)

                return file;
            }
        });

        return new ArrayList<File>(transform);
    }
}
