import java.util.ArrayList;

/**
 * Container class that holds information per line
 * @author Thomas Yang
 *
 */
public class Instance {
	public ArrayList<Integer> binaries; //3
	public ArrayList<Double> reals; //142
	public int year; //2001,2002,3,4,5,6 (6 choices)
	public ArrayList<Integer> tens; //26
	public ArrayList<Integer> hundreds; //4
	public int classVar;
	
	public Instance(){
		binaries = new ArrayList<Integer>();
		reals = new ArrayList<Double>();
		year = -1;
		tens = new ArrayList<Integer>();
		hundreds = new ArrayList<Integer>();
		classVar = -1;
	}
	
	public void addBinary(int num){
		binaries.add(num);
	}
	
	public void addReal(double num){
		reals.add(num);
	}
	
	public void addYear(int y){
		year = y;
	}
	
	public void addTens(int num){
		tens.add(num);
	}
	
	public void addHundreds(int num){
		hundreds.add(num);
	}
	
	public void addClass(int classV){
		classVar = classV;
	}
	
	public ArrayList<Integer> getBin(){
		return binaries; 
	}
	
	public ArrayList<Double> getReals(){
		return reals;
	}
	
	public int getYear() {
		return year;
	}
	
	public ArrayList<Integer> getTens(){
		return tens;
	} 
	
	public ArrayList<Integer> getHundreds(){
		return hundreds;
	}
		
	public int getClassVar() {
		return classVar;
	}
	
}
