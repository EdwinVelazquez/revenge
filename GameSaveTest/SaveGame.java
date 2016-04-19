
public class SaveGame extends CSV{
	
	//list of the names in csv file
	//might add more things as the game gets more complicated
	static final int name = 0;
	static final int maxScores = 1;
	
	//saveGame is static cause there is only one. 
	static SaveGame s = new SaveGame();
	//static arrays of the columns (because there is only one saveGame.csv)
	static String names[] = s.getAllNames();
	static int[] scores = s.getAllScores();

	public SaveGame() {
		//file needs to be in the same folder or give the path of the file
		super("saveGame.csv");
	}
	
	/**
	 * this method gets all the score
	 * NOTE will return error if score is not a int
	 * @return a int array of all score
	 */
	public int[] getAllScores(){
		String[] score = this.readColumn(maxScores,true);
		int[] returnArray = new int[score.length];
		//might move this block into its own method if more things are ints or might make another method in the super class
		for(int i = 0; i < score.length; i++){
			returnArray[i] = Integer.parseInt(score[i]);
		}
		return returnArray;
	}
	/**
	 * this method gets all names in te saveGame.csv
	 * @return string array of all names
	 */
	public String[] getAllNames(){
		return this.readColumn(name,true);
	}
	
	//testing purpose
	public static void main(String args[])
	  {	
		for(int i = 0; i < scores.length;i++){
			System.out.println(names[i]);			
			System.out.println(scores[i]);
		}
		String[] a = {"player","12312"};
		s.addRow(a);
	  }
	

}