package myRPG;

public class Player {
	
	String name;
    int level;
    int strength;
    int intelligence;
    int hp;
    int mp;
    int attack;
    int defence;
    int luck;
    public int bhp; //current hp
    int bmp; //current mp
    Skill[] skills = new Skill[3];
    equipment[] equipment = new equipment[3];
    int count;
    String job;
    int[] explevel = {5,10,20,30,40,50,60,70,80};
    int currentExp;
    

	public Player(int j) { //create the warrior
		if (j == 1) {
			this.job = "warrior";
			level = 0;
			strength = (int)(Math.random() * 10 + 20);
			intelligence = (int)(Math.random() * 5);
			hp = 30 + strength*5;
			mp = 20 + intelligence*5;
			bhp = hp;
			bmp = mp;
			attack = strength * 10;
			defence = 5 * level;
			luck = 40 - strength - intelligence;
			skills[0] = new Skill("Straight Cut",20,5);
			count = 0;
		}
		if (j == 2) {//create the wizard
			this.job = "wizard";
			level = 0;
			strength = (int)(Math.random() * 5);
			intelligence = (int)(Math.random() * 10 + 20);
			hp = 20 + strength * 5;
			mp = 30 + intelligence  *5;
			bhp = hp;
			bmp = mp;
			attack = intelligence * 10;
			defence = 5 * level;
			luck = 40 - strength - intelligence;
			skills[0] = new Skill("Fire Ball",20,5);;
		}
	}
	
	public boolean levelUp() { 
		boolean levelup = false;
		while (currentExp >= explevel[level]) {
			currentExp = currentExp - explevel[level];
			level += 1;
			if(job == "warrior") {
				strength += 5;
				intelligence += 2;
				bhp += 50;
				bmp += 10;
				hp = bhp;
				mp = bmp;
				attack += 30;
				defence += 20;
			} else if(job == "wizard") {
				strength += 2;
				intelligence += 5;
				bhp += 50;
				bmp += 10;
				hp = bhp;
				mp = bmp;
				attack += 30;
				defence += 20;
			}
			levelup = true;
		}
		return levelup;
		
		
	}
	


}
