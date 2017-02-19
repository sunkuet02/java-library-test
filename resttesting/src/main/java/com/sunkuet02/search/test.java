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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sun on 2/16/17.
 */
public class test {
    public static void main(String[] agrs) throws IOException {
        Item item = new Item("sun", "khadok");

        ElasticConnection elasticConnection = new ElasticConnection();
        Client client = elasticConnection.createConnection("elasticsearch", "localhost", 9300);

        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "alu");
        SearchResponse response = client.prepareSearch("itemlist")
            .setTypes("item")
            .setQuery(queryBuilder)
            .get();

        List<Map<String, Object>> searchItems = new ArrayList<>();
        for (SearchHit hit : response.getHits().getHits()) {
            searchItems.add(hit.getSource());
            System.out.println("---");
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(searchItems);

        System.out.println(jsonString);


    }
}
