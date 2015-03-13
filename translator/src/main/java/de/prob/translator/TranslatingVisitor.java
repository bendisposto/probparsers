package de.prob.translator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.ABooleanFalseExpression;
import de.be4.classicalb.core.parser.node.ABooleanTrueExpression;
import de.be4.classicalb.core.parser.node.ACoupleExpression;
import de.be4.classicalb.core.parser.node.AEmptySequenceExpression;
import de.be4.classicalb.core.parser.node.AEmptySetExpression;
import de.be4.classicalb.core.parser.node.ARecEntry;
import de.be4.classicalb.core.parser.node.ARecExpression;
import de.be4.classicalb.core.parser.node.ASequenceExtensionExpression;
import de.be4.classicalb.core.parser.node.ASetExtensionExpression;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PRecEntry;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TIntegerLiteral;
import de.be4.classicalb.core.parser.node.TStringLiteral;
import de.prob.translator.types.Atom;
import de.prob.translator.types.BObject;
import de.prob.translator.types.Boolean;
import de.prob.translator.types.Record;
import de.prob.translator.types.Sequence;
import de.prob.translator.types.String;
import de.prob.translator.types.Tuple;

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

        java.util.Set<BObject> elements = listToSet(node.getExpressions());
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
	public void caseAEmptySequenceExpression(AEmptySequenceExpression node) {
    	this.setResult(new Sequence());
    }

    @Override
    public void caseABooleanTrueExpression(ABooleanTrueExpression node) {
        this.setResult(new Boolean(true));
    }

    @Override
    public void caseABooleanFalseExpression(ABooleanFalseExpression node) {
        this.setResult(new Boolean(false));
    }

    private java.util.Set<BObject> listToSet(LinkedList<PExpression> elements) {
        java.util.Set<BObject> s = de.prob.translator.types.Set.newStorage();
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
