import java.util.*;

class Main {
    public static void main(String[] args) {
        System.out.println(areAnagrams());


    }

    public static String areAnagrams () {
        Scanner scanner = new Scanner(System.in);

        String word1 = scanner.nextLine().toLowerCase();
        String word2 = scanner.nextLine().toLowerCase();
        Map<Character, Integer> letterCounts1 = new HashMap<>();
        Map<Character, Integer> letterCounts2 = new HashMap<>();

        for (Character letter : word1.toCharArray()) {
            if (!(letterCounts1.containsKey(letter))) {
                letterCounts1.put(letter, 1);
            } else {
                letterCounts1.put(letter, letterCounts1.get(letter) + 1);
            }
        }

        for (Character letter : word2.toCharArray()) {
            if (!(letterCounts2.containsKey(letter))) {
                letterCounts2.put(letter, 1);
            } else {
                letterCounts2.put(letter, letterCounts2.get(letter) + 1);
            }
        }

        if (Objects.equals(letterCounts2, letterCounts1)) {
            return "yes";
        } else {
            return "no";
        }

    }
}