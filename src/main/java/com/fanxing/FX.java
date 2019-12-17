package com.fanxing;/**
 * Created by lvaolin on 17/11/9.
 */

/**
 * 测试定义泛型类
 *
 * @author lvaolin
 * @create 17/11/9 下午7:36
 */
public class FX<X,Y> {

    private X data;
    private Y data2;
    public FX(X data,Y data2){
        this.data = data;
        this.data2 = data2;
    }

    public X getData(){
        return data;
    }
    public Y getData2(){
        return data2;
    }

    public void showType(){
        System.out.println("X的实际类型是: "+data.getClass().getName());
        System.out.println("Y的实际类型是: "+data2.getClass().getName());
    }


    public static void main(String[] args) {
             FX<Integer,Integer> intOb = new FX<Integer,Integer>(100,100);
             intOb.showType();
             System.out.println("value= " + intOb.getData());
             System.out.println("----------------------------------");

             FX<String,Integer> strOb = new FX<String,Integer>("CSDN_SEU_Calvin",200);
             strOb.showType();
             System.out.println("value= " + strOb.getData());

            FX<Number,Integer> ex_num = new FX<Number,Integer>(100,100);
            FX<Integer,Double> ex_int = new FX<Integer,Double>(200,300d);
            getData(ex_num);
            getData(ex_int);//编译错误
            getUpperNumberData(ex_num);
            getUpperNumberData(ex_int);

    }


    public static void getData(FX<?,?> temp) { //此行若把Number换为“？”编译通过
        //do something...
    }

    public static void getUpperNumberData(FX<? extends Number,? extends Number> temp){
              System.out.println("class type :" + temp.getClass());
    }
}
