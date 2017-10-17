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
'bindval'('DATA','setExp'('rangeEnum'(['int'(2),'int'(3)])),'src_span'(33,1,33,13,1064,12)).
'channel'('left','type'('dotTupleType'(['val_of'('DATA','src_span'(37,22,37,26,1261,4))]))).
'channel'('right','type'('dotTupleType'(['val_of'('DATA','src_span'(37,22,37,26,1261,4))]))).
'channel'('a','type'('dotTupleType'(['boolType','val_of'('DATA','src_span'(38,21,38,25,1286,4))]))).
'channel'('b','type'('dotTupleType'(['boolType','val_of'('DATA','src_span'(38,21,38,25,1286,4))]))).
'channel'('c','type'('dotTupleType'(['boolType']))).
'channel'('d','type'('dotTupleType'(['boolType']))).
'bindval'('SPEC','let'(['agent'('BUFF'(_s,_N),'ifte'('agent_call'('src_span'(51,10,51,14,1550,4),'null',[_s]),'prefix'('src_span'(52,9,52,13,1571,4),['in'(_x)],'left','agent_call'('src_span'(52,19,52,23,1581,4),'BUFF',['listExp'('rangeEnum'([_x])),_N]),'src_span'(52,16,52,18,1577,18)),'[]'('prefix'('src_span'(54,9,54,14,1613,5),['out'('agent_call'('src_span'(54,15,54,19,1619,4),'head',[_s]))],'right','agent_call'('src_span'(54,26,54,30,1630,4),'BUFF',['agent_call'('src_span'(54,31,54,35,1635,4),'tail',[_s]),_N]),'src_span'(54,23,54,25,1626,28)),'&'('<'('#'(_s),_N),'|~|'('stop'('src_span'(56,17,56,21,1674,4)),'prefix'('src_span'(56,26,56,30,1683,4),['in'(_x2)],'left','agent_call'('src_span'(56,36,56,40,1693,4),'BUFF',['^'(_s,'listExp'('rangeEnum'([_x2]))),_N]),'src_span'(56,33,56,35,1689,20)),'src_span_operator'('no_loc_info_available','src_span'(56,22,56,25,1679,3)))),'src_span_operator'('no_loc_info_available','src_span'(55,9,55,11,1655,2))),'src_span'(51,7,51,17,1547,10),'src_span'(51,18,52,8,1557,43),'no_loc_info_available'),'src_span'(51,7,56,51,1547,161))],'agent_call'('src_span'(61,10,61,14,1841,4),'BUFF',['listExp'('rangeEnum'([])),'int'(3)])),'src_span'(45,1,61,21,1384,468)).
'agent'('lossy_buffer'(_in,_out,_bound),'let'(['agent'('B'('int'(0)),'prefix'('src_span'(75,12,75,14,2368,2),['in'(_x3)],_in,'prefix'('src_span'(75,20,75,23,2376,3),['out'(_x3)],_out,'agent_call'('src_span'(75,29,75,30,2385,1),'B',['-'(_bound,'int'(1))]),'src_span'(75,26,75,28,2381,16)),'src_span'(75,17,75,19,2372,25)),'src_span'(75,12,75,39,2368,27)),'agent'('B'(_n),'prefix'('src_span'(76,12,76,14,2407,2),['in'(_x4)],_in,'|~|'('agent_call'('src_span'(76,21,76,22,2416,1),'B',['-'(_n,'int'(1))]),'prefix'('src_span'(76,32,76,35,2427,3),['out'(_x4)],_out,'agent_call'('src_span'(76,41,76,42,2436,1),'B',['-'(_bound,'int'(1))]),'src_span'(76,38,76,40,2432,16)),'src_span_operator'('no_loc_info_available','src_span'(76,28,76,31,2423,3))),'src_span'(76,17,76,19,2411,38)),'src_span'(76,12,76,52,2407,40))],'agent_call'('src_span'(77,10,77,11,2457,1),'B',['-'(_bound,'int'(1))])),'src_span'(72,3,77,20,2229,238)).
'bindval'('PUT','agent_call'('src_span'(79,7,79,19,2475,12),'lossy_buffer',['a','b','int'(3)]),'src_span'(79,1,79,28,2469,27)).
'bindval'('GET','agent_call'('src_span'(81,7,81,19,2504,12),'lossy_buffer',['c','d','int'(3)]),'src_span'(81,1,81,28,2498,27)).
'bindval'('SEND','let'(['bindval'('Null','int'(99),'src_span'(90,5,90,14,2670,9)),'agent'('S'(_v,_bit),'[]'('ifte'('=='(_v,'val_of'('Null','src_span'(100,16,100,20,3088,4))),'prefix'('src_span'(100,26,100,30,3098,4),['in'(_x5)],'left','agent_call'('src_span'(100,36,100,37,3108,1),'S',[_x5,'bool_not'(_bit)]),'src_span'(100,33,100,35,3104,19)),'prefix'('src_span'(100,55,100,56,3127,1),['out'(_bit),'out'(_v)],'a','agent_call'('src_span'(100,66,100,67,3138,1),'S',[_v,_bit]),'src_span'(100,63,100,65,3134,15)),'no_loc_info_available','no_loc_info_available','src_span'(100,50,100,54,3121,49)),'prefix'('src_span'(102,7,102,8,3164,1),['in'(_ack)],'d','agent_call'('src_span'(102,16,102,17,3173,1),'S',['ifte'('=='(_ack,_bit),'val_of'('Null','src_span'(102,35,102,39,3192,4)),_v,'no_loc_info_available','no_loc_info_available','src_span'(102,40,102,44,3196,11)),_bit]),'src_span'(102,13,102,15,3169,44)),'src_span_operator'('no_loc_info_available','src_span'(101,7,101,9,3155,2))),'no_loc_info_available')],'agent_call'('src_span'(107,10,107,11,3335,1),'S',['val_of'('Null','src_span'(107,12,107,16,3337,4)),'true'])),'src_span'(88,1,107,23,2653,695)).
'bindval'('RECV','let'(['agent'('R'(_bit2),'[]'('prefix'('src_span'(119,7,119,8,3713,1),['in'(_tag),'in'(_data)],'b','ifte'('=='(_tag,_bit2),'prefix'('src_span'(119,39,119,44,3745,5),['out'(_data)],'right','agent_call'('src_span'(119,53,119,54,3759,1),'R',['bool_not'(_bit2)]),'src_span'(119,50,119,52,3755,19)),'agent_call'('src_span'(119,69,119,70,3775,1),'R',[_bit2]),'no_loc_info_available','no_loc_info_available','src_span'(119,64,119,68,3769,36)),'src_span'(119,18,119,20,3723,64)),'prefix'('src_span'(121,7,121,8,3798,1),['out'('bool_not'(_bit2))],'c','agent_call'('src_span'(121,20,121,21,3811,1),'R',[_bit2]),'src_span'(121,17,121,19,3807,18)),'src_span_operator'('no_loc_info_available','src_span'(120,7,120,9,3789,2))),'no_loc_info_available')],'agent_call'('src_span'(126,10,126,11,3932,1),'R',['false'])),'src_span'(109,1,126,18,3350,590)).
'agent'('make_system'(_receiver),'agent_call'('src_span'(139,3,139,19,4395,16),'make_full_system',['val_of'('SEND','src_span'(139,20,139,24,4412,4)),'|||'('val_of'('PUT','src_span'(139,26,139,29,4418,3)),'val_of'('GET','src_span'(139,32,139,35,4424,3)),'src_span_operator'('no_loc_info_available','src_span'(139,29,139,32,4421,3))),_receiver]),'src_span'(139,3,139,46,4395,43)).
'agent'('make_full_system'(_sender,_wiring,_receiver2),'\x5c\'('sharing'('closure'(['a','d']),_sender,'sharing'('closure'(['b','c']),_wiring,_receiver2,'src_span'(142,27,142,38,4511,11)),'src_span'(142,9,142,20,4493,11)),'closure'(['a','b','c','d']),'src_span_operator'('no_loc_info_available','src_span'(142,47,142,48,4531,1))),'no_loc_info_available').
'bindval'('DIVSYSTEM','agent_call'('src_span'(144,13,144,24,4557,11),'make_system',['val_of'('RECV','src_span'(144,25,144,29,4569,4))]),'src_span'(144,1,144,30,4545,29)).
'assertModelCheck'('False','val_of'('DIVSYSTEM','src_span'(146,8,146,17,4583,9)),'LivelockFree').
'bindval'('ALT','prefix'('src_span'(159,7,159,8,5045,1),['in'(_)],'b','prefix'('src_span'(159,14,159,15,5052,1),['in'(_)],'c','val_of'('ALT','src_span'(159,21,159,24,5059,3)),'src_span'(159,18,159,20,5055,9)),'src_span'(159,11,159,13,5048,16)),'src_span'(159,1,159,24,5039,23)).
'agent'('LIMIT'(_M),'let'(['agent'('L'(_bs,_cs),'[]'('&'('<'(_bs,_M),'prefix'('src_span'(167,16,167,17,5202,1),['in'(_)],'b','agent_call'('src_span'(167,23,167,24,5209,1),'L',['+'(_bs,'int'(1)),'int'(0)]),'src_span'(167,20,167,22,5205,16))),'&'('<'(_cs,_M),'prefix'('src_span'(169,16,169,17,5244,1),['in'(_)],'c','agent_call'('src_span'(169,23,169,24,5251,1),'L',['int'(0),'+'(_cs,'int'(1))]),'src_span'(169,20,169,22,5247,16))),'src_span_operator'('no_loc_info_available','src_span'(168,7,168,9,5226,2))),'no_loc_info_available')],'agent_call'('src_span'(170,10,170,11,5271,1),'L',['int'(0),'int'(0)])),'src_span'(165,3,170,16,5168,109)).
'agent'('NDC'(_M2),'let'(['agent'('C'(_n2),'ifte'('=='(_n2,'int'(0)),'prefix'('src_span'(179,9,179,10,5442,1),['in'(_)],'c','agent_call'('src_span'(179,16,179,17,5449,1),'C',['+'(_n2,'int'(1))]),'src_span'(179,13,179,15,5445,12)),'ifte'('=='(_n2,_M2),'prefix'('src_span'(181,9,181,10,5488,1),['in'(_)],'b','agent_call'('src_span'(181,16,181,17,5495,1),'C',['-'(_n2,'int'(1))]),'src_span'(181,13,181,15,5491,12)),'|~|'('prefix'('src_span'(183,9,183,10,5521,1),['in'(_)],'c','agent_call'('src_span'(183,16,183,17,5528,1),'C',['+'(_n2,'int'(1))]),'src_span'(183,13,183,15,5524,12)),'prefix'('src_span'(183,27,183,28,5539,1),['in'(_)],'b','agent_call'('src_span'(183,34,183,35,5546,1),'C',['-'(_n2,'int'(1))]),'src_span'(183,31,183,33,5542,12)),'src_span_operator'('no_loc_info_available','src_span'(183,23,183,26,5535,3))),'no_loc_info_available','no_loc_info_available','no_loc_info_available'),'no_loc_info_available','no_loc_info_available','src_span'(179,23,180,11,5455,110)),'src_span'(178,7,183,40,5421,131))],'agent_call'('src_span'(184,10,184,11,5562,1),'C',['/'(_M2,'int'(2))])),'src_span'(176,3,184,16,5400,168)).
'agent'('modify_receiver'(_constraint),'sharing'('closure'(['b','c']),'val_of'('RECV','src_span'(189,3,189,7,5676,4)),_constraint,'src_span'(189,8,189,19,5681,11)),'src_span'(189,8,189,19,5681,11)).
'bindval'('RCVA','agent_call'('src_span'(191,8,191,23,5712,15),'modify_receiver',['val_of'('ALT','src_span'(191,24,191,27,5728,3))]),'src_span'(191,1,191,28,5705,27)).
'bindval'('RCVL','agent_call'('src_span'(192,8,192,23,5740,15),'modify_receiver',['agent_call'('src_span'(192,24,192,29,5756,5),'LIMIT',['int'(3)])]),'src_span'(192,1,192,33,5733,32)).
'bindval'('RCVN','agent_call'('src_span'(193,8,193,23,5773,15),'modify_receiver',['agent_call'('src_span'(193,24,193,27,5789,3),'NDC',['int'(4)])]),'src_span'(193,1,193,31,5766,30)).
'assertRef'('False','val_of'('SPEC','src_span'(197,8,197,12,5863,4)),'FailureDivergence','agent_call'('src_span'(197,18,197,29,5873,11),'make_system',['val_of'('RCVA','src_span'(197,30,197,34,5885,4))]),'src_span'(197,1,197,35,5856,34)).
'assertRef'('False','val_of'('SPEC','src_span'(198,8,198,12,5898,4)),'FailureDivergence','agent_call'('src_span'(198,18,198,29,5908,11),'make_system',['val_of'('RCVL','src_span'(198,30,198,34,5920,4))]),'src_span'(198,1,198,35,5891,34)).
'assertRef'('False','val_of'('SPEC','src_span'(199,8,199,12,5933,4)),'FailureDivergence','agent_call'('src_span'(199,18,199,29,5943,11),'make_system',['val_of'('RCVN','src_span'(199,30,199,34,5955,4))]),'src_span'(199,1,199,35,5926,34)).
'bindval'('RCVimp','let'(['agent'('R2'(_bit3),'prefix'('src_span'(210,7,210,8,6216,1),['in'(_tag2),'in'(_data2)],'b','ifte'('=='(_tag2,_bit3),'prefix'('src_span'(211,23,211,28,6269,5),['out'(_data2)],'right','prefix'('src_span'(211,37,211,38,6283,1),['out'(_tag2)],'c','agent_call'('src_span'(211,46,211,47,6292,1),'R2',['bool_not'(_bit3)]),'src_span'(211,43,211,45,6288,18)),'src_span'(211,34,211,36,6279,28)),'prefix'('src_span'(213,23,213,24,6350,1),['out'(_tag2)],'c','agent_call'('src_span'(213,32,213,33,6359,1),'R2',[_bit3]),'src_span'(213,29,213,31,6355,14)),'no_loc_info_available','no_loc_info_available','src_span'(211,57,213,22,6302,96)),'src_span'(210,18,210,20,6226,144)),'src_span'(210,7,213,38,6216,149))],'agent_call'('src_span'(214,10,214,11,6375,1),'R2',['false'])),'src_span'(207,1,214,18,6182,201)).
'assertRef'('False','val_of'('RCVN','src_span'(221,8,221,12,6547,4)),'FailureDivergence','val_of'('RCVimp','src_span'(221,18,221,24,6557,6)),'src_span'(221,1,221,24,6540,23)).
'assertRef'('False','val_of'('SPEC','src_span'(223,8,223,12,6572,4)),'FailureDivergence','agent_call'('src_span'(223,18,223,29,6582,11),'make_system',['val_of'('RCVimp','src_span'(223,30,223,36,6594,6))]),'src_span'(223,1,223,37,6565,36)).
'assertRef'('False','val_of'('RCVimp','src_span'(230,8,230,14,6719,6)),'FailureDivergence','val_of'('RCVA','src_span'(230,20,230,24,6731,4)),'src_span'(230,1,230,24,6712,23)).
'assertRef'('False','val_of'('RCVA','src_span'(231,8,231,12,6743,4)),'FailureDivergence','val_of'('RCVimp','src_span'(231,18,231,24,6753,6)),'src_span'(231,1,231,24,6736,23)).
'agent'('LIVCH'(_in2,_out2),'let'(['bindval'('P','|~|'('prefix'('src_span'(253,7,253,9,7361,2),['in'(_)],_in2,'val_of'('P','src_span'(253,15,253,16,7369,1)),'src_span'(253,12,253,14,7365,7)),'repInternalChoice'(['comprehensionGenerator'(_x6,'closure'([_out2]))],'prefix'('src_span'(255,23,255,24,7403,1),[],_x6,'val_of'('P','src_span'(255,28,255,29,7408,1)),'src_span'(255,25,255,27,7404,6)),'src_span'(255,11,255,22,7391,11)),'src_span_operator'('no_loc_info_available','src_span'(254,7,254,10,7377,3))),'src_span'(252,5,255,29,7351,58))],'val_of'('P','src_span'(256,10,256,11,7419,1))),'src_span'(251,3,256,11,7343,77)).
'assertRef'('False','agent_call'('src_span'(258,8,258,13,7429,5),'LIVCH',['a','b']),'FailureDivergence','val_of'('PUT','src_span'(258,24,258,27,7445,3)),'src_span'(258,1,258,27,7422,26)).
'assertRef'('False','agent_call'('src_span'(259,8,259,13,7456,5),'LIVCH',['c','d']),'FailureDivergence','val_of'('GET','src_span'(259,24,259,27,7472,3)),'src_span'(259,1,259,27,7449,26)).
'bindval'('ND','|~|'('prefix'('src_span'(303,6,303,7,9469,1),['in'(_)],'b','val_of'('ND','src_span'(303,13,303,15,9476,2)),'src_span'(303,10,303,12,9472,8)),'prefix'('src_span'(303,20,303,21,9483,1),['in'(_)],'c','val_of'('ND','src_span'(303,27,303,29,9490,2)),'src_span'(303,24,303,26,9486,8)),'src_span_operator'('no_loc_info_available','src_span'(303,16,303,19,9479,3))),'src_span'(303,1,303,29,9464,28)).
'bindval'('RCVG','agent_call'('src_span'(310,8,310,23,9601,15),'modify_receiver',['val_of'('ND','src_span'(310,24,310,26,9617,2))]),'src_span'(310,1,310,27,9594,26)).
'bindval'('GSYSTEM','agent_call'('src_span'(320,11,320,22,9870,11),'make_system',['val_of'('RCVG','src_span'(320,23,320,27,9882,4))]),'src_span'(320,1,320,28,9860,27)).
'assertRef'('False','val_of'('SPEC','src_span'(335,8,335,12,10465,4)),'Failure','val_of'('GSYSTEM','src_span'(335,17,335,24,10474,7)),'src_span'(335,1,335,24,10458,23)).
'assertRef'('False','val_of'('SPEC','src_span'(336,8,336,12,10489,4)),'FailureDivergence','val_of'('GSYSTEM','src_span'(336,18,336,25,10499,7)),'src_span'(336,1,336,25,10482,24)).
'bindval'('PUT\x27\','prefix'('src_span'(350,8,350,9,11134,1),['in'(_tag3),'in'(_data3)],'a','|~|'('prefix'('src_span'(350,23,350,24,11149,1),['out'(_tag3),'out'(_data3)],'b','val_of'('PUT\x27\','src_span'(350,37,350,41,11163,4)),'src_span'(350,34,350,36,11159,13)),'val_of'('PUT\x27\','src_span'(350,47,350,51,11173,4)),'src_span_operator'('no_loc_info_available','src_span'(350,42,350,45,11168,3))),'src_span'(350,19,350,21,11144,39)),'src_span'(350,1,350,52,11127,51)).
'bindval'('GET\x27\','prefix'('src_span'(352,8,352,9,11187,1),['in'(_tag4)],'c','|~|'('prefix'('src_span'(352,18,352,19,11197,1),['out'(_tag4)],'d','val_of'('GET\x27\','src_span'(352,27,352,31,11206,4)),'src_span'(352,24,352,26,11202,12)),'val_of'('GET\x27\','src_span'(352,37,352,41,11216,4)),'src_span_operator'('no_loc_info_available','src_span'(352,32,352,35,11211,3))),'src_span'(352,14,352,16,11192,33)),'src_span'(352,1,352,42,11180,41)).
'bindval'('FSYSTEM','agent_call'('src_span'(362,3,362,19,11509,16),'make_full_system',['val_of'('SEND','src_span'(362,20,362,24,11526,4)),'|||'('val_of'('PUT\x27\','src_span'(362,26,362,30,11532,4)),'val_of'('GET\x27\','src_span'(362,33,362,37,11539,4)),'src_span_operator'('no_loc_info_available','src_span'(362,30,362,33,11536,3))),'val_of'('RCVA','src_span'(362,39,362,43,11545,4))]),'src_span'(361,1,362,44,11497,53)).
'assertRef'('False','val_of'('SPEC','src_span'(372,8,372,12,11917,4)),'Failure','val_of'('FSYSTEM','src_span'(372,17,372,24,11926,7)),'src_span'(372,1,372,24,11910,23)).
'comment'('blockComment'('{-\xa\  Alternating bit protocol.\xa\\xa\  Bill Roscoe, August 1992\xa\  Adapted for FDR2.11, Bryan Scattergood, April 1997\xa\\xa\  This is the initial example of a set which make use of a pair of media\xa\  which are permitted to lose data, and provided no infinite sequence is lost\xa\  will work independently of how lossy the channels are (unlike the file\xa\  prots.csp where the protocols were designed to cope with specific badnesses)\xa\  They work by transmitting messages one way and acknowledgements the other.\xa\\xa\  The alternating bit protocol provides the most standard of all protocol\xa\  examples.  The treatment here has a lot in common with that in the\xa\  Formal Systems information leaflet "The Untimed Analysis of Concurrent\xa\  Systems".\xa\-}'),'src_position'(1,1,0,724)).
'comment'('blockComment'('{-\xa\  Channels and data types\xa\  left and right are the external input and output.\xa\  a and b carry a tag and a data value.\xa\  c and d carry an acknowledgement tag.\xa\  (In this protocol tags are bits.)\xa\\xa\             a  PUT  b\xa\  left        /\x9\    \x5c\       right\xa\  ------> SEND       RECV ------>\xa\              \x5c\     /\xa\             d  GET  c\xa\-}'),'src_position'(19,1,726,336)).
'comment'('lineComment'('-- in a data-independent program, where nothing is done to'),'src_position'(33,15,1078,58)).
'comment'('lineComment'('-- or conditional on data, this is sufficient to establish'),'src_position'(34,15,1151,58)).
'comment'('lineComment'('-- correctness'),'src_position'(35,15,1224,14)).
'comment'('blockComment'('{-\xa\  The overall specification we want to meet is that of a buffer.\xa\-}'),'src_position'(41,1,1312,70)).
'comment'('blockComment'('{-\xa\      The most nondeterministic (left-to-right) buffer with size bounded\xa\      by N is given by BUFF(<>, N), where\xa\    -}'),'src_position'(46,5,1399,124)).
'comment'('blockComment'('{-\xa\      For our purposes we will set N=3 since this example does not introduce\xa\      more buffering than this.\xa\    -}'),'src_position'(57,5,1713,118)).
'comment'('blockComment'('{-\xa\  The protocol is designed to work in the presence of lossy channels.\xa\  We specify here channels which must transmit one out of any three values, but\xa\  any definition would work provided it maintains order and does not lose\xa\  an infinite sequence of values.  The only difference would evidence itself\xa\  in the size of the state-space!\xa\-}'),'src_position'(63,1,1854,340)).
'comment'('lineComment'('-- Increasing bound makes this definition less deterministic.'),'src_position'(73,5,2237,61)).
'comment'('lineComment'('-- n is the number of outputs which may be discarded.'),'src_position'(74,5,2303,53)).
'comment'('blockComment'('{-\xa\  The implementation of the protocol consists of a sender process and\xa\  receiver process, linked by PUT and GET above.\xa\-}'),'src_position'(83,1,2527,124)).
'comment'('lineComment'('-- any value not in DATA'),'src_position'(90,15,2680,24)).
'comment'('blockComment'('{-\xa\      The sender process is parameterised by the current value it\xa\      tries to send out, which may be Null in which case it does\xa\      not try to send it, but instead accepts a new one from\xa\      channel left.\xa\      It is always willing to accept any acknowledgement, and if\xa\      the tag corresponds to the current bit, v is made Null.\xa\    -}'),'src_position'(91,5,2709,348)).
'comment'('blockComment'('{-\xa\      Initially the data value is Null and bit is true so the first\xa\      value input gets bit false.\xa\    -}'),'src_position'(103,5,3214,111)).
'comment'('blockComment'('{-\xa\      The basic part of the receiver takes in messages, sends\xa\      acknowledgements, and transmits messages to the environment.\xa\      R(b) is a process that will always accept a message or\xa\      send an acknowledgement, save that it will not do so when it\xa\      has a pending message to transmit to the environment.\xa\    -}'),'src_position'(111,5,3367,326)).
'comment'('blockComment'('{-\xa\      The first message to be output has tag false, and there is no pending\xa\      message.\xa\    -}'),'src_position'(122,5,3822,100)).
'comment'('blockComment'('{-\xa\  If this receiver is placed in the system, there is the danger of\xa\  livelock, or divergence, if an infinite sequence of acknowledgements is\xa\  transmitted by RECV and received by SEND without the next message being\xa\  transmitted, as is possible.  Alternatively, a message can be transmitted\xa\  and received infinitely without being acknowledged.\xa\  Thus, while the following system is\xa\  partially correct, it can diverge:\xa\-}'),'src_position'(128,1,3942,425)).
'comment'('blockComment'('{-\xa\  We can avoid divergence by preventing the receiver acknowledging or\xa\  receiving infinitely without doing the other.  This can be done by\xa\  putting it in parallel with any process which allows these actions\xa\  in such a way as to avoid these infinitely unfair sequences.  In fact,\xa\  the receiver may choose one of the two to do rather than give the\xa\  choice as above.  Examples that will work are:\xa\-}'),'src_position'(148,1,4611,403)).
'comment'('lineComment'('-- Simple alternation'),'src_position'(157,1,5016,21)).
'comment'('lineComment'('-- Give the environment the choice, provided there is no run of more'),'src_position'(161,1,5064,68)).
'comment'('lineComment'('-- than M of either.'),'src_position'(162,1,5133,20)).
'comment'('lineComment'('-- Choose nondeterministically which to allow, provided the totals'),'src_position'(172,1,5279,66)).
'comment'('lineComment'('-- of b\x27\s and c\x27\s do not differ too much.'),'src_position'(173,1,5346,41)).
'comment'('lineComment'('-- Modified receiver processes, with small values for the constants, are'),'src_position'(186,1,5570,72)).
'comment'('lineComment'('-- and the checks of the respective systems against SPEC'),'src_position'(195,1,5798,56)).
'comment'('blockComment'('{-\xa\  Of course, one would not normally construct one\x27\s receiver as a composition\xa\  of an algorithmic process and constraint in this way, but we now know that\xa\  any receiver which refines RCVN will work.  For example\xa\-}'),'src_position'(201,1,5962,218)).
'comment'('blockComment'('{-\xa\  You can check that RCVimp refines RCVN, which proves that the larger\xa\  check below is correct.  (This can, in this instance, be proved directly.)\xa\-}'),'src_position'(216,1,6385,153)).
'comment'('blockComment'('{-\xa\  Indeed, RCVimp actually equals (semantically) RCVA, and you can check\xa\  that refinement either way.\xa\-}'),'src_position'(225,1,6603,107)).
'comment'('blockComment'('{-\xa\  If you want to develop this example much further, perhaps by inserting\xa\  a more interesting process in one or both channels, the state-space may\xa\  grow uncomfortably large for a Full check (including absence of divergence).\xa\\xa\  Any different channel definitions which satisfy all of\xa\\xa\  1. outputs(tr)  subseq of  inputs(tr)  in the obvious sense\xa\\xa\  2. will not do an infinite sequence of inputs without an output\xa\\xa\  3. refines LIVCH (given below) and hence can always either input\xa\     any value, or make an output\xa\\xa\  is substitutable for PUT and/or GET.\xa\-}'),'src_position'(233,1,6761,561)).
'comment'('blockComment'('{-\xa\  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\xa\   Failures-only Checking\xa\  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\xa\\xa\  The following is fairly advanced material for those who want to\xa\  understand the subtle differences between Full and Failures-only, and even\xa\  exploit them.\xa\\xa\  As we know, Failures and Traces checking do not test the implementation\xa\  for potential divergence.  This is usually something to overcome\xa\  by establishing separately that the implementation is divergence-free.\xa\\xa\  Provided we know clearly what we are doing, there are cases where\xa\  establishing facts about processes which can diverge are valuable.\xa\  In other words, there are useful theorems that can be proved using\xa\  these restricted forms of check that are not provable using a full check.\xa\\xa\  We know of two sorts of results to prove in this way.  They are\xa\  similar in execution, but rather different in interpretation, and\xa\  both can be successfully demonstrated using the alternating bit\xa\  example.\xa\\xa\  Spec [=F Imp  proves that every trace, and every stable refusal\xa\  of Imp is satisfactory.  If we take a divergence-free\xa\  component of Imp and refine it,\xa\  then not only will the overall set of failures and divergence be\xa\  refined (reduced), but so will the observable traces and stable refusals.\xa\\xa\  It follows that, if we can establish Spec  [=F Imp, then we have proved\xa\  a theorem that any Imp\x27\ produced by refining the component which is\xa\  also divergence free (i.e., Imp\x27\ is), then Imp\x27\ will failures-divergence\xa\  refine Spec.\xa\\xa\  This is valuable in arguments like that involving the receiver process\xa\  above, where a number of different processes were introduced, as varying\xa\  ways of eliminating divergence.  We proved each of them worked,\xa\  but really we would like a general result about a class of receiver\xa\  processes, which leaves divergence as the only remaining issue.  This\xa\  can be done with the generalised co-process\xa\-}'),'src_position'(261,1,7477,1985)).
'comment'('blockComment'('{-\xa\  which is an approximation to  ALT, LIMIT and NDC above. The generalised\xa\  receiver process\xa\-}'),'src_position'(305,1,9494,98)).
'comment'('blockComment'('{-\xa\  approximates any conceivable process we might want to put at the\xa\  right-hand-side of the alternating bit protocol.  Note that it can\xa\  always choose whether it wants to accept a message or send an acknowledgement\xa\-}'),'src_position'(312,1,9622,221)).
'comment'('lineComment'('-- The system'),'src_position'(318,1,9845,13)).
'comment'('blockComment'('{-\xa\  will be found to diverge if you use a full check.  However the failures\xa\  check will work, which, as we said above, establishes that\xa\  any refinement of RCVG which eliminates divergence from the whole\xa\  system will give a valid failures-divergence refinement of SPEC.\xa\\xa\  Note that we could not have made the same argument using RECV, since\xa\  it is a deterministic process.  In fact, DIVSYSTEM has no stable\xa\  states at all, a very different proposition from GSYSTEM,\xa\  which implicitly has all the stable states of every possible working\xa\  right-hand process.\xa\-}'),'src_position'(322,1,9889,567)).
'comment'('blockComment'('{-\xa\  The second sort of result uses very similar techniques to reason about\xa\  fairness issues.  It perhaps does not make much sense to talk about a\xa\  fair receiver process, when the design of that process is likely to be\xa\  firmly within our control.  But it certainly makes sense to introduce\xa\  fairness assumptions about the communications medium.  It is really more\xa\  natural to bring in assumption like "the medium will never lose an infinite\xa\  sequence of consecutive messages" rather than "it will not lose more than\xa\  three out of every four".  The ideal model of a communications medium\xa\  in this respect is\xa\-}'),'src_position'(338,1,10508,617)).
'comment'('blockComment'('{-\xa\  with the additional assumption that an infinite set of messages is not\xa\  lost.  This actually defines perfectly valid processes in the infinite\xa\  traces/failures model of CSP, and mathematical arguments in that model\xa\  will exclude divergence from a system such as\xa\-}'),'src_position'(354,1,11223,272)).
'comment'('blockComment'('{-\xa\  with the additional assumptions about EG and FG that we cannot make in\xa\  the finite failures/divergence model.  Since the additional assumptions\xa\  only serve to refine EG and FG further, proving that FSYSTEM refines\xa\  SPEC using a failures check will prove that the system with the\xa\  fairness assumptions in place will be a full refinement of SPEC.\xa\-}'),'src_position'(364,1,11552,356)).
'symbol'('DATA','DATA','src_span'(33,1,33,5,1064,4),'Ident (Groundrep.)').
'symbol'('left','left','src_span'(37,9,37,13,1248,4),'Channel').
'symbol'('right','right','src_span'(37,14,37,19,1253,5),'Channel').
'symbol'('a','a','src_span'(38,9,38,10,1274,1),'Channel').
'symbol'('b','b','src_span'(38,12,38,13,1277,1),'Channel').
'symbol'('c','c','src_span'(39,9,39,10,1299,1),'Channel').
'symbol'('d','d','src_span'(39,12,39,13,1302,1),'Channel').
'symbol'('SPEC','SPEC','src_span'(45,1,45,5,1384,4),'Ident (Groundrep.)').
'symbol'('BUFF','BUFF','src_span'(50,5,50,9,1528,4),'Funktion or Process').
'symbol'('s','s','src_span'(50,10,50,11,1533,1),'Ident (Prolog Variable)').
'symbol'('N','N','src_span'(50,13,50,14,1536,1),'Ident (Prolog Variable)').
'symbol'('null','null','src_span'(51,10,51,14,1550,4),'BuiltIn primitive').
'symbol'('x','x','src_span'(52,14,52,15,1576,1),'Ident (Prolog Variable)').
'symbol'('head','head','src_span'(54,15,54,19,1619,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(54,31,54,35,1635,4),'BuiltIn primitive').
'symbol'('x2','x','src_span'(56,31,56,32,1688,1),'Ident (Prolog Variable)').
'symbol'('lossy_buffer','lossy_buffer','src_span'(71,1,71,13,2196,12),'Funktion or Process').
'symbol'('in','in','src_span'(71,14,71,16,2209,2),'Ident (Prolog Variable)').
'symbol'('out','out','src_span'(71,18,71,21,2213,3),'Ident (Prolog Variable)').
'symbol'('bound','bound','src_span'(71,23,71,28,2218,5),'Ident (Prolog Variable)').
'symbol'('B','B','src_span'(75,5,75,6,2361,1),'Funktion or Process').
'symbol'('x3','x','src_span'(75,15,75,16,2371,1),'Ident (Prolog Variable)').
'symbol'('n','n','src_span'(76,7,76,8,2402,1),'Ident (Prolog Variable)').
'symbol'('x4','x','src_span'(76,15,76,16,2410,1),'Ident (Prolog Variable)').
'symbol'('PUT','PUT','src_span'(79,1,79,4,2469,3),'Ident (Groundrep.)').
'symbol'('GET','GET','src_span'(81,1,81,4,2498,3),'Ident (Groundrep.)').
'symbol'('SEND','SEND','src_span'(88,1,88,5,2653,4),'Ident (Groundrep.)').
'symbol'('Null','Null','src_span'(90,5,90,9,2670,4),'Ident (Groundrep.)').
'symbol'('S','S','src_span'(99,5,99,6,3062,1),'Funktion or Process').
'symbol'('v','v','src_span'(99,7,99,8,3064,1),'Ident (Prolog Variable)').
'symbol'('bit','bit','src_span'(99,9,99,12,3066,3),'Ident (Prolog Variable)').
'symbol'('x5','x','src_span'(100,31,100,32,3103,1),'Ident (Prolog Variable)').
'symbol'('ack','ack','src_span'(102,9,102,12,3166,3),'Ident (Prolog Variable)').
'symbol'('RECV','RECV','src_span'(109,1,109,5,3350,4),'Ident (Groundrep.)').
'symbol'('R','R','src_span'(118,5,118,6,3698,1),'Funktion or Process').
'symbol'('bit2','bit','src_span'(118,7,118,10,3700,3),'Ident (Prolog Variable)').
'symbol'('tag','tag','src_span'(119,9,119,12,3715,3),'Ident (Prolog Variable)').
'symbol'('data','data','src_span'(119,13,119,17,3719,4),'Ident (Prolog Variable)').
'symbol'('make_system','make_system','src_span'(138,1,138,12,4369,11),'Funktion or Process').
'symbol'('receiver','receiver','src_span'(138,13,138,21,4381,8),'Ident (Prolog Variable)').
'symbol'('make_full_system','make_full_system','src_span'(141,1,141,17,4440,16),'Funktion or Process').
'symbol'('sender','sender','src_span'(141,18,141,24,4457,6),'Ident (Prolog Variable)').
'symbol'('wiring','wiring','src_span'(141,26,141,32,4465,6),'Ident (Prolog Variable)').
'symbol'('receiver2','receiver','src_span'(141,34,141,42,4473,8),'Ident (Prolog Variable)').
'symbol'('DIVSYSTEM','DIVSYSTEM','src_span'(144,1,144,10,4545,9),'Ident (Groundrep.)').
'symbol'('ALT','ALT','src_span'(159,1,159,4,5039,3),'Ident (Groundrep.)').
'symbol'('LIMIT','LIMIT','src_span'(164,1,164,6,5155,5),'Funktion or Process').
'symbol'('M','M','src_span'(164,7,164,8,5161,1),'Ident (Prolog Variable)').
'symbol'('L','L','src_span'(166,5,166,6,5176,1),'Funktion or Process').
'symbol'('bs','bs','src_span'(166,7,166,9,5178,2),'Ident (Prolog Variable)').
'symbol'('cs','cs','src_span'(166,10,166,12,5181,2),'Ident (Prolog Variable)').
'symbol'('NDC','NDC','src_span'(175,1,175,4,5389,3),'Funktion or Process').
'symbol'('M2','M','src_span'(175,5,175,6,5393,1),'Ident (Prolog Variable)').
'symbol'('C','C','src_span'(177,5,177,6,5408,1),'Funktion or Process').
'symbol'('n2','n','src_span'(177,7,177,8,5410,1),'Ident (Prolog Variable)').
'symbol'('modify_receiver','modify_receiver','src_span'(188,1,188,16,5644,15),'Funktion or Process').
'symbol'('constraint','constraint','src_span'(188,17,188,27,5660,10),'Ident (Prolog Variable)').
'symbol'('RCVA','RCVA','src_span'(191,1,191,5,5705,4),'Ident (Groundrep.)').
'symbol'('RCVL','RCVL','src_span'(192,1,192,5,5733,4),'Ident (Groundrep.)').
'symbol'('RCVN','RCVN','src_span'(193,1,193,5,5766,4),'Ident (Groundrep.)').
'symbol'('RCVimp','RCVimp','src_span'(207,1,207,7,6182,6),'Ident (Groundrep.)').
'symbol'('R2','R','src_span'(209,5,209,6,6201,1),'Funktion or Process').
'symbol'('bit3','bit','src_span'(209,7,209,10,6203,3),'Ident (Prolog Variable)').
'symbol'('tag2','tag','src_span'(210,9,210,12,6218,3),'Ident (Prolog Variable)').
'symbol'('data2','data','src_span'(210,13,210,17,6222,4),'Ident (Prolog Variable)').
'symbol'('LIVCH','LIVCH','src_span'(250,1,250,6,7324,5),'Funktion or Process').
'symbol'('in2','in','src_span'(250,7,250,9,7330,2),'Ident (Prolog Variable)').
'symbol'('out2','out','src_span'(250,11,250,14,7334,3),'Ident (Prolog Variable)').
'symbol'('P','P','src_span'(252,5,252,6,7351,1),'Ident (Groundrep.)').
'symbol'('x6','x','src_span'(255,11,255,12,7391,1),'Ident (Prolog Variable)').
'symbol'('ND','ND','src_span'(303,1,303,3,9464,2),'Ident (Groundrep.)').
'symbol'('RCVG','RCVG','src_span'(310,1,310,5,9594,4),'Ident (Groundrep.)').
'symbol'('GSYSTEM','GSYSTEM','src_span'(320,1,320,8,9860,7),'Ident (Groundrep.)').
'symbol'('PUT\x27\','PUT\x27\','src_span'(350,1,350,5,11127,4),'Ident (Groundrep.)').
'symbol'('tag3','tag','src_span'(350,10,350,13,11136,3),'Ident (Prolog Variable)').
'symbol'('data3','data','src_span'(350,14,350,18,11140,4),'Ident (Prolog Variable)').
'symbol'('GET\x27\','GET\x27\','src_span'(352,1,352,5,11180,4),'Ident (Groundrep.)').
'symbol'('tag4','tag','src_span'(352,10,352,13,11189,3),'Ident (Prolog Variable)').
'symbol'('FSYSTEM','FSYSTEM','src_span'(361,1,361,8,11497,7),'Ident (Groundrep.)').