package org.sai.play.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Sai
 * Date: 08/10/11
 * Time: 13:53
 * To change this template use File | Settings | File Templates.
 */
public class CustomRowMapper<T> implements RowMapper {

    public T mapRow(ResultSet resultSet, int i) throws SQLException {
        // TODO not to worry about this implementation for now.
        return null;
    }
}
