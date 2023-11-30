package com.techelevator.projects.dao;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestingDatabaseConfig.class)
public abstract class BaseDaoTest {

    public static final String NOT_IMPLEMENTED_LONG_SUFFIX = "() not implemented";

    @Autowired
    protected DataSource dataSource;

    @Autowired
    protected DataSource invalidDataSource;

    protected String didNotThrowAnyExceptionForCannotGetConnection(String methodName) {
        return methodName + "() did not throw exception if unable to reach database";
    }

    protected String didNotThrowDaoExceptionForCannotGetConnection(String methodName) {
        return methodName + "() threw an exception, but it should throw DaoException if unable to reach database";
    }

    protected String didNotThrowAnyExceptionForDataIntegrity(String methodName) {
        return methodName + "() did not throw exception if data integrity violation occurs";
    }

    protected String didNotThrowDaoExceptionForDataIntegrity(String methodName) {
        return methodName + "() threw an exception, but it should throw DaoException if data integrity violation occurs";
    }

    protected String threwNotImplementedException(String methodName)
    {
        return methodName + "() not implemented";
    }


    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }

}
