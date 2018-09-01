MACHINE LibraryStrings
/* This Library Machine provides a convenient access to the ProB
   external functions for STRING manipulation
   Modified 5/5/2018 to include str2int,... from ClearSy
*/
ABSTRACT_CONSTANTS append, length, split, join, chars, codes,
                   str2int, int2str, strIsInt, strIsInStr, int2decStr, decStr2int,
                   hash
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
  codes = %(s).(s:STRING|STRING_CODES(s)) &

  /* convert a string to an integer; it is reversible */
  str2int: STRING --> INTEGER &
  str2int = %(s).(s:STRING|STRING_TO_INT(s)) &

  /* convert an integer to a string; it is reversible */
  int2str: INTEGER --> STRING &
  int2str = %(i).(i:INTEGER|INT_TO_STRING(i)) &

  /* check if the string represents an integer */
  strIsInt: STRING --> BOOL &
  strIsInt = %(s).(s:STRING|bool(STRING_IS_INT(s))) &

  /* check whether the second string occurs contiguously within the first string */
  strIsInStr: STRING * STRING --> BOOL &
  strIsInStr = %(x, y).(x:STRING & y:STRING|STRING_CONTAINS_STRING(x,y)) &

  /* convert an integer to a decimal string representation with the precision provided by the second argument */
  int2decStr: INTEGER * INTEGER --> STRING &
  int2decStr = %(i, p).(i:INTEGER & p:INTEGER|INT_TO_DEC_STRING(i,p)) &

  /* convert a decimal string to an integer with the precision provided by the second argument */
  decStr2int: STRING * INTEGER --> INTEGER &
  decStr2int = %(s, p).(s:STRING & p:INTEGER|DEC_STRING_TO_INT(s,p)) &

  /* obtain an unique integer code for a string */
  hash: STRING --> INTEGER &
  hash = %(s).(s:STRING|HASH(s)) 

DEFINITIONS
  "LibraryStrings.def";
  "LibraryHash.def"
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
  codes(" ") = [32];
  codes("abc") = [97,98,99];
  !(x,y).(x:{"abc","hello",""} & y:{"abc","hello",""}
      => codes(append(x,y)) = codes(x)^codes(y));
  {x| codes(x) = codes("abc") ^ codes("abc")} = {"abcabc"};
  {x| codes(x) = (codes("abc") ; succ) } = {"bcd"};
  {x| codes(x) = %i.(i:1..26|96+i)} = {"abcdefghijklmnopqrstuvwxyz"};

  INT_TO_STRING(1204) = "1204";
  !x.(x:-4..100 => STRING_TO_INT(INT_TO_STRING(x)) = x);

  str2int("0") = 0;
  str2int("150") = 150;
  str2int("-150") = -150;

  int2str(0) = "0";
  int2str(150) = "150";
  int2str(-150) = "-150";

  strIsInt("") = FALSE;
  strIsInt("0") = TRUE;
  strIsInt("-0") = TRUE;
  strIsInt("1.4") = FALSE;
  strIsInt("1.0") = FALSE;
  strIsInt("-15") = TRUE;
  strIsInt("a") = FALSE;
  strIsInt("a5b") = FALSE;
  strIsInt("-") = FALSE;

  strIsInStr("b0a","0a") = TRUE;
  strIsInStr("b0a","c") = FALSE;
  strIsInStr("b0a","") = TRUE;
  strIsInStr("","zer") = FALSE;
  strIsInStr("","") = TRUE;

  int2decStr(-156, 0) = "-156";
  int2decStr(769, 2) = "7.69";
  int2decStr(234, -5) = "23400000";
  int2decStr(3, 6) = "0.000003";

  decStr2int("-156", 0) = -156;
  decStr2int("769", 2) = 76900;
  decStr2int("-57.9", -1) = -6;
  decStr2int("44.9", -1) = 4;
  decStr2int("234", -5) = 0;
  decStr2int("3.03", 6) = 3030000;
  
  hash("") = 231899681;
  hash("a") = 118151707;
  hash("ab c") = 203925323;
  hash("15") = 164651487;
  hash("-44.9") = 268267279
  
END

