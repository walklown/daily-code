package com.zzp.learn.walklown.jarkata.processor;

import com.sun.source.tree.Tree;
import com.sun.tools.javac.api.JavacTrees;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.code.*;


/**
 * https://houbb.github.io/2017/10/13/jctree
 * https://blog.mythsman.com/post/5d2c11c767f841464434a3bf/
 *
 * @author walklown
 * @date 2020-10-16
 */
@SupportedAnnotationTypes({"com.zzp.learn.walklown.jarkata.processor.Getter"})
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class GetterProcessor extends AbstractProcessor {

    /**
     * 用于在编译期打印日志
     */
    private Messager messager;

    private JavacTrees javacTrees;

    private TreeMaker treeMaker;

    private Names names;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.javacTrees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment)processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(Getter.class);
        for (Element element : set) {
            JCTree jcTree = javacTrees.getTree(element);
            jcTree.accept(new TreeTranslator() {
                @Override
                public void visitClassDef(JCTree.JCClassDecl tree) {
                    List<JCTree.JCVariableDecl> jcVariableDecls = List.nil();
                    for (JCTree def : tree.defs) {
                        if (tree.getKind().equals(Tree.Kind.VARIABLE)) {
                            JCTree.JCVariableDecl variableDecl = (JCTree.JCVariableDecl) def;
                            jcVariableDecls = jcVariableDecls.append(variableDecl);
                        }
                    }
                    for (JCTree.JCVariableDecl jcVariableDecl : jcVariableDecls) {
                        messager.printMessage(Diagnostic.Kind.NOTE, jcVariableDecl.getName() + " has been processed");
                        tree.defs = tree.defs.prepend(makeGetterMethodDecl(jcVariableDecl));
                    }
                    super.visitClassDef(tree);
                }
            });
        }
        return true;
    }

    private JCTree makeGetterMethodDecl(JCTree.JCVariableDecl jcVariableDecl) {
        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
        statements.append(treeMaker.Return(treeMaker.Select(treeMaker.Ident(names.fromString("this")), jcVariableDecl.getName())));
        JCTree.JCBlock body = treeMaker.Block(0, statements.toList());
        String nameStr = jcVariableDecl.name.toString();
        Name name = names.fromString("get" + nameStr.substring(0, 1).toUpperCase() + nameStr.substring(1));
        return treeMaker.MethodDef(treeMaker.Modifiers(Flags.PUBLIC), name, jcVariableDecl.vartype,
                List.nil(), List.nil(), List.nil(), body, null);
    }
}
