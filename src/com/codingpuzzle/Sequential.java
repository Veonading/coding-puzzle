package com.codingpuzzle;
import com.codingpuzzle.core.LegitimException;
import com.codingpuzzle.core.Legitimus;
//import com.codingpuzzle.core.ParserException;

/**
 * Check the legitimacy of a single string
 *
 * @author Wei Ding
 */
public class Sequential {	
	public static void main(String[] args) {
        System.out.println("Running sequentially...");
        String inputString = args[0];
        Legitimus legitimus = new Legitimus(inputString);
        try {
            legitimus.legitim();
            System.out.println("true");
        } catch (LegitimException e) {
            System.out.println("false");
        }
    }
}