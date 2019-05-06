import java.util.HashMap;
import java.util.Map;

public class Table {

    public static int[][] tableGen(Map<String, String> sBox){
        int[][] table = new int[16][16];
        String[] deltaxString = {"0000", "0001",  "0010",  "0011",  "0100",  "0101",  "0110",  "0111",
                "1000", "1001",  "1010",  "1011",  "1100",  "1101",  "1110",  "1111"};
        String[] xString = {"0000", "0001",  "0010",  "0011",  "0100",  "0101",  "0110",  "0111",
                "1000", "1001",  "1010",  "1011",  "1100",  "1101",  "1110",  "1111"};
        for(int i = 0; i < 16; i ++){
            String deltax = deltaxString[i];
            String Sx, xdiff, Sxdiff, output;
            for(int j = 0; j < 16; j ++){
                String x = xString[j];
                Sx = sBoxForward(x, sBox); //S(x)
                xdiff = xor(x, deltax); //x \xor deltax
                Sxdiff = sBoxForward(xdiff, sBox); //S(xdiff)
                output = xor(Sx, Sxdiff);
                int index = Integer.parseInt(output,2);
                table[i][index]++;
            }
        }
        return table;
    }


    /**
     * S-box map
     */
    public static Map<String, String> sBoxMap(String sBox){
        int m = 4;
        Map<String, String> sBoxMap = new HashMap<>();
        String order = "00000000 00000001 00000010 00000011 " +
                "00000100 00000101 00000110 00000111 " +
                "00001000 00001001 00001010 00001011 " +
                "00001100 00001101 00001110 00001111";
        for(int i = 0; i < 16; i ++){ //construct a S-box map for lookup
            String sBoxPart = sBox.substring(m + i * 8, m + i * 8 + 4);
            String orderPart = order.substring(m + i * 8, m + i * 8 + 4);
            sBoxMap.put(orderPart,sBoxPart);
            //System.out.println(sBoxMap);
            m ++;
        }
        return sBoxMap;
    }

    /**
     * XOR computation
     * Two inputs are XORed
     */
    public static String xor(String msg, String subkey){
        if (msg.length() != subkey.length()) {
            throw new IllegalArgumentException();
        }
        StringBuilder stringXOR = new StringBuilder();
        for(int i = 0; i < msg.length(); i ++){
            stringXOR.append(msg.charAt(i) != subkey.charAt(i) ? '1': '0'); //XOR
        }
        return stringXOR.toString();
    }

    /**
     * S-box transformation for encrption (forward)
     * msg is a 24-bit string, and is encrpted by s-Box map
     */
    public static String sBoxForward(String msg, Map<String, String> sBox){
        StringBuffer newString = new StringBuffer();
        newString.append(sBox.get(msg));
        return newString.toString();
    }
}

