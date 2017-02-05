public class Utilities {

    public static void main(String[] args) {
        // 1) Integer.parseInt(str): i.e. C++ atoi(c_str)
        String str1 = new String("123");       // compile error if String is assigned to Integer directly
        Integer num1 = Integer.parseInt(str1);
        System.out.println(num1);
        // 2) Double.parseDouble(str)
        String str2 = new String("123.456");
        Double num2 = Double.parseDouble(str2);
        System.out.println(num2);
    }
}
