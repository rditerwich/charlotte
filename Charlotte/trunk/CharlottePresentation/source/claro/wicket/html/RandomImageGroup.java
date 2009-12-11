package claro.wicket.html;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.time.Duration;

public class RandomImageGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final List<RandomImage> randomImages = new ArrayList<RandomImage>();
	private final Duration duration;
	private int[] chances;
	private final int initialChance;
	private final int changeDelta;
	
	/**
	 * 
	 * @param duration
	 * @param initialChance 0 - 100
	 * @param changeDelta -100 - 100
	 */
	public RandomImageGroup(Duration duration, int initialChance, int changeDelta) {
		this.duration = duration;
		this.initialChance = initialChance;
		this.changeDelta = changeDelta;
	}
	
	void add(RandomImage randomImage) {
		if (randomImages.isEmpty()) {
			randomImage.add(new AbstractAjaxTimerBehavior(duration) {
				private static final long serialVersionUID = 1L;
				protected void onTimer(AjaxRequestTarget target) {
					calculateImageResources(target);
				}
			});
		}
		randomImages.add(randomImage);
		randomImage.setOutputMarkupId(true);
		this.chances = calculateUpdateChances();
	}
	
	private int[] calculateUpdateChances() {
		int[] chances = new int[randomImages.size()];
		for (int i = 0, chance = initialChance; i < chances.length; i++, chance += changeDelta) {
			chances[i] = chance;
		}
		return chances;
	}
	
	private void calculateImageResources(AjaxRequestTarget target) {
		shuffleChanges(chances);
		Random random = new Random();
		for (int i = 0; i < chances.length; i++) {
			if (random.nextInt(100) < chances[i]) {
				RandomImage image = randomImages.get(i);
				chooseUniqueRandomImage(image);
				target.addComponent(image);
			}
		}
	}

	void chooseRandomImages() {
		for (RandomImage image : randomImages) {
			chooseUniqueRandomImage(image);
		}
	}
	
	
	private void chooseUniqueRandomImage(RandomImage image) {
		for (int i = 0; i < 20; i++) {
			ResourceReference imageResource = image.chooseRandomImage();
			if (imageResource != null) {
				boolean isUnique = true;
				for (RandomImage otherImage : randomImages) {
					if (imageResource.equals(otherImage.getResource())) {
						isUnique = false;
						break;
					}
				}
				if (isUnique) {
					image.setResource(imageResource);
					break;
				}
			}
		}
	}
	
	private static void shuffleChanges(int[] chances) {
		Random random = new Random();
		for (int i = 0; i < chances.length; i++) {
			int pos = random.nextInt(chances.length);
			int temp = chances[i];
			chances[i] = chances[pos];
			chances[pos] = temp;
		}
	}
}
