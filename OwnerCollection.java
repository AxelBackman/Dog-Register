import java.util.ArrayList;
import java.util.Arrays;

public class OwnerCollection {
	
	private Owner[] ownerCollection = new Owner[0];

	
	public boolean addOwner(Owner owner) {
		if (owner.getName().isEmpty() || containsOwner(owner.getName()) || containsOwner(owner)) {
			return false;
		}
		increaseSize();
		ownerCollection[ownerCollection.length - 1] = owner;
		Arrays.sort(ownerCollection);
		
		return containsOwner(owner);
	}
	
	public boolean removeOwner(String ownerName) {
		int index = findIndex(ownerName);
		if (index < 0) {
			return false;
		}
		if (!ownerCollection[index].getDogs().isEmpty()) {
			return false;
		}
		
        for (int j = index; j < ownerCollection.length - 1; j++)
        	ownerCollection[j] = ownerCollection[j + 1];

        decreaseSize();
        return !containsOwner(ownerName);
    }
	
	public boolean removeOwner(Owner owner) {
		return removeOwner(owner.getName());
		
	}

	public Owner getOwner(String ownerName) {
		int index = findIndex(ownerName);
		
		if (index < 0) {
			return null;
		}
		
		return ownerCollection[index];
	}
	
	private Owner getOwner(Owner owner) {
		return getOwner(owner.getName());
	}
	
	public ArrayList<Owner> getOwners(){
		return new ArrayList<>(Arrays.asList(ownerCollection));
	}
	
	public boolean containsOwner(String ownerName) {
		return getOwner(ownerName) != null;
	}
	
	public boolean containsOwner(Owner owner) {
		return containsOwner(owner.getName());
	}
		
	private int findIndex(Owner owner) {
		return findIndex(owner.getName());
	}
	
	private int findIndex(String ownerName) {
		for (int i = 0; i < ownerCollection.length; i++) {
			if (ownerCollection[i].getName().equals(ownerName)) {
				return i;
			}
		}
		return -1;
	}
	
    private void increaseSize(){
        ownerCollection = Arrays.copyOf(ownerCollection, ownerCollection.length + 1);
    }

    private void decreaseSize(){
    	ownerCollection = Arrays.copyOf(ownerCollection, ownerCollection.length - 1);
    }
}
