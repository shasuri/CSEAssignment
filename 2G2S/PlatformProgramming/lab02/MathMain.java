public class MathMain {
    public static void main(String[] args) {
        int begin = 2;
        int end = 10;

        long sum = getSumBetween(begin,end);
        System.out.printf("Sum between %d and %d : %,d%n",begin,end,sum);
        printSumBetween(begin,end,sum);

        long product = getProductBetween(begin,end);
        System.out.printf("Product between %d and %d : %,d%n",begin,end,product);
        printProductBetween(begin,end,product);
    }

    private static long getSumBetween(final int startNum, final int endNum){
        long sumResult = 0;

        for(int i = startNum; i <= endNum; i++){          
            sumResult += (long)i;
        }

        return sumResult;
    }

    private static void printSumBetween(final int startNum, final int endNum, final long sumResult){
        for(int i = startNum; i <= endNum; i++){
            System.out.printf("%d",i);
            
            if(i < endNum){
                System.out.print("+");
            }
            
            else if(i == endNum){
                System.out.printf(" = %,d\n",sumResult);
            }
        }
    }
    private static long getProductBetween(final int startNum, final int endNum){
        long productResult = 1;

        for(int i = startNum; i <= endNum; i++){
            productResult *= (long)i;
        }

        return productResult;
    }

    private static void printProductBetween(final int startNum, final int endNum, final long productResult){
        for(int i = startNum; i <= endNum; i++){
            System.out.printf("%d",i);
            
            if(i < endNum){
                System.out.print("*");
            }
            
            else if(i == endNum){
                System.out.printf(" = %,d\n",productResult);
            }
        }
    }
    
}