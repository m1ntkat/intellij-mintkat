// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.siyeh.ig.numeric;

import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.PsiTypes;
import com.siyeh.InspectionGadgetsBundle;
import com.siyeh.ig.InspectionGadgetsFix;
import com.siyeh.ig.PsiReplacementUtil;
import org.jetbrains.annotations.NotNull;

class ConvertOctalLiteralToDecimalFix extends InspectionGadgetsFix {

  @Override
  @NotNull
  public String getFamilyName() {
    return InspectionGadgetsBundle.message("convert.octal.literal.to.decimal.literal.quickfix");
  }

  @Override
  protected void doFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
    final PsiElement element = descriptor.getPsiElement();
    if (!(element instanceof PsiLiteralExpression literalExpression)) {
      return;
    }
    replaceWithDecimalLiteral(literalExpression);
  }

  static void replaceWithDecimalLiteral(PsiLiteralExpression literalExpression) {
    final Object value = literalExpression.getValue();
    if (value == null) {
      return;
    }
    final String decimalText = value + (PsiTypes.longType().equals(literalExpression.getType()) ? "L" : "");
    PsiReplacementUtil.replaceExpression(literalExpression, decimalText);
  }
}
