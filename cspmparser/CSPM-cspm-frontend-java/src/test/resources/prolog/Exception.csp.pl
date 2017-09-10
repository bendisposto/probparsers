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
'bindval'('P','exception'('setExp'('rangeEnum'(['b'])),'prefix'('src_span'(3,5,3,6,19,1),[],'a','val_of'('P','src_span'(3,9,3,10,23,1)),'src_span'(3,7,3,8,20,5)),'prefix'('src_span'(3,21,3,22,35,1),[],'c','stop'('src_span'(3,24,3,28,38,4)),'src_span'(3,23,3,23,36,7)),'src_span'(3,12,3,19,26,7)),'src_span'(3,1,3,29,15,28)).
'symbol'('a','a','src_span'(1,9,1,10,8,1),'Channel').
'symbol'('b','b','src_span'(1,11,1,12,10,1),'Channel').
'symbol'('c','c','src_span'(1,13,1,14,12,1),'Channel').
'symbol'('P','P','src_span'(3,1,3,2,15,1),'Ident (Groundrep.)').