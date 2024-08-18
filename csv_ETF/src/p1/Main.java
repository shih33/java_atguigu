package p1;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("ETF日報表_20240701.csv");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String data;
        long sum = 0;
        data = br.readLine(); //讀取第一列的中文
        while((data = br.readLine()) != null) {
            String[] str = data.split("\"");
            if(str[3].endsWith("B")) {
                String noComma = str[9].replace("," , "");
                long number = Long.parseLong(noComma);
                sum += number;
            }
        }
        System.out.println(sum);
        //
        br.close();
    }
}


//                System.out.print(str[3] + " " + number);
//                System.out.print("\n");
