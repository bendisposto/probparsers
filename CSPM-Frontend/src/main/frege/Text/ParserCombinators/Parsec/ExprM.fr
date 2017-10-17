-----------------------------------------------------------------------------
-- |
-- Module      :  Text.ParserCombinators.Parsec.ExprM
-- Stability   :  experimental
-- Portability :  portable
--
-- This module is a variant of Text.ParserCombinators.Parsec.Expr
-- A helper module to parse \"expressions\".
-- Builds a parser given a table of operators and associativities.
-- 
-- In this module, the application of an operator is a monadic action.
-- This is, i.e. usefull if one wants to construct an AST an attach a unique
-- label to every node.
-----------------------------------------------------------------------------

module Text.ParserCombinators.Parsec.ExprM

where

import frege.Prelude hiding (try, pzero, <|>)
import Text.ParserCombinators.Parsec.Prim
import Text.ParserCombinators.Parsec.Combinator

-----------------------------------------------------------
-- Assoc and OperatorTable
-----------------------------------------------------------
data Assoc                = AssocNone 
                          | AssocLeft
                          | AssocRight
                        
data Operator t st a     
    = Infix (GenParser t st (a -> a -> GenParser t st a)) Assoc
    | Prefix (GenParser t st (a -> GenParser t st a))
    | Postfix (GenParser t st (a -> GenParser t st a))

infixM :: 
       (GenParser t st (a -> a -> GenParser t st a))
    -> Assoc 
    -> Operator t st a     
infixM = Infix

prefixM :: (GenParser t st (a -> GenParser t st a)) -> Operator t st a
prefixM = Prefix

postfixM :: (GenParser t st (a -> GenParser t st a)) -> Operator t st a
postfixM = Postfix

type OperatorTable t st a = [[Operator t st a]]



-----------------------------------------------------------
-- Convert an OperatorTable and basic term parser into
-- a full fledged expression parser
-----------------------------------------------------------
buildExpressionParser :: OperatorTable tok st a -> GenParser tok st a -> GenParser tok st a
buildExpressionParser operators simpleExpr
    = fold (makeParser) simpleExpr operators
    where
      makeParser term ops
        = let (rassoc,lassoc,nassoc
               ,prefix,postfix)      = foldr splitOp ([],[],[],[],[]) ops
              
              rassocOp   = choice rassoc
              lassocOp   = choice lassoc
              nassocOp   = choice nassoc
              prefixOp   = choice prefix  <?> ""
              postfixOp  = choice postfix <?> ""
              
              ambigious assoc op
                = try ( op >> fail ("ambiguous use of a " ++ assoc 
                                                 ++ " associative operator")
                      )
              
              ambigiousRight    = ambigious "right" rassocOp
              ambigiousLeft     = ambigious "left" lassocOp
              ambigiousNon      = ambigious "non" nassocOp 
              
              termP      = do{ pre  <- prefixP
                             ; x    <- term     
                             ; post <- postfixP
                             ; pre x >>= post
                             }
              
              postfixP   = postfixOp <|> return return
              
              prefixP    = prefixOp <|> return return
                                         
              rassocP x  = do{ f <- rassocOp
                             ; y  <- do{ z <- termP; rassocP1 z }
                             ; f x y
                             }
                           <|> ambigiousLeft
                           <|> ambigiousNon
                           -- <|> return x
                           
              rassocP1 x = rassocP x  <|> return x                           
                           
              lassocP x  = do{ f <- lassocOp
                             ; y <- termP
                             ; f x y >>= lassocP1
                             }
                           <|> ambigiousRight
                           <|> ambigiousNon
                           -- <|> return x
                           
              lassocP1 x = lassocP x <|> return x                           
                           
              nassocP x  = do{ f <- nassocOp
                             ; y <- termP
                             ;    ambigiousRight
                              <|> ambigiousLeft
                              <|> ambigiousNon
                              <|> f x y
                             }                                                          
                           -- <|> return x                                                      
                           
           in  do{ x <- termP
                 ; rassocP x <|> lassocP  x <|> nassocP x <|> return x
                   <?> "operator"
                 }
                

      splitOp (Infix op assoc) (rassoc,lassoc,nassoc,prefix,postfix)
        = case assoc of
            AssocNone  -> (rassoc,lassoc,op:nassoc,prefix,postfix)
            AssocLeft  -> (rassoc,op:lassoc,nassoc,prefix,postfix)
            AssocRight -> (op:rassoc,lassoc,nassoc,prefix,postfix)
            
      splitOp (Prefix op) (rassoc,lassoc,nassoc,prefix,postfix)
        = (rassoc,lassoc,nassoc,op:prefix,postfix)
        
      splitOp (Postfix op) (rassoc,lassoc,nassoc,prefix,postfix)
        = (rassoc,lassoc,nassoc,prefix,op:postfix)
      
