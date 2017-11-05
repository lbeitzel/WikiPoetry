package hackathon;

import java.util.ArrayList;

public class Limerick {
	String _personName;
	ArrayList<Phrase> _phraseList;
	ArrayList<ArrayList<Phrase>> _rhymeSets;
	private int _index = 0;
	
	public Limerick(String personName, ArrayList<Phrase> phraseList){
		_personName = personName;
		_phraseList = phraseList;
		RhymeSetCreator rsc = new RhymeSetCreator(_phraseList);
		rsc.generateRhymeSets();
		_rhymeSets = rsc._rhymeSets;
	}
	
	public void generateLimerick() {
		if(_rhymeSets.size() == 0){
			System.out.println("No Rhymes!");
		}
		else{
			if (_index  < _rhymeSets.size() - 1){
				System.out.println("=Limerick=");
				System.out.println();
				System.out.println("There once was a man named " + _personName);
				System.out.println(_rhymeSets.get(_index).get(0)._phrase);
				System.out.println(_rhymeSets.get(_index+1).get(0)._phrase);
				System.out.println(_rhymeSets.get(_index + 1).get(1)._phrase);
				System.out.println(_rhymeSets.get(_index).get(1)._phrase);
				_index++;
			}
			else{
				System.out.println("Out of rhymes!");
			}
		}
	}
}
