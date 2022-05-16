public class FactorialMain {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++){
            System.out.print("Factorial of " + i + ": ");
            System.out.println(factorial(i));
        }
    }
    
    private static long factorial(final int n) {
        int result = 1;

        for (int m = 1; m <= n; m++){
            result *= m;
            System.out.print(m);
            
            if(m == n){
                System.out.print("=");
            }
            else{
                System.out.print("*");
            }
        }

        return result;
    }
}
