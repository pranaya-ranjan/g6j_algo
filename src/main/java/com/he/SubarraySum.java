package com.he;

import java.util.Arrays;
import java.util.List;

class SubarraySum {
    /* Returns true if the there is a subarray of arr[] with a sum equal to
       'sum' otherwise returns false.  Also, prints the result */
    int subArraySum(List<Integer> inputSeq, int targetSum) {
        int currSum, i, j;
        int counts = 0;

        // Pick a starting point
        for (i = 0; i < inputSeq.size(); i++) {
            currSum = inputSeq.get(i);

            for (j = i + 1; j <= inputSeq.size(); j++) {
                if (currSum == targetSum) {
                    counts++;
                    int p = j - 1;
                    System.out.println("Sum found between indexes " + i
                            + " and " + p);
                    //  break;

                }
                if (currSum > targetSum || j >= inputSeq.size()) {
                    break;
                }

                    currSum = currSum + inputSeq.get(j);
            }
        }

        return counts;
    }

    public static void main(String[] args) {
        /*SubarraySum arraysum = new SubarraySum();
        List arr = Arrays.asList(6, 2, 4, 1, 5);
        int n = arr.size();
        int sum = 6;*/
      //  System.out.println(arraysum.subArraySum(arr, sum));

        long x = 2500; //long
    }
}

// This code has been contributed by Mayank Jaiswal(mayank_24)

