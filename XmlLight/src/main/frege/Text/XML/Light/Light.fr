{--
    A lightweight XML parsing, filtering and generating library.
    
    This module reexports functions from:
    
    * "Text.XML.Light.Types"
    
    * "Text.XML.Light.Proc" [not yet ported to Frege]
    
    * "Text.XML.Light.Input" [not yet ported to Frege]
    
    * "Text.XML.Light.Output"
    
    [Module]     Text.XML.Light.Light
    [Copyright]  (c) Galois, Inc. 2007
    [License]    BSD3
    
    [Maintainer] Iavor S. Diatchki <diatchki@galois.com>
    [Stability]  provisional
-}

module Text.XML.Light.Light where

import Text.XML.Light.Types public
-- import Text.XML.Light.Proc public
-- import Text.XML.Light.Input public
import Text.XML.Light.Output public

--- Add an attribute to an element.
add_attr :: Attr -> Element -> Element
add_attr a e = add_attrs [a] e

--- Add some attributes to an element.
add_attrs :: [Attr] -> Element -> Element
add_attrs as e = e.{ elAttribs <- (as ++) }

--- Create an unqualified name.
unqual :: String -> QName
unqual x = blank_name.{ qName = x }

--- The "smart element constructor" needs FlexibleInstances, which is not
--- available in Frege (issue #5). So we offer static functions to convert
--- stuff to 'Element'.
--- NOTE: Not every case supported by the original library is covered.

--- Create node with unqualified name
unode :: (QName -> t -> Element) -> String -> t -> Element
unode fNode = fNode . unqual

unodeElement :: String -> Element -> Element
unodeElement n e = unodeElements n [e]

unodeElements :: String -> [Element] -> Element
unodeElements = unode (\n es -> node n ([]::[Attr], map Elem es))

unodeAttr :: String -> Attr -> Element
unodeAttr n a = unodeAttrs n [a]

unodeAttrs :: String -> [Attr] -> Element
unodeAttrs = unode (\n as -> node n (as, []::[Content]))

node :: QName -> ([Attr],[Content]) -> Element
node n (attrs,cont) = blank_element.{ elName     = n
                                    , elAttribs  = attrs
                                    , elContent  = cont
                                    }

-- - A smart element constructor which uses the type of its argument
-- - to determine what sort of element to make.
-- class Node t where
--   node :: QName -> t -> Element
-- 
-- instance Node ([Attr],[Content]) where
--   node n (attrs,cont) = blank_element { elName     = n
--                                       , elAttribs  = attrs
--                                       , elContent  = cont
--                                       }
-- 
-- instance Node [Attr]             where node n as   = node n (as,[]::[Content])
-- instance Node Attr               where node n a    = node n [a]
-- instance Node ()                 where node n ()   = node n ([]::[Attr])
-- 
-- instance Node [Content]          where node n cs     = node n ([]::[Attr],cs)
-- instance Node Content            where node n c      = node n [c]
-- instance Node ([Attr],Content)   where node n (as,c) = node n (as,[c])
-- instance Node (Attr,Content)     where node n (a,c)  = node n ([a],[c])
-- 
-- instance Node ([Attr],[Element]) where
--   node n (as,cs) = node n (as,map Elem cs)
-- 
-- instance Node ([Attr],Element)   where node n (as,c) = node n (as,[c])
-- instance Node (Attr,Element)     where node n (a,c)  = node n ([a],c)
-- instance Node ([Element])        where node n es     = node n ([]::[Attr],es)
-- instance Node (Element)          where node n e      = node n [e]
-- 
-- instance Node ([Attr],[CData])   where
--   node n (as,cs) = node n (as,map Text cs)
-- 
-- instance Node ([Attr],CData)     where node n (as,c) = node n (as,[c])
-- instance Node (Attr,CData)       where node n (a,c)  = node n ([a],c)
-- instance Node [CData]            where node n es     = node n ([]::[Attr],es)
-- instance Node CData              where node n e      = node n [e]
-- 
-- instance Node ([Attr],String)    where
--   node n (as,t) = node n (as,blank_cdata { cdData = t })
-- 
-- instance Node (Attr,String)      where node n (a,t)  = node n ([a],t)
-- instance Node [Char]             where node n t      = node n ([]::[Attr],t)
