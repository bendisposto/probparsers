package de.prob.translator;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.*;
import de.prob.translator.types.*;
import de.prob.translator.types.Boolean;
import de.prob.translator.types.String;

import java.util.*;

public class TranslatingVisitor extends DepthFirstAdapter {
    private BObject result;

    public BObject getResult() {
        if(this.result == null) {
            throw new IllegalStateException("Trying to read a missing intermediate result. This might be a missing case in the translator");
        }
        BObject res = this.result;
        this.result = null;
        return res;

    }

    public void setResult(BObject result) {
        if(this.result != null) {
            throw new IllegalStateException("Trying to overwrite an intermediate result before reading it.");
        }
        this.result = result;
    }
    @Override
    public void caseTIntegerLiteral(final TIntegerLiteral node) {
        final java.lang.String text = node.getText();
        this.setResult(de.prob.translator.types.Number.build(text));
    }

    @Override
    public void caseTIdentifierLiteral(TIdentifierLiteral node) {
        this.setResult(new Atom(node.getText()));
    }

    @Override
    public void caseAEmptySetExpression(AEmptySetExpression node) {
        this.setResult(new de.prob.translator.types.Set());
    }

    @Override
    public void caseASetExtensionExpression(final ASetExtensionExpression node) {

        java.util.Set<BObject> elements = transformList(node.getExpressions());
        this.setResult(new de.prob.translator.types.Set(elements));
    }

    @Override
    public void caseTStringLiteral(final TStringLiteral node) {
        this.setResult(new String(node.getText()));
    }

    @Override
    public void caseACoupleExpression(ACoupleExpression node) {
        List<BObject> s = new ArrayList<BObject>();
        for (PExpression e : node.getList()) {
            e.apply(this);
            s.add(this.getResult());
        }
        this.setResult(new Tuple(s));
    }

    @Override
    public void caseARecExpression(ARecExpression node) {
        Map<java.lang.String, BObject> s = Record.newStorage();
        // TODO or make the record immutable after filling it
        for (PRecEntry e : node.getEntries()) {
            e.apply(this);
            RecordEntry entry = (RecordEntry) this.getResult();
            // TODO add check for non-null key
            s.put(entry.getKey().getValue(), entry.getValue());
        }
        this.setResult(new Record(s));
    }


    @Override
    public void caseARecEntry(ARecEntry node) {
        Atom key = null;
        BObject value = null;
        if (node.getIdentifier() != null) {
            node.getIdentifier().apply(this);
            key = (Atom) this.getResult();
        }

        if (node.getValue() != null) {
            node.getValue().apply(this);
            value = this.getResult();
        }
        this.setResult(new RecordEntry(key, value));
    }

    @Override
    public void caseASequenceExtensionExpression(ASequenceExtensionExpression node) {
        List<BObject> s = new ArrayList<BObject>();
        for (PExpression e : node.getExpression()) {
            e.apply(this);
            s.add(this.getResult());
        }
        this.setResult(new Sequence(s));
    }

    @Override
    public void caseABooleanTrueExpression(ABooleanTrueExpression node) {
        this.setResult(new Boolean(true));
    }

    @Override
    public void caseABooleanFalseExpression(ABooleanFalseExpression node) {
        this.setResult(new Boolean(false));
    }

    private java.util.Set<BObject> transformList(LinkedList<PExpression> elements) {
        HashSet<BObject> s = new HashSet<BObject>();
        for (PExpression p : elements) {
            p.apply(this);
            s.add(this.getResult());
        }
        return s;
    }

    private class RecordEntry implements BObject {
        private final BObject value;
        private final Atom key;

        public RecordEntry(Atom key, BObject value) {
            this.key = key;
            this.value = value;
        }

        public Atom getKey() {
            return key;
        }

        public BObject getValue() {
            return value;
        }
    }
}
