# Uwuify

It's an uwuifier what more is there to say

Here's a usage example i guess:
```java
import xyz.ashyboxy.Uwuifier;

public class Main {
    public static void main(String[] args) {
        System.out.println(Uwuifier.uwuify("Hello, world!"));
    }
}
```

It's available on my maven at https://repos.ashyboxy.xyz/maven

Put this in your build.gradle or equivalent
```groovy
repositories {
	maven {
		name = "AshyBoxy Maven"
		url = "https://repos.ashyboxy.xyz/maven"
	}
}

dependencies {
	implementation "xyz.ashyboxy:uwuifier:1.0.2"
	include "xyz.ashyboxy:uwuifier:1.0.2"
}
```

Configurable uwuifiers coming in 2.0.0 (maybe never)
