package claro.charlotte.presentation;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.resource.ContextRelativeResource;
import org.apache.wicket.util.time.Duration;

import claro.wicket.html.RandomImage;
import claro.wicket.html.RandomImageGroup;

public class CharlottePage extends WebPage {
	
	public CharlottePage() {
		super(new Model<Integer>(1));
		RandomImageGroup imageGroup = new RandomImageGroup(Duration.seconds(6), 80, -70);
		add(new WebMarkupContainer("header-background").
			add(new WebMarkupContainer("header-panel").
				add(new Image("logo", new ContextRelativeResource("CharlotteLogo.gif")))).
			add(new Image("round-corner-left-top", new ContextRelativeResource("RoundCornerLeftTop.gif"))).
    		add(new Image("round-corner-right-top", new ContextRelativeResource("RoundCornerRightTop.gif"))).
			add(new WebMarkupContainer("header-image-panel").
				add(new RandomImage("header-image1", "people/People%d.jpg", imageGroup)).
				add(new RandomImage("header-image2", "people/People%d.jpg", imageGroup)).
				add(new RandomImage("header-image3", "people/People%d.jpg", imageGroup)))).
		add(new BookmarkablePageLink<Void>("home-page", HomePage.class)).
		add(new BookmarkablePageLink<Void>("concept-page", ConceptPage.class)).
		add(new WebMarkupContainer("footer-background").
			add(new WebMarkupContainer("footer-panel")).
			add(new Image("round-corner-left-bottom", new ContextRelativeResource("RoundCornerLeftBottom.gif"))).
			add(new Image("round-corner-right-bottom", new ContextRelativeResource("RoundCornerRightBottom.gif"))));
	}
}