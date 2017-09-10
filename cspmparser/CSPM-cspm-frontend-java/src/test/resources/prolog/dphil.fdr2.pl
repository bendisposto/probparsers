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
'agent'('PHIL'(_i),'prefix'('src_span'(22,12,22,21,478,9),[],'dotTuple'(['sitdown',_i]),'prefix'('src_span'(22,25,22,40,491,15),[],'dotTuple'(['pickup',_i,'agent_call'('src_span'(22,34,22,37,500,3),'inc',[_i])]),'prefix'('src_span'(22,44,22,54,510,10),[],'dotTuple'(['pickup',_i,_i]),'prefix'('src_span'(23,12,23,28,536,16),[],'dotTuple'(['putdown',_i,'agent_call'('src_span'(23,22,23,25,546,3),'inc',[_i])]),'prefix'('src_span'(23,32,23,43,556,11),[],'dotTuple'(['putdown',_i,_i]),'prefix'('src_span'(23,47,23,54,571,7),[],'dotTuple'(['getup',_i]),'agent_call'('src_span'(23,58,23,62,582,4),'PHIL',[_i]),'src_span'(23,55,23,57,578,18)),'src_span'(23,44,23,46,567,33)),'src_span'(23,29,23,31,552,53)),'src_span'(22,55,23,11,520,79)),'src_span'(22,41,22,43,506,98)),'src_span'(22,22,22,24,487,111)),'src_span'(22,12,23,65,478,111)).
'agent'('FORK'(_i2),'[]'('prefix'('src_span'(25,11,25,21,601,10),[],'dotTuple'(['pickup',_i2,_i2]),'prefix'('src_span'(25,25,25,36,615,11),[],'dotTuple'(['putdown',_i2,_i2]),'agent_call'('src_span'(25,40,25,44,630,4),'FORK',[_i2]),'src_span'(25,37,25,39,626,22)),'src_span'(25,22,25,24,611,36)),'prefix'('src_span'(26,12,26,27,649,15),[],'dotTuple'(['pickup','agent_call'('src_span'(26,19,26,22,656,3),'dec',[_i2]),_i2]),'prefix'('src_span'(26,31,26,47,668,16),[],'dotTuple'(['putdown','agent_call'('src_span'(26,39,26,42,676,3),'dec',[_i2]),_i2]),'agent_call'('src_span'(26,51,26,55,688,4),'FORK',[_i2]),'src_span'(26,48,26,50,684,27)),'src_span'(26,28,26,30,664,46)),'src_span_operator'('no_loc_info_available','src_span'(26,9,26,11,646,2))),'no_loc_info_available').
'bindval'('PHILS','procRepAParallel'(['comprehensionGenerator'(_i3,'setExp'('rangeClosed'('int'(0),'int'(4))))],'pair'('closure'(['dotTuple'(['pickup',_i3,_i3]),'dotTuple'(['pickup',_i3,'agent_call'('src_span'(30,47,30,50,788,3),'inc',[_i3])]),'dotTuple'(['putdown',_i3,_i3]),'dotTuple'(['putdown',_i3,'agent_call'('src_span'(31,49,31,52,844,3),'inc',[_i3])]),'dotTuple'(['sitdown',_i3]),'dotTuple'(['getup',_i3])]),'agent_call'('src_span'(33,21,33,25,930,4),'PHIL',[_i3])),'src_span'(30,12,30,22,753,10)),'src_span'(30,1,33,28,742,195)).
'bindval'('FORKS','procRepAParallel'(['comprehensionGenerator'(_i4,'setExp'('rangeClosed'('int'(0),'int'(4))))],'pair'('closure'(['dotTuple'(['pickup',_i4,_i4]),'dotTuple'(['putdown',_i4,_i4]),'dotTuple'(['pickup','agent_call'('src_span'(36,33,36,36,1022,3),'dec',[_i4]),_i4]),'dotTuple'(['putdown','agent_call'('src_span'(36,51,36,54,1040,3),'dec',[_i4]),_i4])]),'agent_call'('src_span'(37,21,37,25,1073,4),'FORK',[_i4])),'src_span'(35,12,35,22,950,10)),'src_span'(35,1,37,28,939,141)).
'bindval'('COLLEGE','aParallel'('closure'(['pickup','putdown','sitdown','getup']),'val_of'('PHILS','src_span'(39,11,39,16,1092,5)),'closure'(['pickup','putdown']),'val_of'('FORKS','src_span'(40,57,40,62,1193,5)),'src_span'(39,17,40,56,1098,94)),'src_span'(39,1,40,62,1082,116)).
'comment'('lineComment'('-- The dining philosophers with deadlock.'),'src_position'(1,1,0,41)).
'comment'('lineComment'('--'),'src_position'(2,1,42,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,45,42)).
'comment'('lineComment'('--'),'src_position'(4,1,88,2)).
'comment'('lineComment'('-- It\x27\s more convenient to call the events pickup.1.2 etc, '),'src_position'(6,1,92,59)).
'comment'('lineComment'('-- instead of 1.pickup.2 as in the notes.'),'src_position'(7,1,152,41)).
'comment'('lineComment'('-- Define addition and subtraction mod 5'),'src_position'(14,1,299,40)).
'comment'('lineComment'('-- The definitions of the philosophers and the forks are just as in'),'src_position'(19,1,384,67)).
'comment'('lineComment'('-- the notes.'),'src_position'(20,1,452,13)).
'comment'('lineComment'('-- Notice the use of indexed parallel here.'),'src_position'(28,1,697,43)).
'symbol'('pickup','pickup','src_span'(9,9,9,15,203,6),'Channel').
'symbol'('putdown','putdown','src_span'(10,9,10,16,232,7),'Channel').
'symbol'('sitdown','sitdown','src_span'(11,9,11,16,262,7),'Channel').
'symbol'('getup','getup','src_span'(12,9,12,14,285,5),'Channel').
'symbol'('inc','inc','src_span'(16,1,16,4,341,3),'Funktion or Process').
'symbol'('x','x','src_span'(16,5,16,6,345,1),'Ident (Prolog Variable)').
'symbol'('dec','dec','src_span'(17,1,17,4,362,3),'Funktion or Process').
'symbol'('x2','x','src_span'(17,5,17,6,366,1),'Ident (Prolog Variable)').
'symbol'('PHIL','PHIL','src_span'(22,1,22,5,467,4),'Funktion or Process').
'symbol'('i','i','src_span'(22,6,22,7,472,1),'Ident (Prolog Variable)').
'symbol'('FORK','FORK','src_span'(25,1,25,5,591,4),'Funktion or Process').
'symbol'('i2','i','src_span'(25,6,25,7,596,1),'Ident (Prolog Variable)').
'symbol'('PHILS','PHILS','src_span'(30,1,30,6,742,5),'Ident (Groundrep.)').
'symbol'('i3','i','src_span'(30,12,30,13,753,1),'Ident (Prolog Variable)').
'symbol'('FORKS','FORKS','src_span'(35,1,35,6,939,5),'Ident (Groundrep.)').
'symbol'('i4','i','src_span'(35,12,35,13,950,1),'Ident (Prolog Variable)').
'symbol'('COLLEGE','COLLEGE','src_span'(39,1,39,8,1082,7),'Ident (Groundrep.)').