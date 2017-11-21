/**
 * This is the class that is utilized in order to represent a binary number.
 * This is used in building the classifier in NaiveBayes.java
 */

public class BinaryNumber {
	private int identity;
	private int numBits;
	private String binaryNum;
	private String classifier;
	
	/**
	 * Creates a BinaryNumber object with the given identitiy
	 * @param ident the value of the binary number
	 */
	public BinaryNumber(int ident){
		identity = ident;
		binaryNum = convertBin(identity);
		classifier = "";
	}
	/**
	 * The number of bits in this binary number is equal to the number of attribute counts that there are
	 * This BinaryNumber class constructor creates a class that contains the integer that is in decimal as well
	 * as the binary number in String format.
	 * @param ident the decimal number that will be identifying the binary number
	 * @param attribCount the attribute count that will b the number of bits for the binary number
	 */
	public BinaryNumber(int ident, int attribCount){
		identity = ident;
		numBits = attribCount;
		binaryNum = convertBin(identity);
	}
	
	/**
	 * Converts the decimal number into a binary number
	 * @param decNum the decimal number that is being converted
	 * @return binNum the binary number in String format
	 */
	private String convertBin(int decNum){
		//The string that is being returned
		String binNum = "";
		
		//while the decimal number is greater than 0 
		while(decNum > 0){
			binNum = decNum%2+binNum;	//put the numbers remainder onto the beginning of the string
			decNum = decNum/2;			//divide the number by 2
		}
		//checking length requirements
		int retLength = binNum.length();
		int needLength = getNumBits();
		
		//while the length is less than the needed length (the bit length that is given while making)
		while(retLength < needLength){
			binNum = "0" + binNum;		//append a zero to the beginning until the retlength is equal to the needed length
			retLength = binNum.length();
		}
		return binNum;
	}
	
	/**
	 * Returns this numbers decimal value
	 * @return identity the decimal value
	 */
	public int getDecimal(){
		return identity;
	}
	
	/**
	 * returns this numbers amount of bits in the binary number 
	 * @return numBits the number of bits in the binary number
	 */
	public int getNumBits(){
		return numBits;
	}
	
	/**
	 * returns this numbers binary value
	 * @return binaryNum the binary value in String form
	 */
	public String getBinary(){
		return binaryNum;
	}
	
	/**
	 * returns what the binary number is supposed to return by processing the training data
	 * @return classifier the string representing 1 or 0
	 */
	public String getClassifier(){
		return classifier;
	}
	
	/**
	 * sets the classifier bit as a string
	 * @param bit either a 0 or a 1
	 */
	public void setClassifier(String bit){
		classifier = bit;
	}
}
