// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We iterate through the input string and compute by checking if its a digit or a character. If its a digit, we
update the current number to get along with its units,tens etc positions.If its a character and not a white space,
we check the recorded last sign and then perform the appropriate action by updating calc and tail values.For
addition, subtraction, computations are straightforward, but, for product and division, we need to follow BODMAS
rules, so, we first subtract the tail part from calc value inorder to get the last recorded number and multiply or divide
with the current numnber and then add it back.Similarly, we update the tail to be ready if any product or division exists
further.We update the last number and sign during this phase itself.Also, when we reach the last index of for loop, we may
miss the computation(as we are doing only when we find an operator), so to handle that we check the i value.
 */
class Solution {
    public int calculate(String s) {
        int currNum = 0;
        int calc = 0 , tail = 0;
        char lastSign = '+';

        for(int i = 0 ; i < s.length() ; i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch))
                currNum = currNum * 10 + ch - '0';
            if((!Character.isDigit(ch) && ch != ' ') || i == s.length() - 1) {
                if(lastSign == '+') {
                    calc = calc + currNum;
                    tail = currNum;
                }
                else if(lastSign == '-') {
                    calc = calc - currNum;
                    tail = -currNum;
                }
                else if(lastSign == '*') {
                    calc = calc - tail + (tail * currNum);
                    tail = tail * currNum;
                }
                else if(lastSign == '/') {
                    calc = calc - tail + (tail / currNum);
                    tail = tail / currNum;
                }
                currNum = 0;
                lastSign = ch;
            }
        }
        return calc;
    }
}