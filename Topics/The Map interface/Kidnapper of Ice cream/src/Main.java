import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sen1 = scanner.nextLine();
        String sen2 = scanner.nextLine();


        Map<String, Integer> words = new HashMap<>();
        for (String word : sen1.split(" ")) {
            if (Arrays.stream(sen2.split(" ")).toList().contains(word)) {
                words.put(word, 1);
            }

        }

        if (words.size() == sen2.split(" ").length) {
            System.out.println("You get money");
        } else {
            System.out.println("You are busted");
        }
    }
}