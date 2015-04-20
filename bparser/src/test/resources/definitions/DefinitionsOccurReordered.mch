MACHINE DefinitionsOccurInLinearOrder
DEFINITIONS
  DPRED == (1=1);
  DPRED2 == not(DPRED);
  DPRED3 == (DPRED <=> not(DPRED2)); /* THIS IS OK BECAUSE DPRED and DPRED2 are parsed before */
  DPRED4 == not(DPRED5); /* here the parser stumbles it seems to think DPRED5 is an expression */
  DPRED5 == (2=2)
END