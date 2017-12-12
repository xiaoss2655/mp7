package myRPG;

public class Monster {
	int hp;
	int attack;
	int defence;
	int exp;
	String name;
	
	public Monster(String name, int hp, int atk, int def,int exp) {
		this.name = name;
		this.hp = hp;
		this.attack = atk;
		this.defence = def;
		this.exp = exp;
	}

}
