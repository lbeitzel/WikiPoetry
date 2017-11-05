package hackathon;

import java.util.ArrayList;

public class FreeVerse {
	ArrayList<Phrase> _phraseList;
	ArrayList<ArrayList<Phrase>> _rhymeSets;

	public FreeVerse(ArrayList<Phrase> phraseList) {
		_phraseList = phraseList;
		RhymeSetCreator rsc = new RhymeSetCreator(_phraseList);
		rsc.generateRhymeSets();
		_rhymeSets = rsc._rhymeSets;
	}

	public void generateFreeVerse() {
		System.out.println("=Free Verse=");
		System.out.println();
		for (int i = 0; i < _rhymeSets.size(); i++) {
			System.out.println(_rhymeSets.get(i).get(0)._phrase);
			System.out.println(_rhymeSets.get(i).get(1)._phrase);
		}
		// System.out.println(_rhymeSets.get(_index).get(0)._phrase);
		// System.out.println(_rhymeSets.get(_index+1).get(0)._phrase);
		// System.out.println(_rhymeSets.get(_index + 1).get(1)._phrase);
		// System.out.println(_rhymeSets.get(_index).get(1)._phrase);
	}
}