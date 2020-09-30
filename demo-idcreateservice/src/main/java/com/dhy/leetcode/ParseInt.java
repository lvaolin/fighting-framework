package com.dhy.leetcode;

public class ParseInt {

    public int parse(String s){
        if (s == null) {
            return 0;
        }

        int result = 0;
        //正数还是负数
        boolean negative = false;
        int i = 0, len = s.length();
        //int limit = -Integer.MAX_VALUE;

        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    //limit = Integer.MIN_VALUE;
                } else if (firstChar != '+'){
                    return 0;
                }
                if (len == 1){
                    // Cannot have lone "+" or "-"
                    return 0;
                }
                i++;
            }
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                int digit = s.charAt(i++);
                //48-57
                if(digit<48||digit>57){
                    return 0;
                }
                switch (digit){
                    case 48:
                        result = result*10+0;
                        break;
                    case 49:
                        result = result*10+1;
                        break;
                    case 50:
                        result = result*10+2;
                        break;
                    case 51:
                        result = result*10+3;
                        break;
                    case 52:
                        result = result*10+4;
                        break;
                    case 53:
                        result = result*10+5;
                        break;
                    case 54:
                        result = result*10+6;
                        break;
                    case 55:
                        result = result*10+7;
                        break;
                    case 56:
                        result = result*10+8;
                        break;
                    case 57:
                        result = result*10+9;
                        break;
                }

            }
        } else {
            return 0;
        }
        return negative ? result : -result;
    }
}
