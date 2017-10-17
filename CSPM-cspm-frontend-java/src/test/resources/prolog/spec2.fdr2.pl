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
'channel'('prize','type'('dotUnitType')).
'bindval'('S','closure'(['year1','year2','year3','pass','fail','graduate']),'src_span'(8,1,8,52,122,51)).
'bindval'('C','closure'(['pass','fail','prize']),'src_span'(10,1,10,28,175,27)).
'bindval'('STUDENT','prefix'('src_span'(12,11,12,16,214,5),[],'year1','[]'('prefix'('src_span'(12,21,12,25,224,4),[],'pass','val_of'('YEAR2','src_span'(12,29,12,34,232,5)),'src_span'(12,26,12,28,228,13)),'prefix'('src_span'(12,38,12,42,241,4),[],'fail','val_of'('STUDENT','src_span'(12,46,12,53,249,7)),'src_span'(12,43,12,45,245,15)),'src_span_operator'('no_loc_info_available','src_span'(12,35,12,37,238,2))),'src_span'(12,17,12,19,219,43)),'src_span'(12,1,12,54,204,53)).
'bindval'('YEAR2','prefix'('src_span'(13,9,13,14,266,5),[],'year2','[]'('prefix'('src_span'(13,19,13,23,276,4),[],'pass','val_of'('YEAR3','src_span'(13,27,13,32,284,5)),'src_span'(13,24,13,26,280,13)),'prefix'('src_span'(13,36,13,40,293,4),[],'fail','val_of'('YEAR2','src_span'(13,44,13,49,301,5)),'src_span'(13,41,13,43,297,13)),'src_span_operator'('no_loc_info_available','src_span'(13,33,13,35,290,2))),'src_span'(13,15,13,17,271,41)),'src_span'(13,1,13,50,258,49)).
'bindval'('YEAR3','prefix'('src_span'(14,9,14,14,316,5),[],'year3','[]'('prefix'('src_span'(14,19,14,23,326,4),[],'pass','prefix'('src_span'(14,27,14,35,334,8),[],'graduate','stop'('src_span'(14,39,14,43,346,4)),'src_span'(14,36,14,38,342,16)),'src_span'(14,24,14,26,330,24)),'prefix'('src_span'(14,47,14,51,354,4),[],'fail','val_of'('YEAR3','src_span'(14,55,14,60,362,5)),'src_span'(14,52,14,54,358,13)),'src_span_operator'('no_loc_info_available','src_span'(14,44,14,46,351,2))),'src_span'(14,15,14,17,321,52)),'src_span'(14,1,14,61,308,60)).
'bindval'('COLLEGE','[]'('prefix'('src_span'(16,11,16,15,380,4),[],'fail','stop'('src_span'(16,19,16,23,388,4)),'src_span'(16,16,16,18,384,12)),'prefix'('src_span'(16,27,16,31,396,4),[],'pass','val_of'('C1','src_span'(16,35,16,37,404,2)),'src_span'(16,32,16,34,400,10)),'src_span_operator'('no_loc_info_available','src_span'(16,24,16,26,393,2))),'src_span'(16,1,16,37,370,36)).
'bindval'('C1','[]'('prefix'('src_span'(17,6,17,10,412,4),[],'fail','stop'('src_span'(17,14,17,18,420,4)),'src_span'(17,11,17,13,416,12)),'prefix'('src_span'(17,22,17,26,428,4),[],'pass','val_of'('C2','src_span'(17,30,17,32,436,2)),'src_span'(17,27,17,29,432,10)),'src_span_operator'('no_loc_info_available','src_span'(17,19,17,21,425,2))),'src_span'(17,1,17,32,407,31)).
'bindval'('C2','[]'('prefix'('src_span'(18,6,18,10,444,4),[],'fail','stop'('src_span'(18,14,18,18,452,4)),'src_span'(18,11,18,13,448,12)),'prefix'('src_span'(18,22,18,26,460,4),[],'pass','prefix'('src_span'(18,30,18,35,468,5),[],'prize','stop'('src_span'(18,39,18,43,477,4)),'src_span'(18,36,18,38,473,13)),'src_span'(18,27,18,29,464,21)),'src_span_operator'('no_loc_info_available','src_span'(18,19,18,21,457,2))),'src_span'(18,1,18,43,439,42)).
'bindval'('SYSTEM','aParallel'('val_of'('S','src_span'(20,20,20,21,502,1)),'val_of'('STUDENT','src_span'(20,10,20,17,492,7)),'val_of'('C','src_span'(20,25,20,26,507,1)),'val_of'('COLLEGE','src_span'(20,29,20,36,511,7)),'src_span'(20,18,20,28,500,10)),'src_span'(20,1,20,36,483,35)).
'bindval'('SPECP','[]'('prefix'('src_span'(22,9,22,13,528,4),[],'pass','val_of'('S1','src_span'(22,17,22,19,536,2)),'src_span'(22,14,22,16,532,10)),'prefix'('src_span'(22,23,22,27,542,4),[],'fail','val_of'('SPECF','src_span'(22,31,22,36,550,5)),'src_span'(22,28,22,30,546,13)),'src_span_operator'('no_loc_info_available','src_span'(22,20,22,22,539,2))),'src_span'(22,1,22,36,520,35)).
'bindval'('S1','[]'('prefix'('src_span'(23,6,23,10,561,4),[],'pass','val_of'('S2','src_span'(23,14,23,16,569,2)),'src_span'(23,11,23,13,565,10)),'prefix'('src_span'(23,20,23,24,575,4),[],'fail','val_of'('SPECF','src_span'(23,28,23,33,583,5)),'src_span'(23,25,23,27,579,13)),'src_span_operator'('no_loc_info_available','src_span'(23,17,23,19,572,2))),'src_span'(23,1,23,33,556,32)).
'bindval'('S2','[]'('prefix'('src_span'(24,6,24,10,594,4),[],'pass','prefix'('src_span'(24,14,24,19,602,5),[],'prize','stop'('src_span'(24,23,24,27,611,4)),'src_span'(24,20,24,22,607,13)),'src_span'(24,11,24,13,598,21)),'prefix'('src_span'(24,31,24,35,619,4),[],'fail','val_of'('SPECF','src_span'(24,39,24,44,627,5)),'src_span'(24,36,24,38,623,13)),'src_span_operator'('no_loc_info_available','src_span'(24,28,24,30,616,2))),'src_span'(24,1,24,44,589,43)).
'bindval'('SPECF','[]'('prefix'('src_span'(26,9,26,13,642,4),[],'pass','val_of'('SPECF','src_span'(26,17,26,22,650,5)),'src_span'(26,14,26,16,646,13)),'prefix'('src_span'(26,26,26,30,659,4),[],'fail','val_of'('SPECF','src_span'(26,34,26,39,667,5)),'src_span'(26,31,26,33,663,13)),'src_span_operator'('no_loc_info_available','src_span'(26,23,26,25,656,2))),'src_span'(26,1,26,39,634,38)).
'assertRef'('False','val_of'('SPECP','src_span'(28,8,28,13,681,5)),'Trace','val_of'('SYSTEM','src_span'(28,18,28,24,691,6)),'src_span'(28,1,28,24,674,23)).
'comment'('lineComment'('-- spec2.fdr2'),'src_position'(1,1,0,13)).
'comment'('lineComment'('--'),'src_position'(2,1,14,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,17,42)).
'comment'('lineComment'('--'),'src_position'(4,1,60,2)).
'symbol'('year1','year1','src_span'(6,9,6,14,72,5),'Channel').
'symbol'('year2','year2','src_span'(6,16,6,21,79,5),'Channel').
'symbol'('year3','year3','src_span'(6,23,6,28,86,5),'Channel').
'symbol'('pass','pass','src_span'(6,30,6,34,93,4),'Channel').
'symbol'('fail','fail','src_span'(6,36,6,40,99,4),'Channel').
'symbol'('graduate','graduate','src_span'(6,42,6,50,105,8),'Channel').
'symbol'('prize','prize','src_span'(6,52,6,57,115,5),'Channel').
'symbol'('S','S','src_span'(8,1,8,2,122,1),'Ident (Groundrep.)').
'symbol'('C','C','src_span'(10,1,10,2,175,1),'Ident (Groundrep.)').
'symbol'('STUDENT','STUDENT','src_span'(12,1,12,8,204,7),'Ident (Groundrep.)').
'symbol'('YEAR2','YEAR2','src_span'(13,1,13,6,258,5),'Ident (Groundrep.)').
'symbol'('YEAR3','YEAR3','src_span'(14,1,14,6,308,5),'Ident (Groundrep.)').
'symbol'('COLLEGE','COLLEGE','src_span'(16,1,16,8,370,7),'Ident (Groundrep.)').
'symbol'('C1','C1','src_span'(17,1,17,3,407,2),'Ident (Groundrep.)').
'symbol'('C2','C2','src_span'(18,1,18,3,439,2),'Ident (Groundrep.)').
'symbol'('SYSTEM','SYSTEM','src_span'(20,1,20,7,483,6),'Ident (Groundrep.)').
'symbol'('SPECP','SPECP','src_span'(22,1,22,6,520,5),'Ident (Groundrep.)').
'symbol'('S1','S1','src_span'(23,1,23,3,556,2),'Ident (Groundrep.)').
'symbol'('S2','S2','src_span'(24,1,24,3,589,2),'Ident (Groundrep.)').
'symbol'('SPECF','SPECF','src_span'(26,1,26,6,634,5),'Ident (Groundrep.)').