//Given a string containing only digits, recover IP addresses in it by returning all possible valid IP address combinations.
//    For example: Given "25525511135",
//    return ["255.255.11.135", "255.255.111.35"].

import java.util.*;

public class validIP {
    public static void main(String[] args) {
        String digits = "25525511135";

        if (args.length > 0) {
            digits = args[0];
        }

        for (String s : possibleIPs(digits)) {
            System.out.println(s);
        }
    }

    public static final int MAX_OCTET_LEN = 3;
    public static final int MIN_OCTET_LEN = 1;
    public static final int MAX_IPV4_LEN = MAX_OCTET_LEN * 4;
    public static final int MIN_IPV4_LEN = MIN_OCTET_LEN * 4;

    public static ArrayList<String> possibleIPs(String input) {
        ArrayList<String> result = new ArrayList<>();

        if (input == null) {
            return result;
        }
        if (input.length() > MAX_IPV4_LEN || input.length() < MIN_IPV4_LEN) {
            return result;
        }

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
        for (int i = poolIndex; i < pool.length() && i < poolIndex+MAX_OCTET_LEN; i++) {
            generatedOctet += pool.charAt(i);

            if (validOctet(generatedOctet)) {
                String[] newOctetCombo = octets.clone();
                newOctetCombo[octetsIndex] = generatedOctet;

                possibleIPs(result, newOctetCombo, octetsIndex+1, pool, i+1);
            }
        }
    }

    public static boolean validOctet(String octet) {
        if (octet.length() > MAX_OCTET_LEN || octet.length() < MIN_OCTET_LEN) {
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
