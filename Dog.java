
public class Dog {
	
	private final String name;
	private String breed;
	private int age;
	private final int weight;
	private static final String[] taxNames = {"Tax", "Dachshund", "Teckel", "mäyräkoira"}; 
	private Owner owner;
	
	public Dog(String name, String breed, int age, int weight) {
		this.name = normalizeString(name);
		this.breed = normalizeString(breed);
		this.age = age;
		this.weight = weight;
	}
	
	private String normalizeString(String string) {
		return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
	}
	
	public String getName() {
		return name;
	}
	
	public String getBreed() {
		return breed;
	}
	
	public int getAge() {
		return age;
	}
	
	public void increaseAge() {
		if (!(age == Integer.MAX_VALUE)) {
			age++;
		}
	}
	
	public int getWeight() {
		return weight;
	}
	
	public double getTailLength() {
		for (String string : taxNames) {
			if(breed.equals(string)) {
				return 3.7;
			}
		}
		double length = age * (getWeight() / 10.0);
        return Math.round(length * 10.0) / 10.0;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Dog's Name: " + name)
			.append(", Breed: " + breed)
			.append(", Age: " + age)
			.append(", weight: " + weight)
			.append(", Tail Length: " + getTailLength())
			.append(", Owner: " + getOwner());
		
		return sb.toString();
	}

	public boolean setOwner(Owner newOwner) {
		if (newOwner == null && owner != null) {
			owner.removeDog(this);
			owner = null;
			return true;
		}
	
		if (newOwner != null && owner == null) {
			owner = newOwner;
			newOwner.addDog(this);
			return true;
		}

		return false;
	}
	
	public Owner getOwner() {
		return owner;
	}
}
