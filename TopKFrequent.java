// In this approach, using a frequency map to store the key as elements and value as the frequency of each element. Using the min heap
// of size k and sorted by the frequency, so adding the elements as int[] pairs, and whenever size>k, poll from the heap. So at last
// we will be left with our ans elements in the heap.
// Time Complexity : O(nlogk)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Base case
        if (nums == null || nums.length < k) {
            return new int[] {};
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            // Put elements with it's frequency in the map
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // Declare pq, and as we are storing [ele, freq], so sort the heap by freq that
        // index 1(a[1] and b[1])
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // Iterate over the map and put the elements in the min heap
        for (int key : map.keySet()) {
            pq.add(new int[] { key, map.get(key) });
            // If the size becomes greater than k, poll
            if (pq.size() > k) {
                pq.poll();
            }
        }
        // Declare result
        int[] result = new int[k];
        System.out.println(pq.size());
        // Loop for result size
        for (int i = 0; i < k; i++) {
            // Poll from heap and put in result
            int[] res = pq.poll();
            result[i] = res[0];
        }
        return result;
    }
}

// In this approach, using bucket sort, finding the max frequency, declaring an
// array of list ( [{},{},{},...] ) of max+1 size, and
// put elements in the list at the index given by their frequency. So it will
// arrange all the elements with max frequency at the end.
// So start looping from end and put the elements in result array.
// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Base case
        if (nums == null || nums.length < k) {
            return new int[] {};
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            // Calc max
            max = Math.max(max, map.get(num));
        }
        // Declare array of list of size max+1
        List<Integer>[] li = new List[max + 1];
        // Iterate over the map, and put the elements at the index given by their
        // frequency
        for (int key : map.keySet()) {
            int index = map.get(key);
            // Check if list is not already present at that index, declare list first
            if (li[index] == null) {
                li[index] = new ArrayList<>();
            }
            // Then add the element to that list
            li[index].add(key);
        }
        // Result
        int[] result = new int[k];
        // Start looping from end of bucket sort array
        for (int i = max; i >= 0; i--) {
            // Get the list
            List<Integer> res = li[i];
            // If it is not null
            if (res != null) {
                // Put elements from it in the result
                for (int j = 0; j < res.size() && k > 0; j++) {
                    result[k - 1] = res.get(j);
                    k--;
                }
            }
        }
        return result;
    }
}