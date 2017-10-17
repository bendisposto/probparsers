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
'dataTypeDef'('Encryption',['constructor'('Alice'),'constructor'('Bob'),'constructor'('Mallory'),'constructor'('Na'),'constructor'('Nb'),'constructor'('Ni'),'constructor'('Kab'),'constructor'('Garbage'),'constructorC'('shared_','dotTupleType'(['typeTuple'(['val_of'('Agent','src_span'(3,12,3,17,91,5)),'val_of'('Agent','src_span'(3,19,3,24,98,5))])])),'constructorC'('Sq','dotTupleType'(['agent_call'('src_span'(3,31,3,34,110,3),'Seq',['Encryption'])])),'constructorC'('Encrypt','dotTupleType'(['typeTuple'(['val_of'('ALL_KEYS','src_span'(4,12,4,20,140,8)),'agent_call'('src_span'(4,21,4,24,149,3),'Seq',['Encryption'])])])),'constructorC'('Xor','dotTupleType'(['typeTuple'(['Encryption','Encryption'])]))]).
'bindval'('ALL_KEYS','agent_call'('src_span'(6,12,6,17,209,5),'Union',['setExp'('rangeEnum'(['val_of'('SessionKey','src_span'(6,19,6,29,216,10)),'val_of'('SharedKey','src_span'(6,31,6,40,228,9))]))]),'src_span'(6,1,6,42,198,41)).
'bindval'('ATOM','setExp'('rangeEnum'(['Alice','Bob','Mallory','Na','Nb','Ni','Kab','Garbage'])),'src_span'(8,1,8,55,241,54)).
'agent'('encrypt'(_m_,_k_),'dotTuple'(['Encrypt','tupleExp'([_k_,_m_])]),'src_span'(10,18,10,33,314,15)).
'agent'('decrypt'('dotpat'(['Encrypt','tuplePat'([_k1_,_m_2])]),_k_2),'ifte'('=='(_k_2,'agent_call'('src_span'(11,41,11,48,370,7),'inverse',[_k1_])),_m_2,'Garbage','no_loc_info_available','no_loc_info_available','src_span'(11,62,11,66,390,15)),'src_span'(11,32,11,74,361,42)).
'agent'('decrypt'(_,_),'Garbage','src_span'(12,16,12,23,419,7)).
'agent'('decryptable'('dotpat'(['Encrypt','tuplePat'([_k1_2,_m_3])]),_k_3),'=='(_k_3,'agent_call'('src_span'(13,42,13,49,468,7),'inverse',[_k1_2])),'no_loc_info_available').
'agent'('decryptable'(_,_),'false','src_span'(14,20,14,25,501,5)).
'agent'('nth'(_ms_,_n_),'ifte'('=='(_n_,'int'(1)),'agent_call'('src_span'(15,31,15,35,537,4),'rmts',['agent_call'('src_span'(15,36,15,40,542,4),'head',[_ms_])]),'agent_call'('src_span'(15,52,15,55,558,3),'nth',['agent_call'('src_span'(15,56,15,60,562,4),'tail',[_ms_]),'-'(_n_,'int'(1))]),'no_loc_info_available','no_loc_info_available','src_span'(15,47,15,51,552,43)),'src_span'(15,15,15,74,521,59)).
'agent'('rmts'('dotpat'([_Timestamp,_t_])),_t_,'src_span'(17,22,17,24,603,2)).
'agent'('rmts'(_x_),_x_,'src_span'(18,12,18,14,617,2)).
'dataTypeDef'('Labels',['constructor'('Msg1'),'constructor'('Msg2'),'constructor'('Msg3'),'constructor'('Msg4'),'constructor'('Env0')]).
'agent'('addGarbage_'(_S),'agent_call'('src_span'(23,18,23,23,692,5),'union',[_S,'val_of'('ATOM','src_span'(23,27,23,31,701,4))]),'src_span'(23,18,23,32,692,14)).
'bindval'('MSG1_BODY','setExp'('rangeEnum'(['tupleExp'(['Msg1','dotTuple'(['Sq','listExp'('rangeEnum'([_A,_na]))])])]),['comprehensionGenerator'(_A,'val_of'('Agent','src_span'(27,11,27,16,755,5))),'comprehensionGenerator'(_na,'val_of'('Nonce','src_span'(27,24,27,29,768,5)))]),'src_span'(25,1,27,30,708,66)).
'bindval'('MSG2_BODY','setExp'('rangeEnum'(['tupleExp'(['Msg2','dotTuple'(['Encrypt','tupleExp'(['agent_call'('src_span'(29,20,29,26,807,6),'shared',[_A2,_B]),'listExp'('rangeEnum'([_na2,_kab]))])])])]),['comprehensionGenerator'(_A2,'val_of'('Agent','src_span'(30,11,30,16,845,5))),'comprehensionGenerator'(_B,'val_of'('Agent','src_span'(30,23,30,28,857,5))),'comprehensionGenerator'(_kab,'val_of'('SessionKey','src_span'(30,37,30,47,871,10))),'comprehensionGenerator'(_na2,'val_of'('Nonce','src_span'(30,55,30,60,889,5)))]),'src_span'(28,1,30,61,775,120)).
'bindval'('MSG3_BODY','setExp'('rangeEnum'(['tupleExp'(['Msg3','dotTuple'(['Encrypt','tupleExp'([_kab2,'listExp'('rangeEnum'([_na3]))])])])]),['comprehensionGenerator'(_kab2,'val_of'('SessionKey','src_span'(33,13,33,23,954,10))),'comprehensionGenerator'(_na3,'val_of'('Nonce','src_span'(33,31,33,36,972,5)))]),'src_span'(31,1,33,37,896,82)).
'bindval'('MSG4_BODY','setExp'('rangeEnum'(['tupleExp'(['Msg4',_nb])]),['comprehensionGenerator'(_nb,'val_of'('Nonce','src_span'(36,12,36,17,1019,5)))]),'src_span'(34,1,36,18,979,46)).
'bindval'('MSG_BODY','agent_call'('src_span'(37,12,37,17,1037,5),'Union',['setExp'('rangeEnum'(['val_of'('MSG1_BODY','src_span'(37,19,37,28,1044,9)),'val_of'('MSG2_BODY','src_span'(37,30,37,39,1055,9)),'val_of'('MSG3_BODY','src_span'(37,41,37,50,1066,9)),'val_of'('MSG4_BODY','src_span'(37,52,37,61,1077,9))]))]),'src_span'(37,1,37,63,1026,62)).
'bindval'('ENVMSG0','setExp'('rangeEnum'(['dotTuple'([_A3,'tupleExp'(['Env0',_B2])])]),['comprehensionGenerator'(_A3,'val_of'('Agent','src_span'(40,23,40,28,1123,5))),'comprehensionGenerator'(_B2,'val_of'('Agent','src_span'(40,35,40,40,1135,5)))]),'src_span'(39,1,40,41,1090,51)).
'bindval'('ENVMSG','val_of'('ENVMSG0','src_span'(41,10,41,17,1151,7)),'src_span'(41,1,41,17,1142,16)).
'bindval'('ENVMSG0_BODY','setExp'('rangeEnum'(['tupleExp'(['Env0',_B3])]),['comprehensionGenerator'(_B3,'val_of'('Agent','src_span'(44,21,44,26,1196,5)))]),'src_span'(43,1,44,27,1160,42)).
'bindval'('ENVMSG_BODY','val_of'('ENVMSG0_BODY','src_span'(45,15,45,27,1217,12)),'src_span'(45,1,45,27,1203,26)).
'agent'('SenderType'('tuplePat'(['Msg1','dotpat'(['Sq','listPat'([_A4,_])])])),'setExp'('rangeEnum'([_A4])),'src_span'(47,34,47,37,1264,3)).
'agent'('SenderType'('tuplePat'(['Msg2','dotpat'(['Encrypt','tuplePat'([_,'listPat'([_,_])])])])),'val_of'('Agent','src_span'(48,44,48,49,1311,5)),'src_span'(48,44,48,49,1311,5)).
'agent'('SenderType'('tuplePat'(['Msg3','dotpat'(['Encrypt','tuplePat'([_,'listPat'([_])])])])),'val_of'('Agent','src_span'(49,41,49,46,1357,5)),'src_span'(49,41,49,46,1357,5)).
'agent'('SenderType'('tuplePat'(['Msg4',_])),'val_of'('Agent','src_span'(50,26,50,31,1388,5)),'src_span'(50,26,50,31,1388,5)).
'agent'('ReceiverType'('tuplePat'(['Msg1','dotpat'(['Sq','listPat'([_,_])])])),'val_of'('Agent','src_span'(52,35,52,40,1429,5)),'src_span'(52,35,52,40,1429,5)).
'agent'('ReceiverType'('tuplePat'(['Msg2','dotpat'(['Encrypt','tuplePat'([_,'listPat'([_,_])])])])),'val_of'('Agent','src_span'(53,45,53,50,1479,5)),'src_span'(53,45,53,50,1479,5)).
'agent'('ReceiverType'('tuplePat'(['Msg3','dotpat'(['Encrypt','tuplePat'([_,'listPat'([_])])])])),'val_of'('Agent','src_span'(54,42,54,47,1526,5)),'src_span'(54,42,54,47,1526,5)).
'agent'('ReceiverType'('tuplePat'(['Msg4',_])),'val_of'('Agent','src_span'(55,27,55,32,1558,5)),'src_span'(55,27,55,32,1558,5)).
'bindval'('ALL_PRINCIPALS','val_of'('Agent','src_span'(57,18,57,23,1582,5)),'src_span'(57,1,57,23,1565,22)).
'channel'('comm','type'('dotTupleType'(['val_of'('ALL_PRINCIPALS','src_span'(59,33,59,47,1621,14)),'val_of'('ALL_PRINCIPALS','src_span'(59,48,59,62,1636,14)),'val_of'('MSG_BODY','src_span'(59,63,59,71,1651,8))]))).
'channel'('fake','type'('dotTupleType'(['val_of'('ALL_PRINCIPALS','src_span'(59,33,59,47,1621,14)),'val_of'('ALL_PRINCIPALS','src_span'(59,48,59,62,1636,14)),'val_of'('MSG_BODY','src_span'(59,63,59,71,1651,8))]))).
'channel'('intercept','type'('dotTupleType'(['val_of'('ALL_PRINCIPALS','src_span'(59,33,59,47,1621,14)),'val_of'('ALL_PRINCIPALS','src_span'(59,48,59,62,1636,14)),'val_of'('MSG_BODY','src_span'(59,63,59,71,1651,8))]))).
'channel'('env','type'('dotTupleType'(['val_of'('ALL_PRINCIPALS','src_span'(60,15,60,29,1674,14)),'val_of'('ENVMSG_BODY','src_span'(60,30,60,41,1689,11))]))).
'dataTypeDef'('ROLE',['constructor'('INITIATOR_role'),'constructor'('RESPONDER_role')]).
'bindval'('ALL_SECRETS','val_of'('SessionKey','src_span'(64,15,64,25,1765,10)),'src_span'(64,1,64,25,1751,24)).
'dataTypeDef'('Signal',['constructorC'('Claim_Secret','dotTupleType'(['val_of'('ALL_PRINCIPALS','src_span'(67,16,67,30,1811,14)),'val_of'('ALL_SECRETS','src_span'(67,31,67,42,1826,11)),'agent_call'('src_span'(67,43,67,46,1838,3),'Set',['val_of'('ALL_PRINCIPALS','src_span'(67,47,67,61,1842,14))])])),'constructorC'('Running1','dotTupleType'(['ROLE','val_of'('ALL_PRINCIPALS','src_span'(68,17,68,31,1876,14)),'val_of'('ALL_PRINCIPALS','src_span'(68,32,68,46,1891,14))])),'constructorC'('Commit1','dotTupleType'(['ROLE','val_of'('ALL_PRINCIPALS','src_span'(69,16,69,30,1923,14)),'val_of'('ALL_PRINCIPALS','src_span'(69,31,69,45,1938,14))]))]).
'channel'('signal','type'('dotTupleType'(['Signal']))).
'agent'('INITIATOR_0'(_A5,_na4),'repChoice'(['comprehensionGenerator'(_B4,'val_of'('Agent','src_span'(76,10,76,15,2035,5)))],'prefix'('src_span'(76,18,76,33,2043,15),[],'dotTuple'(['env',_A5,'tupleExp'(['Env0',_B4])]),'ifte'('!='(_A5,_B4),'prefix'('src_span'(78,3,78,30,2081,27),[],'dotTuple'(['comm',_A5,_B4,'tupleExp'(['Msg1','dotTuple'(['Sq','listExp'('rangeEnum'([_A5,_na4]))])])]),'repChoice'(['comprehensionGenerator'(_kab3,'val_of'('SessionKey','src_span'(79,12,79,22,2123,10)))],'prefix'('src_span'(80,7,80,57,2143,50),[],'dotTuple'(['comm',_B4,_A5,'tupleExp'(['Msg2','dotTuple'(['Encrypt','tupleExp'(['agent_call'('src_span'(80,32,80,38,2168,6),'shared',[_A5,_B4]),'listExp'('rangeEnum'([_na4,_kab3]))])])])]),'prefix'('src_span'(81,3,81,32,2199,29),[],'dotTuple'(['signal','Claim_Secret',_A5,_kab3,'setExp'('rangeEnum'([_B4]))]),'prefix'('src_span'(82,3,82,39,2234,36),[],'dotTuple'(['comm',_A5,_B4,'tupleExp'(['Msg3','dotTuple'(['Encrypt','tupleExp'([_kab3,'listExp'('rangeEnum'([_na4]))])])])]),'repChoice'(['comprehensionGenerator'(_nb2,'val_of'('Nonce','src_span'(83,11,83,16,2284,5)))],'prefix'('src_span'(83,19,83,38,2292,19),[],'dotTuple'(['comm',_B4,_A5,'tupleExp'(['Msg4',_nb2])]),'prefix'('src_span'(84,3,84,36,2317,33),[],'dotTuple'(['signal','Commit1','INITIATOR_role',_A5,_B4]),'skip'('src_span'(85,3,85,7,2356,4)),'src_span'(84,37,85,2,2350,43)),'src_span'(83,39,84,2,2311,68)),'src_span'(83,6,83,18,2279,12)),'src_span'(82,40,83,2,2270,126)),'src_span'(81,33,82,2,2228,161)),'src_span'(80,58,81,2,2193,217)),'src_span'(79,6,79,24,2117,18)),'src_span'(78,31,79,2,2108,279)),'skip'('src_span'(86,8,86,12,2368,4)),'no_loc_info_available','no_loc_info_available','src_span'(85,8,86,7,2360,291)),'src_span'(76,34,77,2,2058,329)),'src_span'(76,6,76,17,2031,11)),'src_span'(76,3,86,12,2028,344)).
'agent'('INITIATOR'(_A6,_na5),'procRenamingComp'('procRenamingComp'('procRenamingComp'('procRenamingComp'('agent_call'('src_span'(89,3,89,14,2395,11),'INITIATOR_0',[_A6,_na5]),['comprehensionGenerator'(_B8,'val_of'('Agent','src_span'(91,14,91,19,2495,5))),'comprehensionGenerator'(_m_7,'val_of'('MSG1_BODY','src_span'(91,27,91,36,2508,9)))],['rename'('dotTuple'(['comm',_A6,_B8,_m_7]),'dotTuple'(['comm',_A6,_B8,_m_7])),'rename'('dotTuple'(['comm',_A6,_B8,_m_7]),'dotTuple'(['intercept',_A6,_B8,_m_7]))]),['comprehensionGenerator'(_B7,'val_of'('Agent','src_span'(93,14,93,19,2601,5))),'comprehensionGenerator'(_m_6,'val_of'('MSG3_BODY','src_span'(93,27,93,36,2614,9)))],['rename'('dotTuple'(['comm',_A6,_B7,_m_6]),'dotTuple'(['comm',_A6,_B7,_m_6])),'rename'('dotTuple'(['comm',_A6,_B7,_m_6]),'dotTuple'(['intercept',_A6,_B7,_m_6]))]),['comprehensionGenerator'(_B6,'val_of'('Agent','src_span'(95,14,95,19,2702,5))),'comprehensionGenerator'(_m_5,'val_of'('MSG2_BODY','src_span'(95,27,95,36,2715,9)))],['rename'('dotTuple'(['comm',_B6,_A6,_m_5]),'dotTuple'(['comm',_B6,_A6,_m_5])),'rename'('dotTuple'(['comm',_B6,_A6,_m_5]),'dotTuple'(['fake',_B6,_A6,_m_5]))]),['comprehensionGenerator'(_B5,'val_of'('Agent','src_span'(97,14,97,19,2803,5))),'comprehensionGenerator'(_m_4,'val_of'('MSG4_BODY','src_span'(97,27,97,36,2816,9)))],['rename'('dotTuple'(['comm',_B5,_A6,_m_4]),'dotTuple'(['comm',_B5,_A6,_m_4])),'rename'('dotTuple'(['comm',_B5,_A6,_m_4]),'dotTuple'(['fake',_B5,_A6,_m_4]))]),'src_span'(96,5,97,38,2731,96)).
'agent'('RESPONDER_0'(_B9,_kab4,_nb3),'repChoice'(['comprehensionGenerator'(_A7,'val_of'('Agent','src_span'(100,10,100,15,2864,5)))],'repChoice'(['comprehensionGenerator'(_na6,'val_of'('Nonce','src_span'(100,26,100,31,2880,5)))],'prefix'('src_span'(100,34,100,61,2888,27),[],'dotTuple'(['comm',_A7,_B9,'tupleExp'(['Msg1','dotTuple'(['Sq','listExp'('rangeEnum'([_A7,_na6]))])])]),'ifte'('!='(_A7,_B9),'prefix'('src_span'(102,3,102,32,2938,29),[],'dotTuple'(['signal','Claim_Secret',_B9,_kab4,'setExp'('rangeEnum'([_A7]))]),'prefix'('src_span'(103,3,103,53,2973,50),[],'dotTuple'(['comm',_B9,_A7,'tupleExp'(['Msg2','dotTuple'(['Encrypt','tupleExp'(['agent_call'('src_span'(103,28,103,34,2998,6),'shared',[_A7,_B9]),'listExp'('rangeEnum'([_na6,_kab4]))])])])]),'prefix'('src_span'(104,3,104,48,3029,45),[],'dotTuple'(['comm',_A7,_B9,'tupleExp'(['Msg3','dotTuple'(['Encrypt','tupleExp'(['agent_call'('src_span'(104,28,104,35,3054,7),'inverse',[_kab4]),'listExp'('rangeEnum'([_na6]))])])])]),'prefix'('src_span'(105,3,105,37,3080,34),[],'dotTuple'(['signal','Running1','RESPONDER_role',_B9,_A7]),'prefix'('src_span'(106,3,106,22,3120,19),[],'dotTuple'(['comm',_B9,_A7,'tupleExp'(['Msg4',_nb3])]),'skip'('src_span'(107,3,107,7,3145,4)),'src_span'(106,23,107,2,3139,29)),'src_span'(105,38,106,2,3114,69)),'src_span'(104,49,105,2,3074,120)),'src_span'(103,54,104,2,3023,176)),'src_span'(102,33,103,2,2967,211)),'skip'('src_span'(108,8,108,12,3157,4)),'no_loc_info_available','no_loc_info_available','src_span'(107,8,108,7,3149,223)),'src_span'(100,62,101,2,2915,273)),'src_span'(100,21,100,33,2875,12)),'src_span'(100,6,100,17,2860,11)),'src_span'(100,3,108,12,2857,304)).
'agent'('RESPONDER'(_B178,_kab5,_nb4),'procRenamingComp'('procRenamingComp'('procRenamingComp'('procRenamingComp'('agent_call'('src_span'(111,3,111,14,3189,11),'RESPONDER_0',[_B178,_kab5,_nb4]),['comprehensionGenerator'(_A187,'val_of'('Agent','src_span'(113,14,113,19,3294,5))),'comprehensionGenerator'(_m_188,'val_of'('MSG2_BODY','src_span'(113,27,113,36,3307,9)))],['rename'('dotTuple'(['comm',_B178,_A187,_m_188]),'dotTuple'(['comm',_B178,_A187,_m_188])),'rename'('dotTuple'(['comm',_B178,_A187,_m_188]),'dotTuple'(['intercept',_B178,_A187,_m_188]))]),['comprehensionGenerator'(_A185,'val_of'('Agent','src_span'(115,14,115,19,3400,5))),'comprehensionGenerator'(_m_186,'val_of'('MSG4_BODY','src_span'(115,27,115,36,3413,9)))],['rename'('dotTuple'(['comm',_B178,_A185,_m_186]),'dotTuple'(['comm',_B178,_A185,_m_186])),'rename'('dotTuple'(['comm',_B178,_A185,_m_186]),'dotTuple'(['intercept',_B178,_A185,_m_186]))]),['comprehensionGenerator'(_A9,'val_of'('Agent','src_span'(117,14,117,19,3501,5))),'comprehensionGenerator'(_m_9,'val_of'('MSG1_BODY','src_span'(117,27,117,36,3514,9)))],['rename'('dotTuple'(['comm',_A9,_B178,_m_9]),'dotTuple'(['comm',_A9,_B178,_m_9])),'rename'('dotTuple'(['comm',_A9,_B178,_m_9]),'dotTuple'(['fake',_A9,_B178,_m_9]))]),['comprehensionGenerator'(_A8,'val_of'('Agent','src_span'(119,14,119,19,3602,5))),'comprehensionGenerator'(_m_8,'val_of'('MSG3_BODY','src_span'(119,27,119,36,3615,9)))],['rename'('dotTuple'(['comm',_A8,_B178,_m_8]),'dotTuple'(['comm',_A8,_B178,_m_8])),'rename'('dotTuple'(['comm',_A8,_B178,_m_8]),'dotTuple'(['fake',_A8,_B178,_m_8]))]),'src_span'(118,5,119,38,3530,96)).
'bindval'('Agent','setExp'('rangeEnum'(['Alice','Bob','Mallory'])),'src_span'(123,1,123,30,3655,29)).
'bindval'('Nonce','setExp'('rangeEnum'(['Na','Nb','Ni'])),'src_span'(124,1,124,21,3685,20)).
'bindval'('SessionKey','setExp'('rangeEnum'(['Kab'])),'src_span'(125,1,125,19,3706,18)).
'bindval'('SharedKey','setExp'('rangeEnum'(['agent_call'('src_span'(126,14,126,20,3738,6),'shared',[_arg_1_,_arg_2_])]),['comprehensionGenerator'(_arg_1_,'val_of'('Agent','src_span'(126,49,126,54,3773,5))),'comprehensionGenerator'(_arg_2_,'val_of'('Agent','src_span'(126,66,126,71,3790,5)))]),'src_span'(126,1,126,72,3725,71)).
'agent'('inverse'('Kab'),'Kab','src_span'(128,16,128,19,3813,3)).
'agent'('inverse'('dotpat'(['shared_',_arg_])),'dotTuple'(['shared_',_arg_]),'src_span'(129,25,129,37,3841,12)).
'agent'('shared'(_arg_1_2,_arg_2_2),'dotTuple'(['shared_','tupleExp'([_arg_1_2,_arg_2_2])]),'src_span'(131,26,131,50,3880,24)).
'bindval'('Fact_0','agent_call'('src_span'(139,3,139,8,4090,5),'Union',['setExp'('rangeEnum'(['setExp'('rangeEnum'(['Garbage'])),'val_of'('Agent','src_span'(141,5,141,10,4117,5)),'val_of'('SharedKey','src_span'(142,5,142,14,4128,9)),'val_of'('SessionKey','src_span'(143,5,143,15,4143,10)),'val_of'('Nonce','src_span'(144,5,144,10,4159,5)),'setExp'('rangeEnum'(['dotTuple'(['Encrypt','tupleExp'(['agent_call'('src_span'(145,15,145,21,4180,6),'shared',[_A194,_B195]),'listExp'('rangeEnum'([_na7,_kab6]))])])]),['comprehensionGenerator'(_A194,'val_of'('Agent','src_span'(146,13,146,18,4219,5))),'comprehensionGenerator'(_B195,'val_of'('Agent','src_span'(146,25,146,30,4231,5))),'comprehensionGenerator'(_kab6,'val_of'('SessionKey','src_span'(146,39,146,49,4245,10))),'comprehensionGenerator'(_na7,'val_of'('Nonce','src_span'(146,57,146,62,4263,5)))]),'setExp'('rangeEnum'(['dotTuple'(['Encrypt','tupleExp'([_kab7,'listExp'('rangeEnum'([_na8]))])])]),['comprehensionGenerator'(_kab7,'val_of'('SessionKey','src_span'(148,15,148,25,4312,10))),'comprehensionGenerator'(_na8,'val_of'('Nonce','src_span'(148,33,148,38,4330,5)))])]))]),'src_span'(138,1,149,5,4078,263)).
'agent'('unSq_'('dotpat'(['Sq',_ms_2])),'agent_call'('src_span'(151,18,151,21,4360,3),'set',[_ms_2]),'src_span'(151,18,151,26,4360,8)).
'agent'('unSq_'(_m_201),'setExp'('rangeEnum'([_m_201])),'src_span'(152,14,152,18,4382,4)).
'bindval'('IK0','setExp'('rangeEnum'(['Alice','Bob','Mallory','Ni','agent_call'('src_span'(154,33,154,39,4420,6),'shared',['Alice','Mallory']),'agent_call'('src_span'(155,8,155,14,4452,6),'shared',['Bob','Mallory']),'agent_call'('src_span'(155,30,155,36,4474,6),'shared',['Mallory','Alice']),'agent_call'('src_span'(155,54,155,60,4498,6),'shared',['Mallory','Bob']),'Garbage'])),'src_span'(154,1,155,84,4388,140)).
'agent'('unknown_'(_S2),'agent_call'('src_span'(157,15,157,19,4544,4),'diff',[_S2,'val_of'('IK0','src_span'(157,22,157,25,4551,3))]),'src_span'(157,15,157,26,4544,11)).
'bindval'('Deductions_0','agent_call'('src_span'(160,3,160,8,4574,5),'Union',['setExp'('rangeEnum'(['val_of'('EncryptionDeductions','src_span'(160,10,160,30,4581,20)),'val_of'('DecryptionDeductions','src_span'(160,32,160,52,4603,20)),'val_of'('VernEncDeductions','src_span'(161,10,161,27,4634,17)),'val_of'('VernDecDeductions','src_span'(161,29,161,46,4653,17)),'val_of'('VernEquivs','src_span'(161,48,161,58,4672,10)),'val_of'('UserDeductions','src_span'(162,10,162,24,4694,14)),'val_of'('FnAppDeductions','src_span'(162,26,162,41,4710,15))]))]),'src_span'(159,1,162,43,4557,170)).
'bindval'('EncryptionDeductions','setExp'('rangeEnum'(['tupleExp'(['dotTuple'(['Encrypt','tupleExp'([_k_4,_fs_])]),'agent_call'('src_span'(165,23,165,31,4774,8),'unknown_',['agent_call'('src_span'(165,32,165,37,4783,5),'union',['setExp'('rangeEnum'([_k_4])),'agent_call'('src_span'(165,44,165,47,4795,3),'set',[_fs_])])])])]),['comprehensionGenerator'('dotpat'(['Encrypt','tuplePat'([_k_4,_fs_])]),'val_of'('Fact_0','src_span'(166,27,166,33,4836,6)))]),'src_span'(164,1,166,34,4729,114)).
'bindval'('DecryptionDeductions','setExp'('rangeEnum'(['tupleExp'([_f_,'agent_call'('src_span'(169,9,169,17,4876,8),'unknown_',['setExp'('rangeEnum'(['dotTuple'(['Encrypt','tupleExp'([_k_5,_fs_2])]),'agent_call'('src_span'(169,37,169,44,4904,7),'inverse',[_k_5])]))])])]),['comprehensionGenerator'('dotpat'(['Encrypt','tuplePat'([_k_5,_fs_2])]),'val_of'('Fact_0','src_span'(170,27,170,33,4947,6))),'comprehensionGenerator'(_f_,'agent_call'('src_span'(170,41,170,49,4961,8),'unknown_',['agent_call'('src_span'(170,50,170,53,4970,3),'set',[_fs_2])]))]),'src_span'(168,1,170,60,4845,135)).
'bindval'('VernEncDeductions','setExp'('rangeEnum'(['tupleExp'(['dotTuple'(['Xor','tupleExp'([_m1_,_m2_])]),'agent_call'('src_span'(173,20,173,28,5021,8),'unknown_',['agent_call'('src_span'(173,29,173,34,5030,5),'union',['agent_call'('src_span'(173,35,173,40,5036,5),'unSq_',[_m1_]),'agent_call'('src_span'(173,47,173,52,5048,5),'unSq_',[_m2_])])])])]),['comprehensionGenerator'('dotpat'(['Xor','tuplePat'([_m1_,_m2_])]),'val_of'('Fact_0','src_span'(174,24,174,30,5088,6)))]),'src_span'(172,1,174,31,4982,113)).
'bindval'('VernDecDeductions','agent_call'('src_span'(177,3,177,8,5119,5),'union',['setExp'('rangeEnum'(['tupleExp'([_m11_,'agent_call'('src_span'(178,13,178,18,5138,5),'union',['agent_call'('src_span'(178,19,178,27,5144,8),'unknown_',['agent_call'('src_span'(178,28,178,33,5153,5),'unSq_',[_m2_2])]),'setExp'('rangeEnum'(['dotTuple'(['Xor','tupleExp'([_m1_2,_m2_2])])]))])])]),['comprehensionGenerator'('dotpat'(['Xor','tuplePat'([_m1_2,_m2_2])]),'val_of'('Fact_0','src_span'(179,25,179,31,5211,6))),'comprehensionGenerator'(_m11_,'agent_call'('src_span'(179,41,179,46,5227,5),'unSq_',[_m1_2]))]),'setExp'('rangeEnum'(['tupleExp'([_m21_,'agent_call'('src_span'(180,13,180,18,5252,5),'union',['agent_call'('src_span'(180,19,180,27,5258,8),'unknown_',['agent_call'('src_span'(180,28,180,33,5267,5),'unSq_',[_m1_3])]),'setExp'('rangeEnum'(['dotTuple'(['Xor','tupleExp'([_m1_3,_m2_3])])]))])])]),['comprehensionGenerator'('dotpat'(['Xor','tuplePat'([_m1_3,_m2_3])]),'val_of'('Fact_0','src_span'(181,25,181,31,5325,6))),'comprehensionGenerator'(_m21_,'agent_call'('src_span'(181,41,181,46,5341,5),'unSq_',[_m2_3]))])]),'src_span'(176,1,181,53,5097,256)).
'bindval'('VernEquivs','setExp'('rangeEnum'(['tupleExp'(['dotTuple'(['Xor','tupleExp'([_m2_4,_m1_4])]),'setExp'('rangeEnum'(['dotTuple'(['Xor','tupleExp'([_m1_4,_m2_4])])]))])]),['comprehensionGenerator'('dotpat'(['Xor','tuplePat'([_m1_4,_m2_4])]),'val_of'('Fact_0','src_span'(185,24,185,30,5430,6))),'comprehensionGuard'('agent_call'('src_span'(185,32,185,38,5438,6),'member',['dotTuple'(['Xor','tupleExp'([_m2_4,_m1_4])]),'val_of'('Fact_0','src_span'(185,55,185,61,5461,6))]))]),'src_span'(183,1,185,64,5355,115)).
'bindval'('UserDeductions','setExp'('rangeEnum'([])),'src_span'(187,1,187,20,5472,19)).
'bindval'('FnAppDeductions','setExp'('rangeEnum'([])),'src_span'(189,1,189,21,5493,20)).
'agent'('components_'('tuplePat'([_,'dotpat'(['Sq',_ms_3])])),'agent_call'('src_span'(191,28,191,31,5542,3),'set',[_ms_3]),'src_span'(191,28,191,36,5542,8)).
'agent'('components_'('tuplePat'([_,_m_219])),'setExp'('rangeEnum'([_m_219])),'src_span'(192,24,192,28,5574,4)).
'agent'('subset'(_A_,_B_),'=='('agent_call'('src_span'(196,17,196,22,5634,5),'inter',[_A_,_B_]),_A_),'no_loc_info_available').
'bindval'('Seeable_','agent_call'('src_span'(198,12,198,17,5665,5),'Union',['setExp'('rangeEnum'(['agent_call'('src_span'(198,19,198,27,5672,8),'unknown_',['agent_call'('src_span'(198,28,198,39,5681,11),'components_',[_m_222])])]),['comprehensionGenerator'(_m_222,'val_of'('MSG_BODY','src_span'(198,53,198,61,5706,8)))])]),'src_span'(198,1,198,63,5654,62)).
'agent'('Close_'(_IK_,_ded_,_fact_),'let'(['bindval'('IK1_','agent_call'('src_span'(202,9,202,14,5767,5),'union',[_IK_,'setExp'('rangeEnum'([_f_2]),['comprehensionGenerator'('tuplePat'([_f_2,_fs_3]),_ded_),'comprehensionGuard'('agent_call'('src_span'(202,44,202,50,5802,6),'subset',[_fs_3,_IK_]))])]),'src_span'(201,7,202,61,5751,68)),'bindval'('ded1_','setExp'('rangeEnum'(['tupleExp'([_f_3,_fs_4])]),['comprehensionGenerator'('tuplePat'([_f_3,_fs_4]),_ded_),'comprehensionGuard'('bool_not'('agent_call'('src_span'(204,44,204,50,5878,6),'member',[_f_3,_IK_]))),'comprehensionGuard'('agent_call'('src_span'(205,25,205,31,5919,6),'subset',[_fs_4,_fact_]))]),'src_span'(203,7,205,43,5826,111)),'bindval'('fact1_','agent_call'('src_span'(206,16,206,21,5953,5),'Union',['setExp'('rangeEnum'([_IK_,'setExp'('rangeEnum'([_f_4]),['comprehensionGenerator'('tuplePat'([_f_4,_fs_5]),_ded_)]),'val_of'('Seeable_','src_span'(206,53,206,61,5990,8))]))]),'src_span'(206,7,206,63,5944,56))],'ifte'('bool_and'('bool_and'('=='(_IK_,'val_of'('IK1_','src_span'(208,11,208,15,6020,4))),'=='(_ded_,'val_of'('ded1_','src_span'(208,26,208,31,6035,5)))),'=='(_fact_,'val_of'('fact1_','src_span'(208,43,208,49,6052,6)))),'tupleExp'([_IK_,'setExp'('rangeEnum'(['tupleExp'([_f_5,'agent_call'('src_span'(209,19,209,23,6077,4),'diff',[_fs_6,_IK_])])]),['comprehensionGenerator'('tuplePat'([_f_5,_fs_6]),_ded_)]),_fact_]),'agent_call'('src_span'(210,8,210,14,6127,6),'Close_',['val_of'('IK1_','src_span'(210,15,210,19,6134,4)),'val_of'('ded1_','src_span'(210,21,210,26,6140,5)),'val_of'('fact1_','src_span'(210,28,210,34,6147,6))]),'no_loc_info_available','no_loc_info_available','src_span'(209,62,210,7,6119,88))),'src_span'(201,3,210,35,5747,407)).
'bindval'('Deductions_1','setExp'('rangeEnum'(['tupleExp'([_f_6,_fs_7])]),['comprehensionGenerator'('tuplePat'([_f_6,_fs_7]),'val_of'('Deductions_0','src_span'(212,40,212,52,6195,12))),'comprehensionGuard'('bool_not'('agent_call'('src_span'(213,37,213,43,6246,6),'member',[_f_6,_fs_7])))]),'src_span'(212,1,213,53,6156,106)).
'bindval'('triple_','agent_call'('src_span'(215,11,215,17,6274,6),'Close_',['val_of'('IK0','src_span'(215,18,215,21,6281,3)),'val_of'('Deductions_1','src_span'(215,23,215,35,6286,12)),'val_of'('Fact_0','src_span'(215,37,215,43,6300,6))]),'src_span'(215,1,215,44,6264,43)).
'agent'('first_'('tuplePat'([_a_,_,_])),_a_,'src_span'(217,20,217,22,6328,2)).
'agent'('second_'('tuplePat'([_,_b_,_])),_b_,'src_span'(218,21,218,23,6351,2)).
'agent'('third_'('tuplePat'([_,_,_c_])),_c_,'src_span'(219,20,219,22,6373,2)).
'bindval'('IK1','agent_call'('src_span'(221,7,221,13,6383,6),'first_',['val_of'('triple_','src_span'(221,14,221,21,6390,7))]),'src_span'(221,1,221,22,6377,21)).
'bindval'('Deductions','agent_call'('src_span'(222,14,222,21,6412,7),'second_',['val_of'('triple_','src_span'(222,22,222,29,6420,7))]),'src_span'(222,1,222,30,6399,29)).
'bindval'('Fact','agent_call'('src_span'(223,8,223,14,6436,6),'third_',['val_of'('triple_','src_span'(223,15,223,22,6443,7))]),'src_span'(223,1,223,23,6429,22)).
'cspPrint'('agent_call'('src_span'(224,7,224,11,6458,4),'card',['val_of'('IK1','src_span'(224,12,224,15,6463,3))])).
'cspPrint'('agent_call'('src_span'(225,7,225,11,6474,4),'card',['val_of'('Fact','src_span'(225,12,225,16,6479,4))])).
'cspPrint'('agent_call'('src_span'(226,7,226,11,6491,4),'card',['val_of'('Deductions','src_span'(226,12,226,22,6496,10))])).
'cspPrint'('agent_call'('src_span'(227,7,227,11,6514,4),'diff',['val_of'('Fact_0','src_span'(227,12,227,18,6519,6)),'val_of'('Fact','src_span'(227,19,227,23,6526,4))])).
'cspPrint'('agent_call'('src_span'(228,7,228,11,6538,4),'diff',['val_of'('IK1','src_span'(228,12,228,15,6543,3)),'val_of'('IK0','src_span'(228,16,228,19,6547,3))])).
'channel'('leak','type'('dotTupleType'(['agent_call'('src_span'(232,16,232,27,6585,11),'addGarbage_',['val_of'('ALL_SECRETS','src_span'(232,28,232,39,6597,11))])]))).
'channel'('hear','type'('dotTupleType'(['val_of'('MSG_BODY','src_span'(233,21,233,29,6630,8))]))).
'channel'('say','type'('dotTupleType'(['val_of'('MSG_BODY','src_span'(233,21,233,29,6630,8))]))).
'channel'('infer','type'('dotTupleType'(['val_of'('Deductions','src_span'(234,17,234,27,6655,10))]))).
'agent'('IGNORANT'(_f_7,_ms_4,_fss_,_ds_),'[]'('prefix'('src_span'(237,4,237,8,6698,4),['inGuard'(_m_246,_ms_4)],'hear','agent_call'('src_span'(237,19,237,24,6713,5),'KNOWS',[_f_7,_ms_4,_ds_]),'src_span'(237,16,237,18,6709,28)),'repChoice'(['comprehensionGenerator'(_fs_8,_fss_)],'prefix'('src_span'(239,21,239,35,6757,14),[],'dotTuple'(['infer','tupleExp'([_f_7,_fs_8])]),'agent_call'('src_span'(239,39,239,44,6775,5),'KNOWS',[_f_7,_ms_4,_ds_]),'src_span'(239,36,239,38,6771,35)),'src_span'(239,8,239,20,6744,12)),'src_span_operator'('no_loc_info_available','src_span'(238,4,238,6,6734,2))),'no_loc_info_available').
'agent'('KNOWS'(_f_8,_ms_5,_ds_2),'[]'('[]'('[]'('prefix'('src_span'(242,3,242,7,6817,4),['inGuard'(_m_251,_ms_5)],'hear','agent_call'('src_span'(242,18,242,23,6832,5),'KNOWS',[_f_8,_ms_5,_ds_2]),'src_span'(242,15,242,17,6828,28)),'prefix'('src_span'(244,3,244,6,6857,3),['inGuard'(_m_252,_ms_5)],'say','agent_call'('src_span'(244,17,244,22,6871,5),'KNOWS',[_f_8,_ms_5,_ds_2]),'src_span'(244,14,244,16,6867,28)),'src_span_operator'('no_loc_info_available','src_span'(243,3,243,5,6852,2))),'prefix'('src_span'(246,3,246,8,6897,5),['inGuard'('tuplePat'([_f1_,_fs_9]),_ds_2)],'infer','agent_call'('src_span'(246,28,246,33,6922,5),'KNOWS',[_f_8,_ms_5,_ds_2]),'src_span'(246,25,246,27,6918,37)),'src_span_operator'('no_loc_info_available','src_span'(245,3,245,5,6891,2))),'&'('agent_call'('src_span'(248,3,248,9,6947,6),'member',[_f_8,'val_of'('ALL_SECRETS','src_span'(248,13,248,24,6957,11))]),'prefix'('src_span'(248,28,248,35,6972,7),[],'dotTuple'(['leak',_f_8]),'agent_call'('src_span'(248,39,248,44,6983,5),'KNOWS',[_f_8,_ms_5,_ds_2]),'src_span'(248,36,248,38,6979,28))),'src_span_operator'('no_loc_info_available','src_span'(247,3,247,5,6942,2))),'no_loc_info_available').
'bindval'('f_ms_fss_ds_s','setExp'('rangeEnum'(['tupleExp'([_f_9,'setExp'('rangeEnum'([_m_256]),['comprehensionGenerator'(_m_256,'val_of'('MSG_BODY','src_span'(251,21,251,29,7039,8))),'comprehensionGuard'('agent_call'('src_span'(251,31,251,37,7049,6),'member',[_f_9,'agent_call'('src_span'(251,41,251,52,7059,11),'components_',[_m_256])]))]),'setExp'('rangeEnum'([_fs_258]),['comprehensionGenerator'('tuplePat'([_f1_2,_fs_258]),'val_of'('Deductions','src_span'(252,29,252,39,7106,10))),'comprehensionGuard'('=='(_f_9,_f1_2))]),'setExp'('rangeEnum'(['tupleExp'([_f1_3,_fs_260])]),['comprehensionGenerator'('tuplePat'([_f1_3,_fs_260]),'val_of'('Deductions','src_span'(253,35,253,45,7164,10))),'comprehensionGuard'('agent_call'('src_span'(253,47,253,53,7176,6),'member',[_f_9,_fs_260]))])])]),['comprehensionGenerator'(_f_9,'agent_call'('src_span'(254,17,254,21,7212,4),'diff',['val_of'('Fact','src_span'(254,22,254,26,7217,4)),'val_of'('IK1','src_span'(254,27,254,30,7222,3))]))]),'src_span'(250,1,254,32,7002,225)).
'agent'('AlphaL'(_f_261,_ms_6,_fss_2,_ds_3),'agent_call'('src_span'(257,3,257,8,7257,5),'Union',['setExp'('rangeEnum'(['ifte'('agent_call'('src_span'(257,14,257,20,7268,6),'member',[_f_261,'val_of'('ALL_SECRETS','src_span'(257,24,257,35,7278,11))]),'setExp'('rangeEnum'(['dotTuple'(['leak',_f_261])])),'setExp'('rangeEnum'([])),'src_span'(257,11,257,36,7265,25),'src_span'(257,37,257,41,7290,37),'src_span'(257,52,257,56,7305,17)),'setExp'('rangeEnum'(['dotTuple'(['hear',_m_265]),'dotTuple'(['say',_m_265])]),['comprehensionGenerator'(_m_265,_ms_6)]),'setExp'('rangeEnum'(['dotTuple'(['infer','tupleExp'([_f_261,_fs_266])])]),['comprehensionGenerator'(_fs_266,_fss_2)]),'setExp'('rangeEnum'(['dotTuple'(['infer','tupleExp'([_f1_4,_fs_268])])]),['comprehensionGenerator'('tuplePat'([_f1_4,_fs_268]),_ds_3)])]))]),'src_span'(257,3,260,48,7257,187)).
'cspTransparent'(['chase']).
'bindval'('INTRUDER_0','\x5c\'('procRepAParallel'(['comprehensionGenerator'('tuplePat'([_f_269,_ms_7,_fss_3,_ds_4]),'val_of'('f_ms_fss_ds_s','src_span'(265,27,265,40,7505,13)))],'pair'('agent_call'('src_span'(266,10,266,16,7531,6),'AlphaL',[_f_269,_ms_7,_fss_3,_ds_4]),'agent_call'('src_span'(266,35,266,43,7556,8),'IGNORANT',[_f_269,_ms_7,_fss_3,_ds_4])),'src_span'(265,7,265,42,7485,35)),'closure'(['infer']),'src_span_operator'('no_loc_info_available','src_span'(267,3,267,4,7586,1))),'src_span'(264,1,267,14,7465,132)).
'bindval'('INTRUDER_1','procRenamingComp'('agent_call'('src_span'(270,3,270,8,7614,5),'chase',['val_of'('INTRUDER_0','src_span'(270,9,270,19,7620,10))]),['comprehensionGenerator'(_m_273,'val_of'('MSG_BODY','src_span'(274,15,274,23,7749,8))),'comprehensionGenerator'(_A_2,'agent_call'('src_span'(274,31,274,41,7765,10),'SenderType',[_m_273])),'comprehensionGenerator'(_B_2,'agent_call'('src_span'(274,53,274,65,7787,12),'ReceiverType',[_m_273]))],['rename'('dotTuple'(['hear',_m_273]),'dotTuple'(['comm',_A_2,_B_2,_m_273])),'rename'('dotTuple'(['hear',_m_273]),'dotTuple'(['intercept',_A_2,_B_2,_m_273])),'rename'('dotTuple'(['say',_m_273]),'dotTuple'(['fake',_A_2,_B_2,_m_273]))]),'src_span'(269,1,274,71,7599,206)).
'bindval'('SAY_KNOWN','[]'('repChoice'(['comprehensionGenerator'(_f_276,'agent_call'('src_span'(277,12,277,17,7830,5),'inter',['val_of'('IK1','src_span'(277,18,277,21,7836,3)),'val_of'('ALL_SECRETS','src_span'(277,23,277,34,7841,11))]))],'prefix'('src_span'(277,38,277,45,7856,7),[],'dotTuple'(['leak',_f_276]),'val_of'('SAY_KNOWN','src_span'(277,49,277,58,7867,9)),'src_span'(277,46,277,48,7863,20)),'src_span'(277,7,277,37,7825,30)),'repChoice'(['comprehensionGenerator'(_m_278,'setExp'('rangeEnum'([_m_277]),['comprehensionGenerator'(_m_277,'val_of'('MSG_BODY','src_span'(279,24,279,32,7906,8))),'comprehensionGuard'('agent_call'('src_span'(279,34,279,40,7916,6),'subset',['agent_call'('src_span'(279,41,279,52,7923,11),'components_',[_m_277]),'val_of'('IK1','src_span'(279,57,279,60,7939,3))]))]))],'let'(['bindval'('ST_','agent_call'('src_span'(280,16,280,26,7962,10),'SenderType',[_m_278]),'src_span'(280,10,280,30,7956,20)),'bindval'('RT_','agent_call'('src_span'(281,16,281,28,7992,12),'ReceiverType',[_m_278]),'src_span'(281,10,281,32,7986,22))],'[]'('[]'('prefix'('src_span'(283,11,283,15,8031,4),['inGuard'(_A_3,'agent_call'('src_span'(283,19,283,23,8039,4),'diff',['val_of'('ST_','src_span'(283,24,283,27,8044,3)),'setExp'('rangeEnum'(['Mallory']))])),'inGuard'(_B_3,'val_of'('RT_','src_span'(283,42,283,45,8062,3))),'out'(_m_278)],'comm','val_of'('SAY_KNOWN','src_span'(283,52,283,61,8072,9)),'src_span'(283,49,283,51,8068,16)),'prefix'('src_span'(284,11,284,20,8092,9),['inGuard'(_A_4,'agent_call'('src_span'(284,24,284,28,8105,4),'diff',['val_of'('ST_','src_span'(284,29,284,32,8110,3)),'setExp'('rangeEnum'(['Mallory']))])),'inGuard'(_B_4,'val_of'('RT_','src_span'(284,47,284,50,8128,3))),'out'(_m_278)],'intercept','val_of'('SAY_KNOWN','src_span'(284,57,284,66,8138,9)),'src_span'(284,54,284,56,8134,16)),'src_span_operator'('no_loc_info_available','src_span'(284,8,284,10,8089,2))),'prefix'('src_span'(285,11,285,15,8158,4),['inGuard'(_A_5,'val_of'('ST_','src_span'(285,19,285,22,8166,3))),'inGuard'(_B_5,'val_of'('RT_','src_span'(285,26,285,29,8173,3))),'out'(_m_278)],'fake','val_of'('SAY_KNOWN','src_span'(285,36,285,45,8183,9)),'src_span'(285,33,285,35,8179,16)),'src_span_operator'('no_loc_info_available','src_span'(285,8,285,10,8155,2)))),'src_span'(279,7,279,64,7889,57)),'src_span_operator'('no_loc_info_available','src_span'(278,3,278,5,7880,2))),'src_span'(276,1,285,47,7807,387)).
'bindval'('INTRUDER','|||'('sharing'('closure'(['dotTuple'(['comm','Mallory']),'dotTuple'(['intercept','Mallory'])]),'val_of'('INTRUDER_1','src_span'(288,4,288,14,8210,10)),'stop'('src_span'(288,55,288,59,8261,4)),'src_span'(288,15,288,54,8221,39)),'val_of'('SAY_KNOWN','src_span'(288,65,288,74,8271,9)),'src_span_operator'('no_loc_info_available','src_span'(288,61,288,64,8267,3))),'src_span'(287,1,288,74,8196,84)).
'bindval'('Alpha_INITIATOR_Alice','agent_call'('src_span'(293,3,293,8,8340,5),'Union',['setExp'('rangeEnum'(['closureComp'(['comprehensionGenerator'(_A_6,'val_of'('ALL_PRINCIPALS','src_span'(294,32,294,46,8379,14))),'comprehensionGenerator'(_m_288,'val_of'('MSG1_BODY','src_span'(294,54,294,63,8401,9)))],['dotTuple'(['comm','Alice',_A_6,_m_288])]),'closureComp'(['comprehensionGenerator'(_A_7,'val_of'('ALL_PRINCIPALS','src_span'(295,32,295,46,8445,14))),'comprehensionGenerator'(_m_290,'val_of'('MSG3_BODY','src_span'(295,54,295,63,8467,9)))],['dotTuple'(['comm','Alice',_A_7,_m_290])]),'closureComp'(['comprehensionGenerator'(_A_8,'val_of'('ALL_PRINCIPALS','src_span'(296,32,296,46,8511,14))),'comprehensionGenerator'(_m_292,'val_of'('MSG2_BODY','src_span'(296,54,296,63,8533,9)))],['dotTuple'(['comm',_A_8,'Alice',_m_292])]),'closureComp'(['comprehensionGenerator'(_A_9,'val_of'('ALL_PRINCIPALS','src_span'(297,32,297,46,8577,14))),'comprehensionGenerator'(_m_294,'val_of'('MSG4_BODY','src_span'(297,54,297,63,8599,9)))],['dotTuple'(['comm',_A_9,'Alice',_m_294])])]))]),'src_span'(292,1,298,5,8313,302)).
'bindval'('INITIATOR_Alice','agent_call'('src_span'(300,19,300,28,8635,9),'INITIATOR',['Alice','Na']),'src_span'(300,1,300,39,8617,38)).
'bindval'('Alpha_Alice','closureComp'(['comprehensionGenerator'(_A_295,'val_of'('ALL_PRINCIPALS','src_span'(302,54,302,68,8710,14)))],['dotTuple'(['comm','Alice',_A_295]),'dotTuple'(['comm',_A_295,'Alice'])]),'src_span'(302,1,302,70,8657,69)).
'bindval'('Alice_STOP_SET','agent_call'('src_span'(305,3,305,8,8748,5),'Union',['setExp'('rangeEnum'(['closureComp'(['comprehensionGenerator'(_A_296,'val_of'('ALL_PRINCIPALS','src_span'(306,32,306,46,8787,14))),'comprehensionGenerator'(_m_297,'val_of'('MSG2_BODY','src_span'(306,54,306,63,8809,9)))],['dotTuple'(['comm','Alice',_A_296,_m_297])]),'closureComp'(['comprehensionGenerator'(_A_298,'val_of'('ALL_PRINCIPALS','src_span'(307,32,307,46,8853,14))),'comprehensionGenerator'(_m_299,'val_of'('MSG4_BODY','src_span'(307,54,307,63,8875,9)))],['dotTuple'(['comm','Alice',_A_298,_m_299])]),'closureComp'(['comprehensionGenerator'(_A_300,'val_of'('ALL_PRINCIPALS','src_span'(308,32,308,46,8919,14))),'comprehensionGenerator'(_m_301,'val_of'('MSG1_BODY','src_span'(308,54,308,63,8941,9)))],['dotTuple'(['comm',_A_300,'Alice',_m_301])]),'closureComp'(['comprehensionGenerator'(_A_302,'val_of'('ALL_PRINCIPALS','src_span'(309,32,309,46,8985,14))),'comprehensionGenerator'(_m_303,'val_of'('MSG3_BODY','src_span'(309,54,309,63,9007,9)))],['dotTuple'(['comm',_A_302,'Alice',_m_303])])]))]),'src_span'(304,1,310,5,8728,295)).
'bindval'('AGENT_Alice','sharing'('val_of'('Alice_STOP_SET','src_span'(313,21,313,35,9059,14)),'val_of'('INITIATOR_Alice','src_span'(313,3,313,18,9041,15)),'stop'('src_span'(313,38,313,42,9076,4)),'src_span'(313,19,313,37,9057,18)),'src_span'(312,1,313,42,9025,55)).
'bindval'('Alpha_RESPONDER_Bob','agent_call'('src_span'(318,3,318,8,9136,5),'Union',['setExp'('rangeEnum'(['closureComp'(['comprehensionGenerator'(_A_304,'val_of'('ALL_PRINCIPALS','src_span'(319,30,319,44,9173,14))),'comprehensionGenerator'(_m_305,'val_of'('MSG2_BODY','src_span'(319,52,319,61,9195,9)))],['dotTuple'(['comm','Bob',_A_304,_m_305])]),'closureComp'(['comprehensionGenerator'(_A_306,'val_of'('ALL_PRINCIPALS','src_span'(320,30,320,44,9237,14))),'comprehensionGenerator'(_m_307,'val_of'('MSG4_BODY','src_span'(320,52,320,61,9259,9)))],['dotTuple'(['comm','Bob',_A_306,_m_307])]),'closureComp'(['comprehensionGenerator'(_A_308,'val_of'('ALL_PRINCIPALS','src_span'(321,30,321,44,9301,14))),'comprehensionGenerator'(_m_309,'val_of'('MSG1_BODY','src_span'(321,52,321,61,9323,9)))],['dotTuple'(['comm',_A_308,'Bob',_m_309])]),'closureComp'(['comprehensionGenerator'(_A_310,'val_of'('ALL_PRINCIPALS','src_span'(322,30,322,44,9365,14))),'comprehensionGenerator'(_m_311,'val_of'('MSG3_BODY','src_span'(322,52,322,61,9387,9)))],['dotTuple'(['comm',_A_310,'Bob',_m_311])])]))]),'src_span'(317,1,323,5,9111,292)).
'bindval'('RESPONDER_Bob','agent_call'('src_span'(325,17,325,26,9421,9),'RESPONDER',['Bob','Kab','Nb']),'src_span'(325,1,325,40,9405,39)).
'bindval'('Alpha_Bob','closureComp'(['comprehensionGenerator'(_A_312,'val_of'('ALL_PRINCIPALS','src_span'(327,48,327,62,9493,14)))],['dotTuple'(['comm','Bob',_A_312]),'dotTuple'(['comm',_A_312,'Bob'])]),'src_span'(327,1,327,64,9446,63)).
'bindval'('Bob_STOP_SET','agent_call'('src_span'(330,3,330,8,9529,5),'Union',['setExp'('rangeEnum'(['closureComp'(['comprehensionGenerator'(_A_313,'val_of'('ALL_PRINCIPALS','src_span'(331,30,331,44,9566,14))),'comprehensionGenerator'(_m_314,'val_of'('MSG1_BODY','src_span'(331,52,331,61,9588,9)))],['dotTuple'(['comm','Bob',_A_313,_m_314])]),'closureComp'(['comprehensionGenerator'(_A_315,'val_of'('ALL_PRINCIPALS','src_span'(332,30,332,44,9630,14))),'comprehensionGenerator'(_m_316,'val_of'('MSG3_BODY','src_span'(332,52,332,61,9652,9)))],['dotTuple'(['comm','Bob',_A_315,_m_316])]),'closureComp'(['comprehensionGenerator'(_A_317,'val_of'('ALL_PRINCIPALS','src_span'(333,30,333,44,9694,14))),'comprehensionGenerator'(_m_318,'val_of'('MSG2_BODY','src_span'(333,52,333,61,9716,9)))],['dotTuple'(['comm',_A_317,'Bob',_m_318])]),'closureComp'(['comprehensionGenerator'(_A_319,'val_of'('ALL_PRINCIPALS','src_span'(334,30,334,44,9758,14))),'comprehensionGenerator'(_m_320,'val_of'('MSG4_BODY','src_span'(334,52,334,61,9780,9)))],['dotTuple'(['comm',_A_319,'Bob',_m_320])])]))]),'src_span'(329,1,335,5,9511,285)).
'bindval'('AGENT_Bob','sharing'('val_of'('Bob_STOP_SET','src_span'(338,19,338,31,9828,12)),'val_of'('RESPONDER_Bob','src_span'(338,3,338,16,9812,13)),'stop'('src_span'(338,34,338,38,9843,4)),'src_span'(338,17,338,33,9826,16)),'src_span'(337,1,338,38,9798,49)).
'bindval'('SYSTEM_0','sharing'('agent_call'('src_span'(344,8,344,13,9902,5),'inter',['val_of'('Alpha_Alice','src_span'(344,14,344,25,9908,11)),'val_of'('Alpha_Bob','src_span'(344,27,344,36,9921,9))]),'val_of'('AGENT_Alice','src_span'(343,4,343,15,9883,11)),'val_of'('AGENT_Bob','src_span'(345,3,345,12,9937,9)),'src_span'(344,5,344,40,9899,35)),'src_span'(342,1,345,13,9869,78)).
'bindval'('SYSTEM','sharing'('closure'(['comm','fake','intercept']),'val_of'('SYSTEM_0','src_span'(347,10,347,18,9958,8)),'val_of'('INTRUDER','src_span'(347,51,347,59,9999,8)),'src_span'(347,19,347,50,9967,31)),'src_span'(347,1,347,59,9949,58)).
'bindval'('Sigma','closure'(['comm','fake','intercept','env','signal','leak']),'src_span'(351,1,351,53,10036,52)).
'agent'('SECRET_SPEC_0'(_s_),'[]'('prefix'('src_span'(356,3,356,22,10139,19),['in'(_A_322),'out'(_s_),'in'(_Bs_)],'dotTuple'(['signal','Claim_Secret']),'ifte'('agent_call'('src_span'(357,9,357,15,10180,6),'member',['Mallory',_Bs_]),'agent_call'('src_span'(357,35,357,48,10206,13),'SECRET_SPEC_0',[_s_]),'agent_call'('src_span'(357,58,357,71,10229,13),'SECRET_SPEC_1',[_s_]),'src_span'(357,6,357,29,10177,23),'src_span'(357,30,357,34,10200,43),'src_span'(357,53,357,57,10223,40)),'src_span'(356,33,357,4,10168,83)),'prefix'('src_span'(359,3,359,10,10255,7),[],'dotTuple'(['leak',_s_]),'agent_call'('src_span'(359,14,359,27,10266,13),'SECRET_SPEC_0',[_s_]),'src_span'(359,11,359,13,10262,28)),'src_span_operator'('no_loc_info_available','src_span'(358,3,358,5,10250,2))),'no_loc_info_available').
'agent'('SECRET_SPEC_1'(_s_2),'prefix'('src_span'(361,21,361,40,10305,19),['in'(_A_325),'out'(_s_2),'in'(_Bs_2)],'dotTuple'(['signal','Claim_Secret']),'agent_call'('src_span'(361,54,361,67,10338,13),'SECRET_SPEC_1',[_s_2]),'src_span'(361,51,361,53,10334,25)),'src_span'(361,21,361,71,10305,50)).
'agent'('AlphaS'(_s_3),'agent_call'('src_span'(364,3,364,8,10373,5),'union',['closureComp'(['comprehensionGenerator'(_A_328,'val_of'('ALL_PRINCIPALS','src_span'(364,45,364,59,10415,14)))],['dotTuple'(['signal','Claim_Secret',_A_328,_s_3])]),'setExp'('rangeEnum'(['dotTuple'(['leak',_s_3])]))]),'src_span'(364,3,364,73,10373,70)).
'bindval'('Alpha_SECRETS','closure'(['leak','dotTuple'(['signal','Claim_Secret'])]),'src_span'(366,1,366,46,10445,45)).
'bindval'('SECRET_SPEC','procRepAParallel'(['comprehensionGenerator'(_s_4,'val_of'('ALL_SECRETS','src_span'(368,24,368,35,10515,11)))],'pair'('agent_call'('src_span'(368,39,368,45,10530,6),'AlphaS',[_s_4]),'agent_call'('src_span'(368,51,368,64,10542,13),'SECRET_SPEC_0',[_s_4])),'src_span'(368,19,368,37,10510,18)),'src_span'(368,1,368,69,10492,68)).
'assertRef'('False','val_of'('SECRET_SPEC','src_span'(370,8,370,19,10569,11)),'Trace','\x5c\'('val_of'('SYSTEM','src_span'(370,24,370,30,10585,6)),'agent_call'('src_span'(370,33,370,37,10594,4),'diff',['val_of'('Sigma','src_span'(370,38,370,43,10599,5)),'val_of'('Alpha_SECRETS','src_span'(370,45,370,58,10606,13))]),'src_span_operator'('no_loc_info_available','src_span'(370,31,370,32,10592,1))),'src_span'(370,1,370,59,10562,58)).
'agent'('AuthenticateRESPONDERToINITIATORAliveness'(_B330),'prefix'('src_span'(375,3,375,18,10713,15),['in'(_B_role_),'out'(_B330),'in'(_C_)],'dotTuple'(['signal','Running1']),'builtin_call'('CHAOS'('src_span'(376,3,376,58,10747,55),'setExp'('rangeEnum'(['dotTuple'(['signal','Commit1','INITIATOR_role',_A333,_B330])]),['comprehensionGenerator'(_A333,'val_of'('Agent','src_span'(376,51,376,56,10795,5)))]))),'src_span'(375,32,376,2,10741,64)),'src_span'(375,3,376,58,10713,89)).
'agent'('AlphaAuthenticateRESPONDERToINITIATORAliveness'(_B334),'closureComp'(['comprehensionGenerator'(_A335,'val_of'('Agent','src_span'(381,13,381,18,10941,5))),'comprehensionGenerator'(_B_role_2,'ROLE')],['dotTuple'(['signal','Running1',_B_role_2,_B334,_A335]),'dotTuple'(['signal','Commit1','INITIATOR_role',_A335,_B334])]),'src_span'(379,3,381,37,10858,107)).
'bindval'('AuthenticateRESPONDERAliceToINITIATORAliveness','stop'('src_span'(384,3,384,7,11018,4)),'src_span'(383,1,384,7,10967,55)).
'assertRef'('False','val_of'('AuthenticateRESPONDERAliceToINITIATORAliveness','src_span'(386,8,386,54,11031,46)),'Trace','\x5c\'('val_of'('SYSTEM','src_span'(387,3,387,9,11084,6)),'agent_call'('src_span'(387,12,387,16,11093,4),'diff',['val_of'('Sigma','src_span'(387,17,387,22,11098,5)),'agent_call'('src_span'(387,24,387,70,11105,46),'AlphaAuthenticateRESPONDERToINITIATORAliveness',['Alice'])]),'src_span_operator'('no_loc_info_available','src_span'(387,10,387,11,11091,1))),'src_span'(386,1,387,78,11024,135)).
'bindval'('AuthenticateRESPONDERBobToINITIATORAliveness','agent_call'('src_span'(390,3,390,44,11210,41),'AuthenticateRESPONDERToINITIATORAliveness',['Bob']),'src_span'(389,1,390,49,11161,95)).
'assertRef'('False','val_of'('AuthenticateRESPONDERBobToINITIATORAliveness','src_span'(392,8,392,52,11265,44)),'Trace','\x5c\'('val_of'('SYSTEM','src_span'(393,3,393,9,11316,6)),'agent_call'('src_span'(393,12,393,16,11325,4),'diff',['val_of'('Sigma','src_span'(393,17,393,22,11330,5)),'agent_call'('src_span'(393,24,393,70,11337,46),'AlphaAuthenticateRESPONDERToINITIATORAliveness',['Bob'])]),'src_span_operator'('no_loc_info_available','src_span'(393,10,393,11,11323,1))),'src_span'(392,1,393,76,11258,131)).
'comment'('lineComment'('-- Definitions of agents'),'src_position'(73,1,1979,24)).
'comment'('lineComment'('-- Types in actual system'),'src_position'(121,1,3628,25)).
'comment'('lineComment'('-- Facts and deductions'),'src_position'(133,1,3906,23)).
'comment'('lineComment'('--["Agent", "SharedKey", "SessionKey", "Nonce"]'),'src_position'(135,1,3931,47)).
'comment'('lineComment'('--[Encrypt (Apply "shared" ["A", "B"]) [Atom "na", Atom "kab"], Encrypt (Atom "kab") [Atom "na"]]'),'src_position'(136,1,3979,97)).
'comment'('lineComment'('-- close up knowledge and deductions'),'src_position'(194,1,5580,36)).
'comment'('lineComment'('-- The intruder'),'src_position'(230,1,6553,15)).
'comment'('lineComment'('-- Process representing Alice'),'src_position'(290,1,8282,29)).
'comment'('lineComment'('-- Process representing Bob'),'src_position'(315,1,9082,27)).
'comment'('lineComment'('-- Complete system'),'src_position'(340,1,9849,18)).
'comment'('lineComment'('-- Systems specifications'),'src_position'(349,1,10009,25)).
'comment'('lineComment'('-- Secret specifications'),'src_position'(353,1,10090,24)).
'comment'('lineComment'('---------- Authentication specifications'),'src_position'(372,1,10622,40)).
'symbol'('Encryption','Encryption','src_span'(1,10,1,20,9,10),'Datatype').
'symbol'('Alice','Alice','src_span'(2,3,2,8,24,5),'Constructor of Datatype').
'symbol'('Bob','Bob','src_span'(2,11,2,14,32,3),'Constructor of Datatype').
'symbol'('Mallory','Mallory','src_span'(2,17,2,24,38,7),'Constructor of Datatype').
'symbol'('Na','Na','src_span'(2,27,2,29,48,2),'Constructor of Datatype').
'symbol'('Nb','Nb','src_span'(2,32,2,34,53,2),'Constructor of Datatype').
'symbol'('Ni','Ni','src_span'(2,37,2,39,58,2),'Constructor of Datatype').
'symbol'('Kab','Kab','src_span'(2,42,2,45,63,3),'Constructor of Datatype').
'symbol'('Garbage','Garbage','src_span'(2,48,2,55,69,7),'Constructor of Datatype').
'symbol'('shared_','shared_','src_span'(3,3,3,10,82,7),'Constructor of Datatype').
'symbol'('Sq','Sq','src_span'(3,28,3,30,107,2),'Constructor of Datatype').
'symbol'('Seq','Seq','src_span'(3,31,3,34,110,3),'BuiltIn primitive').
'symbol'('Encrypt','Encrypt','src_span'(4,3,4,10,131,7),'Constructor of Datatype').
'symbol'('Xor','Xor','src_span'(4,40,4,43,168,3),'Constructor of Datatype').
'symbol'('ALL_KEYS','ALL_KEYS','src_span'(6,1,6,9,198,8),'Ident (Groundrep.)').
'symbol'('Union','Union','src_span'(6,12,6,17,209,5),'BuiltIn primitive').
'symbol'('ATOM','ATOM','src_span'(8,1,8,5,241,4),'Ident (Groundrep.)').
'symbol'('encrypt','encrypt','src_span'(10,1,10,8,297,7),'Funktion or Process').
'symbol'('m_','m_','src_span'(10,9,10,11,305,2),'Ident (Prolog Variable)').
'symbol'('k_','k_','src_span'(10,12,10,14,308,2),'Ident (Prolog Variable)').
'symbol'('decrypt','decrypt','src_span'(11,1,11,8,330,7),'Funktion or Process').
'symbol'('Encrypt','Encrypt','src_span'(4,3,4,10,131,7),'Constructor of Datatype').
'symbol'('k1_','k1_','src_span'(11,18,11,21,347,3),'Ident (Prolog Variable)').
'symbol'('m_2','m_','src_span'(11,22,11,24,351,2),'Ident (Prolog Variable)').
'symbol'('k_2','k_','src_span'(11,26,11,28,355,2),'Ident (Prolog Variable)').
'symbol'('decryptable','decryptable','src_span'(13,1,13,12,427,11),'Funktion or Process').
'symbol'('Encrypt','Encrypt','src_span'(4,3,4,10,131,7),'Constructor of Datatype').
'symbol'('k1_2','k1_','src_span'(13,22,13,25,448,3),'Ident (Prolog Variable)').
'symbol'('m_3','m_','src_span'(13,26,13,28,452,2),'Ident (Prolog Variable)').
'symbol'('k_3','k_','src_span'(13,30,13,32,456,2),'Ident (Prolog Variable)').
'symbol'('nth','nth','src_span'(15,1,15,4,507,3),'Funktion or Process').
'symbol'('ms_','ms_','src_span'(15,5,15,8,511,3),'Ident (Prolog Variable)').
'symbol'('n_','n_','src_span'(15,9,15,11,515,2),'Ident (Prolog Variable)').
'symbol'('head','head','src_span'(15,36,15,40,542,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(15,56,15,60,562,4),'BuiltIn primitive').
'symbol'('rmts','rmts','src_span'(17,1,17,5,582,4),'Funktion or Process').
'symbol'('Timestamp','Timestamp','src_span'(17,6,17,15,587,9),'Ident (Prolog Variable)').
'symbol'('t_','t_','src_span'(17,16,17,18,597,2),'Ident (Prolog Variable)').
'symbol'('x_','x_','src_span'(18,6,18,8,611,2),'Ident (Prolog Variable)').
'symbol'('Labels','Labels','src_span'(20,10,20,16,630,6),'Datatype').
'symbol'('Msg1','Msg1','src_span'(21,3,21,7,641,4),'Constructor of Datatype').
'symbol'('Msg2','Msg2','src_span'(21,10,21,14,648,4),'Constructor of Datatype').
'symbol'('Msg3','Msg3','src_span'(21,17,21,21,655,4),'Constructor of Datatype').
'symbol'('Msg4','Msg4','src_span'(21,24,21,28,662,4),'Constructor of Datatype').
'symbol'('Env0','Env0','src_span'(21,31,21,35,669,4),'Constructor of Datatype').
'symbol'('addGarbage_','addGarbage_','src_span'(23,1,23,12,675,11),'Funktion or Process').
'symbol'('S','S','src_span'(23,13,23,14,687,1),'Ident (Prolog Variable)').
'symbol'('union','union','src_span'(23,18,23,23,692,5),'BuiltIn primitive').
'symbol'('MSG1_BODY','MSG1_BODY','src_span'(25,1,25,10,708,9),'Ident (Groundrep.)').
'symbol'('A','A','src_span'(27,6,27,7,750,1),'Ident (Prolog Variable)').
'symbol'('na','na','src_span'(27,18,27,20,762,2),'Ident (Prolog Variable)').
'symbol'('MSG2_BODY','MSG2_BODY','src_span'(28,1,28,10,775,9),'Ident (Groundrep.)').
'symbol'('A2','A','src_span'(30,6,30,7,840,1),'Ident (Prolog Variable)').
'symbol'('B','B','src_span'(30,18,30,19,852,1),'Ident (Prolog Variable)').
'symbol'('kab','kab','src_span'(30,30,30,33,864,3),'Ident (Prolog Variable)').
'symbol'('na2','na','src_span'(30,49,30,51,883,2),'Ident (Prolog Variable)').
'symbol'('MSG3_BODY','MSG3_BODY','src_span'(31,1,31,10,896,9),'Ident (Groundrep.)').
'symbol'('kab2','kab','src_span'(33,6,33,9,947,3),'Ident (Prolog Variable)').
'symbol'('na3','na','src_span'(33,25,33,27,966,2),'Ident (Prolog Variable)').
'symbol'('MSG4_BODY','MSG4_BODY','src_span'(34,1,34,10,979,9),'Ident (Groundrep.)').
'symbol'('nb','nb','src_span'(36,6,36,8,1013,2),'Ident (Prolog Variable)').
'symbol'('MSG_BODY','MSG_BODY','src_span'(37,1,37,9,1026,8),'Ident (Groundrep.)').
'symbol'('ENVMSG0','ENVMSG0','src_span'(39,1,39,8,1090,7),'Ident (Groundrep.)').
'symbol'('A3','A','src_span'(40,18,40,19,1118,1),'Ident (Prolog Variable)').
'symbol'('B2','B','src_span'(40,30,40,31,1130,1),'Ident (Prolog Variable)').
'symbol'('ENVMSG','ENVMSG','src_span'(41,1,41,7,1142,6),'Ident (Groundrep.)').
'symbol'('ENVMSG0_BODY','ENVMSG0_BODY','src_span'(43,1,43,13,1160,12),'Ident (Groundrep.)').
'symbol'('B3','B','src_span'(44,16,44,17,1191,1),'Ident (Prolog Variable)').
'symbol'('ENVMSG_BODY','ENVMSG_BODY','src_span'(45,1,45,12,1203,11),'Ident (Groundrep.)').
'symbol'('SenderType','SenderType','src_span'(47,1,47,11,1231,10),'Funktion or Process').
'symbol'('Msg1','Msg1','src_span'(21,3,21,7,641,4),'Constructor of Datatype').
'symbol'('Sq','Sq','src_span'(3,28,3,30,107,2),'Constructor of Datatype').
'symbol'('A4','A','src_span'(47,24,47,25,1254,1),'Ident (Prolog Variable)').
'symbol'('Msg2','Msg2','src_span'(21,10,21,14,648,4),'Constructor of Datatype').
'symbol'('Encrypt','Encrypt','src_span'(4,3,4,10,131,7),'Constructor of Datatype').
'symbol'('Msg3','Msg3','src_span'(21,17,21,21,655,4),'Constructor of Datatype').
'symbol'('Encrypt','Encrypt','src_span'(4,3,4,10,131,7),'Constructor of Datatype').
'symbol'('Msg4','Msg4','src_span'(21,24,21,28,662,4),'Constructor of Datatype').
'symbol'('ReceiverType','ReceiverType','src_span'(52,1,52,13,1395,12),'Funktion or Process').
'symbol'('Msg1','Msg1','src_span'(21,3,21,7,641,4),'Constructor of Datatype').
'symbol'('Sq','Sq','src_span'(3,28,3,30,107,2),'Constructor of Datatype').
'symbol'('Msg2','Msg2','src_span'(21,10,21,14,648,4),'Constructor of Datatype').
'symbol'('Encrypt','Encrypt','src_span'(4,3,4,10,131,7),'Constructor of Datatype').
'symbol'('Msg3','Msg3','src_span'(21,17,21,21,655,4),'Constructor of Datatype').
'symbol'('Encrypt','Encrypt','src_span'(4,3,4,10,131,7),'Constructor of Datatype').
'symbol'('Msg4','Msg4','src_span'(21,24,21,28,662,4),'Constructor of Datatype').
'symbol'('ALL_PRINCIPALS','ALL_PRINCIPALS','src_span'(57,1,57,15,1565,14),'Ident (Groundrep.)').
'symbol'('comm','comm','src_span'(59,9,59,13,1597,4),'Channel').
'symbol'('fake','fake','src_span'(59,15,59,19,1603,4),'Channel').
'symbol'('intercept','intercept','src_span'(59,21,59,30,1609,9),'Channel').
'symbol'('env','env','src_span'(60,9,60,12,1668,3),'Channel').
'symbol'('ROLE','ROLE','src_span'(62,10,62,14,1711,4),'Datatype').
'symbol'('INITIATOR_role','INITIATOR_role','src_span'(62,17,62,31,1718,14),'Constructor of Datatype').
'symbol'('RESPONDER_role','RESPONDER_role','src_span'(62,34,62,48,1735,14),'Constructor of Datatype').
'symbol'('ALL_SECRETS','ALL_SECRETS','src_span'(64,1,64,12,1751,11),'Ident (Groundrep.)').
'symbol'('Signal','Signal','src_span'(66,10,66,16,1786,6),'Datatype').
'symbol'('Claim_Secret','Claim_Secret','src_span'(67,3,67,15,1798,12),'Constructor of Datatype').
'symbol'('Set','Set','src_span'(67,43,67,46,1838,3),'BuiltIn primitive').
'symbol'('Running1','Running1','src_span'(68,3,68,11,1862,8),'Constructor of Datatype').
'symbol'('Commit1','Commit1','src_span'(69,3,69,10,1910,7),'Constructor of Datatype').
'symbol'('signal','signal','src_span'(71,9,71,15,1962,6),'Channel').
'symbol'('INITIATOR_0','INITIATOR_0','src_span'(75,1,75,12,2005,11),'Funktion or Process').
'symbol'('A5','A','src_span'(75,13,75,14,2017,1),'Ident (Prolog Variable)').
'symbol'('na4','na','src_span'(75,16,75,18,2020,2),'Ident (Prolog Variable)').
'symbol'('B4','B','src_span'(76,6,76,7,2031,1),'Ident (Prolog Variable)').
'symbol'('kab3','kab','src_span'(79,6,79,9,2117,3),'Ident (Prolog Variable)').
'symbol'('nb2','nb','src_span'(83,6,83,8,2279,2),'Ident (Prolog Variable)').
'symbol'('INITIATOR','INITIATOR','src_span'(88,1,88,10,2374,9),'Funktion or Process').
'symbol'('A6','A','src_span'(88,11,88,12,2384,1),'Ident (Prolog Variable)').
'symbol'('na5','na','src_span'(88,14,88,16,2387,2),'Ident (Prolog Variable)').
'symbol'('B8','B','src_span'(91,9,91,10,2490,1),'Ident (Prolog Variable)').
'symbol'('m_7','m_','src_span'(91,21,91,23,2502,2),'Ident (Prolog Variable)').
'symbol'('B7','B','src_span'(93,9,93,10,2596,1),'Ident (Prolog Variable)').
'symbol'('m_6','m_','src_span'(93,21,93,23,2608,2),'Ident (Prolog Variable)').
'symbol'('B6','B','src_span'(95,9,95,10,2697,1),'Ident (Prolog Variable)').
'symbol'('m_5','m_','src_span'(95,21,95,23,2709,2),'Ident (Prolog Variable)').
'symbol'('B5','B','src_span'(97,9,97,10,2798,1),'Ident (Prolog Variable)').
'symbol'('m_4','m_','src_span'(97,21,97,23,2810,2),'Ident (Prolog Variable)').
'symbol'('RESPONDER_0','RESPONDER_0','src_span'(99,1,99,12,2829,11),'Funktion or Process').
'symbol'('B9','B','src_span'(99,13,99,14,2841,1),'Ident (Prolog Variable)').
'symbol'('kab4','kab','src_span'(99,16,99,19,2844,3),'Ident (Prolog Variable)').
'symbol'('nb3','nb','src_span'(99,21,99,23,2849,2),'Ident (Prolog Variable)').
'symbol'('A7','A','src_span'(100,6,100,7,2860,1),'Ident (Prolog Variable)').
'symbol'('na6','na','src_span'(100,21,100,23,2875,2),'Ident (Prolog Variable)').
'symbol'('RESPONDER','RESPONDER','src_span'(110,1,110,10,3163,9),'Funktion or Process').
'symbol'('B178','B','src_span'(110,11,110,12,3173,1),'Ident (Prolog Variable)').
'symbol'('kab5','kab','src_span'(110,14,110,17,3176,3),'Ident (Prolog Variable)').
'symbol'('nb4','nb','src_span'(110,19,110,21,3181,2),'Ident (Prolog Variable)').
'symbol'('A187','A','src_span'(113,9,113,10,3289,1),'Ident (Prolog Variable)').
'symbol'('m_188','m_','src_span'(113,21,113,23,3301,2),'Ident (Prolog Variable)').
'symbol'('A185','A','src_span'(115,9,115,10,3395,1),'Ident (Prolog Variable)').
'symbol'('m_186','m_','src_span'(115,21,115,23,3407,2),'Ident (Prolog Variable)').
'symbol'('A9','A','src_span'(117,9,117,10,3496,1),'Ident (Prolog Variable)').
'symbol'('m_9','m_','src_span'(117,21,117,23,3508,2),'Ident (Prolog Variable)').
'symbol'('A8','A','src_span'(119,9,119,10,3597,1),'Ident (Prolog Variable)').
'symbol'('m_8','m_','src_span'(119,21,119,23,3609,2),'Ident (Prolog Variable)').
'symbol'('Agent','Agent','src_span'(123,1,123,6,3655,5),'Ident (Groundrep.)').
'symbol'('Nonce','Nonce','src_span'(124,1,124,6,3685,5),'Ident (Groundrep.)').
'symbol'('SessionKey','SessionKey','src_span'(125,1,125,11,3706,10),'Ident (Groundrep.)').
'symbol'('SharedKey','SharedKey','src_span'(126,1,126,10,3725,9),'Ident (Groundrep.)').
'symbol'('arg_1_','arg_1_','src_span'(126,39,126,45,3763,6),'Ident (Prolog Variable)').
'symbol'('arg_2_','arg_2_','src_span'(126,56,126,62,3780,6),'Ident (Prolog Variable)').
'symbol'('inverse','inverse','src_span'(128,1,128,8,3798,7),'Funktion or Process').
'symbol'('Kab','Kab','src_span'(2,42,2,45,63,3),'Constructor of Datatype').
'symbol'('shared_','shared_','src_span'(3,3,3,10,82,7),'Constructor of Datatype').
'symbol'('arg_','arg_','src_span'(129,17,129,21,3833,4),'Ident (Prolog Variable)').
'symbol'('shared','shared','src_span'(131,1,131,7,3855,6),'Funktion or Process').
'symbol'('arg_1_2','arg_1_','src_span'(131,8,131,14,3862,6),'Ident (Prolog Variable)').
'symbol'('arg_2_2','arg_2_','src_span'(131,16,131,22,3870,6),'Ident (Prolog Variable)').
'symbol'('Fact_0','Fact_0','src_span'(138,1,138,7,4078,6),'Ident (Groundrep.)').
'symbol'('A194','A','src_span'(146,8,146,9,4214,1),'Ident (Prolog Variable)').
'symbol'('B195','B','src_span'(146,20,146,21,4226,1),'Ident (Prolog Variable)').
'symbol'('kab6','kab','src_span'(146,32,146,35,4238,3),'Ident (Prolog Variable)').
'symbol'('na7','na','src_span'(146,51,146,53,4257,2),'Ident (Prolog Variable)').
'symbol'('kab7','kab','src_span'(148,8,148,11,4305,3),'Ident (Prolog Variable)').
'symbol'('na8','na','src_span'(148,27,148,29,4324,2),'Ident (Prolog Variable)').
'symbol'('unSq_','unSq_','src_span'(151,1,151,6,4343,5),'Funktion or Process').
'symbol'('Sq','Sq','src_span'(3,28,3,30,107,2),'Constructor of Datatype').
'symbol'('ms_2','ms_','src_span'(151,11,151,14,4353,3),'Ident (Prolog Variable)').
'symbol'('set','set','src_span'(151,18,151,21,4360,3),'BuiltIn primitive').
'symbol'('m_201','m_','src_span'(152,8,152,10,4376,2),'Ident (Prolog Variable)').
'symbol'('IK0','IK0','src_span'(154,1,154,4,4388,3),'Ident (Groundrep.)').
'symbol'('unknown_','unknown_','src_span'(157,1,157,9,4530,8),'Funktion or Process').
'symbol'('S2','S','src_span'(157,10,157,11,4539,1),'Ident (Prolog Variable)').
'symbol'('diff','diff','src_span'(157,15,157,19,4544,4),'BuiltIn primitive').
'symbol'('Deductions_0','Deductions_0','src_span'(159,1,159,13,4557,12),'Ident (Groundrep.)').
'symbol'('EncryptionDeductions','EncryptionDeductions','src_span'(164,1,164,21,4729,20),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(165,32,165,37,4783,5),'BuiltIn primitive').
'symbol'('set','set','src_span'(165,44,165,47,4795,3),'BuiltIn primitive').
'symbol'('Encrypt','Encrypt','src_span'(4,3,4,10,131,7),'Constructor of Datatype').
'symbol'('k_4','k_','src_span'(166,16,166,18,4825,2),'Ident (Prolog Variable)').
'symbol'('fs_','fs_','src_span'(166,19,166,22,4828,3),'Ident (Prolog Variable)').
'symbol'('DecryptionDeductions','DecryptionDeductions','src_span'(168,1,168,21,4845,20),'Ident (Groundrep.)').
'symbol'('Encrypt','Encrypt','src_span'(4,3,4,10,131,7),'Constructor of Datatype').
'symbol'('k_5','k_','src_span'(170,16,170,18,4936,2),'Ident (Prolog Variable)').
'symbol'('fs_2','fs_','src_span'(170,19,170,22,4939,3),'Ident (Prolog Variable)').
'symbol'('f_','f_','src_span'(170,35,170,37,4955,2),'Ident (Prolog Variable)').
'symbol'('set','set','src_span'(170,50,170,53,4970,3),'BuiltIn primitive').
'symbol'('VernEncDeductions','VernEncDeductions','src_span'(172,1,172,18,4982,17),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(173,29,173,34,5030,5),'BuiltIn primitive').
'symbol'('Xor','Xor','src_span'(4,40,4,43,168,3),'Constructor of Datatype').
'symbol'('m1_','m1_','src_span'(174,12,174,15,5076,3),'Ident (Prolog Variable)').
'symbol'('m2_','m2_','src_span'(174,16,174,19,5080,3),'Ident (Prolog Variable)').
'symbol'('VernDecDeductions','VernDecDeductions','src_span'(176,1,176,18,5097,17),'Ident (Groundrep.)').
'symbol'('union','union','src_span'(177,3,177,8,5119,5),'BuiltIn primitive').
'symbol'('Xor','Xor','src_span'(4,40,4,43,168,3),'Constructor of Datatype').
'symbol'('m1_2','m1_','src_span'(179,13,179,16,5199,3),'Ident (Prolog Variable)').
'symbol'('m2_2','m2_','src_span'(179,17,179,20,5203,3),'Ident (Prolog Variable)').
'symbol'('m11_','m11_','src_span'(179,33,179,37,5219,4),'Ident (Prolog Variable)').
'symbol'('Xor','Xor','src_span'(4,40,4,43,168,3),'Constructor of Datatype').
'symbol'('m1_3','m1_','src_span'(181,13,181,16,5313,3),'Ident (Prolog Variable)').
'symbol'('m2_3','m2_','src_span'(181,17,181,20,5317,3),'Ident (Prolog Variable)').
'symbol'('m21_','m21_','src_span'(181,33,181,37,5333,4),'Ident (Prolog Variable)').
'symbol'('VernEquivs','VernEquivs','src_span'(183,1,183,11,5355,10),'Ident (Groundrep.)').
'symbol'('Xor','Xor','src_span'(4,40,4,43,168,3),'Constructor of Datatype').
'symbol'('m1_4','m1_','src_span'(185,12,185,15,5418,3),'Ident (Prolog Variable)').
'symbol'('m2_4','m2_','src_span'(185,16,185,19,5422,3),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(185,32,185,38,5438,6),'BuiltIn primitive').
'symbol'('UserDeductions','UserDeductions','src_span'(187,1,187,15,5472,14),'Ident (Groundrep.)').
'symbol'('FnAppDeductions','FnAppDeductions','src_span'(189,1,189,16,5493,15),'Ident (Groundrep.)').
'symbol'('components_','components_','src_span'(191,1,191,12,5515,11),'Funktion or Process').
'symbol'('Sq','Sq','src_span'(3,28,3,30,107,2),'Constructor of Datatype').
'symbol'('ms_3','ms_','src_span'(191,20,191,23,5534,3),'Ident (Prolog Variable)').
'symbol'('set','set','src_span'(191,28,191,31,5542,3),'BuiltIn primitive').
'symbol'('m_219','m_','src_span'(192,17,192,19,5567,2),'Ident (Prolog Variable)').
'symbol'('subset','subset','src_span'(196,1,196,7,5618,6),'Funktion or Process').
'symbol'('A_','A_','src_span'(196,8,196,10,5625,2),'Ident (Prolog Variable)').
'symbol'('B_','B_','src_span'(196,11,196,13,5628,2),'Ident (Prolog Variable)').
'symbol'('inter','inter','src_span'(196,17,196,22,5634,5),'BuiltIn primitive').
'symbol'('Seeable_','Seeable_','src_span'(198,1,198,9,5654,8),'Ident (Groundrep.)').
'symbol'('m_222','m_','src_span'(198,47,198,49,5700,2),'Ident (Prolog Variable)').
'symbol'('Close_','Close_','src_span'(200,1,200,7,5718,6),'Funktion or Process').
'symbol'('IK_','IK_','src_span'(200,8,200,11,5725,3),'Ident (Prolog Variable)').
'symbol'('ded_','ded_','src_span'(200,13,200,17,5730,4),'Ident (Prolog Variable)').
'symbol'('fact_','fact_','src_span'(200,19,200,24,5736,5),'Ident (Prolog Variable)').
'symbol'('IK1_','IK1_','src_span'(201,7,201,11,5751,4),'Ident (Groundrep.)').
'symbol'('f_2','f_','src_span'(202,27,202,29,5785,2),'Ident (Prolog Variable)').
'symbol'('fs_3','fs_','src_span'(202,30,202,33,5788,3),'Ident (Prolog Variable)').
'symbol'('ded1_','ded1_','src_span'(203,7,203,12,5826,5),'Ident (Groundrep.)').
'symbol'('f_3','f_','src_span'(204,22,204,24,5856,2),'Ident (Prolog Variable)').
'symbol'('fs_4','fs_','src_span'(204,25,204,28,5859,3),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(204,44,204,50,5878,6),'BuiltIn primitive').
'symbol'('fact1_','fact1_','src_span'(206,7,206,13,5944,6),'Ident (Groundrep.)').
'symbol'('f_4','f_','src_span'(206,35,206,37,5972,2),'Ident (Prolog Variable)').
'symbol'('fs_5','fs_','src_span'(206,38,206,41,5975,3),'Ident (Prolog Variable)').
'symbol'('diff','diff','src_span'(209,19,209,23,6077,4),'BuiltIn primitive').
'symbol'('f_5','f_','src_span'(209,37,209,39,6095,2),'Ident (Prolog Variable)').
'symbol'('fs_6','fs_','src_span'(209,40,209,43,6098,3),'Ident (Prolog Variable)').
'symbol'('Deductions_1','Deductions_1','src_span'(212,1,212,13,6156,12),'Ident (Groundrep.)').
'symbol'('f_6','f_','src_span'(212,29,212,31,6184,2),'Ident (Prolog Variable)').
'symbol'('fs_7','fs_','src_span'(212,32,212,35,6187,3),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(213,37,213,43,6246,6),'BuiltIn primitive').
'symbol'('triple_','triple_','src_span'(215,1,215,8,6264,7),'Ident (Groundrep.)').
'symbol'('first_','first_','src_span'(217,1,217,7,6309,6),'Funktion or Process').
'symbol'('a_','a_','src_span'(217,9,217,11,6317,2),'Ident (Prolog Variable)').
'symbol'('second_','second_','src_span'(218,1,218,8,6331,7),'Funktion or Process').
'symbol'('b_','b_','src_span'(218,12,218,14,6342,2),'Ident (Prolog Variable)').
'symbol'('third_','third_','src_span'(219,1,219,7,6354,6),'Funktion or Process').
'symbol'('c_','c_','src_span'(219,13,219,15,6366,2),'Ident (Prolog Variable)').
'symbol'('IK1','IK1','src_span'(221,1,221,4,6377,3),'Ident (Groundrep.)').
'symbol'('Deductions','Deductions','src_span'(222,1,222,11,6399,10),'Ident (Groundrep.)').
'symbol'('Fact','Fact','src_span'(223,1,223,5,6429,4),'Ident (Groundrep.)').
'symbol'('card','card','src_span'(224,7,224,11,6458,4),'BuiltIn primitive').
'symbol'('diff','diff','src_span'(227,7,227,11,6514,4),'BuiltIn primitive').
'symbol'('leak','leak','src_span'(232,9,232,13,6578,4),'Channel').
'symbol'('hear','hear','src_span'(233,9,233,13,6618,4),'Channel').
'symbol'('say','say','src_span'(233,15,233,18,6624,3),'Channel').
'symbol'('infer','infer','src_span'(234,9,234,14,6647,5),'Channel').
'symbol'('IGNORANT','IGNORANT','src_span'(236,1,236,9,6667,8),'Funktion or Process').
'symbol'('f_7','f_','src_span'(236,10,236,12,6676,2),'Ident (Prolog Variable)').
'symbol'('ms_4','ms_','src_span'(236,13,236,16,6679,3),'Ident (Prolog Variable)').
'symbol'('fss_','fss_','src_span'(236,17,236,21,6683,4),'Ident (Prolog Variable)').
'symbol'('ds_','ds_','src_span'(236,22,236,25,6688,3),'Ident (Prolog Variable)').
'symbol'('m_246','m_','src_span'(237,9,237,11,6703,2),'Ident (Prolog Variable)').
'symbol'('fs_8','fs_','src_span'(239,8,239,11,6744,3),'Ident (Prolog Variable)').
'symbol'('KNOWS','KNOWS','src_span'(241,1,241,6,6795,5),'Funktion or Process').
'symbol'('f_8','f_','src_span'(241,7,241,9,6801,2),'Ident (Prolog Variable)').
'symbol'('ms_5','ms_','src_span'(241,10,241,13,6804,3),'Ident (Prolog Variable)').
'symbol'('ds_2','ds_','src_span'(241,14,241,17,6808,3),'Ident (Prolog Variable)').
'symbol'('m_251','m_','src_span'(242,8,242,10,6822,2),'Ident (Prolog Variable)').
'symbol'('m_252','m_','src_span'(244,7,244,9,6861,2),'Ident (Prolog Variable)').
'symbol'('f1_','f1_','src_span'(246,10,246,13,6904,3),'Ident (Prolog Variable)').
'symbol'('fs_9','fs_','src_span'(246,14,246,17,6908,3),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(248,3,248,9,6947,6),'BuiltIn primitive').
'symbol'('f_ms_fss_ds_s','f_ms_fss_ds_s','src_span'(250,1,250,14,7002,13),'Ident (Groundrep.)').
'symbol'('m_256','m_','src_span'(251,15,251,17,7033,2),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(251,31,251,37,7049,6),'BuiltIn primitive').
'symbol'('f1_2','f1_','src_span'(252,17,252,20,7094,3),'Ident (Prolog Variable)').
'symbol'('fs_258','fs_','src_span'(252,21,252,24,7098,3),'Ident (Prolog Variable)').
'symbol'('f1_3','f1_','src_span'(253,23,253,26,7152,3),'Ident (Prolog Variable)').
'symbol'('fs_260','fs_','src_span'(253,27,253,30,7156,3),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(253,47,253,53,7176,6),'BuiltIn primitive').
'symbol'('f_9','f_','src_span'(254,11,254,13,7206,2),'Ident (Prolog Variable)').
'symbol'('AlphaL','AlphaL','src_span'(256,1,256,7,7229,6),'Funktion or Process').
'symbol'('f_261','f_','src_span'(256,8,256,10,7236,2),'Ident (Prolog Variable)').
'symbol'('ms_6','ms_','src_span'(256,11,256,14,7239,3),'Ident (Prolog Variable)').
'symbol'('fss_2','fss_','src_span'(256,15,256,19,7243,4),'Ident (Prolog Variable)').
'symbol'('ds_3','ds_','src_span'(256,20,256,23,7248,3),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(257,14,257,20,7268,6),'BuiltIn primitive').
'symbol'('m_265','m_','src_span'(258,29,258,31,7344,2),'Ident (Prolog Variable)').
'symbol'('fs_266','fs_','src_span'(259,28,259,31,7383,3),'Ident (Prolog Variable)').
'symbol'('f1_4','f1_','src_span'(260,30,260,33,7426,3),'Ident (Prolog Variable)').
'symbol'('fs_268','fs_','src_span'(260,34,260,37,7430,3),'Ident (Prolog Variable)').
'symbol'('chase','chase','src_span'(262,13,262,18,7458,5),'Transparent function').
'symbol'('INTRUDER_0','INTRUDER_0','src_span'(264,1,264,11,7465,10),'Ident (Groundrep.)').
'symbol'('f_269','f_','src_span'(265,8,265,10,7486,2),'Ident (Prolog Variable)').
'symbol'('ms_7','ms_','src_span'(265,11,265,14,7489,3),'Ident (Prolog Variable)').
'symbol'('fss_3','fss_','src_span'(265,15,265,19,7493,4),'Ident (Prolog Variable)').
'symbol'('ds_4','ds_','src_span'(265,20,265,23,7498,3),'Ident (Prolog Variable)').
'symbol'('INTRUDER_1','INTRUDER_1','src_span'(269,1,269,11,7599,10),'Ident (Groundrep.)').
'symbol'('m_273','m_','src_span'(274,9,274,11,7743,2),'Ident (Prolog Variable)').
'symbol'('A_2','A_','src_span'(274,25,274,27,7759,2),'Ident (Prolog Variable)').
'symbol'('B_2','B_','src_span'(274,47,274,49,7781,2),'Ident (Prolog Variable)').
'symbol'('SAY_KNOWN','SAY_KNOWN','src_span'(276,1,276,10,7807,9),'Ident (Groundrep.)').
'symbol'('f_276','f_','src_span'(277,7,277,9,7825,2),'Ident (Prolog Variable)').
'symbol'('inter','inter','src_span'(277,12,277,17,7830,5),'BuiltIn primitive').
'symbol'('m_278','m_','src_span'(279,7,279,9,7889,2),'Ident (Prolog Variable)').
'symbol'('m_277','m_','src_span'(279,18,279,20,7900,2),'Ident (Prolog Variable)').
'symbol'('ST_','ST_','src_span'(280,10,280,13,7956,3),'Ident (Groundrep.)').
'symbol'('RT_','RT_','src_span'(281,10,281,13,7986,3),'Ident (Groundrep.)').
'symbol'('A_3','A_','src_span'(283,16,283,18,8036,2),'Ident (Prolog Variable)').
'symbol'('B_3','B_','src_span'(283,39,283,41,8059,2),'Ident (Prolog Variable)').
'symbol'('A_4','A_','src_span'(284,21,284,23,8102,2),'Ident (Prolog Variable)').
'symbol'('B_4','B_','src_span'(284,44,284,46,8125,2),'Ident (Prolog Variable)').
'symbol'('A_5','A_','src_span'(285,16,285,18,8163,2),'Ident (Prolog Variable)').
'symbol'('B_5','B_','src_span'(285,23,285,25,8170,2),'Ident (Prolog Variable)').
'symbol'('INTRUDER','INTRUDER','src_span'(287,1,287,9,8196,8),'Ident (Groundrep.)').
'symbol'('Alpha_INITIATOR_Alice','Alpha_INITIATOR_Alice','src_span'(292,1,292,22,8313,21),'Ident (Groundrep.)').
'symbol'('A_6','A_','src_span'(294,26,294,28,8373,2),'Ident (Prolog Variable)').
'symbol'('m_288','m_','src_span'(294,48,294,50,8395,2),'Ident (Prolog Variable)').
'symbol'('A_7','A_','src_span'(295,26,295,28,8439,2),'Ident (Prolog Variable)').
'symbol'('m_290','m_','src_span'(295,48,295,50,8461,2),'Ident (Prolog Variable)').
'symbol'('A_8','A_','src_span'(296,26,296,28,8505,2),'Ident (Prolog Variable)').
'symbol'('m_292','m_','src_span'(296,48,296,50,8527,2),'Ident (Prolog Variable)').
'symbol'('A_9','A_','src_span'(297,26,297,28,8571,2),'Ident (Prolog Variable)').
'symbol'('m_294','m_','src_span'(297,48,297,50,8593,2),'Ident (Prolog Variable)').
'symbol'('INITIATOR_Alice','INITIATOR_Alice','src_span'(300,1,300,16,8617,15),'Ident (Groundrep.)').
'symbol'('Alpha_Alice','Alpha_Alice','src_span'(302,1,302,12,8657,11),'Ident (Groundrep.)').
'symbol'('A_295','A_','src_span'(302,48,302,50,8704,2),'Ident (Prolog Variable)').
'symbol'('Alice_STOP_SET','Alice_STOP_SET','src_span'(304,1,304,15,8728,14),'Ident (Groundrep.)').
'symbol'('A_296','A_','src_span'(306,26,306,28,8781,2),'Ident (Prolog Variable)').
'symbol'('m_297','m_','src_span'(306,48,306,50,8803,2),'Ident (Prolog Variable)').
'symbol'('A_298','A_','src_span'(307,26,307,28,8847,2),'Ident (Prolog Variable)').
'symbol'('m_299','m_','src_span'(307,48,307,50,8869,2),'Ident (Prolog Variable)').
'symbol'('A_300','A_','src_span'(308,26,308,28,8913,2),'Ident (Prolog Variable)').
'symbol'('m_301','m_','src_span'(308,48,308,50,8935,2),'Ident (Prolog Variable)').
'symbol'('A_302','A_','src_span'(309,26,309,28,8979,2),'Ident (Prolog Variable)').
'symbol'('m_303','m_','src_span'(309,48,309,50,9001,2),'Ident (Prolog Variable)').
'symbol'('AGENT_Alice','AGENT_Alice','src_span'(312,1,312,12,9025,11),'Ident (Groundrep.)').
'symbol'('Alpha_RESPONDER_Bob','Alpha_RESPONDER_Bob','src_span'(317,1,317,20,9111,19),'Ident (Groundrep.)').
'symbol'('A_304','A_','src_span'(319,24,319,26,9167,2),'Ident (Prolog Variable)').
'symbol'('m_305','m_','src_span'(319,46,319,48,9189,2),'Ident (Prolog Variable)').
'symbol'('A_306','A_','src_span'(320,24,320,26,9231,2),'Ident (Prolog Variable)').
'symbol'('m_307','m_','src_span'(320,46,320,48,9253,2),'Ident (Prolog Variable)').
'symbol'('A_308','A_','src_span'(321,24,321,26,9295,2),'Ident (Prolog Variable)').
'symbol'('m_309','m_','src_span'(321,46,321,48,9317,2),'Ident (Prolog Variable)').
'symbol'('A_310','A_','src_span'(322,24,322,26,9359,2),'Ident (Prolog Variable)').
'symbol'('m_311','m_','src_span'(322,46,322,48,9381,2),'Ident (Prolog Variable)').
'symbol'('RESPONDER_Bob','RESPONDER_Bob','src_span'(325,1,325,14,9405,13),'Ident (Groundrep.)').
'symbol'('Alpha_Bob','Alpha_Bob','src_span'(327,1,327,10,9446,9),'Ident (Groundrep.)').
'symbol'('A_312','A_','src_span'(327,42,327,44,9487,2),'Ident (Prolog Variable)').
'symbol'('Bob_STOP_SET','Bob_STOP_SET','src_span'(329,1,329,13,9511,12),'Ident (Groundrep.)').
'symbol'('A_313','A_','src_span'(331,24,331,26,9560,2),'Ident (Prolog Variable)').
'symbol'('m_314','m_','src_span'(331,46,331,48,9582,2),'Ident (Prolog Variable)').
'symbol'('A_315','A_','src_span'(332,24,332,26,9624,2),'Ident (Prolog Variable)').
'symbol'('m_316','m_','src_span'(332,46,332,48,9646,2),'Ident (Prolog Variable)').
'symbol'('A_317','A_','src_span'(333,24,333,26,9688,2),'Ident (Prolog Variable)').
'symbol'('m_318','m_','src_span'(333,46,333,48,9710,2),'Ident (Prolog Variable)').
'symbol'('A_319','A_','src_span'(334,24,334,26,9752,2),'Ident (Prolog Variable)').
'symbol'('m_320','m_','src_span'(334,46,334,48,9774,2),'Ident (Prolog Variable)').
'symbol'('AGENT_Bob','AGENT_Bob','src_span'(337,1,337,10,9798,9),'Ident (Groundrep.)').
'symbol'('SYSTEM_0','SYSTEM_0','src_span'(342,1,342,9,9869,8),'Ident (Groundrep.)').
'symbol'('inter','inter','src_span'(344,8,344,13,9902,5),'BuiltIn primitive').
'symbol'('SYSTEM','SYSTEM','src_span'(347,1,347,7,9949,6),'Ident (Groundrep.)').
'symbol'('Sigma','Sigma','src_span'(351,1,351,6,10036,5),'Ident (Groundrep.)').
'symbol'('SECRET_SPEC_0','SECRET_SPEC_0','src_span'(355,1,355,14,10116,13),'Funktion or Process').
'symbol'('s_','s_','src_span'(355,15,355,17,10130,2),'Ident (Prolog Variable)').
'symbol'('A_322','A_','src_span'(356,23,356,25,10159,2),'Ident (Prolog Variable)').
'symbol'('Bs_','Bs_','src_span'(356,29,356,32,10165,3),'Ident (Prolog Variable)').
'symbol'('member','member','src_span'(357,9,357,15,10180,6),'BuiltIn primitive').
'symbol'('SECRET_SPEC_1','SECRET_SPEC_1','src_span'(361,1,361,14,10285,13),'Funktion or Process').
'symbol'('s_2','s_','src_span'(361,15,361,17,10299,2),'Ident (Prolog Variable)').
'symbol'('A_325','A_','src_span'(361,41,361,43,10325,2),'Ident (Prolog Variable)').
'symbol'('Bs_2','Bs_','src_span'(361,47,361,50,10331,3),'Ident (Prolog Variable)').
'symbol'('AlphaS','AlphaS','src_span'(363,1,363,7,10357,6),'Funktion or Process').
'symbol'('s_3','s_','src_span'(363,8,363,10,10364,2),'Ident (Prolog Variable)').
'symbol'('A_328','A_','src_span'(364,39,364,41,10409,2),'Ident (Prolog Variable)').
'symbol'('Alpha_SECRETS','Alpha_SECRETS','src_span'(366,1,366,14,10445,13),'Ident (Groundrep.)').
'symbol'('SECRET_SPEC','SECRET_SPEC','src_span'(368,1,368,12,10492,11),'Ident (Groundrep.)').
'symbol'('s_4','s_','src_span'(368,19,368,21,10510,2),'Ident (Prolog Variable)').
'symbol'('AuthenticateRESPONDERToINITIATORAliveness','AuthenticateRESPONDERToINITIATORAliveness','src_span'(374,1,374,42,10664,41),'Funktion or Process').
'symbol'('B330','B','src_span'(374,43,374,44,10706,1),'Ident (Prolog Variable)').
'symbol'('B_role_','B_role_','src_span'(375,19,375,26,10729,7),'Ident (Prolog Variable)').
'symbol'('C_','C_','src_span'(375,29,375,31,10739,2),'Ident (Prolog Variable)').
'symbol'('A333','A','src_span'(376,46,376,47,10790,1),'Ident (Prolog Variable)').
'symbol'('AlphaAuthenticateRESPONDERToINITIATORAliveness','AlphaAuthenticateRESPONDERToINITIATORAliveness','src_span'(378,1,378,47,10804,46),'Funktion or Process').
'symbol'('B334','B','src_span'(378,48,378,49,10851,1),'Ident (Prolog Variable)').
'symbol'('A335','A','src_span'(381,8,381,9,10936,1),'Ident (Prolog Variable)').
'symbol'('B_role_2','B_role_','src_span'(381,20,381,27,10948,7),'Ident (Prolog Variable)').
'symbol'('AuthenticateRESPONDERAliceToINITIATORAliveness','AuthenticateRESPONDERAliceToINITIATORAliveness','src_span'(383,1,383,47,10967,46),'Ident (Groundrep.)').
'symbol'('AuthenticateRESPONDERBobToINITIATORAliveness','AuthenticateRESPONDERBobToINITIATORAliveness','src_span'(389,1,389,45,11161,44),'Ident (Groundrep.)').