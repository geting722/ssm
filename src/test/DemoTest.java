
import java.util.Random;
import java.util.Scanner;

/**
 * 扫雷棋盘
 */
public class DemoTest {
    private static boolean[] book = new boolean[30];
    private static int mp[][] = new int[5][5];
    private static int[][] arrs;
    private static int token = 3;
    private static boolean[] vis = new boolean[31];

    public static void main(String[] args) {
        arrs = generate();
        copy1();
        print(arrs);
//        System.out.println();
//        print(mp);
        play();
    }

    private static void play() {
        System.out.println("Rule of SEGiSweeper :\n" +
                "1. Chances : 3 tokens\n" +
                "2. To play the game, enter a number between 1 – 25\n" +
                "3. Special key :\n" +
                "a. Press r to restart the game\n" +
                "b. Press q to quit the game\n" +
                "4. Avoid the 10 hidden bombs!! Minus 1 token for each exploded bomb\n" +
                "5. You win if you successfully select 15 non-bomb spot\n");
        System.out.println("GOOD LUCK !!");
        System.out.println("Press y to start the game : ");
        Scanner sac = new Scanner(System.in);
        String ch = sac.nextLine();
        if (ch.equals("y")) {
            boolean flag = false;
            boolean first = true;
            while (true) {
                print(mp);
                if (first == true) {
                    System.out.println("Token : " + token);
                    int n;
                    System.out.println("Please key in a number (r-reset, q-quit) : ");
                    n = sac.nextInt();
                    isB(n);
                    first = false;
                } else {
                    int n;
                    System.out.println("Please key in a number (r-reset, q-quit) : ");
                    n = sac.nextInt();
                    if(count() == 20){
                        System.out.println("Congratulations, it's done");
                        break;
                    }
                    if (isB(n) == false)
                        break;
                }
            }
        }
    }

    private static int count() {
        int cnt = 0;
        for (int i = 0; i < vis.length; i++) {
            if (vis[i] == true)
                cnt++;
        }
        return cnt;
    }

    private static boolean isB(int n) {

        for (int i = 0; i < mp.length; i++) {
            for (int j = 0; j < mp[i].length; j++) {
                if (mp[i][j] == n) {
                    if (arrs[i][j] == -2) {
                        if (token > 0) {
                            token--;
                            System.out.println("Ooooopss! KebaBOOOOOOM !!!\n" +
                                    "Token :" + token);
                            mp[i][j] = -2;
                            return true;
                        } else {
                            System.out.println("============================================================================\n" +
                                    "Too bad you are leaving now!\n" +
                                    "Thank you for playing SEGiSweeper\n" +
                                    "Bye . . . Game End");
                            return false;
                        }
                    } else {
                        System.out.println("Good guess! Next move please\n" +
                                "Token :" + token);
                        vis[arrs[i][j]] = true;
                        return true;
                    }
                }
            }
        }
        System.out.println("输入无效");
        return true;
    }

    private static void copy1() {
        Random r = new Random();
        for (int i = 0; i < mp.length; i++) {
            for (int j = 0; j < mp[0].length; j++) {
                mp[i][j] = arrs[i][j];
                if (mp[i][j] == -2) {
                    while (true) {
                        mp[i][j] = r.nextInt(26);
                        if (book[mp[i][j]] == false) {
                            book[mp[i][j]] = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void print(int[][] arrs) {
        if (arrs == null || arrs.length == 0) {
            return;
        }
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[i].length; j++) {
                if (arrs[i][j] == -2) {
                    System.out.print(" -B- ");
                } else {
                    if (vis[arrs[i][j]] == true) System.out.print("     ");
                    else System.out.print(" " + String.format("%03d", arrs[i][j]) + " ");
                }
            }
            //换行打印
            System.out.println();
        }
    }


    private static int[][] generate() {
//        Scanner in =new Scanner(System.in);
        //设置棋盘的大小
//        System.out.println("请输入棋盘大小：");
        int a = 5;
        //设置地雷的个数
//        System.out.println("请设置地雷的个数：");
        int b = 10;
        int[][] arrs = new int[a][a];
        //地雷标记位-2，其它标记为-1
        Random r = new Random();
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[i].length; j++) {
                if (b > 0) {
                    boolean flag = r.nextBoolean();
                    if (flag) {
                        arrs[i][j] = -2;
                        b--;
                    } else {
                        while (true) {
                            arrs[i][j] = r.nextInt(26);
                            if (book[arrs[i][j]] == false) {
                                book[arrs[i][j]] = true;
                                break;
                            }
                        }

                    }
                } else {
                    while (true) {
                        arrs[i][j] = r.nextInt(26);
                        if (book[arrs[i][j]] == false) {
                            book[arrs[i][j]] = true;
                            break;
                        }
                    }
                }
            }
        }
        return arrs;
    }
}
