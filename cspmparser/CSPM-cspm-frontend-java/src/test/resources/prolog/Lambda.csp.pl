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
'bindval'('f2','lambda'([_x,_y,_z],'+'('+'(_x,_y),_z)),'src_span'(3,1,3,21,32,20)).
'comment'('lineComment'('-- test for Lambda-expressions'),'src_position'(1,1,0,30)).
'symbol'('f2','f2','src_span'(3,1,3,3,32,2),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(3,8,3,9,39,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(3,10,3,11,41,1),'Ident (Prolog Variable)').
'symbol'('z','z','src_span'(3,12,3,13,43,1),'Ident (Prolog Variable)').