// String object are immutalbe

public class Strings {

    public static void main(String[] args) {
        String str1 = new String("abcde");         // this creates an immutable String object in heap
        // 1.0) length(): return the length of a string
        System.out.println(str1.length());
        // 1.1) indexOf(substr, [pos]): return the start position of a substring
        System.out.println(str1.indexOf("c"));
        // 1.2) substring(start, [end]): return the substring given the start position [and end position]
        String str3 = new String("01234");
        System.out.println(str3.substring(2));
        System.out.println(str3.substring(2, 4));
        // 1.3) concat() or +: create a new String object that concats two strings
        String str4 = str1.concat("fgh") + "ijk";  // this creates a new immutable String object
        System.out.println(str4); 
        // 1.4) charAt(): reference indivisual character, note that str1[3] is WRONG
        System.out.println(str1.charAt(2));
        // 2) str1 == str2 vs. str1.equals(str2)
        String str1_copy = str1;                   // two separate references refer to the same String object
        if (str1 == str1_copy) {
            System.out.println("str1 & str1_copy refer to the same object");
        }
        if (str1.equals(str1_copy)) {
            System.out.println("str1 & str1_copy refer to the same object and have the same content");
        }
        String str2 = new String("abcde");         // a new immutable String object
        if (str1 == str2) {
            System.out.println("str1 & str2 refer to the same object");
        }
        if (str1.equals(str2)) {
            System.out.println("str1 & str2 refer to different objects but have the same content");
        }
        // 3) toCharArray(): creates a copy of array object of char
        char cArray[] = str1.toCharArray(); // this creates an array object of char in heap 
        // 4) for-loop vs. for-each loop: iterates an array object 
        for (int i = 0; i < cArray.length; i++) {
            if (cArray[i] == 'c') {
                cArray[i] = 'x';
            }
        }
        String str5 = new String(cArray);   // initialize String object with an char[] array
        for (char c : str5.toCharArray()) { // for-each syntax for iterating an array object
            System.out.println(c);
        } 
        // 5) split(String regex): split a string object based on a passed-in regular expression
        String words[] = str5.split("c|1");
        for (String s : words) {
            System.out.println(s);
        }
        // 6) StringBuilder: Java mutable String object
        StringBuilder sb = new StringBuilder("mutable");
        sb.append("string");
        sb.insert(7, " ");
        System.out.println(sb);
        // 7) replaceAll(regex, replacement)
        System.out.println(str4.replaceAll("c|e", "xxx"));
        // 8) trim(): returns a copy of the string, with leading and trailing whitespace omitted
        String str6 = "  strings with spaces    ";
        System.out.println(str6.trim());
    }
}
