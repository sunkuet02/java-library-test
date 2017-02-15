/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sunkuet02.search;
import models.DbConnection;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import util.JsonConverter;
import util.MySqlConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
