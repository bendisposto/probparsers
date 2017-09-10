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
'dataTypeDef'('square',['constructor'('E'),'constructor'('Y'),'constructor'('G')]).
'subTypeDef'('colour',['constructor'('Y'),'constructor'('G')]).
'bindval'('StartBoard','listExp'('rangeEnum'(['Y','Y','Y','E','G','G','G'])),'src_span'(4,1,4,35,52,34)).
'bindval'('EndBoard','listExp'('rangeEnum'(['G','G','G','E','Y','Y','Y'])),'src_span'(5,1,5,33,87,32)).
'agent'('board_el'(_i,_xs),'ifte'('=='(_i,'int'(0)),'agent_call'('src_span'(7,32,7,36,152,4),'head',[_xs]),'agent_call'('src_span'(7,46,7,54,166,8),'board_el',['-'(_i,'int'(1)),'agent_call'('src_span'(7,60,7,64,180,4),'tail',[_xs])]),'no_loc_info_available','no_loc_info_available','src_span'(7,41,7,45,160,37)),'src_span'(7,19,7,69,139,50)).
'bindval'('len','-'('#'('val_of'('StartBoard','src_span'(9,8,9,18,198,10))),'int'(1)),'src_span'(9,1,9,20,191,19)).
'bindval'('pos','setExp'('rangeClosed'('int'(-2),'+'('val_of'('len','src_span'(11,14,11,17,225,3)),'int'(2)))),'src_span'(11,1,11,20,212,19)).
'bindval'('realpos','setExp'('rangeClosed'('int'(0),'val_of'('len','src_span'(12,15,12,18,246,3)))),'src_span'(12,1,12,19,232,18)).
'channel'('move_left','type'('dotTupleType'(['val_of'('pos','src_span'(14,52,14,55,303,3)),'colour']))).
'channel'('move_right','type'('dotTupleType'(['val_of'('pos','src_span'(14,52,14,55,303,3)),'colour']))).
'channel'('hop_left','type'('dotTupleType'(['val_of'('pos','src_span'(14,52,14,55,303,3)),'colour']))).
'channel'('hop_right','type'('dotTupleType'(['val_of'('pos','src_span'(14,52,14,55,303,3)),'colour']))).
'channel'('done','type'('dotUnitType')).
'agent'('EMPTY'(_i2,_target),'[]'('[]'('[]'('[]'('&'('=='(_target,'E'),'prefix'('src_span'(17,32,17,36,359,4),[],'done','agent_call'('src_span'(17,40,17,45,367,5),'EMPTY',[_i2,_target]),'src_span'(17,37,17,39,363,24))),'prefix'('src_span'(18,6,18,17,389,11),['in'(_c)],'dotTuple'(['move_left',_i2]),'agent_call'('src_span'(18,23,18,27,406,4),'FULL',[_i2,_c,_target]),'src_span'(18,20,18,22,402,24)),'src_span_operator'('no_loc_info_available','src_span'(18,3,18,5,386,2))),'prefix'('src_span'(19,6,19,18,430,12),['in'(_c2)],'dotTuple'(['move_right',_i2]),'agent_call'('src_span'(19,24,19,28,448,4),'FULL',[_i2,_c2,_target]),'src_span'(19,21,19,23,444,24)),'src_span_operator'('no_loc_info_available','src_span'(19,3,19,5,427,2))),'prefix'('src_span'(20,6,20,16,472,10),['in'(_c3)],'dotTuple'(['hop_left',_i2]),'agent_call'('src_span'(20,22,20,26,488,4),'FULL',[_i2,_c3,_target]),'src_span'(20,19,20,21,484,24)),'src_span_operator'('no_loc_info_available','src_span'(20,3,20,5,469,2))),'prefix'('src_span'(21,6,21,17,512,11),['in'(_c4)],'dotTuple'(['hop_right',_i2]),'agent_call'('src_span'(21,23,21,27,529,4),'FULL',[_i2,_c4,_target]),'src_span'(21,20,21,22,525,24)),'src_span_operator'('no_loc_info_available','src_span'(21,3,21,5,509,2))),'no_loc_info_available').
'agent'('FULL'(_i3,_c5,_target2),'[]'('[]'('[]'('[]'('[]'('[]'('&'('=='(_target2,_c5),'prefix'('src_span'(23,34,23,38,582,4),[],'done','agent_call'('src_span'(23,42,23,46,590,4),'FULL',[_i3,_c5,_target2]),'src_span'(23,39,23,41,586,26))),'prefix'('src_span'(24,8,24,23,616,15),[],'dotTuple'(['move_left','-'(_i3,'int'(1)),_c5]),'agent_call'('src_span'(24,27,24,32,635,5),'EMPTY',[_i3,_target2]),'src_span'(24,24,24,26,631,35)),'src_span_operator'('no_loc_info_available','src_span'(24,5,24,7,613,2))),'prefix'('src_span'(25,8,25,24,659,16),[],'dotTuple'(['move_right','+'(_i3,'int'(1)),_c5]),'agent_call'('src_span'(25,28,25,33,679,5),'EMPTY',[_i3,_target2]),'src_span'(25,25,25,27,675,36)),'src_span_operator'('no_loc_info_available','src_span'(25,5,25,7,656,2))),'prefix'('src_span'(26,8,26,22,703,14),[],'dotTuple'(['hop_left','-'(_i3,'int'(2)),_c5]),'agent_call'('src_span'(26,26,26,31,721,5),'EMPTY',[_i3,_target2]),'src_span'(26,23,26,25,717,34)),'src_span_operator'('no_loc_info_available','src_span'(26,5,26,7,700,2))),'prefix'('src_span'(27,8,27,23,745,15),[],'dotTuple'(['hop_right','+'(_i3,'int'(2)),_c5]),'agent_call'('src_span'(27,27,27,32,764,5),'EMPTY',[_i3,_target2]),'src_span'(27,24,27,26,760,35)),'src_span_operator'('no_loc_info_available','src_span'(27,5,27,7,742,2))),'prefix'('src_span'(28,8,28,20,788,12),['in'(_x)],'dotTuple'(['hop_left','-'(_i3,'int'(1))]),'agent_call'('src_span'(28,26,28,30,806,4),'FULL',[_i3,_c5,_target2]),'src_span'(28,23,28,25,802,24)),'src_span_operator'('no_loc_info_available','src_span'(28,5,28,7,785,2))),'prefix'('src_span'(29,8,29,21,832,13),['in'(_x2)],'dotTuple'(['hop_right','+'(_i3,'int'(1))]),'agent_call'('src_span'(29,27,29,31,851,4),'FULL',[_i3,_c5,_target2]),'src_span'(29,24,29,26,847,24)),'src_span_operator'('no_loc_info_available','src_span'(29,5,29,7,829,2))),'no_loc_info_available').
'agent'('INIT'(_i4),'ifte'('=='('agent_call'('src_span'(31,14,31,22,884,8),'board_el',[_i4,'val_of'('StartBoard','src_span'(31,26,31,36,896,10))]),'E'),'agent_call'('src_span'(31,46,31,51,916,5),'EMPTY',[_i4,'agent_call'('src_span'(31,55,31,63,925,8),'board_el',[_i4,'val_of'('EndBoard','src_span'(31,67,31,75,937,8))])]),'ifte'('=='('agent_call'('src_span'(32,12,32,20,959,8),'board_el',[_i4,'val_of'('StartBoard','src_span'(32,24,32,34,971,10))]),'Y'),'agent_call'('src_span'(32,44,32,48,991,4),'FULL',[_i4,'Y','agent_call'('src_span'(32,55,32,63,1002,8),'board_el',[_i4,'val_of'('EndBoard','src_span'(32,67,32,75,1014,8))])]),'agent_call'('src_span'(33,14,33,18,1038,4),'FULL',[_i4,'G','agent_call'('src_span'(33,25,33,33,1049,8),'board_el',[_i4,'val_of'('EndBoard','src_span'(33,37,33,45,1061,8))])]),'no_loc_info_available','no_loc_info_available','src_span'(32,78,33,13,1024,80)),'no_loc_info_available','no_loc_info_available','src_span'(31,78,32,8,947,155)),'src_span'(31,11,33,47,881,190)).
'agent'('Alpha'(_i5),'agent_call'('src_span'(35,12,35,17,1084,5),'Union',['setExp'('rangeEnum'(['setExp'('rangeEnum'(['done'])),'closureComp'(['comprehensionGenerator'(_k,'setExp'('rangeEnum'(['int'(0),'int'(1)])))],['dotTuple'(['move_left','-'(_i5,_k)])]),'closureComp'(['comprehensionGenerator'(_k2,'setExp'('rangeEnum'(['int'(0),'int'(1)])))],['dotTuple'(['move_right','+'(_i5,_k2)])]),'closureComp'(['comprehensionGenerator'(_k3,'setExp'('rangeEnum'(['int'(0),'int'(1),'int'(2)])))],['dotTuple'(['hop_left','-'(_i5,_k3)])]),'closureComp'(['comprehensionGenerator'(_k4,'setExp'('rangeEnum'(['int'(0),'int'(1),'int'(2)])))],['dotTuple'(['hop_right','+'(_i5,_k4)])])]))]),'src_span'(35,12,39,43,1084,177)).
'bindval'('COMPLETEBOARD','procRepAParallel'(['comprehensionGenerator'(_i6,'val_of'('realpos','src_span'(41,22,41,29,1284,7)))],'pair'('agent_call'('src_span'(41,33,41,38,1295,5),'Alpha',[_i6]),'agent_call'('src_span'(41,43,41,47,1305,4),'INIT',[_i6])),'src_span'(41,20,41,31,1282,11)),'src_span'(41,1,41,50,1263,49)).
'bindval'('OnBoardPlay','repChoice'(['comprehensionGenerator'(_x3,'val_of'('realpos','src_span'(43,20,43,27,1333,7)))],'[]'('[]'('[]'('prefix'('src_span'(44,8,44,19,1350,11),['in'(_c6)],'dotTuple'(['move_left',_x3]),'val_of'('OnBoardPlay','src_span'(44,25,44,36,1367,11)),'src_span'(44,22,44,24,1363,17)),'prefix'('src_span'(45,22,45,34,1400,12),['in'(_c7)],'dotTuple'(['move_right',_x3]),'val_of'('OnBoardPlay','src_span'(45,40,45,51,1418,11)),'src_span'(45,37,45,39,1414,17)),'src_span_operator'('no_loc_info_available','src_span'(45,19,45,21,1397,2))),'prefix'('src_span'(46,22,46,32,1451,10),['in'(_c8)],'dotTuple'(['hop_left',_x3]),'val_of'('OnBoardPlay','src_span'(46,38,46,49,1467,11)),'src_span'(46,35,46,37,1463,17)),'src_span_operator'('no_loc_info_available','src_span'(46,19,46,21,1448,2))),'prefix'('src_span'(47,22,47,33,1500,11),['in'(_c9)],'dotTuple'(['hop_right',_x3]),'val_of'('OnBoardPlay','src_span'(47,39,47,50,1517,11)),'src_span'(47,36,47,38,1513,17)),'src_span_operator'('no_loc_info_available','src_span'(47,19,47,21,1497,2))),'src_span'(43,18,43,29,1331,11)),'src_span'(43,1,47,52,1314,216)).
'bindval'('AlphaBoard','closure'(['move_left','move_right','hop_left','hop_right']),'src_span'(49,1,49,62,1532,61)).
'bindval'('Ac','agent_call'('src_span'(51,6,51,11,1600,5),'Union',['setExp'('rangeEnum'(['agent_call'('src_span'(51,13,51,18,1607,5),'Alpha',[_i7])]),['comprehensionGenerator'(_i7,'val_of'('realpos','src_span'(51,29,51,36,1623,7)))])]),'src_span'(51,1,51,38,1595,37)).
'bindval'('invalidmoves','agent_call'('src_span'(53,16,53,20,1649,4),'diff',['val_of'('pos','src_span'(53,21,53,24,1654,3)),'val_of'('realpos','src_span'(53,26,53,33,1659,7))]),'src_span'(53,1,53,34,1634,33)).
'bindval'('OffBoardMoves','agent_call'('src_span'(54,17,54,22,1684,5),'Union',['setExp'('rangeEnum'(['closureComp'(['comprehensionGenerator'(_i8,'val_of'('invalidmoves','src_span'(54,46,54,58,1713,12)))],['dotTuple'(['move_left',_i8])]),'closureComp'(['comprehensionGenerator'(_i9,'val_of'('invalidmoves','src_span'(55,33,55,45,1762,12)))],['dotTuple'(['move_right',_i9])]),'closureComp'(['comprehensionGenerator'(_i65,'val_of'('invalidmoves','src_span'(56,31,56,43,1809,12)))],['dotTuple'(['hop_left',_i65])]),'closureComp'(['comprehensionGenerator'(_i66,'val_of'('invalidmoves','src_span'(57,32,57,44,1857,12)))],['dotTuple'(['hop_right',_i66])])]))]),'src_span'(54,1,57,49,1668,206)).
'bindval'('PUZZLE','aParallel'('val_of'('Ac','src_span'(59,26,59,28,1901,2)),'val_of'('COMPLETEBOARD','src_span'(59,10,59,23,1885,13)),'val_of'('AlphaBoard','src_span'(59,32,59,42,1907,10)),'val_of'('OnBoardPlay','src_span'(59,45,59,56,1920,11)),'src_span'(59,24,59,44,1899,20)),'src_span'(59,1,59,56,1876,55)).
'assertRef'('False','stop'('src_span'(61,8,61,12,1940,4)),'Trace','\x5c\'('val_of'('PUZZLE','src_span'(61,17,61,23,1949,6)),'closure'(['move_left','move_right','hop_left','hop_right']),'src_span_operator'('no_loc_info_available','src_span'(61,24,61,25,1956,1))),'src_span'(61,1,61,74,1933,73)).
'symbol'('square','square','src_span'(1,10,1,16,9,6),'Datatype').
'symbol'('E','E','src_span'(1,19,1,20,18,1),'Constructor of Datatype').
'symbol'('Y','Y','src_span'(1,23,1,24,22,1),'Constructor of Datatype').
'symbol'('G','G','src_span'(1,27,1,28,26,1),'Constructor of Datatype').
'symbol'('colour','colour','src_span'(2,9,2,15,36,6),'Datatype').
'symbol'('StartBoard','StartBoard','src_span'(4,1,4,11,52,10),'Ident (Groundrep.)').
'symbol'('EndBoard','EndBoard','src_span'(5,1,5,9,87,8),'Ident (Groundrep.)').
'symbol'('board_el','board_el','src_span'(7,1,7,9,121,8),'Funktion or Process').
'symbol'('i','i','src_span'(7,10,7,11,130,1),'Ident (Prolog Variable)').
'symbol'('xs','xs','src_span'(7,13,7,15,133,2),'Ident (Prolog Variable)').
'symbol'('head','head','src_span'(7,32,7,36,152,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(7,60,7,64,180,4),'BuiltIn primitive').
'symbol'('len','len','src_span'(9,1,9,4,191,3),'Ident (Groundrep.)').
'symbol'('pos','pos','src_span'(11,1,11,4,212,3),'Ident (Groundrep.)').
'symbol'('realpos','realpos','src_span'(12,1,12,8,232,7),'Ident (Groundrep.)').
'symbol'('move_left','move_left','src_span'(14,9,14,18,260,9),'Channel').
'symbol'('move_right','move_right','src_span'(14,20,14,30,271,10),'Channel').
'symbol'('hop_left','hop_left','src_span'(14,32,14,40,283,8),'Channel').
'symbol'('hop_right','hop_right','src_span'(14,42,14,51,293,9),'Channel').
'symbol'('done','done','src_span'(15,9,15,13,322,4),'Channel').
'symbol'('EMPTY','EMPTY','src_span'(17,1,17,6,328,5),'Funktion or Process').
'symbol'('i2','i','src_span'(17,7,17,8,334,1),'Ident (Prolog Variable)').
'symbol'('target','target','src_span'(17,10,17,16,337,6),'Ident (Prolog Variable)').
'symbol'('c','c','src_span'(18,18,18,19,401,1),'Ident (Prolog Variable)').
'symbol'('c2','c','src_span'(19,19,19,20,443,1),'Ident (Prolog Variable)').
'symbol'('c3','c','src_span'(20,17,20,18,483,1),'Ident (Prolog Variable)').
'symbol'('c4','c','src_span'(21,18,21,19,524,1),'Ident (Prolog Variable)').
'symbol'('FULL','FULL','src_span'(23,1,23,5,549,4),'Funktion or Process').
'symbol'('i3','i','src_span'(23,6,23,7,554,1),'Ident (Prolog Variable)').
'symbol'('c5','c','src_span'(23,9,23,10,557,1),'Ident (Prolog Variable)').
'symbol'('target2','target','src_span'(23,12,23,18,560,6),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(28,21,28,22,801,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(29,22,29,23,846,1),'Ident (Prolog Variable)').
'symbol'('INIT','INIT','src_span'(31,1,31,5,871,4),'Funktion or Process').
'symbol'('i4','i','src_span'(31,6,31,7,876,1),'Ident (Prolog Variable)').
'symbol'('Alpha','Alpha','src_span'(35,1,35,6,1073,5),'Funktion or Process').
'symbol'('i5','i','src_span'(35,7,35,8,1079,1),'Ident (Prolog Variable)').
'symbol'('Union','Union','src_span'(35,12,35,17,1084,5),'BuiltIn primitive').
'symbol'('k','k','src_span'(36,24,36,25,1122,1),'Ident (Prolog Variable)').
'symbol'('k2','k','src_span'(37,25,37,26,1162,1),'Ident (Prolog Variable)').
'symbol'('k3','k','src_span'(38,23,38,24,1200,1),'Ident (Prolog Variable)').
'symbol'('k4','k','src_span'(39,24,39,25,1242,1),'Ident (Prolog Variable)').
'symbol'('COMPLETEBOARD','COMPLETEBOARD','src_span'(41,1,41,14,1263,13),'Ident (Groundrep.)').
'symbol'('i6','i','src_span'(41,20,41,21,1282,1),'Ident (Prolog Variable)').
'symbol'('OnBoardPlay','OnBoardPlay','src_span'(43,1,43,12,1314,11),'Ident (Groundrep.)').
'symbol'('x3','x','src_span'(43,18,43,19,1331,1),'Ident (Prolog Variable)').
'symbol'('c6','c','src_span'(44,20,44,21,1362,1),'Ident (Prolog Variable)').
'symbol'('c7','c','src_span'(45,35,45,36,1413,1),'Ident (Prolog Variable)').
'symbol'('c8','c','src_span'(46,33,46,34,1462,1),'Ident (Prolog Variable)').
'symbol'('c9','c','src_span'(47,34,47,35,1512,1),'Ident (Prolog Variable)').
'symbol'('AlphaBoard','AlphaBoard','src_span'(49,1,49,11,1532,10),'Ident (Groundrep.)').
'symbol'('Ac','Ac','src_span'(51,1,51,3,1595,2),'Ident (Groundrep.)').
'symbol'('Union','Union','src_span'(51,6,51,11,1600,5),'BuiltIn primitive').
'symbol'('i7','i','src_span'(51,24,51,25,1618,1),'Ident (Prolog Variable)').
'symbol'('invalidmoves','invalidmoves','src_span'(53,1,53,13,1634,12),'Ident (Groundrep.)').
'symbol'('diff','diff','src_span'(53,16,53,20,1649,4),'BuiltIn primitive').
'symbol'('OffBoardMoves','OffBoardMoves','src_span'(54,1,54,14,1668,13),'Ident (Groundrep.)').
'symbol'('i8','i','src_span'(54,41,54,42,1708,1),'Ident (Prolog Variable)').
'symbol'('i9','i','src_span'(55,28,55,29,1757,1),'Ident (Prolog Variable)').
'symbol'('i65','i','src_span'(56,26,56,27,1804,1),'Ident (Prolog Variable)').
'symbol'('i66','i','src_span'(57,27,57,28,1852,1),'Ident (Prolog Variable)').
'symbol'('PUZZLE','PUZZLE','src_span'(59,1,59,7,1876,6),'Ident (Groundrep.)').