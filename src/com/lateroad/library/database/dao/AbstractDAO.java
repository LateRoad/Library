package com.lateroad.library.database.dao;

import com.lateroad.library.database.CommonDAO;
import com.lateroad.library.entity.Entity;

public abstract class AbstractDAO<T extends Entity> implements CommonDAO<T> {

//    protected WrapperConnector connector;
//
//
//    protected void close() {
//        connector.closeConnection();
//    }
//    protected void closeStatement(Statement statement) {
//        connector.closeStatement(statement);
//    }
}