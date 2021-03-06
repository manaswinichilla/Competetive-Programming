import java.util.*;
public class cyptkicker {

    static int ALPHABET=26;
    static int ASCIILetter=97;
    static int ASCIINum=33;
    public static boolean letterVisited(char wordcLetter, char wordLetter, char[] mapLetters)
    {
        for (int i = 0; i < ALPHABET; i++)
        {
            if (mapLetters[i] == wordLetter && i != (wordcLetter - ASCIILetter))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean wordToword(String wordc, String eachWord, char[] mapLetters)
    {
        char[] copyMapLetters = new char[ALPHABET];
        System.arraycopy(mapLetters, 0, copyMapLetters, 0, mapLetters.length);
        for (int i = 0; i < wordc.length(); i++)
        {
            if ((mapLetters[wordc.charAt(i) - ASCIILetter] != eachWord.charAt(i) && mapLetters[wordc.charAt(i) - ASCIILetter] != '*') || !letterVisited(wordc.charAt(i), eachWord.charAt(i), mapLetters))
            {

                System.arraycopy(copyMapLetters, 0, mapLetters, 0, mapLetters.length);
                return false;
            }
            else
            {
                mapLetters[wordc.charAt(i) - ASCIILetter] = eachWord.charAt(i);
            }
        }

        return true;
    }

    public static String wordFound(String word)
    {
        if (word.length() > 0)
        {
            StringBuilder mapped = new StringBuilder();
            int pos = 0;
            HashMap<Character, Character> hashmap = new HashMap<>();
            for (int i = 0; i < word.length(); i++)
            {
                if (!hashmap.containsKey(word.charAt(i)))
                {
                    hashmap.put(word.charAt(i), (char) (ASCIINum + pos++));
                }
            }
            for (int i = 0; i < word.length(); i++)
            {
                mapped.append(hashmap.get(word.charAt(i)));
            }
            return mapped.toString();

        }
        else
        {
            return "";
        }
    }

    public static boolean mapWords(String[] vocab, String[] wordsCopy, char[] mapLetters)
    {
        ArrayList<String> options = new ArrayList<>();
        String wordc = wordsCopy[0];
        char[] copyMapLetters = new char[ALPHABET];
        System.arraycopy(mapLetters, 0, copyMapLetters, 0, copyMapLetters.length);
        for (String eachWord : vocab)
        {
            if (eachWord.length() == wordc.length() && wordFound(eachWord).equals(wordFound(wordc)))
            {
                options.add(eachWord);
            }
        }
        for (String eachWord : options)
        {
            if (wordToword(wordc, eachWord, mapLetters))
            {
                if (wordsCopy.length > 1)
                {
                    if (mapWords(vocab, Arrays.copyOfRange(wordsCopy, 1, wordsCopy.length), mapLetters))
                    {
                        return true;
                    }
                    else
                    {
                        System.arraycopy(copyMapLetters, 0, mapLetters, 0, mapLetters.length);
                    }
                }
                else
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] cryptKicker(String[] words, String[] vocab)
    {
        String[] afterWords = new String[words.length];
        String[] wordsCopy = Arrays.copyOf(words, words.length);
        Arrays.sort(wordsCopy, new Comparator<String>()
        {
            @Override
            public int compare(String o1, String o2)
            {
                return Integer.compare(o2.length(), o1.length());
            }
        });
        char[] mapLetters = new char[ALPHABET];
        for (int i = 0; i < ALPHABET; i++)
        {
            mapLetters[i] = '*';
        }
        if (!mapWords(vocab, wordsCopy, mapLetters))
        {
            for (int i = 0; i < ALPHABET; i++)
            {
                mapLetters[i] = '*';
            }
        }
        for (int i = 0; i < words.length; i++)
        {
            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < words[i].length(); j++)
            {
                tmp.append(mapLetters[words[i].charAt(j) - ASCIILetter]);
            }
            afterWords[i] = tmp.toString();
        }
        return afterWords;
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        s.nextLine();
        String[] vocab = new String[size];
        for (int i = 0; i < vocab.length; i++)
        {
            vocab[i] = s.nextLine();
            vocab[i] = vocab[i].trim();
        }
        String sentence;
        while (s.hasNextLine())
        {
            sentence = s.nextLine();
            if (sentence.length() == 0)
            {
                System.out.println();
                continue;
            }
            String[] words = sentence.split(" ");
            for (int i = 0; i < words.length; i++)
            {
                words[i] = words[i].trim();
            }
            String[] afterWords = cryptKicker(words, vocab);
            for (int i = 0; i < afterWords.length; i++)
            {
                if (i != afterWords.length - 1)
                {
                    System.out.print(afterWords[i] + " ");
                }
                else
                {
                    System.out.println(afterWords[i]);
                }
            }
        }
    }
}


