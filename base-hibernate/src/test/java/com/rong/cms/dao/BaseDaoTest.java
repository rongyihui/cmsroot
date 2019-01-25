package com.rong.cms.dao;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.xml.sax.InputSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public abstract class BaseDaoTest {

    /**
     * 设置table名称
     */
    protected static String tableName;

    @Resource
    private DataSource dataSource;
    @Resource
    private SessionFactory sessionFactory;
    private File file;
    private IDatabaseConnection iDatabaseConnection;

    @Before
    public void backup() throws DatabaseUnitException, IOException {
        iDatabaseConnection = new DatabaseConnection(DataSourceUtils.getConnection(dataSource));
        //解决延迟加载
        Session s = sessionFactory.openSession();
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
        backupOneTable();
    }

    @Test
    public abstract void add();

    @After
    public void resetTable() throws SQLException, DatabaseUnitException {
        //SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
        //Session s = holder.getSession();
        createDataSet();
        //s.flush();
        TransactionSynchronizationManager.unbindResource(sessionFactory);
        if (iDatabaseConnection != null) iDatabaseConnection.close();

    }

    //备份一张表
    private void backupOneTable() throws IOException, DataSetException {
        QueryDataSet ds = new QueryDataSet(iDatabaseConnection);
        ds.addTable(tableName);
        file = new File(this.getClass().getResource("/data/" + tableName + ".xml").getPath());
        Assert.assertNotNull(tableName+".xml配置文件不存在",file);
        FlatXmlDataSet.write(ds, new FileWriter(file));
    }

    /**通过表创建数据库
     * 在开发过程中，由于备份的表格存在于项目中，所以不会同步开发中的配置
     *
     * @return 返回IDataSet
     * @throws DatabaseUnitException
     * @throws SQLException
     */
    protected IDataSet createDataSet() throws DatabaseUnitException, SQLException {
        InputStream is = this.getClass().getResourceAsStream("/data/" + tableName + ".xml");
        Assert.assertNotNull("数据文件不存在", is);
        IDataSet ds = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(is)));
        DatabaseOperation.TRUNCATE_TABLE.execute(iDatabaseConnection, ds);
        DatabaseOperation.INSERT.execute(iDatabaseConnection, ds);
        return ds;
    }
}
