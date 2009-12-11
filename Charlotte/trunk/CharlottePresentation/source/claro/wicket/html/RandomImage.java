package claro.wicket.html;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.PackageResourceStream;
import org.apache.wicket.util.time.Duration;

public class RandomImage extends Image {

	private static final long serialVersionUID = 1L;
	private static final Map<String, int[]> existingResourceNumbersByResourceFormat = new HashMap<String, int[]>();
	private static final Map<String, Boolean> existingImages = new HashMap<String, Boolean>();
	private static final Random random = new Random();
	private final SessionState model;
	private final String resourceFormat;
	private final RandomImageGroup group;
	private final Class<?> scope;

	public RandomImage(String id, Class<?> scope, String resourceFormat, Duration duration) {
		this(id, scope, resourceFormat, getOrCreateSessionState(id, scope, resourceFormat), new RandomImageGroup(duration, 100, 0));
	}
	
	public RandomImage(String id, Class<?> scope, String resourceFormat, RandomImageGroup group) {
		this(id, scope, resourceFormat, getOrCreateSessionState(id, scope, resourceFormat), group);
	}
	
	private RandomImage(String id, Class<?> scope, String resourceFormat, SessionState model, RandomImageGroup group) {
		super(id, model);
		this.scope = scope;
		this.resourceFormat = resourceFormat;
		this.model = model;
		this.group = group;
		group.add(this);
	}

	public ResourceReference getResource() {
		return model.getObject();
	}
	
	public void setResource(ResourceReference resource) {
		model.setObject(resource);
		setImageResourceReference(resource);
		System.out.println("Setting resource to: " + resource);
	}
	
	private static SessionState getOrCreateSessionState(String id, Class<?> scope, String resourceFormat) {
		HttpSession session = ((ServletWebRequest) RequestCycle.get().getRequest()).getHttpServletRequest().getSession();
		String key = "RandomImageModel-" + id;
		Object attr = session.getAttribute(key);
		if (attr instanceof SessionState) {
			return (SessionState) attr;
		}
		SessionState state = new SessionState();
		state.existingResourceNumbers = existingResourceNumbersByResourceFormat.get(resourceFormat);
		if (state.existingResourceNumbers == null) {
			state.existingResourceNumbers = determineExistingResourceNumbers(scope, resourceFormat);
			existingResourceNumbersByResourceFormat.put(resourceFormat, state.existingResourceNumbers);
		}
		session.setAttribute(key, state);
		return state;
	}
	
	@Override
	protected void onBeforeRender() {
		if (model.getObject() == null) {
			group.chooseRandomImages();
		}
		super.onBeforeRender();
	}
	
	public ResourceReference chooseRandomImage() {
		if (model.existingResourceNumbers.length == 0) {
			return new ResourceReference(scope, "NoImage");
		}
		int randomIndex = random.nextInt(model.existingResourceNumbers.length);
		int resourceNumber = model.existingResourceNumbers[randomIndex];
		final String resourceName = String.format(resourceFormat, resourceNumber);
		return new ResourceReference(scope, resourceName);
	}
	
	private static int[] determineExistingResourceNumbers(Class<?> scope, String resourceFormat) {
		synchronized (existingImages) {
			List<Integer> resourceNumbers = new ArrayList<Integer>();
			for (int i = 0; i < 100; i++) {
				String resource = String.format(resourceFormat, i);
				Boolean exists = existingImages.get(resource);
				if (exists == null) {
					exists = false;
					try {
						IResourceStream stream = new PackageResourceStream(scope, resource);
						try {
							stream.getInputStream();
							exists = true;
						} catch (Throwable e) {
						}
						stream.close();
					} catch (Throwable e) {
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
	
	private static final class SessionState extends Model<ResourceReference> {
		private static final long serialVersionUID = 1L;
//		Resource imageResource;
		int[] existingResourceNumbers;
	}
}
