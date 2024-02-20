## test lexmagic
```shell
antlr Cymbol.g4
javac Cymbol*.java
grun Cymbol file -tokens -tree
```


### mode tags
```shell
antlr ModeTagsLexer.g4
antlr ModeTagsParser.g4
javac ModeTags*.java
grun ModeTags file -tokens
```

### xmlparser
```shell
antlr XMLLexer.g4
antlr XMLParser.g4
javac XML*.java
grun XML document -gui inputs/entity.xml 

grun XML document -ps /tmp/t.ps inputs/cat.xml 
```
