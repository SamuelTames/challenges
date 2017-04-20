//Given "25525511135",
//return ["255.255.11.135", "255.255.111.35"].

import java.util.*;

public class validIP {
    public static void main(String[] args) {
       ArrayList<String> result = possibleIPs("12212235");

       for (String s : result) {
           System.out.println(s);
       }

    }
    public static ArrayList<String> possibleIPs(String input) {
        if (input.length() > 12 || input.length() < 4) {
            return null;
        }
        if (input == null) {
            return null;
        }

        ArrayList<String> result = new ArrayList<>();

        possibleIPs(result, new String[] {"","","",""}, 0, input, 0);

        return result;
    }

    public static void possibleIPs(ArrayList<String> result, String[] octets, int octetsIndex, String pool, int poolIndex) {
        if (octetsIndex == octets.length && poolIndex == pool.length()) {
            result.add(octets[0] + "." + octets[1] + "." + octets[2] + "." + octets[3]);
            return;
        }

        if (octetsIndex == octets.length) {
            return;
        }

        String generatedOctet = octets[octetsIndex];
        for (int i = poolIndex; i < pool.length() && i < poolIndex+3; i++) {
            generatedOctet += pool.charAt(i);

            if (validOctet(generatedOctet)) {
                String[] newOctetCombo = octets.clone();
                newOctetCombo[octetsIndex] = generatedOctet;

                possibleIPs(result, newOctetCombo, octetsIndex+1, pool, i+1);
            }
        }
    }

    public static boolean validOctet(String octet) {
        if (octet.length() > 3 || octet.length() < 1) {
            return false;
        }
        if (octet.length() != 1 && octet.charAt(0) == '0') {
            return false;
        }

        int i = Integer.parseInt(octet);
        if (i < 0 || i > 255) {
            return false;
        }

        return true;
    }
}
