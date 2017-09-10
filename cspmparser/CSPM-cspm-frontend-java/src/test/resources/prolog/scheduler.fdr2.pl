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
'bindval'('n','int'(5),'src_span'(7,1,7,6,123,5)).
'channel'('start','type'('dotTupleType'(['setExp'('rangeClosed'('int'(0),'val_of'('n','src_span'(9,32,9,33,161,1))))]))).
'channel'('finish','type'('dotTupleType'(['setExp'('rangeClosed'('int'(0),'val_of'('n','src_span'(9,32,9,33,161,1))))]))).
'channel'('c','type'('dotTupleType'(['setExp'('rangeClosed'('int'(0),'val_of'('n','src_span'(9,32,9,33,161,1))))]))).
'agent'('inc'(_x),'%'('+'(_x,'int'(1)),'+'('val_of'('n','src_span'(12,19,12,20,216,1)),'int'(1))),'no_loc_info_available').
'agent'('STARTCELL'(_i),'prefix'('src_span'(14,16,14,23,237,7),[],'dotTuple'(['start',_i]),'prefix'('src_span'(14,27,14,35,248,8),[],'dotTuple'(['c','agent_call'('src_span'(14,29,14,32,250,3),'inc',[_i])]),'[]'('prefix'('src_span'(15,20,15,28,280,8),[],'dotTuple'(['finish',_i]),'prefix'('src_span'(15,32,15,35,292,3),[],'dotTuple'(['c',_i]),'agent_call'('src_span'(15,39,15,48,299,9),'STARTCELL',[_i]),'src_span'(15,36,15,38,295,19)),'src_span'(15,29,15,31,288,31)),'prefix'('src_span'(16,20,16,23,331,3),[],'dotTuple'(['c',_i]),'prefix'('src_span'(16,27,16,35,338,8),[],'dotTuple'(['finish',_i]),'agent_call'('src_span'(16,39,16,48,350,9),'STARTCELL',[_i]),'src_span'(16,36,16,38,346,24)),'src_span'(16,24,16,26,334,31)),'src_span_operator'('no_loc_info_available','src_span'(16,17,16,19,328,2))),'src_span'(14,36,15,16,256,116)),'src_span'(14,24,14,26,244,127)),'src_span'(14,16,16,53,237,127)).
'agent'('WAITCELL'(_i2),'prefix'('src_span'(18,15,18,18,380,3),[],'dotTuple'(['c',_i2]),'agent_call'('src_span'(18,22,18,31,387,9),'STARTCELL',[_i2]),'src_span'(18,19,18,21,383,19)),'src_span'(18,15,18,34,380,19)).
'agent'('CELL'(_i3),'ifte'('=='(_i3,'int'(0)),'agent_call'('src_span'(20,24,20,33,424,9),'STARTCELL',[_i3]),'agent_call'('src_span'(20,42,20,50,442,8),'WAITCELL',[_i3]),'no_loc_info_available','no_loc_info_available','src_span'(20,37,20,41,436,29)),'src_span'(20,11,20,53,411,42)).
'bindval'('SCHED','\x5c\'('procRepAParallel'(['comprehensionGenerator'(_i4,'setExp'('rangeClosed'('int'(0),'val_of'('n','src_span'(22,19,22,20,473,1)))))],'pair'('closure'(['dotTuple'(['start',_i4]),'dotTuple'(['finish',_i4]),'dotTuple'(['c',_i4]),'dotTuple'(['c','agent_call'('src_span'(22,54,22,57,508,3),'inc',[_i4])])]),'agent_call'('src_span'(22,66,22,70,520,4),'CELL',[_i4])),'src_span'(22,13,22,23,467,10)),'closure'(['c']),'src_span_operator'('no_loc_info_available','src_span'(23,9,23,10,538,1))),'src_span'(22,1,23,16,455,90)).
'agent'('ALT'(_i5),'prefix'('src_span'(27,10,27,17,579,7),[],'dotTuple'(['start',_i5]),'prefix'('src_span'(27,21,27,29,590,8),[],'dotTuple'(['finish',_i5]),'agent_call'('src_span'(27,33,27,36,602,3),'ALT',[_i5]),'src_span'(27,30,27,32,598,18)),'src_span'(27,18,27,20,586,29)),'src_span'(27,10,27,39,579,29)).
'bindval'('ALTSPEC','procRepAParallel'(['comprehensionGenerator'(_i6,'setExp'('rangeClosed'('int'(0),'val_of'('n','src_span'(29,20,29,21,629,1)))))],'pair'('closure'(['dotTuple'(['start',_i6]),'dotTuple'(['finish',_i6])]),'agent_call'('src_span'(29,52,29,55,661,3),'ALT',[_i6])),'src_span'(29,14,29,24,623,10)),'src_span'(29,1,29,58,610,57)).
'agent'('CYCLE'(_i7),'prefix'('src_span'(31,12,31,19,680,7),[],'dotTuple'(['start',_i7]),'agent_call'('src_span'(31,23,31,28,691,5),'CYCLE',['agent_call'('src_span'(31,29,31,32,697,3),'inc',[_i7])]),'src_span'(31,20,31,22,687,24)),'src_span'(31,12,31,36,680,24)).
'assertRef'('False','val_of'('ALTSPEC','src_span'(35,8,35,15,732,7)),'Trace','val_of'('SCHED','src_span'(35,20,35,25,744,5)),'src_span'(35,1,35,25,725,24)).
'assertRef'('False','agent_call'('src_span'(36,8,36,13,757,5),'CYCLE',['int'(0)]),'Trace','\x5c\'('val_of'('SCHED','src_span'(36,22,36,27,771,5)),'closure'(['finish']),'src_span_operator'('no_loc_info_available','src_span'(36,28,36,29,777,1))),'src_span'(36,1,36,41,750,40)).
'agent'('SCH'(_s,_R),'[]'('ifte'('bool_not'('=='('agent_call'('src_span'(40,20,40,25,859,5),'union',[_R,'setExp'('rangeEnum'([_s]))]),_R)),'prefix'('src_span'(41,18,41,25,896,7),[],'dotTuple'(['start',_s]),'agent_call'('src_span'(41,29,41,32,907,3),'SCH',['agent_call'('src_span'(41,33,41,36,911,3),'inc',[_s]),'agent_call'('src_span'(41,40,41,45,918,5),'union',[_R,'setExp'('rangeEnum'([_s]))])]),'src_span'(41,26,41,28,903,35)),'stop'('src_span'(42,18,42,22,949,4)),'src_span'(40,13,40,19,852,6),'src_span'(40,20,41,17,858,76),'src_span'(41,54,42,17,931,57)),'repChoice'(['comprehensionGenerator'(_i8,_R)],'prefix'('src_span'(43,23,43,31,977,8),[],'dotTuple'(['finish',_i8]),'agent_call'('src_span'(43,35,43,38,989,3),'SCH',[_s,'agent_call'('src_span'(43,41,43,45,995,4),'diff',[_R,'setExp'('rangeEnum'([_i8]))])]),'src_span'(43,32,43,34,985,30)),'src_span'(43,17,43,22,971,5)),'src_span_operator'('no_loc_info_available','src_span'(43,10,43,12,964,2))),'no_loc_info_available').
'bindval'('NEWSCHED','agent_call'('src_span'(45,12,45,15,1021,3),'SCH',['int'(0),'setExp'('rangeEnum'([]))]),'src_span'(45,1,45,21,1010,20)).
'assertRef'('False','val_of'('ALTSPEC','src_span'(49,8,49,15,1076,7)),'Trace','val_of'('NEWSCHED','src_span'(49,20,49,28,1088,8)),'src_span'(49,1,49,28,1069,27)).
'assertRef'('False','agent_call'('src_span'(50,8,50,13,1104,5),'CYCLE',['int'(0)]),'Trace','\x5c\'('val_of'('NEWSCHED','src_span'(50,22,50,30,1118,8)),'closure'(['finish']),'src_span_operator'('no_loc_info_available','src_span'(50,31,50,32,1127,1))),'src_span'(50,1,50,44,1097,43)).
'assertRef'('False','val_of'('SCHED','src_span'(54,8,54,13,1193,5)),'Trace','val_of'('NEWSCHED','src_span'(54,18,54,26,1203,8)),'src_span'(54,1,54,26,1186,25)).
'assertRef'('False','val_of'('NEWSCHED','src_span'(55,8,55,16,1219,8)),'Trace','val_of'('SCHED','src_span'(55,21,55,26,1232,5)),'src_span'(55,1,55,26,1212,25)).
'comment'('lineComment'('-- The Cyclic Scheduler'),'src_position'(1,1,0,23)).
'comment'('lineComment'('--'),'src_position'(2,1,24,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,27,42)).
'comment'('lineComment'('--'),'src_position'(4,1,70,2)).
'comment'('lineComment'('-- Define a constant for the largest cell number'),'src_position'(6,1,74,48)).
'comment'('lineComment'('-- Define increment modulo (n+1)'),'src_position'(11,1,165,32)).
'comment'('lineComment'('-- The specifications'),'src_position'(25,1,547,21)).
'comment'('lineComment'('-- The assertions'),'src_position'(33,1,706,17)).
'comment'('lineComment'('-- The alternative definition of the scheduler'),'src_position'(38,1,792,46)).
'comment'('lineComment'('-- Assertions for the new scheduler'),'src_position'(47,1,1032,35)).
'comment'('lineComment'('-- Trace equivalence of the two schedulers'),'src_position'(52,1,1142,42)).
'symbol'('n','n','src_span'(7,1,7,2,123,1),'Ident (Groundrep.)').
'symbol'('start','start','src_span'(9,9,9,14,138,5),'Channel').
'symbol'('finish','finish','src_span'(9,16,9,22,145,6),'Channel').
'symbol'('c','c','src_span'(9,24,9,25,153,1),'Channel').
'symbol'('inc','inc','src_span'(12,1,12,4,198,3),'Funktion or Process').
'symbol'('x','x','src_span'(12,5,12,6,202,1),'Ident (Prolog Variable)').
'symbol'('STARTCELL','STARTCELL','src_span'(14,1,14,10,222,9),'Funktion or Process').
'symbol'('i','i','src_span'(14,11,14,12,232,1),'Ident (Prolog Variable)').
'symbol'('WAITCELL','WAITCELL','src_span'(18,1,18,9,366,8),'Funktion or Process').
'symbol'('i2','i','src_span'(18,10,18,11,375,1),'Ident (Prolog Variable)').
'symbol'('CELL','CELL','src_span'(20,1,20,5,401,4),'Funktion or Process').
'symbol'('i3','i','src_span'(20,6,20,7,406,1),'Ident (Prolog Variable)').
'symbol'('SCHED','SCHED','src_span'(22,1,22,6,455,5),'Ident (Groundrep.)').
'symbol'('i4','i','src_span'(22,13,22,14,467,1),'Ident (Prolog Variable)').
'symbol'('ALT','ALT','src_span'(27,1,27,4,570,3),'Funktion or Process').
'symbol'('i5','i','src_span'(27,5,27,6,574,1),'Ident (Prolog Variable)').
'symbol'('ALTSPEC','ALTSPEC','src_span'(29,1,29,8,610,7),'Ident (Groundrep.)').
'symbol'('i6','i','src_span'(29,14,29,15,623,1),'Ident (Prolog Variable)').
'symbol'('CYCLE','CYCLE','src_span'(31,1,31,6,669,5),'Funktion or Process').
'symbol'('i7','i','src_span'(31,7,31,8,675,1),'Ident (Prolog Variable)').
'symbol'('SCH','SCH','src_span'(40,1,40,4,840,3),'Funktion or Process').
'symbol'('s','s','src_span'(40,5,40,6,844,1),'Ident (Prolog Variable)').
'symbol'('R','R','src_span'(40,7,40,8,846,1),'Ident (Prolog Variable)').
'symbol'('union','union','src_span'(40,20,40,25,859,5),'BuiltIn primitive').
'symbol'('i8','i','src_span'(43,17,43,18,971,1),'Ident (Prolog Variable)').
'symbol'('diff','diff','src_span'(43,41,43,45,995,4),'BuiltIn primitive').
'symbol'('NEWSCHED','NEWSCHED','src_span'(45,1,45,9,1010,8),'Ident (Groundrep.)').