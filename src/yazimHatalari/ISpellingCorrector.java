package yazimHatalari;


public interface ISpellingCorrector {
    void putWord(String word, Integer frequency);
    String correct(String word);
    boolean containsWord(String word);
}
