package game;
import java.util.*;
import java.awt.*;
import java.io.*;

public class D1R1 extends DungeonNode {
	
	public D1R1() throws FileNotFoundException {
		this.x = 300;
		this.y = 300;
		characterList.add(new Barbarian("B1"));
		characterList.get(0).position = new Point(50, 150);
		characterList.add(new Barbarian("B2"));
		characterList.get(1).position = new Point(200, 100);
		characterList.add(new Barbarian("B3"));
		characterList.get(2).position = new Point(100, 100);
	}

	public void generateUp() throws FileNotFoundException {
		this.up = new D1R1();
	}
	
}
