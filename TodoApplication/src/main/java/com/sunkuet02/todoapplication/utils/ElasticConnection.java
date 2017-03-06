package com.sunkuet02.todoapplication.utils;

/**
 * Created by sun on 3/6/17.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunkuet02.todoapplication.models.Task;
import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by sun on 2/16/17.
 */
public class ElasticConnection {
    final static Logger logger = Logger.getLogger(ElasticConnection.class);

    private String taskMappingFile = "src/main/resources/db/nosql/tasks.json";
    private String taskSettingFile = "src/main/resources/db/nosql/task_settings.json";

    private Client client;

    public ElasticConnection(String clusterName, String host, int port) throws IOException {
        Settings settings = Settings.settingsBuilder()
            .put("client.transport.sniff", true)
            .put("cluster.name", clusterName).build();

        try {
            client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        } catch (UnknownHostException e) {
            logger.error("Could not create elasticsearch Connection, Cause : " , e);
        }
    }

    public Client getClient() {
        return client;
    }

    public void closeConnection() {
        client.close();
    }

    public boolean putMapping(String index, String type) throws IOException {
        IndicesExistsResponse res = client.admin().indices().prepareExists(index).execute().actionGet();
        if(!res.isExists()) {
            String mappings = new String(Files.readAllBytes(Paths.get(taskMappingFile)));
            String settingFromFile = new String(Files.readAllBytes(Paths.get(taskSettingFile)));

            CreateIndexResponse indexResponse = client.admin()
                .indices()
                .prepareCreate(index)
                .setSettings(settingFromFile)
                .addMapping(type, mappings)
                .execute()
                .actionGet();

            return indexResponse.isAcknowledged();
        }
        return false;
    }

    public IndexResponse addTask(Task task) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(task);

        IndexResponse response = client.prepareIndex("todoapp", "task").
            setSource(jsonString).
            execute().
            actionGet();
        return response;
    }

    public List<Task> searchTask(String username, String text) throws IOException {
        QueryBuilder queryBuilder;

        if(text.length() == 0) {
            queryBuilder =QueryBuilders.matchAllQuery();
        } else if(text.length() == 1) {
            queryBuilder =QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("username", username))
                .must(QueryBuilders.prefixQuery("heading", text))
                .must(QueryBuilders.prefixQuery("description", text));
        } else {
            queryBuilder =QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("username", username))
                .must(QueryBuilders.multiMatchQuery(text, "heading", "description"));
        }

        SearchResponse response = client.prepareSearch("todoapp")
            .setTypes("task")
            .setQuery(queryBuilder)
            .get();

        List<Map<String, Object>> searchResultItems = new ArrayList<>();
        List<Task> searchResults = new ArrayList<>();

        for(SearchHit hit : response.getHits().getHits()) {
            searchResultItems.add(hit.getSource());
            String jsonString = new ObjectMapper().writeValueAsString(hit.getSource());
            Task task = new ObjectMapper().readValue(jsonString, Task.class);
            searchResults.add(task);
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(searchResultItems);
        return searchResults;
    }

    private XContentBuilder getListItemMapping() throws IOException {
        String mappings = "{" + "\n"
            + " \"itemlist\" : {" + "\n"
            + "\"item\" : { " + "\n"
            + "\"properties\": {" + "\n"
            + "\"name\" : {" + "\n"
            + "\"type\" : \"string\"" + "\n"
            + "}," + "\n"
            + "\"description\" : {" + "\n"
            + "\"type\" : \"string\"" + "\n"
            + "}" + "\n"
            + "}" + "\n"
            + "}" + "\n"
            + "}" + "\n"
            + "}";
        return XContentFactory.jsonBuilder().value(mappings);
    }
}