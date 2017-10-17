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
'agent'('CurrentCtrl'(_num),'[]'('agent_call'('src_span'(58,5,58,13,1335,8),'JoinCtrl',[_num]),'agent_call'('src_span'(58,21,58,30,1351,9),'LeaveCtrl',[_num]),'src_span_operator'('no_loc_info_available','src_span'(58,19,58,20,1349,1))),'no_loc_info_available').
'agent'('JoinCtrl'(_num2),'&'('<'(_num2,'val_of'('maxLimit','src_span'(66,9,66,17,1531,8))),'prefix'('src_span'(66,20,66,29,1542,9),['in'(_cc)],'enterBank','[]'('&'('=='(_num2,'int'(0)),'prefix'('src_span'(67,20,67,34,1577,14),[],'dotTuple'(['report','success']),'prefix'('src_span'(67,37,67,51,1594,14),[],'dotTuple'(['setCustomer',_cc]),'agent_call'('src_span'(67,54,67,65,1611,11),'CurrentCtrl',['+'(_num2,'int'(1))]),'src_span'(67,52,67,53,1608,35)),'src_span'(67,35,67,36,1591,52))),'&'('>'(_num2,'int'(0)),'prefix'('src_span'(69,20,69,31,1660,11),['in'(_currentCust)],'getCustomer','ifte'('=='(_cc,_currentCust),'prefix'('src_span'(70,41,70,52,1727,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(70,55,70,66,1741,11),'CurrentCtrl',[_num2]),'src_span'(70,53,70,54,1738,30)),'prefix'('src_span'(72,20,72,24,1796,4),['out'(_cc)],'com1','prefix'('src_span'(72,30,72,45,1806,15),['in'(_bb)],'canJoinResponse','prefix'('src_span'(72,51,72,60,1827,9),[],'dotTuple'(['report',_bb]),'ifte'('=='(_bb,'success'),'agent_call'('src_span'(74,24,74,35,1903,11),'CurrentCtrl',['+'(_num2,'int'(1))]),'agent_call'('src_span'(76,24,76,35,1970,11),'CurrentCtrl',[_num2]),'src_span'(73,21,73,36,1859,15),'src_span'(73,37,74,23,1874,59),'src_span'(74,43,76,23,1921,83)),'src_span'(72,61,73,19,1836,160)),'src_span'(72,49,72,50,1824,166)),'src_span'(72,28,72,29,1803,187)),'src_span'(70,14,70,35,1700,21),'src_span'(70,36,70,40,1721,54),'src_span'(70,72,72,19,1757,260)),'src_span'(69,44,70,13,1683,316))),'src_span_operator'('no_loc_info_available','src_span'(68,9,68,10,1639,1))),'src_span'(66,34,67,3,1555,455))),'no_loc_info_available').
'agent'('LeaveCtrl'(_num3),'[]'('&'('=='(_num3,'int'(1)),'prefix'('src_span'(91,20,91,31,2548,11),['in'(_cc2)],'getCustomer','prefix'('src_span'(91,38,91,47,2566,9),['out'(_cc2)],'leaveBank','prefix'('src_span'(92,10,92,37,2590,27),[],'dotTuple'(['setCustomer','val_of'('defaultCustomer','src_span'(92,22,92,37,2602,15))]),'agent_call'('src_span'(92,40,92,51,2620,11),'CurrentCtrl',['int'(0)]),'src_span'(92,38,92,39,2617,44)),'src_span'(91,51,92,9,2578,59)),'src_span'(91,35,91,37,2562,75))),'&'('>'(_num3,'int'(1)),'prefix'('src_span'(94,21,94,32,2667,11),['in'(_cc3)],'getCustomer','prefix'('src_span'(94,38,94,47,2684,9),['out'(_cc3)],'leaveBank','prefix'('src_span'(95,15,95,25,2714,10),['in'(_qNo)],'getQueueNo','prefix'('src_span'(95,32,95,36,2731,4),['out'(_qNo)],'com2','prefix'('src_span'(95,43,95,47,2742,4),['in'(_newCust),'in'(_newQNo)],'com3','prefix'('src_span'(96,15,96,26,2779,11),['out'(_newCust)],'setCustomer','prefix'('src_span'(96,37,96,49,2801,12),['out'(_newQNo)],'setNextQueue','agent_call'('src_span'(96,59,96,70,2823,11),'CurrentCtrl',['-'(_num3,'int'(1))]),'src_span'(96,57,96,58,2820,28)),'src_span'(96,35,96,36,2798,51)),'src_span'(95,63,96,14,2761,87)),'src_span'(95,41,95,42,2739,106)),'src_span'(95,30,95,31,2728,117)),'src_span'(94,51,95,14,2696,148)),'src_span'(94,36,94,37,2681,163))),'src_span_operator'('no_loc_info_available','src_span'(93,10,93,11,2645,1))),'no_loc_info_available').
'bindval'('QueuesCtrl','agent_call'('src_span'(103,14,103,19,3127,5),'QCtrl',['int'(0)]),'src_span'(103,1,103,22,3114,21)).
'agent'('QCtrl'(_s),'[]'('&'('<'(_s,'val_of'('maxQueueingCustomers','src_span'(105,11,105,31,3158,20))),'prefix'('src_span'(105,34,105,38,3181,4),['in'(_cc4)],'com1','prefix'('src_span'(106,16,106,30,3209,14),['out'(_cc4),'in'(_bb2)],'queryIsInQueue','ifte'('=='(_bb2,'yes'),'prefix'('src_span'(108,19,108,34,3285,15),['out'('fail')],'canJoinResponse','agent_call'('src_span'(108,42,108,47,3308,5),'QCtrl',[_s]),'src_span'(108,40,108,41,3305,16)),'prefix'('src_span'(110,19,110,34,3355,15),['out'('success')],'canJoinResponse','prefix'('src_span'(110,45,110,57,3381,12),[],'dotTuple'(['joinQueue',_cc4]),'agent_call'('src_span'(111,5,111,10,3400,5),'QCtrl',['+'(_s,'int'(1))]),'src_span'(110,58,111,4,3393,29)),'src_span'(110,43,110,44,3378,40)),'src_span'(107,16,107,27,3247,11),'src_span'(107,28,108,18,3258,66),'src_span'(108,51,110,18,3316,125)),'src_span'(106,37,107,15,3229,184)),'src_span'(105,44,106,15,3190,224))),'&'('>'(_s,'int'(0)),'prefix'('src_span'(114,15,114,19,3440,4),['in'(_qNo2)],'com2','agent_call'('src_span'(114,26,114,35,3451,9),'NextQCtrl',[_s,_qNo2]),'src_span'(114,24,114,25,3448,23))),'src_span_operator'('no_loc_info_available','src_span'(112,6,112,7,3417,1))),'no_loc_info_available').
'agent'('NextQCtrl'(_s2,_queueNo2),'prefix'('src_span'(118,8,118,23,3565,15),['out'(_queueNo2),'in'(_bb3)],'queryQueueEmpty','ifte'('=='(_bb3,'no'),'prefix'('src_span'(120,9,120,19,3627,10),['out'(_queueNo2),'in'(_cc5)],'leaveQueue','prefix'('src_span'(120,33,120,37,3651,4),['out'(_cc5),'out'('agent_call'('src_span'(120,41,120,44,3659,3),'inc',[_queueNo2]))],'com3','agent_call'('src_span'(120,56,120,61,3674,5),'QCtrl',['-'(_s2,'int'(1))]),'src_span'(120,54,120,55,3671,26)),'src_span'(120,31,120,32,3648,39)),'agent_call'('src_span'(122,14,122,23,3710,9),'NextQCtrl',[_s2,'agent_call'('src_span'(122,26,122,29,3722,3),'inc',[_queueNo2])]),'src_span'(119,8,119,20,3601,12),'src_span'(119,21,120,8,3613,80),'src_span'(120,67,122,13,3684,108)),'src_span'(118,35,119,7,3591,147)),'src_span'(118,8,122,39,3565,170)).
'bindval'('A','closure'(['com1','com2','com3','canJoinResponse','retrieveCustomer']),'src_span'(124,1,124,57,3737,56)).
'bindval'('B','closure'(['joinQueue','leaveQueue','queryQueueEmpty','queryIsInQueue','getCustomer','setCustomer','getQueueNo','setNextQueue']),'src_span'(125,1,125,119,3794,118)).
'bindval'('BankSystem','\x5c\'('sharing'('val_of'('A','src_span'(126,33,126,34,3945,1)),'val_of'('BankCounterCtrl','src_span'(126,15,126,30,3927,15)),'val_of'('QueuesCtrl','src_span'(126,37,126,47,3949,10)),'src_span'(126,31,126,36,3943,5)),'agent_call'('src_span'(126,51,126,56,3963,5),'union',['val_of'('A','src_span'(126,57,126,58,3969,1)),'val_of'('B','src_span'(126,59,126,60,3971,1))]),'src_span_operator'('no_loc_info_available','src_span'(126,49,126,50,3961,1))),'src_span'(126,1,126,61,3913,60)).
'bindval'('BankSystemNoHide','sharing'('val_of'('A','src_span'(127,38,127,39,4011,1)),'val_of'('BankCounterCtrl','src_span'(127,20,127,35,3993,15)),'val_of'('QueuesCtrl','src_span'(127,42,127,52,4015,10)),'src_span'(127,36,127,41,4009,5)),'src_span'(127,1,127,52,3974,51)).
'bindval'('SPEC','repInterleave'(['comprehensionGenerator'(_i,'setExp'('rangeClosed'('int'(1),'val_of'('maxLimit','src_span'(129,18,129,26,4045,8)))))],'val_of'('CUST','src_span'(129,30,129,34,4057,4)),'src_span'(129,12,129,29,4039,17)),'src_span'(129,1,129,34,4028,33)).
'bindval'('CUST','prefix'('src_span'(130,8,130,17,4069,9),['in'(_i2)],'enterBank','[]'('prefix'('src_span'(130,23,130,34,4084,11),[],'dotTuple'(['report','fail']),'val_of'('CUST','src_span'(130,37,130,41,4098,4)),'src_span'(130,35,130,36,4095,18)),'prefix'('src_span'(130,44,130,58,4105,14),[],'dotTuple'(['report','success']),'prefix'('src_span'(131,29,131,40,4150,11),[],'dotTuple'(['leaveBank',_i2]),'val_of'('CUST','src_span'(131,43,131,47,4164,4)),'src_span'(131,41,131,42,4161,18)),'src_span'(130,59,131,28,4119,63)),'src_span_operator'('no_loc_info_available','src_span'(130,42,130,43,4103,1))),'src_span'(130,20,130,21,4080,91)),'src_span'(130,1,131,48,4062,107)).
'bindval'('SPEC2','agent_call'('src_span'(137,9,137,16,4283,7),'NEWSPEC',['int'(0)]),'src_span'(137,1,137,19,4275,18)).
'agent'('NEWSPEC'(_num4),'[]'('&'('<'(_num4,'val_of'('maxLimit','src_span'(139,10,139,18,4319,8))),'prefix'('src_span'(139,21,139,30,4330,9),['in'(_cc6)],'enterBank','|~|'('prefix'('src_span'(140,12,140,26,4358,14),[],'dotTuple'(['report','success']),'agent_call'('src_span'(140,29,140,36,4375,7),'NEWSPEC',['+'(_num4,'int'(1))]),'src_span'(140,27,140,28,4372,31)),'prefix'('src_span'(140,46,140,57,4392,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(140,60,140,67,4406,7),'NEWSPEC',[_num4]),'src_span'(140,58,140,59,4403,26)),'src_span_operator'('no_loc_info_available','src_span'(140,44,140,45,4390,1))),'src_span'(139,35,140,10,4343,80))),'&'('>'(_num4,'int'(0)),'repInternalChoice'(['comprehensionGenerator'(_cc7,'CUSTOMER')],'prefix'('src_span'(142,31,142,44,4455,13),[],'dotTuple'(['leaveBank',_cc7]),'agent_call'('src_span'(142,47,142,54,4471,7),'NEWSPEC',['-'(_num4,'int'(1))]),'src_span'(142,45,142,46,4468,30)),'src_span'(142,16,142,30,4440,14))),'src_span_operator'('no_loc_info_available','src_span'(141,4,141,5,4423,1))),'no_loc_info_available').
'assertRef'('False','val_of'('SPEC','src_span'(145,8,145,12,4495,4)),'Trace','val_of'('BankSystem','src_span'(145,17,145,27,4504,10)),'src_span'(145,1,145,27,4488,26)).
'assertRef'('False','val_of'('SPEC','src_span'(146,8,146,12,4522,4)),'Failure','val_of'('BankSystem','src_span'(146,17,146,27,4531,10)),'src_span'(146,1,146,27,4515,26)).
'assertRef'('False','val_of'('SPEC2','src_span'(148,8,148,13,4550,5)),'Trace','val_of'('BankSystem','src_span'(148,18,148,28,4560,10)),'src_span'(148,1,148,28,4543,27)).
'assertRef'('False','val_of'('SPEC2','src_span'(149,8,149,13,4578,5)),'Failure','val_of'('BankSystem','src_span'(149,18,149,28,4588,10)),'src_span'(149,1,149,28,4571,27)).
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
'comment'('lineComment'('-- we allow any input but then the queues do the checking about'),'src_position'(60,1,1367,63)).
'comment'('lineComment'('-- passing com1 access '),'src_position'(61,1,1431,23)).
'comment'('lineComment'('-- whether the customer is already in the queue '),'src_position'(62,1,1455,48)).
'comment'('lineComment'('-- don\x27\t need to set back to default because could put it as cust'),'src_position'(80,1,2033,65)).
'comment'('lineComment'('--because never going to use that value again'),'src_position'(81,1,2099,45)).
'comment'('lineComment'('-- but what if the size of that queue is empty the we should precoess'),'src_position'(82,1,2145,69)).
'comment'('lineComment'('--the next one along, where is that info going to be kept'),'src_position'(83,1,2215,57)).
'comment'('lineComment'('-- with the com3 is there goign to be a deadlock again with requesting'),'src_position'(84,1,2273,70)).
'comment'('lineComment'('--value, do we need an atomic event to prevent that to signal update'),'src_position'(85,1,2344,68)).
'comment'('lineComment'('--sequence of events'),'src_position'(86,1,2413,20)).
'comment'('lineComment'('-- if > 1 then output the currentperson and get the next one from a'),'src_position'(87,1,2434,67)).
'comment'('lineComment'('--queue'),'src_position'(88,1,2502,7)).
'comment'('lineComment'('--              retrieveCustomer -> getQueueNo?qNo -> com2!qNo -> com3?newCust?newQNo -> '),'src_position'(97,1,2843,89)).
'comment'('lineComment'('--              setCustomer!newCust -> setNextQueue!newQNo -> CurrentCtrl(num-1))'),'src_position'(98,1,2933,81)).
'comment'('lineComment'('-- now queues is tracking the counter and is making the choice of'),'src_position'(100,1,3016,65)).
'comment'('lineComment'('--which queue to get info from'),'src_position'(101,1,3082,30)).
'comment'('lineComment'('--   (s > 0 & retrieveCustomer -> com2?qNo -> NextQCtrl(s,qNo))'),'src_position'(115,1,3469,63)).
'comment'('lineComment'('-- Need to set maxQueueingCustomers to 2 for this check to succeed in'),'src_position'(134,1,4172,69)).
'comment'('lineComment'('-- any sort of reasonable time.'),'src_position'(135,1,4242,31)).
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
'symbol'('JoinCtrl','JoinCtrl','src_span'(65,1,65,9,1506,8),'Funktion or Process').
'symbol'('num2','num','src_span'(65,10,65,13,1515,3),'Ident (Prolog Variable)').
'symbol'('cc','cc','src_span'(66,31,66,33,1553,2),'Ident (Prolog Variable)').
'symbol'('currentCust','currentCust','src_span'(69,32,69,43,1672,11),'Ident (Prolog Variable)').
'symbol'('bb','bb','src_span'(72,46,72,48,1822,2),'Ident (Prolog Variable)').
'symbol'('LeaveCtrl','LeaveCtrl','src_span'(90,1,90,10,2511,9),'Funktion or Process').
'symbol'('num3','num','src_span'(90,11,90,14,2521,3),'Ident (Prolog Variable)').
'symbol'('cc2','cc','src_span'(91,32,91,34,2560,2),'Ident (Prolog Variable)').
'symbol'('cc3','cc','src_span'(94,33,94,35,2679,2),'Ident (Prolog Variable)').
'symbol'('qNo','qNo','src_span'(95,26,95,29,2725,3),'Ident (Prolog Variable)').
'symbol'('newCust','newCust','src_span'(95,48,95,55,2747,7),'Ident (Prolog Variable)').
'symbol'('newQNo','newQNo','src_span'(95,56,95,62,2755,6),'Ident (Prolog Variable)').
'symbol'('QueuesCtrl','QueuesCtrl','src_span'(103,1,103,11,3114,10),'Ident (Groundrep.)').
'symbol'('QCtrl','QCtrl','src_span'(104,1,104,6,3136,5),'Funktion or Process').
'symbol'('s','s','src_span'(104,7,104,8,3142,1),'Ident (Prolog Variable)').
'symbol'('cc4','cc','src_span'(105,41,105,43,3188,2),'Ident (Prolog Variable)').
'symbol'('bb2','bb','src_span'(106,34,106,36,3227,2),'Ident (Prolog Variable)').
'symbol'('qNo2','qNo','src_span'(114,20,114,23,3445,3),'Ident (Prolog Variable)').
'symbol'('NextQCtrl','NextQCtrl','src_span'(117,1,117,10,3534,9),'Funktion or Process').
'symbol'('s2','s','src_span'(117,11,117,12,3544,1),'Ident (Prolog Variable)').
'symbol'('queueNo2','queueNo','src_span'(117,13,117,20,3546,7),'Ident (Prolog Variable)').
'symbol'('bb3','bb','src_span'(118,32,118,34,3589,2),'Ident (Prolog Variable)').
'symbol'('cc5','cc','src_span'(120,28,120,30,3646,2),'Ident (Prolog Variable)').
'symbol'('A','A','src_span'(124,1,124,2,3737,1),'Ident (Groundrep.)').
'symbol'('B','B','src_span'(125,1,125,2,3794,1),'Ident (Groundrep.)').
'symbol'('BankSystem','BankSystem','src_span'(126,1,126,11,3913,10),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(126,51,126,56,3963,5),'BuiltIn primitive').
'symbol'('BankSystemNoHide','BankSystemNoHide','src_span'(127,1,127,17,3974,16),'Ident (Groundrep.)').
'symbol'('SPEC','SPEC','src_span'(129,1,129,5,4028,4),'Ident (Groundrep.)').
'symbol'('i','i','src_span'(129,12,129,13,4039,1),'Ident (Prolog Variable)').
'symbol'('CUST','CUST','src_span'(130,1,130,5,4062,4),'Ident (Groundrep.)').
'symbol'('i2','i','src_span'(130,18,130,19,4079,1),'Ident (Prolog Variable)').
'symbol'('SPEC2','SPEC2','src_span'(137,1,137,6,4275,5),'Ident (Groundrep.)').
'symbol'('NEWSPEC','NEWSPEC','src_span'(138,1,138,8,4294,7),'Funktion or Process').
'symbol'('num4','num','src_span'(138,9,138,12,4302,3),'Ident (Prolog Variable)').
'symbol'('cc6','cc','src_span'(139,32,139,34,4341,2),'Ident (Prolog Variable)').
'symbol'('cc7','cc','src_span'(142,16,142,18,4440,2),'Ident (Prolog Variable)').