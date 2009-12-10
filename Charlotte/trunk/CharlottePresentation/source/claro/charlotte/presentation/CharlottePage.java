package claro.charlotte.presentation;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ImageButton;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;

import claro.wicket.html.RandomImage;
import claro.wicket.html.RandomImageGroup;
import claro.wicket.html.RoundedBox;

public class CharlottePage extends WebPage {
	
	public CharlottePage() {
		super(new Model<Integer>(1));
		RandomImageGroup imageGroup = new RandomImageGroup(Duration.seconds(6), 80, -70);
		add(CSSPackageResource.getHeaderContribution(getClass(), "Charlotte.css"));
		add(CSSPackageResource.getHeaderContribution(getClass(), "CharlottePage.css"));
		add(new RoundedBox("header-panel", getClass(), "images/CornerDarkestOnWhiteLeftTop.gif", "images/CornerDarkestOnWhiteRightTop.gif", null, null).
			add(new Image("logo")).
			add(new WebMarkupContainer("header-image-panel").
				add(new RandomImage("header-image1", "people/People%d.jpg", imageGroup)).
				add(new RandomImage("header-image2", "people/People%d.jpg", imageGroup)).
				add(new RandomImage("header-image3", "people/People%d.jpg", imageGroup))));
		add(new ImageButton("login-button")).
		add(createPageLink("home-page", HomePage.class));
		add(createPageLink("concept-page", ConceptPage.class));
		add(createPageLink("services-page", ConceptPage.class));
		add(createPageLink("profile-page", ConceptPage.class));
		add(createPageLink("blog-page", ConceptPage.class));
		add(createPageLink("contact-page", ConceptPage.class));
		add(new RoundedBox("footer-panel", getClass(), null, null, "images/CornerDarkestOnWhiteLeftBottom.gif", "images/CornerDarkestOnWhiteRightBottom.gif"));
	}
	
	private Link<?> createPageLink(String id, Class<? extends CharlottePage> pageClass) {
		BookmarkablePageLink<Void> link = new BookmarkablePageLink<Void>(id, pageClass);
		if (getClass().equals(pageClass)) {
			link.add(new AttributeAppender("class", new Model<String>("selected"), ";"));
		}
		return link;
	}
}