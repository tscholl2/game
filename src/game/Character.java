package game;
import java.util.*;
import java.awt.*;
import java.io.*;

public class Character{

	Scanner console = new Scanner(System.in);

	
	public Point position;
	
	//stats
	public int level = 1;
	public int HP = 1;
	public int corePhysAtk = 5;
	public int corePhysDef = 5;
	public int coreMagAtk = 5;
	public int coreMagDef = 5;
	public int speed = 5;
	public int intel = 5;
	
	
	
	
	
	
	// loadout
	public Weapon primaryWeapon;
	public Weapon secondaryWeapon;
	public Item equippedArmor;
	
	
	
	public Color color = Color.WHITE;

	public String stringRep;
	public boolean dead = false;
	
	public int totalDefense = 8;
		
	public String characterName;
	public ArrayList<Item> inventory = new ArrayList<Item>();
	public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	public ArrayList<Armor> armor = new ArrayList<Armor>();
	public int money = 100;
	public ArrayList<Item> hotInventory = new ArrayList<Item>() {{
		add(new Item("ONE"));
		add(new Item("TWO"));
		add(new Item("THREE"));
	}};
	
	
	public Character(String name) throws FileNotFoundException {	
		characterName = name;
		stringRep = name.substring(0, 2).toUpperCase();
		position = new Point(50, 50);
		primaryWeapon = new Weapon("Fist", "Fist");
		secondaryWeapon = new Weapon("Fist", "Fist");
		
	}

	public Character() throws FileNotFoundException {
		this("  ");
	}
	
	public String getStringRep() {
		return stringRep;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void showInventory() {
		for (int i = 0; i <inventory.size(); i++) {
			System.out.println(inventory.get(i).toString());
		}
	}

	public Move getMove() {
		if (dead == true) {
			return new Move();
		}
		Move move = new Move();
		
		// prompts for move
		System.out.println("Move:\t\t(u)p\t\t(d)own\t\t(l)eft\t\t(r)ight");
		System.out.println("Attack:\t\t(p)rimary weapon, " + primaryWeapon);
		System.out.println("\t\t(s)econdary weapon, " + secondaryWeapon);
		System.out.println("Quick items: \t(1) " + hotInventory.get(0) + "\t(2) " + hotInventory.get(1) + "\t(3) " + hotInventory.get(2));
		System.out.println("Other options:\t(i)nteract\t(t)alk\t\t(m)anage character and inventory");
		System.out.println("Next action?");
		String actionInput = console.next();
		
		//process input
		switch (actionInput) {
			case "u": move.action = Action.MOVE;
					move.dir = Direction.UP;
					break;
			case "d": move.action = Action.MOVE;
					move.dir = Direction.DOWN;
					break;
			case "l": move.action = Action.MOVE;
					move.dir = Direction.LEFT;
					break;
			case "r": move.action = Action.MOVE;
					move.dir = Direction.RIGHT;
					break;
			case "p": move.action = Action.ATTACK;
					move.dir = Direction.STAY;
					move.weapon = primaryWeapon;
					move.target = promptTarget();
					break;
			case "s": move.action = Action.ATTACK;
					move.dir = Direction.STAY;
					move.weapon = secondaryWeapon;
					move.target = promptTarget();
					break;
			case "1": move.action = Action.USE;
					move.item = hotInventory.get((Integer.parseInt(actionInput) - 1));
					move.target = promptTarget();
			case "2": move.action = Action.USE;
					move.item = hotInventory.get((Integer.parseInt(actionInput) - 1));
					move.target = promptTarget();
			case "3": move.action = Action.USE;
					move.item = hotInventory.get((Integer.parseInt(actionInput) - 1));
					move.target = promptTarget();
			default: System.out.println("Invalid input. Try again!");
					move = this.getMove();
					break;
		}
		return move;
	}

	
	
	public String promptTarget() {
		System.out.println("Attack which enemy? ");
		String target = console.next();
		return target;
	}
	
	public Direction promptDirection() {
		System.out.println("Direction?");
		System.out.println("(u)p, (d)own, (l)eft, (r)ight: ");
		String directionInput = console.next();
		switch (directionInput) {
			case "u": return Direction.UP;
			case "d": return Direction.DOWN;
			case "l": return Direction.LEFT;
			case "r": return Direction.RIGHT;
			default: System.out.println("Invalid input. Try again.");
					return this.promptDirection();
		}
	}
	
	public void die() {
		dead = true;
		stringRep = "*";
	}
	
	
	public String toString() {
		return characterName;
	}
	
	public Item promptItem() {
		System.out.println("Current inventory:");
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println((i + 1) + ". " + inventory.get(i));
		}
		System.out.println("Type item number to use: ");
		int itemNumber = console.nextInt();
		return inventory.get(itemNumber - 1);
	}
		

}
