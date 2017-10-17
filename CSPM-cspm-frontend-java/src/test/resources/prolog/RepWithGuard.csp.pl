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
'bindval'('test','repChoice'(['comprehensionGenerator'(_op,'setExp'('rangeEnum'(['int'(1)])))],'&'('true','repChoice'(['comprehensionGenerator'(_i,_op)],'stop'('src_span'(1,41,1,45,40,4)),'src_span'(1,32,1,40,31,8))),'src_span'(1,11,1,21,10,10)),'src_span'(1,1,1,45,0,44)).
'symbol'('test','test','src_span'(1,1,1,5,0,4),'Ident (Groundrep.)').
'symbol'('op','op','src_span'(1,11,1,13,10,2),'Ident (Prolog Variable)').
'symbol'('i','i','src_span'(1,32,1,33,31,1),'Ident (Prolog Variable)').