
package myRPG;
import java.util.Scanner;
import java.lang.Thread;

public class Game {
	static final int MAX_NUM_ROUNDS = 10;
	static final int MAX_NUM_DAYS = 10;
    
   
    public static Monster monsterCreator(){
    	Dice d100 = new Dice(100);
    	int a = d100.roll();
    	if (a < 60) {
    		return new Monster("Slime",300,40,10,5); //create monster Slime
    	} else if (a < 90) {
    		return new Monster("Ghoul",500,80,20,10); //create monster Ghoul
    	} else {
    		return new Monster("Chimera",1000,120,40,25); //create monster Chimera
    	}
  
    	
    }
    
	public static boolean battle(Monster a, Player b) { 
		Dice d20 = new Dice(20);
		Scanner sc2 = new Scanner(System.in);
		System.out.println("You encouter " + a.name);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int damage;
		for (int round = 1; round < MAX_NUM_ROUNDS; round++) {
			System.out.println("What do you want to do?");
			System.out.println("1. fight; 2. skill; 3. escape");
			int c = sc2.nextInt();
			while (c > 3 || c < 1) {
				System.out.println("Please enter again");
				c = sc2.nextInt();
			}
			if (c == 3) {
				System.out.println("You escape from the battle!");
				return true; 
			}
			if (c == 1) {
				damage = b.attack + Math.max(b.intelligence, b.strength) * b.luck / 10 - a.defence;
				if (damage < 0) {
					damage = 1;
				}
				System.out.println("You hits " + damage + " damage!");
				a.hp = a.hp + a.defence - damage;
			}
			int s1 = 0;
			if (c == 2) {
				System.out.println("Please choose the skill you want to use");
				if (b.skills[1] == null) {
					System.out.println("1. " + b.skills[0].name);
					s1 = sc2.nextInt() - 1;					
					while (s1 == 1) {
						System.out.println("Please choose the skill you want to use");
						s1 = sc2.nextInt() - 1;
					}
				} else if (b.skills[2] == null) {
					System.out.println("1. " + b.skills[0].name + " 2. " + b.skills[1].name);
					s1 = sc2.nextInt() - 1;
					while (s1 > 2 || s1 < 1) {
						System.out.println("Please choose the skill you want to use");
						s1 = sc2.nextInt() - 1;
					}
				} else if (b.skills[2] != null) {
					System.out.println("1. " + b.skills[0].name + " 2. " + b.skills[1].name
							+ " 3. " + b.skills[2].name);
					s1 = sc2.nextInt() - 1;
					while (s1 > 3 || s1 < 1) {
						System.out.println("Please choose the skill you want to use");
						s1 = sc2.nextInt() - 1;
					}
				}
				if (b.mp - b.skills[s1].consume >= 0) {
					damage = b.attack + Math.max(b.intelligence, b.strength) * b.luck / 10 + b.skills[s1].attack - a.defence;
					b.mp -= b.skills[s1].consume;
				} else {
					System.out.println("You don't have enough mp");
					damage = b.attack + Math.max(b.intelligence, b.strength) * b.luck / 10 - a.defence;
					
				}
				if (damage <= 0) {
					damage = 1;
				}
				System.out.println("You hits " + damage + " damage!");
				a.hp = a.hp - damage;
			}
			int damageM = a.attack - b.defence;
			if (damageM < 0) {
				damageM = 1;
			}
			System.out.println(a.name + " hits " + damageM + " damage!");
			b.hp = b.hp - (a.attack - b.defence);
			if (a.hp <= 0) {
				System.out.println("You beat " + a.name + "!");
				b.currentExp += a.exp;
				boolean levelUP = b.levelUp();
				if (levelUP) {
					System.out.println("Level Up!");
				}
			
				if(d20.roll() + b.luck >= 20) {
					equipment a1 = new equipment(6);
					int roll = d20.roll();
					if (roll < 5) {
						if (b.job == "wizard") {
							a1 = new equipment(3);
						}
						a1 = new equipment(1);
					} else if (roll < 10) {
						if (b.job == "wizard") {
							a1 = new equipment(4);
						}
						a1 = new equipment(2);
					} else if (roll < 15) {
						a1 = new equipment(5);
					}
					System.out.println("You get " + a1.name + "!");
					System.out.println("Do you want to equip yourself with it?");
					System.out.println("1. yes; 2. no");
					int aq = sc2.nextInt();
					while (aq > 2 || aq < 1) {
						System.out.println("Enter again");
						aq = sc2.nextInt();
					}
					if (aq == 1) {
						a1.equip(b);
						System.out.println("Now you ATK = " + b.attack + "  DEF = " + b.defence);
					}
				}
				return true;
			}
			if (b.hp <= 0) {
				System.out.println("You are beaten!");
				return false;
			}
		}
		sc2.close();
		if (a.hp > 0) {
			System.out.println("You are beaten!");
			return false;
		}
		return true;

	}

	
	//This is the main game mode code
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("This, is a dangerous world.");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("People used to live peacefully with Earth creatures.");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" But one day, a gigantic meteorite filled with seeds of creatures from the universe fell on the Earth, bringing the Earth a huge catastrophy.");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Various monsters eat and kill animals and creatures, destroying people's family and home. Poeple live ");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("desperately, wishing one day a hero can save their lives and bring them the peace.");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("You are the chosen one. You were born for fighting them to death. That's your destiny, and also the path you chose for yourself." );
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Recently you just find that all monsters have a weird relationships with their leader: Fire Dragon. An elder");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("told you, if you can kill you that beast, all other monsters will die.");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("You have decided, to bring the Earth peace. Now, the chosen one, it's time to fight!");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("You have 10 days to kill it!");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Player enter your name:");
		String name = sc.nextLine();
		System.out.println(name + ", do you want to be a warrior or a wizard?");
		System.out.println("Enter 1 for warrior or 2 for wizard");
		int a = sc.nextInt();
		while (a > 2 || a < 1) {
			System.out.println("You typed wrong. Please enter 1 for warrior or 2 for wizard");
			a = sc.nextInt();
		}
		Player newPlayer = new Player(a);
		System.out.println(name + ", this is you!");
		System.out.println("HP = " + newPlayer.hp + "  " + "MP = " + newPlayer.mp + "  " 
				+ "STR = " + newPlayer.strength  + "  " + "INT = " + newPlayer.intelligence);
		System.out.println("ATK = " + newPlayer.attack  + "  " + "DEF = " + newPlayer.defence + "  " 
				+ "LUCK = " + newPlayer.luck );
		
		System.out.println();
		System.out.println("Begin your adventure!");
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int scount = 0;

		int day = 1;
		//System.out.println("Today is the first day. DAY 1");
		for (day = 1; day <= 8; day++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Monster mon1 = monsterCreator();
			Monster mon2 = monsterCreator();
			System.out.println("Today is Day " + day);
			System.out.println("Your HP is " + newPlayer.hp +"  " +  "Your MP is " + newPlayer.mp);
			System.out.println("What do you want to do?");
			System.out.println("1. battle  2. train  3. rest");
			int a1 = sc.nextInt();
			while (a1 > 3 || a1 < 1) {
				System.out.println("Please enter again");
				a1 = sc.nextInt();
			}
			if (a1 == 1) {
				boolean nd = battle(mon1, newPlayer); 
				if (nd) {
					System.out.println("Do you want to go home?");
					System.out.println("1. yes   2. no ");
					int aq = sc.nextInt();
					while (aq >2 || aq < 1) {
						System.out.println("Enter again");
						aq = sc.nextInt();
					}
					if (aq == 2) {
					      boolean n1d = battle(mon2, newPlayer); 
					}
					if (newPlayer.hp < 0) {
						System.out.println("You fail!");
						newPlayer.hp = 1;
					
					}
				}
			} else if (a1 == 2) {
				System.out.println("You are learning skills!");
				scount += 1;
				if (newPlayer.job == "wizard") {
					if (scount == 2) {
						newPlayer.skills[1] = new Skill("Hell Fire",40,20);
						System.out.println("You get the level 2 skill: " + newPlayer.skills[1].name + "!");
					} else if (scount == 4) {
						newPlayer.skills[2] = new Skill("Thunderstorm!",100,30);
						System.out.println("You get the level 3 skill:" + newPlayer.skills[2].name + "!");
						System.out.println("You cannot learn more skills!");
					}
				} else {
					if (scount == 2) {
						newPlayer.skills[1] = new Skill("Belly Rake",40,20);
						System.out.println("You get the level 2 skill: " + newPlayer.skills[1].name + "!");
					} else if (scount == 4) {
						newPlayer.skills[2] = new Skill("Killing Bite",100,30);
						System.out.println("You get the skill " + newPlayer.skills[2].name + "!");
						System.out.println("You cannot learn more skills!");
					}
				}
				Dice d100 = new Dice(100);
				if(d100.roll() > 30) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("--------------random event---------------");
					int re = d100.roll();
					if (re < 25) {
						System.out.println("A travelling wizard gives you some directions");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("mp + 20");
						newPlayer.mp += 20;
					} else if (re < 50) {
						System.out.println("A travelling warrior gives you some directions");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("ATK + 10");
						newPlayer.attack += 10;
					} else if (re < 75) {
						System.out.println("You unfortunately get sick");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("hp - 20");
						newPlayer.hp -= 10;
					} else {
						System.out.println("Today's training is exceptionally effective. ");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("hp + 20");
						newPlayer.hp += 20;
					}
				}
			} else {
				System.out.println("You take a rest and recover to the best condition");
				newPlayer.levelUp();
				newPlayer.hp = newPlayer.bhp;
				newPlayer.mp = newPlayer.bmp;
				Dice d100 = new Dice(100);
				if (d100.roll() < 6) {
					System.out.println("You luckily find a ring!");
				}
				equipment ring = new equipment(6);
				ring.equip(newPlayer);
			}
			
			if(newPlayer.hp < 0) {
				newPlayer.hp = 1;
			}

			System.out.println();
			System.out.println();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		day = 9;
		System.out.println("Maybe take one day off?"); 
		newPlayer.levelUp();
		newPlayer.hp = newPlayer.bhp;
		newPlayer.mp = newPlayer.bmp;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Monster boss = new Monster("Fire Dragon", 1500, 200, 50, 0);
		boolean nd = battle(boss, newPlayer); 
		if(boss.hp <= 0) {
			System.out.println("We are so proud of you! You are the only one defeating the most dangerous monster all over the world! ");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Because of you, we humanbeing finally can live in a safe and peacful world! You bring us the peace!");
		} else {
			System.out.println("We are sorry for you, you get the fatal attack from the Fire Dragon. ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("You are the only hope for us people, please choose to be reborn and save us again! ");
			
		}
		

		
		
		
		sc.close();
	}

}

