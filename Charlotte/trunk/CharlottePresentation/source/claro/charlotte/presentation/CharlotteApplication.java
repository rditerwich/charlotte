package claro.charlotte.presentation;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class CharlotteApplication extends WebApplication {

	@Override
	protected void init() {
		mountBookmarkablePage("/home", HomePage.class);
		mountBookmarkablePage("/concept", ConceptPage.class);
		mountBookmarkablePage("/services", ServicesPage.class);
	}
	
	@Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }
}