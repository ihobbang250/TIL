import java.util.*;

public class StreamExample {
    public static void main(String[] args) {
        int[] data = {5, 6, 4, 2, 3, 1, 1, 2, 2, 4, 8};

        //#1 solution Using list, set
        ArrayList<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                dataList.add(data[i]);
            }
        }

        HashSet<Integer> dataSet = new HashSet<>(dataList);

        ArrayList<Integer> distinctList = new ArrayList<>(dataSet);
        
        distinctList.sort(Comparator.reverseOrder());

        int[] result = new int[distinctList.size()];
        for (int i = 0; i < distinctList.size(); i++) {
            result[i] = distinctList.get(i);
        }

        //#2 solution Using Stream
        int[] result2 = Arrays.stream(data)  // Create IntStream
            .boxed()  // IntStream -> Stream<Integer>
            .filter((a) -> a % 2 == 0)  //  filtering
            .distinct()  // Remove Overlap
            .sorted(Comparator.reverseOrder())  // Parameter Integer type, ReverseOrder 
            .mapToInt(Integer::intValue)  // Stream<Integer> -> IntStream
            .toArray() // Stream -> array 
            ;  

        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result2));
    }
}