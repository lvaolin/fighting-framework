package com.concurrency.forkjoin;/**
 * Created by lvaolin on 17/10/16.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 考试系统抽题，每种类型的题默认抽取5道即可，把题号放入list返回
 *
 * @author lvaolin
 * @create 17/10/16 上午10:37
 */
public class GetQuestionsTask extends RecursiveTask<List>{

    //参数list==只放题型
    private List questionTypeList;

    public GetQuestionsTask(List questionTypeList) {
        this.questionTypeList = questionTypeList;
    }

    @Override
    protected List compute() {
        System.out.println(Thread.currentThread().getName()+",目前分解的任务数量:"+questionTypeList.size());
        List list = new ArrayList();
        if (questionTypeList.size() <= 2) {
            // 抽题
            list = getQuestions(questionTypeList);
        } else {
            int size = questionTypeList.size();
            int mid = size / 2;
            GetQuestionsTask task1 = new GetQuestionsTask(
                    questionTypeList.subList(0, mid));
            GetQuestionsTask task2 = new GetQuestionsTask(
                    questionTypeList.subList(mid, size));
            invokeAll(task1, task2);
            try {
                list = groupResults(task1.get(), task2.get());
            } catch (InterruptedException e) {

                e.printStackTrace();
            } catch (ExecutionException e) {

                e.printStackTrace();
            }
        }

        return list;
    }

    //合并抽题结果
    private List groupResults(List list1, List list2) {
        System.out.println(Thread.currentThread().getName()+"开始合并结果......");
        // 合并返回结果
        List list = new ArrayList();
        list.addAll(list1);
        list.addAll(list2);
        System.out.println(Thread.currentThread().getName()+"合并结果结束......");
        return list;
    }

    // 抽题
    private List getQuestions(List questTypeList) {
        List list = new ArrayList();
        for(int i=0;i<questTypeList.size();i++){
            System.out.println(Thread.currentThread().getName()+"开始抽题......"+questionTypeList.get(i).toString());
            //假数据，向list中放试题  //实际应该根据试题类别去题库随机获取题号
            list.add(Thread.currentThread().getName()+questionTypeList.get(i).toString()+"-0-"+System.currentTimeMillis());
            list.add(Thread.currentThread().getName()+questionTypeList.get(i).toString()+"-1-"+System.currentTimeMillis());
            list.add(Thread.currentThread().getName()+questionTypeList.get(i).toString()+"-2-"+System.currentTimeMillis());
            list.add(Thread.currentThread().getName()+questionTypeList.get(i).toString()+"-3-"+System.currentTimeMillis());
            list.add(Thread.currentThread().getName()+questionTypeList.get(i).toString()+"-4-"+System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"抽题结束..."+questionTypeList.get(i).toString());
        }
        return list;
    }


    public static void main(String[] args) {


        //参数list==只放题型
         List questionTypeList = new ArrayList();
        for (int i = 0; i <20 ; i++) {
            questionTypeList.add("type"+i);
        }


        //该池的线程数量不会超过0*7fff个（32767）
        //池中维护着ForkJoinWorkerThread对象数组，数组大小由parallelism属性决定，parallelism默认为处理器个数
        ForkJoinPool pool = new ForkJoinPool();
        GetQuestionsTask task = new GetQuestionsTask(questionTypeList);
        pool.execute(task);
        // 试题列表=task.get()
        try {
            List<String> finalList = task.get();
            System.out.println("最终结果个数：" + finalList.size());

            for (String ss:finalList) {
                System.out.println(ss);
            }
        } catch (InterruptedException e) {

            e.printStackTrace();
        } catch (ExecutionException e) {

            e.printStackTrace();
        }
    }
}
