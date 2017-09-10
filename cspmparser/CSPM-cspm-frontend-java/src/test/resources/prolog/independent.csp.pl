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
'channel'('up','type'('dotUnitType')).
'channel'('down','type'('dotUnitType')).
'channel'('left','type'('dotUnitType')).
'channel'('right','type'('dotUnitType')).
'bindval'('UD','prefix'('src_span'(9,6,9,8,108,2),[],'up','prefix'('src_span'(9,12,9,16,114,4),[],'down','val_of'('UD','src_span'(9,20,9,22,122,2)),'src_span'(9,17,9,19,118,10)),'src_span'(9,9,9,11,110,16)),'src_span'(9,1,9,22,103,21)).
'bindval'('LR','prefix'('src_span'(11,6,11,10,131,4),[],'left','prefix'('src_span'(11,14,11,19,139,5),[],'right','val_of'('LR','src_span'(11,23,11,25,148,2)),'src_span'(11,20,11,22,144,11)),'src_span'(11,11,11,13,135,19)),'src_span'(11,1,11,25,126,24)).
'bindval'('UDLR','aParallel'('closure'(['up','down']),'val_of'('UD','src_span'(13,8,13,10,159,2)),'closure'(['left','right']),'val_of'('LR','src_span'(13,49,13,51,200,2)),'src_span'(13,11,13,48,162,37)),'src_span'(13,1,13,51,152,50)).
'comment'('lineComment'('-- independent.csp'),'src_position'(1,1,0,18)).
'comment'('lineComment'('--'),'src_position'(2,1,19,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,22,42)).
'comment'('lineComment'('--'),'src_position'(4,1,65,2)).
'comment'('lineComment'('--'),'src_position'(5,1,68,2)).
'symbol'('up','up','src_span'(7,9,7,11,80,2),'Channel').
'symbol'('down','down','src_span'(7,13,7,17,84,4),'Channel').
'symbol'('left','left','src_span'(7,19,7,23,90,4),'Channel').
'symbol'('right','right','src_span'(7,25,7,30,96,5),'Channel').
'symbol'('UD','UD','src_span'(9,1,9,3,103,2),'Ident (Groundrep.)').
'symbol'('LR','LR','src_span'(11,1,11,3,126,2),'Ident (Groundrep.)').
'symbol'('UDLR','UDLR','src_span'(13,1,13,5,152,4),'Ident (Groundrep.)').