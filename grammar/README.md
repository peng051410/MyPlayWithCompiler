# Antlr
## Hello
`sh
antlr Hello.g4
`

## Compile
`sh
javac -classpath ../lib/*.jar *.java
`

## Generate file
`sh
grun Hello tokens -tokens hello.py
`

## Result
[@1,4:6='age',<Id>,1:4]

1. @1 是 Token 的流水编号
2. 4:6 是 Token 在字符流中的开始和结束位置
3. age 是文本值
4. Id 是其 Token 类别
5. 1:4 表示这个 Token 在源代码中位于第 1 行、第 4 列

## Chinese
### Result
[@5,14:15='如果',<If>,2:0]

## PlayScript
`sh
antlr -visitor PlayScript.g4
`

## Compile
`sh
javac -classpath ../lib/*.jar *.java
`

## Test Parser
`sh
cd ../
grun antlrtest.PlayScript expression -gui
`

end expression and press Ctrl+D

# Grun
## grun Hello r -tokens
Print out the token stream

## grun Hello r -tree
Print the parse tree in LISP form

## grun Hello r -gui
Display the parse tree in a dialog box

