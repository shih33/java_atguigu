package p1;

import java.io.*;
import java.util.ArrayList;
import java.math.BigDecimal;

/*
 * 找出債券類ETF(基金代號是B結尾)的最新申報的總淨值合計。
 * 建立ETF物件
 */

public class readETF4 {
    public static void main(String[] args) throws IOException {
        //創建File物件
        File file = new File("ETF日報表1.csv");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String data;
        ArrayList<String> etf_array = new ArrayList<String>(); //存儲讀取過的債券類ETF
        BigDecimal sum = new BigDecimal("0");  //債券類ETF的最新申報的總淨值合計
        ETF[] table = new ETF[12000];
        int i = 0;

        data = br.readLine(); //讀取第一行的中文
        while((data = br.readLine()) != null) {
            String[] str = data.split("\"");  //以"分割data
            table[i] = new ETF(str[1], str[3], str[5], str[7], str[9]);
            i++;
        }
        for(int j=0; j < i-1 ; j++) {
            System.out.println(table[j].getDate() + ' '+ table[j].getSymbol() + ' ' + table[j].getUnitNetValue()
                    + ' ' + table[j].getIssuedUnits() + ' ' + table[j].getTotalNetValue());
        }

        //System.out.println(i);

        br.close();
    }
}