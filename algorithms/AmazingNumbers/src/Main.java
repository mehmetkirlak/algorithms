import java.util.Scanner;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    public static  void start(){
        boolean flag = false;
        int loop = 0;

        instructions();

        while(!flag){
            System.out.print("Enter a request: > ");
            String input = scanner.nextLine();
            long number = Long.valueOf(input.split(" ")[0]);
            if (input.split(" ").length > 1){
                loop = Integer.valueOf(input.split(" ")[1]);
            }

            if (number==0){
                System.out.println("\nGoodbye!");
                flag = true;
            } else if (number < 0){
                System.out.println("\nThe first parameter should be a natural number or zero.\n");
            }else if (number > 0 && loop > 0){
                System.out.println();
                loop(number,loop);
                System.out.println();
            } else if (loop<0) {
                System.out.println("\nThe second parameter should be a natural number.\n");
            }  else if (number > 0) {
                System.out.println();
                propertiesOfNumber(number);
                System.out.println();
            }
        }
    }

    public static void loop(long number, int loop){
        for (int i = 0 ; i < loop ; i++){
            long mockNumber = number + i;

            String print = "\t\t"+mockNumber+" is " ;
            if (isEven(mockNumber)){
                print = print.concat("even,");
            }
            if (isOdd(mockNumber)){
                print = print.concat("odd,");
            }
            if (isBuzz(mockNumber)){
                print = print.concat("buzz,");
            }
            if (isDuck(mockNumber)){
                print = print.concat("duck,");
            }
            if (isGapful(mockNumber)){
                print = print.concat("gapful,");
            }
            if (isPalindromic(mockNumber)){
                print = print.concat("palindromic,");
            }
            System.out.println(print.substring(0,print.lastIndexOf(",")));
        }
    }

    public static void propertiesOfNumber(long number){
        if (number>0){
            System.out.println("Properties of "+number+"\n" +
                    "\t\tbuzz: "+isBuzz(number)+"\n" +
                    "\t\tspy: "+isSpy(number)+"\n" +
                    "\t\tduck: "+isDuck(number)+"\n" +
                    "\t\tpalindromic: "+ isPalindromic(number)+"\n" +
                    "\t\tgapful: "+ isGapful(number)+"\n" +
                    "\t\teven: "+isEven(number)+"\n"+
                    "\t\todd: "+ isOdd(number)+"\n");
        } else {
            System.out.println( number +" is not natural");
        }

    }

    public static boolean isSpy(long number) {
        long lastDigit = 0;
        long sum = 0;
        long product = 1;

        while(number != 0)
        {
            lastDigit = number % 10;
            sum = sum + lastDigit;
            product = product * lastDigit;
            number = number / 10;
        }
        if(sum == product)
            return true;
        return false;
    }

    public static boolean isGapful(long number){
        String input = String.valueOf(number);
        if (number > 99 && Long.valueOf(input) % Integer.valueOf(input.substring(0,1)+input.substring(input.length()-1))== 0){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPalindromic(long number) {
        String clean = String.valueOf(number).replaceAll("\\s+", "").toLowerCase();
        StringBuilder plain = new StringBuilder(clean);
        StringBuilder reverse = plain.reverse();
        return (reverse.toString()).equals(clean);
    }

    public static boolean isDuck(long number){
        if (String.valueOf(number).contains("0") && number!=0){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOdd(long number){
        if (number%2==1){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEven(long number){
        if (number%2==0){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBuzz(long number){
        if ((number%7==0 && number/10==7) || (number==7) || (number%10==7 && number>10) || (number%7==0) && (number!=0)){
            return true;
        } else {
            return false;
        }
    }

    public static void instructions(){
        System.out.print("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n\n");
    }

}