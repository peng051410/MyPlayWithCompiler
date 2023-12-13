package playdb.parser;

/**
 * 保存where条件的概要信息
 *
 * @author tomyli
 * @date 2023/12/13 enjoy
 */
public class WhereExpr {

    public String columnName = null;
    public String op = "=";
    public String value = null;

    public WhereExpr(String columnName, String op, String value) {
        this.columnName = columnName;
        this.op = op;
        this.value = value;
    }
}
