package hackathon;

import java.util.ArrayList;
import java.util.HashSet;

public class RhymeSetCreator {
	ArrayList<ArrayList<Phrase>> _rhymeSets;
	ArrayList<Phrase> _allPhrases;
	RhymeSetCreator(ArrayList<Phrase> allPhrases){
		_allPhrases = allPhrases;
		_rhymeSets = new ArrayList<ArrayList<Phrase>>();
	}
	public void generateRhymeSets() {
		for(int i = 0; i< _allPhrases.size() || i < 100; i++) {
			Phrase phrase = _allPhrases.get(i);
			phrase.generateRhymes();
		}
		//iterate though all phrases
		for(int i = 0; i< _allPhrases.size() || _rhymeSets.size() < 5; i++) {
			Phrase phrase = _allPhrases.get(i);
			//iterate through all rhymes of each phrase
			for(int j = 0; j < phrase._rhymes.size(); j++) {
				ArrayList<Phrase> rhymeSet = new ArrayList<Phrase>();
				String currentRhyme = phrase._rhymes.get(j);
				//iterate though all other words (k) for each rhyme at j
				for(int k = 0; k < _allPhrases.size(); k++) {
					Phrase checkAllForRhymes = _allPhrases.get(k);
					if(currentRhyme.equals(checkAllForRhymes._lastWord)){
							rhymeSet.add(checkAllForRhymes);
					}
				}
				//making sure empty sets (sets with no other rhymes are added)
				if(rhymeSet.size() != 0) {
					rhymeSet.add(phrase);
					_rhymeSets.add(rhymeSet);
				}
			}
		}
		
		
	}
}
