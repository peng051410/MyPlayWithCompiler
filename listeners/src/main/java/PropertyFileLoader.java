import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tomyli
 * @date 2023/12/15 enjoy
 */
public class PropertyFileLoader extends PropertyFileBaseListener {

    Map<String, String> props = new LinkedHashMap<>();

    @Override
    public void exitProp(PropertyFileParser.PropContext ctx) {
        String id = ctx.ID().getText(); // prop : ID '=' STRING '\n' ;
        String value = ctx.STRING().getText();
        props.put(id, value);
    }
}
