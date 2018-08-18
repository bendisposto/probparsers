MACHINE LibraryStrings
/* This Library Machine provides a convenient access to the ProB
   external functions for STRING manipulation */
ABSTRACT_CONSTANTS append, length, split, join, chars, codes
PROPERTIES
  /* compute the length of a string */
  length: STRING --> INTEGER &
  length = %x.(x:STRING|STRING_LENGTH(x)) &
  
  /* concatenate two strings; is reversible and can enumerate if only result value known */
  append: STRING * STRING --> STRING &
  append = %(x,y).(x:STRING & y:STRING|STRING_APPEND(x,y)) &
  
  /* split a string according to a delimiter string into a sequence of strings */
  split: STRING * STRING --> (INTEGER<->STRING) & 
  split = %(x,y).(x:STRING & y:STRING|STRING_SPLIT(x,y)) &
  
  join: (INTEGER<->STRING) * STRING <-> STRING & 
  join = %(x,y).(x:(INTEGER<->STRING) & y:STRING|STRING_JOIN(x,y)) &
  
  /* obtain the characters of a string as a B sequence of strings of length 1 */
  chars: STRING --> (INTEGER <-> STRING) &
  chars = %(s).(s:STRING|STRING_CHARS(s)) &
  
  /* obtain the characters of a string as a B sequence of Ascii codes; it is reversible */
  codes: STRING --> (INTEGER <-> INTEGER) &
  codes = %(s).(s:STRING|STRING_CODES(s))
DEFINITIONS
  "LibraryStrings.def"
ASSERTIONS
  length("abc") = 3;
  length("") = 0;
  length("hello") = 5;
  !(x,y).(x:{"abc","hello",""} & y:{"abc","hello",""}
      => length(append(x,y)) = length(x)+length(y)); /* this did find a counter-example in atom_length of
   SICStus: dif(X,1), atom_length(a,X) finds a solution in SICStus 4.2.0 & 4.2.1*/
  
  append("abc","abc") = "abcabc";
  append("","abc") = "abc";
  append("abc","") = "abc";
  {x|x:{"abc","abcabc","hello"} & #(prefx).(append(prefx,"c")=x)} = {"abcabc","abc"};
  {x|x/="" & #y.(append(x,y)="abc" & y/="")} = {"a","ab"}; /* compute true prefixes */
  {x|x/="" & #y.(append(y,x)="abc" & y/="")} = {"c","bc"}; /* compute true postfixes */
  {y|y/="" & #(x,z).(append(x,append(y,z))="abc" & length(x)+length(z)>0)} = 
    /* compute true substrings */
    {"a","ab","b","bc","c"};
  {y|y/="" & #(x,z).(append(append(x,y),z)="abc" & length(x)+length(z)>0)} = 
    /* compute true substrings */
    {"a","ab","b","bc","c"};
  
  split("filename.ext",".") = ["filename","ext"];
  split("filename.ext","/") = ["filename.ext"];
  split("/usr/local/lib","/") = ["","usr","local","lib"];
  split("/","/") = ["",""];
  split("abcabc","bc") = ["a","a",""];
  split(";",";") = ["",""];
  {x|split(x,";") = ["a","b","c"]} = {"a;b;c"};
  {x|split(x,";") = ["a"]} = {"a"};
  {x|split(x,";") = ["a",""]} = {"a;"};
  {x|split(x,";") = ["",""]} = {";"};
  join(["a","b","c"],";") = "a;b;c";
  !y.(y:{"a;b;", ";", "abc;;;efg", "abc"} => join(split(y,";"),";") = y);
  join(["a_b_c","d"],"_") = "a_b_c_d";
  {r|split(r,"_") = ["a","_","c"]} = {};
  {r|split(r,"_") = ["a","c"]} = {"a_c"};

  chars("") = <>;
  chars("abc") = ["a","b","c"];
  !(x,y).(x:{"abc","hello",""} & y:{"abc","hello",""}
      => chars(append(x,y)) = chars(x)^chars(y));
  /* find strings with b as character: */
  {x|x:{"abc","abcabc","hello"} & "b" : ran(chars(x))} = {"abc", "abcabc"};
  /* now find strings which have the same character set */
  {x|x:{"abc","abcabc","hello"} & 
    #y.(y:{"abc","abcabc","hello"} & ran(chars(x))=ran(chars(y)) & x/=y)} = {"abc", "abcabc"};
  /* now find permutations */
  {x|x:{"abc","hello","cba",""} & #(y,p).(y:{"abc","hello","cba",""} & p:perm(1..size(chars(x))) &  x/=y & length(x)=length(y) & /* a bit slower without this condition */
  (p;chars(x)) = chars(y))} = {"abc","cba"};
  
  ran({a,b,y| chars(y) = [a]^[b] & a:{"a","b"} & b:{"d","e"}}) = {"ad","ae","bd","be"} &
  {x|chars(x) = %i.(i:1..10|"a_")} = {"a_a_a_a_a_a_a_a_a_a_"} &

  card({x,y,z,v| x: {"abc","abcabcd","filename.ext","/usr/local/lib"} & 
              y:ran(chars(x)) & z:ran(chars(x)) & y/=z & 
              v:ran(chars(x)) & y/=v & z/=v &
              length(x)+length(y)+length(z)+length(v)=6 }) = 6;
  codes("") = <>;
 /* codes(" ") = [32]; the Java parser currently swallows whitespace within strings */
  codes("abc") = [97,98,99];
  !(x,y).(x:{"abc","hello",""} & y:{"abc","hello",""}
      => codes(append(x,y)) = codes(x)^codes(y));
  {x| codes(x) = codes("abc") ^ codes("abc")} = {"abcabc"};
  {x| codes(x) = (codes("abc") ; succ) } = {"bcd"};
  {x| codes(x) = %i.(i:1..26|96+i)} = {"abcdefghijklmnopqrstuvwxyz"};
  
  INT_TO_STRING(1204) = "1204";
  !x.(x:-4..100 => STRING_TO_INT(INT_TO_STRING(x)) = x)
END

