package game;
import java.io.*;
import java.util.*;


public class Weapon extends Item {
	
	public String weaponType;
	public String weaponName;
	
	// weapon stats
	public int weaponClass;
	public int damage;
	public int baseDamage;
	public int baseDurability;
	public int durability;
	public int block;
	public int range = 1;
	public int mod = 1;
	public int modChance;
	public int modCount;
	public String breakString;
	public boolean foundWeapon;
	
	
	public Weapon(String type, String name) throws FileNotFoundException {
		weaponName = name;
		weaponType = type;
		Scanner findWeapon = new Scanner(new File("WeaponList.txt"));
		while (foundWeapon == false && findWeapon.hasNextLine()) {
			String thisLine = findWeapon.nextLine();
			if (type.equals(thisLine)) {
				foundWeapon = true;
				Scanner weaponStats = new Scanner(findWeapon.nextLine());
				weaponClass = weaponStats.nextInt();
				baseDamage = weaponStats.nextInt();
				baseDurability = weaponStats.nextInt();
				block = weaponStats.nextInt();
				range = weaponStats.nextInt();
				mod = weaponStats.nextInt();
				modChance = weaponStats.nextInt();
				breakString = findWeapon.nextLine();
				weaponStats.close();
			}
		}
		findWeapon.close();
		damage = baseDamage;
	}
	
	
	public String examine() {
		return "Name: \t " + weaponName + "\t " + weaponType + "\n Damage: " + damage;
	}
	
	
	public void use() {
		modCount--;
		durability--;
		if (modCount == 0) {
			damage = baseDamage;
			mod = 0;
		}
	}
	

	
	public String getBreakString() {
		return breakString;
	}
	
	public String toString() {
		return weaponName;
	}
	
	public String modify(Item modifier) {
		if (modifier.weaponMod == true) {
			if (modifier.modifierType.equals("fire")) {
				mod = 1;
				damage += 3;
				modChance = 50;
				return "You've applied fire to your " + weaponName + ".";
			}
			if (modifier.equals("sharpen")) {
				if ( weaponClass >= 5) {
					return "You can't sharpen your " + weaponName + ".";
				} else {
					durability = baseDurability;
					return "You've sharpened your " + weaponName + ".";
				}
			}
			return "";
		} else {
			return "You can't apply this item to a weapon.";
		}
	}
	
	
	
}