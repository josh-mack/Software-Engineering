
public class Environment {
	private Species[] animals;
	private Character[] characters;
	private eChar[][] board;
	private int health;
	private int money;
	
	public Environment() {   //Default Game Initialization Constructor
		eChar[][] temp = 
{{eChar.HCrab, eChar.BLANK, eChar.BCrab, eChar.BLANK},
 {eChar.BLANK, eChar.BLANK, eChar.BAMBOO, eChar.BLANK},
 {eChar.BLANK, eChar.BLANK, eChar.RESEARCHER, eChar.BLANK},		
 {eChar.BLANK, eChar.BLANK, eChar.BLANK, eChar.BLANK}};
		this.animals = null;
		this.characters = null;
		this.board = temp;
		this.health = 50;
		this.money = 200;
	}
	
	public Species[] getAnimals() {
		return animals;
	}
	
	public Character[] getCharacters() {
		return characters;
	}
	
	public eChar[][] getBoard() {
		return board;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setAnimals(Species[] animals) {
		this.animals = animals;
	}
	
	public void setCharacters(int[] charcters) {
		this.characters = characters;
	}
	
	public void setBoard(eChar[][] board) {
		this.board = board;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public void makeEvent(){
		
	}
	
	public void resolve() {
		
	}
	
	public void calcGrowth() {
		
	}
	
	public void calcHealth() {
		
	}
	
	public void calcMoney() {
		
	}
	
}