public class Strings {

    public static void main(String[] args) {
        String str1 = new String("abcde");  // this creates an immutable String object in heap
        String str2 = new String("abcde");
        // 0) concat() or +: create a new String object that concats two strings
        String str3 = str1.concat(str2) + "123"; 
        System.out.println(str3); 
        // 1) charAt(): reference indivisual character, note that str1[3] is WRONG
        System.out.println(str1.charAt(2));
        // 2) str1 == str2 vs. str1.equals(str2)
        if (str1 == str2) {
            System.out.println("str1 & str2 are two identical references");
        }
        if (str1.equals(str2)) {
            System.out.println("str1 & str2 store identical characters");
        }
        // 3) toCharArray(): creates a copy of array object of char
        char cArray[] = str1.toCharArray(); // this creates an array object of char in heap 
        // 4) for-loop vs. for-each loop: iterates an array object 
        for (int i = 0; i < cArray.length; i++) {
            if (cArray[i] == 'c') {
                cArray[i] = 'x';
            }
        }
        String str4 = new String(cArray);
        for (char c : str4.toCharArray()) { // for-each syntax for iterating an array object
            System.out.println(c);
        } 
        // 5) split(String regex): split a string object based on a passed-in regular expression
        String words[] = str3.split("c|1");
        for (String s : words) {
            System.out.println(s);
        }
    }
}
