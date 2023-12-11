/**
 * Translate short array into Unicode string.
 *
 * @author tomyli
 * @date 2023/12/11 enjoy
 */
public class ShortToUnicodeString extends ArrayInitBaseListener {

    // Translate { to "
    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        System.out.print('"');
    }

    // Translate } to "
    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        System.out.print('"');
    }

    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {
        //ctx.INT().getText() returns the int value as a string
        Integer value = Integer.valueOf(ctx.INT().getText());
        System.out.printf("\\u%04x", value);
    }
}
