package claro.wicket.html;

import org.apache.wicket.Resource;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.image.Image;

public class RoundedBox extends Border {

	private static final long serialVersionUID = 1L;
	
	public RoundedBox(String id, Class<?> scope, String leftTop, String rightTop, String leftBottom, String rightBottom) {
		super(id);
		add(leftTop != null ? new Image("left-top", PackageResource.get(scope, leftTop)) : new WebComponent("left-top").setVisible(false));
		add(rightTop != null ? new Image("right-top", PackageResource.get(scope, rightTop)) : new WebComponent("right-top").setVisible(false));
		add(leftBottom != null ? new Image("left-bottom", PackageResource.get(scope, leftBottom)) : new WebComponent("left-bottom").setVisible(false));
		add(rightBottom != null ? new Image("right-bottom", PackageResource.get(scope, rightBottom)) : new WebComponent("right-bottom").setVisible(false));
	}
	
	public RoundedBox(String id, Resource leftTop, Resource rightTop, Resource leftBottom, Resource rightBottom) {
		super(id);
		add(new Image("left-top", leftTop).setVisible(leftTop != null)).
		add(new Image("right-top", rightTop).setVisible(rightTop != null)).
		add(new Image("left-bottom", leftBottom).setVisible(leftBottom!= null)).
		add(new Image("right-bottom", rightBottom).setVisible(rightBottom != null));
	}
	
	@Override
	protected void onRender(MarkupStream markupStream) {
		getResponse().write("<div style=\"position:relative;padding:0px\" class=\"rounded-box-panel\">");
		super.onRender(markupStream);
		getResponse().write("</div>");
	}
}
