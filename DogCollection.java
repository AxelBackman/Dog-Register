import java.util.ArrayList;

public class DogCollection {
	
	private ArrayList<Dog> dogCollection = new ArrayList<>();
	
	public boolean addDog(Dog dog) {
		if(containsDog(dog.getName())) {
			return false;
		}
		return dogCollection.add(dog);
	}
	
	
	public boolean removeDog(String dogToRemoveName) {
		Dog dogToRemove = getDog(dogToRemoveName);
		
		if (dogToRemove == null || dogToRemove.getOwner() != null) {
			return false;
		}
		return dogCollection.remove(getDog(dogToRemoveName));
	}
	
	public boolean removeDog(Dog dogToRemove) {
		if (dogToRemove.getOwner() != null) {
			return false;
		}
		
		return dogCollection.remove(dogToRemove);
	}
	
	public boolean containsDog(Dog dog) {
		return dogCollection.contains(dog);
	}
	
	public boolean containsDog(String dogName) {
		for (Dog dog : dogCollection) {
			if(dog.getName().equals(dogName)){
				return true;
			}
		}
		return false;
	}
	
	public Dog getDog(String dogToGet) {
		for (Dog dog : dogCollection) {
			if(dog.getName().equals(dogToGet)){
				return dog;
			}
		}
		return null;
	}
	
	public ArrayList<Dog> getDogs(){
		DogSorter.sortDogs(new DogNameComparator(), dogCollection);
		
		return new ArrayList<>(dogCollection);
	}
	
	public ArrayList<Dog> getDogsWithMinimumTailLength(double tail){
		ArrayList<Dog> dogs = new ArrayList<>();
		for (Dog dog : dogCollection) {
			if (dog.getTailLength() >= tail) {
				dogs.add(dog);
			}
		}
		DogSorter.sortDogs(new DogTailNameComparator(), dogs);
		return dogs;
	}

}
