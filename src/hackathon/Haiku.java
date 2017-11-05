package hackathon;

import java.util.ArrayList;

public class Haiku {
	ArrayList<Phrase> syb5phrases;
	ArrayList<Phrase> syb7phrases;
	int index = 0;
	ArrayList<Phrase> _phraseList;
	public Haiku(ArrayList<Phrase> phraseList) {
		_phraseList = phraseList;
	}
	public void generateHaiku() {
		syb5phrases = new ArrayList<Phrase>();
		syb7phrases = new ArrayList<Phrase>();
		for(; index < _phraseList.size(); index++){
			Phrase currentPhrase = _phraseList.get(index);
			//System.out.println(currentPhrase._phrase);
			if(currentPhrase._phrase.length() < 30){
				currentPhrase.getSyllables();
				if(currentPhrase._syllables == 5){
					syb5phrases.add(currentPhrase);
				}
				else if(currentPhrase._syllables == 7){
					syb7phrases.add(currentPhrase);
				}
			}
			if(syb5phrases.size() >= 2 && syb7phrases.size() >= 1) {
				break;
			}
		}
		index ++;
		if(syb5phrases.size() > 1 && syb7phrases.size() > 0) {
			System.out.println("=Haiku=");
			//Prints first 5 line
			System.out.println(syb5phrases.get(0)._phrase);
			//Prints first 7 line
			System.out.println(syb7phrases.get(0)._phrase);
			//Prints first 5 line
			System.out.println(syb5phrases.get(1)._phrase);
			System.out.println();
		}
		else {
			System.out.println("No More Haikus");
		}
	}
	
	
}
