package com.dhy.int2byte;

/**
 * int 和 byte[] 之间的互转
 */
public class ByteUtil {

    /**
     * int 转换为 byte[]
     * 规则：高字节在数组的高位
     * 比如 ： 00000100  00000010  00000001  00001111
     *
     * (temp>>24)&0xFF = 00000100
     *
     * (temp>>16)&0xFF = 00000000 00000010
     *
     * (temp>>8)&0xFF = 00000000 00000000  00000001
     *
     * (temp)&0xFF  = 00000000 00000000 00000000  00001111
     *
     * 结果为 byte[0] = 00001111   byte[1]=00000001  byte[2]=00000010  byte[3]=00000100
     * @param temp
     * @return
     */
    public static byte[] int2bytes(int temp){
        //int temp= 0000_0000_0000_0001;
        System.out.println(Integer.toBinaryString(temp));
        byte[] bytes = new byte[4];
        bytes[3]= (byte) ((temp>>24)&0xFF);
        bytes[2]= (byte) ((temp>>16)&0xFF);
        bytes[1]= (byte) ((temp>>8)&0xFF);
        bytes[0]= (byte) ((temp)&0xFF);
        return bytes;
    }

    /**
     * 与
     * 将长度为4的byte[]转换为int
     * 比如：src[0]=  00001111   src[1]=00000001  src[2]=00000010  src[3]=00000100
     * 过程为：
     *                                                (src[0] & 0xFF)=00001111
     *
     *                  (src[1] & 0xFF) << 8 = 00000001<<8 = 00000001 00000000
     *
     *          (src[2] & 0xFF) << 16= 00000010<<16=00000010 00000000 00000000
     *
     * (src[3] & 0xFF) << 24= 00000100<<24=00000100 00000000 00000000 00000000
     *
     * 最后进行位或运算 = 00000100 00000010 00000001 00001111
     *
     * @param src
     * @return
     */
    public static int bytes2Int(byte[] src) {
        int value;
        value = (int) ((src[0] & 0xFF) | ((src[1] & 0xFF) << 8) | ((src[2] & 0xFF) << 16) | ((src[3] & 0xFF) << 24));
        return value;
    }



    public static void main(String[] args) {
        byte[] bytes = ByteUtil.int2bytes(99999999);

        for (byte aByte : bytes) {
            System.out.println(Integer.toBinaryString(aByte));
        }

        System.out.println(ByteUtil.bytes2Int(bytes));

    }
}
