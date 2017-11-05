package hackathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Shit {
	public static void main(String[] args) {
		try {
			String test = readToString("https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&explaintext=&titles=Steve%20Jobs");

			ArrayList<String> cleanedOutput = clean(test);

			ArrayList<Phrase> phraseList = new ArrayList<Phrase>();
			// print the outputs
			for (int i = 0; i < cleanedOutput.size(); i++) {
				System.out.println(cleanedOutput.get(i));
				phraseList.add(new Phrase(cleanedOutput.get(i)));
			}
			
//			Haiku h = new Haiku(phraseList);
//			h.generateHaiku();
//			h.generateHaiku();
//			h.generateHaiku();
//			h.generateHaiku();
//			h.generateHaiku();
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String readToString(String targetURL) throws IOException {
		String rawArticle = "";
		URL wikiurl = new URL(targetURL);
		BufferedReader in = new BufferedReader(new InputStreamReader(wikiurl.openStream()));

		String inputLine;
		while ((inputLine = in.readLine()) != null)
			rawArticle += inputLine;
		in.close();
		return rawArticle;
	}

	public static ArrayList<String> clean(String test) {
		ArticleParser ap = new ArticleParser(test);
		// removes the header from the raw String
		String betterTest = ap.removeHeader();

		// adds the entire string as the first index of the inPhrases ArrayList
		ArrayList<String> inPhrases = new ArrayList<String>();
		inPhrases.add(betterTest);

		// stores the ArrayList of sentences in variable output
		ArrayList<String> output = new ArrayList<String>();

		output = ap.betterSplit("\\.", inPhrases);

		output = ap.betterSplit(",", output);

		output = ap.betterSplit(" and ", output);

		output = ap.betterSplit(" or ", output);

		output = ap.betterSplit(" but ", output);

		output = ap.betterSplit(" u ", output);

		output = ap.betterSplit(" s ", output);
		

		// cleans the \n's
		output = ap.removeLeadingNewLines(output);
		// cleans the unicode u's
		output = ap.removeUnicodeUs(output);
		// cleans the leading spaces of the sentences
		output = ap.removeLeadingSpaces(output);

		// cleans the other undesired characters from the sentences
		output = ap.removeUndesiredCharacters(output);
		
		// cleans the leading spaces of the sentences
		output = ap.removeLeadingSpaces(output);

		// cleans up ending bs
		output = ap.removeEndingBs(output);
		
		// cleans the endingSpaces
		output = ap.removeEndingSpaces(output);

		return output;
	}

}
