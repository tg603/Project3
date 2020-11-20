public class TowerNimPlayer extends Player<TowerNim>{

		
	public TowerNimPlayer(){
		//no state necessary
	}

	public String toString(){
		//returning name of my player
		return "TG";
	}
	public TowerNim getMove(TowerNim position, int playerId){
			PureStack<Integer> game = position.getPiles();
		int sticks = game.pop(); 
		if(sticks > 1){
			game.push(sticks - 1);
			return new TowerNim(game);
		}
		return new TowerNim(game);
	}
	
	
	public static void main(String[] args){
	}
}