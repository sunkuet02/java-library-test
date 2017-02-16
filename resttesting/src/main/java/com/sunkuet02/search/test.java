package com.sunkuet02.search;

import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import util.ElasticConnection;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by sun on 2/16/17.
 */
public class test {
    public static void main(String [] agrs) throws IOException {
        Item item = new Item("sun", "khadok");

        ElasticConnection elasticConnection = new ElasticConnection();
        Client client = elasticConnection.createConnection("elasticsearch", "localhost", 9300);

        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "alu");
        SearchResponse response = client.prepareSearch("itemlist")
            .setTypes("item")
            .setQuery(queryBuilder)
            .get();

        for(SearchHit hit : response.getHits().hits()) {

            System.out.println(hit.field("name"));
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(item);

        System.out.println(jsonString);



    }
}
