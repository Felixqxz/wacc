// Generated from WaccParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link WaccParser}.
 */
public interface WaccParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link WaccParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(WaccParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(WaccParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#macro}.
	 * @param ctx the parse tree
	 */
	void enterMacro(WaccParser.MacroContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#macro}.
	 * @param ctx the parse tree
	 */
	void exitMacro(WaccParser.MacroContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(WaccParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(WaccParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#param_list}.
	 * @param ctx the parse tree
	 */
	void enterParam_list(WaccParser.Param_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#param_list}.
	 * @param ctx the parse tree
	 */
	void exitParam_list(WaccParser.Param_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(WaccParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(WaccParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code readStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterReadStat(WaccParser.ReadStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code readStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitReadStat(WaccParser.ReadStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(WaccParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(WaccParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doWhileStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStat(WaccParser.DoWhileStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doWhileStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStat(WaccParser.DoWhileStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifElseStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfElseStat(WaccParser.IfElseStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifElseStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfElseStat(WaccParser.IfElseStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code switchStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStat(WaccParser.SwitchStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code switchStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStat(WaccParser.SwitchStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintStat(WaccParser.PrintStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintStat(WaccParser.PrintStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterContinueStat(WaccParser.ContinueStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitContinueStat(WaccParser.ContinueStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printlnStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintlnStat(WaccParser.PrintlnStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printlnStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintlnStat(WaccParser.PrintlnStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exitStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterExitStat(WaccParser.ExitStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exitStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitExitStat(WaccParser.ExitStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBreakStat(WaccParser.BreakStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBreakStat(WaccParser.BreakStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code freeStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterFreeStat(WaccParser.FreeStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code freeStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitFreeStat(WaccParser.FreeStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterForStat(WaccParser.ForStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitForStat(WaccParser.ForStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code beginStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBeginStat(WaccParser.BeginStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code beginStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBeginStat(WaccParser.BeginStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code skipStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterSkipStat(WaccParser.SkipStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code skipStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitSkipStat(WaccParser.SkipStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code seqStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterSeqStat(WaccParser.SeqStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code seqStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitSeqStat(WaccParser.SeqStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(WaccParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(WaccParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssignStat(WaccParser.AssignStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssignStat(WaccParser.AssignStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declareStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterDeclareStat(WaccParser.DeclareStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declareStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitDeclareStat(WaccParser.DeclareStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(WaccParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link WaccParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(WaccParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#case_expr}.
	 * @param ctx the parse tree
	 */
	void enterCase_expr(WaccParser.Case_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#case_expr}.
	 * @param ctx the parse tree
	 */
	void exitCase_expr(WaccParser.Case_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identLhs}
	 * labeled alternative in {@link WaccParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void enterIdentLhs(WaccParser.IdentLhsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identLhs}
	 * labeled alternative in {@link WaccParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void exitIdentLhs(WaccParser.IdentLhsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayelemLhs}
	 * labeled alternative in {@link WaccParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void enterArrayelemLhs(WaccParser.ArrayelemLhsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayelemLhs}
	 * labeled alternative in {@link WaccParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void exitArrayelemLhs(WaccParser.ArrayelemLhsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pairelemLhs}
	 * labeled alternative in {@link WaccParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void enterPairelemLhs(WaccParser.PairelemLhsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pairelemLhs}
	 * labeled alternative in {@link WaccParser#assign_lhs}.
	 * @param ctx the parse tree
	 */
	void exitPairelemLhs(WaccParser.PairelemLhsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprRhs}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterExprRhs(WaccParser.ExprRhsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprRhs}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitExprRhs(WaccParser.ExprRhsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayliterRhs}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterArrayliterRhs(WaccParser.ArrayliterRhsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayliterRhs}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitArrayliterRhs(WaccParser.ArrayliterRhsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newpairRhs}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterNewpairRhs(WaccParser.NewpairRhsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newpairRhs}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitNewpairRhs(WaccParser.NewpairRhsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pairelemRhs}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterPairelemRhs(WaccParser.PairelemRhsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pairelemRhs}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitPairelemRhs(WaccParser.PairelemRhsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callRhs}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void enterCallRhs(WaccParser.CallRhsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callRhs}
	 * labeled alternative in {@link WaccParser#assign_rhs}.
	 * @param ctx the parse tree
	 */
	void exitCallRhs(WaccParser.CallRhsContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#arg_list}.
	 * @param ctx the parse tree
	 */
	void enterArg_list(WaccParser.Arg_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#arg_list}.
	 * @param ctx the parse tree
	 */
	void exitArg_list(WaccParser.Arg_listContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fstPairElem}
	 * labeled alternative in {@link WaccParser#pair_elem}.
	 * @param ctx the parse tree
	 */
	void enterFstPairElem(WaccParser.FstPairElemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fstPairElem}
	 * labeled alternative in {@link WaccParser#pair_elem}.
	 * @param ctx the parse tree
	 */
	void exitFstPairElem(WaccParser.FstPairElemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sndPairElem}
	 * labeled alternative in {@link WaccParser#pair_elem}.
	 * @param ctx the parse tree
	 */
	void enterSndPairElem(WaccParser.SndPairElemContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sndPairElem}
	 * labeled alternative in {@link WaccParser#pair_elem}.
	 * @param ctx the parse tree
	 */
	void exitSndPairElem(WaccParser.SndPairElemContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(WaccParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(WaccParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#pair_elem_type}.
	 * @param ctx the parse tree
	 */
	void enterPair_elem_type(WaccParser.Pair_elem_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#pair_elem_type}.
	 * @param ctx the parse tree
	 */
	void exitPair_elem_type(WaccParser.Pair_elem_typeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boollitExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoollitExpr(WaccParser.BoollitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boollitExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoollitExpr(WaccParser.BoollitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqNotEqExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqNotEqExpr(WaccParser.EqNotEqExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqNotEqExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqNotEqExpr(WaccParser.EqNotEqExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusMinusExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPlusMinusExpr(WaccParser.PlusMinusExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusMinusExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPlusMinusExpr(WaccParser.PlusMinusExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(WaccParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(WaccParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayelemExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayelemExpr(WaccParser.ArrayelemExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayelemExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayelemExpr(WaccParser.ArrayelemExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code greaterLessExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterGreaterLessExpr(WaccParser.GreaterLessExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code greaterLessExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitGreaterLessExpr(WaccParser.GreaterLessExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesesExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenthesesExpr(WaccParser.ParenthesesExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesesExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenthesesExpr(WaccParser.ParenthesesExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdentExpr(WaccParser.IdentExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdentExpr(WaccParser.IdentExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryOperExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperExpr(WaccParser.UnaryOperExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryOperExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperExpr(WaccParser.UnaryOperExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitOrExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBitOrExpr(WaccParser.BitOrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitOrExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBitOrExpr(WaccParser.BitOrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pairlitExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPairlitExpr(WaccParser.PairlitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pairlitExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPairlitExpr(WaccParser.PairlitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charlitExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCharlitExpr(WaccParser.CharlitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charlitExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCharlitExpr(WaccParser.CharlitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multDivModExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultDivModExpr(WaccParser.MultDivModExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multDivModExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultDivModExpr(WaccParser.MultDivModExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intlitExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntlitExpr(WaccParser.IntlitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intlitExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntlitExpr(WaccParser.IntlitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitAndExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBitAndExpr(WaccParser.BitAndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitAndExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBitAndExpr(WaccParser.BitAndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringlitExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringlitExpr(WaccParser.StringlitExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringlitExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringlitExpr(WaccParser.StringlitExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(WaccParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link WaccParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(WaccParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#ident}.
	 * @param ctx the parse tree
	 */
	void enterIdent(WaccParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#ident}.
	 * @param ctx the parse tree
	 */
	void exitIdent(WaccParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#array_elem}.
	 * @param ctx the parse tree
	 */
	void enterArray_elem(WaccParser.Array_elemContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#array_elem}.
	 * @param ctx the parse tree
	 */
	void exitArray_elem(WaccParser.Array_elemContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#int_liter}.
	 * @param ctx the parse tree
	 */
	void enterInt_liter(WaccParser.Int_literContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#int_liter}.
	 * @param ctx the parse tree
	 */
	void exitInt_liter(WaccParser.Int_literContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#bool_liter}.
	 * @param ctx the parse tree
	 */
	void enterBool_liter(WaccParser.Bool_literContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#bool_liter}.
	 * @param ctx the parse tree
	 */
	void exitBool_liter(WaccParser.Bool_literContext ctx);
	/**
	 * Enter a parse tree produced by {@link WaccParser#array_liter}.
	 * @param ctx the parse tree
	 */
	void enterArray_liter(WaccParser.Array_literContext ctx);
	/**
	 * Exit a parse tree produced by {@link WaccParser#array_liter}.
	 * @param ctx the parse tree
	 */
	void exitArray_liter(WaccParser.Array_literContext ctx);
}