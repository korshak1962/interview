package com.techinterview.ipvalidation;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static com.techinterview.ipvalidation.IpValidator.validateIpAddress;

/**
 * tests for IpValidator class.
 */
public class IpValidatorTest {


    @Test
    public void testValidateIpAddressTrue() {
        Map<String, String> ipAddrToCidr = Map.of(
                "10.0.0.0", "10.0.0.0"
                ,"31.255.255.255", "10.1.0.27/3"
        );
        for (Map.Entry<String, String> entry : ipAddrToCidr.entrySet())
            Assert.assertTrue(validateIpAddress(entry.getKey(), entry.getValue()));
    }

    @Test
    public void testValidateIpAddressFalse() {
        Map<String, String> ipAddrToCidr = Map.of(
                "10.0.0.1", "10.0.0.0/32"
                ,"34.255.255.255", "10.1.0.27/3"
        );
        for (Map.Entry<String, String> entry : ipAddrToCidr.entrySet())
            Assert.assertFalse(validateIpAddress(entry.getKey(), entry.getValue()));
    }

    @Test
    public void testIpToBinaryString() {
        int fullAddr = IpValidator.ipToInt("127.0.0.1");
        String binString = get32StringFromInt(fullAddr);
        String expected = "01111111000000000000000000000001";
        System.out.println(binString);
        Assert.assertEquals(expected, binString);
        int mask = 0x80000000;
        System.out.println(Integer.toBinaryString(mask));
    }

    private String get32StringFromInt(int fullAddr) {
        char[] out = new char[32];
        for (int i = 0; i < 32; i++, fullAddr <<= 1)
            out[i] = ((fullAddr & 0x80000000) != 0) ? '1' : '0';
        return new String(out);
    }

    @Test
    public void testValidateOnlyIpAddrAccept() {
        String[] correctAdrs = {"127.0.0.1",
                "192.168.1.1",
                "192.168.1.255",
                "255.255.255.255",
                "0.0.0.0"};
        for (String ipAddr : correctAdrs) {
            final boolean valid = IpValidator.isIpAddrValid(ipAddr);
            if (!valid) System.out.println(ipAddr + " is not valid.");
            Assert.assertTrue(valid);
        }
    }

    @Test
    public void testValidateOnlyIpAddrReject() {
        String[] inCorrectAdrs = {
                "30.168.1.255.1",
                "127.1",
                "192.168.1.256",
                "-1.2.3.4",
                "1.1.1.1.",
                "3...3"};
        for (String ipAddr : inCorrectAdrs) {
            final boolean valid = IpValidator.isIpAddrValid(ipAddr);
            if (valid) System.out.println(ipAddr + " is  valid.");
            Assert.assertFalse(valid);
        }
    }

    @Test
    public void testvalidateOnlyCidrRangeAccept() {
        String[] correctCidr = {"192.168.0.1",
                "192.168.0.1/0",
                "192.168.0.1/1",
                "192.168.0.1/32"
        };
        for (String cidr : correctCidr) {
            final boolean valid = IpValidator.isCidrRangeValid(cidr);
            if (!valid) System.out.println(cidr + " is not valid.");
            Assert.assertTrue(valid);
        }
    }

    @Test
    public void testValidateOnlyCidrReject() {
        String[] inCorrectCidrs = {
                "3...3",
                "192.168.0.1/",
                "192.168.0.1/33",
                "192.168.0.1/34",
                "192.168.0.1/asd",
                "192.168.0.1/01",
                "192.168.0.1/00"};
        for (String cidr : inCorrectCidrs) {
            final boolean valid = IpValidator.isIpAddrValid(cidr);
            if (valid) System.out.println(cidr + " is  valid.");
            Assert.assertFalse(valid);
        }
    }
}
