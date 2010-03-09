package claro.charlotte.presentation;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.image.Image;

import claro.wicket.html.RoundedBox;

public class HomePage extends CharlottePage {

	public HomePage() {
		add(CSSPackageResource.getHeaderContribution(getClass(), "HomePage.css"));
		add(new RoundedBox("panel-listen", getClass(), "images/CornerLightestOnLightLeftTop.gif", "images/CornerLightestOnLightRightTop.gif", "images/CornerLightestOnLightLeftBottom.gif", "images/CornerLightestOnLightRightBottom.gif"));
		add(new RoundedBox("panel-engage", getClass(), "images/CornerLightestOnLightLeftTop.gif", "images/CornerLightestOnLightRightTop.gif", "images/CornerLightestOnLightLeftBottom.gif", "images/CornerLightestOnLightRightBottom.gif"));
		add(new RoundedBox("panel-measure", getClass(), "images/CornerLightestOnLightLeftTop.gif", "images/CornerLightestOnLightRightTop.gif", "images/CornerLightestOnLightLeftBottom.gif", "images/CornerLightestOnLightRightBottom.gif"));
		add(new RoundedBox("small-panel-container-top", getClass(), "images/CornerLightestOnDarkLeftTop.gif", "images/CornerLightestOnDarkRightTop.gif", "images/CornerLightestOnDarkLeftBottom.gif", "images/CornerLightestOnDarkRightBottom.gif").
			add(new Image("widget-sun")).
			add(new Image("widget-universe")).
			add(new Image("widget-planetandmoon")));
		add(new RoundedBox("small-panel-container-bottom", getClass(), "images/CornerLightestOnDarkLeftTop.gif", "images/CornerLightestOnDarkRightTop.gif", "images/CornerLightestOnDarkLeftBottom.gif", "images/CornerLightestOnDarkRightBottom.gif").
			add(new Image("widget-jaws")).
			add(new Image("widget-eggs")).
			add(new Image("widget-web")).
			add(new Image("widget-body")).
			add(new Image("widget-eyes")));
	}	
}
