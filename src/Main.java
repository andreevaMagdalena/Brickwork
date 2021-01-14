import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter  array size : ");

        int rows = sc.nextInt();
        int columns = sc.nextInt();
        //check if rows and columns are bigger then 100 or less then 1
        if (rows > 100 || columns > 100) {
            System.out.println("Elements must be less than 100");
            return;
        }
        if (rows < 1 || columns < 1) {
            System.out.println("Elements must be greater than 0");
            return;
        }
        //check if rows and columns are even numbers
        if (rows % 2 != 0 || columns % 2 != 0) {
            System.out.println(-1);
        }

        System.out.println("Enter array elements : ");

        int[][] input = new int[rows][columns];
        //fill the first 2D array
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                input[i][j] = sc.nextInt();
            }
        }
        //declare the second 2D array
        int[][] output = new int[rows][columns];
        //calculate the largest number in the array
        int max = (int) ((0.5 * rows) * columns);
        //declare list with all numbers that already be added in the array
        List<Integer> alreadyCheckedNumbers = new ArrayList<>();

       // randomNumber variable keeps the current random value
        int randomNumber = 0;
        int currentRowCurrentCol;
        // randomNumber variable keeps new random value when first is equal to number in the list
        int newRandomNumber = 0;

        for (int i = 0; i < rows; i++) {
            //check is not last row
            if (i < rows - 1) {
                for (int j = 0; j < columns; j++) {
                    //check if randomNumber is in list or is equal to zero and generate new random number
                    while (alreadyCheckedNumbers.contains(randomNumber) || randomNumber == 0) {
                        randomNumber = randomInt(1, max);

                    }
                    if (!alreadyCheckedNumbers.contains(randomNumber)) {
                        currentRowCurrentCol = input[i][j];
                        //check is not last index in the row
                        if(j < columns - 1) {
                            if (input[i][j + 1] == currentRowCurrentCol) {
                                output[i][j] = randomNumber;
                                output[i + 1][j] = output[i][j];
                                alreadyCheckedNumbers.add(output[i][j]);
                            } else {
                                output[i][j] = randomNumber;
                                output[i][j + 1] = output[i][j];
                                alreadyCheckedNumbers.add(output[i][j]);
                                //check numbers from 1 to max number in array witch are contains in list
                                for (int k = 1; k < max; k++) {
                                    if (!alreadyCheckedNumbers.contains(k)) {
                                        newRandomNumber = k;
                                    }
                                }
                                output[i + 1][j] = newRandomNumber;
                                output[i + 1][j + 1] = output[i + 1][j];
                                alreadyCheckedNumbers.add(output[i + 1][j]);

                                j++;
                            }
                        }else {
                            output[i][j] = randomNumber;
                            output[i + 1][j] = output[i][j];
                            alreadyCheckedNumbers.add(randomNumber);
                        }
                    }

                }
            }
            i++;
        }

//print the result
        for (int[] x : output) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }
//generate random number in range
    public static int randomInt(int min, int max) {


        return (int) (Math.random() * ((max - min) + 1)) + min;
    }


}