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
'bindval'('MyInt','setExp'('rangeClosed'('int'(0),'int'(99))),'src_span'(5,1,5,16,105,15)).
'channel'('gen','type'('dotTupleType'(['val_of'('MyInt','src_span'(6,13,6,18,145,5))]))).
'channel'('stop','type'('dotTupleType'(['val_of'('MyInt','src_span'(7,14,7,19,164,5))]))).
'bindval'('MAIN','/\x5c\'('agent_call'('src_span'(9,8,9,11,178,3),'GEN',['int'(0)]),'[>'('agent_call'('src_span'(9,19,9,22,189,3),'GEN',['int'(10)]),'agent_call'('src_span'(9,30,9,33,200,3),'GEN',['int'(20)]),'src_span_operator'('no_loc_info_available','src_span'(9,27,9,29,197,2))),'src_span_operator'('no_loc_info_available','src_span'(9,15,9,17,185,2))),'src_span'(9,1,9,38,171,37)).
'agent'('GEN'(_x),'[]'('&'('<'(_x,'int'(99)),'prefix'('src_span'(11,18,11,21,227,3),['out'(_x)],'gen','agent_call'('src_span'(11,27,11,30,236,3),'GEN',['+'(_x,'int'(1))]),'src_span'(11,24,11,26,232,14))),'prefix'('src_span'(11,40,11,44,249,4),['out'(_x)],'stop','skip'('src_span'(11,50,11,54,259,4)),'src_span'(11,47,11,49,255,10)),'src_span_operator'('no_loc_info_available','src_span'(11,37,11,39,246,2))),'no_loc_info_available').
'bindval'('PROB_TEST_TRACE','[>'('prefix'('src_span'(14,19,14,24,317,5),[],'dotTuple'(['gen','int'(0)]),'prefix'('src_span'(14,28,14,33,326,5),[],'dotTuple'(['gen','int'(1)]),'prefix'('src_span'(14,37,14,42,335,5),[],'dotTuple'(['gen','int'(2)]),'stop'('src_span'(14,46,14,50,344,4)),'src_span'(14,43,14,45,340,13)),'src_span'(14,34,14,36,331,22)),'src_span'(14,25,14,27,322,31)),'prefix'('src_span'(14,54,14,60,352,6),[],'dotTuple'(['gen','int'(20)]),'prefix'('src_span'(14,64,14,70,362,6),[],'dotTuple'(['gen','int'(21)]),'prefix'('src_span'(14,74,14,81,372,7),[],'dotTuple'(['stop','int'(22)]),'stop'('src_span'(14,85,14,89,383,4)),'src_span'(14,82,14,84,379,15)),'src_span'(14,71,14,73,368,25)),'src_span'(14,61,14,63,358,35)),'src_span_operator'('no_loc_info_available','src_span'(14,51,14,53,349,2))),'src_span'(14,1,14,89,299,88)).
'assertRef'('False','val_of'('MAIN','src_span'(16,8,16,12,396,4)),'Trace','val_of'('PROB_TEST_TRACE','src_span'(16,17,16,32,405,15)),'src_span'(16,1,16,32,389,31)).
'comment'('lineComment'('-- SimpleInterruptTimeout'),'src_position'(1,1,0,25)).
'comment'('lineComment'('-- Note: FDR and ProB statespace different due to non-omega semantics of FDR'),'src_position'(3,1,27,76)).
'comment'('lineComment'('-- for FDR'),'src_position'(5,18,122,10)).
'comment'('lineComment'('-- Trace Check Generated by ProB:'),'src_position'(13,1,265,33)).
'symbol'('MyInt','MyInt','src_span'(5,1,5,6,105,5),'Ident (Groundrep.)').
'symbol'('gen','gen','src_span'(6,9,6,12,141,3),'Channel').
'symbol'('stop','stop','src_span'(7,9,7,13,159,4),'Channel').
'symbol'('MAIN','MAIN','src_span'(9,1,9,5,171,4),'Ident (Groundrep.)').
'symbol'('GEN','GEN','src_span'(11,1,11,4,210,3),'Funktion or Process').
'symbol'('x','x','src_span'(11,5,11,6,214,1),'Ident (Prolog Variable)').
'symbol'('PROB_TEST_TRACE','PROB_TEST_TRACE','src_span'(14,1,14,16,299,15),'Ident (Groundrep.)').