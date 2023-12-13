package playdb.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author tomyli
 * @date 2023/12/13 enjoy
 */
class PlayDBTest {

    @Test
    public void testNormalSelect() {

        String sql = "select order_id from orders where cust_id = 'SDYT987645'";
        PlayDB dl = new PlayDB();
        String dbName = dl.getDBName(sql);
        System.out.println("sql:" + sql);
        System.out.println("db:" + dbName);
        Assertions.assertEquals("db1", dbName);
    }

    @Test
    public void testMultiCondition() {

        String sql = "select order_id from orders where cust_id = 'SDYT987645' and price > 200";
        PlayDB dl = new PlayDB();
        String dbName = dl.getDBName(sql);
        System.out.println("sql:" + sql);
        System.out.println("db:" + dbName);
        Assertions.assertEquals("db1", dbName);
    }

    @Test
    public void testNormalDb2() {

        String sql = "select order_id from orders where cust_id = 'BJHD987645' and price > 200";
        PlayDB dl = new PlayDB();
        String dbName = dl.getDBName(sql);
        System.out.println("sql:" + sql);
        System.out.println("db:" + dbName);
        Assertions.assertEquals("db2", dbName);

    }
}
