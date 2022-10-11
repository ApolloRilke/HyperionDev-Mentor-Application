My solution uses recursion, therefore thinking of recursion tree is helpful.  Anytime the method is called, 
a "level" is added to the stack, or rather for every return statement made the function call will be taking 
up space on the program stack, this would be the depth of the recursion tree. Each of the calls is added to 
the stack and takes up actual memory. Additionally, the tree breadth, or the total call the recursive function 
makes, is another feature that is notable with recursive functions. Memory complexity is determined by the 
depth of the number of return statements that is executed before the base case is completed. 

Therefore, the worst case would be a number that has no place holders/zeros in-between the first and the last 
digit (ie 98765321 vs 900000001) as the program will add to the stack each time for each digit and more return 
statements will be executed. Therefore the worst case would be O(D) where D is the number of digits > O(n).

