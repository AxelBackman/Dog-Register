import java.util.ArrayList;
import java.util.Collections;

public class Owner implements Comparable<Owner> {
	
	private final String name;
	private ArrayList<Dog> dogs;

	public Owner(String name) {
		this.name = normalizeString(name);
		this.dogs = new ArrayList<>();
	}
	
	private String normalizeString(String string) {
		return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Ownner's name: " + name)
			.append(", owned dogs: ");
			for (Dog dog : getDogs()) {
				sb.append(dog.getName());
			}
			
		return sb.toString();
	}

	@Override
	public int compareTo(Owner o) {
		return name.compareTo(o.getName());
	}
	
	public boolean addDog(Dog dog) {
		if (dog != null && (dog.getOwner() == this || dog.getOwner() == null) && !dogs.contains(dog)) {
			dogs.add(dog);
			dog.setOwner(this);
			return true;
		}
		return false;	
	}
	
	public boolean removeDog(Dog dog) {
		return dogs.remove(dog);
		
	}
	
	public ArrayList<Dog> getDogs(){
		Collections.sort(dogs, new DogNameComparator());
		return new ArrayList<>(Collections.unmodifiableList(dogs));

	}
}
