package play;

import java.util.List;

/**
 * 函数类型
 */
public interface FunctionType extends Type {

    Type getReturnType();

    List<Type> getParamTypes();

    boolean matchParameterTypes(List<Type> paramTypes);

}
