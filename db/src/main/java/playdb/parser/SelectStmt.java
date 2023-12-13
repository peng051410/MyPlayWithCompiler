package playdb.parser;

import java.util.List;

/**
 * 保存select查询语句的概要信息
 *
 * @author tomyli
 * @date 2023/12/13 enjoy
 */
public class SelectStmt {

    public String tableName;
    public List<WhereExpr> whereExprs;

    public SelectStmt(String tableName, List<WhereExpr> whereExprs) {
        this.tableName = tableName;
        this.whereExprs = whereExprs;
    }
}
