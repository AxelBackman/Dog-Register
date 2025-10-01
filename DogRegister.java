import java.util.ArrayList;

public class DogRegister {
	
	private OwnerCollection owners;
	private DogCollection dogs;
	private InputReader scanner;
	
	public DogRegister(OwnerCollection owners, DogCollection dogs, InputReader scanner) {
		this.owners = owners;
		this.dogs = dogs;
		this.scanner = scanner;
	}
	
	public static void main (String [] args) {
		DogRegister dogRegister = new DogRegister(new OwnerCollection(), new DogCollection(), new InputReader());
		dogRegister.commandMenu();
		dogRegister.commandoLoop();
	}
	
	private void commandMenu() {
		System.out.println("Welcome to the dog register!");
		System.out.println("The following commands are available");
		System.out.println("* Register new dog");
		System.out.println("* Remove dog");
		System.out.println("* Register new owner");
		System.out.println("* Remove owner");
		System.out.println("* List dogs");
		System.out.println("* List owners");
		System.out.println("* Increase age");
		System.out.println("* Give dog to owner");
		System.out.println("* Remove dog from owner");
		System.out.println("* Register new dog");
		System.out.println("* Load");
		System.out.println("* Exit");
	}
	
	private void commandoLoop() {
		boolean isRunning = true;

		while (isRunning) {
			String command = scanner.readLine("\nEnter Command");
			switch (command) {
			case "Register new dog":
				registerNewDog();
				break;
			case "Remove dog":
				removeDog();
				break;
			case "Register new owner":
				registerNewOwner();
				break;
			case "Remove owner":
				removeOwner();
				break;
			case "List dogs":
				listDogs();
				break;
			case "List owners":
				listOwners();
				break;
			case "Increase age":
				increaseAge();
				break;
			case "Give dog to owner":
				giveDogToOwner();
				break;
			case "Remove dog from owner":
				removeDogFromOwner();
				break;
			case "Load":
				load();
				break;
			case "Exit":
				System.out.println("Exiting");
				isRunning = false;
				break;
			default:
				System.out.println("ERROR: Invalid command '" + command + "'");
				continue;
			}
		}
	}
			 
	private void registerNewDog(){
		String name = scanner.readLine("What is the dog's name");
		if (dogs.containsDog(name)) {
			System.out.print("ERROR: The dog " + name + " already exists");
			return;
		}	
		
		String breed = scanner.readLine("What is " + name + "'s breed");
		int age = scanner.readInt("What is " + name + "'s age");
		int weight = scanner.readInt("What is " + name + "'s weight");
		Dog dog = new Dog(name, breed, age, weight);
		
		dogs.addDog(dog);
		System.out.println(name + " has been added to the register.");
	}
	
	private void removeDog() {
		if (dogs.getDogs().isEmpty()) {
			System.out.println("ERROR: There are no dogs in the register.");
			return;
		}
		String name = scanner.readLine("What is the dog's name");
		
		if (!dogs.containsDog(name)){
			System.out.println("ERROR: The dog " + name + " doesn't exist.");
			return;
		}

		Dog dogToRemove = dogs.getDog(name);
	
		if (dogToRemove.getOwner() != null) {
			dogToRemove.getOwner().removeDog(dogToRemove);
			dogToRemove.setOwner(null);
		}
		dogs.removeDog(name);
		System.out.println(name + " has been removed from the register.");
	}
	
	private void registerNewOwner() {
		String name = scanner.readLine("What is the Owner's name");
		if (owners.containsOwner(name)) {
			System.out.println("ERROR: The owner " + name + " already exists.");
			return;
		}
		Owner owner = new Owner(name);
		owners.addOwner(owner);
		System.out.println(name + " has been added to the register.");
	}
	
	public void removeOwner() {
		if (owners.getOwners().isEmpty()) {
			System.out.println("ERROR: There are no owner's in the register.");
			return;
		}
		String name = scanner.readLine("What is the Owner's name");
		
		if (!owners.containsOwner(name)) {
			System.out.println("ERROR: The owner " + name + "doesn't exist.");
			return;
		}
		Owner ownerToRemove = owners.getOwner(name);
		
		for (Dog dog : ownerToRemove.getDogs()) {
			dog.setOwner(null);
			dogs.removeDog(dog);
			ownerToRemove.removeDog(dog);
		}
		if (owners.removeOwner(name)) {
			System.out.println(name + " has been removed from the register.");
		}
	}
	
	private void listDogs() {
        if (dogs.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register");
            return;
        }
        double minTailLength = scanner.readDouble("Enter minimum tail length");
        ArrayList<Dog> dogsToDisplay = dogs.getDogsWithMinimumTailLength(minTailLength);

        for (Dog dog : dogsToDisplay) {
            System.out.println(dog.toString());
        }
    }
	
	
	private void listOwners() {
		if (owners.getOwners().isEmpty()) {
			System.out.println("ERROR: There are no owners in the reigster.");
			return;
		}
		int dogsPrinted = 0;
		
		StringBuilder sb = new StringBuilder();
		sb.append("Name\t\tDogs\n");
		for (Owner owner : owners.getOwners()) {
			sb.append(owner.getName()+"\t");
			if (owner.getDogs().isEmpty()) {
				sb.append("No dogs");
			} else {
				for (Dog dog : owner.getDogs()) {
					if (dogsPrinted > 0) {
						sb.append(", ");
					}
					sb.append(dog.getName());
					dogsPrinted++;
				}
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	private void increaseAge() {
		if (dogs.getDogs().isEmpty()) {
			System.out.println("ERROR: There are no dogs in the register.");
			return;
		}
		String name = scanner.readLine("What is the dog's name");

		if (!dogs.containsDog(name)){
			System.out.println("ERROR: The dog " + name + " doesn't exist.");
			return;
		}
		Dog dog = dogs.getDog(name);
		dog.increaseAge();
		System.out.println(name + " is now " + dog.getAge() + " years old instead of " + (dog.getAge()-1) + " years old.");
	}
	
	private void giveDogToOwner() {
		if (dogs.getDogs().isEmpty()) {
			System.out.println("ERROR: There are no dogs in the register.");
			return;
		}
		if (owners.getOwners().isEmpty()) {
			System.out.println("ERROR: There are no Owners in the register.");
			return;
		}
		String dogName = scanner.readLine("What is the dog's name");
		if (!dogs.containsDog(dogName)) {
			System.out.println("ERROR: The dog " + dogName + " doesn't exist.");
			return;
		}
		if (dogs.getDog(dogName).getOwner() != null) {
			System.out.println("ERROR: The dog " + dogName + " already has an owner");
			return;
		}
		String ownerName = scanner.readLine("What is the owner's name");
		if (!owners.containsOwner(ownerName)) {
			System.out.println("ERROR: The owner " + ownerName + " doesn't exist.");
			return;
		}
		dogs.getDog(dogName).setOwner(owners.getOwner(ownerName));
		System.out.println(dogName + " is now owned by " + ownerName);
	}
	
	private void removeDogFromOwner(){
		if (dogs.getDogs().isEmpty()) {
			System.out.println("ERROR: There are no dogs in the register.");
			return;
		}
		if (owners.getOwners().isEmpty()) {
			System.out.println("ERROR: There are no Owners in the register.");																																																																																																																						
			return;
		}
		String dogName = scanner.readLine("What is the dog's name");
		if (!dogs.containsDog(dogName)) {
			System.out.println("ERROR: The dog " + dogName + " doesn't exist.");
			return;
		}
		Dog dog = dogs.getDog(dogName);
		Owner owner = dog.getOwner();
		owner.getDogs().remove(dog);
		dog.setOwner(null);
		System.out.println(dog.getName() + " has been removed from " + owner.getName() + ".");
	}
	
	private void load() {
		dogs.addDog(new Dog("dogOne", "Tax", 1, 1));
		dogs.addDog(new Dog("dogTwo", "Labrador", 2, 2));
		dogs.addDog(new Dog("dogThree", "Pug", 3, 3));
		dogs.addDog(new Dog("dogFour", "Dachshund", 4, 4));
		dogs.addDog(new Dog("dogFive", "Rottweiler", 5, 5));
		owners.addOwner(new Owner("ownerOne"));
		owners.addOwner(new Owner("ownerTwo"));
		System.out.println("Test dogs and owners are now loaded\n");
		System.out.println("Dogs:");
		listDogs();
		System.out.println("\nOwners:");
		listOwners();
		
	}
}
	
