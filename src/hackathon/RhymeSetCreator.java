package hackathon;

import java.util.ArrayList;
import java.util.Comparator;

public class RhymeSetCreator implements Comparator{
	ArrayList<ArrayList<Phrase>> _rhymeSets;
	ArrayList<Phrase> _allPhrases;
	final int PHRASE_LIMITER = 150;
	RhymeSetCreator(ArrayList<Phrase> allPhrases){
		_allPhrases = allPhrases;
		_rhymeSets = new ArrayList<ArrayList<Phrase>>();
	}
	
	public void generateRhymeSets() {
		for(int i = 0; i < _allPhrases.size() && i < PHRASE_LIMITER; i++) {
			Phrase phrase = _allPhrases.get(i);
			phrase.generateRhymes();
		}
		for(int i = 0; i< _allPhrases.size() && i < PHRASE_LIMITER; i++) {
			Phrase currentPhrase = _allPhrases.get(i);
			ArrayList<String> currentRhymes = currentPhrase._rhymes;
			ArrayList<Phrase> rhymeSet = new ArrayList<Phrase>();
			for(int j = 0; j < currentRhymes.size(); j++) {
				for(int k = 0; k < _allPhrases.size(); k++) {
					if(currentRhymes.get(j).equals(_allPhrases.get(k)._lastWord)){
						rhymeSet.add(_allPhrases.get(k));
					}
				}
			}
			rhymeSet.add(currentPhrase);
			if(rhymeSet.size() > 1) {
				_rhymeSets.add(rhymeSet);
			}
		}
		cleanSets();
	}
	
	public void cleanSets() {
		_rhymeSets.sort(this); //sorts by rhymeset size
		ArrayList<ArrayList<Phrase>> cleanedSet = new ArrayList<ArrayList<Phrase>>();
		for(int i = 0; i < _rhymeSets.size(); i++) {
			boolean repeat = false;
			ArrayList<Phrase> repeatRhymes = new ArrayList<Phrase>();
			for(int j = 0; j < _rhymeSets.size(); j++) {
				if(i != j) {
					if(_rhymeSets.get(i).get(0).equals(_rhymeSets.get(j).get(0))){
						repeat = true;
						repeatRhymes = _rhymeSets.get(j);
					}
				}
			}
			if(repeat) {
				if(repeatRhymes.size() > _rhymeSets.get(i).size()) {
					cleanedSet.add(repeatRhymes);
					cleanedSet.remove(i);
				}
			}
			else {
				cleanedSet.add(_rhymeSets.get(i));
			}
		}
		_rhymeSets = cleanedSet;
	}

	@Override
	public int compare(Object o1, Object o2) {
		ArrayList<Phrase> rhyme1 = (ArrayList<Phrase>) o1;
		ArrayList<Phrase> rhyme2 = (ArrayList<Phrase>) o2;
		return rhyme2.size() - rhyme1.size();
	}
}
