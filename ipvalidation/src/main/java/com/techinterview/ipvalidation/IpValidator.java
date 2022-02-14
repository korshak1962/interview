package com.techinterview.ipvalidation;

public class IpValidator {

    public static final String REGEX_CIDR = "^([0-9]{1,3}\\.){3}[0-9]{1,3}(\\/([0-9]|[1-2][0-9]|3[0-2]))?$";
    public static final String REGEX_ADR = "^(?:(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])(\\.(?!$)|$)){4}$";

    /**
     * Implement the function that validates if provided IP address is in the range defined by cidrRange parameter.
     * You can read about CIDR here https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing
     * Also, you can use this page https://www.ipaddressguide.com/cidr to understand better CIDR concept.
     * <p>
     * Code organization, implementing other functions that are called from validateAddress - all of this up to you.
     * The final code must be compilable, buildable and tested.
     * <p>
     * You are free to use any build framework to build the code (maven, gradle, ant, etc.).
     * <p>
     * You can use any java version for development (8, 9, 10, 11).
     * <p>
     * You can use any libraries to simplify any auxiliary logic (e.g. string parsing).
     * But you cannot use any 3rd party libraries to implement validation itself.
     * <p>
     * Testing framework selection is up to you. Testing the logic from "public static void main" is also OK.
     * <p>
     * If you are familiar with Git, do your work in a branch and create pull request.
     *
     * @param ipAddress String representation of IP address that needs to be validated.
     *                  Function must verify that IP address definition itself is valid.
     *                  If IP address format is invalid, function must throw IllegalArgumentException.
     *                  E.g. 192.168.0.1 is correct definition of IP address.
     *                  256.0.0.1, 192,168.o.1, 192,168.0.1 are examples of invalid IP addresses.
     * @param cidrRange Classless Inter-Domain Routing (CIDR) definition that defines range of allowed IP addresses.
     *                  For example 11.1.0.0/24 CIDR defines IP addresses range from 11.1.0.0 to 11.1.0.255
     *                  Function must verify that cidrRange definition itself is valid.
     *                  If cidrRange format is invalid, function must throw IllegalArgumentException.
     *                  E.g. 192.168.0.0/24, 100.0.0.0/16, 10.10.0.0/32, 10.10.0.1/16 are valid definitions.
     *                  192.168.0.0/35, 300.0.0.0/16, 10.10,0.0/32, 10.10.0.256/16 are invalid definitions
     *                  If slash is omitted in cidrRange definition, you can assume that / 32 is used.
     *                  E.g. cidrRange 10.10.0.1 can be treated as 10.10.0.1/32
     * @return true if provided IP address is covered by the CIDR range; false otherwise.
     * @throws IllegalArgumentException if either ipAddress or cidrRange definitions is invalid.
     */
    public static boolean validateIpAddress(String ipAddress, String cidrRange) {
        if (!isIpAddrValid(ipAddress)) throw new IllegalArgumentException("ipAddress " + ipAddress + " is not valid.");
        if (!isCidrRangeValid(cidrRange))
            throw new IllegalArgumentException("cidrRange " + cidrRange + "is not valid.");
        int prefixLength = 32;
        int cidr;
        String[] splitted = cidrRange.split("/");
        if (splitted.length > 1) {
            cidr = ipToInt(splitted[0]);
            prefixLength = Integer.parseInt(splitted[1]);
        } else {
            cidr = ipToInt(cidrRange);
        }
        int ipAddr = ipToInt(ipAddress);
        final int shiftToRight = 32 - prefixLength;
        if ((cidr >> shiftToRight) == (ipAddr >> shiftToRight)) return true;
        return false;
    }


    static boolean isIpAddrValid(String ipAddress) {
        return ipAddress.matches(REGEX_ADR);
    }

    static boolean isCidrRangeValid(String cidrRange) {
        return cidrRange.matches(REGEX_CIDR);
    }

    static int ipToInt(final String ipAddrDecimalPointSeparated) {
        int fullAddr = 0, segmentParsedValue = 0;
        // Parse all segments of the IP address.
        for (char digit : ipAddrDecimalPointSeparated.toCharArray()) {
            if (digit != '.') {
                segmentParsedValue = segmentParsedValue * 10 + (digit - '0');
            } else {
                // merge segmentParsedValue into fullAddr
                fullAddr = (fullAddr << 8) | segmentParsedValue;
                segmentParsedValue = 0;
            }
        }
        return (fullAddr << 8) | segmentParsedValue;
    }
}
