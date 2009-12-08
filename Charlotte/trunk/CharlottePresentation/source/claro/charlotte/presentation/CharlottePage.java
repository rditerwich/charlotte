package claro.charlotte.presentation;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
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
		add(new RoundedBox("header-panel", getClass(), "images/CornerDarkestOnWhiteLeftTop.gif", "images/CornerDarkestOnWhiteRightTop.gif", null, null).
			add(new Image("logo", PackageResource.get(getClass(), "images/CharlotteLogo.gif"))).
			add(new WebMarkupContainer("header-image-panel").
				add(new RandomImage("header-image1", "people/People%d.jpg", imageGroup)).
				add(new RandomImage("header-image2", "people/People%d.jpg", imageGroup)).
				add(new RandomImage("header-image3", "people/People%d.jpg", imageGroup))));
		add(new BookmarkablePageLink<Void>("home-page", HomePage.class));
		add(new BookmarkablePageLink<Void>("concept-page", ConceptPage.class));
		add(new RoundedBox("footer-panel", getClass(), null, null, "images/CornerDarkestOnWhiteLeftBottom.gif", "images/CornerDarkestOnWhiteRightBottom.gif"));
	}
}