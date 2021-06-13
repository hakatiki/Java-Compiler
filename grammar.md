
Program     -> Class Program
            | Lambda
Class       -> "class" ClassName "{" Exprs "}" 

Exprs       -> Expr Exprs
            | Lambda

Expr        -> VarDec
            | If
            | While
            | Threaded
            | MethodCall
            | Copy

VarDec      -> Type VarName "=" Value //??? Do we check alr whether its the right type
            | ClassName VarName "=" "new" ClassName

Type        -> "Bool"
            | "Bool[]"
            | "Int"
            | "Int[]"

If          -> "if" "(" Cond ")" "{" Exprs "}" ("else" "{" Exprs "}")?

While       -> "while" "(" Cond ")" "{" Exprs "}"

Threaded    -> "@Threaded" "(" Int ")" "{" Exprs "}"

MethodCall  -> 