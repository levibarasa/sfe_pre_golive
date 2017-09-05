package com.sfe.iso;

public class PrepareFields {

    public String formatField2(String str) {
        return leftPaddingNames(16, str);
    }

    public String formatField3(String str) {
        // Processing Code Length is 6 (Zeros in right)
        return formatProcessingCode(str);
    }

    public String formatField4(String str) {
        // Field Length is 16 (Zeros in left)
        return formatMoney(str);
    }

    public String formatField11(String str) {
        return leftPaddingNames(12, str);
    }

    public String formatField24(String str) {
        return leftPaddingNames(3, str);
    }

    public String formatField32(String str) {
        return formatBankCode(str);
    }

    public String formatField49(String str) {
        return leftPaddingNames(3, str);
    }

    public String formatField43(String str) {
        return leftPaddingNames(50, str);
    }

    public String formatField102(String account, String bank, String branch) {
        // Account Number Field Length is 16 (Spaces in left)
        // Bank ID Length is 11 (Spaces in right)
        // Branch code Length is 8 (Spaces in left)
        String resp = padRight(bank, 11) + leftPaddingNames(8, branch) + leftPaddingNames(14, account);
        return resp;
    }

    public String formatField103(String account, String bank, String branch) {
        String resp = padRight(bank, 9) + leftPaddingNames(12, branch) + " " + account;
        // String resp = padRight(bank, 9) + leftPaddingNames(10, branch) + leftPaddingNames(12, account);
        return resp;
    }

    public String formatField123(String str) {
        return leftPaddingNames(3, str);
    }

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String formatMoney(String str) {
        int val = Integer.parseInt(str);
        String str2 = String.format("%16s", String.valueOf(val)).replace(' ', '0');
        return str2;
    }

    public static String formatBankCode(String str) {
        int val = Integer.parseInt(str);
        String str2 = String.format("%6s", String.valueOf(val)).replace(' ', '0');
        return str2;
    }

    public static String formatProcessingCode(String str) {
        int val = Integer.parseInt(str);
        String str2 = String.format("%-6s", val).replace(' ', '0');
        return str2;
    }

    public static String leftPaddingNames(int length, String string) {
        return String.format("%1$" + length + "s", string);
    }
}
