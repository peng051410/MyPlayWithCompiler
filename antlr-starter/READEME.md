# Study
## run ArrayInit.g4
### result
ArrayInit.interp
ArrayInit.tokens
ArrayInitBaseListener.java
ArrayInitLexer.interp
ArrayInitLexer.java
ArrayInitLexer.tokens
ArrayInitListener.java
ArrayInitParser.java

#### ArrayInitParser.java
File contains the parser class definition.

#### ArrayInitLexer.java
contains the lexer class definition.

#### ArrayInit.tokens
Assigns token types and store the value

#### ArrayInitListener.java
Interface describe the callbacks we can implement.

#### ArrayInitBaseListener
Default implementation of ArrayInitListener

## Compile
```shell
         javac -classpath ~/Nustore/lib/*.jar *.java
```

#### Test

##### TestToken
```shell
grun ArrayInit init -tokens
```

##### TestTree
```shell
grun ArrayInit init -tree
```

##### TestGUI
```shell
grun ArrayInit init -gui
```

## tour
### Run XML lexer
```shell
grun XML tokens -tokens t.xml
```
