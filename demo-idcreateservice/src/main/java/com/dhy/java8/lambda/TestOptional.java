package com.dhy.java8.lambda;

import java.util.Optional;

/**
 * @Title TestOptional
 * @Description
 * @Author lvaolin
 * @Date 2021/2/14 22:05
 **/
public class TestOptional {

    public static void main(String[] args) {
       // Optional<Object> o1 = Optional.of(null);//会报错
        Optional<Object> o2 = Optional.ofNullable(null);//不会报错
        o2.ifPresent((o)->{
            System.out.println(o);
        });

        Optional<Integer> x = Optional.of(100);
        System.out.println(x.get().intValue());


        User user = new User("anna@gmail.com", "1234","aa");



    }

    static class User{
        public User(String address, String country, String isocode) {
            this.address = address;
            this.country = country;
            this.isocode = isocode;
        }

        String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getIsocode() {
            return isocode;
        }

        public void setIsocode(String isocode) {
            this.isocode = isocode;
        }

        String country;
        String isocode;
    }
}
