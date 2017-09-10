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
'channel'('get','type'('dotTupleType'(['intType']))).
'channel'('sets','type'('dotTupleType'(['intType']))).
'bindval'('MAIN','sharing'('closure'(['get']),'val_of'('GETSET','src_span'(3,8,3,14,31,6)),'agent_call'('src_span'(3,29,3,32,52,3),'GEN',['int'(0)]),'src_span'(3,15,3,28,38,13)),'src_span'(3,1,3,35,24,34)).
'bindval'('GETSET','prefix'('src_span'(5,10,5,13,69,3),['in'(_Val)],'get','prefix'('src_span'(5,21,5,25,80,4),['out'(_Val)],'sets','val_of'('GETSET','src_span'(5,33,5,39,92,6)),'src_span'(5,30,5,32,88,14)),'src_span'(5,18,5,20,76,26)),'src_span'(5,1,5,39,60,38)).
'agent'('GEN'(_X),'prefix'('src_span'(7,10,7,13,110,3),['out'(_X)],'get','agent_call'('src_span'(7,19,7,22,119,3),'GEN',['+'(_X,'int'(1))]),'src_span'(7,16,7,18,115,14)),'src_span'(7,10,7,27,110,17)).
'symbol'('get','get','src_span'(1,9,1,12,8,3),'Channel').
'symbol'('sets','sets','src_span'(1,13,1,17,12,4),'Channel').
'symbol'('MAIN','MAIN','src_span'(3,1,3,5,24,4),'Ident (Groundrep.)').
'symbol'('GETSET','GETSET','src_span'(5,1,5,7,60,6),'Ident (Groundrep.)').
'symbol'('Val','Val','src_span'(5,14,5,17,73,3),'Ident (Prolog Variable)').
'symbol'('GEN','GEN','src_span'(7,1,7,4,101,3),'Funktion or Process').
'symbol'('X','X','src_span'(7,5,7,6,105,1),'Ident (Prolog Variable)').