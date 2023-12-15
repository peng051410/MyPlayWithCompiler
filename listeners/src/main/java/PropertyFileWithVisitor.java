import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tomyli
 * @date 2023/12/15 enjoy
 */
public class PropertyFileWithVisitor extends PropertyFileBaseVisitor<Void> {

    Map<String, String> props = new LinkedHashMap<>();

    @Override
    public Void visitProp(PropertyFileParser.PropContext ctx) {
        String id = ctx.ID().getText(); // prop : ID '=' STRING '\n' ;
        String value = ctx.STRING().getText();
        props.put(id, value);
        return null;
    }

}
