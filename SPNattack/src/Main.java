import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {


    public static void main(String[] args) {

        String inputFileName = "input_spn.txt";
        File file = new File(inputFileName);
        ArrayList<String> arrayList = new ArrayList<>();
        //read input_spn.txt
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bf = new BufferedReader(fileReader);
            String str;
            while((str = bf.readLine()) != null){
                arrayList.add(str);
            }
            fileReader.close();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> sBox = Table.sBoxMap(arrayList.get(1));
        int[][] table = Table.tableGen(sBox);

        //print
        System.out.printf("%-4s"," ");
        for(int i = 0; i < 16; i++){
            System.out.printf("%-3s", i);
        }
        System.out.println("\n");
        for(int i = 0; i < 16; i++){
            System.out.printf("%-4s", i);
            for(int j = 0; j < 16; j++){
                System.out.printf("%-3s", table[i][j]);
            }
            System.out.println("\n");
        }

//        String test = "1111";
//        int s = Integer.parseInt(test,2);
//        System.out.println(s);

    }
}
