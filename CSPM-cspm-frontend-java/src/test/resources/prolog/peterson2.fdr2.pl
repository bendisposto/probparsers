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
'channel'('flag1set','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2))),'closure'(['false','true'])]))).
'channel'('flag1read','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2))),'closure'(['false','true'])]))).
'channel'('flag2set','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2))),'closure'(['false','true'])]))).
'channel'('flag2read','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2))),'closure'(['false','true'])]))).
'channel'('turnset','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2))),'setExp'('rangeClosed'('int'(1),'int'(2)))]))).
'channel'('turnread','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2))),'setExp'('rangeClosed'('int'(1),'int'(2)))]))).
'channel'('enter','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2)))]))).
'channel'('critical','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2)))]))).
'channel'('leave','type'('dotTupleType'(['setExp'('rangeClosed'('int'(1),'int'(2)))]))).
'agent'('FLAG1'(_v),'[]'('[]'('prefix'('src_span'(10,13,10,21,254,8),['in'(_x),'in'(_y)],'flag1set','agent_call'('src_span'(10,29,10,34,270,5),'FLAG1',[_y]),'src_span'(10,26,10,28,266,14)),'prefix'('src_span'(11,13,11,26,291,13),[],'dotTuple'(['flag1read','int'(1),_v]),'agent_call'('src_span'(11,30,11,35,308,5),'FLAG1',[_v]),'src_span'(11,27,11,29,304,25)),'src_span_operator'('no_loc_info_available','src_span'(11,10,11,12,288,2))),'prefix'('src_span'(12,13,12,26,329,13),[],'dotTuple'(['flag1read','int'(2),_v]),'agent_call'('src_span'(12,30,12,35,346,5),'FLAG1',[_v]),'src_span'(12,27,12,29,342,25)),'src_span_operator'('no_loc_info_available','src_span'(12,10,12,12,326,2))),'no_loc_info_available').
'agent'('FLAG2'(_v2),'[]'('[]'('prefix'('src_span'(14,13,14,21,368,8),['in'(_x2),'in'(_y2)],'flag2set','agent_call'('src_span'(14,29,14,34,384,5),'FLAG2',[_y2]),'src_span'(14,26,14,28,380,14)),'prefix'('src_span'(15,13,15,26,405,13),[],'dotTuple'(['flag2read','int'(1),_v2]),'agent_call'('src_span'(15,30,15,35,422,5),'FLAG2',[_v2]),'src_span'(15,27,15,29,418,25)),'src_span_operator'('no_loc_info_available','src_span'(15,10,15,12,402,2))),'prefix'('src_span'(16,13,16,26,443,13),[],'dotTuple'(['flag2read','int'(2),_v2]),'agent_call'('src_span'(16,30,16,35,460,5),'FLAG2',[_v2]),'src_span'(16,27,16,29,456,25)),'src_span_operator'('no_loc_info_available','src_span'(16,10,16,12,440,2))),'no_loc_info_available').
'agent'('TURN'(_v3),'[]'('[]'('prefix'('src_span'(18,12,18,19,481,7),['in'(_x3),'in'(_y3)],'turnset','agent_call'('src_span'(18,27,18,31,496,4),'TURN',[_y3]),'src_span'(18,24,18,26,492,13)),'prefix'('src_span'(19,12,19,24,515,12),[],'dotTuple'(['turnread','int'(1),_v3]),'agent_call'('src_span'(19,28,19,32,531,4),'TURN',[_v3]),'src_span'(19,25,19,27,527,23)),'src_span_operator'('no_loc_info_available','src_span'(19,9,19,11,512,2))),'prefix'('src_span'(20,12,20,24,550,12),[],'dotTuple'(['turnread','int'(2),_v3]),'agent_call'('src_span'(20,28,20,32,566,4),'TURN',[_v3]),'src_span'(20,25,20,27,562,23)),'src_span_operator'('no_loc_info_available','src_span'(20,9,20,11,547,2))),'no_loc_info_available').
'bindval'('P1','prefix'('src_span'(22,6,22,21,580,15),[],'dotTuple'(['flag1set','int'(1),'true']),'prefix'('src_span'(22,25,22,36,599,11),[],'dotTuple'(['turnset','int'(1),'int'(2)]),'val_of'('P1WAIT','src_span'(22,40,22,46,614,6)),'src_span'(22,37,22,39,610,21)),'src_span'(22,22,22,24,595,40)),'src_span'(22,1,22,46,575,45)).
'bindval'('P1WAIT','[]'('prefix'('src_span'(24,11,24,27,632,16),[],'dotTuple'(['flag2read','int'(1),'true']),'[]'('prefix'('src_span'(24,32,24,44,653,12),[],'dotTuple'(['turnread','int'(1),'int'(2)]),'val_of'('P1WAIT','src_span'(24,48,24,54,669,6)),'src_span'(24,45,24,47,665,22)),'prefix'('src_span'(25,32,25,44,707,12),[],'dotTuple'(['turnread','int'(1),'int'(1)]),'val_of'('P1ENTER','src_span'(25,48,25,55,723,7)),'src_span'(25,45,25,47,719,23)),'src_span_operator'('no_loc_info_available','src_span'(25,29,25,31,704,2))),'src_span'(24,28,24,30,648,99)),'prefix'('src_span'(26,11,26,28,742,17),[],'dotTuple'(['flag2read','int'(1),'false']),'val_of'('P1ENTER','src_span'(26,32,26,39,763,7)),'src_span'(26,29,26,31,759,28)),'src_span_operator'('no_loc_info_available','src_span'(26,8,26,10,739,2))),'src_span'(24,1,26,39,622,148)).
'bindval'('P1ENTER','prefix'('src_span'(28,11,28,18,782,7),[],'dotTuple'(['enter','int'(1)]),'prefix'('src_span'(28,22,28,32,793,10),[],'dotTuple'(['critical','int'(1)]),'prefix'('src_span'(28,36,28,43,807,7),[],'dotTuple'(['leave','int'(1)]),'prefix'('src_span'(28,47,28,63,818,16),[],'dotTuple'(['flag1set','int'(1),'false']),'val_of'('P1','src_span'(28,67,28,69,838,2)),'src_span'(28,64,28,66,834,22)),'src_span'(28,44,28,46,814,33)),'src_span'(28,33,28,35,803,47)),'src_span'(28,19,28,21,789,58)),'src_span'(28,1,28,69,772,68)).
'bindval'('P2','prefix'('src_span'(30,6,30,21,847,15),[],'dotTuple'(['flag2set','int'(2),'true']),'prefix'('src_span'(30,25,30,36,866,11),[],'dotTuple'(['turnset','int'(2),'int'(1)]),'val_of'('P2WAIT','src_span'(30,40,30,46,881,6)),'src_span'(30,37,30,39,877,21)),'src_span'(30,22,30,24,862,40)),'src_span'(30,1,30,46,842,45)).
'bindval'('P2WAIT','[]'('prefix'('src_span'(32,11,32,27,899,16),[],'dotTuple'(['flag1read','int'(2),'true']),'[]'('prefix'('src_span'(32,32,32,44,920,12),[],'dotTuple'(['turnread','int'(2),'int'(1)]),'val_of'('P2WAIT','src_span'(32,48,32,54,936,6)),'src_span'(32,45,32,47,932,22)),'prefix'('src_span'(33,32,33,44,974,12),[],'dotTuple'(['turnread','int'(2),'int'(2)]),'val_of'('P2ENTER','src_span'(33,48,33,55,990,7)),'src_span'(33,45,33,47,986,23)),'src_span_operator'('no_loc_info_available','src_span'(33,29,33,31,971,2))),'src_span'(32,28,32,30,915,99)),'prefix'('src_span'(34,11,34,28,1009,17),[],'dotTuple'(['flag1read','int'(2),'false']),'val_of'('P2ENTER','src_span'(34,32,34,39,1030,7)),'src_span'(34,29,34,31,1026,28)),'src_span_operator'('no_loc_info_available','src_span'(34,8,34,10,1006,2))),'src_span'(32,1,34,39,889,148)).
'bindval'('P2ENTER','prefix'('src_span'(36,11,36,18,1049,7),[],'dotTuple'(['enter','int'(2)]),'prefix'('src_span'(36,22,36,32,1060,10),[],'dotTuple'(['critical','int'(2)]),'prefix'('src_span'(36,36,36,43,1074,7),[],'dotTuple'(['leave','int'(2)]),'prefix'('src_span'(36,47,36,63,1085,16),[],'dotTuple'(['flag2set','int'(2),'false']),'val_of'('P2','src_span'(36,67,36,69,1105,2)),'src_span'(36,64,36,66,1101,22)),'src_span'(36,44,36,46,1081,33)),'src_span'(36,33,36,35,1070,47)),'src_span'(36,19,36,21,1056,58)),'src_span'(36,1,36,69,1039,68)).
'bindval'('aP1','closure'(['dotTuple'(['flag1set','int'(1)]),'dotTuple'(['flag1read','int'(1)]),'dotTuple'(['flag2set','int'(1)]),'dotTuple'(['flag2read','int'(1)]),'dotTuple'(['turnset','int'(1)]),'dotTuple'(['turnread','int'(1)]),'dotTuple'(['enter','int'(1)]),'dotTuple'(['critical','int'(1)]),'dotTuple'(['leave','int'(1)])]),'src_span'(38,1,39,64,1109,122)).
'bindval'('aP2','closure'(['dotTuple'(['flag1set','int'(2)]),'dotTuple'(['flag1read','int'(2)]),'dotTuple'(['flag2set','int'(2)]),'dotTuple'(['flag2read','int'(2)]),'dotTuple'(['turnset','int'(2)]),'dotTuple'(['turnread','int'(2)]),'dotTuple'(['enter','int'(2)]),'dotTuple'(['critical','int'(2)]),'dotTuple'(['leave','int'(2)])]),'src_span'(41,1,42,64,1233,122)).
'bindval'('aF1','closure'(['flag1set','flag1read']),'src_span'(44,1,44,31,1357,30)).
'bindval'('aF2','closure'(['flag2set','flag2read']),'src_span'(46,1,46,31,1389,30)).
'bindval'('aT','closure'(['turnset','turnread']),'src_span'(48,1,48,29,1421,28)).
'bindval'('PROCS','aParallel'('val_of'('aP1','src_span'(50,14,50,17,1464,3)),'val_of'('P1','src_span'(50,9,50,11,1459,2)),'val_of'('aP2','src_span'(50,21,50,24,1471,3)),'val_of'('P2','src_span'(50,27,50,29,1477,2)),'src_span'(50,12,50,26,1462,14)),'src_span'(50,1,50,29,1451,28)).
'bindval'('FLAGS','aParallel'('val_of'('aF1','src_span'(52,24,52,27,1504,3)),'agent_call'('src_span'(52,9,52,14,1489,5),'FLAG1',['false']),'val_of'('aF2','src_span'(52,31,52,34,1511,3)),'agent_call'('src_span'(52,37,52,42,1517,5),'FLAG2',['false']),'src_span'(52,22,52,36,1502,14)),'src_span'(52,1,52,49,1481,48)).
'bindval'('VARS','aParallel'('agent_call'('src_span'(54,16,54,21,1546,5),'union',['val_of'('aF1','src_span'(54,22,54,25,1552,3)),'val_of'('aF2','src_span'(54,26,54,29,1556,3))]),'val_of'('FLAGS','src_span'(54,8,54,13,1538,5)),'val_of'('aT','src_span'(54,34,54,36,1564,2)),'agent_call'('src_span'(54,39,54,43,1569,4),'TURN',['int'(1)]),'src_span'(54,14,54,38,1544,24)),'src_span'(54,1,54,46,1531,45)).
'bindval'('SYSTEM','aParallel'('agent_call'('src_span'(56,18,56,23,1595,5),'union',['val_of'('aP1','src_span'(56,24,56,27,1601,3)),'val_of'('aP2','src_span'(56,28,56,31,1605,3))]),'val_of'('PROCS','src_span'(56,10,56,15,1587,5)),'agent_call'('src_span'(56,36,56,41,1613,5),'union',['agent_call'('src_span'(56,42,56,47,1619,5),'union',['val_of'('aF1','src_span'(56,48,56,51,1625,3)),'val_of'('aF2','src_span'(56,52,56,55,1629,3))]),'val_of'('aT','src_span'(56,57,56,59,1634,2))]),'val_of'('VARS','src_span'(56,63,56,67,1640,4)),'src_span'(56,16,56,62,1593,46)),'src_span'(56,1,56,67,1578,66)).
'comment'('lineComment'('-- Peterson\x27\s Algorithm in CSP: version 2'),'src_position'(1,1,0,41)).
'comment'('lineComment'('--'),'src_position'(2,1,42,2)).
'comment'('lineComment'('-- Simon Gay, Royal Holloway, January 1999'),'src_position'(3,1,45,42)).
'comment'('lineComment'('--'),'src_position'(4,1,88,2)).
'symbol'('flag1set','flag1set','src_span'(6,9,6,17,100,8),'Channel').
'symbol'('flag1read','flag1read','src_span'(6,19,6,28,110,9),'Channel').
'symbol'('flag2set','flag2set','src_span'(6,30,6,38,121,8),'Channel').
'symbol'('flag2read','flag2read','src_span'(6,40,6,49,131,9),'Channel').
'symbol'('turnset','turnset','src_span'(7,9,7,16,171,7),'Channel').
'symbol'('turnread','turnread','src_span'(7,18,7,26,180,8),'Channel').
'symbol'('enter','enter','src_span'(8,9,8,14,211,5),'Channel').
'symbol'('critical','critical','src_span'(8,16,8,24,218,8),'Channel').
'symbol'('leave','leave','src_span'(8,26,8,31,228,5),'Channel').
'symbol'('FLAG1','FLAG1','src_span'(10,1,10,6,242,5),'Funktion or Process').
'symbol'('v','v','src_span'(10,7,10,8,248,1),'Ident (Prolog Variable)').
'symbol'('x','x','src_span'(10,22,10,23,263,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(10,24,10,25,265,1),'Ident (Prolog Variable)').
'symbol'('FLAG2','FLAG2','src_span'(14,1,14,6,356,5),'Funktion or Process').
'symbol'('v2','v','src_span'(14,7,14,8,362,1),'Ident (Prolog Variable)').
'symbol'('x2','x','src_span'(14,22,14,23,377,1),'Ident (Prolog Variable)').
'symbol'('y2','y','src_span'(14,24,14,25,379,1),'Ident (Prolog Variable)').
'symbol'('TURN','TURN','src_span'(18,1,18,5,470,4),'Funktion or Process').
'symbol'('v3','v','src_span'(18,6,18,7,475,1),'Ident (Prolog Variable)').
'symbol'('x3','x','src_span'(18,20,18,21,489,1),'Ident (Prolog Variable)').
'symbol'('y3','y','src_span'(18,22,18,23,491,1),'Ident (Prolog Variable)').
'symbol'('P1','P1','src_span'(22,1,22,3,575,2),'Ident (Groundrep.)').
'symbol'('P1WAIT','P1WAIT','src_span'(24,1,24,7,622,6),'Ident (Groundrep.)').
'symbol'('P1ENTER','P1ENTER','src_span'(28,1,28,8,772,7),'Ident (Groundrep.)').
'symbol'('P2','P2','src_span'(30,1,30,3,842,2),'Ident (Groundrep.)').
'symbol'('P2WAIT','P2WAIT','src_span'(32,1,32,7,889,6),'Ident (Groundrep.)').
'symbol'('P2ENTER','P2ENTER','src_span'(36,1,36,8,1039,7),'Ident (Groundrep.)').
'symbol'('aP1','aP1','src_span'(38,1,38,4,1109,3),'Ident (Groundrep.)').
'symbol'('aP2','aP2','src_span'(41,1,41,4,1233,3),'Ident (Groundrep.)').
'symbol'('aF1','aF1','src_span'(44,1,44,4,1357,3),'Ident (Groundrep.)').
'symbol'('aF2','aF2','src_span'(46,1,46,4,1389,3),'Ident (Groundrep.)').
'symbol'('aT','aT','src_span'(48,1,48,3,1421,2),'Ident (Groundrep.)').
'symbol'('PROCS','PROCS','src_span'(50,1,50,6,1451,5),'Ident (Groundrep.)').
'symbol'('FLAGS','FLAGS','src_span'(52,1,52,6,1481,5),'Ident (Groundrep.)').
'symbol'('VARS','VARS','src_span'(54,1,54,5,1531,4),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(54,16,54,21,1546,5),'BuiltIn primitive').
'symbol'('SYSTEM','SYSTEM','src_span'(56,1,56,7,1578,6),'Ident (Groundrep.)').