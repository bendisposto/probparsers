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
'dataTypeDef'('Move',['constructor'('Up'),'constructor'('Down')]).
'channel'('left','type'('dotTupleType'(['Move']))).
'channel'('right','type'('dotTupleType'(['Move']))).
'bindval'('FORK','[]'('prefix'('src_span'(9,8,9,15,155,7),[],'dotTuple'(['left','Up']),'prefix'('src_span'(9,17,9,26,164,9),[],'dotTuple'(['left','Down']),'val_of'('FORK','src_span'(9,28,9,32,175,4)),'src_span'(9,27,9,27,173,15)),'src_span'(9,16,9,16,162,24)),'prefix'('src_span'(9,36,9,44,183,8),[],'dotTuple'(['right','Up']),'prefix'('src_span'(9,46,9,56,193,10),[],'dotTuple'(['right','Down']),'val_of'('FORK','src_span'(9,58,9,62,205,4)),'src_span'(9,57,9,57,203,16)),'src_span'(9,45,9,45,191,26)),'src_span_operator'('no_loc_info_available','src_span'(9,33,9,35,180,2))),'src_span'(9,1,9,62,148,61)).
'bindval'('PHIL','prefix'('src_span'(11,8,11,15,218,7),[],'dotTuple'(['left','Up']),'prefix'('src_span'(11,17,11,25,227,8),[],'dotTuple'(['right','Up']),'prefix'('src_span'(11,27,11,36,237,9),[],'dotTuple'(['left','Down']),'prefix'('src_span'(11,38,11,48,248,10),[],'dotTuple'(['right','Down']),'val_of'('PHIL','src_span'(11,50,11,54,260,4)),'src_span'(11,49,11,49,258,16)),'src_span'(11,37,11,37,246,27)),'src_span'(11,26,11,26,235,37)),'src_span'(11,16,11,16,225,46)),'src_span'(11,1,11,54,211,53)).
'agent'('LPHILS'(_n),'let'(['agent'('L'('int'(0)),'lParallel'('linkList'(['link'('right','left')]),'val_of'('FORK','src_span'(16,7,16,11,301,4)),'val_of'('PHIL','src_span'(16,27,16,31,321,4)),'src_span'(16,12,16,26,306,14)),'src_position'(16,27,321,4)),'agent'('L'(_n2),'let'(['bindval'('HALF','agent_call'('src_span'(19,16,19,22,362,6),'LPHILS',['-'(_n2,'int'(1))]),'src_span'(19,9,19,27,355,18))],'lParallel'('linkList'(['link'('right','left')]),'val_of'('HALF','src_span'(20,14,20,18,387,4)),'val_of'('HALF','src_span'(20,34,20,38,407,4)),'src_span'(20,19,20,33,392,14))),'src_span'(18,7,20,38,343,68))],'agent_call'('src_span'(22,10,22,16,487,6),'normal',['procRenaming'([],'agent_call'('src_span'(22,17,22,18,494,1),'L',[_n]),'src_span'(22,22,22,27,499,5))])),'src_span'(14,3,22,28,280,225)).
'cspTransparent'(['normal']).
'agent'('RPHILS'(_n3),'procRenaming'(['rename'('left','right'),'rename'('right','left')],'agent_call'('src_span'(27,3,27,9,541,6),'LPHILS',[_n3]),'src_span'(27,13,27,47,551,34)),'src_span'(27,13,27,47,551,34)).
'agent'('PHILS'(_n4),'sharing'('closure'(['left','right']),'agent_call'('src_span'(30,3,30,9,600,6),'LPHILS',['-'(_n4,'int'(1))]),'agent_call'('src_span'(30,39,30,45,636,6),'RPHILS',['-'(_n4,'int'(1))]),'src_span'(30,15,30,38,612,23)),'src_span'(30,15,30,38,612,23)).
'assertModelCheckExt'('False','agent_call'('src_span'(34,8,34,13,710,5),'PHILS',['int'(1)]),'DeadlockFree','F').
'assertModelCheckExt'('False','agent_call'('src_span'(35,8,35,13,747,5),'PHILS',['int'(10)]),'DeadlockFree','F').
'assertModelCheckExt'('False','agent_call'('src_span'(36,8,36,13,785,5),'PHILS',['int'(100)]),'DeadlockFree','F').
'assertModelCheckExt'('False','agent_call'('src_span'(37,8,37,13,824,5),'PHILS',['int'(1000)]),'DeadlockFree','F').
'comment'('lineComment'('-- Play with massive numbers of dining philosophers'),'src_position'(1,1,0,51)).
'comment'('lineComment'('-- (Powers of 2 only)'),'src_position'(2,1,52,21)).
'comment'('lineComment'('-- JBS, May 1997.'),'src_position'(3,1,74,17)).
'comment'('lineComment'('--    transparent normal  -- transparent only allowed at toplevel'),'src_position'(21,1,412,65)).
'comment'('lineComment'('-- PHILS(n) represents a network of 2^n philosophers'),'src_position'(32,1,649,52)).
'symbol'('Move','Move','src_span'(5,10,5,14,102,4),'Datatype').
'symbol'('Up','Up','src_span'(5,17,5,19,109,2),'Constructor of Datatype').
'symbol'('Down','Down','src_span'(5,22,5,26,114,4),'Constructor of Datatype').
'symbol'('left','left','src_span'(7,9,7,13,128,4),'Channel').
'symbol'('right','right','src_span'(7,15,7,20,134,5),'Channel').
'symbol'('FORK','FORK','src_span'(9,1,9,5,148,4),'Ident (Groundrep.)').
'symbol'('PHIL','PHIL','src_span'(11,1,11,5,211,4),'Ident (Groundrep.)').
'symbol'('LPHILS','LPHILS','src_span'(13,1,13,7,266,6),'Funktion or Process').
'symbol'('n','n','src_span'(13,8,13,9,273,1),'Ident (Prolog Variable)').
'symbol'('L','L','src_span'(15,5,15,6,288,1),'Funktion or Process').
'symbol'('n2','n','src_span'(17,7,17,8,332,1),'Ident (Prolog Variable)').
'symbol'('HALF','HALF','src_span'(19,9,19,13,355,4),'Ident (Groundrep.)').
'symbol'('normal','normal','src_span'(24,13,24,19,519,6),'Transparent function').
'symbol'('RPHILS','RPHILS','src_span'(26,1,26,7,527,6),'Funktion or Process').
'symbol'('n3','n','src_span'(26,8,26,9,534,1),'Ident (Prolog Variable)').
'symbol'('PHILS','PHILS','src_span'(29,1,29,6,587,5),'Funktion or Process').
'symbol'('n4','n','src_span'(29,7,29,8,593,1),'Ident (Prolog Variable)').