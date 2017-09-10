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
'channel'('a','type'('dotUnitType')).
'channel'('b','type'('dotUnitType')).
'channel'('c','type'('dotUnitType')).
'bindval'('P','prefix'('src_span'(8,5,8,6,85,1),[],'a','prefix'('src_span'(8,10,8,11,90,1),[],'b','val_of'('P','src_span'(8,15,8,16,95,1)),'src_span'(8,12,8,14,91,6)),'src_span'(8,7,8,9,86,11)),'src_span'(8,1,8,16,81,15)).
'bindval'('Q','prefix'('src_span'(10,5,10,6,102,1),[],'a','prefix'('src_span'(10,10,10,11,107,1),[],'b','stop'('src_span'(10,15,10,19,112,4)),'src_span'(10,12,10,14,108,9)),'src_span'(10,7,10,9,103,14)),'src_span'(10,1,10,19,98,18)).
'bindval'('R','prefix'('src_span'(12,5,12,6,122,1),[],'a','prefix'('src_span'(12,10,12,11,127,1),[],'c','stop'('src_span'(12,15,12,19,132,4)),'src_span'(12,12,12,14,128,9)),'src_span'(12,7,12,9,123,14)),'src_span'(12,1,12,19,118,18)).
'assertRef'('False','val_of'('P','src_span'(14,8,14,9,145,1)),'Trace','val_of'('Q','src_span'(14,14,14,15,151,1)),'src_span'(14,1,14,15,138,14)).
'assertRef'('False','val_of'('P','src_span'(16,8,16,9,161,1)),'Trace','val_of'('R','src_span'(16,14,16,15,167,1)),'src_span'(16,1,16,15,154,14)).
'comment'('lineComment'('-- spec1.fdr2'),'src_position'(1,1,0,13)).
'comment'('lineComment'('--'),'src_position'(2,1,14,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,17,42)).
'comment'('lineComment'('--'),'src_position'(4,1,60,2)).
'symbol'('a','a','src_span'(6,9,6,10,72,1),'Channel').
'symbol'('b','b','src_span'(6,12,6,13,75,1),'Channel').
'symbol'('c','c','src_span'(6,15,6,16,78,1),'Channel').
'symbol'('P','P','src_span'(8,1,8,2,81,1),'Ident (Groundrep.)').
'symbol'('Q','Q','src_span'(10,1,10,2,98,1),'Ident (Groundrep.)').
'symbol'('R','R','src_span'(12,1,12,2,118,1),'Ident (Groundrep.)').