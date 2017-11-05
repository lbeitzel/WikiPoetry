package hackathon;

import java.util.ArrayList;
import java.util.HashSet;

public class rhymeSetCreator {
	ArrayList<HashSet<Phrase>> _rhymeSets;
	ArrayList<Phrase> _allPhrases;
	rhymeSetCreator(ArrayList<Phrase> allPhrases){
		_allPhrases = allPhrases;
		_rhymeSets = new ArrayList<HashSet<Phrase>>();
		generateRhymeSets(allPhrases);
	}
	private void generateRhymeSets(ArrayList<Phrase> allPhrases) {
		for(int i = 0; i< allPhrases.size() || i < 100; i++) {
			Phrase phrase = allPhrases.get(i);
			phrase.generateRhymes();
		}
		for(int i = 0; i< allPhrases.size() || _rhymeSets.size() < 2; i++) {
			Phrase phrase = allPhrases.get(i);
			for(int j = 0; j < phrase._rhymes.size(); j++) {
				HashSet<Phrase> rhymeSet = new HashSet<Phrase>();
				for(int k = 0; k < allPhrases.size(); k++) {
					if(allPhrases.get(k)._lastWord.equals(phrase._rhymes.get(j))) {
						rhymeSet.add(allPhrases.get(k));
					}
				}
				if(rhymeSet.size() != 0) {
					rhymeSet.add(phrase);
					_rhymeSets.add(rhymeSet);
				}
			}
		}
		
		
	}
}
