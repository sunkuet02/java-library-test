package util;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by sun on 2/16/17.
 */
public class ElasticConnection {
    final static Logger logger = Logger.getLogger(ElasticConnection.class);
    private String itemListMappingFile = "/media/sun/20C1-A87E/java-library-test/resttesting/src/main/resources/itemlistmapping.json";

    public Client createConnection(String clusterName, String host, int port) throws UnknownHostException {
        Settings settings = Settings.settingsBuilder()
            .put("client.transport.sniff", true)
            .put("cluster.name", clusterName).build();

        Client client = TransportClient.builder().settings(settings).build()
            .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
        return client;
    }

    public CreateIndexResponse setListItemMapping(Client client, String index, String type) throws IOException {
        String mappings = new String(Files.readAllBytes(Paths.get(itemListMappingFile)));
        CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices().prepareCreate(index);
        createIndexRequestBuilder.addMapping(type, mappings);
        CreateIndexResponse indexResponse =createIndexRequestBuilder.execute().actionGet();
        return indexResponse;
    }

    private XContentBuilder getListItemMapping() throws IOException {
        String mappings = "{"                                 + "\n"
            + " \"itemlist\" : {"                                + "\n"
            +     "\"item\" : { "                            + "\n"
            +          "\"properties\": {"                    + "\n"
            +              "\"name\" : {"                     + "\n"
            +                   "\"type\" : \"string\""       + "\n"
            +               "},"                              + "\n"
            +               "\"description\" : {"             + "\n"
            +                   "\"type\" : \"string\""       + "\n"
            +               "}"                               + "\n"
            +           "}"                                   + "\n"
            +       "}"                                       + "\n"
            +   "}"                                           + "\n"
            + "}" ;
        return XContentFactory.jsonBuilder().value(mappings);
    }
}
