## generate predicates
```shell
antlr -no-listener Enum.g4
```

## generate predicates2
```shell
antlr -no-listener Enum2.g4
```


## test cpp predicates
```shell
antlr PredCppStat.g4
javaca PredCppStat*.java
grun Pred ppStat stat -diagnostics
```
