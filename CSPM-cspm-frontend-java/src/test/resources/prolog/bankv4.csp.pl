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
'bindval'('QUEUENUM','setExp'('rangeClosed'('int'(1),'val_of'('numQueues','src_span'(7,16,7,25,219,9)))),'src_span'(7,1,7,26,204,25)).
'dataTypeDef'('CUSTOMER',['constructor'('c1'),'constructor'('c2'),'constructor'('c3'),'constructor'('c4'),'constructor'('c5'),'constructor'('c6')]).
'dataTypeDef'('STATUS',['constructor'('success'),'constructor'('fail')]).
'dataTypeDef'('QSTATUS',['constructor'('yes'),'constructor'('no')]).
'bindval'('maxLimit','int'(2),'src_span'(12,1,12,13,339,12)).
'bindval'('defaultCounter','int'(1),'src_span'(13,1,13,20,352,19)).
'bindval'('defaultCustomer','c1','src_span'(14,1,14,21,372,20)).
'bindval'('maxQueueingCustomers','-'('val_of'('maxLimit','src_span'(15,24,15,32,416,8)),'int'(1)),'src_span'(15,1,15,35,393,34)).
'bindval'('numQueues','int'(2),'src_span'(16,1,16,14,428,13)).
'channel'('enterBank','type'('dotTupleType'(['CUSTOMER']))).
'channel'('leaveBank','type'('dotTupleType'(['CUSTOMER']))).
'channel'('report','type'('dotTupleType'(['STATUS']))).
'channel'('com1','type'('dotTupleType'(['CUSTOMER']))).
'channel'('com3','type'('dotTupleType'(['CUSTOMER']))).
'channel'('canJoinResponse','type'('dotTupleType'(['STATUS']))).
'channel'('retrieveCustomer','type'('dotUnitType')).
'channel'('joinQueue','type'('dotTupleType'(['CUSTOMER']))).
'channel'('leaveQueue','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(32,22,32,30,791,8)),'CUSTOMER']))).
'channel'('queryQueueEmpty','type'('dotTupleType'(['val_of'('QUEUENUM','src_span'(33,27,33,35,835,8)),'QSTATUS']))).
'agent'('inc'(_queueNo),'+'('%'(_queueNo,'val_of'('numQueues','src_span'(36,27,36,36,880,9))),'int'(1)),'no_loc_info_available').
'agent'('dec'(_queueNo2),'+'('%'('-'(_queueNo2,'int'(2)),'val_of'('numQueues','src_span'(37,33,37,42,927,9))),'int'(1)),'no_loc_info_available').
'bindval'('CounterCtrl','agent_call'('src_span'(51,15,51,26,1292,11),'CurrentCtrl',['int'(0),'val_of'('defaultCustomer','src_span'(51,29,51,44,1306,15))]),'src_span'(51,1,51,45,1278,44)).
'agent'('CurrentCtrl'(_num,_currentCust),'[]'('agent_call'('src_span'(54,5,54,13,1361,8),'JoinCtrl',[_num,_currentCust]),'agent_call'('src_span'(54,35,54,44,1391,9),'LeaveCtrl',[_num,_currentCust]),'src_span_operator'('no_loc_info_available','src_span'(54,32,54,34,1388,2))),'no_loc_info_available').
'agent'('JoinCtrl'(_num2,_currentCust2),'&'('<'(_num2,'val_of'('maxLimit','src_span'(62,9,62,17,1595,8))),'prefix'('src_span'(62,20,62,29,1606,9),['in'(_cc)],'enterBank','[]'('[]'('&'('=='(_num2,'int'(0)),'prefix'('src_span'(63,21,63,35,1643,14),[],'dotTuple'(['report','success']),'agent_call'('src_span'(63,39,63,50,1661,11),'CurrentCtrl',['+'(_num2,'int'(1)),_cc]),'src_span'(63,36,63,38,1657,39))),'&'('bool_and'('>'(_num2,'int'(0)),'bool_not'('=='(_cc,_currentCust2))),'prefix'('src_span'(65,47,65,51,1741,4),['out'(_cc)],'com1','prefix'('src_span'(66,23,66,38,1776,15),['in'(_bb)],'canJoinResponse','prefix'('src_span'(66,48,66,57,1801,9),[],'dotTuple'(['report',_bb]),'ifte'('=='(_bb,'success'),'agent_call'('src_span'(68,27,68,38,1888,11),'CurrentCtrl',['+'(_num2,'int'(1)),_currentCust2]),'agent_call'('src_span'(70,27,70,38,1976,11),'CurrentCtrl',[_num2,_currentCust2]),'src_span'(67,25,67,43,1838,18),'src_span'(67,44,68,26,1856,77),'src_span'(68,58,70,26,1918,116)),'src_span'(66,58,67,24,1810,203)),'src_span'(66,45,66,47,1797,211)),'src_span'(65,56,66,22,1749,259))),'src_span_operator'('no_loc_info_available','src_span'(64,9,64,11,1692,2))),'&'('bool_and'('>'(_num2,'int'(0)),'=='(_cc,_currentCust2)),'prefix'('src_span'(72,47,72,58,2063,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(72,62,72,73,2078,11),'CurrentCtrl',[_num2,_currentCust2]),'src_span'(72,59,72,61,2074,43))),'src_span_operator'('no_loc_info_available','src_span'(71,9,71,11,2014,2))),'src_span'(62,34,63,3,1619,493))),'no_loc_info_available').
'agent'('LeaveCtrl'(_num3,_currentCust3),'[]'('&'('=='(_num3,'int'(1)),'prefix'('src_span'(85,21,85,30,2663,9),['out'(_currentCust3)],'leaveBank','agent_call'('src_span'(85,46,85,57,2688,11),'CurrentCtrl',['int'(0),'val_of'('defaultCustomer','src_span'(85,60,85,75,2702,15))]),'src_span'(85,43,85,45,2684,46))),'&'('>'(_num3,'int'(1)),'prefix'('src_span'(87,21,87,30,2752,9),['out'(_currentCust3)],'leaveBank','prefix'('src_span'(87,46,87,62,2777,16),[],'retrieveCustomer','prefix'('src_span'(87,66,87,70,2797,4),['in'(_cc2)],'com3','agent_call'('src_span'(87,77,87,88,2808,11),'CurrentCtrl',['-'(_num3,'int'(1)),_cc2]),'src_span'(87,74,87,76,2804,28)),'src_span'(87,63,87,65,2793,52)),'src_span'(87,43,87,45,2773,68))),'src_span_operator'('no_loc_info_available','src_span'(86,10,86,12,2729,2))),'no_loc_info_available').
'bindval'('QueuesCtrl','agent_call'('src_span'(92,14,92,19,2943,5),'QCtrl',['int'(0),'int'(1),'setExp'('rangeEnum'([]))]),'src_span'(92,1,92,27,2930,26)).
'agent'('QCtrl'(_s,_queueNo3,_custSet),'[]'('&'('<'(_s,'val_of'('maxQueueingCustomers','src_span'(95,11,95,31,2996,20))),'prefix'('src_span'(95,34,95,38,3019,4),['in'(_cc3)],'com1','ifte'('agent_call'('src_span'(96,20,96,26,3052,6),'member',[_cc3,_custSet]),'prefix'('src_span'(97,19,97,34,3098,15),['out'('fail')],'canJoinResponse','agent_call'('src_span'(97,43,97,48,3122,5),'QCtrl',[_s,_queueNo3,_custSet]),'src_span'(97,40,97,42,3118,33)),'prefix'('src_span'(99,19,99,34,3185,15),['out'('success')],'canJoinResponse','prefix'('src_span'(99,46,99,58,3212,12),[],'dotTuple'(['joinQueue',_cc3]),'agent_call'('src_span'(100,5,100,10,3232,5),'QCtrl',['+'(_s,'int'(1)),_queueNo3,'agent_call'('src_span'(100,23,100,28,3250,5),'union',[_custSet,'setExp'('rangeEnum'([_cc3]))])]),'src_span'(99,59,100,4,3224,60)),'src_span'(99,43,99,45,3208,72)),'src_span'(96,16,96,39,3048,23),'src_span'(96,40,97,18,3071,95),'src_span'(97,68,99,18,3146,174)),'src_span'(95,44,96,15,3028,248))),'&'('>'(_s,'int'(0)),'prefix'('src_span'(103,15,103,31,3304,16),[],'retrieveCustomer','agent_call'('src_span'(103,35,103,44,3324,9),'NextQCtrl',[_s,_queueNo3,'agent_call'('src_span'(103,55,103,58,3344,3),'dec',[_queueNo3]),_custSet]),'src_span'(103,32,103,34,3320,61))),'src_span_operator'('no_loc_info_available','src_span'(101,6,101,8,3280,2))),'no_loc_info_available').
'agent'('NextQCtrl'(_s2,_queueNo4,_lastQueueNo,_custSet2),'[]'('&'('bool_not'('=='(_queueNo4,_lastQueueNo)),'prefix'('src_span'(110,43,110,58,3591,15),['out'(_queueNo4),'in'(_bb2)],'queryQueueEmpty','ifte'('=='(_bb2,'no'),'prefix'('src_span'(112,14,112,24,3664,10),['out'(_queueNo4),'inGuard'(_cc4,_custSet2)],'leaveQueue','prefix'('src_span'(112,49,112,53,3699,4),['out'(_cc4)],'com3','agent_call'('src_span'(112,60,112,65,3710,5),'QCtrl',['-'(_s2,'int'(1)),'agent_call'('src_span'(112,70,112,73,3720,3),'inc',[_queueNo4]),'agent_call'('src_span'(112,83,112,87,3733,4),'diff',[_custSet2,'setExp'('rangeEnum'([_cc4]))])]),'src_span'(112,57,112,59,3706,49)),'src_span'(112,46,112,48,3695,70)),'agent_call'('src_span'(114,14,114,23,3782,9),'NextQCtrl',[_s2,'agent_call'('src_span'(114,26,114,29,3794,3),'inc',[_queueNo4]),_lastQueueNo,_custSet2]),'src_span'(111,12,111,25,3632,13),'src_span'(111,26,112,13,3645,117),'src_span'(112,103,114,13,3752,163)),'src_span'(110,70,111,11,3617,213))),'&'('=='(_queueNo4,_lastQueueNo),'prefix'('src_span'(116,40,116,55,3882,15),['out'('dotTuple'([_queueNo4,'no']))],'queryQueueEmpty','prefix'('src_span'(117,15,117,25,3926,10),['out'(_queueNo4),'inGuard'(_cc5,_custSet2)],'leaveQueue','prefix'('src_span'(117,50,117,54,3961,4),['out'(_cc5)],'com3','agent_call'('src_span'(117,61,117,66,3972,5),'QCtrl',['-'(_s2,'int'(1)),'agent_call'('src_span'(117,71,117,74,3982,3),'inc',[_queueNo4]),'agent_call'('src_span'(117,84,117,88,3995,4),'diff',[_custSet2,'setExp'('rangeEnum'([_cc5]))])]),'src_span'(117,58,117,60,3968,49)),'src_span'(117,47,117,49,3957,70)),'src_span'(116,67,117,14,3908,117))),'src_span_operator'('no_loc_info_available','src_span'(115,12,115,14,3840,2))),'no_loc_info_available').
'bindval'('A','closure'(['com1','com3','canJoinResponse','retrieveCustomer']),'src_span'(125,1,125,51,4316,50)).
'bindval'('B','closure'(['joinQueue','leaveQueue','queryQueueEmpty']),'src_span'(126,1,126,52,4367,51)).
'bindval'('BankSystem','\x5c\'('sharing'('val_of'('A','src_span'(128,28,128,29,4447,1)),'val_of'('CounterCtrl','src_span'(128,14,128,25,4433,11)),'val_of'('QueuesCtrl','src_span'(128,32,128,42,4451,10)),'src_span'(128,26,128,31,4445,5)),'agent_call'('src_span'(128,45,128,50,4464,5),'union',['val_of'('A','src_span'(128,51,128,52,4470,1)),'val_of'('B','src_span'(128,53,128,54,4472,1))]),'src_span_operator'('no_loc_info_available','src_span'(128,43,128,44,4462,1))),'src_span'(128,1,128,55,4420,54)).
'bindval'('FullBankSystem','sharing'('val_of'('A','src_span'(130,32,130,33,4507,1)),'val_of'('CounterCtrl','src_span'(130,18,130,29,4493,11)),'val_of'('QueuesCtrl','src_span'(130,36,130,46,4511,10)),'src_span'(130,30,130,35,4505,5)),'src_span'(130,1,130,46,4476,45)).
'bindval'('SPEC','repInterleave'(['comprehensionGenerator'(_i,'setExp'('rangeClosed'('int'(1),'val_of'('maxLimit','src_span'(134,18,134,26,4580,8)))))],'val_of'('CUST','src_span'(134,30,134,34,4592,4)),'src_span'(134,12,134,29,4574,17)),'src_span'(134,1,134,34,4563,33)).
'bindval'('CUST','prefix'('src_span'(135,8,135,17,4604,9),['in'(_i2)],'enterBank','[]'('prefix'('src_span'(135,24,135,35,4620,11),[],'dotTuple'(['report','fail']),'val_of'('CUST','src_span'(135,39,135,43,4635,4)),'src_span'(135,36,135,38,4631,19)),'prefix'('src_span'(135,47,135,61,4643,14),[],'dotTuple'(['report','success']),'prefix'('src_span'(136,29,136,40,4689,11),[],'dotTuple'(['leaveBank',_i2]),'val_of'('CUST','src_span'(136,44,136,48,4704,4)),'src_span'(136,41,136,43,4700,19)),'src_span'(135,62,136,28,4657,65)),'src_span_operator'('no_loc_info_available','src_span'(135,44,135,46,4640,2))),'src_span'(135,20,135,22,4615,96)),'src_span'(135,1,136,49,4597,112)).
'bindval'('SPEC2','agent_call'('src_span'(140,9,140,16,4809,7),'NEWSPEC',['int'(0)]),'src_span'(140,1,140,19,4801,18)).
'agent'('NEWSPEC'(_num4),'[]'('&'('<'(_num4,'val_of'('maxLimit','src_span'(142,10,142,18,4845,8))),'prefix'('src_span'(142,21,142,30,4856,9),['in'(_cc6)],'enterBank','|~|'('prefix'('src_span'(143,12,143,26,4885,14),[],'dotTuple'(['report','success']),'agent_call'('src_span'(143,30,143,37,4903,7),'NEWSPEC',['+'(_num4,'int'(1))]),'src_span'(143,27,143,29,4899,32)),'prefix'('src_span'(143,49,143,60,4922,11),[],'dotTuple'(['report','fail']),'agent_call'('src_span'(143,64,143,71,4937,7),'NEWSPEC',[_num4]),'src_span'(143,61,143,63,4933,27)),'src_span_operator'('no_loc_info_available','src_span'(143,45,143,48,4918,3))),'src_span'(142,35,143,10,4869,85))),'&'('>'(_num4,'int'(0)),'repInternalChoice'(['comprehensionGenerator'(_cc7,'CUSTOMER')],'prefix'('src_span'(145,33,145,46,4989,13),[],'dotTuple'(['leaveBank',_cc7]),'agent_call'('src_span'(145,50,145,57,5006,7),'NEWSPEC',['-'(_num4,'int'(1))]),'src_span'(145,47,145,49,5002,31)),'src_span'(145,18,145,32,4974,14))),'src_span_operator'('no_loc_info_available','src_span'(144,4,144,6,4954,2))),'no_loc_info_available').
'assertRef'('False','val_of'('SPEC2','src_span'(148,8,148,13,5030,5)),'Trace','val_of'('BankSystem','src_span'(148,18,148,28,5040,10)),'src_span'(148,1,148,28,5023,27)).
'assertRef'('False','val_of'('SPEC2','src_span'(149,8,149,13,5058,5)),'Failure','val_of'('BankSystem','src_span'(149,18,149,28,5068,10)),'src_span'(149,1,149,28,5051,27)).
'assertRef'('False','val_of'('SPEC','src_span'(152,9,152,13,5089,4)),'Trace','val_of'('BankSystem','src_span'(152,18,152,28,5098,10)),'src_span'(152,2,152,28,5082,26)).
'assertRef'('False','val_of'('SPEC','src_span'(153,9,153,13,5117,4)),'Failure','val_of'('BankSystem','src_span'(153,18,153,28,5126,10)),'src_span'(153,2,153,28,5110,26)).
'comment'('lineComment'('-- Example controllers to accompany "ZB2003" paper'),'src_position'(1,1,0,50)).
'comment'('lineComment'('-- version 4 with guards and not assumptions'),'src_position'(2,1,51,44)).
'comment'('lineComment'('-- NextQCtrl is adapted with an additional state variable in order to'),'src_position'(4,1,97,69)).
'comment'('lineComment'('-- prevent divergence of BankSystem'),'src_position'(5,1,167,35)).
'comment'('lineComment'('-- external channels'),'src_position'(19,1,444,20)).
'comment'('lineComment'('-- communication channels, no underlying B, between CSP processes'),'src_position'(24,1,548,65)).
'comment'('lineComment'('-- machine channels'),'src_position'(30,1,721,19)).
'comment'('lineComment'('-- abstract specfication'),'src_position'(39,1,943,24)).
'comment'('lineComment'('-- num is the number of people waiting and the one being serviced'),'src_position'(40,1,968,65)).
'comment'('lineComment'('--Spec  = Waiting(0,<>)'),'src_position'(42,1,1035,23)).
'comment'('lineComment'('--Waiting(num,people_waiting) = '),'src_position'(43,1,1059,32)).
'comment'('lineComment'('--   num < bankLimit & input ? cc -> Waiting(num+1, people_waiting ^<cc>)'),'src_position'(44,1,1092,73)).
'comment'('lineComment'('--   []'),'src_position'(45,1,1166,7)).
'comment'('lineComment'('--   num > 0 & |~| cc : people_waiting @ output. cc ->'),'src_position'(46,1,1174,54)).
'comment'('lineComment'('--   Waiting(num - 1, .....'),'src_position'(47,1,1229,27)).
'comment'('lineComment'('--- implementation'),'src_position'(49,1,1258,18)).
'comment'('lineComment'('-- we allow any input but then the queues do the checking about'),'src_position'(56,1,1419,63)).
'comment'('lineComment'('-- passing com1 access '),'src_position'(57,1,1483,23)).
'comment'('lineComment'('-- whether the customer is already in the queue '),'src_position'(58,1,1507,48)).
'comment'('lineComment'('-- don\x27\t need to set back to default because could put it as cust'),'src_position'(74,1,2135,65)).
'comment'('lineComment'('--because never going to use that value again'),'src_position'(75,1,2201,45)).
'comment'('lineComment'('-- but what if the size of that queue is empty the we should precoess'),'src_position'(76,1,2247,69)).
'comment'('lineComment'('--the next one along, where is that info going to be kept'),'src_position'(77,1,2317,57)).
'comment'('lineComment'('-- with the com3 is there goign to be a deadlock again with requesting'),'src_position'(78,1,2375,70)).
'comment'('lineComment'('--value, do we need an atomic event to prevent that to signal update'),'src_position'(79,1,2446,68)).
'comment'('lineComment'('--sequence of events'),'src_position'(80,1,2515,20)).
'comment'('lineComment'('-- if > 1 then output the currentperson and get the next one from a'),'src_position'(81,1,2536,67)).
'comment'('lineComment'('--queue'),'src_position'(82,1,2604,7)).
'comment'('lineComment'('-- now queues is tracking the counter and is making the choice of'),'src_position'(89,1,2832,65)).
'comment'('lineComment'('--which queue to get info from'),'src_position'(90,1,2898,30)).
'comment'('lineComment'('-- Because after finding somebody to leave the queues we need to'),'src_position'(106,1,3369,64)).
'comment'('lineComment'('--return with those parameters, we need to carry them across as well.'),'src_position'(107,1,3434,69)).
'comment'('lineComment'('-- We have introduced an additional parameter, lastQueueNo, into'),'src_position'(119,1,4017,64)).
'comment'('lineComment'('-- NextQCtrl.  This is the last point by which a `no\x27\ response can'),'src_position'(120,1,4082,66)).
'comment'('lineComment'('-- come on a queryQueueEmpty query.  This is built into the model,'),'src_position'(121,1,4149,66)).
'comment'('lineComment'('-- which has that if queueNo == lastQueueNo then the only possible'),'src_position'(122,1,4216,66)).
'comment'('lineComment'('-- input for that queue is `no\x27\'),'src_position'(123,1,4283,31)).
'comment'('lineComment'('-- not appropriate to check for these '),'src_position'(132,1,4523,38)).
'comment'('lineComment'('-- Need to set maxLimit to 2 for this check to succeed in'),'src_position'(138,1,4711,57)).
'comment'('lineComment'('-- any sort of reasonable time.'),'src_position'(139,1,4769,31)).
'symbol'('QUEUENUM','QUEUENUM','src_span'(7,1,7,9,204,8),'Ident (Groundrep.)').
'symbol'('CUSTOMER','CUSTOMER','src_span'(8,10,8,18,239,8),'Datatype').
'symbol'('c1','c1','src_span'(8,21,8,23,250,2),'Constructor of Datatype').
'symbol'('c2','c2','src_span'(8,26,8,28,255,2),'Constructor of Datatype').
'symbol'('c3','c3','src_span'(8,31,8,33,260,2),'Constructor of Datatype').
'symbol'('c4','c4','src_span'(8,36,8,38,265,2),'Constructor of Datatype').
'symbol'('c5','c5','src_span'(8,41,8,43,270,2),'Constructor of Datatype').
'symbol'('c6','c6','src_span'(8,46,8,48,275,2),'Constructor of Datatype').
'symbol'('STATUS','STATUS','src_span'(9,10,9,16,287,6),'Datatype').
'symbol'('success','success','src_span'(9,19,9,26,296,7),'Constructor of Datatype').
'symbol'('fail','fail','src_span'(9,29,9,33,306,4),'Constructor of Datatype').
'symbol'('QSTATUS','QSTATUS','src_span'(10,10,10,17,320,7),'Datatype').
'symbol'('yes','yes','src_span'(10,19,10,22,329,3),'Constructor of Datatype').
'symbol'('no','no','src_span'(10,25,10,27,335,2),'Constructor of Datatype').
'symbol'('maxLimit','maxLimit','src_span'(12,1,12,9,339,8),'Ident (Groundrep.)').
'symbol'('defaultCounter','defaultCounter','src_span'(13,1,13,15,352,14),'Ident (Groundrep.)').
'symbol'('defaultCustomer','defaultCustomer','src_span'(14,1,14,16,372,15),'Ident (Groundrep.)').
'symbol'('maxQueueingCustomers','maxQueueingCustomers','src_span'(15,1,15,21,393,20),'Ident (Groundrep.)').
'symbol'('numQueues','numQueues','src_span'(16,1,16,10,428,9),'Ident (Groundrep.)').
'symbol'('enterBank','enterBank','src_span'(20,9,20,18,473,9),'Channel').
'symbol'('leaveBank','leaveBank','src_span'(21,9,21,18,502,9),'Channel').
'symbol'('report','report','src_span'(22,9,22,15,531,6),'Channel').
'symbol'('com1','com1','src_span'(25,9,25,13,622,4),'Channel').
'symbol'('com3','com3','src_span'(26,9,26,13,646,4),'Channel').
'symbol'('canJoinResponse','canJoinResponse','src_span'(27,9,27,24,670,15),'Channel').
'symbol'('retrieveCustomer','retrieveCustomer','src_span'(28,9,28,25,703,16),'Channel').
'symbol'('joinQueue','joinQueue','src_span'(31,9,31,18,749,9),'Channel').
'symbol'('leaveQueue','leaveQueue','src_span'(32,9,32,19,778,10),'Channel').
'symbol'('queryQueueEmpty','queryQueueEmpty','src_span'(33,9,33,24,817,15),'Channel').
'symbol'('inc','inc','src_span'(36,1,36,4,854,3),'Funktion or Process').
'symbol'('queueNo','queueNo','src_span'(36,5,36,12,858,7),'Ident (Prolog Variable)').
'symbol'('dec','dec','src_span'(37,1,37,4,895,3),'Funktion or Process').
'symbol'('queueNo2','queueNo','src_span'(37,5,37,12,899,7),'Ident (Prolog Variable)').
'symbol'('CounterCtrl','CounterCtrl','src_span'(51,1,51,12,1278,11),'Ident (Groundrep.)').
'symbol'('CurrentCtrl','CurrentCtrl','src_span'(53,1,53,12,1324,11),'Funktion or Process').
'symbol'('num','num','src_span'(53,13,53,16,1336,3),'Ident (Prolog Variable)').
'symbol'('currentCust','currentCust','src_span'(53,18,53,29,1341,11),'Ident (Prolog Variable)').
'symbol'('JoinCtrl','JoinCtrl','src_span'(61,1,61,9,1558,8),'Funktion or Process').
'symbol'('num2','num','src_span'(61,10,61,13,1567,3),'Ident (Prolog Variable)').
'symbol'('currentCust2','currentCust','src_span'(61,14,61,25,1571,11),'Ident (Prolog Variable)').
'symbol'('cc','cc','src_span'(62,31,62,33,1617,2),'Ident (Prolog Variable)').
'symbol'('bb','bb','src_span'(66,42,66,44,1795,2),'Ident (Prolog Variable)').
'symbol'('LeaveCtrl','LeaveCtrl','src_span'(84,1,84,10,2613,9),'Funktion or Process').
'symbol'('num3','num','src_span'(84,11,84,14,2623,3),'Ident (Prolog Variable)').
'symbol'('currentCust3','currentCust','src_span'(84,15,84,26,2627,11),'Ident (Prolog Variable)').
'symbol'('cc2','cc','src_span'(87,71,87,73,2802,2),'Ident (Prolog Variable)').
'symbol'('QueuesCtrl','QueuesCtrl','src_span'(92,1,92,11,2930,10),'Ident (Groundrep.)').
'symbol'('QCtrl','QCtrl','src_span'(94,1,94,6,2958,5),'Funktion or Process').
'symbol'('s','s','src_span'(94,7,94,8,2964,1),'Ident (Prolog Variable)').
'symbol'('queueNo3','queueNo','src_span'(94,9,94,16,2966,7),'Ident (Prolog Variable)').
'symbol'('custSet','custSet','src_span'(94,17,94,24,2974,7),'Ident (Prolog Variable)').
'symbol'('cc3','cc','src_span'(95,41,95,43,3026,2),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(96,20,96,26,3052,6),'BuiltIn primitive').
'symbol'('union','union','src_span'(100,23,100,28,3250,5),'BuiltIn primitive').
'symbol'('NextQCtrl','NextQCtrl','src_span'(109,1,109,10,3505,9),'Funktion or Process').
'symbol'('s2','s','src_span'(109,11,109,12,3515,1),'Ident (Prolog Variable)').
'symbol'('queueNo4','queueNo','src_span'(109,13,109,20,3517,7),'Ident (Prolog Variable)').
'symbol'('lastQueueNo','lastQueueNo','src_span'(109,21,109,32,3525,11),'Ident (Prolog Variable)').
'symbol'('custSet2','custSet','src_span'(109,33,109,40,3537,7),'Ident (Prolog Variable)').
'symbol'('bb2','bb','src_span'(110,67,110,69,3615,2),'Ident (Prolog Variable)').
'symbol'('cc4','cc','src_span'(112,33,112,35,3683,2),'Ident (Prolog Variable)').
'symbol'('diff','diff','src_span'(112,83,112,87,3733,4),'BuiltIn primitive').
'symbol'('cc5','cc','src_span'(117,34,117,36,3945,2),'Ident (Prolog Variable)').
'symbol'('diff','diff','src_span'(117,84,117,88,3995,4),'BuiltIn primitive').
'symbol'('A','A','src_span'(125,1,125,2,4316,1),'Ident (Groundrep.)').
'symbol'('B','B','src_span'(126,1,126,2,4367,1),'Ident (Groundrep.)').
'symbol'('BankSystem','BankSystem','src_span'(128,1,128,11,4420,10),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(128,45,128,50,4464,5),'BuiltIn primitive').
'symbol'('FullBankSystem','FullBankSystem','src_span'(130,1,130,15,4476,14),'Ident (Groundrep.)').
'symbol'('SPEC','SPEC','src_span'(134,1,134,5,4563,4),'Ident (Groundrep.)').
'symbol'('i','i','src_span'(134,12,134,13,4574,1),'Ident (Prolog Variable)').
'symbol'('CUST','CUST','src_span'(135,1,135,5,4597,4),'Ident (Groundrep.)').
'symbol'('i2','i','src_span'(135,18,135,19,4614,1),'Ident (Prolog Variable)').
'symbol'('SPEC2','SPEC2','src_span'(140,1,140,6,4801,5),'Ident (Groundrep.)').
'symbol'('NEWSPEC','NEWSPEC','src_span'(141,1,141,8,4820,7),'Funktion or Process').
'symbol'('num4','num','src_span'(141,9,141,12,4828,3),'Ident (Prolog Variable)').
'symbol'('cc6','cc','src_span'(142,32,142,34,4867,2),'Ident (Prolog Variable)').
'symbol'('cc7','cc','src_span'(145,18,145,20,4974,2),'Ident (Prolog Variable)').