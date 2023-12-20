# Refactor sample
## Generate lexer and parser
```shell
antlr PropertyFile.g4 
```

## Gererate visitor
```shell
antlr -visitor PropertyFile.g4 
```

# Listener sample
## Generate lexer and parser
```shell
antlr Expr.g4 
```


# Graph
```dot

digraph G {
  ranksep=.25;
  edge [arrowsize=.5]
  node [shape=circle, fontname="ArialNarrow",
        fontsize=12, fixedsize=true, height=.45];
  main; fact; a; b; c; d; e; 
  main -> fact;
  main -> a;
  fact -> print;
  fact -> fact;
  a -> b;
  a -> c;
  a -> d;
  b -> c;
  c -> b;
}
```
