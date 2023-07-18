import java.util.HashMap;
import java.util.Random;

import xyz.ashyboxy.Uwuifier;

// just run it like
// $ java -cp ./build/libs/uwuifier-1.0.2.jar thing.java
public class thing {
    public static void main(String[] args) {
        uwuTest();
        // randTest();
        // charTest();

        // System.out.println(new Random().nextFloat());
    }

    private static void uwuTest() {
        String str = "Random random Stuff stuff!\nNone! none No? no Fuck you\nmeow Meow MEow meOW\ncan you help me Can You Help Me\nStop it! you bitch\nThis stuff is fun\nno no havanna Havanna\nNO chan chAN chAn Chan cHan cHaN che palle cHAn ONII-CHAN onii-chan Onii-chan onII-CHAN!\nHello, world!";
        String str2 = "Hey! This site can help you make any old boring text nice and uwu. We can't imagine anyone would actually use this, but you gotta do what you gotta do.";
        System.out.println(Uwuifier.uwuify(str));
        System.out.println("---");
        System.out.println(Uwuifier.uwuify(str2));
    }

    private static void randTest() {
        System.out.println(new Random().nextInt(4 - 0) + 0);
        System.out.println(new Random().nextInt(1, 3));
    }

    private static void charTest() {
        String str = "aaaa";
        char[] strc = str.toCharArray();
        strc[0] = Character.toUpperCase(strc[0]);
        str = String.valueOf(strc);
        System.out.println(str);
    }
}
