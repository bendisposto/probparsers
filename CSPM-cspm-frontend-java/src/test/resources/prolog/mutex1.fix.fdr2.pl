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
'channel'('flag1set','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2))),'boolType']))).
'channel'('flag1read','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2))),'boolType']))).
'channel'('flag2set','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2))),'boolType']))).
'channel'('flag2read','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2))),'boolType']))).
'channel'('enter','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2)))]))).
'channel'('critical','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2)))]))).
'channel'('leave','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2)))]))).
'agent'('FLAG1'(_v),'[]'('[]'('prefix'('src_span'(6,13,6,21,151,8),['in'(_x),'in'(_y)],'flag1set','agent_call'('src_span'(6,29,6,34,167,5),'FLAG1',[_y]),'src_span'(6,26,6,28,163,14)),'prefix'('src_span'(7,13,7,26,188,13),[],'dotTuple'(['flag1read','int'(1),_v]),'agent_call'('src_span'(7,30,7,35,205,5),'FLAG1',[_v]),'src_span'(7,27,7,29,201,25)),'src_span_operator'('no_loc_info_available','src_span'(7,10,7,12,185,2))),'prefix'('src_span'(8,13,8,26,226,13),[],'dotTuple'(['flag1read','int'(2),_v]),'agent_call'('src_span'(8,30,8,35,243,5),'FLAG1',[_v]),'src_span'(8,27,8,29,239,25)),'src_span_operator'('no_loc_info_available','src_span'(8,10,8,12,223,2))),'no_loc_info_available').
'agent'('FLAG2'(_v2),'[]'('[]'('prefix'('src_span'(10,13,10,21,265,8),['in'(_x2),'in'(_y2)],'flag2set','agent_call'('src_span'(10,29,10,34,281,5),'FLAG2',[_y2]),'src_span'(10,26,10,28,277,14)),'prefix'('src_span'(11,13,11,26,302,13),[],'dotTuple'(['flag2read','int'(1),_v2]),'agent_call'('src_span'(11,30,11,35,319,5),'FLAG2',[_v2]),'src_span'(11,27,11,29,315,25)),'src_span_operator'('no_loc_info_available','src_span'(11,10,11,12,299,2))),'prefix'('src_span'(12,13,12,26,340,13),[],'dotTuple'(['flag2read','int'(2),_v2]),'agent_call'('src_span'(12,30,12,35,357,5),'FLAG2',[_v2]),'src_span'(12,27,12,29,353,25)),'src_span_operator'('no_loc_info_available','src_span'(12,10,12,12,337,2))),'no_loc_info_available').
'bindval'('P1','prefix'('src_span'(14,6,14,21,372,15),[],'dotTuple'(['flag1set','int'(1),'true']),'val_of'('P1WAIT','src_span'(14,25,14,31,391,6)),'src_span'(14,22,14,24,387,25)),'src_span'(14,1,14,31,367,30)).
'bindval'('P1WAIT','[]'('prefix'('src_span'(16,11,16,27,409,16),[],'dotTuple'(['flag2read','int'(1),'true']),'val_of'('P1WAIT','src_span'(16,31,16,37,429,6)),'src_span'(16,28,16,30,425,26)),'prefix'('src_span'(17,11,17,28,446,17),[],'dotTuple'(['flag2read','int'(1),'false']),'val_of'('P1ENTER','src_span'(17,32,17,39,467,7)),'src_span'(17,29,17,31,463,28)),'src_span_operator'('no_loc_info_available','src_span'(17,8,17,10,443,2))),'src_span'(16,1,17,39,399,75)).
'bindval'('P1ENTER','prefix'('src_span'(19,11,19,18,486,7),[],'dotTuple'(['enter','int'(1)]),'prefix'('src_span'(19,22,19,32,497,10),[],'dotTuple'(['critical','int'(1)]),'prefix'('src_span'(19,36,19,43,511,7),[],'dotTuple'(['leave','int'(1)]),'prefix'('src_span'(19,47,19,63,522,16),[],'dotTuple'(['flag1set','int'(1),'false']),'val_of'('P1','src_span'(19,67,19,69,542,2)),'src_span'(19,64,19,66,538,22)),'src_span'(19,44,19,46,518,33)),'src_span'(19,33,19,35,507,47)),'src_span'(19,19,19,21,493,58)),'src_span'(19,1,19,69,476,68)).
'bindval'('P2','prefix'('src_span'(21,6,21,21,551,15),[],'dotTuple'(['flag2set','int'(2),'true']),'val_of'('P2WAIT','src_span'(21,25,21,31,570,6)),'src_span'(21,22,21,24,566,25)),'src_span'(21,1,21,31,546,30)).
'bindval'('P2WAIT','[]'('prefix'('src_span'(23,11,23,27,588,16),[],'dotTuple'(['flag1read','int'(2),'true']),'val_of'('P2WAIT','src_span'(23,31,23,37,608,6)),'src_span'(23,28,23,30,604,26)),'prefix'('src_span'(24,11,24,28,625,17),[],'dotTuple'(['flag1read','int'(2),'false']),'val_of'('P2ENTER','src_span'(24,32,24,39,646,7)),'src_span'(24,29,24,31,642,28)),'src_span_operator'('no_loc_info_available','src_span'(24,8,24,10,622,2))),'src_span'(23,1,24,39,578,75)).
'bindval'('P2ENTER','prefix'('src_span'(26,11,26,18,665,7),[],'dotTuple'(['enter','int'(2)]),'prefix'('src_span'(26,22,26,32,676,10),[],'dotTuple'(['critical','int'(2)]),'prefix'('src_span'(26,36,26,43,690,7),[],'dotTuple'(['leave','int'(2)]),'prefix'('src_span'(26,47,26,63,701,16),[],'dotTuple'(['flag2set','int'(2),'false']),'val_of'('P2','src_span'(26,67,26,69,721,2)),'src_span'(26,64,26,66,717,22)),'src_span'(26,44,26,46,697,33)),'src_span'(26,33,26,35,686,47)),'src_span'(26,19,26,21,672,58)),'src_span'(26,1,26,69,655,68)).
'bindval'('aP1','closure'(['dotTuple'(['flag1set','int'(1)]),'dotTuple'(['flag1read','int'(1)]),'dotTuple'(['flag2set','int'(1)]),'dotTuple'(['flag2read','int'(1)]),'dotTuple'(['enter','int'(1)]),'dotTuple'(['critical','int'(1)]),'dotTuple'(['leave','int'(1)])]),'src_span'(28,1,29,41,725,99)).
'bindval'('aP2','closure'(['dotTuple'(['flag1set','int'(2)]),'dotTuple'(['flag1read','int'(2)]),'dotTuple'(['flag2set','int'(2)]),'dotTuple'(['flag2read','int'(2)]),'dotTuple'(['enter','int'(2)]),'dotTuple'(['critical','int'(2)]),'dotTuple'(['leave','int'(2)])]),'src_span'(31,1,32,41,826,99)).
'bindval'('aF1','closure'(['flag1set','flag1read']),'src_span'(34,1,34,31,927,30)).
'bindval'('aF2','closure'(['flag2set','flag2read']),'src_span'(36,1,36,31,959,30)).
'bindval'('PROCS','aParallel'('val_of'('aP1','src_span'(38,14,38,17,1004,3)),'val_of'('P1','src_span'(38,9,38,11,999,2)),'val_of'('aP2','src_span'(38,21,38,24,1011,3)),'val_of'('P2','src_span'(38,27,38,29,1017,2)),'src_span'(38,12,38,26,1002,14)),'src_span'(38,1,38,29,991,28)).
'bindval'('FLAGS','aParallel'('val_of'('aF1','src_span'(40,24,40,27,1044,3)),'agent_call'('src_span'(40,9,40,14,1029,5),'FLAG1',['false']),'val_of'('aF2','src_span'(40,31,40,34,1051,3)),'agent_call'('src_span'(40,37,40,42,1057,5),'FLAG2',['false']),'src_span'(40,22,40,36,1042,14)),'src_span'(40,1,40,49,1021,48)).
'bindval'('SYSTEM','\x5c\'('aParallel'('agent_call'('src_span'(42,19,42,24,1089,5),'union',['val_of'('aP1','src_span'(42,25,42,28,1095,3)),'val_of'('aP2','src_span'(42,29,42,32,1099,3))]),'val_of'('PROCS','src_span'(42,11,42,16,1081,5)),'agent_call'('src_span'(42,37,42,42,1107,5),'union',['val_of'('aF1','src_span'(42,43,42,46,1113,3)),'val_of'('aF2','src_span'(42,47,42,50,1117,3))]),'val_of'('FLAGS','src_span'(42,54,42,59,1124,5)),'src_span'(42,17,42,53,1087,36)),'agent_call'('src_span'(42,63,42,68,1133,5),'union',['val_of'('aF1','src_span'(42,69,42,72,1139,3)),'val_of'('aF2','src_span'(42,73,42,76,1143,3))]),'src_span_operator'('no_loc_info_available','src_span'(42,61,42,62,1131,1))),'src_span'(42,1,42,77,1071,76)).
'comment'('lineComment'('-- Attempt1 from Coursework 1, in CSP'),'src_position'(1,1,0,37)).
'symbol'('flag1set','flag1set','src_span'(3,9,3,17,47,8),'Channel').
'symbol'('flag1read','flag1read','src_span'(3,19,3,28,57,9),'Channel').
'symbol'('flag2set','flag2set','src_span'(3,30,3,38,68,8),'Channel').
'symbol'('flag2read','flag2read','src_span'(3,40,3,49,78,9),'Channel').
'symbol'('enter','enter','src_span'(4,9,4,14,108,5),'Channel').
'symbol'('critical','critical','src_span'(4,16,4,24,115,8),'Channel').
'symbol'('leave','leave','src_span'(4,26,4,31,125,5),'Channel').
'symbol'('FLAG1','FLAG1','src_span'(6,1,6,6,139,5),'Funktion or Process').
'symbol'('v','v','src_span'(6,7,6,8,145,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(6,22,6,23,160,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(6,24,6,25,162,1),'Ident (Prolog Variable)').
'symbol'('FLAG2','FLAG2','src_span'(10,1,10,6,253,5),'Funktion or Process').
'symbol'('v2','v','src_span'(10,7,10,8,259,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(10,22,10,23,274,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(10,24,10,25,276,1),'Ident (Prolog Variable)').
'symbol'('P1','P1','src_span'(14,1,14,3,367,2),'Ident (Groundrep.)').
'symbol'('P1WAIT','P1WAIT','src_span'(16,1,16,7,399,6),'Ident (Groundrep.)').
'symbol'('P1ENTER','P1ENTER','src_span'(19,1,19,8,476,7),'Ident (Groundrep.)').
'symbol'('P2','P2','src_span'(21,1,21,3,546,2),'Ident (Groundrep.)').
'symbol'('P2WAIT','P2WAIT','src_span'(23,1,23,7,578,6),'Ident (Groundrep.)').
'symbol'('P2ENTER','P2ENTER','src_span'(26,1,26,8,655,7),'Ident (Groundrep.)').
'symbol'('aP1','aP1','src_span'(28,1,28,4,725,3),'Ident (Groundrep.)').
'symbol'('aP2','aP2','src_span'(31,1,31,4,826,3),'Ident (Groundrep.)').
'symbol'('aF1','aF1','src_span'(34,1,34,4,927,3),'Ident (Groundrep.)').
'symbol'('aF2','aF2','src_span'(36,1,36,4,959,3),'Ident (Groundrep.)').
'symbol'('PROCS','PROCS','src_span'(38,1,38,6,991,5),'Ident (Groundrep.)').
'symbol'('FLAGS','FLAGS','src_span'(40,1,40,6,1021,5),'Ident (Groundrep.)').
'symbol'('SYSTEM','SYSTEM','src_span'(42,1,42,7,1071,6),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(42,19,42,24,1089,5),'BuiltIn primitive').
