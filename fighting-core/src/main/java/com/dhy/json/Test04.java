package com.dhy.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 代码不能靠天吃饭，一定要用最正确的代码兼容所有的情况
 * 比较两个对象的内容绝对不能用==
 * 虽然==在某些情况下可以获得正确的结果，但不能兼容所有情况
 */
public class Test04 {

    public static void main(String[] args) {

        String body = "{\"produceDataType\":3}";
        GlDto glDto = JSON.parseObject(body, GlDto.class);
        //glDto.setProduceDataType(new Integer(3));
        //glDto.setProduceDataType(3);
        GlDto glDto1 = JSONObject.parseObject(body, GlDto.class);
        System.out.println("GlConstant.reportByExe:"+GlConstant.reportByExe);
        System.out.println("glDto.produceDataType:"+glDto.produceDataType);
        System.out.println(GlConstant.reportByExe==glDto.produceDataType);
        System.out.println(GlConstant.reportByExe==glDto1.produceDataType);
        System.out.println(GlConstant.reportByExe.equals(glDto.produceDataType));
    }

    static class GlDto{
        public Integer getProduceDataType() {
            return produceDataType;
        }

        public void setProduceDataType(Integer produceDataType) {
            this.produceDataType = produceDataType;
        }

        private Integer produceDataType;
    }
//    static class GlDto{
//        public int getProduceDataType() {
//            return produceDataType;
//        }
//
//        public void setProduceDataType(int produceDataType) {
//            this.produceDataType = produceDataType;
//        }
//
//        private int produceDataType;
//    }

    static class GlConstant{
        public static final Integer reportByExe = 3;
    }
}
