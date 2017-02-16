/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunkuet02.search;
import models.DbConnection;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import util.ElasticConnection;
import util.JsonConverter;
import util.MySqlConnection;

import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;

/**
 *
 * @author sun
 */
@Path("/item")
public class Entry {
    final static Logger logger = Logger.getLogger(Entry.class);
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(Item item) throws ClassNotFoundException, SQLException {

        DbConnection dbConnection = new MySqlConnection();
        Connection connection = dbConnection.getConnection("localhost", "root", "root", "listitems");
        String query = "insert into items(name, description) values(" + "'" + item.getName() + "'" + "," + "'" + item.getDescription() +"')";

        dbConnection.executeUpdateQuery(connection, query);
        dbConnection.closeConnection(connection);

        logger.info(" *** Added Item ***");
        
        return Response.status(Response.Status.CREATED).entity("Added " + item.getName()).build();
    }

    @GET
    @Path("/query")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@QueryParam("name") String name) throws ClassNotFoundException, SQLException, JSONException {
        DbConnection dbConnection = new MySqlConnection();
        Connection connection = dbConnection.getConnection("localhost", "root", "root", "listitems");
        String query = "select * from items where name=" + "'" + name +"'";

        ResultSet resultSet = dbConnection.executeSelectQuery(connection, query);
        String jsonString = JsonConverter.convertResultSetToJson(resultSet);
        dbConnection.closeConnection(connection);

        return Response.status(Response.Status.OK).entity(jsonString).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSearchResult(@QueryParam("text") String text) throws SQLException, ClassNotFoundException, JSONException {
        DbConnection dbConnection = new MySqlConnection();
        Connection connection = dbConnection.getConnection("localhost", "root", "root", "listitems");
        String query = "select * from items where name like" + "'%" + text +"%'" + " OR description like" + "'%" + text + "%'";

        ResultSet resultSet = dbConnection.executeSelectQuery(connection, query);
        String jsonString = JsonConverter.convertResultSetToJson(resultSet);
        dbConnection.closeConnection(connection);

        return Response.status(Response.Status.OK).entity(jsonString).build();
    }

    @POST
    @Path("/es/setmappings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setMappings(ESModel esModel) throws IOException, ExecutionException, InterruptedException {
        ElasticConnection elasticConnection = new ElasticConnection();
        Client client = elasticConnection.createConnection("elasticsearch", "localhost", 9300);

        if(client.admin().indices().exists(new IndicesExistsRequest(esModel.getIndex())).get().isExists()) {
            client.admin().indices().delete(new DeleteIndexRequest(esModel.getIndex())).get();
        }

        logger.info(esModel.getType() + "****** Mapping updated for type : "+esModel.getType() +", Index : " + esModel.getIndex());
        CreateIndexResponse response = elasticConnection.setListItemMapping(client, esModel.getIndex(), esModel.getType());
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @POST
    @Path("/es/additem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItemToES(Item item) throws IOException {
        ElasticConnection elasticConnection = new ElasticConnection();
        Client client = elasticConnection.createConnection("elasticsearch", "localhost", 9300);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(item);

        IndexResponse response = client.prepareIndex("itemlist", "item").
            setSource(jsonString).
            execute().
            actionGet();

        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
    }

    @GET
    @Path("/es/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchItemOnES(@QueryParam("text") String text) throws UnknownHostException {
        ElasticConnection elasticConnection = new ElasticConnection();
        Client client = elasticConnection.createConnection("elasticsearch", "localhost", 9300);

        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", text);
        SearchResponse response = client.prepareSearch("itemlist")
            .setTypes("item")
            .setQuery(queryBuilder)
            .get();
        logger.info(response.getHits().getHits());
        return Response.status(Response.Status.OK).entity(response.getHits()).build();
    }
}
