package game;
import java.awt.*;
import java.io.*;
import java.util.*;

public class BattleTest {
	
	static ArrayList<Character> characters = new ArrayList<Character>();
	static DungeonNode thisRoom;

	public static void main(String[] args) throws FileNotFoundException {
		thisRoom = new D1R1();
		thisRoom.generateUp();
		characters = thisRoom.characterList;
		Random rand = new Random();
		Character player = new SamplePlayer("Connor");
		characters.add(player);
		boolean running = true;
		int moveCount = 0;
		DrawingPanel panel = new DrawingPanel(thisRoom.x, thisRoom.y);
		Graphics g = panel.getGraphics();
		g.setFont(new Font("Monospaced", Font.BOLD, 20));
		while (running = true) {
			drawState(g, thisRoom);
			characters = thisRoom.characterList;
			characters.add(player);
			for (Character character : characters) {
				Move thisMove = character.getMove();
				if (thisMove.action.equals(Action.MOVE)) {
					switch (thisMove.dir) {
					case UP: character.position.translate(0, -20);
						break;
					case DOWN: character.position.translate(0, 20);
						break;
					case LEFT: character.position.translate(-20, 0);
						break;
					case RIGHT: character.position.translate(20, 0);
						break;
					default: break;
					}
					if (character.position.y <= 0) {
						if (character.position.x >= thisRoom.x / 2 - 20 && character.position.x <= thisRoom.x / 2 + 20) {
							thisRoom = thisRoom.up;
							character.position.y = thisRoom.y - 20;
							thisRoom.characterList.add(character);
						} else {
							character.position.translate(0, 20);
						}
					}
				}

				if (thisMove.action.equals(Action.ATTACK)) {
					Character target = null;
					for (int i = 0; i < characters.size(); i++) {
						if (characters.get(i).getStringRep().equals(thisMove.target)) {
							target = characters.get(i);
						}
					}
					if (target == null) {
						System.out.println("No target by that name. You miss!");
						break;
					}
					int blockChance = rand.nextInt(100);
					int evadeChance = rand.nextInt(100);
					if (Math.pow(Math.pow(character.position.x - target.position.x, 2) +
							Math.pow(character.position.y - target.position.y, 2), 0.5) > 30 * character.primaryWeapon.range) {
						System.out.println("Enemy " + target + " is too far away!");
						System.out.println(Math.pow(Math.pow(character.position.x - target.position.x, 2) +
							Math.pow(character.position.y - target.position.y, 2), 0.5));
						System.out.println(character.primaryWeapon.range);
					} else if (evadeChance < target.speed) {
						System.out.println("Enemy " + target + " evaded your attack!");
						
					} else if (blockChance < target.primaryWeapon.block) {
						System.out.println("Enemy " + target + " blocked your attack with " + target.primaryWeapon);
					} else {
						int damage = character.primaryWeapon.damage - target.corePhysDef;
						target.HP -= damage;
						System.out.println(character.primaryWeapon.damage + " " + target.corePhysDef);
						System.out.println(character + " did " + damage + " damage to " + target + "!");
					}
					if (target.HP <= 0) {
						System.out.println("Enemy " + target + " was killed by your" + character.primaryWeapon + "!");
						System.out.println();
						target.die();
					}
				}
				
				
				
				drawState(g, thisRoom);
			}
			moveCount++;
		}
	}
	
	
	public static void drawState(Graphics g, DungeonNode thisRoom) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, thisRoom.x, thisRoom.y);
		g.setColor(Color.GRAY);
		g.fillRect(0,  0,  thisRoom.x,  10);
		g.fillRect(0,  thisRoom.y - 10,  thisRoom.x,  10);
		g.fillRect(0,  0, 10, thisRoom.y);
		g.fillRect(thisRoom.x - 10, 0, 10, thisRoom.y);
		g.setColor(Color.blue);
		if (thisRoom.up != null) {
			g.fillRect(thisRoom.x / 2 - 20, 0, 40, 10);
		}

		for (Character character : thisRoom.characterList) {
			g.setColor(character.getColor());
			g.drawString(character.getStringRep(), character.position.x - 10, character.position.y + 8);
			g.setColor(Color.RED);
			g.fillRect(character.position.x, character.position.y, 2, 2);
		}
	}
}
