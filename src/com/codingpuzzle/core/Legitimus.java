package com.codingpuzzle.core;

import java.util.HashSet;
import java.util.Set;

public class Legitimus {

	/**
	 * Recursively  and sequentially deal with the symbols
	 *
	 * @author Wei Ding
	 */

	private final String toLegitim;
	
	private int nextIndex = 0;
	
	@SuppressWarnings("serial")
	private Set<Character> opening = new HashSet<Character>(){{
		  add('{'); add('('); add('[');
	}};
	    
	public Legitimus(String inputstring) {
		toLegitim = inputstring;
	}
	
	private char nextChar(){
		char nextChar = this.toLegitim.charAt(this.nextIndex);
		this.nextIndex++;
		return nextChar;
	};
	
	public void legitim(){
		while(this.nextIndex < this.toLegitim.length()){
			char nextChar = this.nextChar();
			if(nextChar == '{')
            	this.pushBrace();
			else if(nextChar == '(')
            	this.pushParenthesis();
			else if(nextChar == '[')
            	this.pushBracket();
			else
            	throw new LegitimException("illegal character");
        }
	}
	
	private void pushBrace(){		
		char nextChar = this.nextChar();
		while(nextChar == '['){
			pushBracket();
			nextChar = this.nextChar();
		}
		if(nextChar == '}'){}
		else throw new LegitimException("illegal expresion at place "+
				this.nextIndex+" of "+nextChar );
	}
	
	private void pushParenthesis(){
		char nextChar = this.nextChar();
		while(nextChar == '{'){
			pushBrace();
			nextChar = this.nextChar();
		}
		//nextChar = this.nextChar();
		if(nextChar == ')'){System.out.println("ola");}
		else throw new LegitimException("illegal expresion at place "+
			this.nextIndex+" of "+nextChar );	
	}
	
	private void pushBracket(){
		char nextChar = this.nextChar();
		while(this.opening.contains(nextChar)){
			if(nextChar == '{') pushBrace();
			else if(nextChar == '[') pushBracket();
			else if(nextChar == '(') pushParenthesis();
			nextChar = this.nextChar();
		}
		//nextChar = this.nextChar();
		if(nextChar == ']') {}
		else throw new LegitimException("illegal expresion at place "+
					this.nextIndex+" of "+nextChar );
		
	}
		
}		
	
	
	
