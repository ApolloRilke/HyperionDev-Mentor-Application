My solution uses recursion.  Anytime the method is called, a "level" is added to the stack, 
or rather for every return statement made the function call will be taking up space on the 
program stack. Each of the calls is added to the stack and takes up actual memory. 
Therefore, the worst case would be a number that has no place holders/zeros in-between the 
first and the last digit (ie 98765321 vs 900000001) as the program will add to the stack each 
time for each digit. Therefore the worst case would be O(D) where D is the number of digits. O(n).
