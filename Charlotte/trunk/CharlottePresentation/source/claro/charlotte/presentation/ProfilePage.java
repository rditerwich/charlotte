package claro.charlotte.presentation;

import org.apache.wicket.markup.html.CSSPackageResource;

public class ProfilePage extends CharlottePage {

	public ProfilePage() {
		add(CSSPackageResource.getHeaderContribution(getClass(), "ProfilePage.css"));
	}

}
