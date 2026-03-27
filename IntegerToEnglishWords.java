// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
The idea is to have string to number mapped arrays in form of thousands, tens and numbers less than 20. This way
its easier to access.Now, we iterate through the given input and we split the number into indices of 1000.We
handle the thousands part by using thousand array and use a helper method to recursively handle the hundreds, tens
parts by using respective arrays.For each iteration, we divide the number by 1000 and only continue if it remains
greater than 0.
 */
class Solution {
    String[] lessThanTwenty = new String[]{" ","One", "Two" , "Three" , "Four", "Five", "Six", "Seven","Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = new String[]{" ", " ", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] thousands = new String[]{" ", " Thousand ", " Million ", " Billion "};

    public String numberToWords(int num) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        if(num == 0)
            return "Zero";

        while(num > 0) {
            int triplet = num % 1000;
            if(triplet > 0) {
                result.insert(0, helper(triplet).trim() + thousands[i]);
            }
            num = num / 1000;
            i++;
        }
        return result.toString().trim();
    }

    private String helper(int num) {
        StringBuilder result = new StringBuilder();

        if(num < 20) {
            result.append(lessThanTwenty[num]);
        }
        else if(num < 100) {
            result.append(tens[num / 10]).append(" ").append(lessThanTwenty[num % 10]);
        }
        else {
            result.append(lessThanTwenty[num / 100]).append(" Hundred ").append(helper(num % 100));
        }
        return result.toString();
    }
}