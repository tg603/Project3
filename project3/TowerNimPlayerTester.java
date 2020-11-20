public class TowerNimPlayerTester{
	
public static void main(String[] args) {
Player<TowerNim> random = new RandomPlayer<TowerNim>();
Player<TowerNim> TG = new TowerNimPlayer();
PositionFactory<TowerNim> factory = new TowerNim.PositionBuilder(8, 8);
Referee ref = new Referee(TG, random, factory);
ref.call();
ref.gauntlet(10000);
}
}