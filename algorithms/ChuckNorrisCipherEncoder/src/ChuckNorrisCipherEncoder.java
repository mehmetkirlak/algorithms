import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.nio.file.attribute.UserPrincipal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class ChuckNorrisCipherEncoder {
    static Scanner sc=new Scanner(System.in);


    public static void main(String[] args) throws IOException {

        System.out.println("%s - %s".formatted("mehmet","kırlak"));

        //Float.parseFloat(df.format(koni.getYukseklik() * Math.PI * Math.pow(koni.getYaricap(),2) / 3));

        // separeteNumbersbyParity(5);

        /*long startTime = System.nanoTime();
        reverseNumber(123456);
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

       /* boolean flag = false;
        while (!flag){
            System.out.println("\nPlease input operation (encode/decode/exit):");
            String operation = sc.nextLine();
            if (operation.equals("encode")){
                encode();
            } else if (operation.equals("decode")){
                decode();
            } else if (operation.equals("exit")){
                flag = true;
                System.out.println("Bye!");
            } else {
                System.out.println("There is no '"+ operation +"' operation");
            }
        }*/


    }

    public static void factorialToFile(int loop){
        String output = "";
        for (int i = 0; i<=loop;i++){
            output = output.concat(String.valueOf(factorial2(i)) +"\n");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("faktöriyel.txt"));
            writer.write(output);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static int factorial2(int number){
        if (number<=1 || number==0){
            return 1;
        } else {
            return number * factorial2(number - 1);
        }
    }

    public static int factorial(int number){
        String output = "";
        if (number<=1){
            System.out.print(String.valueOf(number)+"\n");
            output.concat(String.valueOf(number)+"\n");
            return 1;
        } else {
            System.out.print(String.valueOf(number*factorial(number-1))+"\n");
            output.concat(String.valueOf(number)+"\n");
            factorial(number - 1);
        }
        return 0;
    }

    public static int[][] separeteNumbersbyParity(int number){
        Random random = new Random();
        int[] array = new int[number];
        array = Arrays.stream(array).map(x -> random.nextInt(9) + 1).toArray();

        int[] evenArray;
        evenArray = Arrays.stream(array).filter(num -> num%2==0).toArray();
        int[] oddArray;
        oddArray = Arrays.stream(array).filter(num -> num%2==1).toArray();

        return new int[][]{array, oddArray, evenArray};
    }

    public static void encode(){
        String binarySet = "";
        String binary;

        System.out.println("Input string:");
        String input = sc.nextLine();
        System.out.println("Encoded string:");

        for (int i=0;i<input.length();i++){
            if (Integer.toBinaryString((int) input.charAt(i)).length()==6){
                binary="0"+Integer.toBinaryString((int) input.charAt(i));
                binarySet = binarySet.concat(binary);
            }else{
                binary=Integer.toBinaryString((int) input.charAt(i));
                binarySet = binarySet.concat(binary);
            }
        }

        //asd
        //110000111100111100100
        for (int i=0;i<binarySet.length();i++){
            if(binarySet.charAt(i)=='1'){
                int countOnes=0;
                for (int j=i;j<binarySet.length();j++){
                    if (binarySet.charAt(j)=='1'){
                        countOnes++;
                    }else {
                        j=binarySet.length();
                    }
                }
                i+=countOnes-1;
                System.out.print("0 ");
                for (int k=0;k<countOnes;k++){
                    System.out.print("0");
                }
                System.out.print(" ");
            }else {
                int countZeros=0;
                for (int j=i;j<binarySet.length();j++){
                    if (binarySet.charAt(j)=='0'){
                        countZeros++;
                    }else {
                        j=binarySet.length();
                    }
                }
                i+=countZeros-1;
                System.out.print("00 ");
                for (int k=0;k<countZeros;k++){
                    System.out.print("0");
                }
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static String decode(){
        String answer = "";
        String binary = "";
        boolean flag = true;
        ArrayList<String> binaryArray = new ArrayList<>();

        System.out.println("Input encoded string:");
        String cipher = sc.nextLine();

        String[] blocks = cipher.split(" ");

        if (!isMultipleBySeven(blocks) || cipher.isBlank() || cipher.isEmpty() || blocks.length%2==1 ){
            answer = "Encoded string is not valid.";
            System.out.println("Encoded string is not valid.");
            return answer;
        }

        for (int i=0;i<blocks.length;i+=2){
            if (Objects.equals(blocks[i], "0")){
                for (int j=0;j<blocks[i+1].length();j++){
                    binary = binary.concat("1");
                }
            }else {
                for (int j=0;j<blocks[i+1].length();j++){
                    binary = binary.concat("0");
                }
            }
        }//0 0 00 0000 0 000 00 0000 0 00

        for (int i = 1 ; i <= binary.length() ; i++){
            if (i%7==0 && i!=0){
                binaryArray.add(binary.substring(i-7,i));
            }
        }

        for (String bi:binaryArray){
            int intValueOfBinaryString = Integer.parseInt(bi, 2);
            answer = answer.concat(String.valueOf((char) intValueOfBinaryString));
        }

        if (!formatChecker(blocks)){
            answer = "Encoded string is not valid.";
            System.out.println("Encoded string is not valid.");
            return answer;
        }

        System.out.println("Decoded string:\n"+answer);
        return answer;
    }

    public static boolean isMultipleBySeven(String[] cipherArray){
        int totalNums = 0;

        for (int i = 1; i<cipherArray.length;i+=2){
            totalNums += cipherArray[i].length();
        }

        if (totalNums == 0 || totalNums % 7 != 0){
            return false;
        } else {
            return true;
        }
    }

    public static boolean formatChecker(String[] chiperArray){

        //0 0 00 00 0 0 000 000
        for (int i = 0 ; i < chiperArray.length ; i+=2 ){
            if ( chiperArray[i].length() > 2 || chiperArray[i].length() < 1){
                return false;
            }
        }

        for (int i = 0 ; i < chiperArray.length ; i++ ){
            if (chiperArray[i].matches("[a-z]|[A-Z]|[1-9]+")){
                return false;
            }
        }

        return true;
    }

}