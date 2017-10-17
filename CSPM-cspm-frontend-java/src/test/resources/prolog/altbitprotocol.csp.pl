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
'bindval'('ChData','setExp'('rangeEnum'(['int'(0),'int'(1),'int'(2),'int'(3)])),'src_span'(1,1,1,19,0,18)).
'bindval'('ChControl','setExp'('rangeEnum'(['int'(0),'int'(1),'int'(2)])),'src_span'(2,1,2,20,19,19)).
'channel'('in','type'('dotTupleType'(['val_of'('ChData','src_span'(4,18,4,24,57,6))]))).
'channel'('outb','type'('dotTupleType'(['val_of'('ChData','src_span'(4,18,4,24,57,6))]))).
'channel'('out','type'('dotTupleType'(['val_of'('ChData','src_span'(5,18,5,24,82,6)),'val_of'('ChControl','src_span'(5,25,5,34,89,9))]))).
'channel'('inb','type'('dotTupleType'(['val_of'('ChData','src_span'(5,18,5,24,82,6)),'val_of'('ChControl','src_span'(5,25,5,34,89,9))]))).
'channel'('ok','type'('dotUnitType')).
'channel'('err','type'('dotUnitType')).
'agent'('StartA'(_x),'agent_call'('src_span'(8,13,8,16,127,3),'AS1',['%'('+'(_x,'int'(1)),'int'(4))]),'src_span'(8,13,8,25,127,12)).
'agent'('AS1'(_x2),'prefix'('src_span'(9,10,9,13,149,3),['out'(_x2),'out'('int'(1))],'out','agent_call'('src_span'(9,21,9,24,160,3),'AS2',[_x2]),'src_span'(9,18,9,20,156,12)),'src_span'(9,10,9,27,149,17)).
'agent'('AS2'(_x3),'prefix'('src_span'(11,10,11,12,177,2),['in'(_vr)],'in','ifte'('=='(_vr,'int'(1)),'agent_call'('src_span'(12,31,12,37,217,6),'StartA',[_x3]),'ifte'('=='(_vr,'int'(0)),'agent_call'('src_span'(13,29,13,32,255,3),'AS3',[_x3]),'agent_call'('src_span'(14,21,14,24,282,3),'AS5',[_x3]),'src_span'(13,13,13,23,239,10),'src_span'(13,24,13,28,249,19),'src_span'(13,36,14,20,261,33)),'src_span'(12,14,12,24,200,10),'src_span'(12,25,12,30,210,23),'src_span'(12,41,13,11,226,72)),'src_span'(11,16,12,13,182,110)),'src_span'(11,10,14,28,177,112)).
'agent'('AS3'(_x4),'prefix'('src_span'(15,10,15,13,300,3),['out'(_x4),'out'('int'(1))],'out','agent_call'('src_span'(15,21,15,24,311,3),'AS2',[_x4]),'src_span'(15,18,15,20,307,12)),'src_span'(15,10,15,27,300,17)).
'agent'('AS4'(_x5),'prefix'('src_span'(17,10,17,12,328,2),['in'(_vr2)],'in','ifte'('=='(_vr2,'int'(1)),'agent_call'('src_span'(18,31,18,37,368,6),'StartA',[_x5]),'ifte'('=='(_vr2,'int'(0)),'agent_call'('src_span'(19,28,19,34,405,6),'StartA',[_x5]),'agent_call'('src_span'(20,17,20,20,431,3),'AS5',[_x5]),'src_span'(19,12,19,22,389,10),'src_span'(19,23,19,27,399,22),'src_span'(19,38,20,16,414,32)),'src_span'(18,14,18,24,351,10),'src_span'(18,25,18,30,361,23),'src_span'(18,41,19,11,377,69)),'src_span'(17,16,18,13,333,107)),'src_span'(17,10,20,23,328,109)).
'agent'('AS5'(_x6),'prefix'('src_span'(21,10,21,13,457,3),['out'(_x6),'out'('int'(0))],'out','agent_call'('src_span'(21,21,21,24,468,3),'AS4',[_x6]),'src_span'(21,18,21,20,464,12)),'src_span'(21,10,21,27,457,17)).
'agent'('Breceived'(_x7,_y),'ifte'('=='(_x7,_y),'prefix'('src_span'(25,22,25,24,530,2),[],'ok','agent_call'('src_span'(25,28,25,31,536,3),'BS1',['%'('+'(_y,'int'(1)),'int'(4))]),'src_span'(25,25,25,27,532,18)),'prefix'('src_span'(27,8,27,11,578,3),[],'err','agent_call'('src_span'(27,15,27,18,585,3),'BS1',['%'('+'(_y,'int'(1)),'int'(4))]),'src_span'(27,12,27,14,581,19)),'src_span'(24,18,24,27,494,9),'src_span'(24,28,25,21,503,51),'src_span'(25,41,27,7,548,67)),'src_span'(24,18,27,27,494,103)).
'agent'('BS1'(_y2),'prefix'('src_span'(29,10,29,14,608,4),['out'('int'(1))],'outb','agent_call'('src_span'(29,20,29,23,618,3),'BS2',[_y2]),'src_span'(29,17,29,19,614,12)),'src_span'(29,10,29,26,608,16)).
'agent'('BS2'(_y3),'prefix'('src_span'(31,10,31,13,635,3),['in'(_x8),'in'(_ar)],'inb','ifte'('=='(_ar,'int'(1)),'agent_call'('src_span'(32,29,32,38,675,9),'Breceived',[_x8,_y3]),'ifte'('=='(_ar,'int'(0)),'agent_call'('src_span'(33,26,33,29,715,3),'BS3',[_y3]),'agent_call'('src_span'(34,17,34,20,738,3),'BS5',[_y3]),'no_loc_info_available','no_loc_info_available','src_span'(33,33,34,16,721,29)),'no_loc_info_available','no_loc_info_available','src_span'(32,44,33,11,689,69)),'src_span'(31,19,32,13,643,104)),'src_span'(31,10,34,23,635,109)).
'agent'('BS3'(_y4),'prefix'('src_span'(36,10,36,14,765,4),['out'('int'(1))],'outb','agent_call'('src_span'(36,20,36,23,775,3),'BS2',[_y4]),'src_span'(36,17,36,19,771,12)),'src_span'(36,10,36,26,765,16)).
'agent'('BS4'(_y5),'prefix'('src_span'(38,10,38,13,792,3),['in'(_x9),'in'(_ar2)],'inb','ifte'('=='(_ar2,'int'(1)),'agent_call'('src_span'(39,29,39,38,833,9),'Breceived',[_x9,_y5]),'ifte'('=='(_ar2,'int'(0)),'agent_call'('src_span'(40,26,40,35,873,9),'Breceived',[_x9,_y5]),'agent_call'('src_span'(41,17,41,20,904,3),'BS5',[_x9]),'no_loc_info_available','no_loc_info_available','src_span'(40,41,41,16,887,37)),'no_loc_info_available','no_loc_info_available','src_span'(39,44,40,11,847,77)),'src_span'(38,19,39,13,800,113)),'src_span'(38,10,41,23,792,118)).
'agent'('BS5'(_y6),'prefix'('src_span'(42,10,42,14,930,4),['out'('int'(0))],'outb','agent_call'('src_span'(42,20,42,23,940,3),'BS4',[_y6]),'src_span'(42,17,42,19,936,12)),'src_span'(42,10,42,26,930,16)).
'agent'('Buffer'(_cin,_cout),'prefix'('src_span'(44,20,44,23,967,3),['in'(_x80)],_cin,'prefix'('src_span'(44,30,44,34,977,4),['out'(_x80)],_cout,'agent_call'('src_span'(44,40,44,46,987,6),'Buffer',[_cin,_cout]),'src_span'(44,37,44,39,983,22)),'src_span'(44,26,44,28,972,34)),'src_span'(44,20,44,57,967,37)).
'agent'('Buffer2'(_cin2,_cout2),'prefix'('src_span'(45,21,45,24,1025,3),['in'(_x83),'in'(_y7)],_cin2,'prefix'('src_span'(45,32,45,36,1036,4),['out'(_x83),'out'(_y7)],_cout2,'agent_call'('src_span'(45,44,45,51,1048,7),'Buffer2',[_cin2,_cout2]),'src_span'(45,41,45,43,1044,23)),'src_span'(45,29,45,31,1032,35)),'src_span'(45,21,45,61,1025,40)).
'agent'('Buff2'(_cin3,_cout3),'prefix'('src_span'(47,19,47,22,1085,3),['in'(_x87)],_cin3,'agent_call'('src_span'(47,28,47,34,1094,6),'Buff2m',[_cin3,_cout3,_x87]),'src_span'(47,25,47,27,1090,24)),'src_span'(47,19,47,46,1085,27)).
'agent'('Buff2m'(_cin4,_cout4,_x90),'[]'('prefix'('src_span'(48,23,48,27,1135,4),['out'(_x90)],_cout4,'agent_call'('src_span'(48,33,48,38,1145,5),'Buff2',[_cin4,_cout4]),'src_span'(48,30,48,32,1141,21)),'prefix'('src_span'(49,24,49,27,1185,3),['in'(_y8)],_cin4,'prefix'('src_span'(49,33,49,37,1194,4),['out'(_x90)],_cout4,'agent_call'('src_span'(49,43,49,49,1204,6),'Buff2m',[_cin4,_cout4,_y8]),'src_span'(49,40,49,42,1200,24)),'src_span'(49,30,49,32,1190,34)),'src_span_operator'('no_loc_info_available','src_span'(49,20,49,22,1181,2))),'no_loc_info_available').
'agent'('Buff22'(_cin5,_cout5),'prefix'('src_span'(50,20,50,23,1243,3),['in'(_x94),'in'(_y9)],_cin5,'agent_call'('src_span'(50,31,50,38,1254,7),'Buff22m',[_cin5,_cout5,_x94,_y9]),'src_span'(50,28,50,30,1250,27)),'src_span'(50,20,50,52,1243,32)).
'agent'('Buff22m'(_cin6,_cout6,_x98,_y99),'[]'('prefix'('src_span'(51,26,51,30,1301,4),['out'(_x98),'out'(_y99)],_cout6,'agent_call'('src_span'(51,38,51,44,1313,6),'Buff22',[_cin6,_cout6]),'src_span'(51,35,51,37,1309,22)),'prefix'('src_span'(52,24,52,27,1354,3),['in'(_u),'in'(_v)],_cin6,'prefix'('src_span'(52,35,52,39,1365,4),['out'(_x98),'out'(_y99)],_cout6,'agent_call'('src_span'(52,47,52,54,1377,7),'Buff22m',[_cin6,_cout6,_u,_v]),'src_span'(52,44,52,46,1373,27)),'src_span'(52,32,52,34,1361,39)),'src_span_operator'('no_loc_info_available','src_span'(52,20,52,22,1350,2))),'no_loc_info_available').
'bindval'('System','sharing'('closure'(['inb','in']),'\x5c\'('sharing'('closure'(['out']),'agent_call'('src_span'(55,13,55,19,1414,6),'StartA',['int'(0)]),'agent_call'('src_span'(55,39,55,45,1440,6),'Buff22',['out','inb']),'src_span'(55,23,55,38,1424,15)),'closure'(['out']),'src_span_operator'('no_loc_info_available','src_span'(55,56,55,57,1457,1))),'\x5c\'('sharing'('closure'(['outb']),'agent_call'('src_span'(57,13,57,16,1513,3),'BS2',['int'(1)]),'agent_call'('src_span'(57,37,57,42,1537,5),'Buff2',['outb','in']),'src_span'(57,20,57,36,1520,16)),'closure'(['outb']),'src_span_operator'('no_loc_info_available','src_span'(57,53,57,54,1553,1))),'src_span'(56,14,56,34,1480,20)),'src_span'(55,1,57,64,1402,162)).
'channel'('success','type'('dotUnitType')).
'channel'('deadlock','type'('dotUnitType')).
'channel'('ko','type'('dotUnitType')).
'bindval'('TESTER','val_of'('State1','src_span'(63,10,63,16,1714,6)),'src_span'(63,1,63,16,1705,15)).
'bindval'('State1','|~|'('[]'('[]'('[]'('prefix'('src_span'(64,12,64,14,1732,2),['in'(_x102)],'in','val_of'('State1','src_span'(64,18,64,24,1738,6)),'src_span'(64,17,64,17,1736,10)),'prefix'('src_span'(64,28,64,30,1748,2),[],'ok','val_of'('State1','src_span'(64,32,64,38,1752,6)),'src_span'(64,31,64,31,1750,10)),'src_span_operator'('no_loc_info_available','src_span'(64,25,64,27,1745,2))),'prefix'('src_span'(64,42,64,45,1762,3),[],'err','val_of'('State1','src_span'(64,47,64,53,1767,6)),'src_span'(64,46,64,46,1765,11)),'src_span_operator'('no_loc_info_available','src_span'(64,39,64,41,1759,2))),'prefix'('src_span'(64,57,64,60,1777,3),['in'(_x103),'in'(_y104)],'inb','val_of'('State1','src_span'(64,66,64,72,1786,6)),'src_span'(64,65,64,65,1784,10)),'src_span_operator'('no_loc_info_available','src_span'(64,54,64,56,1774,2))),'val_of'('State2','src_span'(65,17,65,23,1810,6)),'src_span_operator'('no_loc_info_available','src_span'(65,12,65,15,1805,3))),'src_span'(64,1,65,24,1721,96)).
'bindval'('State2','[]'('prefix'('src_span'(66,12,66,19,1829,7),[],'success','[]'('[]'('prefix'('src_span'(66,22,66,24,1839,2),['in'(_x105)],'in','val_of'('State2','src_span'(66,28,66,34,1845,6)),'src_span'(66,27,66,27,1843,10)),'prefix'('src_span'(66,38,66,41,1855,3),[],'err','val_of'('State2','src_span'(66,43,66,49,1860,6)),'src_span'(66,42,66,42,1858,11)),'src_span_operator'('no_loc_info_available','src_span'(66,35,66,37,1852,2))),'prefix'('src_span'(66,53,66,56,1870,3),['in'(_x106),'in'(_y107)],'inb','val_of'('State2','src_span'(66,62,66,68,1879,6)),'src_span'(66,61,66,61,1877,10)),'src_span_operator'('no_loc_info_available','src_span'(66,50,66,52,1867,2))),'src_span'(66,20,66,20,1836,57)),'prefix'('src_span'(67,14,67,22,1901,8),[],'deadlock','val_of'('Deadlock','src_span'(67,26,67,34,1913,8)),'src_span'(67,23,67,25,1909,20)),'src_span_operator'('no_loc_info_available','src_span'(67,11,67,13,1898,2))),'src_span'(66,1,67,34,1818,103)).
'bindval'('Deadlock','[]'('[]'('[]'('prefix'('src_span'(69,14,69,16,1936,2),['in'(_x108)],'in','prefix'('src_span'(69,20,69,22,1942,2),[],'ko','stop'('src_span'(69,24,69,28,1946,4)),'src_span'(69,23,69,23,1944,8)),'src_span'(69,19,69,19,1940,12)),'prefix'('src_span'(69,32,69,34,1954,2),[],'ok','prefix'('src_span'(69,36,69,38,1958,2),[],'ko','stop'('src_span'(69,40,69,44,1962,4)),'src_span'(69,39,69,39,1960,8)),'src_span'(69,35,69,35,1956,12)),'src_span_operator'('no_loc_info_available','src_span'(69,29,69,31,1951,2))),'prefix'('src_span'(69,48,69,51,1970,3),[],'err','prefix'('src_span'(69,53,69,55,1975,2),[],'ko','stop'('src_span'(69,57,69,61,1979,4)),'src_span'(69,56,69,56,1977,8)),'src_span'(69,52,69,52,1973,13)),'src_span_operator'('no_loc_info_available','src_span'(69,45,69,47,1967,2))),'prefix'('src_span'(69,65,69,68,1987,3),['in'(_x109),'in'(_y110)],'inb','prefix'('src_span'(69,74,69,76,1996,2),[],'ko','stop'('src_span'(69,78,69,82,2000,4)),'src_span'(69,77,69,77,1998,8)),'src_span'(69,73,69,73,1994,12)),'src_span_operator'('no_loc_info_available','src_span'(69,62,69,64,1984,2))),'src_span'(69,1,69,83,1923,82)).
'bindval'('Composition','\x5c\'('sharing'('closure'(['in','ok','err','inb']),'val_of'('System','src_span'(72,16,72,22,2055,6)),'val_of'('TESTER','src_span'(72,47,72,53,2086,6)),'src_span'(72,23,72,46,2062,23)),'closure'(['in','ok','err','inb','deadlock','ko']),'src_span_operator'('no_loc_info_available','src_span'(73,21,73,22,2115,1))),'src_span'(72,1,73,51,2040,105)).
'bindval'('DComposition','\x5c\'('sharing'('closure'(['in','ok','err','inb']),'val_of'('System','src_span'(74,17,74,23,2162,6)),'val_of'('TESTER','src_span'(74,48,74,54,2193,6)),'src_span'(74,24,74,47,2169,23)),'closure'(['in','ok','err','inb','success']),'src_span_operator'('no_loc_info_available','src_span'(75,21,75,22,2222,1))),'src_span'(74,1,75,47,2146,102)).
'bindval'('SUC','prefix'('src_span'(78,7,78,14,2295,7),[],'success','val_of'('SUC','src_span'(78,16,78,19,2304,3)),'src_span'(78,15,78,15,2302,12)),'src_span'(78,1,78,19,2289,18)).
'assertRef'('False','val_of'('Composition','src_span'(79,8,79,19,2315,11)),'Trace','val_of'('SUC','src_span'(79,24,79,27,2331,3)),'src_span'(79,1,79,27,2308,26)).
'bindval'('RealDeadlock','prefix'('src_span'(81,16,81,24,2351,8),[],'deadlock','stop'('src_span'(81,26,81,30,2361,4)),'src_span'(81,25,81,25,2359,14)),'src_span'(81,1,81,30,2336,29)).
'assertRef'('False','val_of'('DComposition','src_span'(82,8,82,20,2373,12)),'Failure','val_of'('RealDeadlock','src_span'(82,25,82,37,2390,12)),'src_span'(82,1,82,37,2366,36)).
'bindval'('TESTER2','[]'('[]'('[]'('prefix'('src_span'(86,13,86,15,2455,2),['in'(_x111)],'in','val_of'('TESTER2','src_span'(86,19,86,26,2461,7)),'src_span'(86,18,86,18,2459,11)),'prefix'('src_span'(86,30,86,32,2472,2),[],'ok','val_of'('TESTER2','src_span'(86,34,86,41,2476,7)),'src_span'(86,33,86,33,2474,11)),'src_span_operator'('no_loc_info_available','src_span'(86,27,86,29,2469,2))),'prefix'('src_span'(86,45,86,48,2487,3),[],'err','val_of'('State22','src_span'(86,50,86,57,2492,7)),'src_span'(86,49,86,49,2490,12)),'src_span_operator'('no_loc_info_available','src_span'(86,42,86,44,2484,2))),'prefix'('src_span'(86,61,86,64,2503,3),['in'(_x112),'in'(_y113)],'inb','val_of'('TESTER2','src_span'(86,70,86,77,2512,7)),'src_span'(86,69,86,69,2510,11)),'src_span_operator'('no_loc_info_available','src_span'(86,58,86,60,2500,2))),'src_span'(86,1,86,78,2443,77)).
'bindval'('State22','prefix'('src_span'(87,12,87,19,2532,7),[],'success','val_of'('State22','src_span'(87,21,87,28,2541,7)),'src_span'(87,20,87,20,2539,16)),'src_span'(87,1,87,28,2521,27)).
'bindval'('Composition2','\x5c\'('sharing'('closure'(['in','ok','err','inb']),'val_of'('System','src_span'(88,17,88,23,2565,6)),'val_of'('TESTER2','src_span'(88,48,88,55,2596,7)),'src_span'(88,24,88,47,2572,23)),'closure'(['in','ok','err','inb']),'src_span_operator'('no_loc_info_available','src_span'(88,57,88,58,2605,1))),'src_span'(88,1,88,75,2549,74)).
'assertRef'('False','val_of'('Composition2','src_span'(89,8,89,20,2631,12)),'Trace','val_of'('SUC','src_span'(89,25,89,28,2648,3)),'src_span'(89,1,89,28,2624,27)).
'agent'('FaultyBuff2'(_cin7,_cout7),'prefix'('src_span'(95,25,95,28,2754,3),['in'(_x116)],_cin7,'agent_call'('src_span'(95,34,95,46,2763,12),'FaultyBuff2m',[_cin7,_cout7,_x116]),'src_span'(95,31,95,33,2759,30)),'src_span'(95,25,95,58,2754,33)).
'agent'('FaultyBuff2m'(_cin8,_cout8,_x119),'[]'('[]'('prefix'('src_span'(96,29,96,33,2816,4),['out'(_x119)],_cout8,'agent_call'('src_span'(96,39,96,50,2826,11),'FaultyBuff2',[_cin8,_cout8]),'src_span'(96,36,96,38,2822,27)),'prefix'('src_span'(97,24,97,28,2872,4),['out'('int'(2))],_cout8,'agent_call'('src_span'(97,34,97,45,2882,11),'FaultyBuff2',[_cin8,_cout8]),'src_span'(97,31,97,33,2878,27)),'src_span_operator'('no_loc_info_available','src_span'(97,20,97,22,2868,2))),'prefix'('src_span'(98,24,98,27,2928,3),['in'(_y120)],_cin8,'[]'('prefix'('src_span'(98,38,98,42,2942,4),['out'(_x119)],_cout8,'agent_call'('src_span'(98,48,98,60,2952,12),'FaultyBuff2m',[_cin8,_cout8,_y120]),'src_span'(98,45,98,47,2948,30)),'prefix'('src_span'(99,38,99,42,3015,4),['out'(_y120)],_cout8,'prefix'('src_span'(99,48,99,52,3025,4),['out'(_x119)],_cout8,'agent_call'('src_span'(99,58,99,69,3035,11),'FaultyBuff2',[_cin8,_cout8]),'src_span'(99,55,99,57,3031,27)),'src_span'(99,45,99,47,3021,37)),'src_span_operator'('no_loc_info_available','src_span'(99,34,99,36,3011,2))),'src_span'(98,30,98,32,2933,127)),'src_span_operator'('no_loc_info_available','src_span'(98,20,98,22,2924,2))),'no_loc_info_available').
'bindval'('FaultySystem','sharing'('closure'(['inb','in']),'\x5c\'('sharing'('closure'(['out']),'agent_call'('src_span'(103,19,103,25,3155,6),'StartA',['int'(0)]),'agent_call'('src_span'(103,45,103,51,3181,6),'Buff22',['out','inb']),'src_span'(103,29,103,44,3165,15)),'closure'(['out']),'src_span_operator'('no_loc_info_available','src_span'(103,62,103,63,3198,1))),'\x5c\'('sharing'('closure'(['outb']),'agent_call'('src_span'(105,13,105,16,3254,3),'BS2',['int'(1)]),'agent_call'('src_span'(105,37,105,48,3278,11),'FaultyBuff2',['outb','in']),'src_span'(105,20,105,36,3261,16)),'closure'(['outb']),'src_span_operator'('no_loc_info_available','src_span'(105,59,105,60,3300,1))),'src_span'(104,14,104,34,3221,20)),'src_span'(103,1,105,70,3137,174)).
'bindval'('FaultyComposition','\x5c\'('sharing'('closure'(['in','ok','err','inb']),'val_of'('FaultySystem','src_span'(106,22,106,34,3333,12)),'val_of'('TESTER','src_span'(106,59,106,65,3370,6)),'src_span'(106,35,106,58,3346,23)),'closure'(['in','ok','err','inb','deadlock','ko']),'src_span_operator'('no_loc_info_available','src_span'(107,21,107,22,3398,1))),'src_span'(106,1,107,51,3312,116)).
'assertRef'('False','val_of'('FaultyComposition','src_span'(108,8,108,25,3436,17)),'Trace','val_of'('SUC','src_span'(108,30,108,33,3458,3)),'src_span'(108,1,108,33,3429,32)).
'bindval'('DFaultyComposition','\x5c\'('sharing'('closure'(['in','ok','err','inb']),'val_of'('FaultySystem','src_span'(109,23,109,35,3484,12)),'val_of'('TESTER','src_span'(109,60,109,66,3521,6)),'src_span'(109,36,109,59,3497,23)),'closure'(['in','ok','err','inb','success']),'src_span_operator'('no_loc_info_available','src_span'(110,21,110,22,3549,1))),'src_span'(109,1,110,47,3462,113)).
'assertRef'('False','val_of'('DFaultyComposition','src_span'(111,8,111,26,3583,18)),'Failure','val_of'('RealDeadlock','src_span'(111,31,111,43,3606,12)),'src_span'(111,1,111,43,3576,42)).
'bindval'('FaultyComposition2','\x5c\'('sharing'('closure'(['in','ok','err','inb']),'val_of'('FaultySystem','src_span'(113,23,113,35,3642,12)),'val_of'('TESTER2','src_span'(113,60,113,67,3679,7)),'src_span'(113,36,113,59,3655,23)),'closure'(['in','ok','err','inb']),'src_span_operator'('no_loc_info_available','src_span'(113,69,113,70,3688,1))),'src_span'(113,1,113,87,3620,86)).
'assertRef'('False','val_of'('FaultyComposition2','src_span'(114,8,114,26,3714,18)),'Trace','val_of'('SUC','src_span'(114,31,114,34,3737,3)),'src_span'(114,1,114,34,3707,33)).
'bindval'('SPEC','prefix'('src_span'(118,8,118,10,3783,2),[],'ok','val_of'('SPEC','src_span'(118,14,118,18,3789,4)),'src_span'(118,11,118,13,3785,10)),'src_span'(118,1,118,18,3776,17)).
'assertRef'('False','\x5c\'('val_of'('System','src_span'(119,9,119,15,3802,6)),'closure'(['in','err','inb']),'src_span_operator'('no_loc_info_available','src_span'(119,16,119,17,3809,1))),'Trace','val_of'('SPEC','src_span'(119,37,119,41,3830,4)),'src_span'(119,1,119,41,3794,40)).
'assertRef'('False','\x5c\'('val_of'('System','src_span'(120,9,120,15,3843,6)),'closure'(['in','err','inb']),'src_span_operator'('no_loc_info_available','src_span'(120,16,120,17,3850,1))),'Failure','val_of'('SPEC','src_span'(120,37,120,41,3871,4)),'src_span'(120,1,120,41,3835,40)).
'assertRef'('False','\x5c\'('val_of'('System','src_span'(121,9,121,15,3884,6)),'closure'(['in','err','inb']),'src_span_operator'('no_loc_info_available','src_span'(121,16,121,17,3891,1))),'FailureDivergence','val_of'('SPEC','src_span'(121,38,121,42,3913,4)),'src_span'(121,1,121,42,3876,41)).
'bindval'('DivComp','\x5c\'('val_of'('System','src_span'(122,11,122,17,3928,6)),'closure'(['ok']),'src_span_operator'('no_loc_info_available','src_span'(122,18,122,19,3935,1))),'src_span'(122,1,122,25,3918,24)).
'comment'('lineComment'('-- error'),'src_position'(20,25,439,8)).
'comment'('lineComment'('-- error'),'src_position'(34,25,746,8)).
'comment'('lineComment'('-- error'),'src_position'(41,25,912,8)).
'comment'('lineComment'('-- Verify property: always eventually ok'),'src_position'(60,1,1567,40)).
'comment'('lineComment'('-- 1. Buechi automata translation for  not Alw Ev ok = Ev Alw not ok'),'src_position'(61,1,1608,68)).
'comment'('lineComment'('-- 2. compose system with TESTER'),'src_position'(71,1,2007,32)).
'comment'('lineComment'('-- 3. check whether intersection empty'),'src_position'(77,1,2250,38)).
'comment'('lineComment'('-- A safety property:  always not err'),'src_position'(85,1,2405,37)).
'comment'('lineComment'('-- test fails -> safety property ok'),'src_position'(90,1,2652,35)).
'comment'('lineComment'('-- Now the system with a faulty buffer'),'src_position'(93,1,2690,38)).
'comment'('lineComment'('-- or worse                      [] (cout!y -> FaultyBuff2m(cin,cout,x))))'),'src_position'(100,1,3060,74)).
'comment'('lineComment'('-- a classical refinement check:'),'src_position'(117,1,3743,32)).
'symbol'('ChData','ChData','src_span'(1,1,1,7,0,6),'Ident (Groundrep.)').
'symbol'('ChControl','ChControl','src_span'(2,1,2,10,19,9),'Ident (Groundrep.)').
'symbol'('in','in','src_span'(4,9,4,11,48,2),'Channel').
'symbol'('outb','outb','src_span'(4,12,4,16,51,4),'Channel').
'symbol'('out','out','src_span'(5,9,5,12,73,3),'Channel').
'symbol'('inb','inb','src_span'(5,13,5,16,77,3),'Channel').
'symbol'('ok','ok','src_span'(6,9,6,11,107,2),'Channel').
'symbol'('err','err','src_span'(6,12,6,15,110,3),'Channel').
'symbol'('StartA','StartA','src_span'(8,1,8,7,115,6),'Funktion or Process').
'symbol'('x','x','src_span'(8,8,8,9,122,1),'Ident (Prolog Variable)').
'symbol'('AS1','AS1','src_span'(9,1,9,4,140,3),'Funktion or Process').
'symbol'('x2','x','src_span'(9,5,9,6,144,1),'Ident (Prolog Variable)').
'symbol'('AS2','AS2','src_span'(11,1,11,4,168,3),'Funktion or Process').
'symbol'('x3','x','src_span'(11,5,11,6,172,1),'Ident (Prolog Variable)').
'symbol'('vr','vr','src_span'(11,13,11,15,180,2),'Ident (Prolog Variable)').
'symbol'('AS3','AS3','src_span'(15,1,15,4,291,3),'Funktion or Process').
'symbol'('x4','x','src_span'(15,5,15,6,295,1),'Ident (Prolog Variable)').
'symbol'('AS4','AS4','src_span'(17,1,17,4,319,3),'Funktion or Process').
'symbol'('x5','x','src_span'(17,5,17,6,323,1),'Ident (Prolog Variable)').
'symbol'('vr2','vr','src_span'(17,13,17,15,331,2),'Ident (Prolog Variable)').
'symbol'('AS5','AS5','src_span'(21,1,21,4,448,3),'Funktion or Process').
'symbol'('x6','x','src_span'(21,5,21,6,452,1),'Ident (Prolog Variable)').
'symbol'('Breceived','Breceived','src_span'(24,1,24,10,477,9),'Funktion or Process').
'symbol'('x7','x','src_span'(24,11,24,12,487,1),'Ident (Prolog Variable)').
'symbol'('y','y','src_span'(24,13,24,14,489,1),'Ident (Prolog Variable)').
'symbol'('BS1','BS1','src_span'(29,1,29,4,599,3),'Funktion or Process').
'symbol'('y2','y','src_span'(29,5,29,6,603,1),'Ident (Prolog Variable)').
'symbol'('BS2','BS2','src_span'(31,1,31,4,626,3),'Funktion or Process').
'symbol'('y3','y','src_span'(31,5,31,6,630,1),'Ident (Prolog Variable)').
'symbol'('x8','x','src_span'(31,14,31,15,639,1),'Ident (Prolog Variable)').
'symbol'('ar','ar','src_span'(31,16,31,18,641,2),'Ident (Prolog Variable)').
'symbol'('BS3','BS3','src_span'(36,1,36,4,756,3),'Funktion or Process').
'symbol'('y4','y','src_span'(36,5,36,6,760,1),'Ident (Prolog Variable)').
'symbol'('BS4','BS4','src_span'(38,1,38,4,783,3),'Funktion or Process').
'symbol'('y5','y','src_span'(38,5,38,6,787,1),'Ident (Prolog Variable)').
'symbol'('x9','x','src_span'(38,14,38,15,796,1),'Ident (Prolog Variable)').
'symbol'('ar2','ar','src_span'(38,16,38,18,798,2),'Ident (Prolog Variable)').
'symbol'('BS5','BS5','src_span'(42,1,42,4,921,3),'Funktion or Process').
'symbol'('y6','y','src_span'(42,5,42,6,925,1),'Ident (Prolog Variable)').
'symbol'('Buffer','Buffer','src_span'(44,1,44,7,948,6),'Funktion or Process').
'symbol'('cin','cin','src_span'(44,8,44,11,955,3),'Ident (Prolog Variable)').
'symbol'('cout','cout','src_span'(44,12,44,16,959,4),'Ident (Prolog Variable)').
'symbol'('x80','x','src_span'(44,24,44,25,971,1),'Ident (Prolog Variable)').
'symbol'('Buffer2','Buffer2','src_span'(45,1,45,8,1005,7),'Funktion or Process').
'symbol'('cin2','cin','src_span'(45,9,45,12,1013,3),'Ident (Prolog Variable)').
'symbol'('cout2','cout','src_span'(45,13,45,17,1017,4),'Ident (Prolog Variable)').
'symbol'('x83','x','src_span'(45,25,45,26,1029,1),'Ident (Prolog Variable)').
'symbol'('y7','y','src_span'(45,27,45,28,1031,1),'Ident (Prolog Variable)').
'symbol'('Buff2','Buff2','src_span'(47,1,47,6,1067,5),'Funktion or Process').
'symbol'('cin3','cin','src_span'(47,7,47,10,1073,3),'Ident (Prolog Variable)').
'symbol'('cout3','cout','src_span'(47,11,47,15,1077,4),'Ident (Prolog Variable)').
'symbol'('x87','x','src_span'(47,23,47,24,1089,1),'Ident (Prolog Variable)').
'symbol'('Buff2m','Buff2m','src_span'(48,1,48,7,1113,6),'Funktion or Process').
'symbol'('cin4','cin','src_span'(48,8,48,11,1120,3),'Ident (Prolog Variable)').
'symbol'('cout4','cout','src_span'(48,12,48,16,1124,4),'Ident (Prolog Variable)').
'symbol'('x90','x','src_span'(48,17,48,18,1129,1),'Ident (Prolog Variable)').
'symbol'('y8','y','src_span'(49,28,49,29,1189,1),'Ident (Prolog Variable)').
'symbol'('Buff22','Buff22','src_span'(50,1,50,7,1224,6),'Funktion or Process').
'symbol'('cin5','cin','src_span'(50,8,50,11,1231,3),'Ident (Prolog Variable)').
'symbol'('cout5','cout','src_span'(50,12,50,16,1235,4),'Ident (Prolog Variable)').
'symbol'('x94','x','src_span'(50,24,50,25,1247,1),'Ident (Prolog Variable)').
'symbol'('y9','y','src_span'(50,26,50,27,1249,1),'Ident (Prolog Variable)').
'symbol'('Buff22m','Buff22m','src_span'(51,1,51,8,1276,7),'Funktion or Process').
'symbol'('cin6','cin','src_span'(51,9,51,12,1284,3),'Ident (Prolog Variable)').
'symbol'('cout6','cout','src_span'(51,13,51,17,1288,4),'Ident (Prolog Variable)').
'symbol'('x98','x','src_span'(51,18,51,19,1293,1),'Ident (Prolog Variable)').
'symbol'('y99','y','src_span'(51,20,51,21,1295,1),'Ident (Prolog Variable)').
'symbol'('u','u','src_span'(52,28,52,29,1358,1),'Ident (Prolog Variable)').
'symbol'('v','v','src_span'(52,30,52,31,1360,1),'Ident (Prolog Variable)').
'symbol'('System','System','src_span'(55,1,55,7,1402,6),'Ident (Groundrep.)').
'symbol'('success','success','src_span'(62,9,62,16,1685,7),'Channel').
'symbol'('deadlock','deadlock','src_span'(62,17,62,25,1693,8),'Channel').
'symbol'('ko','ko','src_span'(62,26,62,28,1702,2),'Channel').
'symbol'('TESTER','TESTER','src_span'(63,1,63,7,1705,6),'Ident (Groundrep.)').
'symbol'('State1','State1','src_span'(64,1,64,7,1721,6),'Ident (Groundrep.)').
'symbol'('x102','x','src_span'(64,15,64,16,1735,1),'Ident (Prolog Variable)').
'symbol'('x103','x','src_span'(64,61,64,62,1781,1),'Ident (Prolog Variable)').
'symbol'('y104','y','src_span'(64,63,64,64,1783,1),'Ident (Prolog Variable)').
'symbol'('State2','State2','src_span'(66,1,66,7,1818,6),'Ident (Groundrep.)').
'symbol'('x105','x','src_span'(66,25,66,26,1842,1),'Ident (Prolog Variable)').
'symbol'('x106','x','src_span'(66,57,66,58,1874,1),'Ident (Prolog Variable)').
'symbol'('y107','y','src_span'(66,59,66,60,1876,1),'Ident (Prolog Variable)').
'symbol'('Deadlock','Deadlock','src_span'(69,1,69,9,1923,8),'Ident (Groundrep.)').
'symbol'('x108','x','src_span'(69,17,69,18,1939,1),'Ident (Prolog Variable)').
'symbol'('x109','x','src_span'(69,69,69,70,1991,1),'Ident (Prolog Variable)').
'symbol'('y110','y','src_span'(69,71,69,72,1993,1),'Ident (Prolog Variable)').
'symbol'('Composition','Composition','src_span'(72,1,72,12,2040,11),'Ident (Groundrep.)').
'symbol'('DComposition','DComposition','src_span'(74,1,74,13,2146,12),'Ident (Groundrep.)').
'symbol'('SUC','SUC','src_span'(78,1,78,4,2289,3),'Ident (Groundrep.)').
'symbol'('RealDeadlock','RealDeadlock','src_span'(81,1,81,13,2336,12),'Ident (Groundrep.)').
'symbol'('TESTER2','TESTER2','src_span'(86,1,86,8,2443,7),'Ident (Groundrep.)').
'symbol'('x111','x','src_span'(86,16,86,17,2458,1),'Ident (Prolog Variable)').
'symbol'('x112','x','src_span'(86,65,86,66,2507,1),'Ident (Prolog Variable)').
'symbol'('y113','y','src_span'(86,67,86,68,2509,1),'Ident (Prolog Variable)').
'symbol'('State22','State22','src_span'(87,1,87,8,2521,7),'Ident (Groundrep.)').
'symbol'('Composition2','Composition2','src_span'(88,1,88,13,2549,12),'Ident (Groundrep.)').
'symbol'('FaultyBuff2','FaultyBuff2','src_span'(95,1,95,12,2730,11),'Funktion or Process').
'symbol'('cin7','cin','src_span'(95,13,95,16,2742,3),'Ident (Prolog Variable)').
'symbol'('cout7','cout','src_span'(95,17,95,21,2746,4),'Ident (Prolog Variable)').
'symbol'('x116','x','src_span'(95,29,95,30,2758,1),'Ident (Prolog Variable)').
'symbol'('FaultyBuff2m','FaultyBuff2m','src_span'(96,1,96,13,2788,12),'Funktion or Process').
'symbol'('cin8','cin','src_span'(96,14,96,17,2801,3),'Ident (Prolog Variable)').
'symbol'('cout8','cout','src_span'(96,18,96,22,2805,4),'Ident (Prolog Variable)').
'symbol'('x119','x','src_span'(96,23,96,24,2810,1),'Ident (Prolog Variable)').
'symbol'('y120','y','src_span'(98,28,98,29,2932,1),'Ident (Prolog Variable)').
'symbol'('FaultySystem','FaultySystem','src_span'(103,1,103,13,3137,12),'Ident (Groundrep.)').
'symbol'('FaultyComposition','FaultyComposition','src_span'(106,1,106,18,3312,17),'Ident (Groundrep.)').
'symbol'('DFaultyComposition','DFaultyComposition','src_span'(109,1,109,19,3462,18),'Ident (Groundrep.)').
'symbol'('FaultyComposition2','FaultyComposition2','src_span'(113,1,113,19,3620,18),'Ident (Groundrep.)').
'symbol'('SPEC','SPEC','src_span'(118,1,118,5,3776,4),'Ident (Groundrep.)').
'symbol'('DivComp','DivComp','src_span'(122,1,122,8,3918,7),'Ident (Groundrep.)').