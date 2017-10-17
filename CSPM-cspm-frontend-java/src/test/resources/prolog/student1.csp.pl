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
'channel'('year1','type'('dotUnitType')).
'channel'('year2','type'('dotUnitType')).
'channel'('year3','type'('dotUnitType')).
'channel'('pass','type'('dotUnitType')).
'channel'('fail','type'('dotUnitType')).
'channel'('graduate','type'('dotUnitType')).
'channel'('present','type'('dotUnitType')).
'bindval'('S','closure'(['year1','year2','year3','pass','fail','graduate']),'src_span'(9,1,9,52,128,51)).
'bindval'('P','closure'(['pass','present']),'src_span'(11,1,11,24,181,23)).
'bindval'('STUDENT','prefix'('src_span'(13,11,13,16,216,5),[],'year1','[]'('prefix'('src_span'(13,21,13,25,226,4),[],'pass','val_of'('YEAR2','src_span'(13,29,13,34,234,5)),'src_span'(13,26,13,28,230,13)),'prefix'('src_span'(13,38,13,42,243,4),[],'fail','val_of'('STUDENT','src_span'(13,46,13,53,251,7)),'src_span'(13,43,13,45,247,15)),'src_span_operator'('no_loc_info_available','src_span'(13,35,13,37,240,2))),'src_span'(13,17,13,19,221,43)),'src_span'(13,1,13,54,206,53)).
'bindval'('YEAR2','prefix'('src_span'(15,9,15,14,269,5),[],'year2','[]'('prefix'('src_span'(15,19,15,23,279,4),[],'pass','val_of'('YEAR3','src_span'(15,27,15,32,287,5)),'src_span'(15,24,15,26,283,13)),'prefix'('src_span'(15,36,15,40,296,4),[],'fail','val_of'('YEAR2','src_span'(15,44,15,49,304,5)),'src_span'(15,41,15,43,300,13)),'src_span_operator'('no_loc_info_available','src_span'(15,33,15,35,293,2))),'src_span'(15,15,15,17,274,41)),'src_span'(15,1,15,50,261,49)).
'bindval'('YEAR3','prefix'('src_span'(17,9,17,14,320,5),[],'year3','[]'('prefix'('src_span'(17,19,17,23,330,4),[],'pass','prefix'('src_span'(17,27,17,35,338,8),[],'graduate','stop'('src_span'(17,39,17,43,350,4)),'src_span'(17,36,17,38,346,16)),'src_span'(17,24,17,26,334,24)),'prefix'('src_span'(17,47,17,51,358,4),[],'fail','val_of'('YEAR3','src_span'(17,55,17,60,366,5)),'src_span'(17,52,17,54,362,13)),'src_span_operator'('no_loc_info_available','src_span'(17,44,17,46,355,2))),'src_span'(17,15,17,17,325,52)),'src_span'(17,1,17,61,312,60)).
'bindval'('PARENT','prefix'('src_span'(19,10,19,14,383,4),[],'pass','prefix'('src_span'(19,18,19,25,391,7),[],'present','val_of'('PARENT','src_span'(19,29,19,35,402,6)),'src_span'(19,26,19,28,398,17)),'src_span'(19,15,19,17,387,25)),'src_span'(19,1,19,35,374,34)).
'bindval'('SYSTEM','aParallel'('val_of'('S','src_span'(21,20,21,21,429,1)),'val_of'('STUDENT','src_span'(21,10,21,17,419,7)),'val_of'('P','src_span'(21,25,21,26,434,1)),'val_of'('PARENT','src_span'(21,29,21,35,438,6)),'src_span'(21,18,21,28,427,10)),'src_span'(21,1,21,35,410,34)).
'comment'('lineComment'('-- student.csp'),'src_position'(1,1,0,14)).
'comment'('lineComment'('--'),'src_position'(2,1,15,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,18,42)).
'comment'('lineComment'('--'),'src_position'(4,1,61,2)).
'comment'('lineComment'('--'),'src_position'(5,1,64,2)).
'symbol'('year1','year1','src_span'(7,9,7,14,76,5),'Channel').
'symbol'('year2','year2','src_span'(7,16,7,21,83,5),'Channel').
'symbol'('year3','year3','src_span'(7,23,7,28,90,5),'Channel').
'symbol'('pass','pass','src_span'(7,30,7,34,97,4),'Channel').
'symbol'('fail','fail','src_span'(7,36,7,40,103,4),'Channel').
'symbol'('graduate','graduate','src_span'(7,42,7,50,109,8),'Channel').
'symbol'('present','present','src_span'(7,52,7,59,119,7),'Channel').
'symbol'('S','S','src_span'(9,1,9,2,128,1),'Ident (Groundrep.)').
'symbol'('P','P','src_span'(11,1,11,2,181,1),'Ident (Groundrep.)').
'symbol'('STUDENT','STUDENT','src_span'(13,1,13,8,206,7),'Ident (Groundrep.)').
'symbol'('YEAR2','YEAR2','src_span'(15,1,15,6,261,5),'Ident (Groundrep.)').
'symbol'('YEAR3','YEAR3','src_span'(17,1,17,6,312,5),'Ident (Groundrep.)').
'symbol'('PARENT','PARENT','src_span'(19,1,19,7,374,6),'Ident (Groundrep.)').
'symbol'('SYSTEM','SYSTEM','src_span'(21,1,21,7,410,6),'Ident (Groundrep.)').