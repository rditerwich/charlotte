package claro.charlotte.presentation;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class CharlotteApplication extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return CharlottePage.class;
    }
}