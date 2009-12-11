package claro.charlotte.presentation;

import org.apache.wicket.markup.html.CSSPackageResource;

public class BlogPage extends CharlottePage {

	public BlogPage() {
		add(CSSPackageResource.getHeaderContribution(getClass(), "BlogPage.css"));
	}

}
