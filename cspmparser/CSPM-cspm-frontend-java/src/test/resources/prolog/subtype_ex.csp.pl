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
'nameType'('Val2','type'('dotTupleType'(['setExp'('rangeClosed'('int'(0),'int'(6)))]))).
'bindval'('Val','setExp'('rangeClosed'('int'(0),'int'(6))),'src_span'(2,1,2,13,23,12)).
'dataTypeDef'('Slot',['constructor'('sl1'),'constructor'('sl2'),'constructor'('sl3'),'constructor'('sl4')]).
'dataTypeDef'('Signal',['constructor'('noarg'),'constructorC'('valarg','dotTupleType'(['val_of'('Val','src_span'(6,34,6,37,109,3))])),'constructorC'('slotarg','dotTupleType'(['Slot'])),'constructorC'('botharg','dotTupleType'(['val_of'('Val','src_span'(6,63,6,66,138,3)),'Slot']))]).
'subTypeDef'('LPSignal',['constructor'('noarg'),'constructorC'('slotarg','dotTupleType'(['Slot']))]).
'subTypeDef'('HPSignal',['constructorC'('valarg','dotTupleType'(['val_of'('Val','src_span'(10,27,10,30,215,3))])),'constructorC'('botharg','dotTupleType'(['val_of'('Val','src_span'(10,41,10,44,229,3)),'Slot']))]).
'channel'('gen_high_pri','type'('dotTupleType'(['HPSignal']))).
'channel'('gen_low_pri','type'('dotTupleType'(['LPSignal']))).
'channel'('remove','type'('dotTupleType'(['Signal']))).
'bindval'('Test','prefix'('src_span'(18,8,18,14,336,6),['in'(_sgn)],'remove','val_of'('Test','src_span'(18,22,18,26,350,4)),'src_span'(18,19,18,21,346,12)),'src_span'(18,1,18,26,329,25)).
'bindval'('Test2','prefix'('src_span'(19,9,19,21,363,12),['in'(_v)],'gen_high_pri','val_of'('Test2','src_span'(19,26,19,31,380,5)),'src_span'(19,24,19,25,377,10)),'src_span'(19,1,19,31,355,30)).
'bindval'('CAPACITY','int'(3),'src_span'(21,1,21,13,387,12)).
'agent'('Q'(_s1,_s2),'[]'('[]'('[]'('&'('<'('#'(_s1),'val_of'('CAPACITY','src_span'(23,10,23,18,420,8))),'prefix'('src_span'(23,21,23,33,431,12),['in'(_sig)],'gen_high_pri','agent_call'('src_span'(23,41,23,42,451,1),'Q',['^'(_s1,'listExp'('rangeEnum'([_sig]))),_s2]),'src_span'(23,38,23,40,447,22))),'&'('<'('#'(_s2),'val_of'('CAPACITY','src_span'(25,10,25,18,481,8))),'prefix'('src_span'(25,21,25,32,492,11),['in'(_sig2)],'gen_low_pri','agent_call'('src_span'(25,40,25,41,511,1),'Q',[_s1,'^'(_s2,'listExp'('rangeEnum'([_sig2])))]),'src_span'(25,37,25,39,507,22))),'src_span_operator'('no_loc_info_available','src_span'(24,4,24,6,469,2))),'&'('>'('#'(_s1),'int'(0)),'prefix'('src_span'(27,14,27,20,545,6),['out'('agent_call'('src_span'(27,21,27,25,552,4),'head',[_s1]))],'remove','agent_call'('src_span'(27,33,27,34,564,1),'Q',['agent_call'('src_span'(27,35,27,39,566,4),'tail',[_s1]),_s2]),'src_span'(27,30,27,32,560,27))),'src_span_operator'('no_loc_info_available','src_span'(26,4,26,6,529,2))),'&'('bool_and'('=='('#'(_s1),'int'(0)),'>'('#'(_s2),'int'(0))),'prefix'('src_span'(29,27,29,33,611,6),['out'('agent_call'('src_span'(29,34,29,38,618,4),'head',[_s2]))],'remove','agent_call'('src_span'(29,46,29,47,630,1),'Q',[_s1,'agent_call'('src_span'(29,51,29,55,635,4),'tail',[_s2])]),'src_span'(29,43,29,45,626,27))),'src_span_operator'('no_loc_info_available','src_span'(28,4,28,6,582,2))),'no_loc_info_available').
'bindval'('IN','prefix'('src_span'(31,6,31,23,651,17),[],'dotTuple'(['gen_low_pri','noarg']),'prefix'('src_span'(31,27,31,46,672,19),['in'(_v1)],'dotTuple'(['gen_high_pri','valarg']),'prefix'('src_span'(32,8,32,27,707,19),['in'(_s12)],'dotTuple'(['gen_low_pri','slotarg']),'prefix'('src_span'(32,34,32,54,733,20),['in'(_v2),'in'(_s22)],'dotTuple'(['gen_high_pri','botharg']),'stop'('src_span'(32,64,32,68,763,4)),'src_span'(32,61,32,63,759,11)),'src_span'(32,31,32,33,729,41)),'src_span'(31,50,32,7,694,76)),'src_span'(31,24,31,26,668,116)),'src_span'(31,1,32,68,646,121)).
'bindval'('OUT','prefix'('src_span'(34,7,34,13,775,6),['in'(_s)],'remove','val_of'('OUT','src_span'(34,19,34,22,787,3)),'src_span'(34,16,34,18,783,9)),'src_span'(34,1,34,22,769,21)).
'bindval'('NET','sharing'('closure'(['gen_high_pri','gen_low_pri','remove']),'|||'('val_of'('IN','src_span'(36,8,36,10,799,2)),'val_of'('OUT','src_span'(36,15,36,18,806,3)),'src_span_operator'('no_loc_info_available','src_span'(36,11,36,14,802,3))),'agent_call'('src_span'(36,62,36,63,853,1),'Q',['listExp'('rangeEnum'([])),'listExp'('rangeEnum'([]))]),'src_span'(36,20,36,61,811,41)),'src_span'(36,1,36,70,792,69)).
'bindval'('MAIN','val_of'('NET','src_span'(39,8,39,11,871,3)),'src_span'(39,1,39,11,864,10)).
'symbol'('Val2','Val2','src_span'(1,10,1,14,9,4),'Nametype').
'symbol'('Val','Val','src_span'(2,1,2,4,23,3),'Ident (Groundrep.)').
'symbol'('Slot','Slot','src_span'(4,10,4,14,46,4),'Datatype').
'symbol'('sl1','sl1','src_span'(4,17,4,20,53,3),'Constructor of Datatype').
'symbol'('sl2','sl2','src_span'(4,23,4,26,59,3),'Constructor of Datatype').
'symbol'('sl3','sl3','src_span'(4,29,4,32,65,3),'Constructor of Datatype').
'symbol'('sl4','sl4','src_span'(4,35,4,38,71,3),'Constructor of Datatype').
'symbol'('Signal','Signal','src_span'(6,10,6,16,85,6),'Datatype').
'symbol'('noarg','noarg','src_span'(6,19,6,24,94,5),'Constructor of Datatype').
'symbol'('valarg','valarg','src_span'(6,27,6,33,102,6),'Constructor of Datatype').
'symbol'('slotarg','slotarg','src_span'(6,40,6,47,115,7),'Constructor of Datatype').
'symbol'('botharg','botharg','src_span'(6,55,6,62,130,7),'Constructor of Datatype').
'symbol'('LPSignal','LPSignal','src_span'(8,9,8,17,156,8),'Datatype').
'symbol'('HPSignal','HPSignal','src_span'(10,9,10,17,197,8),'Datatype').
'symbol'('gen_high_pri','gen_high_pri','src_span'(12,9,12,21,247,12),'Channel').
'symbol'('gen_low_pri','gen_low_pri','src_span'(14,9,14,20,280,11),'Channel').
'symbol'('remove','remove','src_span'(16,9,16,15,312,6),'Channel').
'symbol'('Test','Test','src_span'(18,1,18,5,329,4),'Ident (Groundrep.)').
'symbol'('sgn','sgn','src_span'(18,15,18,18,343,3),'Ident (Prolog Variable)').
'symbol'('Test2','Test2','src_span'(19,1,19,6,355,5),'Ident (Groundrep.)').
'symbol'('v','v','src_span'(19,22,19,23,376,1),'Ident (Prolog Variable)').
'symbol'('CAPACITY','CAPACITY','src_span'(21,1,21,9,387,8),'Ident (Groundrep.)').
'symbol'('Q','Q','src_span'(22,1,22,2,400,1),'Funktion or Process').
'symbol'('s1','s1','src_span'(22,3,22,5,402,2),'Ident (Prolog Variable)').
'symbol'('s2','s2','src_span'(22,6,22,8,405,2),'Ident (Prolog Variable)').
'symbol'('sig','sig','src_span'(23,34,23,37,444,3),'Ident (Prolog Variable)').
'symbol'('sig2','sig','src_span'(25,33,25,36,504,3),'Ident (Prolog Variable)').
'symbol'('head','head','src_span'(27,21,27,25,552,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(27,35,27,39,566,4),'BuiltIn primitive').
'symbol'('head','head','src_span'(29,34,29,38,618,4),'BuiltIn primitive').
'symbol'('tail','tail','src_span'(29,51,29,55,635,4),'BuiltIn primitive').
'symbol'('IN','IN','src_span'(31,1,31,3,646,2),'Ident (Groundrep.)').
'symbol'('v1','v1','src_span'(31,47,31,49,692,2),'Ident (Prolog Variable)').
'symbol'('s12','s1','src_span'(32,28,32,30,727,2),'Ident (Prolog Variable)').
'symbol'('v2','v2','src_span'(32,55,32,57,754,2),'Ident (Prolog Variable)').
'symbol'('s22','s2','src_span'(32,58,32,60,757,2),'Ident (Prolog Variable)').
'symbol'('OUT','OUT','src_span'(34,1,34,4,769,3),'Ident (Groundrep.)').
'symbol'('s','s','src_span'(34,14,34,15,782,1),'Ident (Prolog Variable)').
'symbol'('NET','NET','src_span'(36,1,36,4,792,3),'Ident (Groundrep.)').
'symbol'('MAIN','MAIN','src_span'(39,1,39,5,864,4),'Ident (Groundrep.)').