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
'cspTransparent'(['diamond']).
'bindval'('n','int'(5),'src_span'(9,1,9,18,187,17)).
'bindval'('DISCS','setExp'('rangeClosed'('int'(1),'val_of'('n','src_span'(12,21,12,22,266,1)))),'src_span'(12,1,12,23,246,22)).
'dataTypeDef'('PEGS',['constructor'('A'),'constructor'('B'),'constructor'('C')]).
'channel'('get','type'('dotTupleType'(['val_of'('DISCS','src_span'(23,20,23,25,491,5))]))).
'channel'('put','type'('dotTupleType'(['val_of'('DISCS','src_span'(23,20,23,25,491,5))]))).
'channel'('full','type'('dotUnitType')).
'agent'('allowed'(_s),'setExp'('rangeClosed'('int'(1),'-'('agent_call'('src_span'(27,19,27,23,596,4),'head',['^'(_s,'listExp'('rangeEnum'(['+'('val_of'('n','src_span'(27,27,27,28,604,1)),'int'(1))])))]),'int'(1)))),'src_span'(27,14,27,36,591,22)).
'agent'('PEG'(_s2),'[]'('[]'('prefix'('src_span'(30,3,30,6,626,3),['inGuard'(_d,'agent_call'('src_span'(30,9,30,16,632,7),'allowed',[_s2]))],'get','agent_call'('src_span'(30,21,30,24,644,3),'PEG',['^'('listExp'('rangeEnum'([_d])),_s2)]),'src_span'(30,20,30,20,642,25)),'&'('bool_not'('agent_call'('src_span'(32,7,32,11,668,4),'null',[_s2])),'prefix'('src_span'(32,17,32,20,678,3),['out'('agent_call'('src_span'(32,21,32,25,682,4),'head',[_s2]))],'put','agent_call'('src_span'(32,30,32,33,691,3),'PEG',['agent_call'('src_span'(32,34,32,38,695,4),'tail',[_s2])]),'src_span'(32,29,32,29,689,22))),'src_span_operator'('no_loc_info_available','src_span'(31,5,31,7,659,2))),'&'('=='('agent_call'('src_span'(34,3,34,9,713,6),'length',[_s2]),'val_of'('n','src_span'(34,16,34,17,726,1))),'prefix'('src_span'(34,20,34,24,730,4),[],'full','agent_call'('src_span'(34,26,34,29,736,3),'PEG',[_s2]),'src_span'(34,25,34,25,734,12))),'src_span_operator'('no_loc_info_available','src_span'(33,5,33,7,708,2))),'no_loc_info_available').
'channel'('move','type'('dotTupleType'(['val_of'('DISCS','src_span'(43,16,43,21,940,5)),'PEGS','PEGS']))).
'channel'('complete','type'('dotTupleType'(['PEGS']))).
'agent'('initial'(_p),'ifte'('=='(_p,'A'),'listExp'('rangeClosed'('int'(1),'val_of'('n','src_span'(46,34,46,35,1014,1)))),'listExp'('rangeEnum'([])),'no_loc_info_available','no_loc_info_available','src_span'(46,37,46,41,1016,15)),'src_span'(46,16,46,44,996,28)).
'agent'('POLE'(_p2),'procRenamingComp'('agent_call'('src_span'(50,3,50,6,1039,3),'PEG',['agent_call'('src_span'(50,7,50,14,1043,7),'initial',[_p2])]),['comprehensionGenerator'(_i,'PEGS'),'comprehensionGuard'('!='(_i,_p2)),'comprehensionGenerator'(_d2,'val_of'('DISCS','src_span'(53,51,53,56,1161,5)))],['rename'('full','dotTuple'(['complete',_p2])),'rename'('dotTuple'(['get',_d2]),'dotTuple'(['move',_d2,_p2,_i])),'rename'('dotTuple'(['put',_d2]),'dotTuple'(['move',_d2,_i,_p2]))]),'src_span'(51,5,53,59,1059,110)).
'agent'('interface'(_p3),'closureComp'(['comprehensionGenerator'(_d3,'val_of'('DISCS','src_span'(60,59,60,64,1358,5))),'comprehensionGenerator'(_i2,'PEGS')],['dotTuple'(['move',_d3,_i2,_p3]),'dotTuple'(['move',_d3,_p3,_i2]),'dotTuple'(['complete',_p3])]),'src_span'(60,16,60,76,1315,60)).
'bindval'('PUZZLE','procRepAParallel'(['comprehensionGenerator'(_p4,'PEGS')],'pair'('agent_call'('src_span'(63,19,63,28,1404,9),'interface',[_p4]),'agent_call'('src_span'(63,34,63,41,1419,7),'diamond',['agent_call'('src_span'(63,42,63,46,1427,4),'POLE',[_p4])])),'src_span'(63,6,63,16,1391,10)),'src_span'(62,1,63,50,1377,58)).
'bindval'('NOTSOLVED','[]'('prefix'('src_span'(72,13,72,21,1695,8),['inGuard'(_x,'setExp'('rangeEnum'(['A','B'])))],'complete','val_of'('NOTSOLVED','src_span'(72,32,72,41,1714,9)),'src_span'(72,30,72,31,1711,20)),'prefix'('src_span'(72,45,72,49,1727,4),['in'(_x2),'in'(_y),'in'(_z)],'move','val_of'('NOTSOLVED','src_span'(72,59,72,68,1741,9)),'src_span'(72,56,72,58,1737,15)),'src_span_operator'('no_loc_info_available','src_span'(72,42,72,44,1724,2))),'src_span'(72,1,72,68,1683,67)).
'assertRef'('False','val_of'('NOTSOLVED','src_span'(74,8,74,17,1759,9)),'Trace','val_of'('PUZZLE','src_span'(74,22,74,28,1773,6)),'src_span'(74,1,74,28,1752,27)).
'assertRef'('False','\x5c\'('val_of'('PUZZLE','src_span'(75,8,75,14,1787,6)),'closure'(['dotTuple'(['complete','A']),'dotTuple'(['complete','B']),'move']),'src_span_operator'('no_loc_info_available','src_span'(75,15,75,16,1794,1))),'Failure','stop'('src_span'(75,56,75,60,1835,4)),'src_span'(75,1,75,60,1780,59)).
'comment'('blockComment'('{-\xa\  An version of the Towers of Hanoi using lots of features\xa\  which were not present in FDR 1.4\xa\  JBS 6 March 1995 (based loosely on AWR\x27\s version for FDR 1.4)\xa\-}'),'src_position'(1,1,0,164)).
'comment'('lineComment'('-- How many discs'),'src_position'(9,19,205,17)).
'comment'('lineComment'('-- Discs are numbered'),'src_position'(11,1,224,21)).
'comment'('lineComment'('-- But the pegs are labelled'),'src_position'(14,1,270,28)).
'comment'('blockComment'('{-\xa\  For a given peg, we can get a new disc or put the\xa\  top disc somewhere else.  We are also allowed to\xa\  to indicate when the peg is full.\xa\-}'),'src_position'(17,1,326,144)).
'comment'('lineComment'('-- We are allowed to put any *smaller* disc onto the current stack'),'src_position'(26,1,511,66)).
'comment'('blockComment'('{-\xa\  Now, given a simple peg we can rename it to form each\xa\  of the three physical pegs (`poles\x27\) of the puzzle.\xa\\xa\  move.d.i.j indicates that disc d moves to pole i from pole j\xa\-}'),'src_position'(36,1,744,179)).
'comment'('blockComment'('{-\xa\  The puzzle is just the three poles, communicating on the\xa\  relevant events: all the moves, and the done/notdone events.\xa\-}'),'src_position'(55,1,1171,127)).
'comment'('blockComment'('{-\xa\  The puzzle is solved by asserting that C cannot become complete.\xa\  then the trace that refutes the assertion is the solution.\xa\-}'),'src_position'(65,1,1437,133)).
'comment'('lineComment'('-- partial instantitiation of channel move '),'src_position'(70,1,1572,43)).
'comment'('lineComment'('-- NOTSOLVED = complete?x:{A,B}-> NOTSOLVED [] move?x -> NOTSOLVED'),'src_position'(71,1,1616,66)).
'symbol'('diamond','diamond','src_span'(7,13,7,20,178,7),'Transparent function').
'symbol'('n','n','src_span'(9,1,9,2,187,1),'Ident (Groundrep.)').
'symbol'('DISCS','DISCS','src_span'(12,1,12,6,246,5),'Ident (Groundrep.)').
'symbol'('PEGS','PEGS','src_span'(15,10,15,14,308,4),'Datatype').
'symbol'('A','A','src_span'(15,17,15,18,315,1),'Constructor of Datatype').
'symbol'('B','B','src_span'(15,21,15,22,319,1),'Constructor of Datatype').
'symbol'('C','C','src_span'(15,25,15,26,323,1),'Constructor of Datatype').
'symbol'('get','get','src_span'(23,9,23,12,480,3),'Channel').
'symbol'('put','put','src_span'(23,14,23,17,485,3),'Channel').
'symbol'('full','full','src_span'(24,9,24,13,505,4),'Channel').
'symbol'('allowed','allowed','src_span'(27,1,27,8,578,7),'Funktion or Process').
'symbol'('s','s','src_span'(27,9,27,10,586,1),'Ident (Prolog Variable)').
'symbol'('head','head','src_span'(27,19,27,23,596,4),'BuiltIn primitive').
'symbol'('PEG','PEG','src_span'(29,1,29,4,615,3),'Funktion or Process').
'symbol'('s2','s','src_span'(29,5,29,6,619,1),'Ident (Prolog Variable)').
'symbol'('d','d','src_span'(30,7,30,8,630,1),'Ident (Prolog Variable)').
'symbol'('null','null','src_span'(32,7,32,11,668,4),'BuiltIn primitive').
'symbol'('head','head','src_span'(32,21,32,25,682,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(32,34,32,38,695,4),'BuiltIn primitive').
'symbol'('length','length','src_span'(34,3,34,9,713,6),'BuiltIn primitive').
'symbol'('move','move','src_span'(43,9,43,13,933,4),'Channel').
'symbol'('complete','complete','src_span'(44,9,44,17,964,8),'Channel').
'symbol'('initial','initial','src_span'(46,1,46,8,981,7),'Funktion or Process').
'symbol'('p','p','src_span'(46,9,46,10,989,1),'Ident (Prolog Variable)').
'symbol'('POLE','POLE','src_span'(49,1,49,5,1027,4),'Funktion or Process').
'symbol'('p2','p','src_span'(49,6,49,7,1032,1),'Ident (Prolog Variable)').
'symbol'('i','i','src_span'(53,30,53,31,1140,1),'Ident (Prolog Variable)').
'symbol'('d2','d','src_span'(53,48,53,49,1158,1),'Ident (Prolog Variable)').
'symbol'('interface','interface','src_span'(60,1,60,10,1300,9),'Funktion or Process').
'symbol'('p3','p','src_span'(60,11,60,12,1310,1),'Ident (Prolog Variable)').
'symbol'('d3','d','src_span'(60,56,60,57,1355,1),'Ident (Prolog Variable)').
'symbol'('i2','i','src_span'(60,66,60,67,1365,1),'Ident (Prolog Variable)').
'symbol'('PUZZLE','PUZZLE','src_span'(62,1,62,7,1377,6),'Ident (Groundrep.)').
'symbol'('p4','p','src_span'(63,6,63,7,1391,1),'Ident (Prolog Variable)').
'symbol'('NOTSOLVED','NOTSOLVED','src_span'(72,1,72,10,1683,9),'Ident (Groundrep.)').
'symbol'('x','x','src_span'(72,22,72,23,1704,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(72,50,72,51,1732,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(72,52,72,53,1734,1),'Ident (Prolog Variable)').
'symbol'('z','z','src_span'(72,54,72,55,1736,1),'Ident (Prolog Variable)').