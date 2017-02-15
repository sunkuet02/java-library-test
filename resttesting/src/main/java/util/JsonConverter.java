package util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by sun on 2/15/17.
 */
public class JsonConverter {
    public static String convertResultSetToJson(ResultSet resultSet) throws SQLException, JSONException {
        if(resultSet == null)
            return null;

        JSONArray json = new JSONArray();
        ResultSetMetaData metadata = resultSet.getMetaData();
        int numColumns = metadata.getColumnCount();

        while(resultSet.next()) 			//iterate rows
        {
            JSONObject obj = new JSONObject();		//extends HashMap
            for (int i = 1; i <= numColumns; ++i) 			//iterate columns
            {
                String column_name = metadata.getColumnName(i);
                obj.put(column_name, resultSet.getObject(column_name));
            }
            json.put(obj);
        }
        return json.toString();
    }
}
