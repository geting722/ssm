
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 解压文件
 */
public class decode {
    /**
     * hashMap用于存储字符及其huffmanCode
     */
    static  HashMap<String,Integer> hashMap= new HashMap<>();
    static int readCount=7;
    static   ArrayList<Integer> code= new ArrayList<>();

    /**
     * 构造器
     * @param path
     * @throws IOException
     */
    public  decode(String path) throws IOException {
        BufferedInputStream inputStream= new BufferedInputStream(new FileInputStream(path));
        File file= new File(path);
        decodeFile(inputStream);
        inputStream.close();
    }

    /**
     *  将要解压的文件读出来
     * @param inputStream
     * @throws IOException
     */
    private void decodeFile(BufferedInputStream inputStream) throws IOException {
        int lengthOfFileName = inputStream.read();
        int fileType= inputStream.read();
        String name="" ;
        while(name.length()<lengthOfFileName){
            int value= inputStream.read();
            name= name+(char)value;
        }
        if(fileType==1){
            File file1= new File(name);
            //  System.out.println(name+"   文件夹");//文件夹
            if(!file1.exists())
                file1.mkdir();
            decodeFile(inputStream);
        }else if(fileType==0) {
            decode_file(inputStream,name);//解压文件
        }else {
            //它按顺序解压文件，如果类型不是1或0，这意味着它到达了文件的末尾
            System.exit(0);
        }

    }

    /**
     * 解压文件
     * @param inputStream
     * @param str
     * @throws IOException
     */
    private void decode_file(BufferedInputStream inputStream, String str) throws IOException {
        BufferedOutputStream outputStream =new BufferedOutputStream(new FileOutputStream(str));
        int[] str_length= new int [256];

        int value;
        int bb=24;
        int lengthOfChar=0;
        System.out.println(inputStream.available());
        for(int j=0;j<4;j++){
            value=inputStream.read();
            // System.out.println("32位编码 ："+value);
            int tt=value<<bb;
            bb=bb-8;
            lengthOfChar=lengthOfChar|tt;
        }
        //  length=yy;//读出字符的长度

        //  System.out.println(yy+"    长度");//
        //System.out.println(inputStream.available());

        for (int i=0;i<256;i++){
            value= inputStream.read();
            str_length[i]=value;
        }//把哈希表的长度写入数组

        for(int j =0;j<256;j++) {
            String s = "";
            if (str_length[j] != 0) {
                int x=0;
                while(x<str_length[j]){
                    if(code.size()==0){
                        value=inputStream.read();
                        read(value);
                    }
                    s=s+code.get(0);
                    code.remove(0);
                    x++;
                }
                hashMap.put(s, j);
            }
        }//构建哈希表

        // System.out.println( "构建哈希表后的code size   "+ code.size());



        int written_length =0;
        String theCodeOfRead="";
        while (written_length<lengthOfChar){
            if(code.size()==0){
                value=inputStream.read();
                read(value);
            }
            theCodeOfRead=theCodeOfRead+code.get(0);
            code.remove(0);
            if(hashMap.containsKey(theCodeOfRead)){
                /// System.out.println(ss+ "   code  ");
                outputStream.write(hashMap.get(theCodeOfRead));
                written_length++;
                theCodeOfRead="";
            }

        }
        code.clear();
        hashMap.clear();
        outputStream.close();
        decodeFile(inputStream);

    }

    /**
     * 读文件中的内容
     * @param x
     */
    private static void read(int x ){
        for(int i=0;i<8;i++){
            int y=x>> readCount;
            readCount--;
            if(readCount==-1)
                readCount=7;
            y= y&1;
            code.add(y);
        }
    }




    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        System.out.println("输入你要解压的文件(.zip)");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
       decode decode= new decode(path);
        long end = System.currentTimeMillis();
        System.out.println("execute time:"+(end - start)+"ms");

    }

}

