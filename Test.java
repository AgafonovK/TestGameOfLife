import com.sun.org.apache.xpath.internal.SourceTree;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.Arrays;
import java.util.Scanner;

public class Test {

    private static int step;
    private static int[][] buf;
    private static Scanner in;
    private static Scanner in2;

    public static void main(String[] args) {

        in2 = new Scanner(System.in);
        System.out.println("Set value of desk");
        int length = in2.nextInt();
        System.out.println(length);
        in2.close();
     //   System.exit(0);
        step = 0;
        in = new Scanner(System.in);
        int[][] test = generate();
        buf = new int[test.length][test.length];
        display(test);
        test[test.length / 2][test.length / 2 - 1] = 1;
        test[test.length / 2][(test.length / 2)] = 1;
        test[test.length / 2][(test.length / 2) - 2] = 1;
        // test[test.length / 2 - 1][test.length / 2] = 1;
        System.out.println("\n");
        //display(test);

        while (step <= 62) {
            for (int x = 1; x < test.length - 1; x++) {
                for (int y = 1; y < test.length - 1; y++) {
                    //mass around cheking cell
                    int[][] varBuf2 = {{test[x - 1][y - 1], test[x - 1][y], test[x - 1][y + 1]},
                            {test[x][y - 1], test[x][y], test[x][y + 1]},
                            {test[x + 1][y - 1], test[x + 1][y], test[x + 1][y + 1]}};
                    //display(varBuf2);
                    int summ = 0;
                    // summ cell around checking cell
                    for (int i = 0; i < varBuf2.length; i++) {
                        for (int j = 0; j < varBuf2.length; j++) {
                            summ += varBuf2[i][j];
                            if (summ > 4) {
                                Arrays.fill(varBuf2, 0);
                                continue;
                            }


                        }
                    }
                    //System.out.println("summ=" + summ);
                    switch (summ) {
                        case 3:

                            buf[x][y] = 1;

                            step++;
                            continue;
                        case 4:
                            if (test[x][y] == 1) {
                                buf[x][y] = 1;
                            }
                            step++;
                            continue;
                        default:
                            buf[x][y] = 0;

                    }
                    step++;

                }

            }
            //refresh value test from buf
            test = buf.clone();
            display(test);
            System.out.println("\n");

            if (step == 64) {
                step = 0;
                buf = new int[test.length][test.length];

            }
            if (in.nextBoolean()) {
                continue;
            }
            ;
        }

    }


    private static int[][] generate() {
        int n = 10;
        int[][] var = new int[n][n];
        int i = 0;
        //  System.out.println(i);
        for (i = 0; i < var.length; i++) {
            for (int j = 0; j < var.length; j++) {
                var[i][j] = 0;
            }
        }
        return var;
    }

    private static void display(int variable[][]) {
        int n = variable.length;
        int i;
        for (i = 0; i < n; i++) {
            System.out.println();
            for (int j = 0; j < n; j++) {
                System.out.print(variable[i][j] + " ");

            }
        }
    }
}
