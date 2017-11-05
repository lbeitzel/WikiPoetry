package hackathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Phrase {
	String _phrase;
	String _lastWord;
	ArrayList<String> _rhymes;
	static int _syllables;
	
	Phrase(String phrase){
		_phrase = phrase;
		_lastWord = "";
		for(int i = _phrase.length() - 1; i >= 0 && (_phrase.charAt(i) != ' '); i--){
			_lastWord += _phrase.charAt(i);
		}
		_lastWord = new StringBuilder(_lastWord).reverse().toString();
		try {
			String rawRhymeOutput = readToString(_lastWord);
			_rhymes = parseRhymes(rawRhymeOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//getSyllables(_phrase);
	}
	
	public static String readToString(String word) throws IOException {
		String rawArticle = "";
		URL wikiurl = new URL("https://api.datamuse.com/words?rel_rhy=" + word);
	    BufferedReader in = new BufferedReader(
	    new InputStreamReader(wikiurl.openStream()));
	    String inputLine;
	    while ((inputLine = in.readLine()) != null)
	        rawArticle += inputLine;
	    in.close();
	    return rawArticle;
	}
	public static String readSyllablesString(String word) throws IOException {
		String rawSyllableData = "";
		URL wikiurl = new URL("http://api.datamuse.com/words?sp=" + word + "&qe=sp&md=s&max=1");
	    BufferedReader in = new BufferedReader(
	    new InputStreamReader(wikiurl.openStream()));
	    String inputLine;
	    while ((inputLine = in.readLine()) != null)
	    	rawSyllableData += inputLine;
	    in.close();
	    return rawSyllableData;
	}
	
	public static ArrayList<String> parseRhymes(String rawText){
		ArrayList<String> retVal = new ArrayList<String>();
		for(int i = 0; i < rawText.length() - 6; i++){
			String sub = rawText.substring(i, i+7);
			if(sub.equals("word\":\"")){
				String word = "";
				for(int j = i + 7; rawText.charAt(j) != '"'; j++){
					word += rawText.charAt(j);
				}
				if(!word.contains(" ")){
					retVal.add(word);
				}
			}
		}
		return retVal;
		
	}
	public static void getSyllables(String phrase){
		int syllables = 0;
		String[] words = phrase.split(" ");
		String[] rawText = new String[words.length];
		for(int i = 0; i < words.length; i++){
			try {
				rawText[i] = readSyllablesString(words[i]);
				for(int j = 0; j < rawText[i].length() - 3; j++){
					String sub = rawText[i].substring(j, j+4);
					if(sub.equals("es\":")){
						syllables += Integer.parseInt(rawText[i].substring(j+4, j+5));
					}
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(syllables);
		_syllables = syllables;
	}
}

