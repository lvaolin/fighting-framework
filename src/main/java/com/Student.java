package com;

/**
 * @author lvaolin
 * @create 18/4/17 上午11:03
 */
public class Student extends Person{
    public  String name = "s1";
    public static String nameStatic = "static s1";

    {
        System.out.println("Student非静态代码块");
    }

    static {
        System.out.println("Student静态代码块");
    }

    public Student(){
        System.out.println("Student 无参构造方法");
    }
    @Override
    public String show(){
        return "Student:"+name;
    }
    public static String showStatic(){
        return "Student："+nameStatic;
    }

    public static void main(String[] args) {

        Student student = new Student();
        System.out.println(student.name);
        System.out.println(student.show());
        System.out.println(Student.showStatic());
        Person person = new Student();
        System.out.println(person.name);
        System.out.println(person.show());
        System.out.println(Person.showStatic());
    }

}
