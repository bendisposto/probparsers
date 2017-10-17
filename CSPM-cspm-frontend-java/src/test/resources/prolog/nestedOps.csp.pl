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
'bindval'('t','+'('+'('int'(3),'int'(3)),'int'(1)),'src_span'(1,1,1,13,0,12)).
'bindval'('t2','bool_and'('bool_and'('true','true'),'true'),'src_span'(2,1,2,28,13,27)).
'symbol'('t','t','src_span'(1,1,1,2,0,1),'Ident (Groundrep.)').
'symbol'('t2','t2','src_span'(2,1,2,3,13,2),'Ident (Groundrep.)').