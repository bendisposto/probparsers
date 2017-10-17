:- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5.
:- dynamic module/4.
'parserVersionStr'('0.6.1.1').
'parseResult'('ok','',0,0,0).
:- dynamic channel/2, bindval/3, agent/3.
:- dynamic agent_curry/3, symbol/4.
:- dynamic dataTypeDef/2, subTypeDef/2, nameType/2.
:- dynamic cspTransparent/1.
:- dynamic cspPrint/1.
:- dynamic pragma/1.
:- dynamic comment/2.
:- dynamic assertBool/1, assertRef/5, assertTauPrio/6.
:- dynamic assertModelCheckExt/4, assertModelCheck/3.
:- dynamic assertLtl/4, assertCtl/4.
'parserVersionNum'([0,11,0,1]).
'parserVersionStr'('CSPM-Frontent-0.11.0.1').
'bindval'('res','+'('+'('val_of'('a','src_span'(1,7,1,8,6,1)),'val_of'('b','src_span'(1,11,1,12,10,1))),'val_of'('c','src_span'(1,15,1,16,14,1))),'src_span'(1,1,1,16,0,15)).
'bindval'('a','int'(10),'src_span'(2,1,2,5,16,4)).
'bindval'('b','int'(20),'src_span'(3,1,3,5,21,4)).
'bindval'('c','+'('val_of'('a','src_span'(4,4,4,5,29,1)),'val_of'('b','src_span'(4,8,4,9,33,1))),'src_span'(4,1,4,9,26,8)).
'bindval'('l','listExp'('rangeEnum'(['int'(1),'int'(2),'int'(3),'int'(4)])),'src_span'(5,1,5,14,35,13)).
'bindval'('t','tupleExp'(['int'(1),'int'(2),'int'(3),'val_of'('l','src_span'(6,12,6,13,60,1))]),'src_span'(6,1,6,14,49,13)).
'agent'('f'(_x2),'int'(10),'src_span'(7,6,7,8,68,2)).
'bindval'('g','lambda'([_x3],'int'(10)),'src_span'(8,1,8,12,71,11)).
'bindval'('tuplePat'(['x','y','z']),'tupleExp'(['*'('int'(1),'int'(1111)),'*'('int'(2),'int'(1111)),'*'('int'(3),'int'(1111))]),'src_span'(10,1,10,33,84,32)).
'bindval'('tuplePat'(['e1','e2','e3','e4']),'val_of'('t','src_span'(11,17,11,18,133,1)),'src_span'(11,1,11,18,117,17)).
'bindval'('listPat'(['o1','o2','o3','o4']),'val_of'('l','src_span'(13,18,13,19,153,1)),'src_span'(13,1,13,19,136,18)).
'symbol'('res','res','src_span'(1,1,1,4,0,3),'Ident (Groundrep.)').
'symbol'('a','a','src_span'(2,1,2,2,16,1),'Ident (Groundrep.)').
'symbol'('b','b','src_span'(3,1,3,2,21,1),'Ident (Groundrep.)').
'symbol'('c','c','src_span'(4,1,4,2,26,1),'Ident (Groundrep.)').
'symbol'('l','l','src_span'(5,1,5,2,35,1),'Ident (Groundrep.)').
'symbol'('t','t','src_span'(6,1,6,2,49,1),'Ident (Groundrep.)').
'symbol'('f','f','src_span'(7,1,7,2,63,1),'Funktion or Process').
'symbol'('x2','x','src_span'(7,3,7,4,65,1),'Ident (Prolog Variable)').
'symbol'('g','g','src_span'(8,1,8,2,71,1),'Ident (Groundrep.)').
'symbol'('x3','x','src_span'(8,6,8,7,76,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(10,2,10,3,85,1),'Ident (Groundrep.)').
'symbol'('y','y','src_span'(10,4,10,5,87,1),'Ident (Groundrep.)').
'symbol'('z','z','src_span'(10,6,10,7,89,1),'Ident (Groundrep.)').
'symbol'('e1','e1','src_span'(11,2,11,4,118,2),'Ident (Groundrep.)').
'symbol'('e2','e2','src_span'(11,5,11,7,121,2),'Ident (Groundrep.)').
'symbol'('e3','e3','src_span'(11,8,11,10,124,2),'Ident (Groundrep.)').
'symbol'('e4','e4','src_span'(11,11,11,13,127,2),'Ident (Groundrep.)').
'symbol'('o1','o1','src_span'(13,2,13,4,137,2),'Ident (Groundrep.)').
'symbol'('o2','o2','src_span'(13,5,13,7,140,2),'Ident (Groundrep.)').
'symbol'('o3','o3','src_span'(13,8,13,10,143,2),'Ident (Groundrep.)').
'symbol'('o4','o4','src_span'(13,11,13,13,146,2),'Ident (Groundrep.)').