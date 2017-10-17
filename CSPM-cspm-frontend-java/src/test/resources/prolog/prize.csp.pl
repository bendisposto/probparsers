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
'channel'('prize','type'('dotUnitType')).
'bindval'('S','closure'(['year1','year2','year3','pass','fail','graduate']),'src_span'(10,1,10,52,134,51)).
'bindval'('P','closure'(['pass','present']),'src_span'(12,1,12,24,187,23)).
'bindval'('C','closure'(['pass','fail','prize']),'src_span'(14,1,14,28,212,27)).
'bindval'('STUDENT','prefix'('src_span'(16,11,16,16,251,5),[],'year1','[]'('prefix'('src_span'(16,21,16,25,261,4),[],'pass','val_of'('YEAR2','src_span'(16,29,16,34,269,5)),'src_span'(16,26,16,28,265,13)),'prefix'('src_span'(16,38,16,42,278,4),[],'fail','val_of'('STUDENT','src_span'(16,46,16,53,286,7)),'src_span'(16,43,16,45,282,15)),'src_span_operator'('no_loc_info_available','src_span'(16,35,16,37,275,2))),'src_span'(16,17,16,19,256,43)),'src_span'(16,1,16,54,241,53)).
'bindval'('YEAR2','prefix'('src_span'(18,9,18,14,304,5),[],'year2','[]'('prefix'('src_span'(18,19,18,23,314,4),[],'pass','val_of'('YEAR3','src_span'(18,27,18,32,322,5)),'src_span'(18,24,18,26,318,13)),'prefix'('src_span'(18,36,18,40,331,4),[],'fail','val_of'('YEAR2','src_span'(18,44,18,49,339,5)),'src_span'(18,41,18,43,335,13)),'src_span_operator'('no_loc_info_available','src_span'(18,33,18,35,328,2))),'src_span'(18,15,18,17,309,41)),'src_span'(18,1,18,50,296,49)).
'bindval'('YEAR3','prefix'('src_span'(20,9,20,14,355,5),[],'year3','[]'('prefix'('src_span'(20,19,20,23,365,4),[],'pass','prefix'('src_span'(20,27,20,35,373,8),[],'graduate','stop'('src_span'(20,39,20,43,385,4)),'src_span'(20,36,20,38,381,16)),'src_span'(20,24,20,26,369,24)),'prefix'('src_span'(20,47,20,51,393,4),[],'fail','val_of'('YEAR3','src_span'(20,55,20,60,401,5)),'src_span'(20,52,20,54,397,13)),'src_span_operator'('no_loc_info_available','src_span'(20,44,20,46,390,2))),'src_span'(20,15,20,17,360,52)),'src_span'(20,1,20,61,347,60)).
'bindval'('PARENT','prefix'('src_span'(22,10,22,14,418,4),[],'pass','prefix'('src_span'(22,18,22,25,426,7),[],'present','val_of'('PARENT','src_span'(22,29,22,35,437,6)),'src_span'(22,26,22,28,433,17)),'src_span'(22,15,22,17,422,25)),'src_span'(22,1,22,35,409,34)).
'bindval'('COLLEGE','[]'('prefix'('src_span'(24,11,24,15,455,4),[],'fail','stop'('src_span'(24,19,24,23,463,4)),'src_span'(24,16,24,18,459,12)),'prefix'('src_span'(24,27,24,31,471,4),[],'pass','val_of'('C1','src_span'(24,35,24,37,479,2)),'src_span'(24,32,24,34,475,10)),'src_span_operator'('no_loc_info_available','src_span'(24,24,24,26,468,2))),'src_span'(24,1,24,37,445,36)).
'bindval'('C1','[]'('prefix'('src_span'(26,6,26,10,488,4),[],'fail','stop'('src_span'(26,14,26,18,496,4)),'src_span'(26,11,26,13,492,12)),'prefix'('src_span'(26,22,26,26,504,4),[],'pass','val_of'('C2','src_span'(26,30,26,32,512,2)),'src_span'(26,27,26,29,508,10)),'src_span_operator'('no_loc_info_available','src_span'(26,19,26,21,501,2))),'src_span'(26,1,26,32,483,31)).
'bindval'('C2','[]'('prefix'('src_span'(28,6,28,10,521,4),[],'fail','stop'('src_span'(28,14,28,18,529,4)),'src_span'(28,11,28,13,525,12)),'prefix'('src_span'(28,22,28,26,537,4),[],'pass','prefix'('src_span'(28,30,28,35,545,5),[],'prize','stop'('src_span'(28,39,28,43,554,4)),'src_span'(28,36,28,38,550,13)),'src_span'(28,27,28,29,541,21)),'src_span_operator'('no_loc_info_available','src_span'(28,19,28,21,534,2))),'src_span'(28,1,28,43,516,42)).
'bindval'('SYSTEM','aParallel'('agent_call'('src_span'(30,40,30,45,599,5),'union',['val_of'('S','src_span'(30,46,30,47,605,1)),'val_of'('P','src_span'(30,48,30,49,607,1))]),'aParallel'('val_of'('S','src_span'(30,21,30,22,580,1)),'val_of'('STUDENT','src_span'(30,11,30,18,570,7)),'val_of'('P','src_span'(30,26,30,27,585,1)),'val_of'('PARENT','src_span'(30,30,30,36,589,6)),'src_span'(30,19,30,29,578,10)),'val_of'('C','src_span'(30,54,30,55,613,1)),'val_of'('COLLEGE','src_span'(30,58,30,65,617,7)),'src_span'(30,38,30,57,597,19)),'src_span'(30,1,30,65,560,64)).
'comment'('lineComment'('-- prize.csp'),'src_position'(1,1,0,12)).
'comment'('lineComment'('--'),'src_position'(2,1,13,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,16,42)).
'comment'('lineComment'('--'),'src_position'(4,1,59,2)).
'comment'('lineComment'('--'),'src_position'(5,1,62,2)).
'symbol'('year1','year1','src_span'(8,9,8,14,75,5),'Channel').
'symbol'('year2','year2','src_span'(8,16,8,21,82,5),'Channel').
'symbol'('year3','year3','src_span'(8,23,8,28,89,5),'Channel').
'symbol'('pass','pass','src_span'(8,30,8,34,96,4),'Channel').
'symbol'('fail','fail','src_span'(8,36,8,40,102,4),'Channel').
'symbol'('graduate','graduate','src_span'(8,42,8,50,108,8),'Channel').
'symbol'('present','present','src_span'(8,52,8,59,118,7),'Channel').
'symbol'('prize','prize','src_span'(8,61,8,66,127,5),'Channel').
'symbol'('S','S','src_span'(10,1,10,2,134,1),'Ident (Groundrep.)').
'symbol'('P','P','src_span'(12,1,12,2,187,1),'Ident (Groundrep.)').
'symbol'('C','C','src_span'(14,1,14,2,212,1),'Ident (Groundrep.)').
'symbol'('STUDENT','STUDENT','src_span'(16,1,16,8,241,7),'Ident (Groundrep.)').
'symbol'('YEAR2','YEAR2','src_span'(18,1,18,6,296,5),'Ident (Groundrep.)').
'symbol'('YEAR3','YEAR3','src_span'(20,1,20,6,347,5),'Ident (Groundrep.)').
'symbol'('PARENT','PARENT','src_span'(22,1,22,7,409,6),'Ident (Groundrep.)').
'symbol'('COLLEGE','COLLEGE','src_span'(24,1,24,8,445,7),'Ident (Groundrep.)').
'symbol'('C1','C1','src_span'(26,1,26,3,483,2),'Ident (Groundrep.)').
'symbol'('C2','C2','src_span'(28,1,28,3,516,2),'Ident (Groundrep.)').
'symbol'('SYSTEM','SYSTEM','src_span'(30,1,30,7,560,6),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(30,40,30,45,599,5),'BuiltIn primitive').