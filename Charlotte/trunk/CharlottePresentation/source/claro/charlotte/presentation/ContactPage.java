package claro.charlotte.presentation;

import org.apache.wicket.markup.html.CSSPackageResource;

public class ContactPage extends CharlottePage {

	public ContactPage() {
		add(CSSPackageResource.getHeaderContribution(getClass(), "ContactPage.css"));
	}

}
