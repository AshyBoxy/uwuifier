package xyz.ashyboxy;

import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Provides an uwuify method
 */
public abstract class Uwuifier {
    private static Random rand = new Random();
    private static Pattern puncPattern = Pattern.compile("([!,?\\.])([ \\n])?");
    private static Pattern wordPattern = Pattern.compile("(\\S+?)([ \\n!,?\\.]|\\z)");
    // we add to the end seperately, so don't let this match a final newline
    private static Pattern newLinePattern = Pattern.compile("\\n(?!\\z)");

    private static HashMap<String, Punc> puncs = new HashMap<>();
    private static HashMap<String, String> words = new HashMap<>();

    private static String[] kaomoji = {
            ":3", "ÚwÚ", ">-<", "(* ^ ω ^)",
            "o.O", "(U ﹏ U)", "mya", "(✿oωo)",
            ";w;", "*blushes*", "uwu", "owo"
    };

    static {
        puncs.put("!", new Punc("!", "1", 5, 1, 0.4f));
        puncs.put("?", new Punc("?", "/", 5, 1, 0.3f));
        puncs.put(",", new Punc(",", null, 3, 0, 0));
        puncs.put(".", new Punc(".", null, 3, 0, 0));

        words.put("what", "nani");
        words.put("meow", "nyaa~");
        words.put("cute", "kawaii~");
        words.put("fluff", "floof");
        words.put("love", "luv");
        words.put("stupid", "baka");
        words.put("small", "smol");
        words.put("bitch", "meanie");
        words.put("fuck", "*licks you*");
    }

    /**
     * Takes a string and returns an uwuified version of it,
     * e.g. "Hello, world!" could become "Hewwo wowwd!1! :3"
     *
     * @param str The string to uwuify
     * @return The uwuified string
     */
    public static String uwuify(String str) {
        str = wordPattern.matcher(str).replaceAll((match) -> {
            String r = "";
            if ((r = words.get(match.group(1).toLowerCase())) != null) {
                char[] a = match.group(1).toCharArray();
                char[] b = r.toCharArray();
                if (Character.isUpperCase(a[0])) {
                    if (Character.isUpperCase(a[1]))
                        r = r.toUpperCase();
                    else {
                        b[0] = Character.toUpperCase(b[0]);
                        r = String.valueOf(b);
                    }
                }
            } else
                r = match.group(1);
            return r + match.group(2);
        });

        str = str.replace("r", "w").replace("R", "W").replace("l", "w").replace("L", "W");

        // no > nyo, No > Nyo, nO > nyO, NO > NYO
        // also cha > chya
        // + the other vowels
        str = str.replaceAll("(?i)(n|ch)([aeiou])", "$1y$2");
        str = str.replaceAll("(N|CH)y([AEIOU])", "$1Y$2");

        str = str.replace("ST", "SHT").replace("st", "sht").replace("St", "Sht").replace("st", "shT");

        // nya + nyo > nya~ + nyo~
        str = str.replaceAll("(?i)(ny[ao])([ \\n]|\\z)", "$1~$2");

        // stutter sometimes
        str = wordPattern.matcher(str).replaceAll((match) -> {
            String s = "";
            if (rand.nextFloat() < 0.2f) {
                float r = rand.nextFloat();
                // 60% 1, 30% 2, 10% 3
                s += (match.group(0).charAt(0) + "-").repeat(r < 0.6 ? 1 : r < 0.9 ? 2 : 3);
            }

            return s + match.group(0);
        });

        str = puncPattern.matcher(str).replaceAll((match) -> {
            Punc p;
            if ((p = puncs.get(match.group(1))) == null)
                return match.group(0);
            String s = puncGen(p.punc, p.alt, p.maxLen, p.minLen, p.altChance);
            if (match.group(2) != null && rand.nextFloat() < 0.3f)
                s += " " + kaomoji[rand.nextInt(kaomoji.length)] + match.group(2);
            else
                s += match.group(2) != null ? match.group(2) : "";
            return s;
        });

        str = newLinePattern.matcher(str).replaceAll(
                (match) -> (rand.nextFloat() < 0.2f ? " " + kaomoji[rand.nextInt(kaomoji.length)] : "") + "\n");

        // maybe add a kaomoji to the end
        if (rand.nextFloat() < 0.4f)
            str += " " + kaomoji[rand.nextInt(kaomoji.length)];

        return str;
    }

    private static String puncGen(String punc, String alt, int maxLen, int minLen, float altChance) {
        int count = rand.nextInt(maxLen - minLen) + minLen;
        String result = "";
        for (int i = 0; i < count; i++) {
            if (rand.nextFloat() < altChance)
                result += alt;
            else
                result += punc;
        }
        return result;
    }

    private static class Punc {
        public String punc;
        public String alt;
        public int maxLen;
        public int minLen;
        public float altChance;

        public Punc(String punc, String alt, int maxLen, int minLen, float altChance) {
            this.punc = punc;
            this.alt = alt;
            this.maxLen = maxLen;
            this.minLen = minLen;
            this.altChance = altChance;
        }
    }
}
