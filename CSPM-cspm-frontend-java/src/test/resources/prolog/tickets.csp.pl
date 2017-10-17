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
'channel'('on','type'('dotUnitType')).
'channel'('off','type'('dotUnitType')).
'channel'('staines','type'('dotUnitType')).
'channel'('ashford','type'('dotUnitType')).
'channel'('pound','type'('dotUnitType')).
'channel'('ticket','type'('dotUnitType')).
'bindval'('MACHINE','prefix'('src_span'(9,11,9,13,128,2),[],'on','val_of'('TICKETS','src_span'(9,17,9,24,134,7)),'src_span'(9,14,9,16,130,13)),'src_span'(9,1,9,24,118,23)).
'bindval'('TICKETS','[]'('[]'('prefix'('src_span'(11,13,11,20,155,7),[],'staines','prefix'('src_span'(11,24,11,29,166,5),[],'pound','prefix'('src_span'(11,33,11,39,175,6),[],'ticket','val_of'('TICKETS','src_span'(11,43,11,50,185,7)),'src_span'(11,40,11,42,181,17)),'src_span'(11,30,11,32,171,26)),'src_span'(11,21,11,23,162,37)),'prefix'('src_span'(12,13,12,20,205,7),[],'ashford','prefix'('src_span'(12,24,12,29,216,5),[],'pound','prefix'('src_span'(12,33,12,38,225,5),[],'pound','prefix'('src_span'(12,42,12,48,234,6),[],'ticket','val_of'('TICKETS','src_span'(12,52,12,59,244,7)),'src_span'(12,49,12,51,240,17)),'src_span'(12,39,12,41,230,26)),'src_span'(12,30,12,32,221,35)),'src_span'(12,21,12,23,212,46)),'src_span_operator'('no_loc_info_available','src_span'(12,10,12,12,202,2))),'prefix'('src_span'(13,13,13,16,264,3),[],'off','val_of'('MACHINE','src_span'(13,20,13,27,271,7)),'src_span'(13,17,13,19,267,14)),'src_span_operator'('no_loc_info_available','src_span'(13,10,13,12,261,2))),'src_span'(11,1,13,27,143,135)).
'comment'('lineComment'('-- tickets.csp'),'src_position'(1,1,0,14)).
'comment'('lineComment'('--'),'src_position'(2,1,15,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,18,42)).
'comment'('lineComment'('--'),'src_position'(4,1,61,2)).
'comment'('lineComment'('--'),'src_position'(5,1,64,2)).
'symbol'('on','on','src_span'(7,9,7,11,76,2),'Channel').
'symbol'('off','off','src_span'(7,13,7,16,80,3),'Channel').
'symbol'('staines','staines','src_span'(7,18,7,25,85,7),'Channel').
'symbol'('ashford','ashford','src_span'(7,27,7,34,94,7),'Channel').
'symbol'('pound','pound','src_span'(7,36,7,41,103,5),'Channel').
'symbol'('ticket','ticket','src_span'(7,43,7,49,110,6),'Channel').
'symbol'('MACHINE','MACHINE','src_span'(9,1,9,8,118,7),'Ident (Groundrep.)').
'symbol'('TICKETS','TICKETS','src_span'(11,1,11,8,143,7),'Ident (Groundrep.)').