package report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 做报表数据的存储
 */
@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TabularData {

    //数据，字段名和值，值可以是向量（List<Object>）或标量。
    private final Map<String, Object> fieldValues = new HashMap<>();

    //行数
    private int numRows = 0;

    public int getNumRows() {
        return numRows;
    }

    @SuppressWarnings("unchecked")
    public Object getFieldValue(String fieldName, int rowIndex) {
        Object rtn = fieldValues.get(fieldName);
        if (rtn instanceof List<?>) {
            rtn = ((List<Object>) rtn).get(rowIndex);
        }
        return rtn;
    }

    public Object getField(String fieldName) {
        return fieldValues.get(fieldName);
    }

    public boolean hasField(String fieldName) {
        return fieldValues.containsKey(fieldName);
    }

    /**
     * 添加新列。
     *
     * @param fieldName 字段名称，有可能是公式。
     * @param value     这一列的数据。
     */
    public void setField(String fieldName, Object value) {
        fieldValues.put(fieldName, value);
    }

    //创建一个例子报表
    public static TabularData sampleData() {
        TabularData data = new TabularData();

        data.numRows = 5;

        List<Object> col1 = new ArrayList<>();
        col1.add("电话销售部");
        col1.add("现场销售部");
        col1.add("电子商务部");
        col1.add("渠道销售部");
        col1.add("微商销售部");
        data.fieldValues.put("dept", col1);

        List<Object> col2 = new ArrayList<>();
        col2.add(10);
        col2.add(20);
        col2.add(15);
        col2.add(20);
        col2.add(12);
        data.fieldValues.put("num_person", col2);

        List<Object> col3 = new ArrayList<>();
        col3.add(2345.0);
        col3.add(5860.0);
        col3.add(3045.0);
        col3.add(5500.0);
        col3.add(3624.0);
        data.fieldValues.put("sales_amount", col3);

        return data;
    }
}
