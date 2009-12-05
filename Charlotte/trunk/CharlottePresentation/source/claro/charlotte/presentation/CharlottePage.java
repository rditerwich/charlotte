package claro.charlotte.presentation;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.resource.ContextRelativeResource;
import org.apache.wicket.util.time.Duration;

import claro.wicket.html.RandomImage;
import claro.wicket.html.RandomImageGroup;

public class CharlottePage extends WebPage {
	
	public CharlottePage() {
		super(new Model<Integer>(1));
		RandomImageGroup imageGroup = new RandomImageGroup(Duration.seconds(5), 90, -40);
		add(new WebMarkupContainer("header-background").
			add(new WebMarkupContainer("header").
				add(new Image("logo", "CharlotteLogo.png")).
	    		add(new Label("company-phrase", "Your Eyes on the Web"))).
			add(new WebMarkupContainer("header-images").
				add(new RandomImage("headerimage1", "people/People%d.jpg", imageGroup)).
				add(new RandomImage("headerimage2", "people/People%d.jpg", imageGroup)).
				add(new RandomImage("headerimage3", "people/People%d.jpg", imageGroup))).
			add(new Image("round-corner-right-top", new ContextRelativeResource("RoundCornerRightTop.png"))));
	}
}