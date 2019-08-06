package yazimHatalari;

import java.util.*;
import java.io.*;
import java.util.stream.Stream;


public class SpellingCorrector implements ISpellingCorrector {

    // word to count map - how may times a word is present - or a weight attached to a word
    private Map<String, Integer> dictionary = null;

    private final static char[] ch = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'r', 's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z', 'x', 'w', 'q', '\''};

    public SpellingCorrector(int lruCount) {
        this.dictionary = Collections.synchronizedMap(new LruCache<>(lruCount));
        File f = new File("kelimevesiklik.txt");

        try {
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {

                putWord(sc.next(), sc.nextInt());
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("hata");
        }

    }

    public SpellingCorrector() {
    }
    

    @Override
    public void putWord(String word, Integer frequency) {

        dictionary.put(word, frequency);
    }

    @Override
    public String correct(String word) {
        if (word == null || word.trim().isEmpty()) {
            return word;
        }

         word = word.toLowerCase();
        // If the word exists in our dictionary then return
        if (dictionary.containsKey(word)) {
            return word;
        }

        Map<String, Integer> possibleMatches = new HashMap<>();

        List<String> closeEdits = wordEdits(word);
        for (String closeEdit : closeEdits) {
            if (dictionary.containsKey(closeEdit)) {
                possibleMatches.put(closeEdit, this.dictionary.get(closeEdit));
            }
        }

        if (!possibleMatches.isEmpty()) {
            // Sorted least likely first
            Object[] matches = this.sortByValue(possibleMatches).keySet().toArray();

            // Try to match anything of the same length first
//            String bestMatch = "";
//            for (Object o : matches) {
//                if (o.toString().length() == word.length()) {
//                    bestMatch = o.toString();
//                }
//            }
//
//            if (!bestMatch.trim().isEmpty()) {
//                return bestMatch;
//            }

            // Just return whatever is the best match
            return matches[matches.length - 1].toString();
        }

        // Ok we did't find anything, so lets run the edits function on the previous results and use those
        // this gives us results which are 2 characters away from whatever was entered
        List<String> furtherEdits = new ArrayList<>();
        for (String closeEdit : closeEdits) {
            furtherEdits.addAll(this.wordEdits(closeEdit));
            
        }

        for (String futherEdit : furtherEdits) {
            if (dictionary.containsKey(futherEdit)) {
                possibleMatches.put(futherEdit, this.dictionary.get(futherEdit));
            }
        }

        if (!possibleMatches.isEmpty()) {
            // Sorted least likely first
            Object[] matches = this.sortByValue(possibleMatches).keySet().toArray();

            // Try to match anything of the same length first
            String bestMatch = "";
            for (Object o : matches) {
                if (o.toString().length() == word.length()) {
                    bestMatch = o.toString();
                }
            }

            if (!bestMatch.trim().isEmpty()) {
                return bestMatch;
            }

            // Just return whatever is the best match
            return matches[matches.length - 1].toString();
        }

        // If unable to find something better return the same string
        return word;
    }

    @Override
    public boolean containsWord(String word) {
        if (dictionary.containsKey(word)) {
            return true;
        }

        return false;
    }

 
    public List<String> wordEdits(String word) {
        List<String> closeWords = new ArrayList<String>();

        for (int i = 0; i < word.length() + 1; i++) {
            for (char character : ch) {
                // Maybe they forgot to type a letter? Try adding one
                StringBuilder sb = new StringBuilder(word);
                sb.insert(i, character);
                closeWords.add(sb.toString());
            }

        }

        for (int i = 0; i < word.length(); i++) {
            for (char character : ch) {
                // Maybe they mistyped a single letter? Try replacing them all
                StringBuilder sb = new StringBuilder(word);
                sb.setCharAt(i, character);
                closeWords.add(sb.toString());
            }
            // Maybe they added an extra letter? Try deleting one
            StringBuilder sb = new StringBuilder(word);
            sb.deleteCharAt(i);
            closeWords.add(sb.toString());

        }
       

        return closeWords;
    }

   
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        st.sorted(Map.Entry.comparingByValue()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

    /**
     * A very simple LRU cache implementation that can be used for random data
     * types.
     */
    public class LruCache<A, B> extends LinkedHashMap<A, B> {

        private final int maxEntries;

        public LruCache(final int maxEntries) {
            super(maxEntries + 1, 1.0f, true);
            this.maxEntries = maxEntries;
        }

        @Override
        protected boolean removeEldestEntry(final Map.Entry<A, B> eldest) {
            return super.size() > maxEntries;
        }
    }

}
