import java.util.Comparator;

public class DogTailNameComparator implements Comparator<Dog> {

	@Override
	public int compare(Dog first, Dog second) {
		DogTailComparator dtc = new DogTailComparator();
		DogNameComparator dnc = new DogNameComparator();
		
		int result = dtc.compare(first, second);
		
		if(result == 0) {
			return dnc.compare(first, second);
		}
		return result;
	}
}
