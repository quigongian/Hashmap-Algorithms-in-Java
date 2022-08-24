import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);

        //findDistinguishingChar
        System.out.println("Enter your first string.");
        String temp = scnr.nextLine();
        System.out.println("Enter your second string.");
        String temp2 = scnr.nextLine();
        findDistinguishingChar(temp, temp2);
        System.out.println();

        //isPalindrome
        System.out.println("Enter another string.");
        String temp3 = scnr.nextLine();
        System.out.println("Palindrome = " + isAPotentialPalindrome(temp3));

        //UniqueElements
        int[] test = {8, 8, 73, 26, 44, 102, 26, 18, 66, 77, 73};
        System.out.println("Unique Elements:" + sumOfUniqueElements(test));
    }

    public static void findDistinguishingChar(String temp, String temp2) {
        HashMap<String, Integer> frequencyHash = new HashMap<>();
        HashMap<String, Integer> frequencyHash2 = new HashMap<>();
        String[] arr = "abcdefghijklmnopqrstuvwxyz".split("");
        ArrayList<String> list = new ArrayList<>(26);
        String[] tempArr = temp.split("");
        String[] tempArr2 = temp2.split("");
        if (temp.equals(temp2)) { //If both strings are equal.
            System.out.println("Most Frequent" + Arrays.toString(tempArr));
        }
        int max = 0;
        for(String str: arr) { //Creates hash map with all letters
            frequencyHash.put(str, 0);
            frequencyHash2.put(str, 0);
        }
        for(int i = 0; i < tempArr.length; i++) //Updates frequency of first hashmap
            frequencyHash.put(tempArr[i], frequencyHash.get(tempArr[i]) + 1);
        for(int i = 0; i < tempArr2.length; i++) //Updates frequency of second hashmap
            frequencyHash2.put(tempArr2[i], frequencyHash2.get(tempArr2[i]) + 1);
        for(int i = 0; i < arr.length; i++) { //Compares hashmaps
            int totalNum = 0; //Largest frequency
            if (frequencyHash.get(arr[i]) > frequencyHash2.get(arr[i]))
                totalNum = frequencyHash.get(arr[i]) - frequencyHash2.get(arr[i]);
            else
                totalNum = frequencyHash2.get(arr[i]) - frequencyHash.get(arr[i]);
            if (totalNum > max) { //Updates arraylist containing the max frequency
                max = totalNum;
                list.clear();
                list.add(arr[i]);
            } else if (totalNum == max) { //Adds matching frequencies to arraylist
                if (!list.contains(arr[i]))
                    list.add(arr[i]);
            }
        }
        System.out.println("Most Frequent");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1)
                System.out.print(list.get(i));
            else
                System.out.print(list.get(i) + ", ");
        }
    }

    public static boolean isAPotentialPalindrome(String temp) {
        HashMap<String, Integer> hashyMap = new HashMap<>();
        String[] arr = temp.split("");
        ArrayList<String> list = new ArrayList<>();
        int tempNum = 0;
        for(int i = 0; i < arr.length; i++) {
            if (hashyMap.containsKey(arr[i]))
                hashyMap.put(arr[i], hashyMap.get(arr[i]) + 1);
            else {
                hashyMap.put(arr[i], 1);
                list.add(arr[i]);
            }
        }

        for(int i = 0; i < arr.length; i++)
            if(hashyMap.get(arr[i]) % 2 != 0)
                if(tempNum >= 1)
                    return false;
                else
                    tempNum++;
        return true;
    }

    public static int sumOfUniqueElements(int[] test) {
        AtomicInteger totalNum = new AtomicInteger();
        HashMap<Integer, Integer> hashyMap = new HashMap<>();
        for(int i = 0; i < test.length; i++)
            if (hashyMap.containsKey(test[i]))
                hashyMap.put(test[i], hashyMap.get(test[i]) + 1);
            else
                hashyMap.put(test[i], 1);
        hashyMap.forEach((k,j) -> {
            if(j == 1)
                totalNum.addAndGet(k);
        });
        return totalNum.get();
    }
}

