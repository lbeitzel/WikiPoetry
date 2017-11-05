package hackathon;

import java.util.ArrayList;

public class Haiku {
	ArrayList<Phrase> syb5phrases;
	ArrayList<Phrase> syb7phrases;
	int index = 0;
	public Haiku(ArrayList<Phrase> phraseList) {
		syb5phrases = new ArrayList<Phrase>();
		syb7phrases = new ArrayList<Phrase>();
		for(int i = index; (i < phraseList.size() && syb5phrases.size() < 2 && syb7phrases.size() < 1); i++){
			Phrase currentPhrase = phraseList.get(i);
			System.out.println(currentPhrase._phrase);
			if(currentPhrase._phrase.length() < 30){
				currentPhrase.getSyllables();
				if(currentPhrase._syllables == 5){
					syb5phrases.add(currentPhrase);
				}
				else if(currentPhrase._syllables == 7){
					syb7phrases.add(currentPhrase);
				}
			}
			currentPhrase.getSyllables();
		}
		for(int i = 0; i<syb5phrases.size();i++)
		{ 
			System.out.println(syb5phrases.get(i)._phrase);
		}
		System.out.println("End of 5");
	
		for(int i = 0; i<syb7phrases.size();i++)
		{ 
			System.out.println(syb7phrases.get(i)._phrase);
		}
	
	
	
	}
	
	
}
