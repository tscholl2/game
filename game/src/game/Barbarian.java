package game;
import java.io.*;

public class Barbarian extends Character {
	
	public Barbarian(String name) throws FileNotFoundException {
		characterName = name;
		weapons.add(new Weapon("Iron Sword", "HellBreaker"));
		armor.add(new Armor("Loincloth"));
	}

}