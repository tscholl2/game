package game;

import java.io.*;
import java.util.*;

public class test {
	
	public static boolean endGame = false;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		Character player = intro(console);
		Character enemy = new Barbarian("Ted");
		player.inventory.add(new Item("fire"));
		System.out.print(player.weapons.toString());
		System.out.println(player.inventory.toString());
		System.out.println(player.weapons.get(0).modify(player.inventory.get(0)));
		player.showInventory();

	}
	
	public static Character intro(Scanner console) throws FileNotFoundException {
		Character player = new Character();
		System.out.println("Hi! What type of character would you like to create? Type barbarian, mage, or rogue.");
		String type = console.next();
		System.out.println("What is the name of your character?");
		String name = console.next();
		if (type.startsWith("b")) {
			player = new Barbarian(name);
		} else if (type.startsWith("m")) {
			player = new Mage(name);
		}
		System.out.println("You've created a " + type + " named " + player.toString() + ".");
		return player;
	}

}
