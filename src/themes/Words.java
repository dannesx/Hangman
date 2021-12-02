package themes;

import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Words {
  Random random = new Random();
  private static HashMap<String, ArrayList<String>> themes = new HashMap<>();
  private final String[] themeNames = { "Animal", "Color", "Country", "Famous Singer", "Rock Band" };

  public Words() {
    themes.put("Animal",
        new ArrayList<String>(Arrays.asList("Dog", "Cat", "Horse", "Mouse", "Fox", "Elephant", "Lion", "Dolphin",
            "Sheep", "Wolf", "Bear", "Giraffe")));
    themes.put("Color",
        new ArrayList<String>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple", "White", "Black", "Orange",
            "Cyan", "Pink", "Brown", "Grey")));
    themes.put("Country", new ArrayList<String>(
        Arrays.asList("Brazil", "United States", "Canada", "Italy", "Portugal", "Spain", "France", "Japan",
            "United Kingdom", "Germany", "Australia", "Netherlands")));
    themes.put("Famous Singer",
        new ArrayList<String>(Arrays.asList("Michael Jackson", "Freddie Mercury", "David Bowie", "Beyonce", "Rihanna",
            "Lady Gaga", "Mariah Carey", "Elton John", "Justin Timberlake", "Justin Bieber", "Adele", "Harry Styles")));
    themes.put("Rock Band",
        new ArrayList<String>(Arrays.asList("Queen", "Led Zeppelin", "ACDC", "Kiss", "Deep Purple", "Pink Floyd",
            "The Beatles", "Ramones", "The Rolling Stones", "Metallica", "Megadeth", "Black Sabbath")));
  }

  public String[] getWord() {
    int randomTheme = random.nextInt(themeNames.length);
    int randomWord = random.nextInt(themes.get(themeNames[randomTheme]).size());

    String theme = themeNames[randomTheme];
    String word = themes.get(themeNames[randomTheme]).get(randomWord);
    String[] round = { theme, word };

    return round;
  }
}
