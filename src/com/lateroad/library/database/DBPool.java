package com.lateroad.library.database;

import com.lateroad.library.database.dao.ConnectorDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class DBPool {
    private static DBPool instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    private BlockingQueue<Connection> pool = new ArrayBlockingQueue<Connection>(20, true);

    public static DBPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new DBPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private DBPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        createPool();
    }

    private void createPool() {
        for (int i = 0; i < 5; i++) {
            try {
                pool.add(ConnectorDB.getConnection());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void putConnection(Connection connection) {
        if (connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
