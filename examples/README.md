# CSV example
Single field hold multiple values.

## Generate lexer and parser
```shell
antlr CSV.g4 -package example
```

## Run tokens
```shell
grun example.CSV file -tokens data.csv
```

## Run tree
```shell
grun example.CSV file -tree data.csv
```
# JSON example
## Generate lexer and parser
```shell
antlr JSON.g4 -package example
```

## Compile
```shell
javaca JSON*.java 
```

## Run tokens
```shell
grun example.JSON json -tokens
```

## Run tree
```shell
grun example.JSON json -tree
```

# DOT example
## Generate lexer and parser
```shell
antlr DOT.g4 -package example
```

## Compile
```shell
javaca DOT*.java 
```

 ## GUI
```shell
grun example.DOT graph -gui t.dot
```

# Cymbol example
## Generate lexer and parser
```shell
antlr Cymbol.g4 -package example
```

## Compile
```shell
javaca Cymbol*.java 
```

## Run tokens
```shell
grun example.Cymbol file -tokens t.cymbol
```

## Run tree
```shell
grun example.Cymbol file -tree t.cymbol
```

# R example
## Generate lexer and parser
```shell
antlr R.g4 -package example
```

## Compile
```shell
javaca R*.java 
```

## Run GUI
```shell
grun example.R prog -gui t.R
```
