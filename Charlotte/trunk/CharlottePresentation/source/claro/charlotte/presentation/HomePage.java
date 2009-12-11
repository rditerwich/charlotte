package claro.charlotte.presentation;

import org.apache.wicket.markup.html.CSSPackageResource;

public class HomePage extends CharlottePage {

	public HomePage() {
		add(CSSPackageResource.getHeaderContribution(getClass(), "HomePage.css"));
	}

}
