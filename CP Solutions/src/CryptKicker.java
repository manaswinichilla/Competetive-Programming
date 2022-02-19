import java.util.*;

public class CryptKicker {

    public static boolean letterVisited(char letter, char aLetter, char[] mapLetters)
    {
        for(int i=0; i<26; i++)
        {
            if(mapLetters[i]==aLetter && i!=(letter-97))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean wordToword(String aWord, String word, char[] mapLetters)
    {
        char[] mapLettersCopy = new char[26];
        System.arraycopy(mapLetters, 0, mapLettersCopy, 0, mapLettersCopy.length);
        for(int i=0; i<aWord.length(); i++)
        {
            if((mapLetters[aWord.charAt(i)-97]!=word.charAt(i) && mapLetters[aWord.charAt(i)-97]!='*')||!letterVisited(aWord.charAt(i), word.charAt(i), mapLetters))
            {
                System.arraycopy(mapLettersCopy, 0, mapLetters, 0, mapLetters.length);
                return false;
            }
            else
            {
                mapLetters[aWord.charAt(i)-97] = aWord.charAt(i);
            }
        }
        return true;
    }

    public static String mapfound(String word)
    {
        if(word.length()>0)
        {
            StringBuilder mapped = new StringBuilder();
            int c=0;
            HashMap<Character, Character> hashmap = new HashMap<>();
            for(int i=0; i<word.length(); i++)
            {
                if(!hashmap.containsKey(word.charAt(i)))
                {
                    hashmap.put(word.charAt(i), (char) (48 + c++));
                }
            }
            for(int i=0; i<word.length(); i++) {
                mapped.append(hashmap.get(word.charAt(i)));
            }
            return hashmap.toString();
        }
        else
        {
            return "";
        }
    }

    public static boolean mapWords(String[] vocab, String[] wordsBackup, char[] mapLetters)
    {
        ArrayList<String> options = new ArrayList<>();
        String aWord = wordsBackup[0];
        char[] mapLettersCopy = new char[26];
        System.arraycopy(mapLetters, 0, mapLettersCopy, 0, mapLettersCopy.length);
        for( String word: vocab)
        {
            if(word.length()==aWord.length() && mapfound(word).equals(mapfound(aWord)))
            {
                options.add(word);
            }
        }

        for(String word : options)
        {
            if(wordToword(aWord, word, mapLetters))
            {
                if(wordsBackup.length>1)
                {
                    if(mapWords(vocab, Arrays.copyOfRange(wordsBackup, 1, wordsBackup.length), mapLetters))
                    {
                        return true;
                    }
                    else
                    {
                        System.arraycopy(mapLettersCopy, 0, mapLetters, 0, mapLetters.length);
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

    public static String[] cryptKicker(String[] words, String[]vocab)
    {
        String[] afterWords = new String[words.length];
        String[] wordsBackup= Arrays.copyOf(words, words.length);
        Arrays.sort(wordsBackup, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o2.length(), o1.length());
            }
        });

        char[] mapLetters = new char[26];
        for(int i=0; i<26; i++)
        {
            mapLetters[i]='*';
        }

        if(!mapWords(vocab, wordsBackup, mapLetters))
        {
            for(int i=0; i<26; i++)
            {
                mapLetters[i]='*';
            }
        }
        for(int i=0; i<words.length; i++)
        {
            StringBuilder tmp = new StringBuilder();
            for(int j=0; j<words[i].length(); j++)
            {
                tmp.append(mapLetters[words[i].charAt(j)-97]);
            }
            afterWords[i]=tmp.toString();
        }
        return afterWords;
    }

    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        int size=s.nextInt();
        String[] vocab = new String[size];
        for(int i=0; i<size; i++)
        {
            vocab[i]=s.nextLine();
            vocab[i]=vocab[i].trim();
        }
        String sentence;
        while(s.hasNextLine())
        {
            sentence=s.nextLine();
            if(sentence.length()==0)
            {
                System.out.println();
                continue;
            }

            String[] words=sentence.split(" ");
            for(int i=0; i<words.length; i++)
            {
                words[i]=words[i].trim();
            }
            String[] afterWords = cryptKicker(words, vocab);
            for(int i=0; i< afterWords.length; i++)
            {
                if(i!=afterWords.length-1)
                {
                    System.out.print(afterWords[i]+" ");
                }
                else
                {
                    System.out.println(afterWords[i]);
                }
            }
        }
    }
}
