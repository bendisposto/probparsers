MACHINE ManyLocalDeclarations2

VARIABLES xx, yy

INVARIANT xx : INT & yy : POW(INTEGER)
INITIALISATION xx := 1; yy := {1,2,3} \/ {2}

OPERATIONS
out <-- Inc(aa) =
    PRE aa : NAT THEN
        out := {1,2};
        VAR out IN
            out := aa + 1;
            VAR out IN
                out := aa + 1;
                VAR out IN
                    out := aa + 1
                END;
                out := 1
            END;
            out := 2
        END;
        VAR out IN
            out := aa + 1
        END
    END


END