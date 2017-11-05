package hackathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		// Takes in the Wikipedia page and makes a String of the wikiPage
		System.out.println("What Wikipedia page would you like to make poetry from?");
		Scanner scanner1 = new Scanner(System.in);
		String pageName = scanner1.nextLine();
		
		String finalPageName = pageName.replaceAll(" ", "%20");

		String wikiURL = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&explaintext=&titles=";
		String wikiPageFile = "";
		try {
			wikiPageFile = readToString(wikiURL + finalPageName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Cleans the file and converts it into a phraseList
		ArrayList<String> cleanedOutput = clean(wikiPageFile);

		ArrayList<Phrase> phraseList = new ArrayList<Phrase>();
		// print the outputs
		for (int i = 0; i < cleanedOutput.size(); i++) {
			// System.out.println(cleanedOutput.get(i));
			phraseList.add(new Phrase(cleanedOutput.get(i)));
		}

		// make a Haiku object
		Haiku haiku = new Haiku(phraseList);
		// make Limerick object
		Limerick limerick = new Limerick(pageName, phraseList);
		// make Free Verse object
		FreeVerse freeVerse = new FreeVerse(phraseList);
		

		boolean redo = true;
		while (redo) {
			// Takes in a desires poetry type
			System.out.println(
					"What kind of poem would you like to make? (Haiku(H), Limerick(L), Free Verse(F), or Quit(Q)");
			Scanner scanner2 = new Scanner(System.in);
			String poemType = scanner2.nextLine();

			if (poemType.equalsIgnoreCase("H") || poemType.equalsIgnoreCase("Haiku")) {
				haiku.generateHaiku();
			} 
			else if (poemType.equalsIgnoreCase("L") || poemType.equalsIgnoreCase("Limerick")) {
				limerick.generateLimerick();
			} 
			else if (poemType.equalsIgnoreCase("F") || poemType.equalsIgnoreCase("Free Verse")) {
				freeVerse.generateFreeVerse();
			} 
			else if (poemType.equalsIgnoreCase("Q") || poemType.equalsIgnoreCase("Quit")) {
				redo = false;
				break;
			} 
			else {
				System.out.println("Please enter a valid response");
			}
		}

	}

	
//	 // print the outputs
//	 for(int i = 0;i<cleanedOutput.size();i++)
//	 {
//	 System.out.println(cleanedOutput.get(i));
//	 phraseList.add(new Phrase(cleanedOutput.get(i)));
//	 }
//	
//	 RhymeSetCreator r = new
//	 RhymeSetCreator(phraseList);r.generateRhymeSets();for(
//	 int i = 0;i<r._rhymeSets.size();i++)
//	 {
//	 ArrayList<Phrase> h = r._rhymeSets.get(i);
//	 for (Phrase p : h) {
//	 System.out.println(p._phrase);
//	 }
//	 System.out.println("--------------");
//	 }

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
