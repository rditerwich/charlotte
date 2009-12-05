package claro.wicket.html;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.image.ContextImage;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.resource.ContextRelativeResource;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.time.Duration;

public class RandomImage extends ContextImage {

	private static final long serialVersionUID = 1L;
	private static final Map<String, Boolean> existingImages = new HashMap<String, Boolean>();
	private static final Random random = new Random();
	private final RandomImageModel model;
	private final String resourceFormat;

	public RandomImage(String id, String resourceFormat, Duration duration) {
		this(id, resourceFormat, new RandomImageModel(), new RandomImageGroup(duration, 100, 0));
	}
	
	public RandomImage(String id, String resourceFormat, RandomImageGroup group) {
		this(id, resourceFormat, new RandomImageModel(), group);
	}
	
	private RandomImage(String id, String resourceFormat, RandomImageModel model, RandomImageGroup group) {
		super(id, model);
		this.resourceFormat = resourceFormat;
		this.model = model;
		group.add(this);
	}

	public String getResource() {
		return model.getObject();
	}
	
	public void setResource(String resource) {
		model.setObject(resource);
	}
	
	@Override
	protected void onBeforeRender() {
		if (model.getObject() == null) {
			model.setObject(chooseRandomImage());
		}
		super.onBeforeRender();
	}
	
	public String chooseRandomImage() {
		if (model.existingResourceNumbers == null) {
			model.existingResourceNumbers = determineExistingResourceNumbers(resourceFormat);
		}
		if (model.existingResourceNumbers.length == 0) {
			return "";
		}
		int randomIndex = random.nextInt(model.existingResourceNumbers.length);
		int resourceNumber = model.existingResourceNumbers[randomIndex];
		final String resourceName = WebApplication.get().getServletContext().getContextPath() + "/" + String.format(resourceFormat, resourceNumber);
		return resourceName;
	}
	
	private static int[] determineExistingResourceNumbers(String resourceFormat) {
		synchronized (existingImages) {
			List<Integer> resourceNumbers = new ArrayList<Integer>();
			for (int i = 0; i < 100; i++) {
				String resource = String.format(resourceFormat, i);
				Boolean exists = existingImages.get(resource);
				if (exists == null) {
					exists = false;
					try {
						IResourceStream stream = new ContextRelativeResource(resource).getResourceStream();
						try {
							stream.getInputStream();
							exists = true;
						} catch (ResourceStreamNotFoundException e) {
						}
						stream.close();
					} catch (IOException e) {
					}
					existingImages.put(resource, exists);
				}
				if (exists) {
					resourceNumbers.add(i);
				}
			}
			int[] result = new int[resourceNumbers.size()];
			for (int i = 0; i < result.length; i++) {
				result[i] = resourceNumbers.get(i);
			}
			return result;
		}
	}
	
	private static final class RandomImageModel extends Model<String> {
		private static final long serialVersionUID = 1L;
		transient int[] existingResourceNumbers;
	}
}
