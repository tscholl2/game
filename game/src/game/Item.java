package game;

public class Item {
	
	public String itemName;
	public String modifierType = "fire";
	boolean weaponMod = true;
	boolean armorMod = true;
	public int[] stats = new int[7];
	
	public Item(String name) {
		itemName = name;
	}
	
	public Item() {
		this("");
	}
	
	public String toString() {
		return "item";
	}

	public void use() {
		// TODO Auto-generated method stub
		
	}
	
}
