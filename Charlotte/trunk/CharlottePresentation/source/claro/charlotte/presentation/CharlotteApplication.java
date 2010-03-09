package claro.charlotte.presentation;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class CharlotteApplication extends WebApplication {

	@Override
	protected void init() {
		mountBookmarkablePage("/home", HomePage.class);
//		mountBookmarkablePage("/concept", ConceptPage.class);
//		mountBookmarkablePage("/services", ServicesPage.class);
//		mountBookmarkablePage("/profile", ProfilePage.class);
//		mountBookmarkablePage("/blog", BlogPage.class);
		mountBookmarkablePage("/about", AboutUsPage.class);
		mountBookmarkablePage("/contact", ContactPage.class);
	}
	
	@Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }
}