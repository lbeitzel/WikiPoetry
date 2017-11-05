package hackathon;

import java.util.ArrayList;

public class ArticleParser {

	String _inputFile;
	
	final int MIN_PHRASE_LENGTH = 15;
	
	public ArticleParser(String inputTextFile) {
		_inputFile = inputTextFile;
	}
	
	public String removeHeader() {
		return _inputFile.substring(_inputFile.indexOf("\"extract\"") + 11, _inputFile.indexOf("= References"));
	}
	
	public ArrayList<String> betterSplit(String splitPoint, ArrayList<String> inPhrases) {
		ArrayList<String> outPhrases = new ArrayList<String>();
		String[] array;
		for(int i = 0; i < inPhrases.size(); i++) {
			array = inPhrases.get(i).split(splitPoint);
			for(int j = 0; j < array.length; j++) {
				if(array[j].length() >= MIN_PHRASE_LENGTH) {
					outPhrases.add(array[j]);
				}
			}
		}
		return outPhrases;
	}
	
	public ArrayList<String> removeLeadingSpaces(ArrayList<String> inPhrases) {
		ArrayList<String> outPhrases = new ArrayList<String>();
		for(int i = 0; i < inPhrases.size(); i++) {
			if (inPhrases.get(i).charAt(0) == ' ') {
				outPhrases.add(inPhrases.get(i).substring(1));
			} 
			else {
				outPhrases.add(inPhrases.get(i));
			}
		}
		return outPhrases;
	}
	
	//make remove endingSpaces
	public ArrayList<String> removeEndingSpaces(ArrayList<String> inPhrases) {
		ArrayList<String> outPhrases = new ArrayList<String>();
		for(int i = 0; i < inPhrases.size(); i++) {
			if (inPhrases.get(i).charAt(inPhrases.get(i).length()-1) == ' ') {
				outPhrases.add(inPhrases.get(i).substring(0,inPhrases.get(i).length()-1));
			} 
			else {
				outPhrases.add(inPhrases.get(i));
			}
		}
		return outPhrases;
	}
	
	//make remove endingSpaces
		public ArrayList<String> removeEndingBs(ArrayList<String> inPhrases) {
			ArrayList<String> outPhrases = new ArrayList<String>();
			for(int i = 0; i < inPhrases.size(); i++) {
				if (inPhrases.get(i).charAt(inPhrases.get(i).length()-1) == 'b') {
					outPhrases.add(inPhrases.get(i).substring(0,inPhrases.get(i).length()-1));
				} 
				else {
					outPhrases.add(inPhrases.get(i));
				}
			}
			return outPhrases;
		}
	
	
	
	public ArrayList<String> removeLeadingNewLines(ArrayList<String> inPhrases) {
		ArrayList<String> outPhrases = new ArrayList<String>();

		for (int i = 0; i < inPhrases.size(); i++) {
			String phrase = "";
			
			for (int j = 0; j < inPhrases.get(i).length() - 1; j++) {
				char c = inPhrases.get(i).charAt(j);
				char d = inPhrases.get(i).charAt(j + 1);
				if (c == '\\' && d == 'n') {
					j++;
				}
				else {
					phrase += c;

					if (j == inPhrases.get(i).length() - 2) {
						phrase += d;
					}
				}
			}			
			if(phrase.length() >= MIN_PHRASE_LENGTH) {
				outPhrases.add(phrase);
			}
			
		}
		return outPhrases;
	}
	
	public ArrayList<String> removeUnicodeUs(ArrayList<String> inPhrases) {
		ArrayList<String> outPhrases = new ArrayList<String>();

		for (int i = 0; i < inPhrases.size(); i++) {
			String phrase = "";
			
			for (int j = 0; j < inPhrases.get(i).length() - 1; j++) {
				char c = inPhrases.get(i).charAt(j);
				char d = inPhrases.get(i).charAt(j + 1);
				if (c == '\\' && d == 'u') {
					j++;
				}
				else {
					phrase += c;

					if (j == inPhrases.get(i).length() - 2) {
						phrase += d;
					}
				}
			}			
			if(phrase.length() >= MIN_PHRASE_LENGTH) {
				outPhrases.add(phrase);
			}
			
		}
		return outPhrases;
	}
	
	public ArrayList<String> removeUndesiredCharacters(ArrayList<String> inPhrases) {
		ArrayList<String> outPhrases = new ArrayList<String>();
		
		//loops through each of the phrases in ArrayList inPhrases
		for (int i = 0; i < inPhrases.size(); i++) {
			String outPhrase = "";
			String currentPhrase = inPhrases.get(i);
			//loops through each of the characters of the currentPhrase
			for(int j = 0; j < currentPhrase.length(); j++) {
				char c = currentPhrase.charAt(j);
				//if the current character is a desired character
				if((c == 32) || (c == 39) || (c >=65 && c <= 90) || (c >=97 && c <= 122)){
					outPhrase += c;
				}
				if(c == 45){
					outPhrase += " ";
				}
				//else don't add that character to the String outPhrase
			}
			//adds the cleaned phrase to the list of phrases
			if(outPhrase.length() >= MIN_PHRASE_LENGTH) {
				outPhrases.add(outPhrase);
			}
		}
		return outPhrases;
	}
	
	
}
