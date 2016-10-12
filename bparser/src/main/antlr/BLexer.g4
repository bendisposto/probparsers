lexer grammar BLexer;

@header {
package files;
}

@lexer::members {
public boolean rulesGrammar = true;
}

channels {
  PRAGMA_CHANNEL
}


fragment DIGIT: ('0'..'9');
fragment LETTER: [a-zA-Z];

Number
  : ('1'..'9') DIGIT+
  | DIGIT
  ;

MACHINE: 'MACHINE' {rulesGrammar = false;};
RULES_MACHINE: 'RULES_MACHINE' {rulesGrammar}? ;
MODEL: 'MODEL';
SYSTEM: 'SYSTEM';
REFINEMENT: 'REFINEMENT';
IMPLEMENTATION: 'IMPLEMENTATION';
REFINES: 'REFINES';
END: 'END' ;
INITIALISATION: 'INITIALISATION';
OPERATIONS: 'OPERATIONS';
LOCAL_OPERATIONS: 'LOCAL_OPERATIONS';
VALUES: 'VALUES';


// clauses
DEFINITIONS: 'DEFINITIONS';
CONSTANTS: 'CONSTANTS';
ABSTRACT_CONSTANTS: 'ABSTRACT_CONSTANTS';
CONCRETE_CONSTANTS: 'CONCRETE_CONSTANTS';
VARIABLES: 'VARIABLES';
ABSTRACT_VARIABLES: 'ABSTRACT_VARIABLES';
CONCRETE_VARIABLES: 'CONCRETE_VARIABLES';

// predicate clauses
CONSTRAINTS: 'CONSTRAINTS';
PROPERTIES: 'PROPERTIES';
INVARIANT: 'INVARIANT';
ASSERTIONS: 'ASSERTIONS';
// reference clauses
SEES: 'SEES';
USES: 'USES';
INCLUDES: 'INCLUDES';
EXTENDS: 'EXTENDS';
IMPORTS: 'IMPORTS';
PROMOTES: 'PROMOTES';

SETS: 'SETS';

BEGIN: 'BEGIN';
SKIP_SUB: 'skip';
IF: 'IF';
ELSIF: 'ELSIF';
THEN: 'THEN';
ELSE: 'ELSE';
PRE: 'PRE';
ASSERT: 'ASSERT';
CHOICE: 'CHOICE';
SUBSTITUTION_OR: 'OR';
SELECT: 'SELECT';
WHEN: 'WHEN';
CASE: 'CASE';
OF: 'OF';
EITHER: 'EITHER';
ANY: 'ANY';
WHERE: 'WHERE';
LET: 'LET';
BE: 'BE';
IN: 'IN';
VAR: 'VAR';
WHILE: 'WHILE';
VARIANT: 'VARIANT';
DO: 'DO';

FOR_ANY: '!' | '\u2200';
EXITS: '#' | '\u2203';
LAMBDA: '%' | '\u03bb';
OUTPUT_PRAMETERS: '<--' | '\u2190';
DOUBLE_EQUAL: EQUAL EQUAL;
ASSIGN: ':=';
DOUBLE_COLON: '::' | ':' '\u2208' ;  /* becomes_element_of */
EQUIVALENCE: '<=>' | '\u21d4';
IMPLIES: EQUAL GREATER | '\u21d2';
LEFT_BRACE: '{';
RIGHT_BRACE: '}';
LEFT_PAR: '(';
RIGHT_PAR: ')';
LEFT_BRACKET: '[';
RIGHT_BRACKET: ']';
MINUS: '-' | '\u2212';
PLUS: '+' | '\u002b';
SINGLE_QUOTE: '\'';
TILDE: '~' | '\u223c';
DOT: '.';
SEMICOLON: ';';
VERTICAL_BAR: '|';
DOUBLE_VERTICAL_BAR: '||' | '\u2225';
COMMA: ',';
REC: 'rec';
STRUCT: 'struct';



//predicate prefix operators
NOT: 'not' | '\u00ac';
BOOl_CAST: 'bool';

// predicate infix operators
AND: '&';
OR: 'or';

//expression_p125
SET_RELATION: '<->' | '\u2194';
PARTIAL_FUNCTION: '+->';
TOTAL_FUNCTION: '-->';
TOTAL_INJECTION: '>->';
PARTIAL_INJECTION: '>+>';
TOTAL_SURJECTION: '-->>';
PARTIAL_SURJECTION: '+->>';
TOTAL_BIJECTION: '>->>';
PARTIAL_BIJECTION: '>+>>';

// Extensions
TOTAL_RELATION: '<<->';

// expression infix operators P160
OVERWRITE_RELATION: '<+';
DIRECT_PRODUCT: '<>'| '\u2297';//?
CONCAT: '^';
DOMAIN_RESTRICTION: '<|';
DOMAIN_SUBSTRACTION: '<<|';
RANGE_RESTRICTION:  '|>';
RANGE_SUBSTRATION:  '|>>';
INSERT_FRONT: '->'| '\u21fe';
INSERT_TAIL:  '<-' | '\u21fd';
GENERALIZED_UNION: 'UNION' | '\u22c3';
GENERALIZED_INTER: 'inter';

INTERSECTION: '/\\' | '\u2229';
RESTRICT_FRONT: '/|\\' | '\u2191';
RESTRICT_TAIL: '\\|/' | '\u2193';
MAPLET: '|->' | '\u21a6';
UNION: '\\/' | '\u222a';


DOLLAR_ZERO: '$0';

//expression infix operators

MULT: '*';
DIVIDE: '/';

MOD: 'mod';
POWER_OF: '**';
INTERVAL: '..' | '\u2025';

// predicate infix opertors
EQUAL: '=' | '\u003d';
NOT_EQUAL: '/=' | '\u2260';
ELEMENT_OF: ':' | '\u2208';
INCLUSION: '<:' | '\u2286';
STRICT_INCLUSION: '<<:' | '\u2282';
NON_INCLUSION: '/<:' | '\u2288';
STRICT_NON_INCLUSION: '/<<:' | '\u2284';
NOT_BELONGING: '/:' | '\u2209';
LESS: '<' | '\u003c';
LESS_EQUAL: LESS EQUAL | '\u2264';
GREATER: '>' | '\u003e';
GREATER_EQUAL: GREATER EQUAL | '\u2265';


// expression prefix operators with one parameter

BTREE: 'btree';
CARD: 'card';
CLOSURE: 'closure';
CLOSURE1: 'closure1';
CONC: 'conc';
DOM: 'dom';
FIRST: 'first';
FNC: 'fnc';
FRONT: 'front';
ID: 'id';
INFIX: 'infix';
ISEQ: 'iseq';
ISEQ1: 'iseq1'; // add 'iseq'0x8321 ?
LAST: 'last';
LEFT: 'left';
MAX: 'max';
MIN: 'min';
MIRROR: 'mirror';
PERM: 'perm';
POSTFIX: 'postfix';
POW: 'POW';
PREFIX: 'prefix';
RAN: 'ran';
REL: 'rel';
REV: 'rev';
RIGHT: 'right';
SEQ: 'seq';
SEQ1: 'seq1'; // add | 'seq'0x8321 ?
SIZET: 'sizet';
SIZE: 'size';
SONS: 'sons';
TAIL: 'tail';
TOP: 'top';
TREE: 'tree';

// expression prefix operators with two parameters
CONST: 'const';
FATHER: 'father';
PRJ1: 'prj1'; // add | 'iseq'0x8321 ?
PRJ2: 'prj2';
RANK: 'rank';
SUBTREE: 'subtree';
ARITY: 'arity';
ITERATE: 'iterate';

// expression prefix operators with three parameters
SON: 'son';

// expression prefix operators with a variable list of parameters
BIN: 'bin';

//keyword operators
NATURAL: 'NATURAL' | '\u2115';
NATURAL1: 'NATURAL1' | '\u2115' '\u0031' | '\u2115' '\u2081';
NAT: 'NAT';
NAT1: 'NAT';
INT: 'INT';
INTEGER: 'INTEGER' | '\u2124';
BOOL: 'BOOL';
PRED: 'pred';
SUCC: 'succ';
// other


EXPRESSION_KEYWORD: '#EXPRESSION';
PREDICATE_KEYWORD: '#PREDICATE';
SUBSTITUTION_KEYWORD: '#SUBSTITUTION';
FORMULA_KEYWORD: '#FORMULA';


Identifier
  : LETTER (LETTER | DIGIT | '_')*
  ;

NOT_REACHABLE: 'NOT_REACHABLE';

//PRAGMA_START: '/*@' -> pushMode(PRAGMAS), skip ;

COMMENT
  :   '/*' (~[@] .*?)? '*/' -> skip
  ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;

WS: [ \t\r\n]+ -> skip;


//---------------------- PRAGMA -------------------------------
//mode PRAGMAS;

//PRAGMA_STOP: '*/' -> popMode, skip  ;

//PRAGMA_WS: [ \t\r\n]+ -> skip;

//PRAGMA_UNIT: 'unit' -> skip;
//PRAGMA_LABEL: 'label' -> channel(PRAGMA_CHANNEL);
//PRAGMA_ID_OR_STRING: LETTER (LETTER | DIGIT | '_')* -> channel(PRAGMA_CHANNEL) ;
