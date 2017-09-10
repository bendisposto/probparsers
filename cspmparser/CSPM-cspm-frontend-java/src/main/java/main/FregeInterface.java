package frege.main;

import frege.runtime.Lambda;
import frege.prelude.PreludeBase.TMaybe;

/**
 * This class contains helper functions for calling frege functions.
 */
public class FregeInterface {
    
    /**
     * Evaluates the given return object of a (lazy) frege function with frege return type IO a
     * Example:
     *   TModule ast = (TModule)evaluateIOFunction(TranslateToProlog.translateToAst("N=1"));
     *   String term = (String)evaluateIOFunction(
     *       TranslateToProlog.translateDeclToPrologTerm$tick(ast, "N")
     *   );
     * @param res The result of calling the frege function with all parameters applied
     * @return Evaluated result of calling the function call of frege type a.
     */
    public static Object evaluateIOFunction(Lambda res) {
        return frege.runtime.Delayed.forced(
            frege.prelude.PreludeBase.TST.performUnsafe(
                res
            )
        );
    }
    
    /**
     * Evaluates the given return object of a (lazy) frege function with frege return type IO ()
     * @param res The result of calling the frege function with all parameters applied
     */
    public static void evaluateIOUnitFunction(Object res) {
        frege.runtime.Runtime.runMain(
            frege.prelude.PreludeBase.TST.performUnsafe(
                frege.runtime.Delayed.<frege.runtime.Lambda>forced(
                    res
                )
            )
        );
    }
    
    public static <T> TMaybe just(T o) {
        return TMaybe.DJust.mk(o);
    }
    
    public static <T> TMaybe nothing() {
        return TMaybe.DNothing.<T>mk();
    }
}
