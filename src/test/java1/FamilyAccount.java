package java;

public class FamilyAccount {
    private static int balance = 1000;
    static String details = "";


    public static void chioce() {

        System.out.println("       请选择（1-4）");
        char key = Utility.readMenuSelection();
        boolean flag = true;
        while (flag) {
            switch (key) {
                case '1':
                    System.out.println("        当前收入明细");
                    if (details.equals("")) System.out.println("无");
                    else {
                        System.out.println(details);
                    }
                    System.out.println("--------------------");
                    break;
                case '2':
                    System.out.println("        请输入收入金额");
                    int a1 = Utility.readNumber();
                    balance+=a1;
                    System.out.println("        请输入收入说明");
                    String s1 = Utility.readString();
                    details+="收入\t"+a1+"余额: "+balance+" 说明:"+s1+'\n';
                    System.out.println("        登记完成");
                    break;
                case '3':
                    System.out.println("        请输入支出金额");
                    int a2 = Utility.readNumber();
                    balance-=a2;
                    System.out.println("        请输入支出说明");
                    String s2 = Utility.readString();
                    details+="支出\t"+a2+"余额: "+balance+"说明:"+s2+'\n';
                    System.out.println("        登记完成");
                    break;
                case '4':
                    System.out.println("        确认是否退出(Y/N)");
                    char f = Utility.readConfirmSelection();
                    if (f=='Y')flag = false;
                    else if(f=='N') flag = true;
                    break;
            }
            if (flag==false)break;
            else Main();
        }
    }

    public static void Main() {
        System.out.println("--------家庭记账系统-------");
        System.out.println("        1.收入明细");
        System.out.println("        2.登记收入");
        System.out.println("        3.登记支出");
        System.out.println("        4.退   出");
        FamilyAccount.chioce();
    }

    public static void main(String[] args) {
        Main();
    }
}
