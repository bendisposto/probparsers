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
'channel'('coin','type'('dotUnitType')).
'channel'('choc','type'('dotUnitType')).
'bindval'('VM','prefix'('src_span'(9,6,9,10,89,4),[],'coin','prefix'('src_span'(9,14,9,18,97,4),[],'choc','stop'('src_span'(9,22,9,26,105,4)),'src_span'(9,19,9,21,101,12)),'src_span'(9,11,9,13,93,20)),'src_span'(9,1,9,26,84,25)).
'comment'('lineComment'('-- vm1.csp'),'src_position'(1,1,0,10)).
'comment'('lineComment'('--'),'src_position'(2,1,11,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,14,42)).
'comment'('lineComment'('--'),'src_position'(4,1,57,2)).
'comment'('lineComment'('--'),'src_position'(5,1,60,2)).
'symbol'('coin','coin','src_span'(7,9,7,13,72,4),'Channel').
'symbol'('choc','choc','src_span'(7,15,7,19,78,4),'Channel').
'symbol'('VM','VM','src_span'(9,1,9,3,84,2),'Ident (Groundrep.)').