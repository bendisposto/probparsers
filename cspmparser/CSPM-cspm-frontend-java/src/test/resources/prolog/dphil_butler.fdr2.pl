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
'channel'('pickup','type'('dotTupleType'(['setExp'('rangeClosed'('int'(0),'int'(4))),'setExp'('rangeClosed'('int'(0),'int'(4)))]))).
'channel'('putdown','type'('dotTupleType'(['setExp'('rangeClosed'('int'(0),'int'(4))),'setExp'('rangeClosed'('int'(0),'int'(4)))]))).
'channel'('sitdown','type'('dotTupleType'(['setExp'('rangeClosed'('int'(0),'int'(4)))]))).
'channel'('getup','type'('dotTupleType'(['setExp'('rangeClosed'('int'(0),'int'(4)))]))).
'agent'('inc'(_x),'%'('+'(_x,'int'(1)),'int'(5)),'no_loc_info_available').
'agent'('dec'(_x2),'%'('-'(_x2,'int'(1)),'int'(5)),'no_loc_info_available').
'agent'('PHIL'(_i),'prefix'('src_span'(13,12,13,21,249,9),[],'dotTuple'(['sitdown',_i]),'prefix'('src_span'(13,25,13,40,262,15),[],'dotTuple'(['pickup',_i,'agent_call'('src_span'(13,34,13,37,271,3),'inc',[_i])]),'prefix'('src_span'(13,44,13,54,281,10),[],'dotTuple'(['pickup',_i,_i]),'prefix'('src_span'(14,12,14,28,307,16),[],'dotTuple'(['putdown',_i,'agent_call'('src_span'(14,22,14,25,317,3),'inc',[_i])]),'prefix'('src_span'(14,32,14,43,327,11),[],'dotTuple'(['putdown',_i,_i]),'prefix'('src_span'(14,47,14,54,342,7),[],'dotTuple'(['getup',_i]),'agent_call'('src_span'(14,58,14,62,353,4),'PHIL',[_i]),'src_span'(14,55,14,57,349,18)),'src_span'(14,44,14,46,338,33)),'src_span'(14,29,14,31,323,53)),'src_span'(13,55,14,11,291,79)),'src_span'(13,41,13,43,277,98)),'src_span'(13,22,13,24,258,111)),'src_span'(13,12,14,65,249,111)).
'agent'('FORK'(_i2),'[]'('prefix'('src_span'(16,11,16,21,372,10),[],'dotTuple'(['pickup',_i2,_i2]),'prefix'('src_span'(16,25,16,36,386,11),[],'dotTuple'(['putdown',_i2,_i2]),'agent_call'('src_span'(16,40,16,44,401,4),'FORK',[_i2]),'src_span'(16,37,16,39,397,22)),'src_span'(16,22,16,24,382,36)),'prefix'('src_span'(17,12,17,27,420,15),[],'dotTuple'(['pickup','agent_call'('src_span'(17,19,17,22,427,3),'dec',[_i2]),_i2]),'prefix'('src_span'(17,31,17,47,439,16),[],'dotTuple'(['putdown','agent_call'('src_span'(17,39,17,42,447,3),'dec',[_i2]),_i2]),'agent_call'('src_span'(17,51,17,55,459,4),'FORK',[_i2]),'src_span'(17,48,17,50,455,27)),'src_span'(17,28,17,30,435,46)),'src_span_operator'('no_loc_info_available','src_span'(17,9,17,11,417,2))),'no_loc_info_available').
'bindval'('PHILS','procRepAParallel'(['comprehensionGenerator'(_i3,'setExp'('rangeClosed'('int'(0),'int'(4))))],'pair'('closure'(['dotTuple'(['pickup',_i3,_i3]),'dotTuple'(['pickup',_i3,'agent_call'('src_span'(19,47,19,50,514,3),'inc',[_i3])]),'dotTuple'(['putdown',_i3,_i3]),'dotTuple'(['putdown',_i3,'agent_call'('src_span'(20,49,20,52,570,3),'inc',[_i3])]),'dotTuple'(['sitdown',_i3]),'dotTuple'(['getup',_i3])]),'agent_call'('src_span'(22,21,22,25,656,4),'PHIL',[_i3])),'src_span'(19,12,19,22,479,10)),'src_span'(19,1,22,28,468,195)).
'bindval'('FORKS','procRepAParallel'(['comprehensionGenerator'(_i4,'setExp'('rangeClosed'('int'(0),'int'(4))))],'pair'('closure'(['dotTuple'(['pickup',_i4,_i4]),'dotTuple'(['putdown',_i4,_i4]),'dotTuple'(['pickup','agent_call'('src_span'(25,33,25,36,748,3),'dec',[_i4]),_i4]),'dotTuple'(['putdown','agent_call'('src_span'(25,51,25,54,766,3),'dec',[_i4]),_i4])]),'agent_call'('src_span'(26,21,26,25,799,4),'FORK',[_i4])),'src_span'(24,12,24,22,676,10)),'src_span'(24,1,26,28,665,141)).
'bindval'('COLLEGE','aParallel'('closure'(['pickup','putdown','sitdown','getup']),'val_of'('PHILS','src_span'(28,11,28,16,818,5)),'closure'(['pickup','putdown']),'val_of'('FORKS','src_span'(29,41,29,46,902,5)),'src_span'(28,17,29,39,824,76)),'src_span'(28,1,29,46,808,99)).
'agent'('BUTLER'(_i5),'ifte'('=='(_i5,'int'(0)),'prefix'('src_span'(32,18,32,25,949,7),['in'(_x3)],'sitdown','agent_call'('src_span'(32,31,32,37,962,6),'BUTLER',['int'(1)]),'src_span'(32,28,32,30,958,15)),'ifte'('=='(_i5,'int'(4)),'prefix'('src_span'(34,23,34,28,1021,5),['in'(_y)],'getup','agent_call'('src_span'(34,34,34,40,1032,6),'BUTLER',['int'(3)]),'src_span'(34,31,34,33,1028,15)),'[]'('prefix'('src_span'(35,26,35,33,1067,7),['in'(_x4)],'sitdown','agent_call'('src_span'(35,39,35,45,1080,6),'BUTLER',['+'(_i5,'int'(1))]),'src_span'(35,36,35,38,1076,17)),'prefix'('src_span'(36,26,36,31,1117,5),['in'(_y2)],'getup','agent_call'('src_span'(36,37,36,43,1128,6),'BUTLER',['-'(_i5,'int'(1))]),'src_span'(36,34,36,36,1124,17)),'src_span_operator'('no_loc_info_available','src_span'(36,23,36,25,1114,2))),'no_loc_info_available','no_loc_info_available','src_span'(34,44,35,22,1041,120)),'no_loc_info_available','no_loc_info_available','src_span'(32,41,33,17,971,192)),'src_span'(31,13,36,50,921,220)).
'bindval'('NEWCOLLEGE','aParallel'('closure'(['pickup','putdown','sitdown','getup']),'val_of'('COLLEGE','src_span'(38,14,38,21,1156,7)),'closure'(['sitdown','getup']),'agent_call'('src_span'(39,44,39,50,1245,6),'BUTLER',['int'(0)]),'src_span'(38,22,39,43,1164,80)),'src_span'(38,1,39,53,1143,111)).
'comment'('lineComment'('-- The dining philosophers with a butler.'),'src_position'(1,1,0,41)).
'comment'('lineComment'('--'),'src_position'(2,1,42,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,45,42)).
'comment'('lineComment'('--'),'src_position'(4,1,88,2)).
'symbol'('pickup','pickup','src_span'(5,9,5,15,99,6),'Channel').
'symbol'('putdown','putdown','src_span'(6,9,6,16,128,7),'Channel').
'symbol'('sitdown','sitdown','src_span'(7,9,7,16,158,7),'Channel').
'symbol'('getup','getup','src_span'(8,9,8,14,181,5),'Channel').
'symbol'('inc','inc','src_span'(10,1,10,4,195,3),'Funktion or Process').
'symbol'('x','x','src_span'(10,5,10,6,199,1),'Ident (Prolog Variable)').
'symbol'('dec','dec','src_span'(11,1,11,4,216,3),'Funktion or Process').
'symbol'('x2','x','src_span'(11,5,11,6,220,1),'Ident (Prolog Variable)').
'symbol'('PHIL','PHIL','src_span'(13,1,13,5,238,4),'Funktion or Process').
'symbol'('i','i','src_span'(13,6,13,7,243,1),'Ident (Prolog Variable)').
'symbol'('FORK','FORK','src_span'(16,1,16,5,362,4),'Funktion or Process').
'symbol'('i2','i','src_span'(16,6,16,7,367,1),'Ident (Prolog Variable)').
'symbol'('PHILS','PHILS','src_span'(19,1,19,6,468,5),'Ident (Groundrep.)').
'symbol'('i3','i','src_span'(19,12,19,13,479,1),'Ident (Prolog Variable)').
'symbol'('FORKS','FORKS','src_span'(24,1,24,6,665,5),'Ident (Groundrep.)').
'symbol'('i4','i','src_span'(24,12,24,13,676,1),'Ident (Prolog Variable)').
'symbol'('COLLEGE','COLLEGE','src_span'(28,1,28,8,808,7),'Ident (Groundrep.)').
'symbol'('BUTLER','BUTLER','src_span'(31,1,31,7,909,6),'Funktion or Process').
'symbol'('i5','i','src_span'(31,8,31,9,916,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(32,26,32,27,957,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(34,29,34,30,1027,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(35,34,35,35,1075,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(36,32,36,33,1123,1),'Ident (Prolog Variable)').
'symbol'('NEWCOLLEGE','NEWCOLLEGE','src_span'(38,1,38,11,1143,10),'Ident (Groundrep.)').