package com.dhy.temp;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueueTest {

        public static void main(String[] args) throws Exception {

                System.out.println (new StringBuilder ().append ("Byte-->>").append ("字节数：").append (Byte.BYTES).append (";位数：").
                        append (Byte.SIZE).append ("; 最小值-->最大值:").append (Byte.MIN_VALUE).append ("-->").append (Byte.MAX_VALUE));
                System.out.println (new StringBuilder ().append ("Short-->>").append ("字节数：").append (Short.BYTES).append (";位数：").
                        append (Short.SIZE).append ("; 最小值-->最大值:").append (Short.MIN_VALUE).append ("-->").append (Short.MAX_VALUE));
                System.out.println (new StringBuilder ().append ("Integer-->>").append ("字节数：").append (Integer.BYTES).append (";位数：").
                        append (Integer.SIZE).append ("; 最小值-->最大值:").append (Integer.MIN_VALUE).append ("-->").append (Integer.MAX_VALUE));
                System.out.println (new StringBuilder ().append ("Long-->>").append ("字节数：").append (Long.BYTES).append (";位数：").
                        append (Long.SIZE).append ("; 最小值-->最大值:").append (Long.MIN_VALUE).append ("-->").append (Long.MAX_VALUE));
                System.out.println (new StringBuilder ().append ("Float-->>").append ("字节数：").append (Float.BYTES).append (";位数：").
                        append (Float.SIZE).append ("; 最小值-->最大值:").append (Float.MIN_VALUE).append ("-->").append (Float.MAX_VALUE));
                System.out.println (new StringBuilder ().append ("Double-->>").append ("字节数：").append (Double.BYTES).append (";位数：").
                        append (Double.SIZE).append ("; 最小值-->最大值:").append (Double.MIN_VALUE).append ("-->").append (Double.MAX_VALUE));
                System.out.println (new StringBuilder ().append ("Character-->>").append ("字节数：").append (Character.BYTES).append (";位数：").
                        append (Character.SIZE).append ("; 最小值-->最大值:").append ((int)Character.MIN_VALUE).append ("-->").append ((int)Character.MAX_VALUE));

            BlockingQueue<Integer> queue = new SynchronousQueue<>();
            System.out.println(queue.offer(1,5, TimeUnit.SECONDS)+" ");
            System. out .print(queue.offer(1) + " ");
            System. out .print(queue.offer(2) + " ");
            System. out .print(queue.offer(3) + " ");
           // System. out .print(queue.take() + " ");
            System. out .println(queue.size());
        }

}
