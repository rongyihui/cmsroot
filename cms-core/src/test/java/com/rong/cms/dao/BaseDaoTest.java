package com.rong.cms.dao;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
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
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.xml.sax.InputSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public abstract class BaseDaoTest {

    /**
     * 设置table名称
     */
    protected static File tempFile;
    protected static File tempDtdFile;
    protected static String tableTestName;
    protected static String tableName;

    @Resource
    private DataSource dataSource;
    @Resource
    private SessionFactory sessionFactory;
    private File file;
    private IDatabaseConnection iDatabaseConnection;

    @Before
    public void backup() throws DatabaseUnitException, IOException, SQLException {
        iDatabaseConnection = new DatabaseConnection(DataSourceUtils.getConnection(dataSource));
        //解决延迟加载
        Session s = sessionFactory.openSession();
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
        //1.备份表
        backupAllTable();
        //2.将测试数据写入数据库
        createDataSet(tableTestName);
    }

    @Test
    public abstract void add();

    @After
    public void resetTable() throws SQLException, DatabaseUnitException, IOException {
        //SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
        //Session s = holder.getSession();
        //3.将备份的表恢复到数据库
        createDataSet("");
        //s.flush();
        TransactionSynchronizationManager.unbindResource(sessionFactory);
        if (iDatabaseConnection != null) iDatabaseConnection.close();
        tempFile.deleteOnExit();
        tempDtdFile.deleteOnExit();

    }
    //备份所有的表
    private void backupAllTable() throws IOException, SQLException, DataSetException {
        IDataSet ids = iDatabaseConnection.createDataSet();
        QueryDataSet ds = new QueryDataSet(iDatabaseConnection);
        for(String tablen:ids.getTableNames()){
            if(!"sys_config".equals(tablen))
            ds.addTable(tablen);
        }
        tempFile = File.createTempFile("base","xml");
        tempDtdFile = File.createTempFile("base","dtd");
        FlatDtdDataSet.write(ds,new FileOutputStream(tempDtdFile));
        FlatXmlDataSet.write(ds, new FileOutputStream(tempFile));
    }

    //备份一张表
    private void backupOneTable(String name) throws IOException, DatabaseUnitException, SQLException {
        QueryDataSet ds = new QueryDataSet(iDatabaseConnection);
        ds.addTable(tableName);
        //将数据库的数据存到t_.xml中
        file = new File(this.getClass().getResource("/data/" + name + ".xml").getPath());
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
    protected IDataSet createDataSet(String name) throws DatabaseUnitException, SQLException, IOException {
        InputStream is =null;
        if (name==null||"".equals(name)){
            is = new FileInputStream(tempFile);
        }else{
            is = this.getClass().getResourceAsStream("/data/" + name + ".xml");
            Assert.assertNotNull("数据文件不存在", is);
        }
        IDataSet ds = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(is),new FlatDtdDataSet(new FileInputStream(tempDtdFile))));
        DatabaseOperation.TRUNCATE_TABLE.execute(iDatabaseConnection, ds);
        DatabaseOperation.INSERT.execute(iDatabaseConnection, ds);
        return ds;
    }
}
