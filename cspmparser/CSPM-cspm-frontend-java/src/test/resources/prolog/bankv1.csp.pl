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
'bindval'('QUEUENUM','setExp'('rangeClosed'('int'(1),'int'(3))),'src_span'(5,1,5,18,71,17)).
'dataTypeDef'('CUSTOMER',['constructor'('c1'),'constructor'('c2'),'constructor'('c3')]).
'dataTypeDef'('STATUS',['constructor'('success'),'constructor'('fail')]).
'dataTypeDef'('QSTATUS',['constructor'('yes'),'constructor'('no')]).
'bindval'('maxLimit','int'(2),'src_span'(10,1,10,13,183,12)).
'bindval'('defaultCounter','int'(1),'src_span'(11,1,11,20,196,19)).
'bindval'('defaultCustomer','c1','src_span'(12,1,12,21,216,20)).
'bindval'('maxQueueingCustomers','-'('val_of'('maxLimit','src_span'(13,24,13,32,260,8)),'int'(1)),'src_span'(13,1,13,35,237,34)).
'bindval'('numQueues','int'(2),'src_span'(14,1,14,14,272,13)).
'channel'('enterBank','type'('dotTupleType'(['CUSTOMER']))).
'channel'('leaveBank','type'('dotTupleType'(['CUSTOMER']))).
'channel'('report','type'('dotTupleType'(['STATUS']))).
'channel'('com1','type'('dotTupleType'(['CUSTOMER']))).
'channel'('com2','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(24,16,24,24,498,8))]))).
'channel'('com3','type'('dotTupleType'(['CUSTOMER','val_of'('QUEUENUM','src_span'(25,25,25,33,531,8))]))).
'channel'('canJoinResponse','type'('dotTupleType'(['STATUS']))).
'channel'('retrieveCustomer','type'('dotUnitType')).
'channel'('joinQueue','type'('dotTupleType'(['CUSTOMER']))).
'channel'('leaveQueue','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(31,22,31,30,670,8)),'CUSTOMER']))).
'channel'('queryQueueEmpty','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(32,27,32,35,714,8)),'QSTATUS']))).
'channel'('queryIsInQueue','type'('dotTupleType'(['CUSTOMER','QSTATUS']))).
'channel'('getQueueNo','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(35,22,35,30,795,8))]))).
'channel'('setNextQueue','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(36,24,36,32,827,8))]))).
'channel'('setCustomer','type'('dotTupleType'(['CUSTOMER']))).
'channel'('getCustomer','type'('dotTupleType'(['CUSTOMER']))).
'agent'('inc'(_queueNo),'+'('%'(_queueNo,'val_of'('numQueues','src_span'(41,27,41,36,926,9))),'int'(1)),'no_loc_info_available').
'bindval'('BankCounterCtrl','agent_call'('src_span'(55,19,55,30,1295,11),'CurrentCtrl',['int'(0)]),'src_span'(55,1,55,33,1277,32)).
'agent'('CurrentCtrl'(_num),'[]'('agent_call'('src_span'(58,5,58,13,1335,8),'JoinCtrl',[_num]),'agent_call'('src_span'(58,22,58,31,1352,9),'LeaveCtrl',[_num]),'src_span_operator'('no_loc_info_available','src_span'(58,19,58,21,1349,2))),'no_loc_info_available').
'agent'('JoinCtrl'(_num2),'&'('<'(_num2,'val_of'('maxLimit','src_span'(66,9,66,17,1532,8))),'prefix'('src_span'(66,20,66,29,1543,9),['in'(_cc)],'enterBank','[]'('&'('=='(_num2,'int'(0)),'prefix'('src_span'(67,21,67,35,1580,14),[],'dotTuple'(['report','success']),'prefix'('src_span'(67,39,67,53,1598,14),[],'dotTuple'(['setCustomer',_cc]),'agent_call'('src_span'(67,57,67,68,1616,11),'CurrentCtrl',['+'(_num2,'int'(1))]),'src_span'(67,54,67,56,1612,36)),'src_span'(67,36,67,38,1594,54))),'&'('>'(_num2,'int'(0)),'prefix'('src_span'(69,20,69,31,1666,11),['in'(_currentCust)],'getCustomer','ifte'('=='(_cc,_currentCust),'prefix'('src_span'(70,42,70,53,1735,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(70,57,70,68,1750,11),'CurrentCtrl',[_num2]),'src_span'(70,54,70,56,1746,31)),'prefix'('src_span'(72,20,72,24,1805,4),['out'(_cc)],'com1','prefix'('src_span'(72,31,72,46,1816,15),['in'(_bb)],'canJoinResponse','prefix'('src_span'(72,53,72,62,1838,9),[],'dotTuple'(['report',_bb]),'ifte'('=='(_bb,'success'),'agent_call'('src_span'(74,24,74,35,1916,11),'CurrentCtrl',['+'(_num2,'int'(1))]),'agent_call'('src_span'(76,24,76,35,1983,11),'CurrentCtrl',[_num2]),'src_span'(73,21,73,37,1871,16),'src_span'(73,38,74,23,1887,60),'src_span'(74,43,76,23,1934,83)),'src_span'(72,63,73,19,1847,162)),'src_span'(72,50,72,52,1834,169)),'src_span'(72,28,72,30,1812,191)),'src_span'(70,14,70,36,1707,22),'src_span'(70,37,70,41,1729,56),'src_span'(70,74,72,19,1766,265)),'src_span'(69,44,70,13,1689,323))),'src_span_operator'('no_loc_info_available','src_span'(68,9,68,11,1644,2))),'src_span'(66,34,67,3,1556,467))),'no_loc_info_available').
'agent'('LeaveCtrl'(_num3),'[]'('&'('=='(_num3,'int'(1)),'prefix'('src_span'(91,21,91,32,2562,11),['in'(_cc2)],'getCustomer','prefix'('src_span'(91,40,91,49,2581,9),['out'(_cc2)],'leaveBank','prefix'('src_span'(92,10,92,37,2606,27),[],'dotTuple'(['setCustomer','val_of'('defaultCustomer','src_span'(92,22,92,37,2618,15))]),'agent_call'('src_span'(92,41,92,52,2637,11),'CurrentCtrl',['int'(0)]),'src_span'(92,38,92,40,2633,45)),'src_span'(91,53,92,9,2593,61)),'src_span'(91,36,91,39,2576,78))),'&'('>'(_num3,'int'(1)),'prefix'('src_span'(94,21,94,32,2685,11),['in'(_cc3)],'getCustomer','prefix'('src_span'(94,39,94,48,2703,9),['out'(_cc3)],'leaveBank','prefix'('src_span'(95,15,95,25,2734,10),['in'(_qNo)],'getQueueNo','prefix'('src_span'(95,33,95,37,2752,4),['out'(_qNo)],'com2','prefix'('src_span'(95,45,95,49,2764,4),['in'(_newCust),'in'(_newQNo)],'com3','prefix'('src_span'(96,15,96,26,2802,11),['out'(_newCust)],'setCustomer','prefix'('src_span'(96,38,96,50,2825,12),['out'(_newQNo)],'setNextQueue','agent_call'('src_span'(96,61,96,72,2848,11),'CurrentCtrl',['-'(_num3,'int'(1))]),'src_span'(96,58,96,60,2844,29)),'src_span'(96,35,96,37,2821,53)),'src_span'(95,65,96,14,2783,90)),'src_span'(95,42,95,44,2760,110)),'src_span'(95,30,95,32,2748,122)),'src_span'(94,52,95,14,2715,154)),'src_span'(94,36,94,38,2699,170))),'src_span_operator'('no_loc_info_available','src_span'(93,10,93,12,2662,2))),'no_loc_info_available').
'bindval'('QueuesCtrl','agent_call'('src_span'(103,14,103,19,3152,5),'QCtrl',['int'(0)]),'src_span'(103,1,103,22,3139,21)).
'agent'('QCtrl'(_s),'[]'('&'('<'(_s,'val_of'('maxQueueingCustomers','src_span'(105,11,105,31,3183,20))),'prefix'('src_span'(105,34,105,38,3206,4),['in'(_cc4)],'com1','prefix'('src_span'(106,16,106,30,3235,14),['out'(_cc4),'in'(_bb2)],'queryIsInQueue','ifte'('=='(_bb2,'yes'),'prefix'('src_span'(108,19,108,34,3313,15),['out'('fail')],'canJoinResponse','agent_call'('src_span'(108,43,108,48,3337,5),'QCtrl',[_s]),'src_span'(108,40,108,42,3333,17)),'prefix'('src_span'(110,19,110,34,3384,15),['out'('success')],'canJoinResponse','prefix'('src_span'(110,46,110,58,3411,12),[],'dotTuple'(['joinQueue',_cc4]),'agent_call'('src_span'(111,5,111,10,3431,5),'QCtrl',['+'(_s,'int'(1))]),'src_span'(110,59,111,4,3423,30)),'src_span'(110,43,110,45,3407,42)),'src_span'(107,16,107,28,3274,12),'src_span'(107,29,108,18,3286,68),'src_span'(108,52,110,18,3345,128)),'src_span'(106,37,107,15,3255,189)),'src_span'(105,44,106,15,3215,230))),'&'('>'(_s,'int'(0)),'prefix'('src_span'(114,15,114,19,3472,4),['in'(_qNo2)],'com2','agent_call'('src_span'(114,27,114,36,3484,9),'NextQCtrl',[_s,_qNo2]),'src_span'(114,24,114,26,3480,24))),'src_span_operator'('no_loc_info_available','src_span'(112,6,112,8,3448,2))),'no_loc_info_available').
'agent'('NextQCtrl'(_s2,_queueNo2),'prefix'('src_span'(118,8,118,23,3598,15),['out'(_queueNo2),'in'(_bb3)],'queryQueueEmpty','ifte'('=='(_bb3,'no'),'prefix'('src_span'(120,9,120,19,3662,10),['out'(_queueNo2),'in'(_cc5)],'leaveQueue','prefix'('src_span'(120,34,120,38,3687,4),['out'(_cc5),'out'('agent_call'('src_span'(120,42,120,45,3695,3),'inc',[_queueNo2]))],'com3','agent_call'('src_span'(120,58,120,63,3711,5),'QCtrl',['-'(_s2,'int'(1))]),'src_span'(120,55,120,57,3707,27)),'src_span'(120,31,120,33,3683,41)),'agent_call'('src_span'(122,14,122,23,3747,9),'NextQCtrl',[_s2,'agent_call'('src_span'(122,26,122,29,3759,3),'inc',[_queueNo2])]),'src_span'(119,8,119,21,3635,13),'src_span'(119,22,120,8,3648,83),'src_span'(120,69,122,13,3721,110)),'src_span'(118,35,119,7,3624,151)),'src_span'(118,8,122,39,3598,174)).
'bindval'('A','closure'(['com1','com2','com3','canJoinResponse','retrieveCustomer']),'src_span'(124,1,124,57,3774,56)).
'bindval'('B','closure'(['joinQueue','leaveQueue','queryQueueEmpty','queryIsInQueue','getCustomer','setCustomer','getQueueNo','setNextQueue']),'src_span'(125,1,125,119,3831,118)).
'bindval'('BankSystem','\x5c\'('sharing'('val_of'('A','src_span'(126,33,126,34,3982,1)),'val_of'('BankCounterCtrl','src_span'(126,15,126,30,3964,15)),'val_of'('QueuesCtrl','src_span'(126,37,126,47,3986,10)),'src_span'(126,31,126,36,3980,5)),'agent_call'('src_span'(126,51,126,56,4000,5),'union',['val_of'('A','src_span'(126,57,126,58,4006,1)),'val_of'('B','src_span'(126,59,126,60,4008,1))]),'src_span_operator'('no_loc_info_available','src_span'(126,49,126,50,3998,1))),'src_span'(126,1,126,61,3950,60)).
'bindval'('BankSystemNoHide','sharing'('val_of'('A','src_span'(127,38,127,39,4048,1)),'val_of'('BankCounterCtrl','src_span'(127,20,127,35,4030,15)),'val_of'('QueuesCtrl','src_span'(127,42,127,52,4052,10)),'src_span'(127,36,127,41,4046,5)),'src_span'(127,1,127,52,4011,51)).
'bindval'('SPEC','repInterleave'(['comprehensionGenerator'(_i,'setExp'('rangeClosed'('int'(1),'val_of'('maxLimit','src_span'(129,18,129,26,4082,8)))))],'val_of'('CUST','src_span'(129,30,129,34,4094,4)),'src_span'(129,12,129,29,4076,17)),'src_span'(129,1,129,34,4065,33)).
'bindval'('CUST','prefix'('src_span'(130,8,130,17,4106,9),['in'(_i2)],'enterBank','[]'('prefix'('src_span'(130,24,130,35,4122,11),[],'dotTuple'(['report','fail']),'val_of'('CUST','src_span'(130,39,130,43,4137,4)),'src_span'(130,36,130,38,4133,19)),'prefix'('src_span'(130,47,130,61,4145,14),[],'dotTuple'(['report','success']),'prefix'('src_span'(131,29,131,40,4191,11),[],'dotTuple'(['leaveBank',_i2]),'val_of'('CUST','src_span'(131,44,131,48,4206,4)),'src_span'(131,41,131,43,4202,19)),'src_span'(130,62,131,28,4159,65)),'src_span_operator'('no_loc_info_available','src_span'(130,44,130,46,4142,2))),'src_span'(130,20,130,22,4117,96)),'src_span'(130,1,131,49,4099,112)).
'bindval'('SPEC2','agent_call'('src_span'(137,9,137,16,4325,7),'NEWSPEC',['int'(0)]),'src_span'(137,1,137,19,4317,18)).
'agent'('NEWSPEC'(_num4),'[]'('&'('<'(_num4,'val_of'('maxLimit','src_span'(139,10,139,18,4361,8))),'prefix'('src_span'(139,21,139,30,4372,9),['in'(_cc6)],'enterBank','|~|'('prefix'('src_span'(140,12,140,26,4401,14),[],'dotTuple'(['report','success']),'agent_call'('src_span'(140,30,140,37,4419,7),'NEWSPEC',['+'(_num4,'int'(1))]),'src_span'(140,27,140,29,4415,32)),'prefix'('src_span'(140,49,140,60,4438,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(140,64,140,71,4453,7),'NEWSPEC',[_num4]),'src_span'(140,61,140,63,4449,27)),'src_span_operator'('no_loc_info_available','src_span'(140,45,140,48,4434,3))),'src_span'(139,35,140,10,4385,85))),'&'('>'(_num4,'int'(0)),'repInternalChoice'(['comprehensionGenerator'(_cc7,'CUSTOMER')],'prefix'('src_span'(142,33,142,46,4505,13),[],'dotTuple'(['leaveBank',_cc7]),'agent_call'('src_span'(142,50,142,57,4522,7),'NEWSPEC',['-'(_num4,'int'(1))]),'src_span'(142,47,142,49,4518,31)),'src_span'(142,18,142,32,4490,14))),'src_span_operator'('no_loc_info_available','src_span'(141,4,141,6,4470,2))),'no_loc_info_available').
'assertRef'('False','val_of'('SPEC','src_span'(145,8,145,12,4546,4)),'Trace','val_of'('BankSystem','src_span'(145,17,145,27,4555,10)),'src_span'(145,1,145,27,4539,26)).
'assertRef'('False','val_of'('SPEC','src_span'(146,8,146,12,4573,4)),'Failure','val_of'('BankSystem','src_span'(146,17,146,27,4582,10)),'src_span'(146,1,146,27,4566,26)).
'assertRef'('False','val_of'('SPEC2','src_span'(148,8,148,13,4601,5)),'Trace','val_of'('BankSystem','src_span'(148,18,148,28,4611,10)),'src_span'(148,1,148,28,4594,27)).
'assertRef'('False','val_of'('SPEC2','src_span'(149,8,149,13,4629,5)),'Failure','val_of'('BankSystem','src_span'(149,18,149,28,4639,10)),'src_span'(149,1,149,28,4622,27)).
'comment'('lineComment'('-- Example controllers to accompany "ZB2003" paper'),'src_position'(1,1,0,50)).
'comment'('lineComment'('-- bank version 1'),'src_position'(3,1,52,17)).
'comment'('lineComment'('-- external channels'),'src_position'(17,1,289,20)).
'comment'('lineComment'('-- communication channels, no underlying B, between CSP processes'),'src_position'(22,1,393,65)).
'comment'('lineComment'('-- machine channels'),'src_position'(29,1,600,19)).
'comment'('lineComment'('-- abstract specfication'),'src_position'(43,1,942,24)).
'comment'('lineComment'('-- num is the number of people waiting and the one being serviced'),'src_position'(44,1,967,65)).
'comment'('lineComment'('--Spec  = Waiting(0,<>)'),'src_position'(46,1,1034,23)).
'comment'('lineComment'('--Waiting(num,people_waiting) = '),'src_position'(47,1,1058,32)).
'comment'('lineComment'('--   num < bankLimit & input ? cc -> Waiting(num+1, people_waiting ^<cc>)'),'src_position'(48,1,1091,73)).
'comment'('lineComment'('--   []'),'src_position'(49,1,1165,7)).
'comment'('lineComment'('--   num > 0 & |~| cc : people_waiting @ output. cc ->'),'src_position'(50,1,1173,54)).
'comment'('lineComment'('--   Waiting(num - 1, .....'),'src_position'(51,1,1228,27)).
'comment'('lineComment'('--- implementation'),'src_position'(53,1,1257,18)).
'comment'('lineComment'('-- we allow any input but then the queues do the checking about'),'src_position'(60,1,1368,63)).
'comment'('lineComment'('-- passing com1 access '),'src_position'(61,1,1432,23)).
'comment'('lineComment'('-- whether the customer is already in the queue '),'src_position'(62,1,1456,48)).
'comment'('lineComment'('-- don\x27\t need to set back to default because could put it as cust'),'src_position'(80,1,2046,65)).
'comment'('lineComment'('--because never going to use that value again'),'src_position'(81,1,2112,45)).
'comment'('lineComment'('-- but what if the size of that queue is empty the we should precoess'),'src_position'(82,1,2158,69)).
'comment'('lineComment'('--the next one along, where is that info going to be kept'),'src_position'(83,1,2228,57)).
'comment'('lineComment'('-- with the com3 is there goign to be a deadlock again with requesting'),'src_position'(84,1,2286,70)).
'comment'('lineComment'('--value, do we need an atomic event to prevent that to signal update'),'src_position'(85,1,2357,68)).
'comment'('lineComment'('--sequence of events'),'src_position'(86,1,2426,20)).
'comment'('lineComment'('-- if > 1 then output the currentperson and get the next one from a'),'src_position'(87,1,2447,67)).
'comment'('lineComment'('--queue'),'src_position'(88,1,2515,7)).
'comment'('lineComment'('--              retrieveCustomer -> getQueueNo?qNo -> com2!qNo -> com3?newCust?newQNo -> '),'src_position'(97,1,2868,89)).
'comment'('lineComment'('--              setCustomer!newCust -> setNextQueue!newQNo -> CurrentCtrl(num-1))'),'src_position'(98,1,2958,81)).
'comment'('lineComment'('-- now queues is tracking the counter and is making the choice of'),'src_position'(100,1,3041,65)).
'comment'('lineComment'('--which queue to get info from'),'src_position'(101,1,3107,30)).
'comment'('lineComment'('--   (s > 0 & retrieveCustomer -> com2?qNo -> NextQCtrl(s,qNo))'),'src_position'(115,1,3502,63)).
'comment'('lineComment'('-- Need to set maxQueueingCustomers to 2 for this check to succeed in'),'src_position'(134,1,4214,69)).
'comment'('lineComment'('-- any sort of reasonable time.'),'src_position'(135,1,4284,31)).
'symbol'('QUEUENUM','QUEUENUM','src_span'(5,1,5,9,71,8),'Ident (Groundrep.)').
'symbol'('CUSTOMER','CUSTOMER','src_span'(6,10,6,18,98,8),'Datatype').
'symbol'('c1','c1','src_span'(6,21,6,23,109,2),'Constructor of Datatype').
'symbol'('c2','c2','src_span'(6,26,6,28,114,2),'Constructor of Datatype').
'symbol'('c3','c3','src_span'(6,31,6,33,119,2),'Constructor of Datatype').
'symbol'('STATUS','STATUS','src_span'(7,10,7,16,131,6),'Datatype').
'symbol'('success','success','src_span'(7,19,7,26,140,7),'Constructor of Datatype').
'symbol'('fail','fail','src_span'(7,29,7,33,150,4),'Constructor of Datatype').
'symbol'('QSTATUS','QSTATUS','src_span'(8,10,8,17,164,7),'Datatype').
'symbol'('yes','yes','src_span'(8,19,8,22,173,3),'Constructor of Datatype').
'symbol'('no','no','src_span'(8,25,8,27,179,2),'Constructor of Datatype').
'symbol'('maxLimit','maxLimit','src_span'(10,1,10,9,183,8),'Ident (Groundrep.)').
'symbol'('defaultCounter','defaultCounter','src_span'(11,1,11,15,196,14),'Ident (Groundrep.)').
'symbol'('defaultCustomer','defaultCustomer','src_span'(12,1,12,16,216,15),'Ident (Groundrep.)').
'symbol'('maxQueueingCustomers','maxQueueingCustomers','src_span'(13,1,13,21,237,20),'Ident (Groundrep.)').
'symbol'('numQueues','numQueues','src_span'(14,1,14,10,272,9),'Ident (Groundrep.)').
'symbol'('enterBank','enterBank','src_span'(18,9,18,18,318,9),'Channel').
'symbol'('leaveBank','leaveBank','src_span'(19,9,19,18,347,9),'Channel').
'symbol'('report','report','src_span'(20,9,20,15,376,6),'Channel').
'symbol'('com1','com1','src_span'(23,9,23,13,467,4),'Channel').
'symbol'('com2','com2','src_span'(24,9,24,13,491,4),'Channel').
'symbol'('com3','com3','src_span'(25,9,25,13,515,4),'Channel').
'symbol'('canJoinResponse','canJoinResponse','src_span'(26,9,26,24,548,15),'Channel').
'symbol'('retrieveCustomer','retrieveCustomer','src_span'(27,9,27,25,581,16),'Channel').
'symbol'('joinQueue','joinQueue','src_span'(30,9,30,18,628,9),'Channel').
'symbol'('leaveQueue','leaveQueue','src_span'(31,9,31,19,657,10),'Channel').
'symbol'('queryQueueEmpty','queryQueueEmpty','src_span'(32,9,32,24,696,15),'Channel').
'symbol'('queryIsInQueue','queryIsInQueue','src_span'(33,9,33,23,739,14),'Channel').
'symbol'('getQueueNo','getQueueNo','src_span'(35,9,35,19,782,10),'Channel').
'symbol'('setNextQueue','setNextQueue','src_span'(36,9,36,21,812,12),'Channel').
'symbol'('setCustomer','setCustomer','src_span'(37,9,37,20,844,11),'Channel').
'symbol'('getCustomer','getCustomer','src_span'(38,9,38,20,875,11),'Channel').
'symbol'('inc','inc','src_span'(41,1,41,4,900,3),'Funktion or Process').
'symbol'('queueNo','queueNo','src_span'(41,5,41,12,904,7),'Ident (Prolog Variable)').
'symbol'('BankCounterCtrl','BankCounterCtrl','src_span'(55,1,55,16,1277,15),'Ident (Groundrep.)').
'symbol'('CurrentCtrl','CurrentCtrl','src_span'(57,1,57,12,1311,11),'Funktion or Process').
'symbol'('num','num','src_span'(57,13,57,16,1323,3),'Ident (Prolog Variable)').
'symbol'('JoinCtrl','JoinCtrl','src_span'(65,1,65,9,1507,8),'Funktion or Process').
'symbol'('num2','num','src_span'(65,10,65,13,1516,3),'Ident (Prolog Variable)').
'symbol'('cc','cc','src_span'(66,31,66,33,1554,2),'Ident (Prolog Variable)').
'symbol'('currentCust','currentCust','src_span'(69,32,69,43,1678,11),'Ident (Prolog Variable)').
'symbol'('bb','bb','src_span'(72,47,72,49,1832,2),'Ident (Prolog Variable)').
'symbol'('LeaveCtrl','LeaveCtrl','src_span'(90,1,90,10,2524,9),'Funktion or Process').
'symbol'('num3','num','src_span'(90,11,90,14,2534,3),'Ident (Prolog Variable)').
'symbol'('cc2','cc','src_span'(91,33,91,35,2574,2),'Ident (Prolog Variable)').
'symbol'('cc3','cc','src_span'(94,33,94,35,2697,2),'Ident (Prolog Variable)').
'symbol'('qNo','qNo','src_span'(95,26,95,29,2745,3),'Ident (Prolog Variable)').
'symbol'('newCust','newCust','src_span'(95,50,95,57,2769,7),'Ident (Prolog Variable)').
'symbol'('newQNo','newQNo','src_span'(95,58,95,64,2777,6),'Ident (Prolog Variable)').
'symbol'('QueuesCtrl','QueuesCtrl','src_span'(103,1,103,11,3139,10),'Ident (Groundrep.)').
'symbol'('QCtrl','QCtrl','src_span'(104,1,104,6,3161,5),'Funktion or Process').
'symbol'('s','s','src_span'(104,7,104,8,3167,1),'Ident (Prolog Variable)').
'symbol'('cc4','cc','src_span'(105,41,105,43,3213,2),'Ident (Prolog Variable)').
'symbol'('bb2','bb','src_span'(106,34,106,36,3253,2),'Ident (Prolog Variable)').
'symbol'('qNo2','qNo','src_span'(114,20,114,23,3477,3),'Ident (Prolog Variable)').
'symbol'('NextQCtrl','NextQCtrl','src_span'(117,1,117,10,3567,9),'Funktion or Process').
'symbol'('s2','s','src_span'(117,11,117,12,3577,1),'Ident (Prolog Variable)').
'symbol'('queueNo2','queueNo','src_span'(117,13,117,20,3579,7),'Ident (Prolog Variable)').
'symbol'('bb3','bb','src_span'(118,32,118,34,3622,2),'Ident (Prolog Variable)').
'symbol'('cc5','cc','src_span'(120,28,120,30,3681,2),'Ident (Prolog Variable)').
'symbol'('A','A','src_span'(124,1,124,2,3774,1),'Ident (Groundrep.)').
'symbol'('B','B','src_span'(125,1,125,2,3831,1),'Ident (Groundrep.)').
'symbol'('BankSystem','BankSystem','src_span'(126,1,126,11,3950,10),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(126,51,126,56,4000,5),'BuiltIn primitive').
'symbol'('BankSystemNoHide','BankSystemNoHide','src_span'(127,1,127,17,4011,16),'Ident (Groundrep.)').
'symbol'('SPEC','SPEC','src_span'(129,1,129,5,4065,4),'Ident (Groundrep.)').
'symbol'('i','i','src_span'(129,12,129,13,4076,1),'Ident (Prolog Variable)').
'symbol'('CUST','CUST','src_span'(130,1,130,5,4099,4),'Ident (Groundrep.)').
'symbol'('i2','i','src_span'(130,18,130,19,4116,1),'Ident (Prolog Variable)').
'symbol'('SPEC2','SPEC2','src_span'(137,1,137,6,4317,5),'Ident (Groundrep.)').
'symbol'('NEWSPEC','NEWSPEC','src_span'(138,1,138,8,4336,7),'Funktion or Process').
'symbol'('num4','num','src_span'(138,9,138,12,4344,3),'Ident (Prolog Variable)').
'symbol'('cc6','cc','src_span'(139,32,139,34,4383,2),'Ident (Prolog Variable)').
'symbol'('cc7','cc','src_span'(142,18,142,20,4490,2),'Ident (Prolog Variable)').