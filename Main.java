import java.io.IOException;

/**
 * This is the main file
 * The execution takes in two inputs in the order of training set and then testing set
 * If the execution is valid(checks if the number of arguments is 2) then it executes code
 * The training set is used in order to train the Naive Bayes classifier
 * The training and testing sets are then run through in order to find the accuracy 
 */
public class Main {
	public static void main(String [] args) throws IOException{
		// Read input file and run algorithm to create a decision tree
		if(args.length!= 2){
			System.out.println("The proper input is: java Assignment1 <Training Set> <Test Set>");
		}
		NaiveBayes algo = new NaiveBayes();
		String trainSet = args[0];
		String testSet = args[1];
		
		//train and rints the parameters of the classifier that were estimated from the training set
		algo.runAlg(trainSet);
		algo.printAll();
		
		//use Naive Bayes to determine the accuracy of the training and test sets
		double trainAcc = algo.predict(trainSet);
		System.out.printf("Accuracy on training set (" + algo.getNumInstances() + " instances): %.1f %% \n", trainAcc);
		double testAcc = algo.predict(testSet);
		System.out.printf("Accuracy on test set (" + algo.getNumInstances() + " instances): %.1f %% \n" , testAcc);

	}
}
