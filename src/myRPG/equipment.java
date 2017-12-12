package myRPG;

public class equipment {
	
	String name;
	int eattack;
	int edefence;
	int eluck;
	int num;
	int seriesnumber;

	public equipment(int n) {
		if (n == 1) {
			name = "nife";
			eattack = 20;
			edefence = 0;
			eluck = 0;
			num = 0;
		} 
		if (n == 2) {
			name = "teeth from the demon";
			eattack = 40;
			edefence = 0;
			eluck = 0;
			num = 0;
		} 
		if (n == 3) {
			name = "The Holy Grail";
			eattack = 20;
			edefence = 0;
			eluck = 0;
			num = 0;
		} 
		if (n == 4) {
			name = "silver sword";
			eattack = 40;
			edefence = 0;
			eluck = 0;
			num = 0;
		}
		if (n == 5) {
			name = "golden sword";
			eattack = 0;
			edefence = 10;
			eluck = 0;
			num = 1;
		} 
		if (n == 6) {
			name = "magic wand";
			eattack = 0;
			edefence = 20;
			eluck = 0;
			num = 1;
		} 
		if (n == 7) {
			name = "The book of Demon";
			eattack = 0;
			edefence = 0;
			eluck = 5;
			num = 2;
		} 
		

	}
	
	public void equip(Player a){
		if(a.equipment[this.num] != null) {
			a.attack += (eattack - a.equipment[this.num].eattack);
			a.defence += (edefence - a.equipment[this.num].edefence);
			a.luck += (eluck - a.equipment[this.num].eluck);
		}
		a.attack += eattack;
		a.defence += edefence;
		a.luck += eluck;
		a.equipment[this.num] = this;
	}

}
