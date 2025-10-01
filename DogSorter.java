import java.util.ArrayList;
import java.util.Comparator;

public class DogSorter {
	
	private static void swapDogs(ArrayList<Dog> dogs, int i, int j) {
		if (!(dogs.size()-1 < i || dogs.size()-1 < j || i == j)) {
			Dog f = dogs.get(i);
			Dog s = dogs.get(j);
			
			dogs.set(i, s);
			dogs.set(j, f);
		}
	}
	
	private static int nextDog(Comparator<Dog> cmp, ArrayList<Dog> dogs, int index) {
		int nextIndex = 0;
		Dog nextDog = null;
			
		for (int i = index; i < dogs.size(); i++) {
			if (nextDog == null || cmp.compare(nextDog, dogs.get(i)) > 0) {
				nextDog = dogs.get(i);
				nextIndex = i;
			}
		}
		return nextIndex;
	}
	
	public static int sortDogs(Comparator<Dog> cmp, ArrayList<Dog> dogs) {
		int count = 0;
		
		for (int i = 0; i < dogs.size(); i++) {
			int nextIndex = nextDog(cmp, dogs, i);
			if (!(nextIndex == i)) {
				swapDogs(dogs, i, nextIndex);
				count++;
			}
		}
		return count;
	}
}
