package claro.charlotte.presentation;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.image.Image;

import claro.wicket.html.RoundedBox;

public class ServicesPage extends CharlottePage {

	public ServicesPage() {
		add(CSSPackageResource.getHeaderContribution(getClass(), "ServicesPage.css"));
		for (int i = 1; i <= 5; i++) {
			add(new RoundedBox("header" + i, getClass(), "images/CornerDarkOnLightLeftTop.gif", "images/CornerDarkOnLightRightTop.gif", null, null));
		}
		for (int i = 1; i <= 5; i++) {
			add(new RoundedBox("footer" + i, getClass(), null, null, "images/CornerDarkOnLightLeftBottom.gif", "images/CornerDarkOnLightRightBottom.gif").
				add(new Image("footer" + i + "-image")));
		}
	}
}
