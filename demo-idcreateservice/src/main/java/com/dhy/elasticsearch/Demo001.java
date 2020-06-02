package com.dhy.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author lvaolin
 * @create 2019/12/17 7:16 PM
 */
public class Demo001 {

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        //集群节点
                        //new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9200, "http")));
        if(client!=null){
            System.out.println("已连接");

            IndexRequest indexRequest = new IndexRequest();
            indexRequest.index("dahuangya");
            indexRequest.opType(DocWriteRequest.OpType.INDEX);
            indexRequest.source("i am you it hello word");
            indexRequest.create(true);

            IndexResponse index1 = client.index(indexRequest, RequestOptions.DEFAULT);
            System.out.println(index1.toString());
            System.out.println("创建索引库");

//            String s = "";
//            String id = "CW0sb2wBYtyF3syfPTY8";
//            String index = "article-2019.08.08.03";
//            GetRequest getRequest = new GetRequest(index,id);
//            GetResponse response = null;
//            response = client.get(getRequest, RequestOptions.DEFAULT);
//            Map<String, Object> source =  response.getSource();
//            System.out.println((String) source.get("content"));



            System.out.println("查询");
            System.out.println("删除");
        }


        if(client!=null){
            try {
                client.close();
                System.out.println("已关闭");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
